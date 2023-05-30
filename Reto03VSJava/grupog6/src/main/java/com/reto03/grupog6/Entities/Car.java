package com.reto03.grupog6.Entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="cars")

public class Car implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idCar;
    private String name;
    private String brand;
    private Integer year;
    private String description;

    //Muchos a Uno // GAMA
    @ManyToOne
    @JoinColumn(name="idGama")
    @JsonIgnoreProperties("cars")
    private Gama gama;

    //Uno a muchos // MESSAGES
    @OneToMany(cascade = (CascadeType.PERSIST), mappedBy = "car") 
    @JsonIgnoreProperties({"cars","clients","car","client"})
    private List<Message> messages;

    //Uno a muchos // RESERVATIONS
    @OneToMany(cascade = (CascadeType.PERSIST), mappedBy = "car") 
    @JsonIgnoreProperties({"car","messages"})
    private List<Reservation> reservations;

    public Car() {
    }


    public Car(Integer idCar, String name, String brand, Integer year, String description, Gama gama,
    List<Message> messages, List<Reservation> reservations) {
        this.idCar = idCar;
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.description = description;
        this.gama = gama;
        this.messages = messages;
        this.reservations = reservations;
    }


    public Integer getIdCar() {
        return idCar;
    }


    public void setIdCar(Integer idCar) {
        this.idCar = idCar;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getBrand() {
        return brand;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }


    public Integer getYear() {
        return year;
    }


    public void setYear(Integer year) {
        this.year = year;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public Gama getGama() {
        return gama;
    }


    public void setGama(Gama gama) {
        this.gama = gama;
    }


    public List<Message> getMessages() {
        return messages;
    }


    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }


    public List<Reservation> getReservations() {
        return reservations;
    }


    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

 

    
}
