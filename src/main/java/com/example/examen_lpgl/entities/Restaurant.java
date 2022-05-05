package com.example.examen_lpgl.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long restaurantId;

    private String name;

    private String type;

    @ManyToMany
    @JoinTable(name="restaurants_plats" , joinColumns = @JoinColumn(name="restaurantId"), inverseJoinColumns = @JoinColumn(name="platId"))
    private List<Plat> platsList = new ArrayList<>();

    public Restaurant() {

    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Restaurant(Long id, String name, String type, List<Plat> platsList) {
        this.restaurantId = id;
        this.name = name;
        this.type = type;
        this.platsList = platsList;
    }

}
