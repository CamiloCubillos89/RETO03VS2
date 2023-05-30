package com.reto03.grupog6.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reto03.grupog6.Entities.Car;
import com.reto03.grupog6.Repository.CarRepository;

@Service//Encargada de usar el repository como parte de su lógica 
public class CarService {
    @Autowired
    private CarRepository carRepository;
    
    public Car addCar(Car car){
        Boolean bGrabar = true;

        if (car.getName() == null)
            bGrabar = false;
        if (car.getBrand() == null)
            bGrabar = false;
        if (car.getYear() == null)
            bGrabar = false;
        if (car.getDescription() == null)
            bGrabar = false;

     /* if (car.getGama().getIdGama() == null)
            bGrabar = false;*/
        
        if(bGrabar)
            return carRepository.addCar(car);
        else
            return car;
    }


    public List<Car> getAll(){
        return (List<Car>)carRepository.getAll();
    }

    public Car updCar(Car car){
        return carRepository.updCar(car);
    }

    public Car getCar(Integer idCar){
        return carRepository.getCar(idCar);
    }

    public void delCar(Integer idCar){
        carRepository.delCar(idCar);
    }
}
