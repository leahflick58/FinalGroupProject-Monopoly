public class Parking extends Spaces {
    /**
     * Parking action - Nothing
     * @param p active Player
     */
    @Override
    void action(Player p) {
        // Nothing happens in free parking
    }

    @Override
    String getName() {
        return "Free Parking";
    }
}
