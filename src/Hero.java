

public class Hero extends Creature {

    private int experience;
    private int healPotion;
    private int gold;

    private int maxHealth;

    private boolean isDead;

    public Hero(Builder builder) {
        super(builder.name, builder.health, builder.strength, builder.agility, 1);
        maxHealth = builder.health;
        experience = 0;
        gold = 0;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getGold() {
        return gold;
    }

    public boolean isDead() {
        return isDead;
    }


    public Hero addExperience(int experience) {
        this.experience += experience;
        return this;
    }

    public int getExperience() {
        return this.experience;
    }

    public void setDead() {
        isDead = true;
    }

    public Hero addGold(int gold) {
        this.gold += gold;
        return this;
    }

    public Hero buyAndHeal(int price) {
        if (price > getGold()) {
            System.out.println("Sorry, you don`t have enough money! You need " + (price - getGold()) + " more. Go on and get it!");
        } else {
            gold -= 20;
            setHealth(getHealth() + 20);
            //здоровье не может быть выше максимального
            if (getHealth() > maxHealth) {
                setHealth(maxHealth);
            }
        }

        return this;
    }

    @Override
    public String toString() {
        return "Hero " + getName() + " created!";
    }

    @Override
    public void attack(Creature enemy, int randomResist) {
        System.out.print("Hero " + getHealth());
        super.attack(enemy, randomResist);
    }

    public Hero fullHealth() {
        setHealth(maxHealth);
        return this;
    }

    public Hero setMaxHealth() {
        maxHealth = getHealth();
        return this;
    }

    public static class Builder {

        private String name;
        private int health;
        private int strength;
        private int agility;
        private int level;

        private Builder() {
        }

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setHealth(int health) {
            this.health = health;
            return this;
        }

        public Builder setStrength(int strength) {
            this.strength = strength;
            return this;
        }

        public Builder setAgility(int agility) {
            this.agility = agility;
            return this;
        }

        public Hero build() {
            return new Hero(this);
        }
    }
}
