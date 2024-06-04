package it.ms.api.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.ms.api.data.entity.ActionData;
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

        if (id == -1) {

            game = gameRepo.save(new Game());

            List<Cell> fields = Game.GenerateField(game);
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
        
        return cellRepo.findAllByGameIdAndRevealed(getGame(id), true);
    }

    @PutMapping("/{id}")
    public List<Cell> move(@PathVariable("id") Long id, @RequestBody ActionData actionData) {


        List<Cell> field = cellRepo.findAllByGameId(gameRepo.findById(id).get());

        if (actionData.getActionCode() == 0) { //left

            if (field.get(actionData.getY() * 13 + actionData.getX()).getMonsterId() == 1) {

                //you lost
                gameRepo.delete(getGame(id));
                return null;
            }
            else {

                field.get(actionData.getY() * 13 + actionData.getX()).setRevealed(true);
            }
        }

        return cellRepo.saveAll(field);
    }
}
