import java.util.*;

public class Board {

    protected static HashMap<Integer, Spaces> spaces;
    protected static HashMap<Spaces, Integer> locations;
    protected static HashMap<String, Set<Streets>> colorGroups;
    protected static Set<Streets> brown;
    protected static Set<Streets> lightBlue;
    protected static Set<Streets> pink;
    protected static Set<Streets> orange;
    protected static Set<Streets> red;
    protected static Set<Streets> yellow;
    protected static Set<Streets> green;
    protected static Set<Streets> darkBlue;
    protected static LinkedList<Chance> chance;                   //Chance cards
    protected static LinkedList<Community> communityChests;       //Community Chest cards

    /**
     * Board constructor.
     * Because the board will not change during the game, all spaces and cards are initialized
     * in the Board constructor
     */
    public Board() {
        //TODO: create Street names
        spaces = new HashMap<>();
        spaces.put(0, new Go(6,6,"Go",180));
        spaces.put(1, new Streets(106, 6,"Street",180,"Mediterranean Avenue", 2, 60, "Brown"));  //10 with house
        spaces.put(2, new CommunitySpace(206, 6, "Community", 180));
        spaces.put(3, new Streets(306, 6, "Street",180, "Baltic Avenue", 4, 60, "Brown"));   //20
        spaces.put(4, new Taxes(406, 6, "Taxes", 180));
        spaces.put(5, new Railroads(506, 6, "Railroad", 180,"Reading Railroad", 25,200));
        spaces.put(6, new Streets(606, 6, "Street", 180,"Oriental Avenue", 6,100,"Light Blue"));   //30
        spaces.put(7, new ChanceSpace(706, 6, "Chance", 180));
        spaces.put(8, new Streets(806, 6, "Street", 180, "Vermont Avenue", 6,100,"Light Blue")); //30 with house
        spaces.put(9, new Streets(906, 6, "Street", 180, "Connecticut Avenue",8,120,"Light Blue"));  //40

        spaces.put(10, new Jail(1006, 6, "Jail", -135));
        spaces.put(11, new Streets(1006, 106, "Street", -135, "St Charles Place", 10,140,"Pink")); //50
        spaces.put(12, new Utilities(1006, 206, "Utility", -135,"Electric Company", 4,150));
                                    //4x dice roll or 10x if both utilities owned by this player
        spaces.put(13, new Streets(1006, 306, "Street", -135,"States Avenue", 10,140,"Pink")); //50
        spaces.put(14, new Streets(1006, 406, "Street", -135,"Virginia Avenue", 12,160,"Pink"));   //60
        spaces.put(15, new Railroads(1006, 506, "Railroad", -135, "Pennsylvania Railroad", 25,200));
        spaces.put(16, new Streets(1006, 606, "Street", -135,"St James Place", 14,180,"Orange"));    //70
        spaces.put(17, new CommunitySpace(1006, 706, "Community", -135));
        spaces.put(18, new Streets(1006, 806, "Street", -135, "Tennessee Avenue", 14,180,"Orange"));   //70
        spaces.put(19, new Streets(1006, 906, "Street", -135, "New York Avenue", 16,200,"Orange"));   //80

        spaces.put(20, new Parking(1006, 1006, "Parking", -90));
        spaces.put(21, new Streets(906, 1006, "Street", -90, "Kentucky Avenue", 18,220,"Red"));  //90
        spaces.put(22, new ChanceSpace(806, 1006, "Chance", -90));
        spaces.put(23, new Streets(706, 1006, "Street", -90, "Indiana Avenue", 18,220,"Red"));   //90
        spaces.put(24, new Streets(606, 1006, "Street", -90, "Illinois Avenue", 20,240,"Red"));  //100
        spaces.put(25, new Railroads(506, 1006, "Railroad", -90, "B & O Railroad", 25,200));
        spaces.put(26, new Streets(406, 1006, "Street", -90, "Atlantic Avenue", 22,260,"Yellow"));   //110
        spaces.put(27, new Streets(306, 1006, "Street", -90, "Ventnor Avenue", 22,260,"Yellow"));    //110
        spaces.put(28, new Utilities(206, 1006, "Utility", -90, "Water Works", 4,150));
                                        //rent is 4x dice roll, 10x if both utilities
        spaces.put(29, new Streets(106, 1006, "Street", -90, "Marvin Gardens", 24,280,"Yellow")); //120

        spaces.put(30, new GoToJail(6, 1006, "Go To Jail", -45));
        spaces.put(31, new Streets(6, 906, "Street", 0, "Pacific Avenue", 26,300,"Green")); //130
        spaces.put(32, new Streets(6, 806, "Street", 0, "North Carolina Avenue", 26,300,"Green"));  //130
        spaces.put(33, new CommunitySpace(6, 706, "Community", 0));
        spaces.put(34, new Streets(6, 606, "Street", 0, "Pennsylvania Avenue", 28,320,"Green"));    //150
        spaces.put(35, new Railroads(6, 506, "Railroad", 0, "Short Line Railroad", 25,200));
        spaces.put(36, new ChanceSpace(6, 406, "Chance", 0));
        spaces.put(37, new Streets(6, 306, "Street", 0, "Park Place", 35,350,"Dark Blue"));    //175
        spaces.put(38, new Taxes(6, 206, "Taxes", 0));
        spaces.put(39, new Streets(6, 106, "Street", 0, "Boardwalk", 50,400, "Dark Blue"));   //200

        //Cards information taken from https://www.monopolyland.com/list-monopoly-chance-community-chest-cards/
        //Deck of Community Chest cards
        communityChests = new LinkedList<>();
        communityChests.add(new Community("goToJail", 1, "Go to Jail. Go directly to Jail, do not" +
                " pass Go, do not collect $200"));
        communityChests.add(new Community("advance", 0, "Advance to Go (Collect $200)"));
        communityChests.add(new Community("collectBank", 200, "Bank error in your favor. " +
                "Collect $200"));
        communityChests.add(new Community("payBank", 50, "Doctor's fee. Pay $50"));
        communityChests.add(new Community("collectBank", 50, "From sale of stock you get $50."));
        communityChests.add(new Community("getOutOfJail", 1, "Get Out of Jail Free"));

        communityChests.add(new Community("collectBank", 100, "Holiday fund matures. Receive" +
                " $100"));
        communityChests.add(new Community("collectBank", 20, "Income tax refund. Collect $20"));
        communityChests.add(new Community("collectPlayers", 10,"It is your birthday. Collect $10" +
                " from every player."));
        communityChests.add(new Community("collectBank", 100, "Life insurance matures. Collect " +
                "$100"));
        communityChests.add(new Community("payBank", 100, "Pay hospital fees of $100"));
        communityChests.add(new Community("payBank",50, "Pay school fees of $50"));
        communityChests.add(new Community("collectBank",25, "Receive $25 consultancy " +
                "fee"));
        communityChests.add(new Community("homeImprovement", 0, "You are assessed for street " +
                "repair: $40 per house, $115 per hotel"));
        communityChests.add(new Community("collectBank",10, "You won second prize in a beauty " +
                "contest. Collect $10"));
        communityChests.add(new Community("collectBank", 100,"You inherit $100"));


        //Deck of Chance cards
        chance = new LinkedList<>();
        chance.add(new Chance("advance", 39, "Advance to Boardwalk"));
        chance.add(new Chance("advance", 0, "Advance to " + spaces.get(0).spaceName() + " (Collect $200)"));
        chance.add(new Chance("advance", 24, "Advance to " + spaces.get(24).spaceName() + ". If you " +
                "pass Go, collect $200"));
        chance.add(new Chance("advance", 11, "Advance to " + spaces.get(11).spaceName() + ". If you " +
                "pass Go, collect $200"));
        chance.add(new Chance("advNearestRR", 0, "Advance to the nearest Railroad. If you " +
                "pass Go, collect $200"));
        chance.add(new Chance("advNearestRR", 0, "Advance to the nearest Railroad. If you " +
                "pass Go, collect $200"));
        chance.add(new Chance("advNearestUtil", 0, "Advance to the nearest Utility. If unowned, " +
                "you may buy it from the Bank. If owned, pay owner the rent to which they are entitled. "));
        chance.add(new Chance("collectBank",50,"Bank pays you dividend of $50"));
        chance.add(new Chance("goBack", 3, "Go back 3 spaces"));
        chance.add(new Chance("goToJail", 1, "Go to Jail. Go directly to Jail, " +
                "do not pass Go, do not collect $200."));
        chance.add(new Chance("homeImprovement", 0, "Make general repairs on all your property." +
                " For each house pay $40. For each hotel pay $115."));
        chance.add(new Chance("payBank",15, "Speeding fine $15"));
        chance.add(new Chance("advance", 0, "Take a trip to Reading Railroad. If you pass Go, " +
                "collect $200."));
        chance.add(new Chance("payPlayers", 50, "You have been selected Chairman of the Board." +
                " Pay each player $50."));
        chance.add(new Chance("collectBank", 150, "Your building loan matures. Collect $150"));

        //Shuffle card decks
        Collections.shuffle(communityChests);
        Collections.shuffle(chance);

        //Color Group Sets
        colorGroups = new HashMap<>();
        colorGroups.put("Brown", new HashSet<>(List.of(
                (Streets) spaces.get(1),
                (Streets) spaces.get(3))));
        colorGroups.put("Light Blue", new HashSet<>(List.of(
                (Streets) spaces.get(6),
                (Streets) spaces.get(8),
                (Streets) spaces.get(9))));
        colorGroups.put("Pink", new HashSet<>(List.of(
                (Streets) spaces.get(11),
                (Streets) spaces.get(13),
                (Streets) spaces.get(14))));
        colorGroups.put("Orange", new HashSet<>(List.of(
                (Streets) spaces.get(16),
                (Streets) spaces.get(18),
                (Streets) spaces.get(19))));
        colorGroups.put("Red", new HashSet<>(List.of(
                (Streets) spaces.get(21),
                (Streets) spaces.get(23),
                (Streets) spaces.get(24))));
        colorGroups.put("Yellow", new HashSet<>(List.of(
                (Streets) spaces.get(26),
                (Streets) spaces.get(27),
                (Streets) spaces.get(29))));
        colorGroups.put("Green", new HashSet<>(List.of(
                (Streets) spaces.get(31),
                (Streets) spaces.get(32),
                (Streets) spaces.get(34))));
        colorGroups.put("Dark Blue", new HashSet<>(List.of(
                (Streets) spaces.get(37),
                (Streets) spaces.get(39))));
    }

