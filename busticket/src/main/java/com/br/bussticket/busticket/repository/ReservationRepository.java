package com.br.bussticket.busticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.bussticket.busticket.model.ReservationTable;


public interface ReservationRepository extends JpaRepository<ReservationTable, Long> {
    
}
