import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Monopoly extends JFrame {
    private GameLoop loop;
    private Player winner;
    JPanel contentIncluder;

    /**
     * Creates a GameLoop for the Monopoly game and gets the winner
     * @param players as an ArrayList of String names
     */
    public Monopoly(ArrayList<String> players) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(1080,1000);
        contentIncluder = new JPanel();
        contentIncluder.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentIncluder);
        contentIncluder.setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBorder(new LineBorder(new Color(0, 0,0)));
        layeredPane.setBounds(6,6,632,630);
        contentIncluder.add(layeredPane);

        //board
        //player1...player6

        //Control Panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBorder(new LineBorder(Color.BLACK));
        rightPanel.setBounds(640,6,419,630);    //AM adjusted dimensions
        contentIncluder.add(rightPanel);

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
        Monopoly myGame = new Monopoly(new ArrayList<>(List.of("Alexis","David")));
        myGame.loop.gameLoop();
        myGame.setVisible(true);
        System.out.println(myGame.winner.name + "won!");
    }


}
