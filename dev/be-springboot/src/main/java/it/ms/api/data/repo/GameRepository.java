package it.ms.api.data.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import it.ms.api.data.entity.Game;


public interface GameRepository extends JpaRepository<Game, Long> {

  
}