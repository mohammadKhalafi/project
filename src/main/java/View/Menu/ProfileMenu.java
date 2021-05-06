package View.Menu;

import Model.Data.DataForServerFromClient;
import View.GetInput;
import View.Printer.Printer;
import View.Utils;

import java.util.regex.Matcher;

public class ProfileMenu extends Menu{

    private static ProfileMenu instance = null;

    private ProfileMenu() {
        super("Profile Menu");
    }

    public static ProfileMenu getInstance() {

        if (instance == null) {
            instance = new ProfileMenu();
        }
        return instance;

    }
    public void run(String username) {
        setUsername(username);

        while (true) {
            String command;
            command = GetInput.getString();
            if (command.matches("profile change --nickname \\S+")) {
                changeNickName(command, Utils.getMatcher(command, "profile change (.+)"));
            } else if (command.matches("profile change" +
                    "(?=.*?--password)(?=.*?--current \\S+)(?=.*--new \\S+)" +
                    "( --((current \\S+)|(new \\S+)|(password))){3}")) {
                changePassword(Utils.getMatcher(command, "profile change (.+)"));
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


    private void changeNickName(String command, Matcher matcher) {

        String nickname = Utils.getDataInCommandByKey(matcher.group(1), "--nickname");

        if(!Utils.checkFormatValidity(Utils.getHashMap
                ("nickname", nickname))){
            return;
        }

        Printer.print(sendDataToServer(
                new DataForServerFromClient(command ,username, menuName)).getMessage());
    }

    private void changePassword(Matcher matcher) {

        matcher.find();
        String currentPassword = Utils.getDataInCommandByKey(matcher.group(1), "--current");
        String newPassword = Utils.getDataInCommandByKey(matcher.group(1), "--new");

        if(Utils.checkFormatValidity(
                Utils.getHashMap(
                        "password", currentPassword, "newPassword", newPassword))){
            return;
        }

        Printer.print(sendDataToServer(
                new DataForServerFromClient("profile change --password" +
                        "--current " + currentPassword + " --new " + newPassword
                        ,username, menuName)).getMessage());

    }


    private void help() {
        System.out.print("""
                profile change --nickname [nickname]
                profile change --password --current [current password] --new [new password]
                help
                menu show-current
                menu [menu name]
                menu exit
                """);

    }


}
