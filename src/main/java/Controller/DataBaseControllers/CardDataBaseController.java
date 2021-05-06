package Controller.DataBaseControllers;

import Model.Card.Card;
import Model.Enums.CardNames;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;

public class CardDataBaseController extends DataBaseController {

    public static String getCardFilePathByCardName(CardNames cardName) {
        return DataBaseController.getCardsPath() + "\\" + cardName + ".json";
    }

    public static Card getCardObjectByCardName(CardNames cardName){
        return (Card) getObjectByGsonFile(getCardFilePathByCardName(cardName),
                getClassByClassName(cardName.getClassName()));
    }

    public static String getCardNames(){

        StringBuilder returnedData = new StringBuilder();

        File[] cardFiles = getFilesOfOneFolder(getCardsPath());
        String tempData;

        for(File file : cardFiles){
            tempData = readDataFromFile(file);
            JsonObject jsonObjectAlt = JsonParser.parseString(tempData).getAsJsonObject();
            JsonElement nameJson = jsonObjectAlt.get("Name");
            JsonElement priceJson = jsonObjectAlt.get("Price");
            returnedData.append(nameJson.toString() + ":" + priceJson.toString() + "\n");
        }
        returnedData.deleteCharAt(returnedData.length() - 1);
        return returnedData.toString();
    }


}
