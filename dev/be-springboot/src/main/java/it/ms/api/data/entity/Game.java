package it.ms.api.data.entity;

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

	public Game() {

        this.level = 1;
        this.gold = 0;
        this.health = 100;
        this.maxHealth = 100;
        this.mana = 100;
        this.maxMana = 100;
	}

    public Game(long id) {

        this.id = id;
        this.level = 1;
        this.gold = 0;
        this.health = 100;
        this.maxHealth = 100;
        this.mana = 100;
        this.maxMana = 100;
	}

	public Game(int level, int gold, int health, int maxHealth, int mana, int maxMana) {
		
        this.level = level;
        this.gold = gold;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
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
		return "Game " + id + ":  [Level-" + level + ", Gold-" + gold + ", Health-" + health + "/" + maxHealth + ", Mana-" + mana + "/" + maxMana +"]";
	}
}
