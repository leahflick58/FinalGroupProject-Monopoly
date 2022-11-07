public class Properties {
    private int rent;
    private int price;
    private int mortgage;
    private boolean mortgaged;

    public Properties(int rent, int price, int mortgage) {
        this.rent = rent;
        this.price = price;
        this.mortgage = mortgage;
    }


    public void mortage() {
        mortgaged = true;
    }

    public void unmortgage() {
        mortgaged = false;
    }

    //abstract void upgrade();

}
