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
    var ruta_ = database.ref('TIME_WINDOWS');

    $("#btnGuardarDatos").click(function (e) {

        var newPostKey = ruta_.push().key;
        console.log(newPostKey);

        var horaInicio = $("#idhorainicio").val();
        var horaFin = $("#idhorafin").val();

        ruta_.child(newPostKey).set({
            startTime: horaInicio,
            endTime: horaFin,
            timeWindowId: newPostKey
        });
        listarTimeWindows();
        $("#modalDefault").hide();
    });

    listarTimeWindows();

    function listarTimeWindows() {
        $("#tableTimeWindows tbody").empty();
        ruta_.once('value').then(function (snapshot) {

            snapshot.forEach(function (childSnapshot) {
                var object = childSnapshot.val();
                console.log(object);
                llenarTimeWindows(object);
            });

        });
    }

    function llenarTimeWindows(timeWindows) {

        var html = " <tr> "
                + " <td>" + timeWindows.timeWindowId + "</td>"
                + " <td>" + timeWindows.startTime + "</td>"
                + " <td>" + timeWindows.endTime + "</td>"
                + " <td><a id='el" + timeWindows.timeWindowId + "' href='#'><i class=\"md md-delete\"></i></a></td>"
                + " </tr>";

        $("#tableTimeWindows tbody").append(html);

        $("#el" + timeWindows.timeWindowId + "").click(function (e) {
            swal({
                title: "Atencion...!",
                text: "Esta seguro de eliminar",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "Si, eliminar!",
                closeOnConfirm: false
            }, function () {
                eliminar(timeWindows);
            });
        });

    }

    function eliminar(timeWindows) {
        ruta_.child(timeWindows.timeWindowId).remove().then(function () {
            swal("Eliminado!", "", "success");
            listarTimeWindows();
        });
        ;

    }

});

