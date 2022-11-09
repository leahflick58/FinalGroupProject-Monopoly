public class Property extends Spaces {
    private int rent;
    private int price;
    private int mortgage;
    private boolean mortgaged;

    public Property(int rent, int price, int mortgage) {
        this.rent = rent;
        this.price = price;
        this.mortgage = mortgage;
        mortgaged = false;
    }

    public int getRent() {
        return rent;
    }

    public int getPrice() {
        return price;
    }

    public int getMortgage() {
        return mortgage;
    }

    @Override
    public void action() {
        ;
    }

    public void mortage() {
        mortgaged = true;
    }

    public void unmortgage() {
        mortgaged = false;
    }


}
