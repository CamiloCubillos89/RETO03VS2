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

import com.reto03.grupog6.Entities.Gama;
import com.reto03.grupog6.Services.GamaService;

@RestController
@RequestMapping("/api/Gama")
public class GamaController {
    @Autowired
    private GamaService gamaService;

    @GetMapping("/all")
    public List<Gama> getAll() {
        return gamaService.getAll();
    }

    @PostMapping("/save")
    public Gama addGama(@RequestBody Gama gama) {
        return gamaService.addGama(gama);
    }

    @PutMapping("/update")
    public Gama updGama(@RequestBody Gama gama) {
        System.out.println("va a service");
        return gamaService.updGama(gama);
    }

    @DeleteMapping("/{id}")
    public void delGama(@PathVariable Integer id) {
         gamaService.delGama(id);
    }

    @GetMapping("/{id}")
    public Gama getGama(@PathVariable Integer id) {
        return gamaService.getGama(id);
    }

}