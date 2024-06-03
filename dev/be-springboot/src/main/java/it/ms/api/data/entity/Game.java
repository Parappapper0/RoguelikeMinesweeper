package it.ms.api.data.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

    /*
    @OneToMany(mappedBy="game_id", cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @Column(name = "cells")
    private List<Cell> field;
     */

	public Game() {
        //this.field = GenerateField(this, level);
	}

    public Game(long id) {

        this.id = id;
        //this.field = GenerateField(this, level);
	}

    public static List<Cell> GenerateField(Game creator, int level) {

        List<List<Cell>> newField = new ArrayList<>();

        for (int i = 0; i < 13; i++) {
            newField.add(new ArrayList<Cell>());
            for (int j = 0; j < 13; j++)
                newField.get(i).add(new Cell(creator, i * 13 + j));
        }
            

        int max_number_of_enemies = (int)Math.max(73, 19 + (Math.pow(level, 1 + level / 50) / Math.pow(level, 1 + level / 1000)));

        for (int i = 0; i < max_number_of_enemies; i++) {

            newField.get((int)(Math.round(Math.random() * 12))).get((int)(Math.round(Math.random() * 12))).setMonsterId(1);
        }

        for (int i = 0; i < 13; i++) {

            for (int j = 0; j < 13; j++) {

                if (i != 0 && newField.get(i-1).get(j).getMonsterId() != 0 && newField.get(i-1).get(j).getMonsterId() != -1)
                    newField.get(i).get(j).incrementNearbyMines();

                if (i != 12 && newField.get(i+1).get(j).getMonsterId() != 0 && newField.get(i+1).get(j).getMonsterId() != -1)
                    newField.get(i).get(j).incrementNearbyMines();
                

                if (i != 0 && j != 0 && newField.get(i-1).get(j-1).getMonsterId() != 0 && newField.get(i-1).get(j-1).getMonsterId() != -1)
                    newField.get(i).get(j).incrementNearbyMines();

                if (j != 0 && newField.get(i).get(j-1).getMonsterId() != 0 && newField.get(i).get(j-1).getMonsterId() != -1)
                    newField.get(i).get(j).incrementNearbyMines();

                if (i != 12 && j != 0 && newField.get(i+1).get(j-1).getMonsterId() != 0 && newField.get(i+1).get(j-1).getMonsterId() != -1)
                    newField.get(i).get(j).incrementNearbyMines();


                if (i != 0 && j != 12 && newField.get(i-1).get(j+1).getMonsterId() != 0 && newField.get(i-1).get(j+1).getMonsterId() != -1)
                    newField.get(i).get(j).incrementNearbyMines();

                if (j != 12 && newField.get(i).get(j+1).getMonsterId() != 0 && newField.get(i).get(j+1).getMonsterId() != -1)
                    newField.get(i).get(j).incrementNearbyMines();

                if (i != 12 && j != 12 && newField.get(i+1).get(j+1).getMonsterId() != 0 && newField.get(i+1).get(j+1).getMonsterId() != -1)
                    newField.get(i).get(j).incrementNearbyMines();
            }
        }
        
        List<Cell> flattened_field = new ArrayList<>();

        for(int i = 0; i < 169; i++) {

            flattened_field.add(newField.get(i % 13).get((i - (i % 13)) / 13));
        }

        return flattened_field;
    }

	public long getId() {
        return id;
    }

    @Override
	public String toString() {
		return "Game " + id;
	}
}
