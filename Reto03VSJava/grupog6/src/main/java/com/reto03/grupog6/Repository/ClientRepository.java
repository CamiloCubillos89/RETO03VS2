package com.reto03.grupog6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog6.CrudRepository.ClientCrudRepository;
import com.reto03.grupog6.Entities.Client;

@Repository//Spring lo gestiona como un componente para llamar metodos, acciones, funciones necesarias para BD
public class ClientRepository {
    @Autowired//Automaticamente Java usa la memoria que requiere
    private ClientCrudRepository clientCrudRepository;

    //retorna todas las entidades en BD
    public List<Client> getAll(){
        return (List<Client>)clientCrudRepository.findAll();
    }

    //guarda la entidad en BD
    public Client addClient(Client client){
        if (client.getIdClient() == null ||(client.getIdClient() == 0))
            return clientCrudRepository.save(client);
        else
            return client;
    }

    //Determina si el Client existe en BD
    private Client existClient(Integer id){
        Optional<Client> objClient = clientCrudRepository.findById(id);
        Client objClientRespuesta;

        if(objClient.isEmpty())
            objClientRespuesta = null;
        else
            objClientRespuesta = objClient.get();
            return objClientRespuesta;
    }

    //Actualiza datos
    public Client updClient(Client client){
        Client objClient;

        objClient = existClient(client.getIdClient());

        if (objClient==null)
            return client;
        else{
            if (client.getName() != null)
            objClient.setName(client.getName());
            if (client.getEmail() != null)
            objClient.setEmail(client.getEmail());
            if (client.getPassword() != null)
            objClient.setPassword(client.getPassword());
            if (client.getAge() != null)
            objClient.setAge(client.getAge());

            return clientCrudRepository.save(objClient);
        }
    }

    //Elimina datos
    public void delClient(Integer idClient){
        Client objClient;

        objClient = existClient(idClient);
        if (objClient!=null)
            clientCrudRepository.delete(objClient); 
    }

    //Trae datos
    public Client getClient(Integer idClient){
        Client objClient;

        objClient = existClient(idClient);
        if (objClient==null)
            return objClient; 
        else 
            return null;
    }

    public Client getClientByEmailAndPassword(String email, String password) {
        return clientCrudRepository.findByEmailAndPassword(email, password);
   }

}
