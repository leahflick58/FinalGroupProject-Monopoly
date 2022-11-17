import java.util.Objects;

public class ChanceSpace extends Spaces {

    /**
     * Draws a card from the Chance deck and carries out the assigned action
     * @param p active Player
     */
    @Override
    void action(Player p) {
        Chance drawnCard = Board.chance.get(0);
        int cardAmount = drawnCard.getAmount();
        System.out.println(drawnCard.getMessage());
        switch (drawnCard.getType()) {
            case "payBank":
                drawnCard.payBank(p, cardAmount);
            case "payPlayers":
                drawnCard.payPlayers(p, cardAmount);
            case "collectBank":
                drawnCard.collectBank(p, cardAmount);
            case "collectPlayers":
                drawnCard.collectPlayers(p, cardAmount);
            case "goToJail":
                drawnCard.goToJail(p);
            case "getOutOfJail":
                drawnCard.getOutOfJail(p);
            case "homeImprovement":
                drawnCard.homeImprovement(p);
        }
        if (!Objects.equals(drawnCard.getType(), "getOutOfJail")) {
            Board.chance.addLast(drawnCard);
            Board.chance.remove(drawnCard);
        }

    }

    @Override
    String getName() {
        return "Chance";
    }
}
