package MonopolyGUI;

import java.util.Random;
import java.util.Scanner;

public class Jail extends Spaces{
    public Jail(int xCoord, int yCoord, String labelString, int rotationDegrees) {
        super(xCoord, yCoord, labelString, rotationDegrees);
    }

    /**
     * Jail Action
     * Check if a Player is currently in jail, if not space acts as "Just Visiting" and nothing happens
     * If the player is in jail, they have three options to escape jail:
     * 1. Play get out of jail free card
     * 2. Pay bank $50
     * 3. Roll doubles
     * After three consecutive rounds in jail, if the Player has not escaped jail, they must pay the fine and isInJail
     * is set to false
     * @param p active Player
     */
    @Override
    void action(Player p) {
        //TODO: Clean up and final testing: enough turns in jail?

        if(!p.isInJail) {
            System.out.println("Just Visiting");
        } else {
            //Option 1: Use Get Out of Jail Free card
            if (p.numGetOutOfJail > 0) {
                System.out.println("Play 'Get Out of Jail' card? Enter Y/N: ");
                Scanner in = new Scanner(System.in);
                if (in.next().equals("Y")) {
                    p.setNumGetOutOfJail(-1);    //removes 1 GetOutOfJail card
                    if(p.getNumGetOutOfJail() == 1) {
                        System.out.println("You have 1 Get Out Of Jail card left.");
                    } else {
                        System.out.println("You have " + p.numGetOutOfJail + " Get Out Of Jail cards left.");
                    }
                    p.isInJail = false;
                    p.resetTurnsInJail();
                }
            }
            if(p.isInJail) {

                //Option 2: Pay $50 fine to bank
                System.out.println("Pay $50 fine to bank? Enter Y/N:");
                Scanner in = new Scanner(System.in);
                if (in.next().equals("Y")) {
                    p.addOrSubBankBalance(-50);
                    p.isInJail = false;
                    p.resetTurnsInJail();
                } else {

                    //Option 3: Roll doubles
                    System.out.println("If you roll doubles, you can get out of jail early. Rolling dice...");
                    if (rolledDoubles()) {
                        System.out.println("You rolled doubles and are free to go on your next turn");
                        p.isInJail = false;
                        p.resetTurnsInJail();
                    } else {
                        p.setTurnsInJail(p.turnsInJail - 1);
                        if (p.getTurnsInJail() == 0) {
                            p.addOrSubBankBalance(-50);
                            p.isInJail = false;
                            System.out.println("You did not roll doubles. You have 0 turns left in jail and must pay " +
                                    "the $50 fine.");
                            System.out.println(p.name + "'s New Balance: " + p.bankBalance + "\n");
                        } else {
                            if(p.getTurnsInJail() == 1) {
                                System.out.println("You did not roll doubles. You have 1 turn left in jail.");
                            } else {
                                System.out.println("You did not roll doubles. You have " + p.getTurnsInJail() + " turns left in " +
                                        "jail.");
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    String spaceName() {
        return "Jail";
    }

    /**
     * Rolls two dice once and returns true if both dice land on the same number of digits
     * @return doubles
     */
    public static boolean rolledDoubles() {
        boolean doubles = false;
        Random rand = new Random();

        int die1 = rand.nextInt(6) + 1;
        int die2 = rand.nextInt(6) + 1;
        System.out.println("You rolled a " + die1 + " and a " + die2);

        if(die1 == die2) {
            doubles = true;
        }
        return doubles;
    }

}


