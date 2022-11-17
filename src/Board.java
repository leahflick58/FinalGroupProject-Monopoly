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
        spaces.put(1, new Streets("Med", 2, 60, 30, "Brown"));  //10 with house
        spaces.put(2, new CommunitySpace());
        spaces.put(3, new Streets("Baltic", 4, 60, 30, "Brown"));   //20
        spaces.put(4, new Taxes());     //income tax
        spaces.put(5, new Railroads("Reading", 25,200,0));
        spaces.put(6, new Streets("Oriental", 6,100,50, "Blue"));   //30
        spaces.put(7, new ChanceSpace());
        spaces.put(8, new Streets("Vermont", 6,100,50,"Blue")); //30 with house
        spaces.put(9, new Streets("Connecticut",8,120,60,"Blue"));  //40

        spaces.put(10, new Jail());
        spaces.put(11, new Streets("St Charles", 10,140,70,"Magenta")); //50
        spaces.put(12, new Utilities("Electric Company", 4,150,75));
                                    //4x dice roll or 10x if both utilities owned by this player
        spaces.put(13, new Streets("States", 10,140,70,"Magenta")); //50
        spaces.put(14, new Streets("Virginia", 12,160,80,"Magenta"));   //60
        spaces.put(15, new Railroads("Pennsylvania", 25,200,0));
        spaces.put(16, new Streets("St James", 14,180,90,"Orange"));    //70
        spaces.put(17, new CommunitySpace());
        spaces.put(18, new Streets("Tennessee", 14,180,90,"Orange"));   //70
        spaces.put(19, new Streets("New York", 16,200,100,"Orange"));   //80

        spaces.put(20, new Parking());
        spaces.put(21, new Streets("Kentucky", 18,220,110,"Red"));  //90
        spaces.put(22, new ChanceSpace());
        spaces.put(23, new Streets("Indiana", 18,220,110,"Red"));   //90
        spaces.put(24, new Streets("Illinois", 20,240,120,"Red"));  //100
        spaces.put(25, new Railroads("B & O", 25,200,0));
        spaces.put(26, new Streets("Atlantic", 22,260,130,"Yellow"));   //110
        spaces.put(27, new Streets("Ventnor", 22,260,130,"Yellow"));    //110
        spaces.put(28, new Utilities("Water Works", 4,150,75));
                                        //rent is 4x dice roll, 10x if both utilities
        spaces.put(29, new Streets("Marvin Gardens", 24,280,140,"Yellow")); //120

        spaces.put(30, new GoToJail());
        spaces.put(31, new Streets("Pacific", 26,300,150,"Green")); //130
        spaces.put(32, new Streets("NC", 26,300,150,"Green"));  //130
        spaces.put(33, new CommunitySpace());
        spaces.put(34, new Streets("Pennsylvania", 28,320,160,"Green"));    //150
        spaces.put(35, new Railroads("Short Line", 25,200,0));
        spaces.put(36, new ChanceSpace());
        spaces.put(37, new Streets("Park Place", 35,350,175,"Blue"));    //175
        spaces.put(38, new Taxes());
        spaces.put(39, new Streets("Boardwalk", 50,400,200, "Blue"));   //200



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
