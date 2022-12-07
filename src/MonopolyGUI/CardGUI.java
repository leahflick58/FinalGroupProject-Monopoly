package MonopolyGUI;

public abstract class CardGUI {
    private final String type;
    private final int amount;
    private final String message;

    /**
     * A singular card for the CommunityGUI/ChanceGUI deck of cards.
     * @param type relates to the action associated with the type of card (Collect money, pay bank, pay other bankers,
     *             go to jail, etc.)
     * @param amount either the amount due/owed, the location of the desired space, or the number of spaces to move
     *               depending on the type
     * @param message note written on the card for the user to see
     */
    public CardGUI(String type, int amount, String message) {
        this.type = type;
        this.amount = amount;
        this.message = message;
    }

    /**
     * @return CardGUI type
     */
    public String getType() {
        return this.type;
    }

    /**
     * @return CardGUI amount
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * @return CardGUI message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * For type "payBank" - only affects active player.
     * Subtracts specified amount from PlayerGUI bank balance.
     * @param p active PlayerGUI
     * @param amount specified dollar amount
     */
    public void payBank(PlayerGUI p, int amount) {
        p.addOrSubBankBalance(-amount);
    }

    /**
     * For type "payPlayers" - affects all players in game.
     * Subtracts specified amount from active PlayerGUI's bank balances and pays each respective player.
     * @param p active PlayerGUI
     * @param amount specified dollar amount
     */
    public void payPlayers(PlayerGUI p, int amount) {
        for (PlayerGUI player : GameLoopGUI.players) {
            player.addOrSubBankBalance(amount);
            p.addOrSubBankBalance(-amount);
        }
    }

    /**
     * For type "collectBank" - only affects active player.
     * Adds specified amount from PlayerGUI bank balance.
     * @param p active PlayerGUI
     * @param amount specified dollar amount
     */
    public void collectBank(PlayerGUI p, int amount) {
        p.addOrSubBankBalance(amount);
    }

    /**
     * For type "collectPlayers" - affects all players in game.
     * Subtracts specified amount from all other plays and adds the sum to the active PlayerGUI's bank balance.
     * @param p active PlayerGUI
     * @param amount specified dollar amount
     */
    public void collectPlayers(PlayerGUI p, int amount) {
        for (PlayerGUI player : GameLoopGUI.players) {
            player.addOrSubBankBalance(-amount);
            p.addOrSubBankBalance(amount);
        }
    }

    /**
     * For type "goToJail" - only affects active player.
     * Sends active PlayerGUI to JailGUI space (10) with 3 turns in jail.
     * @param p active PlayerGUI
     */
    public void goToJail(PlayerGUI p) {
        p.setCurrentSpace(10);
        p.isInJail = true;
        p.setTurnsInJail(3);
        BoardGUI.spaces.get(p.getCurrentSpace()).action(p);
    }

    /**
     * For type "getOutOfJail" - only affects active player.
     * Adds a Get Out of JailGUI card to the active PlayerGUI's inventory.
     * @param p active PlayerGUI
     */
    public void getOutOfJail(PlayerGUI p) {
        p.setNumGetOutOfJail(1);
    }

    /**
     * For type "homeImprovement" - only affects active player (specific to ChanceGUI cards).
     * PlayerGUI must pay the bank ($115 * their number of hotels) + ($40 * their number of houses).
     * @param p active PlayerGUI
     */
    public void homeImprovement(PlayerGUI p) {
        p.addOrSubBankBalance(p.numberHotels() * 115);
        p.addOrSubBankBalance(p.numberHouses() * 40);

        System.out.println("Number of Houses: " + p.numberHouses());
        System.out.println("Number of Hotels: " + p.numberHotels());
        System.out.println("Bank balance: $" + p.bankBalance);
    }

    /**
     * For type "advance" - only affects active player (specific to CommunityGUI cards).
     * PlayerGUI is moved to the specified location on the card and then does the action on that space.
     * PlayerGUI receives $200 if he/she passes GoGUI.
     * @param p active PlayerGUI
     * @param space intended space number
     */
    public void advance(PlayerGUI p, int space) {
        if((space < p.currentSpace) && (space != 0)) {      //otherwise, will add $200 twice
            p.addOrSubBankBalance(200);
        }
        p.setCurrentSpace(space);
        BoardGUI.spaces.get(p.getCurrentSpace()).action(p);
    }

    /**
     * For type "goBack" - only affects active player (specific to CommunityGUI cards).
     * PlayerGUI is moved back a specified number of spaces and then does the action on that space.
     * @param p active PlayerGUI
     * @param amount number of spaces to move back
     */
    public void goBack(PlayerGUI p, int amount) {
        p.setCurrentSpace(p.getCurrentSpace() - amount - 1);
        System.out.println("Landed on " +
                BoardGUI.spaces.get(p.getCurrentSpace()).spaceName());
        BoardGUI.spaces.get(p.getCurrentSpace()).action(p);
    }

    /**
     * For type "advance" - only affects active player (specific to ChanceGUI cards).
     * PlayerGUI advances to the nearest Railroad and performs the action on that space.
     * PlayerGUI receives $200 if he/she passes GoGUI.
     * @param p active PlayerGUI
     */
    public void advNearestRR(PlayerGUI p) {
        int nearest = p.nearestRailroad();
        if(nearest < p.currentSpace) {
            p.addOrSubBankBalance(200);
        }
        p.setCurrentSpace(nearest);
        BoardGUI.spaces.get(p.getCurrentSpace()).action(p);
    }

    /**
     * For type "advance" - only affects active player (specific to ChanceGUI cards).
     * PlayerGUI advances to the nearest Utility and performs the action on that space.
     * PlayerGUI receives $200 if he/she passes GoGUI.
     * @param p active PlayerGUI
     */
    public void advNearestUtil(PlayerGUI p) {
        int nearest = p.nearestUtility();
        if(nearest < p.currentSpace) {
            p.addOrSubBankBalance(200);
        }
        p.setCurrentSpace(nearest);
        BoardGUI.spaces.get(p.getCurrentSpace()).action(p);
    }

}
