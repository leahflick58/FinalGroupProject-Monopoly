public abstract class Card {
    private final String type;
    private final int amount;
    private final String message;

    /**
     * A singular card for the Community/Chance deck of cards
     * @param type relates to the action associated with the type of card (Collect money, pay bank, pay other bankers, go to jail, etc.)
     * @param amount either the amount due/owed, the location of the desired space, or the number of spaces to move depending on the type
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
     * For type "payBank" - only affects active player
     * Subtracts specified amount from Player bank balance
     * @param p active Player
     * @param amount specified dollar amount
     */
    public void payBank(Player p, int amount) {
        p.addOrSubBankBalance(-amount);
    }

    /**
     * For type "payPlayers" - affects all players in game
     * Subtracts specified amount from active Player's bank balances and pays each respective player
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
     * For type "collectBank" - only affects active player
     * Adds specified amount from Player bank balance
     * @param p active Player
     * @param amount specified dollar amount
     */
    public void collectBank(Player p, int amount) {
        p.addOrSubBankBalance(amount);
    }

    /**
     * For type "collectPlayers" - affects all players in game
     * Subtracts specified amount from all other plays and adds the sum to the active Player's bank balance
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
     * For type "goToJail" - only affects active player
     * Sends active Player to Jail space
     * @param p active Player
     */
    public void goToJail(Player p) {
        p.setCurrentSpace(30);  //jail in location 10, Go to Jail location = 30
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }

    /**
     * For type "getOutOfJail" - only affects active player
     * Adds a Get Out of Jail card to the active Player's inventory
     * @param p active Player
     */
    public void getOutOfJail(Player p) {
        p.setNumGetOutOfJail(p.numGetOutOfJail + 1);
    }

    /**
     * For type "homeImprovement" - only affects active player (specific to Chance cards)
     * Player must pay the bank ($115 * their number of hotels) + ($40 * their number of houses)
     * @param p active Player
     */
    public void homeImprovement(Player p) {
        p.addOrSubBankBalance(p.numberHotels() * 115);
        p.addOrSubBankBalance(p.numberHouses() * 40);
    }

    /**
     * For type "advance" - only affects active player (specific to Community cards)
     * Player is moved to the specified location on the card and then does the action on that space
     * @param p active Player
     * @param amount intended space number
     */
    public void advance(Player p, int amount) {
        p.setCurrentSpace(amount);
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }

    /**
     * For type "goBack" - only affects active player (specific to Community cards)
     * Player is moved back a specified number of spaces and then does the action on that space
     * @param p active Player
     * @param amount number of spaces to move back
     */
    public void goBack(Player p, int amount) {
        p.setCurrentSpace(p.getBankBalance() - amount);
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }

    /**
     * Advances player to nearest Railroad
     * @param p
     */
    public void advNearestRR(Player p) {
        p.setCurrentSpace(p.nearestRailroad());
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }

    /**
     * Advances player to nearest Utility
     * @param p
     */
    public void advNearestUtil(Player p) {
        p.setCurrentSpace(p.nearestUtility());
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }

}
