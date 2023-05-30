package com.reto03.grupog6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog6.CrudRepository.MessageCrudRepository;
import com.reto03.grupog6.Entities.Car;
import com.reto03.grupog6.Entities.Client;
import com.reto03.grupog6.Entities.Message;

@Repository
public class MessageRepository {
    @Autowired
    private MessageCrudRepository messageCrudRepository;

    //retorna todas las entidades en BD
    public List<Message> getAll() {
        return (List<Message>) messageCrudRepository.findAll();
    }

    //guarda la entidad en BD
    public Message addMessage(Message message) {
        if (message.getIdMessage() == null || message.getIdMessage() == 0)
            return messageCrudRepository.save(message);
        else
            return message;
    }

     //Determina si el Message existe en BD
    private Message existMessage(Integer idMessage) {
        Optional<Message> objMessage = messageCrudRepository.findById(idMessage);
        Message objMessageRespuesta;

        if (objMessage.isEmpty())
            objMessageRespuesta = null;
        else
            objMessageRespuesta = objMessage.get();
        
        return objMessageRespuesta;

    }
    
    //Actualiza datos
    public Message updMessage(Message message) {
        Message objMessage;
        Car car = new Car();
        Client client = new Client();

        objMessage = existMessage(message.getIdMessage());
        if (objMessage == null)
            return message;
        else {
            if (message.getMessageText() != null)
                objMessage.setMessageText(message.getMessageText());
            
            if (message.getClient().getIdClient() != null) {
                client.setIdClient(message.getClient().getIdClient());
                objMessage.setClient(client);
            }
   
            if (message.getCar().getIdCar() != null) {
                car.setIdCar(message.getCar().getIdCar());
                objMessage.setCar(car);
            }
            return messageCrudRepository.save(objMessage);

        }
    }

    //Elimina datos
    public void delMessage(Integer idMessage){
        Message objMessage;

        objMessage = existMessage(idMessage);
        if(objMessage != null)
            messageCrudRepository.delete(objMessage);
    }

    //Trae datos
    public Message getMessage(Integer idMessage) {
        Message objMessage;

        objMessage = existMessage(idMessage);
        if (objMessage != null)
            return objMessage;
        else
            return null;
        
    }
}
