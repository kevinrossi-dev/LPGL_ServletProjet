package com.example.examen_lpgl.controllers;

import com.example.examen_lpgl.entities.Plat;
import com.example.examen_lpgl.repositories.PlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class PlatController {
    @Autowired
    private PlatRepository platRepository;

    @GetMapping("/plats")
    public List<Plat> getAllPlats(){
        return platRepository.findAll();
    }

    @GetMapping("/plats/{id}")
    public ResponseEntity<Plat> getPlatById(@PathVariable(value="id")Long platId){
        Plat plat = platRepository.findById(platId).orElseThrow(()->new ResourceNotFoundException("Plat not found with : "+platId));
        return ResponseEntity.ok().body(plat);
    }

    @PostMapping("/plats")
    public Plat createPlat(@RequestBody Plat plat){
        return platRepository.save(plat);
    }
}
