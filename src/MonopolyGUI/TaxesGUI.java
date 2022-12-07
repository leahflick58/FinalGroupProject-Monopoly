package MonopolyGUI;

public class TaxesGUI extends SpacesGUI {
    public TaxesGUI(int xCoord, int yCoord, String labelString, int rotationDegrees) {
        super(xCoord, yCoord, labelString, rotationDegrees);
    }

    /**
     * TaxesGUI action - Pay specific amount of taxes to bank
     * @param p active PlayerGUI
     */
    @Override
    void action(PlayerGUI p) {
        // Normally there is an option to do 200 or 10% of all owned assets, I got rid of that choice
        p.addOrSubBankBalance(-200);
    }

    @Override
    String spaceName() {
        return "TaxesGUI";     //will need to split into Income and Luxury
    }
}
