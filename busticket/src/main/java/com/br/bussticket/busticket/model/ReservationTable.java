package com.br.bussticket.busticket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "reservation")
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

    @Column(name = "seat")
    private Integer seat;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReservationStatus status;

}
