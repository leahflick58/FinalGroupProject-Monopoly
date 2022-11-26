import java.util.ArrayList;
import java.util.List;


public class Monopoly {
    private GameLoop loop;
    private Player winner;

    /**
     * Creates a GameLoop for the Monopoly game and gets the winner
     * @param players as an ArrayList of String names
     */
    public Monopoly(ArrayList<String> players) {
        //TODO: create shuffle methods for Community and Chance
        loop = new GameLoop(players);
        winner = loop.gameLoop();
    }

    /**
     * Gets the winner of the Monopoly game
     * @return Player winner
     */
    public Player getWinner() {
        return winner;
    }

    public static void main(String[] args) {
        Monopoly myGame = new Monopoly(new ArrayList<>(List.of("James","Mary","Joe")));
        myGame.loop.gameLoop();
        System.out.println(myGame.winner.name + "won!");
    }


}
