package com.reto03.grupog6.CrudRepository;

import org.springframework.data.repository.CrudRepository;

import com.reto03.grupog6.Entities.Car;

public interface CarCrudRepository extends CrudRepository<Car,Integer> {
    
}
