package com.example.examen_lpgl.controllers;

import com.example.examen_lpgl.entities.Plat;
import com.example.examen_lpgl.entities.Restaurant;
import com.example.examen_lpgl.repositories.PlatRepository;
import com.example.examen_lpgl.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="*",allowedHeaders = "*")
@RestController
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private PlatRepository platRepository;

    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants(){
        return restaurantRepository.findAll();
    }

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable(value="id")Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new ResourceNotFoundException("Restaurant not found with : "+restaurantId));
        return ResponseEntity.ok().body(restaurant);
    }

    @PostMapping("/restaurants")
    public ResponseEntity createRestaurant(@RequestBody Restaurant restaurant){
        if(restaurantRepository.existsRestaurantByAdresseAndNom(restaurant.getAdresse(),restaurant.getNom()))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(restaurantRepository.save(restaurant),HttpStatus.OK);
    }

    @PutMapping("/restaurants/{id}")
    public Restaurant updateRestaurant(@PathVariable("id")Long restaurantId, @RequestBody Restaurant newRestaurant){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new ResourceNotFoundException("Restaurant not found with : "+restaurantId));
        restaurant.setNom(newRestaurant.getNom());
        restaurant.setType(newRestaurant.getType());
        restaurant.setAdresse(newRestaurant.getAdresse());
        return restaurantRepository.save(restaurant);
    }

    @PostMapping("/restaurants/{id}/plats")
    public Restaurant addPlatRestaurant(@PathVariable("id")Long restaurantId,@RequestBody Plat plat){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new ResourceNotFoundException("Restaurant not found with : "+restaurantId));
        List<Plat> liste = restaurant.getPlatsList();
        liste.add(plat);
        restaurant.setPlatsList(liste);
        return restaurantRepository.save(restaurant);
    }
    @DeleteMapping("/restaurants/{restaurantId}/plats/{platId}")
    public Restaurant removePlatRestaurant(@PathVariable("restaurantId")Long restaurantId,@PathVariable("platId")Long platId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new ResourceNotFoundException("Restaurant not found with : "+restaurantId));
        List<Plat> liste = restaurant.getPlatsList();
        Plat plat = platRepository.findById(platId).orElseThrow(()->new ResourceNotFoundException("Plat not found with : "+platId));
        liste.remove(plat);
        restaurant.setPlatsList(liste);
        return restaurantRepository.save(restaurant);
    }

    @DeleteMapping("/restaurants/{restaurantId}")
    public ResponseEntity deleteRestaurant(@PathVariable("restaurantId")Long restaurantId){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new ResourceNotFoundException("Restaurant not found with : "+restaurantId));
        restaurantRepository.delete(restaurant);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}
