import java.util.Random;
import java.util.Scanner;

public class GoToJail extends Spaces {
    /**
     * GoToJail action - Sends active Player to Jail space (10) with 3 turns in jail and sets boolean isInJail to true.
     * @param p active Player
     */
    @Override
    void action(Player p) {
        //TODO: modify according to the following rules:
        //You may buy and erect houses and/or hotels, sell or buy property, collect rent, mortgage properties,
        // participate in auctions and deal with other players even though in Jail.

        if(p.getNumGetOutOfJail() > 0) {
            System.out.println("Play 'Get Out of Jail' card? Enter Y/N: ");
            Scanner in = new Scanner(System.in);
            if (in.next().equals("Y")) {
                p.setNumGetOutOfJail(-1);    //removes 1 GetOutOfJail card
                if (p.getNumGetOutOfJail() == 1) {
                    System.out.println("You have 1 Get Out Of Jail card left.");
                } else {
                    System.out.println("You have " + p.numGetOutOfJail + " Get Out Of Jail cards left.");
                }
            }
        } else {
            p.setCurrentSpace(10);
            p.isInJail = true;
            p.setTurnsInJail(3);
        }
    }

    @Override
    String getName() {
        return "Go To Jail";
    }
}
