package com.reto03.grupog6.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "gama")
public class Gama implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer idGama;
    private String name;
    private String description;

    // Una a Muchos // CARS
    @OneToMany(cascade = (CascadeType.PERSIST), mappedBy = "gama")
    @JsonIgnoreProperties("gama")
    private List<Car> cars;

    public Gama() {
    }

    public Gama(Integer idGama, String name, String description, List<Car> cars) {
        this.idGama = idGama;
        this.name = name;
        this.description = description;
        this.cars = cars;
    }

    public Integer getIdGama() {
        return idGama;
    }

    public void setIdGama(Integer idGama) {
        this.idGama = idGama;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    

    
}

