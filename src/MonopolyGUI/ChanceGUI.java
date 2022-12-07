package MonopolyGUI;

public class ChanceGUI extends CardGUI {

    /**
     *
     * @param type payBank, payPlayers, collectBank, collectPlayers, goToJail, getOutOfJail, homeImprovement,
     *             advNearestRR, advNearestUtil
     * @param amount dollars collected/paid, or in getOutOfJail adds one Get Out of JailGUI card to PlayerGUI's inventory
     *               If goToJail, getOutOfJail, or homeImprovement -> amount = 0
     * @param message note to go along with card
     */
    public ChanceGUI(String type, int amount, String message) {
        super(type, amount, message);
    }


}
