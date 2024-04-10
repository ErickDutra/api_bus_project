package com.br.bussticket.busticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class TripTable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_trip;

    @OneToOne
    @JoinColumn(name = "id_bus")
    private BusTable busTable;


    private String origin;
    private String destination;
    private String matchData;
    private String arrivalData;
    private float priceTrip;


}
