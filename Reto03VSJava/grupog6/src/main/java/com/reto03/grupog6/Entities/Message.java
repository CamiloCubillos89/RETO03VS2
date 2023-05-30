package com.reto03.grupog6.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="messages")

public class Message implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    private String messageText;

    // Muchos a Uno // CAR
    @ManyToOne 
    @JoinColumn(name="idCar")
    @JsonIgnoreProperties({"messages","reservations"})
    private Car car;  
    
    // Muchos a Uno CLIENTS
    @ManyToOne
    @JoinColumn(name="idClient")
    @JsonIgnoreProperties({"messages","reservations"})
    private Client client;   

    public Message() {
    }

    public Message(Integer idMessage, String messageText, Client client, Car car) {
        this.idMessage = idMessage;
        this.messageText = messageText;
        this.client = client;
        this.car = car;
    }

    public Integer getIdMessage() {
        return idMessage;
    }
    public void setIdMessage(Integer idMessage) {
        this.idMessage = idMessage;
    }
    public String getMessageText() {
        return messageText;
    }
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Car getCar() {
        return car;
    }
    public void setCar(Car car) {
        this.car = car;
    }   
}
