import java.util.ArrayList;
import java.util.List;

public class Monopoly {
    private Board board;
    private GameLoop loop;

    public Monopoly(ArrayList<String> players) {
        board = new Board();        //ArrayLists <Spaces>,<Chance>, <CommunityChest>
                                    //Board constructor should not take any args
        loop = new GameLoop(players);      //take player names
    }

    public static void main(String[] args) {
        Monopoly myGame = new Monopoly(new ArrayList<>(List.of("James","Mary","Joe")));
        //TODO: return winner
    }

}
