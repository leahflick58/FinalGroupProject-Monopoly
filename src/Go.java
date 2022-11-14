public class Go extends Spaces {
    /**
     * Go action - pay Player 200 dollars
     * @param p active Player
     */
    @Override
    void action(Player p) {
        p.addOrSubBankBalance(200);
    }
}
