package com.br.bussticket.busticket.dto;

import com.br.bussticket.busticket.model.ReservationTable;

import lombok.Data;

@Data
public class ReservationDto {

    private Long id_reservation;
    private Long id_trip;
    private Long id_user;
    private Integer seat;
    private ReservationTable.ReservationStatus status;


}
