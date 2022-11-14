public class Utilities extends Property {
    public Utilities(String name, int rent, int price, int mortgage) {
        super(name, rent, price, mortgage);
    }

    // did a new dice roll to save hassle
    @Override
    int getTotalRent(Player p) {
        int roll = GameLoop.rollDice();
        int numUtilities = p.numberUtilities();
        if (numUtilities == 2) {
            return roll * 10;
        }
        return roll * 4;
    }
}
