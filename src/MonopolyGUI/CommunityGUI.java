package MonopolyGUI;

public class CommunityGUI extends CardGUI {

    /**
     *
     * @param type payBank, payPlayers, collectBank, collectPlayers, goToJail, getOutOfJail, homeImprovement, advance
     * @param amount dollars collected/paid, or in getOutOfJail adds one Get Out of JailGUI card to PlayerGUI's inventory, or
     *               int of destination space.
     *               If goToJail, getOutOfJail, or repairs -> amount = 0
     * @param message note to go along with card
     */
    public CommunityGUI(String type, int amount, String message) {
        super(type, amount, message);
    }
}
