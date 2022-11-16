public class Parking extends Spaces {
    /**
     * Parking action - Nothing
     * @param p active Player
     */
    @Override
    void action(Player p) {
        // TODO: nothing happens in free parking
    }

    @Override
    String getName() {
        return "Free Parking";
    }
}
