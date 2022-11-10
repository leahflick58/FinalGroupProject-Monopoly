public class Taxes extends Spaces{
    @Override
    void action(Player p) {
        // Normally there is an option to do 200 or 10% of all owned assets, I got rid of that choice
        p.addOrSubBankBalance(-200);
    }
}
