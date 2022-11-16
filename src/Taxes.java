public class Taxes extends Spaces{
    /**
     * Taxes action - Pay specific amount of taxes to bank
     * @param p active Player
     */
    @Override
    void action(Player p) {
        // Normally there is an option to do 200 or 10% of all owned assets, I got rid of that choice
        p.addOrSubBankBalance(-200);
    }

    @Override
    String getName() {
        return "Taxes";     //will need to split into Income and Luxury
    }
}
