package MonopolyGUI;

public class GoGUI extends SpacesGUI {
    public GoGUI(int xCoord, int yCoord, String labelString, int rotationDegrees) {
        super(xCoord, yCoord, labelString, rotationDegrees);
    }

    /**
     * GoGUI action - pay PlayerGUI $200
     * @param p active PlayerGUI
     */
    @Override
    void action(PlayerGUI p) {
        p.addOrSubBankBalance(200);
    }

    @Override
    String spaceName() {
        return "GoGUI";
    }
}
