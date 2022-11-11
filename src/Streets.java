public class Streets extends Property {
    private boolean isHotel;
    private final String colorGroup;
    public Streets(int rent, int price, int mortgage, String colorGroup) {
        super(rent, price, mortgage);
        this.isHotel = false;
        this.colorGroup = colorGroup;
    }

    public boolean getHouseStatus() {
        return isHotel;
    }

    public String getColorGroup() {
        return colorGroup;
    }

    public void upgrade(Streets property) {
        property.isHotel = true;
    }

    @Override
    public void reset() {
        super.reset();
        isHotel = false;
    }

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
