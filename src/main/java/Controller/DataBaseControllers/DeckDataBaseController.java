package Controller.DataBaseControllers;

import Model.Deck;

public class DeckDataBaseController extends DataBaseController{

    //name=Username_DeckName
    private static String getDeckPathByGetName(String name){
        return getDecksPath()+"\\"+name+".json";
    }

    public static void createDeck(String user,Deck deck){
        String path=getDeckPathByGetName(user+"_"+deck.getName());
        createFileByPathAndData(path,makeObjectJson(deck));
    }

    public static void changeDeck(String user,Deck deck){
        String path=getDeckPathByGetName(user+"_"+deck.getName());
        rewriteFileOfObjectGson(path, deck);
    }

    public static void removeDeck(String user,String deck){
        String path=getDeckPathByGetName(user+"_"+deck);
        DataBaseController.deleteFile(path);
    }

    public static Deck getDeckByName(String name){
        return (Deck)getObjectByGsonFile(getDeckPathByGetName(name),Deck.class);
    }

}
