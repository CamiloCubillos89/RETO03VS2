var profile;
var nameUser;
var thisUrl;

//Función para cargar perfil
function loadProfile(){
    profile = sessionStorage.getItem("rol");
    nameUser = sessionStorage.getItem("name");
    thisUrl = window.location.href;
    sessionStorage.setItem("thisUrl", thisUrl);
    
    if(profile == "Admin"){
        document.title = "ADMIN";
    } else if(profile == "Client"){
        document.title = "CLIENT";
        $('.disableForClients').hide();  
    } else{
        alert("PERFIL NO AUTORIZADO");
        location.href = "./index.html";
    }

    $("#welcome").text("Bienvenid@: " + nameUser);
    $('.options').hide();
}
//Función para cerrar sesión
function logOut() {
    sessionStorage.clear();
    location.href = "./login.html";
}
//OPCIONES
function showOptions(option) {
    switch (option) {
        case 'opt_gama':
            if ($('#opt_gama').is(':visible')) {$('#opt_gama').hide();}
            else{$('.options').hide();$('#opt_gama').show();}
            break;
        case 'opt_car':
            if ($('#opt_car').is(':visible')) {$('#opt_car').hide();}
            else{$('.options').hide();$('#opt_car').show();}
            break;
        case 'opt_client':
            if ($('#opt_client').is(':visible')) {$('#opt_client').hide();}
            else{$('.options').hide();$('#opt_client').show();}
            break;
        case 'opt_message':
            if ($('#opt_message').is(':visible')) {$('#opt_message').hide();}
            else{$('.options').hide();$('#opt_message').show();}
            break;
        case 'opt_admin':
            if ($('#opt_admin').is(':visible')) {$('#opt_admin').hide();}
            else{$('.options').hide();$('#opt_admin').show();}
            break;
        case 'opt_reservation':
            if ($('#opt_reservation').is(':visible')) {$('#opt_reservation').hide();}
            else{$('.options').hide();$('#opt_reservation').show();}
            break;
        default:
            break;
    }
}

