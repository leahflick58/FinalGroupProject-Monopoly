import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLoop {

    public ArrayList<Player> players;
    private boolean hasWinner;

    public GameLoop(ArrayList<String> names) {
        hasWinner = false;

        this.players = new ArrayList<>();
        for (String p : names) {
            this.players.add(new Player(p));
        }

        //iterate through rounds while no winner
        while(!hasWinner) {
            System.out.println("TEST");

            //TODO: add winner boolean to play() logic

            hasWinner = true;
//            play();
        }
    }

    //plays a single round
    public void play() {
        for(Player p : players) {
            if (!p.bankrupt) {
                int spaces = rollDice();
                if(spaces == 0) {
                    //p goes to jail and BREAK
                }
                //TODO: handle doubles or triples
                p.setCurrentSpace(p.getCurrentSpace() + spaces);
                int newSpace = p.getCurrentSpace();

                //TODO: identify type of space and act accordingly

//            if(!p.ownsProperty()) {
//
//            } else {
//
//            }
            }

        }
    }

    //TODO: test logic
    public int rollDice() {
        Random randGen = new Random();
        int sum = 0;
        int rolls = 0;
        boolean doubles = false;

        do {
            int die1 = randGen.nextInt(6) + 1;
            int die2 = randGen.nextInt(6) + 1;
            sum += die1 + die2;
            rolls++;
            if(die1 == die2) {
                doubles = true;
            }
        } while (doubles && (rolls < 4));
        if(rolls > 3) {
            return 0;
        }
        return sum;
    }
}
