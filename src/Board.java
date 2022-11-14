import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Board {
    protected static HashMap<Integer, Spaces> spaces;
    protected LinkedList<Chance> chance;                   //Chance cards
    protected LinkedList<Community> communityChests;       //Community Chest cards

    public Board() {
        //TODO: complete constructor (+ Spaces, Chance, Community constructors)
        spaces = new HashMap<>();
        spaces.put(0, new Go());
//        spaces.put(1, new Streets());

        //chance = new Chance();
        //communityChests = new Community();
    }

    /**
     * Getter: Spaces on Board
     * @return
     */
    public HashMap<Integer, Spaces> getSpaces() {
        return spaces;
    }
}
