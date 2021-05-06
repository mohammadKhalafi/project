package Controller.MenuControllers;

import Controller.DataBaseControllers.CardDataBaseController;
import Controller.DataBaseControllers.DataBaseController;
import Controller.Utils;
import Model.Card.Card;
import Model.Data.DataForClientFromServer;
import Model.Enums.CardNames;
import Model.Enums.MessageType;

import java.util.regex.Matcher;

public class ImportAndExportMenuController{

    private static ImportAndExportMenuController instance = null;

    private ImportAndExportMenuController() {
    }

    public static ImportAndExportMenuController getInstance() {
        if (instance == null) {
            instance = new ImportAndExportMenuController();
        }
        return instance;
    }

    private DataForClientFromServer importCard(Matcher matcher) {

        matcher.find();

        CardNames cardName = Utils.getCardEnumByName(matcher.group(1));

        Card card = CardDataBaseController.getCardObjectByCardName(cardName);

        if(card == null){
             return new DataForClientFromServer
                    ("invalid card name", MessageType.ERROR);
        }

         return  new DataForClientFromServer(card.toString(), MessageType.SUCCESSFUL);

    }


    private DataForClientFromServer exportCard(Matcher matcher) {

        matcher.find();

        CardNames cardName = Utils.getCardEnumByName(matcher.group(1));

        if (cardName == null) {
            return new DataForClientFromServer
                    ("invalid card name", MessageType.ERROR);

        }

        return new DataForClientFromServer(DataBaseController.readDataFromFile
                (CardDataBaseController.getCardFilePathByCardName(cardName)), MessageType.SUCCESSFUL);


//        System.out.println(DataBaseController.makeObjectJson
//                (CardDataBaseController.getCardObjectByCardName(cardName)));

    }


    public DataForClientFromServer run(String command) {

        if (command.matches("import card .+")) {
            return importCard(Utils.getMatcher(command, "import card (.+)"));
        } else if (command.matches("export card .+")) {
            return exportCard(Utils.getMatcher(command, "export card (.+)"));
        }
        //else
        return Utils.getDataSendToClientForInvalidInput();

    }

}
