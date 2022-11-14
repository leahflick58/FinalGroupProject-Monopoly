public class Community extends Card {

    /**
     *
     * @param type payBank, payPlayers, collectBank, collectPlayers, goToJail, getOutOfJail, repairs, advance
     * @param amount dollars collected/paid, or in getOutOfJail adds one Get Out of Jail card to Player's inventory, or int of destination space
     *               If goToJail, getOutOfJail, or repairs -> amount = 0
     * @param message note to go along with card
     */
    public Community(String type, int amount, String message) {
        super(type, amount, message);
    }
}
