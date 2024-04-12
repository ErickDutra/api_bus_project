package com.br.bussticket.busticket.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.br.bussticket.busticket.model.ReservationTable;
import com.br.bussticket.busticket.repository.ReservationRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping
    public ReservationTable createReservation(@RequestBody ReservationTable reservation) {
        return reservationRepository.save(reservation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationTable> readReservation(@PathVariable Long id) {
        Optional<ReservationTable> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            return ResponseEntity.ok(reservation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}