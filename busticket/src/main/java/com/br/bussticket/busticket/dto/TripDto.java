package com.br.bussticket.busticket.dto;

import lombok.Data;

@Data
public class TripDto {
  
    private Long id_trip;
    private String busTable;
    private String origin;
    private String destination;
    private String matchData;
    private String arrivalData;
    private float priceTrip;

}
