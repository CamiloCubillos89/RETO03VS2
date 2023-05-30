package com.reto03.grupog6.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog6.Entities.Message;
import com.reto03.grupog6.Repository.MessageRepository;

@Service//Encargada de usar el repository como parte de su lógica 
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public Message addMessage(Message message){
        Boolean bGrabar = true;

        if (message.getMessageText() == null)
            bGrabar = false;
        if (message.getClient().getIdClient() == null)
            bGrabar = false;
        if (message.getCar().getIdCar() == null)
            bGrabar = false;

        if (bGrabar)
            return messageRepository.addMessage(message);
        else
            return message;
    }

    public List<Message> getAll(){
        return (List<Message>)messageRepository.getAll();
    }

    public Message updMessage(Message message){
        return messageRepository.updMessage(message);
    }

    public Message getMessage(Integer idMessage){
        return messageRepository.getMessage(idMessage);
    }

    public void delMessage(Integer idMessage){
        messageRepository.delMessage(idMessage);
    }

}
