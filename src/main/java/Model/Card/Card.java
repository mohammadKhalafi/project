package Model.Card;

import Model.Enums.CardNames;
import Model.Enums.CardType;


public class Card {
    private int price;
    private CardNames cardName;
    String description;
    CardType cardType;

    public int getPrice() {
        return price;
    }
}
