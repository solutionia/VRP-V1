/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (e) {
    $body = $("body");
    var map;
    var mainClientes = new Array();
    var mainVehiculos = new Array();
    var location = new google.maps.LatLng(-12.046374, -77.042793);
    initMap();
    function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
            center: location,
            zoom: 8
        });
    }

    var config = {
        apiKey: "AIzaSyAmXZWep3lUwoyGTX8xnvX8Cwan1qdFuc8",
        authDomain: "kewin-6f6cb.firebaseapp.com",
        databaseURL: "https://kewin-6f6cb.firebaseio.com",
        storageBucket: "kewin-6f6cb.appspot.com",
        messagingSenderId: "1092748913494"
    };
    firebase.initializeApp(config);
    var database = firebase.database();
    var ruta_vehiculo = database.ref('VEHICULO');
    var ruta_cliente = database.ref('CLIENTE');
    listarVehiculos();
    function listarVehiculos() {
        $("#tableVehiculos tbody").empty();
        ruta_vehiculo.once('value').then(function (snapshot) {

            snapshot.forEach(function (childSnapshot) {
                var object = childSnapshot.val();
                console.log(object);
                llenarDatosVehiculos(object);
            });
        });
    }

    function llenarDatosVehiculos(vehiculo) {
        var object = JSON.stringify(vehiculo);
        var html = " <tr> "

                + " <td>" + vehiculo.description + "</td>"
                + " <td>" + vehiculo.carPlate + "</td>"
                + " <td> <a id='ve" + vehiculo.vehicleId + "' href='#'>" + vehiculo.vehicleType.capacity + " - " + vehiculo.vehicleType.costPerDistanceKm + "</a></td>"
                + "<td><input class='vehiculo' value='" + object + "' type=\"checkbox\" /></td>"
                + " </tr>";
        $("#tableVehiculos tbody").append(html);
    }


    listarClientes();
    function listarClientes() {
        $("#tableCliente tbody").empty();
        ruta_cliente.once('value').then(function (snapshot) {

            snapshot.forEach(function (childSnapshot) {
                var object = childSnapshot.val();
                console.log(object);
                llenarDatosClientes(object);
            });
        });
    }

    function llenarDatosClientes(cliente) {
        var object = JSON.stringify(cliente);
        var html = " <tr> "

                + " <td>" + cliente.description + "</td>"
                + " <td><a id='lo" + cliente.nodeClientId + "' href=\"#\" >" + cliente.location.address + "</a></td>"
                + " <td>" + cliente.quantityOrder + "</td>"
                + " <td><a id='tw" + cliente.nodeClientId + "' href=\"#\">" + cliente.timeWindow.startTime + " - " + cliente.timeWindow.endTime + "</a></td>"
                + "<td><input class='client' id='ra" + cliente.nodeClientId + "'  value='" + object + "' type=\"checkbox\" /></td>"
                + " </tr>";
        $("#tableCliente tbody").append(html);
        $("#ra" + cliente.nodeClientId + "").click(function (e) {

            agregarMarker();
        });
    }
    var gmarkers = [];
    function agregarMarker() {
        removeMarkers();
        $('#tableCliente > tbody > tr > td').each(function (e) {

            if ($(this).children(".client").val() !== undefined) {
                if ($(this).children(".client").is(':checked')) {

                    var cliente = JSON.parse($(this).children(".client").val());
                    var infowindow = new google.maps.InfoWindow({
                        content: "<span>" + cliente.description + "</span>"
                    });
                    var location = new google.maps.LatLng(cliente.location.latitude, cliente.location.longitude);
                    console.log($(this).children(".client").val());
                    var marker = new google.maps.Marker({
                        position: location,
                        map: map,
                        title: cliente.description
                    });
                    gmarkers.push(marker);
                    google.maps.event.addListener(marker, 'click', function () {
                        infowindow.open(map, marker);
                    });
                }
            }
        });
    }



    function removeMarkers() {
        for (i = 0; i < gmarkers.length; i++) {
            gmarkers[i].setMap(null);
        }

    }

    function obtenerdatosClientes() {

        $('#tableCliente > tbody > tr > td').each(function (e) {

            if ($(this).children(".client").val() !== undefined) {
                if ($(this).children(".client").is(':checked')) {

                    var object = JSON.parse($(this).children(".client").val());
                    //console.log(object);
                    mainClientes.push(object);
                }
            }
        });
    }


    function obtenerdatosVehiculos() {

        $('#tableVehiculos > tbody > tr > td').each(function (e) {

            if ($(this).children(".vehiculo").val() !== undefined) {

                if ($(this).children(".vehiculo").is(':checked')) {
                    var object = JSON.parse($(this).children(".vehiculo").val());
                    //console.log(object);
                    mainVehiculos.push(object);
                }
            }

        });
    }


    $("#btnPlanificar").click(function (e) {
        obtenerdatosClientes();
        obtenerdatosVehiculos();
        builProblem();
    });
    function builProblem() {
        var data = JSON.stringify(getJsonObjectProblem());

        console.log(data);
        var url = "ServiceApi/VRPService";
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: url,
            dataType: "json",
            data: data,
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                 $body.removeClass("loading");

            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('addWine error: ' + errorThrown);
                $body.removeClass("loading");
            },
            beforeSend: function () {
                $body.addClass("loading");
            }
        });
    }


    function getJsonObjectProblem() {
        var object =
                {
                    "apiKeyGoogle": "AIzaSyCagC0TKLKuZ_1whR2PFpU4lU9yUmFfwWY",
                    "clients": mainClientes,
                    "isFinite": false,
                    "dateCurrent": "29/01/2017",
                    "description": "Generar solucion para la empresa Energigas SAC",
                    "node_Depot": {
                        "capacity": 10000,
                        "location": {
                            "address": "Av. Nicolás de Ayllón 7548, Lima",
                            "codigoUbigeo": "150103",
                            "latitude": -12.0178427,
                            "locationId": "LOC0002",
                            "longitude": -76.900919
                        },
                        "name": "Deposito Central",
                        "nodeDepotId": "DEP0001"
                    },
                    "problemId": "PRO0001",
                    "vehicles": mainVehiculos
                };

        return object;

    }


});
