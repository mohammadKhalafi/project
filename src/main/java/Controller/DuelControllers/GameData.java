package Controller.DuelControllers;

import Model.Card.Card;
import Model.Gamer;

import java.util.ArrayList;

public class GameData {
    ArrayList<Gamer> gamers = new ArrayList<>();
    private Card selectedCard;

    public GameData(Gamer firstGamer, Gamer secondGamer) {
        gamers.add(firstGamer);
        gamers.add(secondGamer);
    }

    public boolean isGameOver() {
        return false;
    }

    public Card getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard = selectedCard;
    }

    public Gamer getFirstGamer(){
        return gamers.get(0);
    }

    public Gamer getSecondGamer(){
        return gamers.get(1);
    }

}
