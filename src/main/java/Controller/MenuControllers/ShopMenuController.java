package Controller.MenuControllers;

import Controller.DataBaseControllers.CardDataBaseController;
import Controller.Utils;
import Model.Card.Card;
import Model.Data.DataForClientFromServer;
import Model.Enums.MessageType;
import Model.User;
import View.GetInput;

import java.util.regex.Matcher;

public class ShopMenuController {


    private ShopMenuController() {
    }

    private static ShopMenuController instance = null;

    public static ShopMenuController getInstance() {

        if (instance == null) {
            instance = new ShopMenuController();
        }
        return instance;
    }


    public DataForClientFromServer run(User user) {

        String command;

        command = GetInput.getString();
        if (command.matches("shop buy (\\S+)")) {
            return manageBuyCards(user, Utils.getMatcher(command, "shop buy (\\S+)"));
        }
        else if (command.matches("shop show --all")){
            return showAllCards();
        }
        return Utils.getDataSendToClientForInvalidInput();

    }

    private DataForClientFromServer manageBuyCards(User user, Matcher matcher) {

        matcher.find();
        String cardName = matcher.group(1);

        Card card = CardDataBaseController.getCardObjectByCardName(Utils.getCardEnumByName(cardName));

        if (card == null) {
            return new DataForClientFromServer("there is no card with this name",
                    MessageType.ERROR);
        }

        if(user.getCredit() < card.getPrice()){
            return new DataForClientFromServer("not enough money", MessageType.ERROR);
        }

        user.addCard(Utils.getCardEnumByName(cardName));
        return new DataForClientFromServer("you successfully bought the card",
                MessageType.SUCCESSFUL);

    }

    private DataForClientFromServer showAllCards() {

        return new DataForClientFromServer(CardDataBaseController.getCardNames(), MessageType.Card);
    }
}
