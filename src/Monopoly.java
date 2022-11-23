import java.util.ArrayList;
import java.util.List;


public class Monopoly {
    private GameLoop loop;
    private Player winner;

    public Monopoly(ArrayList<String> players) {
        loop = new GameLoop(players);      //take player names
        winner = loop.gameLoop();
    }
    public Player getWinner() {
        return winner;
    }

    public static void main(String[] args) {
        Monopoly myGame = new Monopoly(new ArrayList<>(List.of("James","Mary","Joe")));
        myGame.loop.gameLoop();
        System.out.println(myGame.winner.name + "won!");
    }


}
