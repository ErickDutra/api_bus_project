package com.br.bussticket.busticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.bussticket.busticket.model.TripTable;

public interface TripRepository extends JpaRepository<TripTable, Long> {
    
}