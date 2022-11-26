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
            case "goBack":
                drawnCard.goBack(p, cardAmount);
            case "advance":
                drawnCard.advance(p, cardAmount);
                break;
            case "payBank":
                drawnCard.payBank(p, cardAmount);
                break;
            case "payPlayers":
                drawnCard.payPlayers(p, cardAmount);
                break;
            case "collectBank":
                drawnCard.collectBank(p, cardAmount);
                break;
            case "collectPlayers":
                drawnCard.collectPlayers(p, cardAmount);
                break;
            case "goToJail":
                drawnCard.goToJail(p);
                break;
            case "getOutOfJail":
                drawnCard.getOutOfJail(p);
                break;
            case "homeImprovement":
                drawnCard.homeImprovement(p);
                break;
            case "advNearestRR":
                drawnCard.advNearestRR(p);
                break;
            case "advNearestUtil":
                drawnCard.advNearestUtil(p);
                break;
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
