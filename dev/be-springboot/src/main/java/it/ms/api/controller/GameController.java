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
                List<Cell> fields = Game.GenerateField(game);
                cellRepo.saveAll(fields);
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

        if (actionData.getActionCode() == 0) { //left click
            int x = actionData.getX();
            int y = actionData.getY();
            Cell cell = field.get(y * 13 + x);

            if (cell.getMonsterId() == 1) {
                // you lost
                cellRepo.deleteAll(cellRepo.findAllByGameId(getGame(id)));
                gameRepo.delete(getGame(id));
                return null;
            } else if (cell.getNearbyMines() == 0) {
                revealAdjacentCells(field, x, y);
            } else {
                cell.setRevealed(true);
            }

            /*if(allNonMineCellsRevealed(field)){
                cellRepo.findAllByGameIdAndRevealed(getGame(id), false);
            }*/
        }
        
        cellRepo.saveAll(field);

        List<Cell> temp = cellRepo.findAllByGameIdAndRevealed(getGame(id), true); 
        
        if(temp.size() == 144){ //to check if the user won
            cellRepo.deleteAll(cellRepo.findAllByGameId(getGame(id)));
            gameRepo.delete(getGame(id));
        }

        return temp;
    }

    private void revealAdjacentCells(List<Cell> field, int x, int y) {
        if (x < 0 || x >= 13 || y < 0 || y >= 13) {
            return; 
        }
    
        Cell cell = field.get(y * 13 + x);
        if (cell.isRevealed()) {
            return; 
        }
    
        cell.setRevealed(true);
    
        if (cell.getNearbyMines() == 0) {
            int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
            int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
            for (int i = 0; i < 8; i++) {
                revealAdjacentCells(field, x + dx[i], y + dy[i]);
            }
        }
    }

    /*private boolean allNonMineCellsRevealed(List<Cell> field) {
        boolean onemissing = false;
        for (Cell cell : field) {
            if (cell.getMonsterId() != 1 && !cell.isRevealed()) {
                if(onemissing)
                    return false;
                onemissing = true;
            }
        }
        return true; 
    }
    */
}
