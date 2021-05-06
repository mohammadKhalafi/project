package Model;


import Model.Card.Card;
import Model.Enums.CardNames;

import java.util.ArrayList;

public class Deck {
    private String name;
    private ArrayList<CardNames> mainDeckCards=new ArrayList<>();
    private ArrayList<CardNames> sideDeckCards=new ArrayList<>();

    public Deck(String name) {
        this.name=name;
    }

    public static Deck gsonToDeck(String gson) {
        return null;
    }



    private void setID() {
    }

    public void addCardToMainDeck(CardNames cardName) {
        mainDeckCards.add(cardName);
    }

    public void addCardToSideDeck(CardNames cardName) {
        sideDeckCards.add(cardName);
    }

    public ArrayList<CardNames> getMainDeckCards(){
        return mainDeckCards;
    }
    public ArrayList<CardNames> getSideDeckCards(){
        return sideDeckCards;
    }

    public boolean isDeckValid() {
        return true;
    }

    public String getName(){
        return name;
    }

    public void removeCardFromSideDeck(CardNames cardName) {
        sideDeckCards.remove(cardName);
    }

    public void removeCardFromMainDeck(CardNames cardName) {
        mainDeckCards.remove(cardName);
    }


    public boolean isSideDeckFull(){
        return sideDeckCards.size()>=15;
    }
    public boolean isMainDeckFull(){
        return mainDeckCards.size()>=60;
    }

    public ArrayList<CardNames> getAllCard(){
        ArrayList<CardNames> allCardNames=new ArrayList<>();
        allCardNames.addAll(mainDeckCards);
        allCardNames.addAll(sideDeckCards);
        return allCardNames;
    }

    private boolean canAddThisCard(Card card) {
        return true;
    }

    public boolean isThereThreeCardsInDeck(String CardName){
        int cnt=0;
        for(CardNames cardName:getAllCard()){
            if(cardName.toString().equals(CardName)){
                cnt++;
            }
        }
        return cnt>=3;
    }
    @Override
    public String toString(){
        String temp= name+": main deck "+mainDeckCards.size()+", side deck " +
                sideDeckCards.size()+", ";
        if(isDeckValid()){
            return temp+"valid";
        }
        else{
            return temp+"invalid";
        }
    }

}
