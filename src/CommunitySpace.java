import java.util.Objects;

public class CommunitySpace extends Spaces {

    /**
     * Draws a card from the Community Chest deck and carries out the assigned action
     * @param p active Player
     */
    @Override
    void action(Player p) {
        Community drawnCard = Board.communityChests.get(0);
        System.out.println(drawnCard.getMessage());
        int cardAmount = drawnCard.getAmount();
        switch (drawnCard.getType()) {
            case "homeImprovement":
                drawnCard.homeImprovement(p);
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
            case "advance":
                drawnCard.advance(p, cardAmount);
                break;
            case "goBack":
                drawnCard.goBack(p, cardAmount);
                break;
        }
        if (!Objects.equals(drawnCard.getType(), "getOutOfJail")) {
            Board.communityChests.addLast(drawnCard);
        }
        Board.communityChests.remove(drawnCard);
    }

    @Override
    String getName() {
        return "Community Chest";
    }

}
