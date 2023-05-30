package com.reto03.grupog6.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reto03.grupog6.Entities.Car;
import com.reto03.grupog6.Services.CarService;

@RestController
@RequestMapping("/api/Car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/all")
    public List<Car> getAll() {
        return carService.getAll();
    }

    @PostMapping("/save")
    public Car addCar(@RequestBody Car car) {
        return carService.addCar(car);
    }

    @PutMapping("/update")
    public Car updCar(@RequestBody Car car) {
        return carService.updCar(car);
    }

    @DeleteMapping("/{id}")
    public void delCar(@PathVariable Integer id) {
         carService.delCar(id);
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable Integer id) {
        return carService.getCar(id);
    }

}
