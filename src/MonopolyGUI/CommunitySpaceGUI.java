package MonopolyGUI;

import java.util.Objects;

public class CommunitySpaceGUI extends SpacesGUI {

    public CommunitySpaceGUI(int xCoord, int yCoord, String labelString, int rotationDegrees) {
        super(xCoord, yCoord, labelString, rotationDegrees);
    }

    /**
     * Draws a card from the CommunityGUI Chest deck and carries out the assigned action
     * @param p active PlayerGUI
     */
    @Override
    void action(PlayerGUI p) {
        CommunityGUI drawnCard = BoardGUI.communityChests.get(0);
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
            BoardGUI.communityChests.addLast(drawnCard);
        }
        BoardGUI.communityChests.remove(drawnCard);
    }

    @Override
    String spaceName() {
        return "CommunityGUI Chest";
    }

}
