package com.br.bussticket.busticket.repository;

import com.br.bussticket.busticket.model.BusTable;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<BusTable, Long> {

}