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
    private JPanel contentIncluder;
    private JLayeredPane layeredPane;
    private JPanel rightPanel;
    private JButton btnBuy;
    private JButton btnPayRent;
    private JButton btnRollDice;
    private JButton btnNextTurn;
    private static JTextArea infoConsole;
    private JPanel playerAssetsPanel;
    private CardLayout c1 = new CardLayout();
    private JTextArea panelPlayer1TextArea;
//    private JTextArea panelPlayer2TextArea;

    /**
     * Constructs the Monopoly GUI window, adapted from [PUT GITHUB REPOSITORY HERE]
     * Creates a GameLoop for the Monopoly game and gets the winner
     * @param players as an ArrayList of String names
     */
    public Monopoly(ArrayList<String> players) {
//        //@author
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 450, 300);
//        setSize(1080,700);
//        contentIncluder = new JPanel();
//        contentIncluder.setBorder(new EmptyBorder(5, 5, 5, 5));
//        setContentPane(contentIncluder);
//        contentIncluder.setLayout(null);
//
//        //@author
//        layeredPane = new JLayeredPane();
//        layeredPane.setBorder(new LineBorder(new Color(0, 0,0)));
//        layeredPane.setBounds(6,6,632,630);
//        contentIncluder.add(layeredPane);
//
//        //Control Panel
//        //@author
//        rightPanel = new JPanel();
//        rightPanel.setBackground(Color.LIGHT_GRAY);
//        rightPanel.setBorder(new LineBorder(new Color(0,0,0)));
//        rightPanel.setBounds(640,6,419,630);    //AM adjusted dimensions
//        contentIncluder.add(rightPanel);
//        rightPanel.setLayout(null);
//
//        btnBuy = new JButton("Buy");
//        btnBuy.setBounds(81,478,117,29);
//        rightPanel.add(btnBuy);
//        btnBuy.setEnabled(false);
//
//        btnPayRent = new JButton("Pay Rent");
//        btnPayRent.setBounds(210, 478, 117, 29);
//        rightPanel.add(btnPayRent);
//        btnPayRent.setEnabled(false);
//
//        btnRollDice = new JButton("Roll Dice");
//        btnRollDice.setBounds(81, 413, 246, 53);
//        rightPanel.add(btnRollDice);
//        btnRollDice.setEnabled(false);
//
//        btnNextTurn = new JButton("Next Turn");
//        btnNextTurn.setBounds(81, 519, 246, 53);
//        rightPanel.add(btnNextTurn);
//        btnNextTurn.setEnabled(false);
//
//        JPanel test = new JPanel();
//        test.setBounds(81, 312, 246, 68);
//        rightPanel.add(test);
//        test.setLayout(null);
//
//        infoConsole = new JTextArea();
//        infoConsole.setColumns(20);
//        infoConsole.setRows(5);
//        infoConsole.setBounds(6, 6, 234, 56);
//        test.add(infoConsole);
//        infoConsole.setLineWrap(true);
//        infoConsole.setText("PLayer 1 starts the game by clicking Roll Dice!");
//
//        playerAssetsPanel = new JPanel();
//        playerAssetsPanel.setBounds(81, 28, 246, 189);
//        rightPanel.add(playerAssetsPanel);
//        playerAssetsPanel.setLayout(c1);
//
//        //Player 1
//        //will change to for each player in loop:GameLoop
//        JPanel panelPlayer1 = new JPanel();
//        panelPlayer1.setBackground(Color.RED);
//        playerAssetsPanel.add(panelPlayer1, "1");
//        panelPlayer1.setLayout(null);
//
//        JLabel panelPlayer1Title = new JLabel("Player 1 Assets");
//        panelPlayer1Title.setForeground(Color.WHITE);
//        panelPlayer1Title.setHorizontalAlignment(SwingConstants.CENTER);
//        panelPlayer1Title.setBounds(0, 6, 240, 16);
//        panelPlayer1.add(panelPlayer1Title);
//
//        panelPlayer1TextArea = new JTextArea();
//        panelPlayer1TextArea.setBounds(10, 34, 230, 149);
//        panelPlayer1TextArea.setText("Put text here");
//        panelPlayer1.add(panelPlayer1TextArea);
//
//
//
//
//
//        setVisible(true);

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
        System.out.println(myGame.winner.name + "won!");
    }


}
