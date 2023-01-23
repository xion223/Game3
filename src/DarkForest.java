import java.util.Random;

public class DarkForest implements Runnable {

    Hero hero;
    Creature enemy;
    Game game;
    Random rand = new Random();B
    Thread levelUpListener;
    boolean endFight = false;

    DarkForest(Hero hero, Game game) {
        this.game = game;
        this.hero = hero;
        levelUpListener = new Thread(new LevelUpListener(hero));
        levelUpListener.start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            createMonster();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createMonster() {
        int randomizeEnemy = getRandInt(3, 1);
        System.out.println("Появляется монстр!");
        try {
            if (randomizeEnemy <= 1) {
                enemy = Skeleton.Builder.newInstance().setName("Skeleton").setHealth(80).setAgility(5).setStrength(5).build();
            }
            if (randomizeEnemy >= 2) {
                enemy = Goblin.Builder.newInstance().setName("Goblin").setHealth(100).setAgility(4).setStrength(6).build();
            }
            System.out.println(fight());
        } catch (NullPointerException e) {
            System.out.println("EXCEPTION: " + randomizeEnemy);
        }
    }

    private int getRandInt(int max, int min) {
        int result = rand.nextInt(((max - min) + 1) + min);
        return result;
    }

    private String fight() {
        int randomResist;
        //Начинается бой
        while (!endFight) {
            randomResist = getRandInt(101, 0);
            hero.attack(enemy, randomResist);
            if (enemy.getHealth() <= 0) {
                endFight = true;
                break;
            }

            randomResist = getRandInt(101, 0);
            enemy.attack(hero, randomResist);
            if (hero.getHealth() <= 0) {
                endFight = true;
                break;
            }
        }
        String result = "";

        if (hero.getHealth() > 0 && enemy.getHealth() <= 0) {

            int randomGold = getRandInt(10, 0);
            int receivedGold = randomGold + (enemy.getLevel());
            int receivedExp = ((enemy.getLevel() * 10) + 100);

            hero.addGold(receivedGold);
            hero.addExperience(receivedExp);
            result = "Gold: " + receivedGold + " , and exp: " + receivedExp + "\n";
            result += "Подожди, закапываем монстра, считаем золото...";

        } else if (enemy.getHealth() > 0 && hero.getHealth() <= 0) {
            result = "Герой мертв, нажмите любую кнопку для выхода.";
            game.setExit();
            hero.setDead();
        }
        return result;
    }
}
