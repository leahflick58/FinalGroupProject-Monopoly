package MonopolyGUI;

import java.util.Scanner;

public class GoToJailGUI extends SpacesGUI {
    public GoToJailGUI(int xCoord, int yCoord, String labelString, int rotationDegrees) {
        super(xCoord, yCoord, labelString, rotationDegrees);
    }

    /**
     * GoToJailGUI action - Sends active PlayerGUI to JailGUI space (10) with 3 turns in jail and sets boolean isInJail to true.
     * @param p active PlayerGUI
     */
    @Override
    void action(PlayerGUI p) {
        //You may buy and erect houses and/or hotels, sell or buy property, collect rent,
        //and deal with other players even though in JailGUI.

        if(p.getNumGetOutOfJail() > 0) {
            System.out.println("Play 'Get Out of JailGUI' card? Enter Y/N: ");
            Scanner in = new Scanner(System.in);
            if (in.next().equals("Y")) {
                p.setNumGetOutOfJail(-1);    //removes 1 GetOutOfJail card
                if (p.getNumGetOutOfJail() == 1) {
                    System.out.println("You have 1 Get Out Of JailGUI card left.");
                } else {
                    System.out.println("You have " + p.numGetOutOfJail + " Get Out Of JailGUI cards left.");
                }
            }
        } else {
            p.setCurrentSpace(10);
            p.isInJail = true;
            p.setTurnsInJail(3);
        }
    }

    @Override
    String spaceName() {
        return "GoGUI To JailGUI";
    }
}
