package MonopolyGUI;


public class StreetsGUI extends PropertyGUI {
    private boolean isHotel;
    private final String colorGroup;

    /**
     * StreetsGUI have all the same parameters from their parent class, PropertyGUI, with one addition
     * @param name name of the property (i.e. "Beans on Broad", "Hall of Arts and Letters", etc.
     * @param rent rent due when someone other than the owner lands on the property
     * @param price price in order to buy property
     * @param colorGroup color group the Street is associated with, determines if a PlayerGUI can upgrade to a house
     */
    public StreetsGUI(int xCoord, int yCoord, String labelString, int rotationDegrees, String name, int rent, int price, String colorGroup) {
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
     * If a PlayerGUI owns all respective StreetsGUI in a color group, they can upgrade a property to a Hotel
     * @param property Street PlayerGUI wants to upgrade
     */
    public void upgrade(StreetsGUI property) {
        property.isHotel = true;
    }

    /**
     * When a PropertyGUI is sold back to the bank, it resets it its "natural" state
     */
    @Override
    public void reset() {
        isHotel = false;
    }

    /**
     * Returns the amount of rent due based on PlayerGUI's number of StreetsGUI (houses + hotels)
     * @param p PlayerGUI who owns the respective property
     * @return total dollar amount due
     */
    // Instead of the regular house/hotel rent since we only have one rent variable, I just have hotels marked up 25%
    @Override
    int getTotalRent(PlayerGUI p) {
        int rent = 0;
        for (PropertyGUI property : p.properties) {
            if (property instanceof StreetsGUI) {
                if (((StreetsGUI) property).isHotel) {
                    rent += property.getRent() * (1.25);
                } else {
                    rent += property.getRent();
                }
            }
        }
        return rent;
    }
}
