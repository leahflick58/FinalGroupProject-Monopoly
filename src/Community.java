public class Community extends Spaces {
    String type;
    // payBank, payPlayers, collectBank, collectPlayers, goToJail, getOutOfJail, repairs, advance
    int amount;

    /**
     *
     * @param type payBank, payPlayers, collectBank, collectPlayers, goToJail, getOutOfJail, repairs, advance
     * @param amount
     */
    public Community(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }
    @Override
    void action(Player p) {

    }
}
