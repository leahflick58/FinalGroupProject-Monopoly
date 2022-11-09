public class Streets extends Property {
    private boolean isHouse;
    public Streets(int rent, int price, int mortgage) {
        super(rent, price, mortgage);
        this.isHouse = false;
    }

    public boolean getHouseStatus() {
        return isHouse;
    }
}
