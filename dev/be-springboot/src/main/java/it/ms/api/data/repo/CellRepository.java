package it.ms.api.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ms.api.data.entity.Cell;


public interface CellRepository extends JpaRepository<Cell, Long> {

  
}