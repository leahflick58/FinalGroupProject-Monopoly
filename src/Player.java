import java.util.ArrayList;
import java.util.Properties;

public class Player {
    protected String name;
    protected boolean isInJail;
    protected boolean bankrupt;
    protected boolean hasGetOutOfJail;
    protected int turnsInJail;
    protected ArrayList<Properties> properties;
    protected int bankBalance;

    public Player(String name) {
        this.name = name;
        isInJail = false;
        bankrupt = false;   // might want to get rid of this and instead just check if bankBalance <= 0
        hasGetOutOfJail = false;
        turnsInJail = 0;
        properties = new ArrayList<>();
        bankBalance = 1;
        // TODO: Get starting bank balance
    }

    // TODO: Finish Method
    public int numberUtilities() {
        return 1;
    }

    // TODO: Finish Method
    public int numberRailRoads() {
        return 1;
    }

    // TODO: Finish Method
    public int numberHouses() {
        return 1;
    }

    // TODO: Finish Method
    public int numberHotels() {
        return 1;
    }

    public void addProperty(Properties newProperty) {
        // TODO: Change bank balance
        properties.add(newProperty);
    }

    public void sellProperty(Properties soldProperty) {
        // TODO: Change bank balance
        properties.remove(soldProperty);
    }

    public boolean ownsProperty(Properties property) {
        return properties.contains(property);
    }


}
