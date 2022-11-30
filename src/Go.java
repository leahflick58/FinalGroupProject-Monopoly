public class Go extends Spaces {
    public Go(int xCoord, int yCoord, String labelString, int rotationDegrees) {
        super(xCoord, yCoord, labelString, rotationDegrees);
    }

    /**
     * Go action - pay Player $200
     * @param p active Player
     */
    @Override
    void action(Player p) {
        p.addOrSubBankBalance(200);
    }

    @Override
    String spaceName() {
        return "Go";
    }
}
