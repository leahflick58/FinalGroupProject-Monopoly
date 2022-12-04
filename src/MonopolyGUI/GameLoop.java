package MonopolyGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class GameLoop extends JFrame {
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
    public static ArrayList<Player> players;
    private Board board;

    /**
     * GameLoop constructor: Initializes a new Monopoly board and takes an ArrayList of player names and creates a new
     * ArrayList of Player objects.
     * @param names of the players in the game
     */
    public GameLoop(ArrayList<String> names) {
        //@author
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(1080,700);
        contentIncluder = new JPanel();
        contentIncluder.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentIncluder);
        contentIncluder.setLayout(null);

        //@author
        layeredPane = new JLayeredPane();
        layeredPane.setBorder(new LineBorder(new Color(0, 0,0)));
        layeredPane.setBounds(6,6,632,630);
        contentIncluder.add(layeredPane);

        //Control Panel
        //@author
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBorder(new LineBorder(new Color(0,0,0)));
        rightPanel.setBounds(640,6,419,630);    //AM adjusted dimensions
        contentIncluder.add(rightPanel);
        rightPanel.setLayout(null);

        btnBuy = new JButton("Buy");
        btnBuy.setBounds(81,478,117,29);
        rightPanel.add(btnBuy);
        btnBuy.setEnabled(false);

        btnPayRent = new JButton("Pay Rent");
        btnPayRent.setBounds(210, 478, 117, 29);
        rightPanel.add(btnPayRent);
        btnPayRent.setEnabled(false);

        btnRollDice = new JButton("Roll Dice");
        btnRollDice.setBounds(81, 413, 246, 53);
        rightPanel.add(btnRollDice);
        btnRollDice.setEnabled(false);

        btnNextTurn = new JButton("Next Turn");
        btnNextTurn.setBounds(81, 519, 246, 53);
        rightPanel.add(btnNextTurn);
        btnNextTurn.setEnabled(false);

        JPanel test = new JPanel();
        test.setBounds(81, 312, 246, 68);
        rightPanel.add(test);
        test.setLayout(null);

        infoConsole = new JTextArea();
        infoConsole.setColumns(20);
        infoConsole.setRows(5);
        infoConsole.setBounds(6, 6, 234, 56);
        test.add(infoConsole);
        infoConsole.setLineWrap(true);
        infoConsole.setText("PLayer 1 starts the game by clicking Roll Dice!");

//        playerAssetsPanel = new JPanel();
//        playerAssetsPanel.setBounds(81, 28, 246, 189);
//        rightPanel.add(playerAssetsPanel);
//        playerAssetsPanel.setLayout(c1);


        setVisible(true);

        ////////////////////////////
        board = new Board();        //ArrayLists <Spaces>,<Chance>, <CommunityChest>
        players = new ArrayList<>();
        for (String p : names) {
            players.add(new Player(p));
        }
    }

    /**
     * Iterates through multiple rounds of Monopoly until all but one player (winner) is bankrupt
     * @return Player that has won the game
     */
    public Player gameLoop() {
        while(!hasWinner()) {
            play();
        }
        return winner();
    }

    /**
     * Plays a single round of Monopoly.
     * Rolls two 6-sided dice according to Monopoly rules:
     * If the first roll yields doubles (same number of digits on each die), player can roll dice again.
     * If the second roll yields doubles, player can roll dice a third time.
     * If the third roll yields doubles, player is considered to be cheating and must go to jail.
     */
    public void play() {
        for(Player p : players) {
            System.out.println("\n" + p.name + "'s Balance: $" + p.bankBalance);

            //adapted from []
            playerAssetsPanel = new JPanel();
            playerAssetsPanel.setBounds(81, 28, 246, 189);
            rightPanel.add(playerAssetsPanel);
            playerAssetsPanel.setLayout(c1);

            JPanel playerPanel = new JPanel();
            playerPanel.setBackground(new Color(0,190,190));
            playerAssetsPanel.add(playerPanel, "0");
            playerPanel.setLayout(null);

            JLabel playerTitle = new JLabel(p.name + "'s Assets");
            playerTitle.setForeground(Color.WHITE);
            playerTitle.setHorizontalAlignment(SwingConstants.CENTER);
            playerTitle.setBounds(0, 6, 240, 16);
            playerPanel.add(playerTitle);

            panelPlayer1TextArea = new JTextArea();
            panelPlayer1TextArea.setBounds(10, 34, 230, 149);
            panelPlayer1TextArea.setText("Balance: $" + p.bankBalance);
            playerPanel.add(panelPlayer1TextArea);

            setVisible(true);

            if (!p.isBankrupt()) {

                if (!p.isInJail) {
                    int rolls = 0;
                    boolean doubles;
                    boolean toJail;

                    do {
                        doubles = false;
                        toJail = false;
                        int die1 = rollDie();
                        int die2 = rollDie();

                        System.out.println("You rolled a " + die1 + " and a " + die2);
                        rolls++;

                        if(die1 == die2) {
                            doubles = true;
                        }
                        if((rolls == 3) && doubles) {   //caught cheating
                            move(p, 0);
                            System.out.println(p.name + " is in jail");
                            playerAction(p);
                            if(p.currentSpace == 30) {
                                toJail = true;
                                System.out.println(p.name + "'s New Balance: $" + p.bankBalance);
                            }
                        } else {
                            move(p, die1 + die2);
                            System.out.println("Landed on " + getSpace(p.getCurrentSpace()) + " (" + p.getCurrentSpace() +
                                    ")");
                            playerAction(p);
                            if(doubles) {
                                System.out.println("You rolled doubles. Roll dice again!");
                            }
                        }
                    } while((rolls <= 3) && doubles && !p.isInJail && !toJail);
                    System.out.println(p.name + "'s New Balance: $" + p.bankBalance);

                } else {
                    if(p.getTurnsInJail() > 0) {
                        System.out.println(p.name + " is in jail");
                        playerAction(p);    //jail action
                    }
                }

                //You may upgrade to hotels, collect rent, and deal with other players even though in Jail.

                //Upgrade to hotel:
                if(p.properties.size() > 0) {   //will not iterate through color groups if player owns 0 properties
                    checkColorGroupsForUpgrade(p);
                }

            }
            else System.out.println(p.name + " is bankrupt");
        }
    }

    /**
     * Gets the name of the space on the board given its integer location
     * @param currentSpace
     * @return String name of space
     */
    public String getSpace(int currentSpace) {
        return board.getSpaces().get(currentSpace).spaceName();
    }

    /**
     * Rolls a 6-sided die
     * @return result of the roll
     */
    public static int rollDie() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    /**
     * Moves player's current location on board based on dice roll.
     * @param p
     * @param spaces
     * @return int new location on board
     */
    public void move(Player p, int spaces) {
        if (spaces != 0) {
            int newSpace = p.getCurrentSpace() + spaces;
            if(newSpace > 39) {
                p.addOrSubBankBalance(200);
                System.out.println("You earned $200 for passing Go");
            }
            p.setCurrentSpace(newSpace % 40);
        } else {
            System.out.println("Caught cheating on rolling dice");
            p.currentSpace = 30;
        }
    }

    /**
     * Calls Spaces' action()
     * @param p
     */
    public void playerAction(Player p) {
        board.getSpaces().get(p.currentSpace).action(p);
    }

    /**
     * If all but one player is bankrupt, the game has a winner.
     * @return true if game has a winner
     */
    public boolean hasWinner() {
        int numPlaying = 0;

        for(Player p : this.players) {
            if(!p.isBankrupt()) {
                numPlaying++;
            }
        }
        return ((numPlaying == 1) && (this.players.size() > 1));
    }

    /**
     * If the game has a winner, finds and returns the winner of the game.
     * @return address of Player who has won the game
     */
    public Player winner() {
        if(hasWinner()) {
            for(Player p : this.players) {
                if(!p.bankrupt) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Checks all color groups in Board for active player ownership.
     * If active player owns an entire color group, they may upgrade to hotels.
     * @param p
     */
    public void checkColorGroupsForUpgrade(Player p) {
        for(String colorGroup : Board.colorGroups.keySet()) {
            Set<Streets> thisColor = Board.colorGroups.get(colorGroup);
            if (p.hasEntireColorGroup(thisColor)) {
                Scanner in = new Scanner(System.in);
                String decision = "O";
                while (!decision.equals("Y") && !decision.equals("N")) {
                    System.out.println("Do you want to upgrade to hotels? Enter Y/N: ");
                    decision = in.next();
                    if (!decision.equals("Y") && !decision.equals("N")) {
                        System.out.println("Invalid input. Choose Y or N");
                    }
                }
                if (decision.equals("Y")) {
                    for (Streets s : thisColor) {
                        s.upgrade(s);
                    }
                }
            }
        }
    }

}
