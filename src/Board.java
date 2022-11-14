import java.util.HashMap;
import java.util.LinkedList;

public class Board {
    protected static HashMap<Integer, Spaces> spaces;
    protected static LinkedList<Chance> chance;                   //Chance cards
    protected static LinkedList<Community> communityChests;       //Community Chest cards

    /**
     * Board constructor
     * Because the board will not change during the game, all spaces are initialized in the Board constructor
     */
    public Board() {
        //TODO: complete constructor (+ Spaces, Chance, Community cons`tructors)
        spaces = new HashMap<>();
        spaces.put(0, new Go());
        spaces.put(1, new Streets("SAC", 2, 60, 30, "Brown"));


        communityChests.add(0, new Community("payBank", 10, "copay"));
        chance.add(0, new Chance("collectPlayers", 10, "birthday"));
    }

    /**
     * Getter: Spaces on Board
     * @return HashMap of spaces
     */
    public HashMap<Integer, Spaces> getSpaces() {
        return spaces;
    }
}
