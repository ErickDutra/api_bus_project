package com.br.bussticket.busticket.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.br.bussticket.busticket.model.BusTable;
import com.br.bussticket.busticket.repository.BusRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/buses")
public class BusController {

    @Autowired
    private BusRepository busRepository;

    @PostMapping
    public BusTable createBus(@RequestBody BusTable bus) {
        return busRepository.save(bus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusTable> readBus(@PathVariable Long id) {
        Optional<BusTable> bus = busRepository.findById(id);
        if (bus.isPresent()) {
            return ResponseEntity.ok(bus.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id) {
        if (busRepository.existsById(id)) {
            busRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}