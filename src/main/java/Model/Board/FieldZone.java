package Model.Board;

import Model.Card.Card;

public class FieldZone extends Zones {

    private Card card;

    public Card getCard(int id) {

        return null;
    }

    public Card removeCard(int id) {

        return null;
    }

    public void addCard(Card card) {


    }

    public String toString(){
        if(card==null){
            return "E";
        }
        else{
            return "O";
        }
    }
}
