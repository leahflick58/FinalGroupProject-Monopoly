public class Chance extends Spaces{
    private String type;
    // payBank, payPlayers, collectBank, collectPlayers, goToJail, getOutOfJail, homeImprovement
    private int amount;
    public Chance(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }
    @Override
    void action(Player p) {

    }
}
