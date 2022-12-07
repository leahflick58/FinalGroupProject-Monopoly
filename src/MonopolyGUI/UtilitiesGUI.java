package MonopolyGUI;

public class UtilitiesGUI extends PropertyGUI {
    /**
     * UtilitiesGUI have all the same parameters as their parent class, PropertyGUI
     * @param name name of the property (i.e. "Beans on Broad", "Hall of Arts and Letters", etc.
     * @param rent rent due when someone other than the owner lands on the property
     * @param price price in order to buy property
     */
    public UtilitiesGUI(int xCoord, int yCoord, String labelString, int rotationDegrees, String name, int rent, int price) {
        super(xCoord, yCoord, labelString, rotationDegrees, name, rent, price);
    }

    /**
     * Returns the amount of rent due based on PlayerGUI's number of utilities
     * @param p PlayerGUI who owns the respective property
     * @return total dollar amount due
     */
    // did a new dice roll to save hassle
    @Override
    int getTotalRent(PlayerGUI p) {
        int roll = GameLoopGUI.rollDie() + GameLoopGUI.rollDie();
        int numUtilities = p.numberUtilities();
        if (numUtilities == 2) {
            return roll * 10;
        }
        return roll * 4;
    }
}
