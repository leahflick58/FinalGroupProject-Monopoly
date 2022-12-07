package MonopolyGUI;

import java.util.Objects;

public class ChanceSpaceGUI extends SpacesGUI {

    public ChanceSpaceGUI(int xCoord, int yCoord, String labelString, int rotationDegrees) {
        super(xCoord, yCoord, labelString, rotationDegrees);
    }

    /**
     * Draws a card from the ChanceGUI deck and carries out the assigned action
     * @param p active PlayerGUI
     */
    @Override
    void action(PlayerGUI p) {
        ChanceGUI drawnCard = BoardGUI.chance.get(0);
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
            BoardGUI.chance.addLast(drawnCard);
        }
        BoardGUI.chance.remove(drawnCard);

    }

    @Override
    String spaceName() {
        return "ChanceGUI";
    }
}
