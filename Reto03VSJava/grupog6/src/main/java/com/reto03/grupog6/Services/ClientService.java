package com.reto03.grupog6.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog6.Entities.Client;
import com.reto03.grupog6.Repository.ClientRepository;

@Service//Encargada de usar el repository como parte de su lógica 
public class ClientService {
    @Autowired 
    private ClientRepository clientRepository;
    
    public Client addClient(Client client){
        Boolean bGrabar = true;

        if (client.getName() == null)
            bGrabar = false;
        if (client.getEmail() == null)
            bGrabar = false;
        if (client.getPassword() == null)
            bGrabar = false;
        if (client.getAge() == null)
            bGrabar = false;
        
        if(bGrabar)
            return clientRepository.addClient(client);
        else
            return client;
    }

    public List<Client> getAll(){
        return (List<Client>)clientRepository.getAll();
    }

    public Client updClient(Client client){
        return clientRepository.updClient(client);
    }

    public Client getClient(Integer idClient){
        return clientRepository.getClient(idClient);
    }

    public void delClient(Integer idClient){
        clientRepository.delClient(idClient);
    }

    public Client getClientByEmailAndPassword(String email, String password) {   
        return clientRepository.getClientByEmailAndPassword(email, password);
    }

}
