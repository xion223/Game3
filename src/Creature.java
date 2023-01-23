import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Creature {

    private final String name;
    private int health;
    private int strength;
    private int agility;
    private int level;
    private Hero hero;

    public Creature(String name, int health, int strength, int agility, int level) {
        this.name = name;
        this.health = health;
        this.strength = strength;
        this.agility = agility;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return this.health;
    }

    public int getStrength() {
        return strength;
    }

    public int getAgility() {
        return agility;
    }

    public int getLevel() {
        return level;
    }

    public Creature setHealth(int newHealth) {
        health = newHealth;
        return this;
    }

    public Creature addStrength() {
        strength++;
        return this;
    }

    public Creature addAgility() {
        agility++;
        return this;
    }

    public Creature addLevel() {
        level++;
        return this;
    }

    public void attack(Creature enemy, int randomResist) {
        StringBuilder result = new StringBuilder();
        boolean hit = this.getAgility() * 3 >= randomResist - this.getAgility();
        System.out.print(" attacks!");
        System.out.println("RR = " + randomResist);
        if (hit) {
            System.out.println("Hit! ");
            boolean criticalHit = this.getAgility() * 3 <= randomResist + 5;
            int damage = this.getStrength() * 2;

            if (criticalHit) {
                result.delete(0, 3);
                System.out.println("Critical damage! ");
                damage = this.getStrength() * 2;
            }
            System.out.println("Damage: " + damage);
            enemy.setHealth(enemy.getHealth() - damage);

        } else {
            System.out.println("Miss! Damage is 0");
        }
        System.out.println(this.getName() + " health = " + this.getHealth());
    }
}
