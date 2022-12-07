package MonopolyGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.List;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class BoardGUI extends JPanel {

    protected static HashMap<Integer, SpacesGUI> spaces;
    protected static HashMap<SpacesGUI, Integer> locations;
    protected static HashMap<String, Set<StreetsGUI>> colorGroups;
    protected static Set<StreetsGUI> brown;
    protected static Set<StreetsGUI> lightBlue;
    protected static Set<StreetsGUI> pink;
    protected static Set<StreetsGUI> orange;
    protected static Set<StreetsGUI> red;
    protected static Set<StreetsGUI> yellow;
    protected static Set<StreetsGUI> green;
    protected static Set<StreetsGUI> darkBlue;
    protected static LinkedList<ChanceGUI> chance;                   //ChanceGUI cards
    protected static LinkedList<CommunityGUI> communityChests;       //CommunityGUI Chest cards

    /**
     * BoardGUI constructor.
     * Because the board will not change during the game, all spaces and cards are initialized
     * in the BoardGUI constructor
     */
    public BoardGUI() {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(6,6, 850, 345);
        this.setLayout(null);
        spaces = new HashMap<>();
        spaces.put(0, new GoGUI(3,3,"Go",135));
        spaces.put(1, new StreetsGUI(78, 3,"Broad St",180,"Broad St", 2, 60, "Brown"));  //10 with house
        spaces.put(2, new CommunitySpaceGUI(153, 3, "Community", 180));
        spaces.put(3, new StreetsGUI(228, 3, "Collage",180, "Collage", 4, 60, "Brown"));   //20
        spaces.put(4, new TaxesGUI(303, 3, "Parking Ticket", 180));
        spaces.put(5, new RailroadsGUI(378, 3, "MEP", 180,"MEP", 25,200));
        spaces.put(6, new StreetsGUI(453, 3, "Center St", 180,"Center St", 6,100,"Light Blue"));   //30
        spaces.put(7, new ChanceSpaceGUI(528, 3, "Chances", 180));
        spaces.put(8, new StreetsGUI(603,3, "PEW", 180, "PEW", 6,100,"Light Blue")); //30 with house
        spaces.put(9, new StreetsGUI(678, 3, "Lincoln Ave", 180, "Lincoln Ave",8,120,"Light Blue"));  //40

        spaces.put(10, new JailGUI(753, 3, "Provost", -135));
        spaces.put(11, new StreetsGUI(753, 33, "SAC", -90, "SAC", 10,140,"Pink")); //50
        spaces.put(12, new UtilitiesGUI(753, 63, "MAP Dining", -90,"MAP Dining", 4,150));
                                    //4x dice roll or 10x if both utilities owned by this player
        spaces.put(13, new StreetsGUI(753, 93, "E Pine St", -90,"E Pine St", 10,140,"Pink")); //50
        spaces.put(14, new StreetsGUI(753, 123, "Thorn Field", -90,"Thorn Field", 12,160,"Pink"));   //60
        spaces.put(15, new RailroadsGUI(753, 153, "Memorial", -90, "Memorial", 25,200));
        spaces.put(16, new StreetsGUI(753,183, "Commuter Lounge", -90,"Commuter Lounge", 14,180,"Orange"));    //70
        spaces.put(17, new CommunitySpaceGUI(753, 213, "Community", -90));
        spaces.put(18, new StreetsGUI(753, 243, "TLC", -90, "TLC", 14,180,"Orange"));   //70
        spaces.put(19, new StreetsGUI(753, 273, "Rathburn", -90, "Rathburn", 16,200,"Orange"));   //80

        spaces.put(20, new ParkingGUI(753, 303, "Parking", -45));
        spaces.put(21, new StreetsGUI(603, 303, "Dunkin", 0, "Dunkin", 18,220,"Red"));  //90
        spaces.put(22, new ChanceSpaceGUI(678, 303, "Chance", 0));
        spaces.put(23, new StreetsGUI(528, 303, "STEM", 0, "STEM", 18,220,"Red"));   //90
        spaces.put(24, new StreetsGUI(453, 303, "Hoyt", 0, "Hoyt", 20,240,"Red"));  //100
        spaces.put(25, new RailroadsGUI(378, 303, "Lincoln", 0, "Lincoln", 25,200));
        spaces.put(26, new StreetsGUI(303, 303, "Beans", 0, "Beans", 22,260,"Yellow"));   //110
        spaces.put(27, new StreetsGUI(228, 303, "Library", 0, "Library", 22,260,"Yellow"));    //110
        spaces.put(28, new UtilitiesGUI(153, 303, "Hicks Dining", 0, "Hicks Dining", 4,150));
                                        //rent is 4x dice 828, 10x if both utilities
        spaces.put(29, new StreetsGUI(78, 303, "E Main St", 0, "E Main St", 24,280,"Yellow")); //120

        spaces.put(30, new GoToJailGUI(3, 303, "Go To Provost", 45));
        spaces.put(31, new StreetsGUI(3, 273, "Guthrie Theater", 90, "Guthrie Theater", 26,300,"Green")); //130
        spaces.put(32, new StreetsGUI(3, 243, "Carnegie", 90, "Carnegie", 26,300,"Green"));  //130
        spaces.put(33, new CommunitySpaceGUI(3, 213, "Community", 90));
        spaces.put(34, new StreetsGUI(3, 183, "Chapel", 90, "Chapel", 28,320,"Green"));    //150
        spaces.put(35, new RailroadsGUI(3, 153, "Memorial", 90, "Memorial", 25,200));
        spaces.put(36, new ChanceSpaceGUI(3, 123, "Chance", 90));
        spaces.put(37, new StreetsGUI(3, 93, "HAL", 90, "HAL", 35,350,"Dark Blue"));    //175
        spaces.put(38, new TaxesGUI(3, 63, "Parking Ticket", 90));
        spaces.put(39, new StreetsGUI(3, 33, "McNulty's House", 90, "McNulty's House", 50,400, "Dark Blue"));   //200
        spaces.forEach((key, value) -> {
               this.add(value);
            });

        //Cards information taken from https://www.monopolyland.com/list-monopoly-chance-community-chest-cards/
        //Deck of CommunityGUI Chest cards
        communityChests = new LinkedList<>();
        communityChests.add(new CommunityGUI("goToJail", 1, "Go to the Provost. Go directly to the Provost, do not" +
                " pass Go, do not collect $200"));
        communityChests.add(new CommunityGUI("advance", 0, "Advance to Go (Collect $200)"));
        communityChests.add(new CommunityGUI("collectBank", 200, "Financial Aid error in your favor. " +
                "Collect $200"));
        communityChests.add(new CommunityGUI("payBank", 50, "Class fee. Pay $50"));
        communityChests.add(new CommunityGUI("collectBank", 50, "From Career Services raffle you get $50."));
        communityChests.add(new CommunityGUI("getOutOfJail", 1, "Get Out of Provost OOffice Free"));

        communityChests.add(new CommunityGUI("collectBank", 100, "Greek Life hosts an event. Receive" +
                " $100"));
        communityChests.add(new CommunityGUI("collectBank", 20, "Book store is buying back textbooks. Collect $20"));
        communityChests.add(new CommunityGUI("collectPlayers", 10,"It is your birthday. Collect $10" +
                " from every player."));
        communityChests.add(new CommunityGUI("collectBank", 100, "School overcharged you for your textbooks. Collect " +
                "$100"));
        communityChests.add(new CommunityGUI("payBank", 100, "Pay textbook fees of $100"));
        communityChests.add(new CommunityGUI("payBank",50, "Pay school fees of $50"));
        communityChests.add(new CommunityGUI("collectBank",25, "Receive $25 school job " +
                "compensation"));
        communityChests.add(new CommunityGUI("homeImprovement", 0, "You are assessed for building " +
                "repair: $40 per house, $115 per hotel"));
        communityChests.add(new CommunityGUI("collectBank",10, "You won second prize in a writing " +
                "contest. Collect $10"));
        communityChests.add(new CommunityGUI("collectBank", 100,"You inherit $100"));


        //Deck of ChanceGUI cards
        chance = new LinkedList<>();
        chance.add(new ChanceGUI("advance", 39, "Advance to McNulty's House"));
        chance.add(new ChanceGUI("advance", 0, "Advance to " + spaces.get(0).spaceName() + " (Collect $200)"));
        chance.add(new ChanceGUI("advance", 24, "Advance to " + spaces.get(24).spaceName() + ". If you " +
                "pass Go, collect $200"));
        chance.add(new ChanceGUI("advance", 11, "Advance to " + spaces.get(11).spaceName() + ". If you " +
                "pass Go, collect $200"));
        chance.add(new ChanceGUI("advNearestRR", 0, "Advance to the nearest Dorm. If you " +
                "pass Go, collect $200"));
        chance.add(new ChanceGUI("advNearestRR", 0, "Advance to the nearest Dorm. If you " +
                "pass Go, collect $200"));
        chance.add(new ChanceGUI("advNearestUtil", 0, "Advance to the nearest Dining Hall. If unowned, " +
                "you may buy it from the Bank. If owned, pay owner the rent to which they are entitled. "));
        chance.add(new ChanceGUI("collectBank",50,"Bank pays you dividend of $50"));
        chance.add(new ChanceGUI("goBack", 3, "Go back 3 spaces"));
        chance.add(new ChanceGUI("goToJail", 1, "Go to the Provost. Go directly to the Provost, " +
                "do not pass Go, do not collect $200."));
        chance.add(new ChanceGUI("homeImprovement", 0, "Make general repairs on all your property." +
                " For each house pay $40. For each hotel pay $115."));
        chance.add(new ChanceGUI("payBank",15, "Speeding fine $15"));
        chance.add(new ChanceGUI("advance", 0, "Take a trip to MEP. If you pass Go, " +
                "collect $200."));
        chance.add(new ChanceGUI("payPlayers", 50, "You have been selected President of Student Government." +
                " Pay each player $50."));
        chance.add(new ChanceGUI("collectBank", 150, "You got on the honor roll! Collect $150"));

        //Shuffle card decks
        Collections.shuffle(communityChests);
        Collections.shuffle(chance);

        //Color Group Sets
        colorGroups = new HashMap<>();
        colorGroups.put("Brown", new HashSet<>(List.of(
                (StreetsGUI) spaces.get(1),
                (StreetsGUI) spaces.get(3))));
        colorGroups.put("Light Blue", new HashSet<>(List.of(
                (StreetsGUI) spaces.get(6),
                (StreetsGUI) spaces.get(8),
                (StreetsGUI) spaces.get(9))));
        colorGroups.put("Pink", new HashSet<>(List.of(
                (StreetsGUI) spaces.get(11),
                (StreetsGUI) spaces.get(13),
                (StreetsGUI) spaces.get(14))));
        colorGroups.put("Orange", new HashSet<>(List.of(
                (StreetsGUI) spaces.get(16),
                (StreetsGUI) spaces.get(18),
                (StreetsGUI) spaces.get(19))));
        colorGroups.put("Red", new HashSet<>(List.of(
                (StreetsGUI) spaces.get(21),
                (StreetsGUI) spaces.get(23),
                (StreetsGUI) spaces.get(24))));
        colorGroups.put("Yellow", new HashSet<>(List.of(
                (StreetsGUI) spaces.get(26),
                (StreetsGUI) spaces.get(27),
                (StreetsGUI) spaces.get(29))));
        colorGroups.put("Green", new HashSet<>(List.of(
                (StreetsGUI) spaces.get(31),
                (StreetsGUI) spaces.get(32),
                (StreetsGUI) spaces.get(34))));
        colorGroups.put("Dark Blue", new HashSet<>(List.of(
                (StreetsGUI) spaces.get(37),
                (StreetsGUI) spaces.get(39))));


        JLabel lblMonopoly = new JLabel("MONOPOLY"){
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
                AffineTransform aT = g2.getTransform();
                Shape oldshape = g2.getClip();
                double x = getWidth()/2.0;
                double y = getHeight()/2.0;
                aT.rotate(Math.toRadians(-35), x, y);
                g2.setTransform(aT);
                g2.setClip(oldshape);
                super.paintComponent(g);
            }
        };
        lblMonopoly.setForeground(Color.WHITE);
        lblMonopoly.setBackground(Color.RED);
        lblMonopoly.setOpaque(true);
        lblMonopoly.setHorizontalAlignment(SwingConstants.CENTER);
        lblMonopoly.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
        lblMonopoly.setBounds(310, 150, 200, 55);
        this.add(lblMonopoly);
    }

    public void paintComponent (Graphics g) { super.paintComponent(g);}

    /**
     * Getter: SpacesGUI on BoardGUI
     * @return HashMap of spaces
     */
    public HashMap<Integer, SpacesGUI> getSpaces() {
        return spaces;
    }

    /**
     * Reverses HashMap<Integer location, SpacesGUI space> spaces
     * @param spaces HashMap of an inherited space class and it's relative integer location
     * @return HashMap<SpacesGUI, Integer> locations
     */
    public HashMap<SpacesGUI, Integer> getLocations(HashMap<Integer, SpacesGUI> spaces) {
        locations = new HashMap<>();
        for(Integer i : spaces.keySet()) {
            locations.put(spaces.get(i), i);
        }
        return locations;
    }

    /**
     * Getter: Brown color group
     * @return HashSet of StreetsGUI properties in the Brown color group
     */
    public static Set<StreetsGUI> getBrown() {
        return brown;
    }
    /**
     * Getter: Light Blue color group
     * @return HashSet of StreetsGUI properties in the Light Blue color group
     */
    public static Set<StreetsGUI> getLightBlue() {
        return lightBlue;
    }
    /**
     * Getter: Pink color group
     * @return HashSet of StreetsGUI properties in the Pink color group
     */
    public static Set<StreetsGUI> getPink() {
        return pink;
    }
    /**
     * Getter: Orange color group
     * @return HashSet of StreetsGUI properties in the Orange color group
     */
    public static Set<StreetsGUI> getOrange() {
        return orange;
    }
    /**
     * Getter: Red color group
     * @return HashSet of StreetsGUI properties in the Red color group
     */
    public static Set<StreetsGUI> getRed() {
        return red;
    }
    /**
     * Getter: Yellow color group
     * @return HashSet of StreetsGUI properties in the Yellow color group
     */
    public static Set<StreetsGUI> getYellow() {
        return yellow;
    }
    /**
     * Getter: Green color group
     * @return HashSet of StreetsGUI properties in the Green color group
     */
    public static Set<StreetsGUI> getGreen() {
        return green;
    }
    /**
     * Getter: Dark Blue color group
     * @return HashSet of StreetsGUI properties in the Dark Blue color group
     */
    public static Set<StreetsGUI> getDarkBlue() {
        return darkBlue;
    }
}
