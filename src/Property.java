import java.util.Scanner;

abstract class Property extends Spaces {
    private final String name;
    private final int rent;
    private final int price;
    private final int mortgage;
    private boolean mortgaged;

    /**
     * A Property, extended from Spaces class, has five attributes:
     * @param name name of the property (i.e. "Beans on Broad", "Hall of Arts and Letters", etc.)
     * @param rent rent due when someone other than the owner lands on the property
     * @param price price in order to buy property
     * @param mortgage the amount the player can receive from the bank from mortgaging the property
     *                 Finally, boolean mortgaged is set to false
     */
    public Property(String name, int rent, int price, int mortgage) {
        this.name = name;
        this.rent = rent;
        this.price = price;
        this.mortgage = mortgage;
        mortgaged = false;
    }

    /**
     * @return Property's rent
     */
    public int getRent() {
        return rent;
    }

    /**
     * @return Property's price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @return Property's mortgage
     */
    public int getMortgage() {
        return mortgage;
    }

    /**
     * When a Player lands on a Property space, first check if that property is owned.
     * If the Player who landed on the space owns it, break.
     * If a different Player owns it, the current Player must pay them rent.
     * If no one owns it, the Player has the choice to buy the Property or pass.
     * @param p
     */
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
                System.out.println(player.name + " owns this property. You must pay " + this.getTotalRent(player) + " in rent.");
                if (player != p) {
                    p.payRent(player, this);
                }   // no else statement, if the property is owned by current player the move passes to the next player
                break;
            }
        }
        // if the property is not owned, the player may either buy or pass
        if (!owned) {
            // NOTICE - First time I've had to deal with user input
            Scanner in = new Scanner(System.in);
            String decision = "O";
            while (!decision.equals("Y") && !decision.equals("N")) {
                System.out.println("Do you want to buy this property? Enter Y/N: ");
                System.out.println(this.getDetails());
                decision = in.next();
                if (!decision.equals("Y") && !decision.equals("N")) {
                    System.out.println("Invalid input. Choose Y or N");
                }
            }
            if(decision.equals("Y")) {
                p.addProperty(this);
                System.out.println(p.name + " has bought " + this.name);
            }
        }
    }

    /**
     * When a Property is sold back to the bank, it resets it its "natural" state
     */
    public void reset() {
        unmortgage();
    }

    /**
     * Used when a player wishes to mortgage a property for extra cash
     */
    public void mortgage() {
        mortgaged = true;
    }

    /**
     * Used when either a player chooses to unmortgage a property or when the property is seized by the bank (reset()
     * method)
     */
    public void unmortgage() {
        mortgaged = false;
    }

    /**
     * Returns the amount of rent due based on Player's number of specific property type
     * @param p Player who owns the respective property
     * @return total dollar amount due
     */
    abstract int getTotalRent(Player p);

    public String getName() {
        return name;
    }

    /**
     * Gets a String of all of this property's values
     * @return String
     */
    public String getDetails() {
        return (getName() + "\nPrice: $" + getPrice() + "\nRent: $" + getRent()
        + "\nMortgage: $" + getMortgage());
    }

}
