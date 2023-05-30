package com.reto03.grupog6.Entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name ="scores")
public class Score implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idScore;
    private Integer result; //calificación
    private String description; //de la calificación
    
    //Uno a Uno // RESERVATIONS
    @OneToOne
    @JoinColumn(name = "idReservation")
    @JsonIgnoreProperties({"score","startDate","devolutionDate","status","car","client"})
    private Reservation reservation;

    public Score() {
    }

    public Score(Integer idScore, Integer result, String description, Reservation reservation) {
        this.idScore = idScore;
        this.result = result;
        this.description = description;
        this.reservation = reservation;
    }

    public Integer getIdScore() {
        return idScore;
    }

    public void setIdScore(Integer idScore) {
        this.idScore = idScore;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    
    
}