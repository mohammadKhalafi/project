package View.Menu;

import Controller.ClientDataController;
import Controller.Utils;
import Model.Data.DataForClientFromServer;
import Model.Data.DataForServerFromClient;
import View.Printer.Printer;
import View.Printer.RegisterProfilePrinter;

import java.util.HashMap;
import java.util.regex.Matcher;

public class Menu {

    protected static final HashMap<String, Integer> menuLevels;

    protected final String menuName;

    protected String username;

    static {

        menuLevels = new HashMap<>();

        menuLevels.put("Login Menu", 0);
        menuLevels.put("Main Menu", 1);
        menuLevels.put("Duel Menu", 2);
        menuLevels.put("Deck Menu", 2);
        menuLevels.put("Scoreboard Menu", 2);
        menuLevels.put("Profile Menu", 2);
        menuLevels.put("Shop Menu", 2);
        menuLevels.put("Import/Export Menu", 2);

    }

    public Menu(String menuName) {
        this.menuName = menuName;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected void handleMenuOrders(String command) {

        if (command.matches("menu show-current")) {
            showCurrentMenu();
        } else if (command.matches("menu enter (.+)")) {
            enterOtherMenu(Utils.getFirstGroupInMatcher(Utils.getMatcher(command, "menu enter (.+)")));
        } else {
            Printer.printInvalidCommand();
        }

    }


    private void enterOtherMenu(String menuName) {

        if (!isMenuNameValid(menuName)) {
            Printer.print("invalid menu name");
            return;
        }
        if (!canEnterTheMenu(menuName)) {
            RegisterProfilePrinter.printCanNotNavigate();
            return;
        }

        if (menuName.matches("Profile Menu")) {
            ProfileMenu.getInstance().run(username);
        } else if (menuName.matches("Deck Menu")) {
            DeckMenu.getInstance().run(username);
        } else if (menuName.matches("Scoreboard Menu")) {
            ScoreBoardMenu.getInstance().run();
        } else if (menuName.matches("Shop Menu")) {
//            ShopMenu.getInstance().run(user);
        } else if (menuName.matches("Import/Export Menu")) {
            ImportAndExportMenu.getInstance().run();
        } else if (menuName.matches("Main Menu")) {
            MainMenu.getInstance().run(username);
        }

    }

    private boolean canEnterTheMenu(String menuName) {
        return menuLevels.get(menuName) - menuLevels.get(this.menuName) == 1;
    }

    private boolean isMenuNameValid(String menuName) {
        return menuLevels.containsKey(menuName);
    }

    private void showCurrentMenu() {
        Printer.print(menuName);
    }

    protected DataForClientFromServer sendDataToServer(DataForServerFromClient data) {

        //function of server
        return ClientDataController.handleMessageOfClientAndGetFeedback(data);
    }

    protected void sendCommandToServer1(Matcher matcher){

        matcher.find();

        DataForClientFromServer data = sendDataToServer
                (new DataForServerFromClient(matcher.group(0), username, menuName));

        Printer.print(data.getMessage());
    }

    protected void sendCommandToServer2(Matcher matcher, String name){

        matcher.find();

        String tempName = matcher.group(1);


        if (!View.Utils.isFormatValid(tempName)) {
            Printer.print(name + " name format is invalid");
            return;
        }

        DataForClientFromServer data = sendDataToServer
                (new DataForServerFromClient(matcher.group(0), username, menuName));

        Printer.print(data.getMessage());
    }
}
