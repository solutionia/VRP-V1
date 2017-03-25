/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (e) {


    var map;
    var marker;
    var location = new google.maps.LatLng(-12.046374, -77.042793);
    var mainLocation;
    var mainCliente;
    var allMarkers = [];
    var mainCliente;
    var mainTimeWindows;

    var config = {
        apiKey: "AIzaSyAmXZWep3lUwoyGTX8xnvX8Cwan1qdFuc8",
        authDomain: "kewin-6f6cb.firebaseapp.com",
        databaseURL: "https://kewin-6f6cb.firebaseio.com",
        storageBucket: "kewin-6f6cb.appspot.com",
        messagingSenderId: "1092748913494"
    };
    firebase.initializeApp(config);
    var database = firebase.database();
    var ruta_ = database.ref('CLIENTE');

    initMap();
    $("#btnGuardarDatos").click(function (e) {

        var newPostKey = ruta_.push().key;
        console.log(newPostKey);

        var descripcion = $("#iddescripcion").val();
        var ubicacion = $("#idubicacion").val();
        var cantidadorden = $("#idcantidadorden").val();
        var ventanatiempo = $("#idventanatiempo").val();

        ruta_.child(newPostKey).set({

            description: descripcion,
            location: ubicacion,
            nodeClientId: newPostKey,
            quantityOrder: cantidadorden,
            sequence: 0,
            timeWindow: ventanatiempo
        });
        listarClientes();
        $("#modalDefault").hide();

    });
    listarClientes();

    function listarClientes() {
        $("#tableCliente tbody").empty();
        ruta_.once('value').then(function (snapshot) {

            snapshot.forEach(function (childSnapshot) {
                var object = childSnapshot.val();
                console.log(object);
                llenarDatosClientes(object);
            });

        });
    }

    function llenarDatosClientes(cliente) {

        var html = " <tr> "
                + " <td>" + cliente.nodeClientId + "</td>"
                + " <td>" + cliente.description + "</td>"
                + " <td><a id='lo" + cliente.nodeClientId + "' href=\"#\" >" + cliente.location.address + "</a></td>"
                + " <td>" + cliente.quantityOrder + "</td>"
                + " <td><a id='tw" + cliente.nodeClientId + "' href=\"#\">" + cliente.timeWindow.startTime + " - " + cliente.timeWindow.endTime + "</a></td>"
                + "<td> <a id='el" + cliente.nodeClientId + "' href='#'><i class=\"md md-delete\"></i></a> </td>"
                + " </tr>";

        $("#tableCliente tbody").append(html);

        $('#lo' + cliente.nodeClientId).click(function (e) {
            $('#modalDefaultMap').modal('show');
            mainCliente = cliente;
            initMap();

        });

        $('#tw' + cliente.nodeClientId).click(function (e) {
            $("#modalDefaultTimeWindows").modal("show");
            listarTimeWindows();
            mainCliente = cliente;

        });

        $("#el" + cliente.nodeClientId + "").click(function (e) {
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
        ruta_.child(cliente.nodeClientId).remove().then(function () {
            swal("Eliminado!", "", "success");
            listarClientes();
        });
        ;

    }

    function listarTimeWindows() {
        $("#selectTimeWindows").empty();
        var ruta_ = database.ref('TIME_WINDOWS');
        ruta_.once('value').then(function (snapshot) {

            snapshot.forEach(function (childSnapshot) {
                var object = childSnapshot.val();
                console.log(object);
                llenarTimeWindows(object);
            });

        });
    }

    function llenarTimeWindows(timeWindows) {

        var object = JSON.stringify(timeWindows);
        var html = " <option value='" + object + "' > "
                + "INICIO: " + timeWindows.startTime + " - "
                + "FIN: " + timeWindows.endTime + ""
                + " </option>";

        $("#selectTimeWindows").append(html);

    }



    $("#btnGuardarTimeWindows").click(function (e) {

        mainTimeWindows = $("#selectTimeWindows").val();

        ruta_.child(mainCliente.nodeClientId).set({

            description: mainCliente.description,
            location: mainCliente.location,
            nodeClientId: mainCliente.nodeClientId,
            quantityOrder: mainCliente.quantityOrder,
            sequence: 0,
            timeWindow: JSON.parse(mainTimeWindows)
        });
        $("#modalDefaultTimeWindows").modal("hide");
        listarClientes();

    });

    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: location,
            zoom: 8
        });
        agregarMarkers();
    }

    function agregarMarkers() {

        var ruta_LO = database.ref('LOCATION');
        ruta_LO.once('value').then(function (snapshot) {

            snapshot.forEach(function (childSnapshot) {

                var marker;
                var object = childSnapshot.val();
                var location = new google.maps.LatLng(object.latitude, object.longitude);

                marker = new google.maps.Marker({
                    position: location,
                    map: map
                });
                marker.metadata = JSON.parse(JSON.stringify(object));
                console.log("MetaData");
                console.log(marker.metadata);

                marker.addListener('click', function (event) {
                    stopAllAnimation();
                    marker.setAnimation(google.maps.Animation.BOUNCE);
                    mainLocation = marker.metadata;
                    console.log("LOCATION");
                });

                allMarkers.push(marker);

            });
        });


    }

    function stopAllAnimation() {
        for (var i = 0; i < allMarkers.length; i++) {
            allMarkers[i].setAnimation(null);
        }
    }

    $("#btnGuardarUbicacion").click(function (e) {
        ruta_.child(mainCliente.nodeClientId).set({

            description: mainCliente.description,
            location: mainLocation,
            nodeClientId: mainCliente.nodeClientId,
            quantityOrder: mainCliente.quantityOrder,
            sequence: 0,
            timeWindow: mainCliente.timeWindow
        });
        listarClientes();
        $("#modalDefaultMap").modal("hide");
    });


});


