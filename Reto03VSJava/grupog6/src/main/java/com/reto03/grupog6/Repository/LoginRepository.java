package com.reto03.grupog6.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog6.Entities.Admin;
import com.reto03.grupog6.Entities.Client;
import com.reto03.grupog6.Entities.Login;

@Repository
public class LoginRepository {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;
    
    public Login getLogin(String email, String password){
        Client objClient;
        Admin objAdmin;
        
        objClient = clientRepository.getClientByEmailAndPassword(email, password);
        objAdmin = adminRepository.getAdminByEmailAndPassword(email, password);

        if(objClient != null){
            return new Login(objClient.getIdClient(), "Client", objClient.getName());
        } else if(objAdmin != null){
            return new Login(objAdmin.getIdAdmin(), "Admin", objAdmin.getName());
        } else {return null;}
    }
}