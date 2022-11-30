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
     * Plays a single round of Monopoly.
     * Rolls two 6-sided dice according to Monopoly rules:
     * If the first roll yields doubles (same number of digits on each die), player can roll dice again.
     * If the second roll yields doubles, player can roll dice a third time.
     * If the third roll yields doubles, player is considered to be cheating and must go to jail.
     */
    public void play() {
        for(Player p : players) {
            if (!p.isBankrupt()) {
                System.out.println("\n" + p.name + "'s Balance: $" + p.bankBalance);
                                        //if p.isinJail && p.usesJailFreeCard {
                if (!p.isInJail) {
                    int rolls = 0;
                    boolean doubles;
                    boolean toJail;

                    do {
                        doubles = false;
                        toJail = false;
                        int die1 = rollDie();
                        int die2 = rollDie();

                        System.out.println("You rolled a " + die1 + " and a " + die2);
                        rolls++;

                        if(die1 == die2) {
                            doubles = true;
                        }
                        if((rolls == 3) && doubles) {   //caught cheating
                            move(p, 0);
                            System.out.println(p.name + " is in jail");
                            playerAction(p);
                            if(p.currentSpace == 30) {
                                toJail = true;
                                System.out.println(p.name + "'s New Balance: $" + p.bankBalance);
                            }
                        } else {
                            move(p, die1 + die2);
                            System.out.println("Landed on " + getSpace(p.getCurrentSpace()) + " (" + p.getCurrentSpace() +
                                    ")");
                            playerAction(p);
                            if(doubles) {
                                System.out.println("You rolled doubles. Roll dice again!");
                            }
                        }
                    } while((rolls <= 3) && doubles && !p.isInJail && !toJail);
                    System.out.println(p.name + "'s New Balance: $" + p.bankBalance);

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
     * Rolls a 6-sided die
     * @return result of the roll
     */
    public static int rollDie() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
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
            p.currentSpace = 30;
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
