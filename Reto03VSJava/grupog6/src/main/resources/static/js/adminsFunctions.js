const URL_SERVICE_ADMIN = 'http://localhost:8080/api/Admin/'
const tHeaders = ['NOMBRE', 'CORREO'];

 //función para traer datos
function getAdmins() {
    $.ajax({
      url: URL_SERVICE_ADMIN + 'all',
      type: "GET",
      dataType: "json",
  
      success: function (response) {
        showAdmins(response);
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
  function saveAdmin() {
    let dataAdmin = {
      name: $("#name").val(), 
      email: $("#email").val(),
      password: $("#password").val()
    };
  
    if ( dataAdmin.id == '' || dataAdmin.name == '' || dataAdmin.email == '') {
      alert("ERROR, REVISE DATOS EN FORMULARIO");
      return;
    }
  
    $.ajax({
      url: URL_SERVICE_ADMIN + 'save',
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(dataAdmin),
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
    $("#name").val('');
    $("#email").val('');
    $("#password").val('');
  }
  
  //función para mostrar datos en tabla
  function showAdmins(data) {
    idTableHtml = 'admins_table';
    let htmlCode = "";

    //Crear encabezado de tabla
    htmlCode += " <thead><tr>"
    tHeaders.forEach(element => {
        htmlCode += "<th>" + element + "</th>";
    });
    htmlCode += "<th>Editar</th>" + "<th>Borrar</th>" + "</tr></thead> ";
        
  //Presentar los datos traídos de BD
  htmlCode += "<tbody>";
  for (let i = 0; i < data.length; i++) {
    htmlCode += "<tr class='centrar'>";
    htmlCode += "<td>" + data[i].name + "</td>";
    htmlCode += "<td>" + data[i].email + "</td>";
    htmlCode += "<td><a href='#' onclick='dataStorageSession(" + data[i].idAdmin + ",\"Admin\",tHeaders)'>Editar</a></td>";
    htmlCode += "<td><a href='#' onclick='deleteData(" + data[i].idAdmin + ",\"Admin\")'>Borrar</a></td>";
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
    url: URL_SERVICE_ADMIN + id.toString(),
    type: "DELETE",
    success: function (response) {
      getAdmins();
    },
    error: function (xhr, status) {
     // alert("ERROR");
    },
    complete: function (xhr, status) {
     // alert("COMPLETED");
    },
  });
}