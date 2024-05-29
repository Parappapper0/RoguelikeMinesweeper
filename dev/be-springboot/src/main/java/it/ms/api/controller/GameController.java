package it.ms.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.ms.api.data.entity.Cell;
import it.ms.api.data.entity.Game;
import it.ms.api.data.repo.CellRepository;
import it.ms.api.data.repo.GameRepository;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired GameRepository gameRepo;
    @Autowired CellRepository cellRepo;

    @GetMapping("list")
    public List<Game> list() {
        return gameRepo.findAll();
    }

    @GetMapping("")
    public Game getGame(@RequestParam(name = "id", required = false) Long id) {
        
        Game game;

        if (id == null) {

            game = gameRepo.save(new Game());

            List<Cell> fields = Game.GenerateField(game, 1);
            cellRepo.saveAll(fields);
        }
        else {
            try {

                game = gameRepo.findById(id).get();

            } catch(NoSuchElementException e) {

                game = gameRepo.save(new Game(id));
                //cellRepo.saveAll(game.getField());
            }
        }
        
        return game;
    }

    @GetMapping("/cells")
    public List<Cell> getCells(@RequestParam(name = "id", required = true) Long id) {
        
        return cellRepo.findAllByGameId(getGame(id));
    }
}
