package com.br.bussticket.busticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BusTable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_bus;
    
    private String model_bus;
    private String plate_bus;
    private Integer numberSeats;

}
