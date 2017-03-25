/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (e) {

    var map;
    var marker;
    var location = new google.maps.LatLng(-12.046374, -77.042793);

    initMap();
    var config = {
        apiKey: "AIzaSyAmXZWep3lUwoyGTX8xnvX8Cwan1qdFuc8",
        authDomain: "kewin-6f6cb.firebaseapp.com",
        databaseURL: "https://kewin-6f6cb.firebaseio.com",
        storageBucket: "kewin-6f6cb.appspot.com",
        messagingSenderId: "1092748913494"
    };
    firebase.initializeApp(config);
    var database = firebase.database();
    var ruta_ = database.ref('LOCATION');


    $("#btnGuardarDatos").click(function (e) {

        var newPostKey = ruta_.push().key;
        console.log(newPostKey);

        var direccion = $("#iddireccion").val();
        var codigoUbigeo = $("#idcodigoubigeo").val();
        var latitud = $("#idlatitud").val();
        var longitud = $("#idlongitud").val();

        ruta_.child(newPostKey).set({

            address: direccion,
            codigoUbigeo: codigoUbigeo,
            latitude: latitud,
            locationId: newPostKey,
            longitude: longitud
        });
        listarLocation();

        $('#modalDefault').modal('hide');
        // $("#modalDefault").hide();

    });
    listarLocation();

    function listarLocation() {
        $("#tableLocation tbody").empty();
        ruta_.once('value').then(function (snapshot) {

            snapshot.forEach(function (childSnapshot) {
                var object = childSnapshot.val();
                console.log(object);
                llenarDatosLocation(object);
            });

        });
    }

    function llenarDatosLocation(cliente) {

        var html = " <tr> "
                + " <td>" + cliente.locationId + "</td>"
                + " <td>" + cliente.address + "</td>"
                + " <td>" + cliente.codigoUbigeo + "</td>"
                + " <td>" + cliente.latitude + "</td>"
                + " <td>" + cliente.longitude + "</td>"
                + " <td><a id='el" + cliente.locationId + "' href='#'><i class=\"md md-delete\"></i></a></td>"

                + " </tr>";

        $("#tableLocation tbody").append(html);
        $("#el" + cliente.locationId + "").click(function (e) {
            swal({
                title: "Atencion...!",
                text: "Esta seguro de eliminar",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Si, eliminar!",
                closeOnConfirm: false
            }, function () {
                eliminar(cliente);
            });
        });
    }
    function eliminar(cliente) {
        ruta_.child(cliente.locationId).remove().then(function () {
            swal("Eliminado!", "", "success");
            listarLocation();
        });
        ;

    }

    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: location,
            zoom: 8
        });

        agregarMarker(location);
    }

    function agregarMarker(location) {
        marker = new google.maps.Marker({
            position: location,
            draggable: true,
            map: map
        });
        setLatLng(location.lat(), location.lng());

        marker.addListener('drag', function (event) {
            setLatLng(event.latLng.lat(), event.latLng.lng());
        });
    }

    $("#btnAgregar").click(function (ev) {
        $('#modalDefault').modal('show');
        initMap();

    });

    function setLatLng(lat, long) {
        $("#idlatitud").val(lat);
        $("#idlongitud").val(long);
    }




});


