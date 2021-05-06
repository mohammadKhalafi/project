package View.Menu;

import Model.Data.DataForClientFromServer;
import Model.Data.DataForServerFromClient;
import Model.User;
import View.GetInput;
import View.Printer.Printer;
import View.Utils;

import java.util.regex.Matcher;

public class DeckMenu extends Menu {

    boolean commandIsDone = false;

    private String username;


    private User user;

    private String getPath(String deckName) {
        return user.getUsername() + "_" + deckName;
    }

    private static DeckMenu instance = null;

    private DeckMenu() {
        super("Deck Menu");
    }

    public static DeckMenu getInstance() {
        if (instance == null)
            return new DeckMenu();
        return instance;
    }

    public void run(String username) {
        this.username = username;

        String command = GetInput.getString();

        while (!command.equals("menu exit")) {

            commandIsDone = false;

            createDeck(Utils.getMatcher(command, "deck create (\\S+)"));

            deleteDeck(Utils.getMatcher(command, "deck delete (\\S+)"));

            setActiveDeck(Utils.getMatcher(command, "deck set-active (\\S+)"));

            addCardToDeck(Utils.getMatcher(command,
                    "deck add-card" +
                            "(?=.*?--side)(?=.*?--card \\S+)(?=.*--deck \\S+)" +
                            "( --((card \\S+)|(deck \\S+)|(side))){3}"), true);

            addCardToDeck(Utils.getMatcher(command,
                    "deck add-card" +
                            "(?=.*?--card \\S+)(?=.*--deck \\S+)" +
                            "( --((card \\S+)|(deck \\S+))){2}"), false);

            deleteCardFromDeck(Utils.getMatcher(command, "deck rm-card --card (\\S+) --deck (\\S+)" +
                    " (--side|)"));

            showUserDecks(Utils.getMatcher(command, "deck show --all"));

            showSingleDeck(Utils.getMatcher(command, "deck show(?=.*?--deck-name \\S+)(?=.*--side)" +
                            "(( --deck-name (\\S+))|( --side)){2}"),
                    true);

            showSingleDeck(Utils.getMatcher(command, "deck show --deck-name (\\S+)"),
                    false);


            showAllCards(Utils.getMatcher(command, "deck show --cards"));

            handleCommandsStartWithMenu(command);

            help(command);

            if (!commandIsDone) {
                Printer.printInvalidCommand();
            }

            command = GetInput.getString();
        }

    }

    private void createDeck(Matcher matcher) {

        if (matcher.matches()) {


            commandIsDone = true;

            sendCommandToServer2(matcher, "deck");
        }

    }

    private void deleteDeck(Matcher matcher) {
        if (matcher.matches()) {


            commandIsDone = true;

            sendCommandToServer2(matcher, "deck");

        }

    }

    private void setActiveDeck(Matcher matcher) {

        if (matcher.matches()) {

            commandIsDone = true;


            sendCommandToServer2(matcher, "deck");
        }

    }

    private void addCardToDeck(Matcher matcher, boolean isSideDeck) {

        if (matcher.matches()) {


            commandIsDone = true;

            String command = matcher.group(0);

            Matcher matcher1 = Utils.getMatcher(command, "deck add-card (.+)");

            matcher1.find();

            String cardName = Utils.getDataInCommandByKey(matcher1.group(1), "--card");
            String deckName = Utils.getDataInCommandByKey(matcher1.group(1), "--deck");

            if (!Utils.checkFormatValidity(Utils.getHashMap(
                    "cardname", cardName, "deckName", deckName))) {
                return;
            }

            String commandToSendToServer = "deck add-card --card " + cardName + " --deck " + deckName;
            if (isSideDeck) {
                commandToSendToServer = commandToSendToServer + " --side";
            }

            DataForClientFromServer data = sendDataToServer
                    (new DataForServerFromClient(commandToSendToServer, username, menuName));

            Printer.print(data.getMessage());
        }
    }


    private void deleteCardFromDeck(Matcher matcher) {

        if (matcher.matches()) {


            commandIsDone = true;

            String command = matcher.group(0);

            Matcher matcher1 = Utils.getMatcher(command, "deck rm-card (.+)");

            matcher1.find();

            String cardName = Utils.getDataInCommandByKey(matcher1.group(1), "--card");
            String deckName = Utils.getDataInCommandByKey(matcher1.group(1), "--deck");

            boolean isSideDeck = matcher1.group(1).contains("--side");

            if (!Utils.checkFormatValidity(Utils.getHashMap(
                    "cardname", cardName, "deckName", deckName))) {
                return;
            }

            String commandToSendToServer = "ddeck rm --card " + cardName + " --deck " + deckName;

            if (isSideDeck) {
                commandToSendToServer = commandToSendToServer + " --side";
            }

            DataForClientFromServer data = sendDataToServer
                    (new DataForServerFromClient(commandToSendToServer, username, menuName));

            Printer.print(data.getMessage());
        }
    }

    private void showUserDecks(Matcher matcher) {

        if (!matcher.matches()) {

            commandIsDone = true;

            sendCommandToServer1(matcher);
        }

    }


    private void showSingleDeck(Matcher matcher, boolean isDeckSideDeck) {

        if (matcher.matches()) {


            commandIsDone = true;

            Matcher matcher1 = Utils.getMatcher(matcher.group(0),
                    "deck show (.*)");

            matcher1.find();

            String name = Utils.getDataInCommandByKey(matcher1.group(1), "--deck-name");

            if (!Utils.isFormatValid(name)) {
                Printer.print("name format is invalid");
                return;
            }

            String commandToSendToServer = "deck show --deck-name " + name;

            if (isDeckSideDeck) {
                commandToSendToServer = commandToSendToServer + " --side";
            }

            DataForClientFromServer data = sendDataToServer
                    (new DataForServerFromClient(commandToSendToServer, username, menuName));

            Printer.print(data.getMessage());
        }

    }

    private void showAllCards(Matcher matcher) {

        if (!matcher.matches()) {
            return;
        }

        commandIsDone = true;

        sendCommandToServer1(matcher);

    }

    private void handleCommandsStartWithMenu(String command) {
        if (command.startsWith("menu ")) {
            commandIsDone = true;
            handleMenuOrders(command);
        }
    }

    private void help(String command){
        if(command.equals("help")){
            commandIsDone=true;
            System.out.println("""
                    deck create <deck name>
                    deck delete <deck name>
                    deck set-activate <deck name>
                    deck add-card --card <card name> --deck <deck name> --side(optional)
                    deck rm-card --card <card name> --deck <deck name> --side(optional)
                    deck show --all
                    deck show --deck-name <deck name> --side(Opt)
                    deck show --cards
                    help
                    menu show-current
                    menu [menu name]
                    menu exit                    
                    """);
        }
    }


}
