package Model;

import Model.Enums.CardNames;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeSet;

public class User {

    private String username;
    private String password;
    private String nickname;
    private TreeSet<String> deckNames = new TreeSet<>();
    private String activeDeckName;
    private int score;
    private int credit;
    private ArrayList<CardNames> cards = new ArrayList<>();

    public User(String username, String nickName, String password) {

        setUsername(username);
        setPassword(password);
        setNickname(nickName);

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public TreeSet<String> getDeckNames() {
        return deckNames;
    }

    public void setDeckIDs(TreeSet<String> deckNames) {
        this.deckNames = deckNames;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickName) {
        this.nickname = nickName;
    }

    public void addCard(CardNames cardName){
        cards.add(cardName);
    }
    public void removeCard(CardNames cardName){
        cards.remove(cardName);
    }

    public void removeDeck(String name){
        deckNames.remove(name);
    }

    public void addDeck(String name){
        deckNames.add(name);
    }

    public String getActiveDeckName(){
        return activeDeckName;
    }

    public void setActiveDeckName(String activeDeckName){
        this.activeDeckName=activeDeckName;
    }

    public ArrayList<CardNames> getCards(){
        return cards;
    }

    public static User getUserByGson(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
            return gson.fromJson(reader, User.class);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public boolean isPasswordCorrect(String password) {
        return password.matches(this.password);
    }
    @Override
    public String toString(){
        return nickname+": "+score;
    }
}
