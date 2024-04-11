package com.br.bussticket.busticket.dto;

import lombok.Data;

@Data
public class BusDto {
    private Long id_bus;
    private String model_bus;
    private String plate_bus;
    private Integer numberSeats;
}
