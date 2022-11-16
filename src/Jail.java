public class Jail extends Spaces{
    /**
     * Jail Action
     * Check if a Player is currently in jail, if not space acts as "Just Visiting" and nothing happens
     * If the player is in jail, they have three options to escape jail:
     * 1. Pay bank $50
     * 2. Roll doubles
     * 3. Play get out of jail free card
     * After three consecutive rounds in jail, if the Player has not escaped jail, they must pay the fine and isInJail is set to false
     * @param p active Player
     */
    @Override
    void action(Player p) {
        // TODO: Finish method
        // like a test at the beginning of the round we check to see if player's current space is Jail?
    }

    @Override
    String getName() {
        return "Jail";
    }
}
