package Model.Board;

import Model.Card.Card;
import Model.Enums.CardMod;

import java.util.ArrayList;

public class MonsterCardZone extends Zones {
    private ArrayList<String> cardsInMonsterZone = new ArrayList<>();
    private ArrayList<CardMod> cardsMods = new ArrayList<>();

    {
        for (int i = 0; i < 5; i++) {
            cardsInMonsterZone.add(null);
            cardsMods.add(CardMod.EMPTY);
        }
    }


    public String getCardById(int id, boolean selfRequest) {
        if (selfRequest) {
            return cardsInMonsterZone.get(hashNumber(id));
        } else {
            return cardsInMonsterZone.get(hashRivalNumber(id));
        }
    }

    public Card removeCard(int id) {

        return null;
    }

    public void addCard(Card card) {


    }

    public void changeModById(int id, CardMod cardMod) {


    }
    @Override
    public String toString(){
        StringBuilder temp= new StringBuilder("\t");
        for(int i=0;i<5;i++){
            if(cardsMods.get(i).equals(CardMod.EMPTY)){
                temp.append("E\t");
            }
            else if(cardsMods.get(i).equals(CardMod.DEFENSIVE_HIDDEN)){
                temp.append("DH\t");
            }
            else if(cardsMods.get(i).equals(CardMod.DEFENSIVE_OCCUPIED)){
                temp.append("DO\t");
            }
            else{
                temp.append("OO\t");
            }
        }
        return temp.toString();
    }


}
