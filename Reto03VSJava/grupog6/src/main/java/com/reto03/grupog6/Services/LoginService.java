package com.reto03.grupog6.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog6.Entities.Login;
import com.reto03.grupog6.Repository.LoginRepository;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;
    
    public Login getLogin(String email, String password){
        return loginRepository.getLogin(email, password);
    }
    
}
