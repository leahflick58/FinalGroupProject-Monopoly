package MonopolyGUI;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Monopoly extends JFrame {
    private GameLoop loop;
    private Player winner;

    /**
     * Constructs the Monopoly GUI window, adapted from [PUT GITHUB REPOSITORY HERE]
     * Creates a GameLoop for the Monopoly game and gets the winner
     * @param players as an ArrayList of String names
     */
    public Monopoly(ArrayList<String> players) {
        loop = new GameLoop(players);
        winner = loop.gameLoop();
    }

    public static void main(String[] args) {
        Monopoly myGame = new Monopoly(new ArrayList<>(List.of("Alexis","David")));
        myGame.setVisible(true);
        myGame.loop.gameLoop();
        System.out.println(myGame.winner.name + "won!");
    }


}
