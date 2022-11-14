public class Chance extends Spaces{
    private final String type;
    // payBank, payPlayers, collectBank, collectPlayers, goToJail, getOutOfJail, homeImprovement
    private final int amount;

    /**
     *
     * @param type payBank, payPlayers, collectBank, collectPlayers, goToJail, getOutOfJail, homeImprovement
     * @param amount dollars collected/paid, or in getOutOfJail adds one Get Out of Jail card to Player's inventory
     */
    public Chance(String type, int amount) {
        this.type = type;
        this.amount = amount;
    }
    @Override
    void action(Player p) {
        switch (this.type) {
            case "payBank":
                payBank(p, this.amount);
            case "payPlayers":
                payPlayers(p, this.amount);
            case "collectBank":
                collectBank(p, this.amount);
            case "collectPlayers":
                collectPlayers(p, this.amount);
            case "goToJail":
                goToJail(p);
            case "getOutOfJail":
                getOutOfJail(p);
            case "homeImprovement":
                homeImprovement(p);
        }
    }

    public void payBank(Player p, int amount) {
        p.addOrSubBankBalance(-amount);
    }

    public void payPlayers(Player p, int amount) {
        for (Player player : GameLoop.players) {
            player.addOrSubBankBalance(amount);
            p.addOrSubBankBalance(-amount);
        }
    }

    public void collectBank(Player p, int amount) {
        p.addOrSubBankBalance(amount);
    }

    public void collectPlayers(Player p, int amount) {
        for (Player player : GameLoop.players) {
            player.addOrSubBankBalance(-amount);
            p.addOrSubBankBalance(amount);
        }
    }

    public void goToJail(Player p) {
        // TODO: Set this space to jail's actual int
        p.setCurrentSpace(0);
    }

    public void getOutOfJail(Player p) {
        p.setHasGetOutOfJail(p.hasGetOutOfJail + 1);
    }

    public void homeImprovement(Player p) {
        p.addOrSubBankBalance(p.numberHotels() * 115);
        p.addOrSubBankBalance(p.numberHouses() * 40);
    }


}