    /**
     * Getter: Spaces on Board
     * @return HashMap of spaces
     */
    public HashMap<Integer, Spaces> getSpaces() {
        return spaces;
    }

    /**
     * Reverses HashMap<Integer location, Spaces space> spaces
     * @param spaces HashMap of an inherited space class and it's relative integer location
     * @return HashMap<Spaces, Integer> locations
     */
    public HashMap<Spaces, Integer> getLocations(HashMap<Integer, Spaces> spaces) {
        locations = new HashMap<>();
        for(Integer i : spaces.keySet()) {
            locations.put(spaces.get(i), i);
        }
        return locations;
    }

    /**
     * Getter: Brown color group
     * @return HashSet of Streets properties in the Brown color group
     */
    public static Set<Streets> getBrown() {
        return brown;
    }
    /**
     * Getter: Light Blue color group
     * @return HashSet of Streets properties in the Light Blue color group
     */
    public static Set<Streets> getLightBlue() {
        return lightBlue;
    }
    /**
     * Getter: Pink color group
     * @return HashSet of Streets properties in the Pink color group
     */
    public static Set<Streets> getPink() {
        return pink;
    }
    /**
     * Getter: Orange color group
     * @return HashSet of Streets properties in the Orange color group
     */
    public static Set<Streets> getOrange() {
        return orange;
    }
    /**
     * Getter: Red color group
     * @return HashSet of Streets properties in the Red color group
     */
    public static Set<Streets> getRed() {
        return red;
    }
    /**
     * Getter: Yellow color group
     * @return HashSet of Streets properties in the Yellow color group
     */
    public static Set<Streets> getYellow() {
        return yellow;
    }
    /**
     * Getter: Green color group
     * @return HashSet of Streets properties in the Green color group
     */
    public static Set<Streets> getGreen() {
        return green;
    }
    /**
     * Getter: Dark Blue color group
     * @return HashSet of Streets properties in the Dark Blue color group
     */
    public static Set<Streets> getDarkBlue() {
        return darkBlue;
    }
}
