import java.util.Optional;
import java.util.Scanner;

//Неправильно поднимается уровень: всегда после 110 опыта, каждый бой.
public class Game {

    private volatile Hero hero;
    private volatile Skeleton skeleton;
    private volatile Goblin goblin;
    private Thread battle;
    private Scanner scanner;
    private static String stats;
    private final String MAINMENU =
            """
                    Меню:
                    1. К торговцу
                    2. В Темный Лес
                    3. На выход
                    """;
    public final String DARKFORESTMENU =
            """
                    Меню:
                    1. Вернуться в город
                    2. Продолжить бой
                    """;
    public final String TRADERMENU =
            """
                    Меню:
                    1. Купить зелье здоровья (20 золотых)
                    2. Выйти в город""";
    private boolean exit = false;

    public static void main(String[] args) {

        Game game = new Game();
        game.createHero();
        game.printMainMenu();
    }

    private String createHero() {
        System.out.println("Назовите своего героя: ");
        scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        hero = Hero.Builder.newInstance().setName(name).setHealth(100).setAgility(10).setStrength(10).build();
        System.out.println(hero.toString());
        return hero.toString();
    }

    public void setExit() {
        exit = true;
    }

//Метод вывода главного меню

    public Optional printMainMenu() {
        stats = "Level: " + hero.getLevel() + " Gold: " + hero.getGold() + " Health: " + hero.getHealth();
        System.out.println(stats);
        System.out.println("Куда хотите пойти?");
        System.out.println(MAINMENU);
        scanner = new Scanner(System.in);
        int result = scanner.nextInt();
        if(exit) {
            return Optional.empty();
        }
        switch (result) {
            case 1:
                printTraderMenu();
                break;
            case 2:
                battle = new Thread(new DarkForest(hero, this));
                battle.start();
                System.out.println("Вы вошли в Темный лес. Повсюду скелеты и гоблины, берегитесь!");
                try {
                    battle.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printDarkForestMenu();
                break;
            case 3:
                return Optional.empty();

        }

        if (!exit || !hero.isDead()) {
            printMainMenu();
        } else {
            System.out.println("GAME OVER");
            return Optional.empty();
        }
        return Optional.empty();
    }

//Метод вывода меню Темного леса

    public Optional printDarkForestMenu() {
        //Печатаем меню опций темного леса
        stats = "Level: " + hero.getLevel() + " Gold: " + hero.getGold() + " Health: " + hero.getHealth();
        System.out.println(stats);
        System.out.println(DARKFORESTMENU);
        //Ждем выбор (если не первая итерация, параллельно запустится бой и будет выведен лог боя)
        int result = scanner.nextInt();
        if(exit) {
            return Optional.empty();
        }
        switch (result) {
            case 1:
                printMainMenu();
                break;
            case 2:
                battle = new Thread(new DarkForest(hero, this));
                battle.start();
                try {
                    battle.join();
                    if(!hero.isDead()){
                        printDarkForestMenu();
                    } else {
                        System.out.println("Игра окончена.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }

        return Optional.empty();
    }

//Метод вывода меню торговца
    public Optional printTraderMenu(){
        stats = "Level: " + hero.getLevel() + " Gold: " + hero.getGold() + " Health: " + hero.getHealth();
        System.out.println(stats);
        System.out.println(TRADERMENU);
        int result = scanner.nextInt();
        switch (result) {
            case 1:
                Trader.buyPotion(hero);
                printTraderMenu();
                break;
            case 2:
                printMainMenu();
        }
        return Optional.empty();
    }
}
