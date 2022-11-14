public abstract class Card {
    private final String type;
    private final int amount;
    private final String message;

    public Card(String type, int amount, String message) {
        this.type = type;
        this.amount = amount;
        this.message = message;
    }


    public String getType() {
        return this.type;
    }

    public int getAmount() {
        return this.amount;
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

    public void advance(Player p, int amount) {
        p.setCurrentSpace(amount);
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }

    public void goBack(Player p, int amount) {
        p.setCurrentSpace(p.getBankBalance() - amount);
        Board.spaces.get(p.getCurrentSpace()).action(p);
    }
}
