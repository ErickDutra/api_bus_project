package com.br.bussticket.busticket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import lombok.Data;

@Entity
@Data
@Table(name = "bus")
public class BusTable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_bus;
    
    @Column(name = "model")
    private String model_bus;
    @Column(name = "plate")
    private String plate_bus;
    @Column(name = "numberSeats")
    @Max(32) 
    private Integer numberSeats;

}
