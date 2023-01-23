public class LevelUpListener implements Runnable {

    Hero hero;
    int baseExp;

    public LevelUpListener(Hero hero) {
        this.hero = hero;
    }


    @Override
    public void run() {
        while (!hero.isDead()) {
            baseExp = hero.getLevel() * 300;
            if (hero.getExperience() > baseExp) {
                levelUp();
                System.out.println("Level " + hero.getLevel() + " achieved!");
                String stats = "Level: " + hero.getLevel() + " Gold: " + hero.getGold() + " Health: " + hero.getHealth();
                System.out.println(stats);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void levelUp() {
        hero.fullHealth().addLevel().addAgility().addStrength().setHealth(hero.getMaxHealth() + 10);
        hero.setMaxHealth();
    }
}
