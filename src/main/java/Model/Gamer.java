package Model;

public class Gamer {
    AllBoards gameBoard = new AllBoards();
    private int lifePoint = 4000;
    private User user;
    private Deck deck;

    public Gamer(User user) {
        this.user = user;
        //this.deck= (Deck) DeckDataBaseController.getDeckByName(user.getActiveDeckName()).clone();
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public String selfToString() {
        return gameBoard.selfToString() +
                user.getNickname() + ":" + lifePoint;
    }

    public String rivalToString() {
        return user.getNickname() + ":" + lifePoint + "\n" +
                gameBoard.rivalToString();
    }

    public void increaseLifePoint(int amount) {
        this.lifePoint += amount;
    }

    public void decreaseLifePoint(int amount) {
        this.lifePoint += amount;
    }

    public AllBoards getGameBoard(){
        return gameBoard;
    }
}
