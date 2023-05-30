package com.reto03.grupog6.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.reto03.grupog6.CrudRepository.CarCrudRepository;
import com.reto03.grupog6.Entities.Car;

@Repository//Spring lo gestiona como un componente para llamar metodos, acciones, funciones necesarias para BD
public class CarRepository {
    @Autowired//Automaticamente Java usa la memoria que requiere
    private CarCrudRepository carCrudRepository;

    //retorna todas las entidades en BD
    public List<Car> getAll(){
        return (List<Car>)carCrudRepository.findAll();
    }

    //guarda la entidad en BD
    public Car addCar(Car car){
        if (car.getIdCar() == null ||(car.getIdCar() == 0))
            return carCrudRepository.save(car);
        else
            return car;
    }

    //Determina si el Car existe en BD
    private Car existCar(Integer id){
        Optional<Car> objCar = carCrudRepository.findById(id);
        Car objCarRespuesta;

        if(objCar.isEmpty())
            objCarRespuesta = null;
        else
            objCarRespuesta = objCar.get();
        
        return objCarRespuesta;
    }

    //Actualiza datos
    public Car updCar(Car car){
        Car objCar;

        objCar = existCar(car.getIdCar());

        if (objCar==null)
            return car;
        else{
            if (car.getName() != null)
                objCar.setName(car.getName());

            if (car.getBrand() != null)
                objCar.setBrand(car.getBrand());

            if (car.getYear() != null)
                objCar.setYear(car.getYear());
                
            if (car.getDescription() != null)
                objCar.setDescription(car.getDescription());

            return carCrudRepository.save(objCar);
        }
    }

    //Elimina datos
    public void delCar(Integer idCar){
        Car objCar;

        objCar = existCar(idCar);
        if (objCar!=null)
            carCrudRepository.delete(objCar); 
    }

    //Trae datos
    public Car getCar(Integer idCar){
        Car objCar;

        objCar = existCar(idCar);
        if (objCar==null)
            return objCar; 
        else 
            return null;
    }


}
