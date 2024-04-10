package com.br.bussticket.busticket.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.br.bussticket.busticket.model.TripTable;
import com.br.bussticket.busticket.repository.TripRepository;

@RestController
@RequestMapping("/trips")
public class TripController {

    @Autowired
    private TripRepository tripRepository;

    @PostMapping
    public TripTable createTrip(@RequestBody TripTable trip) {
        return tripRepository.save(trip);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripTable> readTrip(@PathVariable Long id) {
        Optional<TripTable> trip = tripRepository.findById(id);
        if (trip.isPresent()) {
            return ResponseEntity.ok(trip.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        if (tripRepository.existsById(id)) {
            tripRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}