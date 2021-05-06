package Controller.MenuControllers;

import Controller.DataBaseControllers.UserDataBaseController;
import Controller.Utils;
import Model.Data.DataForClientFromServer;
import Model.Enums.MessageType;
import Model.User;

import java.util.regex.Matcher;

public class LoginMenuController{

    static LoginMenuController instance = null;

    private LoginMenuController() {
    }

    public static LoginMenuController getInstance() {
        if (instance == null) {
            instance = new LoginMenuController();
        }
        return instance;
    }

    public DataForClientFromServer run(String command) {

        if (command.matches("user create " +
                "--username \\w+ --nickname \\w+ --password \\w+")) {

            return manageCreatingAccount(Utils.getMatcher(command, "user create (.+)"));

        } else if (command.matches("user login " +
                "--username \\S+ --password \\S+")) {
            return manageLogin(Utils.getMatcher(command, "user login (.+)"));

        }

        return Utils.getDataSendToClientForInvalidInput();

    }


    private DataForClientFromServer checkUserLoginErrors(String username, String password) {

        DataForClientFromServer dataSendToClient = null;

        if (!UserDataBaseController.doesUserExistWithThisUsername(username)) {
            dataSendToClient = new DataForClientFromServer
                    ("Username and password didn't match!", MessageType.ERROR);
            return dataSendToClient;

        } else if (!isPasswordTrue(username, password)) {
            dataSendToClient = new DataForClientFromServer
                    ("Username and password didn't match!", MessageType.ERROR);
            return dataSendToClient;
        }

        return dataSendToClient;

    }

    private DataForClientFromServer manageCreatingAccount(Matcher matcher) {

        DataForClientFromServer dataSendToClient = null;

        matcher.find();
        String username = Utils.getDataInCommandByKey(matcher.group(1), "--username");
        String password = Utils.getDataInCommandByKey(matcher.group(1), "--password");
        String nickname = Utils.getDataInCommandByKey(matcher.group(1), "--nickname");

        if (Utils.isPasswordWeak(password)) {
            dataSendToClient = new DataForClientFromServer("password is weak", MessageType.ERROR);
            return dataSendToClient;
        }

        return UserDataBaseController.createUser(new User(username, nickname, password));
    }


    private DataForClientFromServer manageLogin(Matcher matcher) {

        DataForClientFromServer dataSendToClient = null;

        matcher.find();

        String username = Utils.getDataInCommandByKey(matcher.group(1), "--username");
        String password = Utils.getDataInCommandByKey(matcher.group(1), "--password");

        dataSendToClient = checkUserLoginErrors(username, password);

        if (dataSendToClient != null) {
            return dataSendToClient;
        }

        return login(UserDataBaseController.getUserByUsername(username));
    }

    private DataForClientFromServer login(User user) {

        DataForClientFromServer dataSendToClient = null;

        dataSendToClient = new DataForClientFromServer
                ("user logged in successfully!", MessageType.SUCCESSFUL);

        return dataSendToClient;
    }

    private boolean isPasswordTrue(String username, String password) {
        return UserDataBaseController.isUserPasswordCorrect(username, password);
    }


}
