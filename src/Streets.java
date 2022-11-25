public class Streets extends Property {
    private boolean isHotel;
    private final String colorGroup;

    /**
     * Streets have all the same parameters from their parent class, Property, with one addition
     * @param name name of the property (i.e. "Beans on Broad", "Hall of Arts and Letters", etc.
     * @param rent rent due when someone other than the owner lands on the property
     * @param price price in order to buy property
     * @param mortgage the amount the player can receive from the bank from mortgaging the property
     * @param colorGroup color group the Street is associated with, determines if a Player can upgrade to a house
     */
    public Streets(String name, int rent, int price, int mortgage, String colorGroup) {
        super(name, rent, price, mortgage);
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
        //TODO: check to see if Player is eligible to upgrade
        property.isHotel = true;
    }

    /**
     * When a Property is sold back to the bank, it resets it its "natural" state
     */
    @Override
    public void reset() {
        super.reset();
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
}
