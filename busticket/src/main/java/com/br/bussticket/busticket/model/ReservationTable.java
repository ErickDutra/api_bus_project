package com.br.bussticket.busticket.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class ReservationTable {

    public enum ReservationStatus {
        CONFIRMADO,
        PENDENTE,
        CANCELADO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_reservation;

    @OneToOne
    @JoinColumn(name = "id_trip")
    private TripTable busTable;

    @OneToOne
    @JoinColumn(name = "id_user")
    private UserTable userTable;

    private Integer seat;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

}
