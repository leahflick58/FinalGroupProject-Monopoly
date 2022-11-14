public abstract class Card {
    private String type;
    private int amount;
    private String message;

    // abstract payBank, payPlayers, collectBank, collectPlayers, goToJail, getOutOfJail,
    void action(Player p) {};
}
