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
     * Adds half of property's price to Player BankBalance
     * Resets property status (un-mortgaged and house)
     * @param soldProperty property that player is Selling
     */
    public void sellProperty(Property soldProperty) {
        bankBalance = bankBalance + (soldProperty.getPrice() / 2);
        properties.remove(soldProperty);
        soldProperty.reset();
    }

    /**
     * Checks a Player's owned properties list to see if that Player owns the property
     * @param property property in question
     * @return true if Player owns that property, else false
     */
    public boolean ownsProperty(Property property) {
        return properties.contains(property);
    }

    /**
     * The space the Player currently is on the board
     * @return an integer representing the Player's space
     */
    public int getCurrentSpace() {
        return currentSpace;
    }

    /**
     * Moves player on the board and saves their new space
     * @param space the space the player should end up
     */
    public void setCurrentSpace(int space) {
        this.currentSpace = space;
    }

    public boolean getHasGetOutOfJail() {
        return hasGetOutOfJail;
    }

    public void setHasGetOutOfJail(boolean status) {
        hasGetOutOfJail = status;
    }

    /**
     * To test if a Player has been in jail 3 concurrent turns -> if so they must pay the fee and can leave
     * @return number of concurrent turns in jail
     */
    public int getTurnsInJail() {
        return turnsInJail;
    }

    /**
     * After a player leaves Jail, TurnsInJail is reset back to zero
     */
    public void resetTurnsInJail() {
        turnsInJail = 0;
    }

    /**
     *
     * @return a player's current bank balance as an int
     */
    public int getBankBalance() {
        return bankBalance;
    }

    /**
     * Changes a player's bank balance by provided amount
     * @param dollars positive to add money or negative to subtract money
     */
    public void addOrSubBankBalance(int dollars) {
        bankBalance = bankBalance + dollars;
    }



}
