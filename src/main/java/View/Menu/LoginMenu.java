package View.Menu;

import Model.Data.DataForClientFromServer;
import Model.Data.DataForServerFromClient;
import Model.Enums.MessageType;
import View.GetInput;
import View.Printer.Printer;
import View.Printer.RegisterProfilePrinter;
import View.Utils;

import java.util.regex.Matcher;

public class LoginMenu extends Menu {

    static LoginMenu instance = null;

    public static LoginMenu getInstance() {

        if (instance == null) {
            instance = new LoginMenu();
        }
        return instance;
    }

    private LoginMenu() {
        super("Login Menu");
    }

    public void run() {

        while (true) {

            String command;
            command = GetInput.getString();

            if (command.matches("user create" +
                    "(:?(:? --username \\S+)|(:? --nickname \\S+)|(:? --password \\S+)){3}")) {
                manageCreatingAccount(command);
            } else if (command.matches("user login" +
                    "(:?(:? --username \\S+)|(:? --password \\S+)){2}")) {
                manageLogin(command);
            } else if (command.matches("menu exit")) {
                break;
            } else if (command.startsWith("menu ")) {
                handleMenuOrders(command);
            } else if (command.matches("help")) {
                help();
            } else {
                Printer.printInvalidCommand();
            }
        }

    }

    private void manageLogin(String command) {

        Matcher matcher = Controller.Utils.getMatcher(command, "user login (.+)");

        matcher.find();
        String username = Utils.getDataInCommandByKey(matcher.group(1), "--username");
        String password = Utils.getDataInCommandByKey(matcher.group(1), "--password");

        if (username == null | password == null) {
            Printer.printInvalidCommand();
            return;
        }

        if (!Utils.checkFormatValidity(Utils.getHashMap("username", username,
                "password", password))) {
            return;
        }

        String commandToSendToServer = "user login" +
                " --username " + username + " --password " + password;

        DataForClientFromServer data = sendDataToServer
                (new DataForServerFromClient(commandToSendToServer, username, menuName));

        Printer.print(data.getMessage());

        if (data.getMessageType().equals(MessageType.ERROR)) {
            return;
        }

        login(username);
    }

    private void login(String username) {
        MainMenu.getInstance().run(username);
    }

    private void manageCreatingAccount(String command) {

        Matcher matcher = Utils.getMatcher(command, "user create (.+)");

        matcher.find();
        String username = Utils.getDataInCommandByKey(matcher.group(1), "--username");
        String password = Utils.getDataInCommandByKey(matcher.group(1), "--password");
        String nickname = Utils.getDataInCommandByKey(matcher.group(1), "--nickname");

        if (username == null | password == null | nickname == null) {
            Printer.printInvalidCommand();
            return;
        }

        if (!Utils.checkFormatValidity(Utils.getHashMap("username", username,
                "password", password, "nickname", nickname))) {
            return;
        }

        if (Utils.isPasswordWeak(password)) {
            RegisterProfilePrinter.printPasswordSafetyError();
            return;
        }

        String commandToSendToServer = "user create --username " + username +
                " --nickname " + nickname + " --password " + password;

        DataForClientFromServer data = sendDataToServer
                (new DataForServerFromClient(commandToSendToServer, username, menuName));

        Printer.print(data.getMessage());
    }

    private void help() {
        System.out.print("""
                user create --username [username] --nickname [nickname] --password [password]
                user login --username <username> --password <password>
                 help
                menu show-current
                menu [menu name]
                menu exit               
                """);
    }

}
