import java.util.LinkedList;

public class ChanceSpace extends Spaces {
    LinkedList<Chance> chance;

    public ChanceSpace() {
        //TODO
    }

    /**
     * Draws a card from the Chance deck and carries out the assigned action
     * @param p active Player
     */
    @Override
    void action(Player p) {
        chance.get(0).action(p);
        chance.addLast(chance.get(0));
        chance.remove(chance.get(0));
    }
}
