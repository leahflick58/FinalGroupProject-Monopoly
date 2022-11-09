public class Streets extends Property {
    private boolean isHotel;
    public Streets(int rent, int price, int mortgage) {
        super(rent, price, mortgage);
        this.isHotel = false;
    }

    public boolean getHouseStatus() {
        return isHotel;
    }

    public void upgrade(Streets property) {
        property.isHotel = true;
    }

}
