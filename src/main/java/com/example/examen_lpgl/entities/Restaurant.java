package com.example.examen_lpgl.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long restaurantId;

    private String nom;

    private String type;
    private String adresse;

    @ManyToMany
    @JoinTable(name="restaurants_plats" , joinColumns = @JoinColumn(name="restaurantId"), inverseJoinColumns = @JoinColumn(name="platId"))
    private List<Plat> platsList = new ArrayList<>();

    public Restaurant() {

    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Plat> getPlatsList() {
        return platsList;
    }

    public void setPlatsList(List<Plat> platsList) {
        this.platsList = platsList;
    }

    public Restaurant(Long id, String adresse,String nom, String type, List<Plat> platsList) {
        this.restaurantId = id;
        this.adresse=adresse;
        this.nom = nom;
        this.type = type;
        this.platsList = platsList;
    }

    public String toString(){
        return "Restaurant n°"+this.restaurantId+" | "+this.nom+" | "+this.adresse+" | "+this.type;
    }

}
