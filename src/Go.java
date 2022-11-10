public class Go extends Spaces {
    @Override
    void action(Player p) {
        p.addOrSubBankBalance(200);
    }
}
