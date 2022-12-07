package MonopolyGUI;

import java.util.HashMap;
import java.util.Map;

public class RailroadsGUI extends PropertyGUI {
    Map<Integer, Integer> railRoadRent;

    /**
     * RailRoads have all the same parameters as their parent class, PropertyGUI.
     * A HashMap is created for rent amounts based on amount of RailRoads owned
     *@param name name of the property (i.e. "Beans on Broad", "Hall of Arts and Letters", etc.
     *@param rent rent due when someone other than the owner lands on the property
     *@param price price in order to buy property
     */
    public RailroadsGUI(int xCoord, int yCoord, String labelString, int rotationDegrees, String name, int rent, int price) {
        super(xCoord, yCoord, labelString, rotationDegrees, name, rent, price);
        railRoadRent = new HashMap<>();
        railRoadRent.put(1, 25);
        railRoadRent.put(2, 50);
        railRoadRent.put(3, 100);
        railRoadRent.put(4, 200);
    }

    /**
     * Returns the amount of rent due based on PlayerGUI's number of railroads.
     * @param p PlayerGUI who owns the respective property
     * @return total dollar amount due
     */
    @Override
    public int getTotalRent(PlayerGUI p) {
        int numRailroads = p.numberRailRoads();
        return railRoadRent.get(numRailroads);
    }
}
