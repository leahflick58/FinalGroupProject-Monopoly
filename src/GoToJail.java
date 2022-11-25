import java.util.Random;
import java.util.Scanner;

public class GoToJail extends Spaces {
    /**
     * GoToJail action - send Player to jail space and set Player isInJail boolean to True
     * @param p active Player
     */
    //TODO: check method
    @Override
    void action(Player p) {
        //You may buy and erect houses and/or hotels, sell or buy property, collect rent, mortgage properties,
        // participate in auctions and deal with other players even though in Jail.

        p.setCurrentSpace(10);
        p.isInJail = true;
        p.setTurnsInJail(3);
    }

    @Override
    String getName() {
        return "Go To Jail";
    }
}
