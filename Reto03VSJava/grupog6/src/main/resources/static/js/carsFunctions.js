const URL_SERVICE_CAR = "http://localhost:8080/api/Car/";
const URL_SERVICE_GAMA = "http://localhost:8080/api/Gama/";
const tHeaders = ['NOMBRE', 'MARCA', 'MODELO', 'DESCRIPCIÓN', 'CATEGORIA' ];

 //función para traer datos
function getCars() {
  $.ajax({
    url: URL_SERVICE_CAR + "all",
    type: "GET",
    dataType: "json",

    success: function (response) {
      showCars(response);
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
function saveCar() {
  let dataCar = {
    name: $("#name").val(),
    brand: $("#brand").val(),
    year: $("#yearCar").val(),
    description: $("#description").val(),
    gama: {idGama: parseInt($("#gamas_selector").val())}
  };

  if ( dataCar.id == '' || dataCar.brand == '' || dataCar.year == '' || dataCar.description == '' 
          || dataCar.gama.idGama == 0) {
    alert("ERROR, REVISE DATOS EN FORMULARIO");
    return;
  }

  $.ajax({
    url: URL_SERVICE_CAR + 'save',
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(dataCar),
    success: function (response) {
      alert("Guardado con éxito");
      cleanData();
    },
    error: function (response, xhr) {
    //  alert("ERROR");
    },
    complete: function (xhr, status) {
    //  alert("COMPLETED");
    },
  });
}

//función para limpiar la pantalla
function cleanData(){
  $("#name").val('');
  $("#brand").val('');
  $("#yearCar").val('');
  $("#description").val('');
}

//función para mostrar datos en tabla
function showCars(data) {
    let idTableHtml = 'cars_table';
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
    htmlCode += "<td>" + data[i].brand + "</td>";
    htmlCode += "<td>" + data[i].year + "</td>";
    htmlCode += "<td>" + data[i].description + "</td>";
    htmlCode += "<td>" + (data[i].gama==null?'No registra':data[i].gama.name) + "</td>";
    htmlCode += "<td><a href='#' onclick='dataStorageSession(" + data[i].idCar + ",\"Car\",tHeaders)'>" + "Editar" + "</a></td>";
    htmlCode += "<td><a href='#' onclick='deleteData(" + data[i].idCar + ",\"Car\")'>" + "Eliminar" + "</a></td>";
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
    url: URL_SERVICE_CAR + id.toString(),
    type: "DELETE",
    success: function (response) {
      getCars();
    },
    error: function (xhr, status) {
    //  alert("ERROR");
    },
    complete: function (xhr, status) {
    //  alert("COMPLETED");
    },
  });
}

/////////////////////////////////
//función para traer datos (GAMAS)
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

//función para mostrar datos (Selector GAMAS)
function showGamas(data) {
  let idSelector = 'gamas_selector';
  let htmlCode = '';

  htmlCode += '<option value=0>Seleccione GAMA</option>'
  for (let i = 0; i < data.length; i++) {
    htmlCode += '<option value="' + data[i].idGama + '">' + data[i].name + '</option>';
  }
  $('#' + idSelector).html(htmlCode);
}