import java.util.ArrayList;

public class Player {
    protected String name;
    protected boolean isInJail;
    protected boolean bankrupt;
    protected boolean hasGetOutOfJail;
    protected int turnsInJail;
    protected ArrayList<Property> properties;
    protected int bankBalance;
    protected int currentSpace;

    public Player(String name) {
        this.name = name;
        isInJail = false;
        bankrupt = false;
        hasGetOutOfJail = false;
        turnsInJail = 0;
        properties = new ArrayList<>();
        bankBalance = 1500;
        currentSpace = 0;
    }

    /**
     * Searches through a players owned properties and counts the number of utilities
     * @return number of Utilities a Player owns in their Properties ArrayList
     */
    public int numberUtilities() {
        int utilities = 0;
        for (Property property : properties) {
            if (property instanceof Utilities) {
                utilities ++;
            }
        }
        return utilities;
    }

    /**
     * Searches through a players owned properties and counts the number of railroads
     * @return number of RailRoads a Player owns in their Properties ArrayList
     */
    public int numberRailRoads() {
        int railroads = 0;
        for (Property property : properties) {
            if (property instanceof Railroads) {
                railroads ++;
            }
        }
        return railroads;
    }

    /**
     * Searches through a players owned properties and counts the number of houses
     * @return number of Houses a Player owns in their Properties ArrayList
     */
    public int numberHouses() {
        int houses = 0;
        for (Property property : properties) {
            if (property instanceof Streets) {
                if (!((Streets) property).getHouseStatus())
                        houses ++;
            }
        }
        return houses;
    }

    /**
     * Searches through a players owned properties and counts the number of Hotels
     * @return number of Hotels a Player owns in their Properties ArrayList
     */
    public int numberHotels() {
        int hotels = 0;
        for (Property property : properties) {
            if (property instanceof Streets) {
                if (((Streets) property).getHouseStatus())
                    hotels ++;
            }
        }
        return hotels;
    }

    /**
     * Adds a Property to a players owned properties list
     * @param newProperty property that player is buying
     */
    public void addProperty(Property newProperty) {
        bankBalance = bankBalance - newProperty.getPrice();
        properties.add(newProperty);
    }

    /**
     * Removes a Property from a players owned properties list.
     * @param soldProperty
     */
    public void sellProperty(Property soldProperty) {
        bankBalance = bankBalance + (soldProperty.getPrice() / 2);
        properties.remove(soldProperty);
    }

    public boolean ownsProperty(Property property) {
        return properties.contains(property);
    }

    public int getCurrentSpace() {
        return currentSpace;
    }

    public void setCurrentSpace(int space) {
        this.currentSpace = space;
    }

    public boolean getHasGetOutOfJail() {
        return hasGetOutOfJail;
    }

    public void setHasGetOutOfJail(boolean status) {
        hasGetOutOfJail = status;
    }

    public int getTurnsInJail() {
        return turnsInJail;
    }

    public void resetTurnsInJail() {
        turnsInJail = 0;
    }

    public int getBankBalance() {
        return bankBalance;
    }

    public void addOrSubBankBalance(int dollars) {
        bankBalance = bankBalance + dollars;
    }



}
