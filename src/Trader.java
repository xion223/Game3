public class Trader {

    Hero hero;

    public static Hero buyPotion(Hero hero) {
        //1 банка стоит 20 золота
        int price = 20;
        hero.buyAndHeal(price);
        return hero;
    }
}
