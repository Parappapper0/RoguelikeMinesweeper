package it.ms.api.data.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cells")
public class Cell {
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;



    @ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "game_id", nullable = true)
    private Game game_id;


    @Column(name = "position")
    private int pos;


    @Column(name = "monster_id")
    private int monsterId;
    @Column(name = "nearby_mines")
    private int nearbyMines;

    public Cell() {

        pos = -1;
        monsterId = 0;
        nearbyMines = 0;
    }

    public Cell(Game game_id, int pos) {

        this.game_id = game_id;
        this.pos = pos;
        monsterId = 0;
        nearbyMines = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Game getGame_id() {
        return game_id;
    }

    public void setGame_id(Game game_id) {
        this.game_id = game_id;
    }



    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getPos() {
        return pos;
    }

    public int getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(int monsterId) {
        this.monsterId = monsterId;
    }

    public int getNearbyMines() {
        return nearbyMines;
    }

    public void setNearbyMines(int nearbyMines) {
        this.nearbyMines = nearbyMines;
    }

    public void incrementNearbyMines() {
        this.nearbyMines++;
    }
}