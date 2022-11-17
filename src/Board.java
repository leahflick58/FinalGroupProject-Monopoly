import java.util.HashMap;
import java.util.LinkedList;

public class Board {
    protected static HashMap<Integer, Spaces> spaces;
    protected static LinkedList<Chance> chance;                   //Chance cards
    protected static LinkedList<Community> communityChests;       //Community Chest cards

    /**
     * Board constructor
     * Because the board will not change during the game, all spaces and cards are initialized
     * in the Board constructor
     */
    public Board() {
        //TODO: complete constructor (+ Spaces, Chance, Community constructors)
        //TODO: create names and property values of spaces
        spaces = new HashMap<>();
        spaces.put(0, new Go());
        spaces.put(1, new Streets("Med", 2, 60, 30, "Brown"));
        spaces.put(2, new CommunitySpace());
        spaces.put(3, new Streets("Baltic", 2, 60, 30, "Brown"));
        spaces.put(4, new Taxes());     //income tax
        spaces.put(5, new Railroads("Reading", 1,1,1));
        spaces.put(6, new Streets("Oriental", 1,1,1, "Blue"));
        spaces.put(7, new ChanceSpace());
        spaces.put(8, new Streets("Vermont", 1,1,1,"Blue"));
        spaces.put(9, new Streets("Connecticut",1,1,1,"Blue"));

        spaces.put(10, new Jail());
        spaces.put(11, new Streets("St Charles", 1,1,1,"Magenta"));
        spaces.put(12, new Utilities("Electric Company", 1,1,1));
        spaces.put(13, new Streets("States", 1,1,1,"Magenta"));
        spaces.put(14, new Streets("Virginia", 1,1,1,"Magenta"));
        spaces.put(15, new Railroads("Pennsylvania", 1,1,1));
        spaces.put(16, new Streets("St James", 1,1,1,"Orange"));
        spaces.put(17, new CommunitySpace());
        spaces.put(18, new Streets("Tennessee", 1,1,1,"Orange"));
        spaces.put(19, new Streets("New York", 1,1,1,"Orange"));

        spaces.put(20, new Parking());
        spaces.put(21, new Streets("Kentucky", 1,1,1,"Red"));
        spaces.put(22, new ChanceSpace());
        spaces.put(23, new Streets("Can't read the board", 1,1,1,"Red"));
        spaces.put(24, new Streets("Illinois", 1,1,1,"Red"));
        spaces.put(25, new Railroads("B & O", 1,1,1));
        spaces.put(26, new Streets("Atlantic", 1,1,1,"Yellow"));
        spaces.put(27, new Streets("Too fuzzy", 1,1,1,"Yellow"));
        spaces.put(28, new Utilities("Water Works", 1,1,1));
        spaces.put(29, new Streets("Martin Gardens", 1,1,1,"Yellow"));

        spaces.put(30, new GoToJail());
        spaces.put(31, new Streets("Pacific", 1,1,1,"Green"));
        spaces.put(32, new Streets("NC", 1,1,1,"Green"));
        spaces.put(33, new CommunitySpace());
        spaces.put(34, new Streets("Pennsylvania", 1,1,1,"Green"));
        spaces.put(35, new Railroads("Short Line", 1,1,1));
        spaces.put(36, new ChanceSpace());
        spaces.put(37, new Streets("Park Place", 1,1,1,"Blue"));
        spaces.put(38, new Taxes());
        spaces.put(39, new Streets("Boardwalk", 1,1,1, "Blue"));



        //TODO: complete Community Chest cards
        communityChests = new LinkedList<>();
        communityChests.add(new Community("payBank", 10, "copay"));
        communityChests.add(new Community("payBank", 10, "copay"));

        //TODO: complete Chance cards
        chance = new LinkedList<>();
        chance.add(new Chance("collectPlayers", 10, "birthday"));
        chance.add(new Chance("collectPlayers", 10, "birthday"));
    }

    /**
     * Getter: Spaces on Board
     * @return HashMap of spaces
     */
    public HashMap<Integer, Spaces> getSpaces() {
        return spaces;
    }
}
