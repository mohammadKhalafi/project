package Controller.DuelControllers.Phases;

import Controller.DuelControllers.GameData;
import Model.Card.Card;
import Model.Gamer;
import View.GetInput;
import View.Printer.Printer;

public class DrawPhase extends AllPhases{
    private GameData gamedata;

    public DrawPhase(GameData gamedata) {
        this.gamedata = gamedata;
        this.phaseName = "Draw Phase";
    }

    public String run() {
        while (true) {
            showPhaseName();
            String command;
            command = GetInput.getString();
            if (command.matches("next phase")) {
                break;
            } else if (command.matches("help")) {
//                help();
            } else {
                Printer.printInvalidCommand();
            }
        }
        addCardToPlayerHand();
        return "";
    }

    private void addCardToPlayerHand() {
        Gamer currentPlayer = gamedata.getFirstGamer();
        Card cardToAddToHand = currentPlayer.getGameBoard().deckZone.removeCard(0);
        Printer.print("new card added to the hand :" + cardToAddToHand.getName());
        currentPlayer.getGameBoard().hand.addCard(cardToAddToHand);
    }

}
