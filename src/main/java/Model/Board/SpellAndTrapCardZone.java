package Model.Board;

import Model.Card.Card;
import Model.Enums.SpellCardMods;

import java.util.ArrayList;

public class SpellAndTrapCardZone extends Zones {

    ArrayList<String> allCards=new ArrayList<>();
    ArrayList<SpellCardMods> spellMods=new ArrayList<>();


    {
        for(int i=0;i<5;i++){
            allCards.add(null);
            spellMods.add(SpellCardMods.EMPTY);
        }
    }

    public Card getCard(int id) {

        return null;
    }

    public Card removeCard(int id) {

        return null;
    }

    public void addCard(Card card) {


    }

    @Override
    public String toString(){
        StringBuilder stringBuilder=new StringBuilder("\t");
        for(int i=0;i<5;i++){
            if(spellMods.get(i).equals(SpellCardMods.EMPTY)){
                stringBuilder.append("E\t");
            }
            else if(spellMods.get(i).equals(SpellCardMods.HIDDEN)){
                stringBuilder.append("H\t");
            }
            else{
                stringBuilder.append("O\t");
            }

        }
        return stringBuilder.toString();
    }
}
