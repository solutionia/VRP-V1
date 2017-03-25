/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function (e) {

    $body = $("body");
    var map;
    var directionsService = new google.maps.DirectionsService();
    var locationDepot = new google.maps.LatLng(-12.046374, -77.042793);
    var mainArrayService = [];
    initMap();
    var colors = ['red', 'green', 'blue', 'orange', 'yellow'];
    function initMap() {

        map = new google.maps.Map(document.getElementById('map'), {
            center: locationDepot,
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
        var mainClientes = new Array();
        mainClientes.length = 0;
        $('#tableCliente > tbody > tr > td').each(function (e) {

            if ($(this).children(".client").val() !== undefined) {
                if ($(this).children(".client").is(':checked')) {

                    var object = JSON.parse($(this).children(".client").val());

                    mainClientes.push(object);
                }
            }
        });

        return mainClientes;
    }


    function obtenerdatosVehiculos() {
        var mainVehiculos = new Array();
        mainVehiculos.length = 0;
        $('#tableVehiculos > tbody > tr > td').each(function (e) {

            if ($(this).children(".vehiculo").val() !== undefined) {

                if ($(this).children(".vehiculo").is(':checked')) {
                    var object = JSON.parse($(this).children(".vehiculo").val());
                    mainVehiculos.push(object);
                }
            }

        });
        return mainVehiculos;
    }


    function getJsonObjectProblem(clientes, vehiculos) {
        var object =
                {
                    "apiKeyGoogle": "AIzaSyCagC0TKLKuZ_1whR2PFpU4lU9yUmFfwWY",
                    "clients": clientes,
                    "isFinite": true,
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
                    "vehicles": vehiculos
                };

        return object;

    }


    $("#btnPlanificar").click(function (e) {
        var clientes = obtenerdatosClientes();
        var vehiculos = obtenerdatosVehiculos();
        console.log(clientes);
        console.log(vehiculos);
        builProblem(clientes, vehiculos);
    });
    function builProblem(clientes, vehiculos) {
        var data = JSON.stringify(getJsonObjectProblem(clientes, vehiculos));

        // console.log(data);
        var url = "ServiceApi/VRPService";
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: url,
            dataType: "json",
            data: data,
            success: function (data, textStatus, jqXHR) {
                //console.log(data);
                initMap();
                $body.removeClass("loading");
                listarResultado(data);

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

    function guardarResultado(solution) {
        var sol = JSON.parse(JSON.stringify(solution));
        var ruta_solucion = database.ref('SOLUTIONS');
        var newPostKey = ruta_solucion.push().key;
        ruta_solucion.child(newPostKey).set({
            solution:solution
        });
    }

    function listarUnassignedClients(data) {
        $("#divUnassignedClients").css("display", "block");
        $("#tablaUnassignedClients tbody").empty();
        data.unassignedClients.forEach(function (cliente) {
            console.log("divUnassignedClients");
            console.log(cliente);
            var html = "    <tr>"
                    + "  <td>" + cliente.description + "</td>"
                    + "  <td>" + cliente.quantityOrder + "</td>"
                    + "   <td>" + cliente.timeWindow.startTime + " - " + cliente.timeWindow.endTime + "</td>"
                    + "  </tr>";
            $("#tablaUnassignedClients tbody").append(html);

        });
    }

    function listarResultado(data) {

        console.log('La ruta no fue completada');
        console.log(data);
        guardarResultado(data);
        if (data.isSuccess) {

            if (!data.isRutesCompleted) {
                notify('Rutas completadas parcialmente', 'notice');
                listarUnassignedClients(data);

            } else {
                notify('Rutas completadas correctamente', 'success');
                
            }

        } else {
            notify('Rutas no completadas', 'error');
            return;
        }


        agregarDeposito(data.depots);
        $("#tablaResultado tbody").empty();
        removeMarkers();
        var i = 0;
        data.rutes.forEach(function (ruta) {
            mainArrayService = [];
            var location = new google.maps.LatLng(data.depots.location.latitude, data.depots.location.longitude);
            mainArrayService.push(location);
            llenarDatos(ruta, colors[i]);
            mainArrayService.push(location);
            calcRoute(colors[i]);
            i++;
        });


    }

    function agregarDeposito(depot) {
        var infowindow = new google.maps.InfoWindow({
            content: "<span>" + depot.name + " -  " + depot.capacity + "</span>"
        });
        var location = new google.maps.LatLng(depot.location.latitude, depot.location.longitude);

        var marker = new google.maps.Marker({
            position: location,
            map: map,
            title: depot.name,
            icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png'
        });
        google.maps.event.addListener(marker, 'click', function () {
            infowindow.open(map, marker);
        });
    }

    function llenarDatos(ruta, color) {

        var htmlHead = "<tr>"
                + "  <td><a href='#' style='color:" + color + "' > [---] &nbsp &nbsp <a/><img  width=\"40px\" src=\"https://image.flaticon.com/icons/svg/25/25430.svg\" /> <span>" + ruta.vehicle.description + " - " + ruta.vehicle.carPlate + "</span></td>"
                + " <td>";
        var htmlBody = "";
        var htmlFooter = "  </td>"
                + " </tr>";

        ruta.clients.forEach(function (cliente) {
            console.log(cliente);
            htmlBody = htmlBody + "<ul class=\"main - menu\">"
                    + "      <li><span><i class=\"md md - home\"></i>" + cliente.description + " - <a href='#'> " + cliente.timeWindow.startTime + " - " + cliente.timeWindow.endTime + "</a></span></li>"
                    + "  </ul>";
            agregarMarkerCliente(cliente);
            var location = new google.maps.LatLng(cliente.location.latitude, cliente.location.longitude);

            mainArrayService.push(location);
        });
        $("#tablaResultado tbody").append(htmlHead + htmlBody + htmlFooter);
    }

    function agregarMarkerCliente(cliente) {

        var infowindow = new google.maps.InfoWindow({
            content: "<span> " + cliente.sequence + " - " + cliente.description + "</span>"
        });
        var location = new google.maps.LatLng(cliente.location.latitude, cliente.location.longitude);

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
    var rendererOptions = {
        preserveViewport: true,
        suppressMarkers: true
    };
    function drawRoute(origen, destino, color) {
        var start = origen;
        //var end = new google.maps.LatLng(38.334818, -181.884886);
        var end = destino;
        var request = {
            origin: start,
            optimizeWaypoints: true,
            destination: end,
            travelMode: google.maps.TravelMode.DRIVING
        };

        var directionsDisplay = new google.maps.DirectionsRenderer(rendererOptions);
        directionsDisplay.setMap(map);

        directionsService.route(request, function (response, status) {
            if (status == google.maps.DirectionsStatus.OK) {
                directionsDisplay.setDirections(response);
                directionsDisplay.setMap(map);
                directionsDisplay.setOptions({suppressMarkers: true, polylineOptions: {
                        strokeColor: color
                    }});
            } else {
                alert("Directions Request from " + start.toUrlValue(6) + " to " + end.toUrlValue(6) + " failed: " + status);
            }
        });
    }

    function calcRoute(color) {
        console.log(mainArrayService.length);
        for (var i = 1; i < mainArrayService.length; i++) {
            var objectOrigen = mainArrayService[i - 1];
            var objectDestino = mainArrayService[i];
            drawRoute(objectOrigen, objectDestino, color);
        }
    }


    function notify(message, type) {
        $.growl({
            message: message,
            type: type
        });
    }



});
