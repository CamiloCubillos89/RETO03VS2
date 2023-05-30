package com.reto03.grupog6.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="logins")
public class Login implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idLogin;
    private Integer idRol;
    private String rol;
    private String name;
    
    public Login() {
    }
    
    public Login(Integer idLogin, Integer idRol, String rol) {
        this.idLogin = idLogin;
        this.idRol = idRol;
        this.rol = rol;
    }

    public Login(Integer idRol, String rol, String name) {
        this.idRol = idRol;
        this.rol = rol;
        this.name = name;
    }

    public Integer getIdLogin() {
        return idLogin;
    }

    public void setIdLogin(Integer idLogin) {
        this.idLogin = idLogin;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer integer) {
        this.idRol = integer;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
