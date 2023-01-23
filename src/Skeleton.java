public class Skeleton extends Creature {

    public Skeleton(Builder builder) {
        super(builder.name, builder.health, builder.strength, builder.agility, 1);
    }


    public static class Builder {

        private String name;
        private int health;
        private int strength;
        private int agility;

        private Builder() {
        }

        ;

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

        public Skeleton build() {
            return new Skeleton(this);
        }

    }

    @Override
    public String toString() {
        return "Skeleton created!";
    }

    @Override
    public void attack(Creature enemy, int randomResist) {
        System.out.print("Skeleton ");
        super.attack(enemy, randomResist);
    }
}
