package MonopolyGUI;

public class ParkingGUI extends SpacesGUI {
    public ParkingGUI(int xCoord, int yCoord, String labelString, int rotationDegrees) {
        super(xCoord, yCoord, labelString, rotationDegrees);
    }

    /**
     * ParkingGUI action - Nothing
     * @param p active PlayerGUI
     */
    @Override
    void action(PlayerGUI p) {
        // Nothing happens in free parking
    }

    @Override
    String spaceName() {
        return "Free Parking";
    }
}
