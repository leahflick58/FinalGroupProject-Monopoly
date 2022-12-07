package MonopolyGUI;

import java.util.ArrayList;
import java.util.Set;

public class PlayerGUI {
    protected String name;
    protected boolean isInJail;
    protected boolean bankrupt;
    protected int numGetOutOfJail;
    protected int turnsInJail;
    protected ArrayList<PropertyGUI> properties;
    protected int bankBalance;
    protected int currentSpace;

    /**
     * A PlayerGUI has 8 attributes, only one of which is up for user input.
     * Pre-determined attributes:
     * isInJail - boolean for if a PlayerGUI is in jail.
     * bankrupt - boolean for if a PlayerGUI is still "in the game".
     * hasGetOutOfJail - int of number of Get Out of JailGUI cards a PlayerGUI has.
     * turnsInJail - int of number of concurrent turns a PlayerGUI has spent in jail.
     * properties - an empty ArrayList of PlayerGUI's owned Properties.
     * bankBalance - int of PlayerGUI's available money, initiated at $1500.
     * currentSpace - int of PlayerGUI's respective space on the BoardGUI. Initiated at "GO".
     * @param name User's name
     */
    public PlayerGUI(String name) {
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
     * @return number of UtilitiesGUI a PlayerGUI owns in their Properties ArrayList
     */
    public int numberUtilities() {
        int utilities = 0;
        for (PropertyGUI property : properties) {
            if (property instanceof UtilitiesGUI) {
                utilities ++;
            }
        }
        return utilities;
    }

    /**
     * Searches through a players owned properties and counts the number of railroads
     * @return number of RailRoads a PlayerGUI owns in their Properties ArrayList
     */
    public int numberRailRoads() {
        int railroads = 0;
        for (PropertyGUI property : properties) {
            if (property instanceof RailroadsGUI) {
                railroads ++;
            }
        }
        return railroads;
    }

    /**
     * Searches through a players owned properties and counts the number of houses
     * @return number of Houses a PlayerGUI owns in their Properties ArrayList
     */
    public int numberHouses() {
        int houses = 0;
        for (PropertyGUI property : properties) {
            if (property instanceof StreetsGUI) {
                if (!((StreetsGUI) property).getHouseStatus())
                        houses ++;
            }
        }
        return houses;
    }

    /**
     * Searches through a players owned properties and counts the number of Hotels
     * @return number of Hotels a PlayerGUI owns in their Properties ArrayList
     */
    public int numberHotels() {
        int hotels = 0;
        for (PropertyGUI property : properties) {
            if (property instanceof StreetsGUI) {
                if (((StreetsGUI) property).getHouseStatus())
                    hotels ++;
            }
        }
        return hotels;
    }

    /**
     * Adds a PropertyGUI to a players owned properties list
     * @param newProperty property that player is buying
     */
    public void addProperty(PropertyGUI newProperty) {
        bankBalance = bankBalance - newProperty.getPrice();
        properties.add(newProperty);
    }

    /**
     * Removes a PropertyGUI from a players owned properties list.
     * Adds half of property's price to PlayerGUI BankBalance
     * Resets property status (un-mortgaged and house)
     * @param soldProperty property that player is Selling
     */
    public void sellProperty(PropertyGUI soldProperty) {
        bankBalance = bankBalance + (soldProperty.getPrice() / 2);
        properties.remove(soldProperty);
        soldProperty.reset();
    }

    /**
     * Checks a PlayerGUI's owned properties list to see if that PlayerGUI owns the property
     * @param property property in question
     * @return true if PlayerGUI owns that property, else false
     */
    public boolean ownsProperty(PropertyGUI property) {
        return properties.contains(property);
    }

    /**
     * The space the PlayerGUI currently is on the board
     * @return an integer representing the PlayerGUI's space
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
     * @return true if PlayerGUI has 1+ Get Out of JailGUI cards
     */
    public int getNumGetOutOfJail() {
        return numGetOutOfJail;
    }

    /**
     * Add or subtract a Get Out of JailGUI CardGUI if a PlayerGUI either:
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
     * After a player leaves JailGUI, TurnsInJail is reset back to zero
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
     * Determines the amount of rent due depending on PropertyGUI type
     * @param otherPlayer the player that owns the property landed on
     * @param property property in question in order to gain the property type for getTotalRent() method
     */
    public void payRent(PlayerGUI otherPlayer, PropertyGUI property) {
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
     * @return true if player owns all StreetsGUI within color group
     */
    public boolean hasEntireColorGroup(Set<StreetsGUI> color) {
        int numStreets = 0;
        for(StreetsGUI s : color) {
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
