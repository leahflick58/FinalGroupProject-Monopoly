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

}
