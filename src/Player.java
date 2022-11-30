import java.util.*;

public class Player {
    protected String name;
    protected boolean isInJail;
    protected boolean bankrupt;
    protected int numGetOutOfJail;
    protected int turnsInJail;
    protected ArrayList<Property> properties;
    protected int bankBalance;
    protected int currentSpace;

    /**
     * A Player has 8 attributes, only one of which is up for user input.
     * Pre-determined attributes:
     * isInJail - boolean for if a Player is in jail.
     * bankrupt - boolean for if a Player is still "in the game".
     * hasGetOutOfJail - int of number of Get Out of Jail cards a Player has.
     * turnsInJail - int of number of concurrent turns a Player has spent in jail.
     * properties - an empty ArrayList of Player's owned Properties.
     * bankBalance - int of Player's available money, initiated at $1500.
     * currentSpace - int of Player's respective space on the Board. Initiated at "GO".
     * @param name User's name
     */
    public Player(String name) {
        this.name = name;
        isInJail = false;
        bankrupt = false;
        numGetOutOfJail = 0;
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

    /**
     * @return true if Player has 1+ Get Out of Jail cards
     */
    public int getNumGetOutOfJail() {
        return numGetOutOfJail;
    }

    /**
     * Add or subtract a Get Out of Jail Card if a Player either:
     * a) receives a card
     * b) plays a card
     * @param amount +1 to receive a card, -1 to play a card
     */
    public void setNumGetOutOfJail(int amount) {
        this.numGetOutOfJail = numGetOutOfJail + amount;
    }

    /**
     * @return number of concurrent turns in jail
     */
    public int getTurnsInJail() {
        return turnsInJail;
    }

    /**
     * Sets number of turns that a player is in jail.
     * @param turns
     */
    public void setTurnsInJail(int turns) {
        this.turnsInJail = turns;
    }

    /**
     * After a player leaves Jail, TurnsInJail is reset back to zero
     */
    public void resetTurnsInJail() {
        turnsInJail = 0;
    }

    /**
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

    /**
     * Determines the amount of rent due depending on Property type
     * @param otherPlayer the player that owns the property landed on
     * @param property property in question in order to gain the property type for getTotalRent() method
     */
    public void payRent(Player otherPlayer, Property property) {
        int rentAmount = property.getTotalRent(otherPlayer);
        this.addOrSubBankBalance(-rentAmount);
        otherPlayer.addOrSubBankBalance(rentAmount);
    }

    /**
     * Finds the nearest Railroad to player (forward direction only)
     * @return int location
     */
    public int nearestRailroad() {
        //NOTE: hard-coded values
        if(((currentSpace > 0) && currentSpace < 5) || (currentSpace > 35)) {
            return 5;
        }
        else if((currentSpace >= 5) && currentSpace < 15) {
            return 15;
        }
        if((currentSpace >= 15) && currentSpace < 25) {
            return 25;
        }
        else return 35;
    }

    /**
     * Finds the nearest Utility to player (forward direction only)
     * @return int location
     */
    public int nearestUtility() {
        //NOTE: hard-coded values
        if(((currentSpace > 0) && currentSpace < 12) || (currentSpace > 28)) {
            return 12;
        }
        else return 28;
    }

    /**
     * Determines whether this player is bankrupt
     * @return true if bankrupt
     */
    public boolean isBankrupt() {
        if(bankBalance <= 0) {
            bankrupt = true;
        }
        return bankrupt;
    }

    /**
     * Determines whether this player owns all Street properties in this color group
     * @param color
     * @return true if player owns all Streets within color group
     */
    public boolean hasEntireColorGroup(Set<Streets> color) {
        int numStreets = 0;
        for(Streets s : color) {
            if(ownsProperty(s)) {
                numStreets++;
            }
        }
        if(numStreets == color.size()) {
            return true;
        }
        return false;
    }

}
