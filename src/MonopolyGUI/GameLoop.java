package MonopolyGUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class GameLoop extends JFrame {
    private JPanel contentIncluder;
    private JLayeredPane layeredPane;
    private JPanel rightPanel;
    private static JTextArea infoConsole;
    private JUserInput input;
    private JPanel playerAssetsPanel;
    private JPanel playerPanel;
    private JLabel playerTitle;
    private CardLayout c1 = new CardLayout();
    public static ArrayList<Player> players;
    private Board board = new Board();

    /**
     * GameLoop constructor: Initializes a new Monopoly board and takes an ArrayList of player names and creates a new
     * ArrayList of Player objects.
     * @param names of the players in the game
     */
    public GameLoop(ArrayList<String> names) {
        //@author
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setSize(1080,1080);
        contentIncluder = new JPanel();
        contentIncluder.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentIncluder);
        contentIncluder.setLayout(null);

        //@author
        layeredPane = new JLayeredPane();
        layeredPane.setBorder(new LineBorder(new Color(0, 0,0)));
        layeredPane.setBounds(6,6,632,630);
        layeredPane.add(board, new Integer(0));
        contentIncluder.add(layeredPane);

        //CONTROL PANEL (GRAY)
        //@author
        rightPanel = new JPanel();
        rightPanel.setBackground(Color.LIGHT_GRAY);
        rightPanel.setBorder(new LineBorder(new Color(0,0,0)));
        rightPanel.setBounds(640,6,419,630);    //AM adjusted dimensions
        contentIncluder.add(rightPanel);
        rightPanel.setLayout(null);

        //PLAYER ASSETS
        //@author
        playerAssetsPanel = new JPanel();
        playerAssetsPanel.setBounds(81, 58, 246, 189);
        rightPanel.add(playerAssetsPanel);
        playerAssetsPanel.setLayout(c1);

        //@author
        playerPanel = new JPanel();
        playerPanel.setBackground(new Color(140, 185, 160));
        playerAssetsPanel.add(playerPanel);
        playerPanel.setLayout(null);

        //INFORMATION CONSOLE
        //@author
        JPanel info = new JPanel();
        info.setBounds(81, 312, 246, 258);
        info.setBackground(new Color(140, 185, 160));
        rightPanel.add(info);
        info.setLayout(null);

        JLabel infoLabel = new JLabel("Information Console");
        infoLabel.setForeground(Color.WHITE);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setBounds(0, 6, 240, 16);
        info.add(infoLabel);

        //@author
        infoConsole = new JTextArea();
        infoConsole.setColumns(20);
        infoConsole.setRows(5);
        infoConsole.setBounds(6, 35, 234, 210);
        infoConsole.setLineWrap(true);
        infoConsole.setEditable(false);
        info.add(infoConsole);

        //USER INPUT CONSOLE
//        input = new JUserInput(rightPanel);

        setVisible(true);

        /////////////////////////////

        board = new Board();            //ArrayLists <Spaces>,<Chance>, <CommunityChest>
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

            playerPanel.removeAll();
            //adapted from []
            playerTitle = new JLabel(p.name + "'s Assets");
            playerTitle.setForeground(Color.WHITE);
            playerTitle.setHorizontalAlignment(SwingConstants.CENTER);
            playerTitle.setBounds(0, 6, 240, 16);
            playerPanel.add(playerTitle);


            //adapted from []
            JTextArea panelPlayerTextArea = new JTextArea(15,20);
            panelPlayerTextArea.setBounds(10, 34, 230, 149);
            panelPlayerTextArea.setEditable(false);
            panelPlayerTextArea.setLineWrap(true);
            panelPlayerTextArea.setText("Balance: $" + p.bankBalance +
                    "\nGet Out of Jail Free cards: " + p.getNumGetOutOfJail());
            if(p.properties.size() > 0) {
                panelPlayerTextArea.append("\nProperties:");
                for (Property property : p.properties) {
                    panelPlayerTextArea.append("\n" + property.getName());
                }
            }
            playerPanel.add(panelPlayerTextArea);
            playerPanel.repaint();
            setVisible(true);

            //game logic
            if (!p.isBankrupt()) {
                infoConsole.setText(p.name + "'s Balance: $" + p.bankBalance);
                if(!p.isInJail) {
                    int rolls = 0;
                    boolean doubles;
                    boolean toJail;

                    do {
                        doubles = false;
                        toJail = false;
                        int die1 = rollDie();
                        int die2 = rollDie();

                        infoConsole.append("\nYou rolled a " + die1 + " and a " + die2);
                        rolls++;

                        if(die1 == die2) {
                            doubles = true;
                        }
                        if((rolls == 3) && doubles) {   //caught cheating
                            move(p, 0);
                            infoConsole.append("\n" + p.name + " is in jail.");
                            playerAction(p);
                            if (p.currentSpace == 30) {
                                toJail = true;
                                infoConsole.append("\n" + p.name + "'s New Balance: $" + p.bankBalance);
                            }
                        } else {
                                move(p, die1 + die2);
                                infoConsole.append("\nLanded on " + getSpace(p.getCurrentSpace()) + " (" + p.getCurrentSpace() +
                                        ")");
                                playerAction(p);
                                if(doubles) {
                                    infoConsole.append("\nYou rolled doubles. Roll dice again!");
                                }
                        }
                    } while ((rolls <= 3) && doubles && !p.isInJail && !toJail);
                    infoConsole.append("\n" + p.name + "'s New Balance: $" + p.bankBalance);
                } else {
                    if(p.getTurnsInJail() > 0) {
                        infoConsole.append("\n" + p.name + " is in jail");
                        playerAction(p);    //jail action
                    }
                }
                if(p.properties.size() > 0) {   //will not iterate through color groups if player owns 0 properties
                    checkColorGroupsForUpgrade(p);
                }
            } else infoConsole.append("\n" + p.name + " is bankrupt");
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
