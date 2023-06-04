const URL_SERVICE_MESSAGE = "http://localhost:8080/api/Message/"
const URL_SERVICE_CAR = "http://localhost:8080/api/Car/"
const URL_SERVICE_CLIENT = "http://localhost:8080/api/Client/"
const tHeaders = ['CLIENTE','VEHICULO','MENSAJE'];
var dataTemp;

//función para traer datos
function getMessages() {
  $.ajax({
    url: URL_SERVICE_MESSAGE + "all",
    type: "GET",
    dataType: "json",

    success: function (response) {
      dataTemp = response;
      showMessages(response);
    },
    error: function (xhr, status) {
    //  alert("ERROR");
    },
    complete: function (xhr, status) {
    //  alert("COMPLETED");
    },
  });
}

//función para guardar datos
function saveMessage() {
  let dataMessage = {
    messageText: $("#messagetext").val(),
    client: {idClient:parseInt($("#clients_selector").val())},
    car: {idCar:parseInt($("#cars_selector").val())},
  };

  if ( dataMessage.client.idClient == 0 || dataMessage.car.idCar == 0 || dataMessage.messagetext == '') {
    alert("ERROR, REVISE DATOS EN FORMULARIO");
    return;
  }

  $.ajax({
    url: URL_SERVICE_MESSAGE + "save",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(dataMessage),
    success: function (response) {
      alert("Guardado con éxito");
      cleanData();
    },
    error: function (response, xhr) {
    //  alert("ERROR");
    },
    complete: function (xhr, status) {
      location.href = sessionStorage.getItem("thisUrl");
    },
  });
}

//función para limpiar la pantalla
function cleanData(){
  $("#messagetext").val('');
}

//función para mostrar datos en tabla
function showMessages(data) {
  let idTableHtml = 'messages_table';
  let htmlCode = "";
  let optCar = parseInt($("#cars_selector").val());
  let optClient = parseInt($("#clients_selector").val());

  optCar = (isNaN(optCar))?0:optCar;
  optClient = (isNaN(optClient))?0:optClient;

  //Crear encabezado de tabla
  htmlCode += " <thead><tr>"
  tHeaders.forEach(element => {
      htmlCode += "<th>" + element + "</th>";
  });
  htmlCode += "<th>Editar</th><th>Eliminar</th></tr></thead> ";
      
  //Presentar los datos traídos de BD
  htmlCode += "<tbody>";

  for (let i = 0; i < data.length; i++) {
    if(
      (optCar == 0 && optClient == 0) ||
      (optCar == data[i].car.idCar && optClient == 0) || 
      (optCar == 0 && optClient == data[i].client.idClient) ||  
      (optCar == data[i].car.idCar && optClient == data[i].client.idClient)
      ){
      htmlCode += "<tr class='centrar'>";
      htmlCode += "<td>" + data[i].client.name + "</td>";
      htmlCode += "<td>" + data[i].car.name + "</td>";
      htmlCode += "<td>" + data[i].messageText + "</td>";
      htmlCode += "<td><a href='#' onclick='dataStorageSession(" + data[i].idMessage + ",\"Message\",tHeaders)'>" + "Editar" + "</a></td>";
      htmlCode += "<td><a href='#' onclick='deleteData(" + data[i].idMessage + ",\"Message\")'>" + "Eliminar" + "</a></td>";
      htmlCode += "</tr>";
    }
  }
  htmlCode += "</tbody>";

  //Remplazar datos antiguos con nuevos
  $("#" + idTableHtml).html("");
  $("#" + idTableHtml).empty();
  $("#" + idTableHtml).append(htmlCode);
}

 //función para eliminar datos
function deleteData(id, tableType){
  let dataInfo = { 'id': id
  };
  
  $.ajax({
    url: URL_SERVICE_MESSAGE + id.toString(),
    type: "DELETE",
    contentType: "application/json",
    data: JSON.stringify(dataInfo),
    success: function (response) {
      getMessages();
    },
    error: function (xhr, status) {
    //  alert("ERROR");
    },
    complete: function (xhr, status) {
    //   alert("COMPLETED");
    },
  });
}

////////////////////////////////////
//función para traer datos (CARS)
function getCars() {
  $.ajax({
    url: URL_SERVICE_CAR + "all",
    type: "GET",
    dataType: "json",

    success: function (response) {
      showCars(response);
    },
    error: function (xhr, status) {
    //  alert("ERROR");
    },
    complete: function (xhr, status) {
    // alert("COMPLETED");
    },
  });
}

//función para mostrar datos (Selector CARS)
function showCars(data) {
  let idSelector = 'cars_selector';
  let htmlCode = '';

  htmlCode += '<option value=0>Seleccione CAR</option>'
  for (let i = 0; i < data.length; i++) {
    htmlCode += '<option value="' + data[i].idCar + '">' + data[i].name + '</option>';
  }
  $('#' + idSelector).html(htmlCode);
}

////////////////////////////////////////////////////////
//función para traer datos (CLIENT)
function getClients() {
  let profile = sessionStorage.getItem("rol");
  if(profile=="Admin"){
    $.ajax({
      url: URL_SERVICE_CLIENT + "all",
      type: "GET",
      dataType: "json",

      success: function (response) {
        showClients(response);
      },
      error: function (xhr, status) {
      //  alert("ERROR");
      },
      complete: function (xhr, status) {
      //  alert("COMPLETED");
      },
    });

  } else {
    let idUser = parseInt(sessionStorage.getItem("idUser"));
    $.ajax({
      url: URL_SERVICE_CLIENT + idUser,
      type: "GET",
      dataType: "json",
  
      success: function (response) {
        showClients(response); 
      },
      error: function (xhr, status) {
      //  alert("ERROR");
      },
      complete: function (xhr, status) {
      //  alert("COMPLETED");
      },
    });
  }
}

//función para mostrar datos (Selector CLIENTS)
function showClients(data) {
  let profile = sessionStorage.getItem("rol");
  let idSelector = 'clients_selector';
  let htmlCode = '';

  if(profile=="Admin"){
    htmlCode += '<option value=0>Seleccione CLIENT</option>'
    for (let i = 0; i < data.length; i++) {
      htmlCode += '<option value="' + data[i].idClient + '">' + data[i].name + '</option>';
    }
  } else {
    htmlCode += '<option value="' + data.idClient + '">' + data.name + '</option>';
    $('#' + idSelector).prop('disabled', true);
  }
  $('#' + idSelector).html(htmlCode);
}
