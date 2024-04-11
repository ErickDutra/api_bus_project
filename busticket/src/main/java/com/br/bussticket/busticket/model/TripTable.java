package com.br.bussticket.busticket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "trip")
public class TripTable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_trip;

    @OneToOne
    @JoinColumn(name = "id_bus")
    private BusTable busTable;

    @Column(name = "origin")
    private String origin;
    @Column(name = "destination")
    private String destination;
    @Column(name = "matchData")
    private String matchData;
    @Column(name = "arrivalData")
    private String arrivalData;
    @Column(name = "priceTrip")
    private float priceTrip;

}
