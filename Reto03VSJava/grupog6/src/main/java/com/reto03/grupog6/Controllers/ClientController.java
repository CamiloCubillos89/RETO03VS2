package com.reto03.grupog6.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.reto03.grupog6.Entities.Client;
import com.reto03.grupog6.Services.ClientService;

@RestController//Encargado de gestionar las peticiones (metodo http y URL)
@RequestMapping("/api/Client")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/all")
    public List<Client> getAll(){
        return clientService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Client addClient(@RequestBody Client client){
        return clientService.addClient(client);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Client updClient(@RequestBody Client client){
        return clientService.updClient(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delClient(@PathVariable Integer id){
        clientService.delClient(id);
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Integer id){
        return clientService.getClient(id);
    }

}
