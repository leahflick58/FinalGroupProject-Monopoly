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
            case "advance":
                drawnCard.advance(p, cardAmount);
            case "goBack":
                drawnCard.goBack(p, cardAmount);
        }
        if (!Objects.equals(drawnCard.getType(), "getOutOfJail")) {
            Board.communityChests.addLast(drawnCard);
            Board.communityChests.remove(drawnCard);
        }
    }

    @Override
    String getName() {
        return "Community Chest";
    }

}
