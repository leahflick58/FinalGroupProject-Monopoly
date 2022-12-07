package MonopolyGUI;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class MonopolyGUI extends JFrame {
    private GameLoopGUI loop;
    private PlayerGUI winner;

    /**
     * Constructs the MonopolyGUI GUI window, adapted from [PUT GITHUB REPOSITORY HERE]
     * Creates a GameLoop for the MonopolyGUI game and gets the winner
     * @param players as an ArrayList of String names
     */
    public MonopolyGUI(ArrayList<String> players) {
        loop = new GameLoopGUI(players);
        winner = loop.gameLoop();
    }

    public static void main(String[] args) {
        MonopolyGUI myGame = new MonopolyGUI(new ArrayList<>(List.of("Alexis","David")));
        myGame.setVisible(true);
        myGame.loop.gameLoop();
        System.out.println(myGame.winner.name + "won!");
    }


}
