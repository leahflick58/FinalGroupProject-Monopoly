import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameLoop {
    public static ArrayList<Player> players;
    private Board board;

    /**
     * GameLoop constructor: Initializes a new Monopoly board and takes an ArrayList of player names and creates a new
     * ArrayList of Player objects.
     * @param names of the players in the game
     */
    public GameLoop(ArrayList<String> names) {
        board = new Board();        //ArrayLists <Spaces>,<Chance>, <CommunityChest>
        players = new ArrayList<>();
        for (String p : names) {
            players.add(new Player(p));
        }
    }

    /**
     * Iterates through multiple rounds of Monopoly until all but one player (winner) is bankrupt
     * @return Player that has won the game
     */
    public Player gameLoop() {
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
            if (!p.isBankrupt()) {
                System.out.println(p.name + "'s Balance: $" + p.bankBalance);
                                        //if p.isinJail && p.usesJailFreeCard {
                if (!p.isInJail) {
                    move(p, rollDice());
                    if(p.isInJail) {
                        System.out.println(p.name + " is in jail");
                    } else {
                        System.out.println("Landed on " + getSpace(p.getCurrentSpace()) + " (" + p.getCurrentSpace() +
                                ")");
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

    /**
     * Gets the name of the space on the board given its integer location
     * @param currentSpace
     * @return String name of space
     */
    public String getSpace(int currentSpace) {
        return board.getSpaces().get(currentSpace).getName();
    }

    /**
     * Rolls two 6-sided dice according to Monopoly rules:
     * If the first roll yields doubles (same number of digits on each die), player can roll dice again.
     * If the second roll yields doubles, player can roll dice a third time.
     * If the third roll yields doubles, player is considered to be cheating and must go to jail.
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
     * Moves player's current location on board based on dice roll.
     * @param p
     * @param spaces
     * @return int new location on board
     */
    public void move(Player p, int spaces) {
        if (spaces != 0) {
            int newSpace = p.getCurrentSpace() + spaces;
            if(newSpace > 39) {
                p.addOrSubBankBalance(200);
                System.out.println("You earned $200 for passing Go");
            }
            p.setCurrentSpace(newSpace % 40);
        } else {
            System.out.println("Caught cheating on rolling dice");
            p.isInJail = true;  //if cheating on rolling dice, go to jail
            p.setTurnsInJail(3);
            p.currentSpace = 10;
        }
    }

    /**
     * Calls Spaces' action()
     * @param p
     */
    public void playerAction(Player p) {
        board.getSpaces().get(p.currentSpace).action(p);
    }

    /**
     * If all but one player is bankrupt, the game has a winner.
     * @return true if game has a winner
     */
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
     * If the game has a winner, finds and returns the winner of the game.
     * @return address of Player who has won the game
     */
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
