const URL_SERVICE_CLIENT = 'http://localhost:8080/api/Client/'
const tableHeaders = ['NOMBRE', 'CORREO', 'EDAD'];

//función para traer datos
function getClients() {
    $.ajax({
      url: URL_SERVICE_CLIENT + 'all',
      type: "GET",
      dataType: "json",
  
      success: function (response) {
        showClients(response);
      },
      error: function (xhr, status) {
       // alert("ERROR");
      },
      complete: function (xhr, status) {
       // alert("COMPLETED");
      },
    });
  }
  
   //función para guardar datos
  function saveClient() {
    let dataClient = {
      name: $("#name").val(), 
      email: $("#email").val(),
      password: $("#password").val(),
      age: $("#age").val(),
    };
  
    if ( dataClient.id == '' || dataClient.name == '' || dataClient.email == '' || dataClient.age == '') {
      alert("ERROR, REVISE DATOS EN FORMULARIO");
      return;
    }
  
    $.ajax({
      url: URL_SERVICE_CLIENT + 'save',
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(dataClient),
      success: function (response) {
        alert("Guardado con éxito");
        cleanData();
      },
      error: function (response, xhr) {
       // alert("ERROR");
      },
      complete: function (xhr, status) {
        location.href = sessionStorage.getItem("thisUrl");
      },
    });
  }

  //función para limpiar la pantalla
  function cleanData(){
    $("#name").val('');
    $("#email").val('');
    $("#password").val('');
    $("#age").val('');
  }
  
  //función para mostrar datos en tabla
  function showClients(data) {
    idTableHtml = 'clients_table';
    let htmlCode = "";

    //Crear encabezado de tabla
    htmlCode += " <thead><tr>"
    tableHeaders.forEach(element => {
        htmlCode += "<th>" + element + "</th>";
    });
    htmlCode += "<th>Editar</th>" + "<th>Eliminar</th>"  + "</tr></thead> ";
        
  //Presentar los datos traídos de BD
  htmlCode += "<tbody>";
  for (let i = 0; i < data.length; i++) {
    htmlCode += "<tr class='centrar'>";
    htmlCode += "<td>" + data[i].name + "</td>";
    htmlCode += "<td>" + data[i].email + "</td>";
    htmlCode += "<td>" + data[i].age + "</td>";
    htmlCode += "<td><a href='#' onclick='dataStorageSession(" + data[i].idClient + ",\"Client\",tableHeaders)'>Editar</a></td>";
    htmlCode += "<td><a href='#' onclick='deleteData(" + data[i].idClient + ",\"Client\")'>Eliminar</a></td>";
    htmlCode += "</tr>";
    }
    htmlCode += "</tbody>";
  
   //Remplazar datos antiguos con nuevos
    $("#" + idTableHtml).html("");
    $("#" + idTableHtml).empty();
    $("#" + idTableHtml).append(htmlCode);
  }

  //función para eliminar datos
  function deleteData(id, tableType){
  $.ajax({
    url: URL_SERVICE_CLIENT + id.toString(),
    type: "DELETE",
    success: function (response) {
      getClients();
    },
    error: function (xhr, status) {
    //  alert("ERROR");
    },
    complete: function (xhr, status) {
    //  alert("COMPLETED");
    },
  });
}