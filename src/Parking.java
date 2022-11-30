public class Parking extends Spaces {
    public Parking(int xCoord, int yCoord, String labelString, int rotationDegrees) {
        super(xCoord, yCoord, labelString, rotationDegrees);
    }

    /**
     * Parking action - Nothing
     * @param p active Player
     */
    @Override
    void action(Player p) {
        // Nothing happens in free parking
    }

    @Override
    String spaceName() {
        return "Free Parking";
    }
}
