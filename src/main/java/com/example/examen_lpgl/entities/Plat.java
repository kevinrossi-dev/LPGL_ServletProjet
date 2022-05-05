package com.example.examen_lpgl.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plat {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long platId;

    private String nom;
    private String type;
    private double prix;

    public Plat() {

    }

    public long getPlatId() {
        return platId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Plat(Long id ,String nom, String type, double prix) {
        this.platId=id;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
    }

}
