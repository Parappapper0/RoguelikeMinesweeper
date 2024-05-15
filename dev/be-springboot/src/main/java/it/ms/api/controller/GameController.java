package it.ms.api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.ms.api.data.entity.Game;
import it.ms.api.data.repo.GameRepository;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired GameRepository gameRepo;

    @GetMapping("list")
    public List<Game> list() {
        return gameRepo.findAll();
    }

    @GetMapping("")
    public Game getGame(@RequestParam(name = "id", required = false) Long id) {
        
        Game game;

        if (id == null) {

            game = gameRepo.save(new Game());
        }
        else {
            try {

                game = gameRepo.findById(id).get();

            } catch(NoSuchElementException e) {

                game = gameRepo.save(new Game(id));
            }
        }
        
        return game;
    }
}
