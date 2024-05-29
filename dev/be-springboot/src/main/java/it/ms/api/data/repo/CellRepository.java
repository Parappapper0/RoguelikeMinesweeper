package it.ms.api.data.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ms.api.data.entity.Cell;
import it.ms.api.data.entity.Game;


public interface CellRepository extends JpaRepository<Cell, Long> {

    List<Cell> findAllByGameId(Game gameId);
}