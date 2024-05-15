package it.ms.api.data.entity;

import ch.qos.logback.core.joran.sanity.Pair;
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

    @Column(name = "field")
    private String field;

	public Game() {

        this.level = 1;
        this.gold = 0;
        this.health = 100;
        this.maxHealth = 100;
        this.mana = 100;
        this.maxMana = 100;
        this.field = GenerateField(level);
	}

    public Game(long id) {

        this.id = id;
        this.level = 1;
        this.gold = 0;
        this.health = 100;
        this.maxHealth = 100;
        this.mana = 100;
        this.maxMana = 100;
        this.field = GenerateField(level);
	}

	public Game(int level, int gold, int health, int maxHealth, int mana, int maxMana) {
		
        this.level = level;
        this.gold = gold;
        this.health = health;
        this.maxHealth = maxHealth;
        this.mana = mana;
        this.maxMana = maxMana;
        this.field = GenerateField(level);
	}

    public static String GenerateField(int level) {

        char[] field = new String(new char[121]).replace('\0', (char)Integer.parseInt("10100000", 2)).toCharArray();

        for(int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                field[48 + (i * 11) + j] = (char)Integer.parseInt("0000" + Integer.toBinaryString(field[48 + (i * 11) + j]).substring(3, 7), 2);

        field[60] = (char)Integer.parseInt("00000001", 2);

        for (int i = 0; i < (int)Math.max(73, 19 + (Math.pow(level, 1 + level / 50) / Math.pow(level, 1 + level / 1000))); i++) {

            field[(int)Math.round(Math.random() * 10 * 11) + (int)Math.round(Math.random() * 11)] = (char)Integer.parseInt("00000011", 2);
        }

        for (int i = 0; i < 11; i++) {

            for (int j = 0; j < 11; j++) {

                int count = 0;
                for(int a = -1; a < 2; a++)
                    for (int b = -1; b < 2; b++) {
                        
                        if (Integer.toBinaryString(field[(int)Math.min(Math.max(0, i * 11 + a), 110) + (int)Math.min(Math.max(0, j + b), 11)]).substring(4, 7).equals("0000")) {
                            field[(int)Math.min(Math.max(0, i * 11 + a), 110) + (int)Math.min(Math.max(0, j + b), 11)] = ""
                            //fix fox doesn't work. (need to count nearby bombs)
                        }
                    }
            }
        }



        return field;
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

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
	public String toString() {
		return "Game " + id + ":  [Level-" + level + ", Gold-" + gold + ", Health-" + health + "/" + maxHealth + ", Mana-" + mana + "/" + maxMana + ", Field-{" + field + "}]";
	}
}
