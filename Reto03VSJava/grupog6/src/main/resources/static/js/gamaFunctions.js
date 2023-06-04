const URL_SERVICE_GAMA = "http://localhost:8080/api/Gama/";
const tHeaders = ['NOMBRE', 'DESCRIPCIÓN'];

//función para traer datos
function getGamas() {
  $.ajax({
    url: URL_SERVICE_GAMA + "all",
    type: "GET",
    dataType: "json",

    success: function (response) {
      showGamas(response);
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
function saveGama() {
  let dataGama = {
    name: $("#name").val(),
    description: $("#description").val(),
  };

  if ( dataGama.name == '' || dataGama.description == '') {
    alert("ERROR, REVISE DATOS EN FORMULARIO");
    return;
  }

  $.ajax({
    url: _SERVICE_GAMA + "save",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(dataGama),
    success: function (response) {
      alert("Guardado con éxito");
      cleanData();
    },
    error: function (response, xhr) {
    //  alert("ERROR");
    },
    complete: function (xhr, status) {
    },
  });
}

//función para limpiar la pantalla
function cleanData(){
  $("#name").val('');
  $("#description").val('');
}

//función para mostrar datos en tabla
function showGamas(data) {
    let idTableHtml = 'gamas_table';
    let htmlCode = "";
   
    //Crear encabezado de tabla
    htmlCode += " <thead><tr>"
    tHeaders.forEach(element => {
        htmlCode += "<th>" + element + "</th>";
    });
    htmlCode += "<th>Editar</th>" + "<th>Eliminar</th>"  + "</tr></thead> ";
        
  //Presentar los datos traídos de BD
  htmlCode += "<tbody>";
  for (let i = 0; i < data.length; i++) {
    htmlCode += "<tr class='centrar'>";
    htmlCode += "<td>" + data[i].name + "</td>";
    htmlCode += "<td>" + data[i].description + "</td>";
    htmlCode += "<td><a href='#' onclick='dataStorageSession(" + data[i].idGama + ",\"Gama\",tHeaders)'>" + "Editar" + "</a></td>";
    htmlCode += "<td><a href='#' onclick='deleteData(" + data[i].idGama + ",\"Gama\")'>" + "Eliminar" + "</a></td>";
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
    url: URL_SERVICE_GAMA + id.toString(),
    type: "DELETE",
    success: function (response) {
      getGamas();
    },
    error: function (xhr, status) {
    //  alert("ERROR");
    },
    complete: function (xhr, status) {
    //  alert("COMPLETED");
    },
  });
}