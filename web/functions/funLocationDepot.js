/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (e) {
    var mainDepot;
    var map;
    var marker;
    var config = {
        apiKey: "AIzaSyAmXZWep3lUwoyGTX8xnvX8Cwan1qdFuc8",
        authDomain: "kewin-6f6cb.firebaseapp.com",
        databaseURL: "https://kewin-6f6cb.firebaseio.com",
        storageBucket: "kewin-6f6cb.appspot.com",
        messagingSenderId: "1092748913494"
    };
    firebase.initializeApp(config);
    var database = firebase.database();
    var ruta_ = database.ref('DEPOSITO');

    function initMap(mainDepot) {
        console.log("MAP");
        var location = new google.maps.LatLng(mainDepot.location.latitude, mainDepot.location.longitude);

        map = new google.maps.Map(document.getElementById('map'), {
            center: location,
            zoom: 8
        });
        console.log(map);
        agregarMarker(location);
    }
    listarDeposito();
    function listarDeposito() {
        $("#tableDeposito tbody").empty();
        ruta_.once('value').then(function (snapshot) {

            var object = snapshot.val();
            console.log(object);
            llenarDatosDeposito(object);
            initMap(object);
        });
    }

    function llenarDatosDeposito(deposito) {
        var html = " <tr>"
                + "  <td>" + deposito.nodeDepotId + "</td>"
                + "  <td>" + deposito.name + "</td>"
                + "  <td>" + deposito.capacity + "</td>"
                + "  <td>" + deposito.location.address + "</td>"
                + "  <td><a href=\"#\" id='edit" + deposito.nodeDepotId + "' ><i class=\"md md-edit\"></i></a></td>"
                + "  </tr>";
        $("#tableDeposito tbody").append(html);

        $("#edit" + deposito.nodeDepotId + "").click(function (e) {
            mainDepot = deposito;

            $("#modalDefault").modal("show");
            $("#idnombre").val(deposito.name);
            $("#idcapacidad").val(deposito.capacity);
            $("#iddireccion").val(deposito.location.address);
            $("#idlatitud").val(deposito.location.latitude);
            $("#idlongitud").val(deposito.location.longitude);
            initMap(mainDepot);
        });
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
    function setLatLng(lat, long) {
        $("#idlatitud").val(lat);
        $("#idlongitud").val(long);
    }

    $("#btnGuardarDatos").click(function (e) {

        $("#modalDefault").modal("hide");
        var nombre = $("#idnombre").val();
        var capacidad = $("#idcapacidad").val();
        var direccion = $("#iddireccion").val();
        var latitud = $("#idlatitud").val();
        var longitud = $("#idlongitud").val();

        ruta_.set({
            nodeDepotId: mainDepot.nodeDepotId,
            name: nombre,
            capacity: capacidad,
            location: {
                address: direccion,
                latitude: latitud,
                longitude: longitud,
                locationId: mainDepot.location.locationId
            }
        });

        listarDeposito();
    });

});


