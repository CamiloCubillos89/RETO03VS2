package com.reto03.grupog6.Controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto03.grupog6.Entities.Login;
import com.reto03.grupog6.Services.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping
    public Login getUserLogin(@RequestBody Map<String, String> body){
        return loginService.getLogin(body.get("email"), body.get("password"));
    }
}
