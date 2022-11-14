import java.util.LinkedList;

public class CommunitySpace extends Spaces {
    public static LinkedList<Community> communityChest;

    public CommunitySpace() {
        //TODO
    }

    /**
     * Draws a card from the Community Chest deck and carries out the assigned action
     * @param p
     */
    @Override
    void action(Player p) {
        communityChest.get(0).action(p);
        communityChest.addLast(communityChest.get(0));
        communityChest.remove(communityChest.get(0));
    }
}
