public class GoToJail extends Spaces {
    /**
     * GoToJail action - send Player to jail space and set Player isInJail boolean to True
     * @param p active Player
     */
    @Override
    void action(Player p) {
        // TODO: Change to whatever int space Jail actually is
        p.setCurrentSpace(0);
        p.isInJail = true;
    }

    @Override
    String getName() {
        return "Go To Jail";
    }
}
