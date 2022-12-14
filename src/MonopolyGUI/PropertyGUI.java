package MonopolyGUI;

import java.util.Scanner;

abstract class PropertyGUI extends SpacesGUI {
    private final String name;
    private final int rent;
    private final int price;

    /**
     * A PropertyGUI, extended from SpacesGUI class, has five attributes:
     * @param name name of the property (i.e. "Beans on Broad", "Hall of Arts and Letters", etc.)
     * @param rent rent due when someone other than the owner lands on the property
     * @param price price in order to buy property
     */
    public PropertyGUI(int xCoord, int yCoord, String labelString, int rotationDegrees, String name, int rent, int price) {
        super(xCoord, yCoord, labelString, rotationDegrees);
        this.name = name;
        this.rent = rent;
        this.price = price;
    }

    /**
     * @return PropertyGUI's rent
     */
    public int getRent() {
        return rent;
    }

    /**
     * @return PropertyGUI's price
     */
    public int getPrice() {
        return price;
    }


    /**
     * When a PlayerGUI lands on a PropertyGUI space, first check if that property is owned.
     * If the PlayerGUI who landed on the space owns it, break.
     * If a different PlayerGUI owns it, the current PlayerGUI must pay them rent.
     * If no one owns it, the PlayerGUI has the choice to buy the PropertyGUI or pass.
     * @param p
     */
    @Override
    public void action(PlayerGUI p) {
        // loop through all players
        boolean owned = false;
        for (PlayerGUI player : GameLoopGUI.players) {
            // if a player owns this property, mark as owned
            if (player.ownsProperty(this)) {
                owned = true;
                // if the player that owns this property is not whose turn it is, the current player must pay rent
                System.out.println(player.name + " owns this property. You must pay $" + this.getTotalRent(player) + " in rent.");
                if (player != p) {
                    if (p.bankBalance - this.getTotalRent(player) > 0) {
                        p.payRent(player, this);
                    } else {
                        if (p.properties.size() > 0) {
                            System.out.println("You cannot afford rent. You must sell a property to pay. What property would you like to sell?");
                            for (PropertyGUI property: p.properties) {
                                System.out.println(property.name + " Selling Price: " + (property.getPrice() / 2));
                            }
                            while (p.bankBalance - this.getTotalRent(player) < 0) {
                                Scanner in = new Scanner(System.in);
                                boolean correct_name = false;
                                while (!correct_name) {
                                    System.out.println("PropertyGUI: ");
                                    String property = in.next();
                                    for (PropertyGUI properties: p.properties) {
                                        if (properties.spaceName().equals(property)) {
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
                            for (PropertyGUI property : p.properties) {
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
     * When a PropertyGUI is sold back to the bank, it resets it its "natural" state
     */
    public void reset() {
    }

    /**
     * @return the name of this property
     */
    public String getName() {
        return name;
    }


    /**
     * Returns the amount of rent due based on PlayerGUI's number of specific property type
     * @param p PlayerGUI who owns the respective property
     * @return total dollar amount due
     */
    abstract int getTotalRent(PlayerGUI p);

    public String spaceName() {
        return name;
    }

    /**
     * Gets a String of all of this property's values
     * @return String
     */
    public String getDetails() {
        return (spaceName() + "\nPrice: $" + getPrice() + "\nRent: $" + getRent());
    }

}
