import java.util.ArrayList;
import java.util.List;

public class Monopoly {
    private GameLoop loop;

    public Monopoly(ArrayList<String> players) {
        loop = new GameLoop(players);      //take player names
    }

    public static void main(String[] args) {
        Monopoly myGame = new Monopoly(new ArrayList<>(List.of("James","Mary","Joe")));
        //TODO: return winner
    }

}
