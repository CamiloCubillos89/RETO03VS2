package com.reto03.grupog6.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.reto03.grupog6.Entities.Message;
import com.reto03.grupog6.Services.MessageService;

@RestController//Encargado de gestionar las peticiones (metodo http y URL)
@RequestMapping("/api/Message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/all")
    public List<Message> getAll(){
        return messageService.getAll();
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message addMessage(@RequestBody Message message){
        return messageService.addMessage(message);
    }  
    
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Message updMessage(@RequestBody Message message) {
        return messageService.updMessage(message);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delMessage(@PathVariable Integer id) {
         messageService.delMessage(id);
    }

    @GetMapping("/{id}")
    public Message getMessage(@PathVariable Integer id) {
        return messageService.getMessage(id);
    }

}
