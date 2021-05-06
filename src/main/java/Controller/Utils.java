package Controller;

import Model.Data.DataForClientFromServer;
import Model.Enums.CardNames;
import Model.Enums.MessageType;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static Matcher getMatcher(String matchingStr, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(matchingStr);
    }

    public static String getFirstGroupInMatcher(Matcher matcher) {
        matcher.find();
        return matcher.group(1);
    }

    public static <key, value> HashMap<key, value> getHashMap(Object... keysAndValues) {

        HashMap<key, value> hashMap = new HashMap<>();
        for (int i = 0; i < keysAndValues.length; i += 2) {
            hashMap.put((key) keysAndValues[i], (value) keysAndValues[i + 1]);
        }
        return hashMap;
    }

    public static String getDataInCommandByKey(String command, String key) {
        Matcher matcher = Utils.getMatcher(command, key + " (\\S+)");
        if (matcher.find())
            return matcher.group(1);
        return null;
    }

    public static boolean isPasswordWeak(String password) {

        if (password.length() < 5) {
            return true;
        }
        if (!password.matches(".*?\\d.*")) {
            return true;
        }
        if (!password.matches(".*?[a-z].*")) {
            return true;
        }
        if (!password.matches(".*?[A-Z].*")) {
            return true;
        }
        return false;
    }

    public static DataForClientFromServer checkFormatValidity(HashMap<String, String> userData) {

        for (String dataKey : userData.keySet()) {
            if (!isFormatValid(userData.get(dataKey))) {

                return new DataForClientFromServer
                        (dataKey + " format is not valid", MessageType.ERROR);
            }
        }

        return null;
    }

    public static boolean isFormatValid(String data) {
        return data.matches("\\w+");
    }

    public static CardNames getCardEnumByName(String cardNameStr){

        return CardNames.valueOf(getStringInSnaleUpperCase(cardNameStr));
    }

    public static  String getStringInSnaleUpperCase(String string){
        String tempString  = string;
        tempString = tempString.replaceAll("\\s|,", "_");
        tempString = tempString.toUpperCase();

        return tempString;
    }

    public static DataForClientFromServer getDataSendToClientForInvalidInput(){
        return new DataForClientFromServer("invalid input", MessageType.ERROR);
    }

    public static DataForClientFromServer getDataSendToClientForOperationFailed(){
        return new DataForClientFromServer("operation failed", MessageType.ERROR);
    }

}
