public abstract class Card {
    private final String type;
    private final int amount;
    private final String message;

    /**
     * A singular card for the Community/Chance deck of cards.
     * @param type relates to the action associated with the type of card (Collect money, pay bank, pay other bankers,
     *             go to jail, etc.).
     * @param amount either the amount due/owed, the location of the desired space, or the number of spaces to move
     *               depending on the type
     * @param message note written on the card for the user to see
     */
    public Card(String type, int amount, String message) {
        this.type = type;
        this.amount = amount;
        this.message = message;
    }

    /**
     * @return Card type
     */
    public String getType() {
        return this.type;
    }

    /**
     * @return Card amount
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * @return Card message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * For type "payBank" - only affects active player.
     * Subtracts specified amount from Player bank balance.
     * @param p active Player
     * @param amount specified dollar amount
     */
    public void payBank(Player p, int amount) {
        p.addOrSubBankBalance(-amount);
    }

    /**
     * For type "payPlayers" - affects all players in game.
     * Subtracts specified amount from active Player's bank balances and pays each respective player.
     * @param p active Player
     * @param amount specified dollar amount
     */
    public void payPlayers(Player p, int amount) {
        for (Player player : GameLoop.players) {
            player.addOrSubBankBalance(amount);
            p.addOrSubBankBalance(-amount);
        }
    }

    /**
     * For type "collectBank" - only affects active player.
     * Adds specified amount from Player bank balance.
     * @param p active Player
     * @param amount specified dollar amount
     */
    public void collectBank(Player p, int amount) {
        p.addOrSubBankBalance(amount);
    }

    /**
     * For type "collectPlayers" - affects all players in game.
     * Subtracts specified amount from all other plays and adds the sum to the active Player's bank balance.
     * @param p active Player
     * @param amount specified dollar amount
     */
    public void collectPlayers(Player p, int amount) {
        for (Player player : GameLoop.players) {
            player.addOrSubBankBalance(-amount);
            p.addOrSubBankBalance(amount);
        }
    }

    /**
     * For type "goToJail" - only affects active player.
     * Sends active Player to Jail space (10) with 3 turns in jail.
     * @param p active Player
     */
    public void goToJail(Player p) {
        p.setCurrentSpace(10);
        p.isInJail = true;
        p.setTurnsInJail(3);
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }

    /**
     * For type "getOutOfJail" - only affects active player.
     * Adds a Get Out of Jail card to the active Player's inventory.
     * @param p active Player
     */
    public void getOutOfJail(Player p) {
        p.setNumGetOutOfJail(1);
    }

    /**
     * For type "homeImprovement" - only affects active player (specific to Chance cards).
     * Player must pay the bank ($115 * their number of hotels) + ($40 * their number of houses).
     * @param p active Player
     */
    public void homeImprovement(Player p) {
        p.addOrSubBankBalance(p.numberHotels() * 115);
        p.addOrSubBankBalance(p.numberHouses() * 40);

        System.out.println("Number of Houses: " + p.numberHouses());
        System.out.println("Number of Hotels: " + p.numberHotels());
        System.out.println("Bank balance: $" + p.bankBalance);
    }

    /**
     * For type "advance" - only affects active player (specific to Community cards).
     * Player is moved to the specified location on the card and then does the action on that space.
     * Player receives $200 if he/she passes Go.
     * @param p active Player
     * @param space intended space number
     */
    public void advance(Player p, int space) {
        if((space < p.currentSpace) && (space != 0)) {      //otherwise, will add $200 twice
            p.addOrSubBankBalance(200);
        }
        p.setCurrentSpace(space);
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }

    /**
     * For type "goBack" - only affects active player (specific to Community cards).
     * Player is moved back a specified number of spaces and then does the action on that space.
     * @param p active Player
     * @param amount number of spaces to move back
     */
    public void goBack(Player p, int amount) {
        p.setCurrentSpace(p.getCurrentSpace() - amount - 1);
        System.out.println("Landed on " +
                Board.spaces.get(p.getCurrentSpace()).getName());
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }

    /**
     * For type "advance" - only affects active player (specific to Chance cards).
     * Player advances to the nearest Railroad and performs the action on that space.
     * Player receives $200 if he/she passes Go.
     * @param p
     */
    public void advNearestRR(Player p) {
        int nearest = p.nearestRailroad();
        if(nearest < p.currentSpace) {
            p.addOrSubBankBalance(200);
        }
        p.setCurrentSpace(nearest);
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }

    /**
     * For type "advance" - only affects active player (specific to Chance cards).
     * Player advances to the nearest Utility and performs the action on that space.
     * Player receives $200 if he/she passes Go.
     * @param p
     */
    public void advNearestUtil(Player p) {
        int nearest = p.nearestUtility();
        if(nearest < p.currentSpace) {
            p.addOrSubBankBalance(200);
        }
        p.setCurrentSpace(nearest);
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }

}
