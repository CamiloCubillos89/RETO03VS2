package com.reto03.grupog6.CrudRepository;

import org.springframework.data.repository.CrudRepository;

import com.reto03.grupog6.Entities.Message;

public interface MessageCrudRepository extends CrudRepository<Message,Integer>  {
    
}
