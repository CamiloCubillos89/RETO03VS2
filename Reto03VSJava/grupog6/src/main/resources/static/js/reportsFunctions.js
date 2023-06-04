const URL_SERVICE_RESERVATION = 'http://localhost:8080/api/Reservation/';
const URL_SERVICE_CLIENT = 'http://localhost:8080/api/Client/';
const tHeadersReservation = ["ID","F. INICIO","F. FIN","ID CLIENTE","CLIENTE", "VEHICULO","CALIFICACIÓN"];
const tHeadersClientsTop = ["PUESTO N°","ID", "NOMBRE","TOTAL RESERVAS"];

//Función para reporte de reservación
function reservationDates(){
    let startDate = $("#startDate").val();
    let endDate= $("#endDate").val();

    if (startDate == "" || endDate == "") {
        alert("Indique las fechas de reservación");
        return;
    } 
    $.ajax({
        url: URL_SERVICE_RESERVATION + 'report-dates/' +startDate + '/' + endDate,
        type: "GET",
        dataType: "json",
 
        success: function(response){
            $("#report-dates").text(response.length);
            reservationsDates(response);
        }, 
        error: function(xhr, status){
          //  alert("ERROR");
        },
        complete: function(xhr, status){
          //  alert("COMPLETED");
        },
    });
}

 //función para mostrar datos en tabla
function reservationsDates(data){
    let idTableHtml = 'table_reservations_dates';
    let htmlCode = "";
  
    //Crear encabezado de tabla
    htmlCode += " <thead><tr>"
    tHeadersReservation.forEach(element => {
        htmlCode += "<th>" + element + "</th>";
    });
        
    //Presentar los datos traídos de BD
    htmlCode += "<tbody>";
  
    for (let i = 0; i < data.length; i++) {
        htmlCode += "<tr class='centrar'>";
        htmlCode += "<td>" + data[i].idReservation + "</td>";
        htmlCode += "<td>" + data[i].startDate.split('T')[0] + "</td>";
        htmlCode += "<td>" + data[i].devolutionDate.split('T')[0] + "</td>";
        htmlCode += "<td>" + data[i].client.idClient + "</td>";
        htmlCode += "<td>" + data[i].client.name + "</td>";
        htmlCode += "<td>" + data[i].car.name + "</td>";
        htmlCode += "<td>" + ((data[i].score==null)?"No calificado":data[i].score.result) + "</td>";
        htmlCode += "</tr>";
    }
    htmlCode += "</tbody>";
  
    //Remplazar datos antiguos con nuevos 
    $("#" + idTableHtml).html("");
    $("#" + idTableHtml).empty();
    $("#" + idTableHtml).append(htmlCode);
}

//Función para mostrar y ocultar reservas
function showReservationsDates(){
    let startDate = $("#startDate").val();
    let endDate= $("#endDate").val();
    if (startDate == "" || endDate == "") {
        alert("Indique las fechas de reservación");
        return;
    } 
    if ($('#table_reservations_dates').is(':visible')) {
        $('#table_reservations_dates').hide();
        $("#link_reservations_date").text('Ver reservas');
    } else {
        $('#table_reservations_dates').show();
        $("#link_reservations_date").text('Ocultar reservas');
    }
}

//función para mostrar estados de reservas
function reservationStatus(){
    $.ajax({
        url: URL_SERVICE_RESERVATION + 'report-status-query',
        type: "GET",
        dataType: "json",

        success: function(response){
            $("#completed").text(response.completed);
            $("#cancelled").text(response.cancelled);
        },
        error: function(xhr, status){
          //  alert("ERROR");
        },
        complete: function(xhr, status){
          //  alert("COMPLETED");
        },
    });
}

//función para mostrar clientes top
function topClients(){
    $.ajax({
        url: URL_SERVICE_RESERVATION + 'report-clients',
        type: "GET",
        dataType: "json",

        success: function(response){
            showTopClients(response);
        },
        error: function(xhr, status){
          //  alert("ERROR");
        },
        complete: function(xhr, status){
          //  alert("COMPLETED");
        },
    });
}
//función para mostrar datos en tabla
function showTopClients(data){
    let idTableHtml = 'report-clients';
    let htmlCode = "";
  
    //Crear encabezado de tabla
    htmlCode += " <thead><tr>"
    tHeadersClientsTop.forEach(element => {
        htmlCode += "<th>" + element + "</th>";
    });
        
    //Presentar los datos traídos de BD
    htmlCode += "<tbody>";  
    for (let i = 0; i < data.length; i++) {
        htmlCode += "<tr class='centrar'>";
        htmlCode += "<td>" + (i+1) + "</td>";
        htmlCode += "<td>" + data[i].client.idClient + "</td>";
        htmlCode += "<td>" + data[i].client.name + "</td>";
        htmlCode += "<td>" + data[i].total + "</td>";
        htmlCode += "</tr>";
    }
    htmlCode += "</tbody>";
  
    //Remplazar datos antiguos con nuevos
    $("#" + idTableHtml).html("");
    $("#" + idTableHtml).empty();
    $("#" + idTableHtml).append(htmlCode);
}

/////////////////////////////////////////
//Función para validación de rango fechas
$(document).ready(function() {
    $('#startDate').on('change', function() {
      let startDate = new Date($(this).val());
      let endDate = new Date($('#endDate').val());
      if (startDate > endDate) {
        alert('ERROR, REVISE FECHAS');
        $(this).val('');
      }
    });
    
    $('#endDate').on('change', function() {
      let endDate = new Date($(this).val());
      let startDate = new Date($('#startDate').val());
      if (endDate < startDate) {
        alert('ERROR, REVISE FECHAS');
        $(this).val('');
      }
    });
  });