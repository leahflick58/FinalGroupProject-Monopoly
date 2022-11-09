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
        bankrupt = false;   // might want to get rid of this and instead just check if bankBalance <= 0
        hasGetOutOfJail = false;
        turnsInJail = 0;
        properties = new ArrayList<>();
        bankBalance = 1500;
        currentSpace = 0;
    }

    public int numberUtilities() {
        int utilities = 0;
        for (Property property : properties) {
            if (property instanceof Utilities) {
                utilities ++;
            }
        }
        return utilities;
    }

    public int numberRailRoads() {
        int railroads = 0;
        for (Property property : properties) {
            if (property instanceof Railroads) {
                railroads ++;
            }
        }
        return railroads;
    }

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

    public void addProperty(Property newProperty) {
        bankBalance = bankBalance - newProperty.getPrice();
        properties.add(newProperty);
    }

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


}
