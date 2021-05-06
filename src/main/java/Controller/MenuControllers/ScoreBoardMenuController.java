package Controller.MenuControllers;

import Controller.DataBaseControllers.UserDataBaseController;
import Controller.Utils;
import Model.Data.DataForClientFromServer;
import Model.Enums.MessageType;
import Model.User;

import java.util.Comparator;
import java.util.TreeSet;


public class ScoreBoardMenuController {

    static ScoreBoardMenuController instance = null;

    private ScoreBoardMenuController() {
    }

    public static ScoreBoardMenuController getInstance() {

        if (instance == null) {
            instance = new ScoreBoardMenuController();
        }
        return instance;
    }

    public DataForClientFromServer run(String command) {

        if (command.matches("scoreboard show")) {
            return showScores();
        }
        return Utils.getDataSendToClientForInvalidInput();


    }

    private TreeSet<User> allUsers = new TreeSet<>(new UserComp());

    private DataForClientFromServer showScores() {

        StringBuilder returnedStr = new StringBuilder();
        gatherAllUsers();
        int rank = 0;
        int currentScore = -10;
        for (User user : allUsers) {
            if (user.getScore() != currentScore) {
                currentScore = user.getScore();
                rank++;
            }
            returnedStr.append(rank + "-" + user.toString() + "\n");
        }
        return new DataForClientFromServer(returnedStr.toString(), MessageType.SCORE);

    }

    private void gatherAllUsers() {
        allUsers.clear();
        allUsers.addAll(UserDataBaseController.allUsers());
    }

}


class UserComp implements Comparator<User> {

    public int compare(User firstUser, User secondUser) {
        int scoreComp = Integer.compare(firstUser.getScore(), secondUser.getScore());
        int nickNameComp = firstUser.getUsername().compareTo(secondUser.getUsername());
        if (scoreComp == 0) {
            return nickNameComp;
        } else {
            return -scoreComp;
        }
    }

}