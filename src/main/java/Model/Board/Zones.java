package Model.Board;

import Model.Card.Card;

public abstract class Zones {

    public abstract Card removeCard(int id);

    public abstract void addCard(Card card);

    protected int hashNumber(int i) {
        if(i%2==1){
            return (5-i)/2;
        }
        else{
            return 2+i/2;
        }
    }

    protected int hashRivalNumber(int i) {
        if(i%2==0){
            return (4-i)/2;
        }
        else{
            return i/2+2;
        }
    }
}
