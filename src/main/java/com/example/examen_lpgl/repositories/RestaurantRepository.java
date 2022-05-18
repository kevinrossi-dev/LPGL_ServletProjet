package com.example.examen_lpgl.repositories;

import com.example.examen_lpgl.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    boolean existsRestaurantByAdresseAndNom(String adresse, String nom);
}
