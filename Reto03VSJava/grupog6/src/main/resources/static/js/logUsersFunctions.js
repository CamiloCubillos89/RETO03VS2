const URL_SERVICE_LOGIN = "http://localhost:8080/api/login";

//función para ingresar usuario
function validateUser() {
    let user = {
        email: $("#email").val(),
        password: $("#password").val()
    };

  $.ajax({
    url: URL_SERVICE_LOGIN,
    type: "POST",
    dataType: "json",
    contentType: "application/json;",
    data:JSON.stringify(user),
    success: function (response) {
      saveUser(response);
    },
    error: function (xhr, status) {
      alert("ERROR, EL USUARIO NO EXISTE");
    },
    complete: function (xhr, status) {
      alert("COMPLETED");
    },
  });
}

//función para guardar usuario (ADMIN)
function saveUser(data){
    sessionStorage.setItem("idUser", data.idRol);
    sessionStorage.setItem("rol", data.rol);
    sessionStorage.setItem("name", data.name);
    location.href = "./menuAdmin.html";
}

//función para crear usuario nuevo
function registerUser() {
  sessionStorage.setItem("thisUrl", window.location.href);
  location.href = "./clientNew.html";
}
