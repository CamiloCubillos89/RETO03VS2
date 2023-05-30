package com.reto03.grupog6.CrudRepository;

import org.springframework.data.repository.CrudRepository;

import com.reto03.grupog6.Entities.Admin;

public interface AdminCrudRepository extends CrudRepository<Admin, Integer>{

    public Admin findByEmailAndPassword(String email, String password);
    
}