package Controller.MenuControllers;

import Controller.DataBaseControllers.UserDataBaseController;
import Controller.Utils;
import Model.Data.DataForClientFromServer;
import Model.Enums.MessageType;
import Model.User;
import View.Printer.RegisterProfilePrinter;

import java.util.regex.Matcher;

public class ProfileMenuController{

    private static ProfileMenuController instance = null;

    private ProfileMenuController() {

    }

    public static ProfileMenuController getInstance() {

        if (instance == null) {
            instance = new ProfileMenuController();
        }
        return instance;

    }

    public DataForClientFromServer run(User user, String command) {

        if (command.matches("profile change --nickname (\\w+)")) {
            return changeNickName(
                    user, Utils.getMatcher(command, "profile change --nickname (\\w+)"));

        }
        else if (command.matches("profile change --password" +
                "--current (\\w+) --new (\\w+)")) {

            return changePassword(
                    user,
                    Utils.getMatcher(command, "profile change --password" +
                        "--current (\\w+) --new (\\w+)"));
        }

        return Utils.getDataSendToClientForInvalidInput();

    }


    private DataForClientFromServer changeNickName(User user, Matcher matcher) {

        matcher.find();
        String newNickname = matcher.group(1);

        if (UserDataBaseController.isNickNameRepetitious(newNickname)) {

            return new DataForClientFromServer(
                    "user with nickname " + newNickname + " already exists", MessageType.ERROR);

        } else {
            RegisterProfilePrinter.printNicknameChanged();

            if (UserDataBaseController.changeNickname(user, newNickname))
                return new DataForClientFromServer("", MessageType.SUCCESSFUL);
            else
                return Utils.getDataSendToClientForOperationFailed();
        }
    }

    private DataForClientFromServer changePassword(User user, Matcher matcher) {

        matcher.find();
        String currentPassword = Utils.getDataInCommandByKey(matcher.group(1), "--current");
        String newPassword = Utils.getDataInCommandByKey(matcher.group(1), "--new");

        if (!isPasswordTrue(user.getUsername(), currentPassword)) {

            new DataForClientFromServer("current password is invalid", MessageType.ERROR);

        } else if (currentPassword.matches(newPassword)) {
            new DataForClientFromServer("please enter a new password", MessageType.ERROR);

        } else if (Utils.isPasswordWeak(newPassword)) {
            new DataForClientFromServer("password is weak", MessageType.ERROR);

        }

        if(UserDataBaseController.changePassword(user, newPassword))
            return new DataForClientFromServer("password changed successfully!",
                    MessageType.SUCCESSFUL);
        else
            return Utils.getDataSendToClientForOperationFailed();

    }

    private boolean isPasswordTrue(String username, String password) {
        return UserDataBaseController.isUserPasswordCorrect(username, password);
    }
}
