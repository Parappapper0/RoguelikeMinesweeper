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

	@Column(name = "level")
	private int level;

	@Column(name = "gold")
	private int gold;

	@Column(name = "health")
	private int health;

    @Column(name = "maxHealth")
    private int maxHealth;

    @Column(name = "mana")
    private int mana;

    @Column(name = "maxMana")
    private int maxMana;

    /*
    @OneToMany(mappedBy="game_id", cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @Column(name = "cells")
    private List<Cell> field;
     */

	public Game() {

        this.level = 1;
        this.gold = 0;
        this.health = 100;
        this.maxHealth = 100;
        this.mana = 100;
        this.maxMana = 100;
        //this.field = GenerateField(this, level);
	}

    public Game(long id) {

        this.id = id;
        this.level = 1;
        this.gold = 0;
        this.health = 100;
        this.maxHealth = 100;
        this.mana = 100;
        this.maxMana = 100;
        //this.field = GenerateField(this, level);
	}

	public Game(int level, int gold, int health, int maxHealth, int mana, int maxMana) {
		
        this.level = level;
        this.gold = gold;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }


    @Override
	public String toString() {
		return "Game " + id + ":  [Level-" + level + ", Gold-" + gold + ", Health-" + health + "/" + maxHealth + ", Mana-" + mana + "/" + maxMana + "}]";
	}
}
