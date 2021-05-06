package Model.Card;

import Model.Enums.CardType;

public class Card {
    private int Price;
    private String Name;
    private String Description;

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    private CardType cardType;

    public String getName() {
        return Name;
    }
}
