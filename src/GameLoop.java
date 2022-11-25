import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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

        players = new ArrayList<>();
        for (String p : names) {
            players.add(new Player(p));
        }
        System.out.println("Created game loop");
    }

    /**
     * Iterates through multiple rounds of Monopoly until all but one player is bankrupt (winner)
     * @return Player that has won the game
     */
    //TODO: test logic
    public Player gameLoop() {
        //plays one round; winner has highest bankBalance
//        play();
//
//        Player winner = players.get(0);
//        for(Player p : players) {
//            if(p.bankBalance > winner.bankBalance) {
//                winner = p;
//            }
//        }

        //iterate through rounds while no winner

        while(!hasWinner()) {
            play();
        }
        return winner();
//        return winner;
    }

    /**
     * Plays a single Monopoly round
     */
    public void play() {
        for(Player p : players) {
            if (!p.isBankrupt()) {
                System.out.println(p.name + "'s Balance: $" + p.bankBalance);
                                        //if p.isinJail && p.usesJailFreeCard {
                if (!p.isInJail) {
                    move(p, rollDice());
                    if(p.isInJail) {
                        System.out.println(p.name + " is in jail");
                    } else {
                        System.out.println("Landed on " + getSpace(p.getCurrentSpace()) + " (" + p.getCurrentSpace() + ")");
                    }
                    playerAction(p);
                    System.out.println(p.name + "'s New Balance: $" + p.bankBalance + "\n");
                } else {
                    if(p.getTurnsInJail() > 0) {
                        System.out.println(p.name + " is in jail");
                        playerAction(p);    //jail action
                    }
                }
            }
            else System.out.println(p.name + " is bankrupt");
        }
    }

    public String getSpace(int currentSpace) {
        return board.getSpaces().get(currentSpace).getName();
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
            p.setCurrentSpace((p.getCurrentSpace() + spaces) % 40);
            //TODO: +$200 passing Go
        } else {
            System.out.println("Caught cheating on rolling dice");
            p.isInJail = true;  //if cheating on rolling dice, go to jail
            p.currentSpace = 10;
                //TODO: get int value corresponding to key "Jail" after reversing HashMap
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
            if(!p.isBankrupt()) {
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
