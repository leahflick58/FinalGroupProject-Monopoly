public class Utilities extends Property {
    /**
     * Utilities have all the same parameters as their parent class, Property
     * @param name name of the property (i.e. "Beans on Broad", "Hall of Arts and Letters", etc.
     * @param rent rent due when someone other than the owner lands on the property
     * @param price price in order to buy property
     * @param mortgage the amount the player can receive from the bank from mortgaging the property
     *                 Finally, the boolean mortgaged is set to false
     */
    public Utilities(String name, int rent, int price, int mortgage) {
        super(name, rent, price, mortgage);
    }

    /**
     * Returns the amount of rent due based on Player's number of utilities
     * @param p Player who owns the respective property
     * @return total dollar amount due
     */
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
