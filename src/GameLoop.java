import java.util.ArrayList;
import java.util.Random;

public class GameLoop {
    public static ArrayList<Player> players;
    private Board board;

    /**
     * GameLoop constructor: takes an ArrayList of player names and creates a new ArrayList of Player objects.
     * @param names
     */
    public GameLoop(ArrayList<String> names) {
        board = new Board();        //ArrayLists <Spaces>,<Chance>, <CommunityChest>
                                    //Board constructor should not take any args

        this.players = new ArrayList<>();
        for (String p : names) {
            this.players.add(new Player(p));
        }
    }

    /**
     * Iterates through multiple rounds of Monopoly until all but one player is bankrupt (winner)
     * @param players
     * @return Player that has won the game
     */
    //TODO: test logic
    public Player gameLoop(ArrayList<Player> players) {

        //iterate through rounds while no winner
        while(!hasWinner()) {
            play();
        }
        return winner();
    }

    /**
     * Plays a single Monopoly round
     */
    public void play() {
        for(Player p : players) {
            if (!p.bankrupt) {
                                        //if p.isinJail && p.usesJailFreeCard {
                if (!p.isInJail) {
                    move(p, rollDice());
                    playerAction(p);
                } else {
                    p.isInJail = false;
                }
            }
        }
    }

    /**
     * Rolls two 6-sided dice according to Monopoly rules
     * @return sum of rolls or 0 if caught cheating, indicating player should go to jail
     */
    //Tests Passed
    public static int rollDice() {
        Random randGen = new Random();
        int sum = 0;
        int rolls = 0;
        boolean doubles;

        do {
            doubles = false;
            int die1 = randGen.nextInt(6) + 1;
            int die2 = randGen.nextInt(6) + 1;
            sum += die1 + die2;
            rolls++;
            if(die1 == die2) {
                doubles = true;
            }
        } while (doubles && (rolls < 3));
        if(rolls == 3) {
            return 0;
        }
        return sum;
    }

    /**
     * Moves player's current location on board based on dice roll
     * @param p
     * @param spaces
     * @return int new location on board
     */
    //TODO: test logic
    public int move(Player p, int spaces) {
        if (spaces != 0) {
            p.setCurrentSpace(p.getCurrentSpace() + spaces);
        } else {
            p.isInJail = true;  //if cheating on rolling dice, go to jail
            p.currentSpace = 10;
                //TODO: get int value corresponding to key "Jail" after reversing HashMap
                //consider making 0: Go, 10: Just Visiting [Jail] and 11: Jail
        }
        return p.currentSpace;
    }

    /**
     * calls Spaces' action()
     * @param p
     */
    public void playerAction(Player p) {
        board.getSpaces().get(p.currentSpace).action(p);
    }

    /**
     * If all but one player is bankrupt, the game has a winner
     * @return
     */
    //TODO: test logic
    public boolean hasWinner() {
        int numPlaying = 0;

        for(Player p : this.players) {
            if(!p.bankrupt) {
                numPlaying++;
            }
        }
        return ((numPlaying == 1) && (this.players.size() > 1));
    }

    /**
     * If the game has a winner, finds and returns the winner of the game
     * @return address of Player who has won the game
     */
    //TODO: test logic
    public Player winner() {
        if(hasWinner()) {
            for(Player p : this.players) {
                if(!p.bankrupt) {
                    return p;
                }
            }
        }
        return null;
    }
}
