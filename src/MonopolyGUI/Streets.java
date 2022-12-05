package MonopolyGUI;

import java.awt.*;

public class Streets extends Property {
    private boolean isHotel;
    private final String colorGroup;

    /**
     * Streets have all the same parameters from their parent class, Property, with one addition
     * @param name name of the property (i.e. "Beans on Broad", "Hall of Arts and Letters", etc.
     * @param rent rent due when someone other than the owner lands on the property
     * @param price price in order to buy property
     * @param colorGroup color group the Street is associated with, determines if a Player can upgrade to a house
     */
    public Streets(int xCoord, int yCoord, String labelString, int rotationDegrees, String name, int rent, int price, String colorGroup) {
        super(xCoord, yCoord, labelString, rotationDegrees, name, rent, price);
        this.isHotel = false;
        this.colorGroup = colorGroup;
    }

    /**
     * @return if a Street is a house or hotel
     */
    public boolean getHouseStatus() {
        return isHotel;
    }

    /**
     * @return Street's color group
     */
    public String getColorGroup() {
        return colorGroup;
    }

    /**
     * If a Player owns all respective Streets in a color group, they can upgrade a property to a Hotel
     * @param property Street Player wants to upgrade
     */
    public void upgrade(Streets property) {
        property.isHotel = true;
    }

    /**
     * When a Property is sold back to the bank, it resets it its "natural" state
     */
    @Override
    public void reset() {
        isHotel = false;
    }

    /**
     * Returns the amount of rent due based on Player's number of Streets (houses + hotels)
     * @param p Player who owns the respective property
     * @return total dollar amount due
     */
    // Instead of the regular house/hotel rent since we only have one rent variable, I just have hotels marked up 25%
    @Override
    int getTotalRent(Player p) {
        int rent = 0;
        for (Property property : p.properties) {
            if (property instanceof Streets) {
                if (((Streets) property).isHotel) {
                    rent += property.getRent() * (1.25);
                } else {
                    rent += property.getRent();
                }
            }
        }
        return rent;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(this.colorGroup.equals("brown")){
            g.drawRect(0,this.getHeight()-20,this.getWidth(),20);
            g.setColor(new Color(45, 18, 18));
            g.fillRect(0,this.getHeight()-20,this.getWidth(),20);
        }
        if(this.colorGroup.equals("lightBlue")){
            g.drawRect(0,this.getHeight()-20,this.getWidth(),20);
            g.setColor(new Color(133, 238, 238, 255));
            g.fillRect(0,this.getHeight()-20,this.getWidth(),20);
        }
        if(this.colorGroup.equals("pink")){
            g.drawRect(0,0,20,this.getHeight());
            g.setColor(Color.PINK);
            g.fillRect(0,0,20,this.getHeight());
        }
        if(this.colorGroup.equals("orange")){
            g.drawRect(0,0,20,this.getHeight());
            g.setColor(Color.ORANGE);
            g.fillRect(0,0,20,this.getHeight());
        }
        if(this.colorGroup.equals("red")){
            g.drawRect(0,0,this.getWidth(),20);
            g.setColor(Color.RED);
            g.fillRect(0,0,this.getWidth(),20);
        }
        if(this.colorGroup.equals("yellow")){
            g.drawRect(0,0,this.getWidth(),20);
            g.setColor(Color.YELLOW);
            g.fillRect(0,0,this.getWidth(),20);
        }
        if(this.colorGroup.equals("green")){
            g.drawRect(this.getWidth()-20,0,20,this.getHeight());
            g.setColor(Color.GREEN);
            g.fillRect(this.getWidth()-20,0,20,this.getHeight());
        }
        if(this.colorGroup.equals("darkBlue")){
            g.drawRect(this.getWidth()-20,0,20,this.getHeight());
            g.setColor(new Color(6, 41, 98, 239));
            g.fillRect(this.getWidth()-20,0,20,this.getHeight());
        }
    }
}
