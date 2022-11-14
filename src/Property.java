abstract class Property extends Spaces {
    private final String name;
    private final int rent;
    private final int price;
    private final int mortgage;
    private boolean mortgaged;

    public Property(String name, int rent, int price, int mortgage) {
        this.name = name;
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

    // TODO: This is gonna require a lot of testing, I don't know if this does what we want
    @Override
    public void action(Player p) {
        // loop through all players
        boolean owned = false;
        for (Player player : GameLoop.players) {
            // if a player owns this property, mark as owned
            if (player.ownsProperty(this)) {
                owned = true;
                // if the player that owns this property is not whose turn it is, the current player must pay rent
                if (player != p) {
                    p.payRent(player, this);
                }   // no else statement, if the property is owned by current player the move passes to the next player
                break;
            }
        }
        // if the property is not owned, the player may either buy or pass
        if (!owned) {
            // NOTICE - First time I've had to deal with user input
            System.out.println("Do you want to buy this property?");
            System.out.println(this.getPrice());
            // TODO: Scanner???
            p.addProperty(this);
        }
    }

    /**
     * When a Property is sold back to the bank, it resets it its "natural" state
     */
    public void reset() {
        unmortgage();
    }

    public void mortage() {
        mortgaged = true;
    }

    public void unmortgage() {
        mortgaged = false;
    }

    abstract int getTotalRent(Player p);


}
