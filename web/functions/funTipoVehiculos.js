/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function (ev) {

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
    var ruta_ = database.ref('TYPO_VEHICULO');

    $("#btnGuardarDatos").click(function (e) {
  $("#modalDefault").modal("hide");
        var newPostKey = ruta_.push().key;
        console.log(newPostKey);

        var capacidad = $("#idcapacidad").val();
        var costo = $("#idcosto").val();
        ruta_.child(newPostKey).set({
            capacity: capacidad,
            costPerDistanceKm: costo,
            vehicleTypeId: newPostKey

        });
      
        listarTypeVehiculo();
    });



    listarTypeVehiculo();

    function listarTypeVehiculo() {
        $("#tableTipoVehiculo tbody").empty();
        ruta_.once('value').then(function (snapshot) {

            snapshot.forEach(function (childSnapshot) {
                var object = childSnapshot.val();
                console.log(object);
                llenarTypeVehiculo(object);
            });

        });
    }

    function llenarTypeVehiculo(vehiculo) {

        var html = " <tr> "
                + " <td>" + vehiculo.vehicleTypeId + "</td>"
                + " <td>" + vehiculo.capacity + "</td>"
                + " <td>" + vehiculo.costPerDistanceKm + "</td>"
                + " <td><a id='el" + vehiculo.vehicleTypeId + "' href='#'><i class=\"md md-delete\"></i></a></td>"
                + " </tr>";

        $("#tableTipoVehiculo tbody").append(html);
        $("#el" + vehiculo.vehicleTypeId + "").click(function (e) {
            swal({
                title: "Atencion...!",
                text: "Esta seguro de eliminar",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Si, eliminar!",
                closeOnConfirm: false
            }, function () {
                eliminar(vehiculo);
            });
        });

    }
    function eliminar(vehiculo) {
        ruta_.child(vehiculo.vehicleTypeId).remove().then(function () {
            swal("Eliminado!", "", "success");
            listarTypeVehiculo();
        });
        ;

    }


});

