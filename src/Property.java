import java.util.Scanner;

abstract class Property extends Spaces {
    private final String name;
    private final int rent;
    private final int price;

    /**
     * A Property, extended from Spaces class, has five attributes:
     * @param name name of the property (i.e. "Beans on Broad", "Hall of Arts and Letters", etc.)
     * @param rent rent due when someone other than the owner lands on the property
     * @param price price in order to buy property
     */
    public Property(String name, int rent, int price) {
        this.name = name;
        this.rent = rent;
        this.price = price;
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
     * When a Player lands on a Property space, first check if that property is owned.
     * If the Player who landed on the space owns it, break.
     * If a different Player owns it, the current Player must pay them rent.
     * If no one owns it, the Player has the choice to buy the Property or pass.
     * @param p
     */
    @Override
    public void action(Player p) {
        // loop through all players
        boolean owned = false;
        for (Player player : GameLoop.players) {
            // if a player owns this property, mark as owned
            if (player.ownsProperty(this)) {
                owned = true;
                // if the player that owns this property is not whose turn it is, the current player must pay rent
                System.out.println(player.name + " owns this property. You must pay $" + this.getTotalRent(player) + " in rent.");
                if (player != p) {
                    // TODO: Check if player can afford it
                    if (p.bankBalance - this.getTotalRent(player) > 0) {
                        p.payRent(player, this);
                    } else {
                        if (p.properties.size() > 0) {
                            System.out.println("You cannot afford rent. You must sell a property to pay. What property would you like to sell?");
                            for (Property property: p.properties) {
                                System.out.println(property.name + " Selling Price: " + (property.getPrice() / 2));
                            }
                            while (p.bankBalance - this.getTotalRent(player) < 0) {
                                Scanner in = new Scanner(System.in);
                                boolean correct_name = false;
                                while (!correct_name) {
                                    System.out.println("Property: ");
                                    String property = in.next();
                                    for (Property properties: p.properties) {
                                        if (properties.getName().equals(property)) {
                                            p.sellProperty(properties);
                                            System.out.println(property + " sold to the bank.");
                                            correct_name = true;
                                            break;
                                        }
                                    }
                                    if (!correct_name) {
                                        System.out.println("An invalid property name was entered. Please enter a valid property.");
                                    }
                                }
                            }
                            p.payRent(player, this);
                        } else {
                            System.out.println("You cannot afford the rent. Your remaining bank balance will be transferred to " + player.name + " and your assets will be given to the bank.");
                            p.bankrupt = true;
                            for (Property property : p.properties) {
                                p.sellProperty(property);
                            }
                            break;
                        }
                    }
                }   // no else statement, if the property is owned by current player the move passes to the next player
                break;
            }
        }
        // if the property is not owned, the player may either buy or pass
        if (!owned) {
            // NOTICE - First time I've had to deal with user input
            if (p.bankBalance - this.getPrice() > 0) {
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
            } else {
                System.out.println("You cannot afford this property.");
            }
        }
    }

    /**
     * When a Property is sold back to the bank, it resets it its "natural" state
     */
    public void reset() {

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
        return (getName() + "\nPrice: $" + getPrice() + "\nRent: $" + getRent());
    }

}
