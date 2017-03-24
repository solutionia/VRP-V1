/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function (ev) {

    var mainVehiculo;

    $('body').on('click', '#btn-color-targets > .btn', function () {
        var color = $(this).data('target-color');
        $('#modalColor').attr('data-modal-color', color);
    });
    var config = {
        apiKey: "AIzaSyAmXZWep3lUwoyGTX8xnvX8Cwan1qdFuc8",
        authDomain: "kewin-6f6cb.firebaseapp.com",
        databaseURL: "https://kewin-6f6cb.firebaseio.com",
        storageBucket: "kewin-6f6cb.appspot.com",
        messagingSenderId: "1092748913494"
    };
    firebase.initializeApp(config);
    var database = firebase.database();
    var ruta_ = database.ref('VEHICULO');

    $("#btnGuardarDatos").click(function (e) {

        var newPostKey = ruta_.push().key;
        console.log(newPostKey);

        var nombre = $("#idnombre").val();
        var placa = $("#idplaca").val();
        var tipo = $("#idtipo").val();
        ruta_.child(newPostKey).set({
            carPlate: placa,
            description: nombre,
            vehicleId: newPostKey,
            vehicleType: tipo
        });
        listarVehiculos();
        $("#modalDefault").hide();
    });

    listarVehiculos();

    function listarVehiculos() {
        $("#tableVehiculos tbody").empty();
        ruta_.once('value').then(function (snapshot) {

            snapshot.forEach(function (childSnapshot) {
                var object = childSnapshot.val();
                console.log(object);
                llenarDatosVehiculos(object);
            });

        });
    }

    function llenarDatosVehiculos(vehiculo) {

        var html = " <tr> "
                + " <td>" + vehiculo.vehicleId + "</td>"
                + " <td>" + vehiculo.description + "</td>"
                + " <td>" + vehiculo.carPlate + "</td>"
                + " <td> <a id='ve" + vehiculo.vehicleId + "' href='#'>" + vehiculo.vehicleType.capacity + " - "+vehiculo.vehicleType.costPerDistanceKm+"</a></td>"
                + " </tr>";

        $("#tableVehiculos tbody").append(html);

        $('#ve' + vehiculo.vehicleId).click(function (e) {
            showUpdateTipo(vehiculo);
        });
    }

    llenartipo();

    function llenartipo() {
        var ruta_TIPO = database.ref('TYPO_VEHICULO');

        ruta_TIPO.once('value').then(function (snapshot) {

            snapshot.forEach(function (childSnapshot) {
                var object = childSnapshot.val();
                var jsonObject = JSON.stringify(object);
                console.log("object");
                console.log(object);
                var html = "<option data-value='"+jsonObject+"'>CA: " + object.capacity + " - $$: " + object.costPerDistanceKm + "</option>";
                $("#selecttipo").append(html);



            });

        });

    }

    $("#selecttipo").change(function (e) {


    });

    function showUpdateTipo(vehiculo) {
        mainVehiculo = vehiculo;
        $("#modalTypoVehiculo").modal("show");
    }

    $("#btnGuardartipo").click(function (e) {
        var typo = $("#selecttipo").find(":selected").data("value");
        console.log("typo");
        console.log(typo);
        ruta_.child(mainVehiculo.vehicleId).set({
            carPlate: mainVehiculo.carPlate,
            description: mainVehiculo.description,
            vehicleId: mainVehiculo.vehicleId,
            vehicleType: {
                capacity: typo.capacity,
                costPerDistanceKm: typo.costPerDistanceKm,
                vehicleTypeId: typo.vehicleTypeId
            }
        });
        listarVehiculos();
    });


});

