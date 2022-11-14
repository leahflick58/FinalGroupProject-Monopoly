public class Community extends Spaces {
    private final String type;
    // payBank, payPlayers, collectBank, collectPlayers, goToJail, getOutOfJail, repairs, advance
    private final int amount;

    /**
     *
     * @param type payBank, payPlayers, collectBank, collectPlayers, goToJail, getOutOfJail, repairs, advance
     * @param amount dollars collected/paid, or in getOutOfJail adds one Get Out of Jail card to Player's inventory, or int of destination space
     */
    public Community(String type, int amount) {
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
            case "repairs":
                repairs(p);
            case "advance":
                advance(p, this.amount);
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

    public void repairs(Player p) {
        p.addOrSubBankBalance(p.numberHotels() * 100);
        p.addOrSubBankBalance(p.numberHouses() * 25);
    }

    public void advance(Player p, int amount) {
        p.setCurrentSpace(amount);
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }

}
