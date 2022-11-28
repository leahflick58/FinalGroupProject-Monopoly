import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Board {
    protected static HashMap<Integer, Spaces> spaces;
    protected static HashMap<Spaces, Integer> locations;
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


        //Cards information taken from https://www.monopolyland.com/list-monopoly-chance-community-chest-cards/

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



        //TODO: create shuffle method opt
        chance = new LinkedList<>();
        chance.add(new Chance("advance", 39, "Advance to Boardwalk"));
        chance.add(new Chance("advance", 0, "Advance to " + spaces.get(0).getName() + " (Collect $200)"));
        chance.add(new Chance("advance", 24, "Advance to " + spaces.get(24).getName() + ". If you " +
                "pass Go, collect $200"));
        chance.add(new Chance("advance", 11, "Advance to " + spaces.get(11).getName() + ". If you " +
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


}
