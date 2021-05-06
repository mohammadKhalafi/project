package Controller.DuelControllers;

import Controller.DataBaseControllers.DeckDataBaseController;
import Controller.DataBaseControllers.UserDataBaseController;
import Controller.DuelControllers.Phases.PhaseController;
import Controller.Utils;
import Model.Gamer;
import Model.User;
import View.GetInput;
import View.Printer.Printer;

import java.util.regex.Matcher;

class DuelController {
    static DuelController instance = null;

    User user;

    private boolean commandIsDone;

    public static DuelController getInstance() {
        if (instance == null) {
            instance = new DuelController();
        }
        return instance;
    }

    private void startDuel(Matcher matcher) {
        if (matcher.matches()) {
            commandIsDone = true;
            User rival = UserDataBaseController.getUserByUsername(matcher.group(1));
            int turn = Integer.parseInt(matcher.group(2));
            if (rival == null) {
                Printer.print("there is no player with this username");
            } else if (user.getActiveDeckName() == null) {
                Printer.print(user.getUsername() + "has no active deck");
            } else if (rival.getActiveDeckName() == null) {
                Printer.print(rival.getUsername() + "has no active deck");
            } else if (!DeckDataBaseController.getDeckByName(user.getUsername() + "_" + user.getActiveDeckName()).isDeckValid()) {
                Printer.print(user.getUsername() + "’s deck is invalid");
            } else if (!DeckDataBaseController.getDeckByName(rival.getUsername() + "_" + rival.getActiveDeckName()).isDeckValid()) {
                Printer.print(rival.getUsername() + "’s deck is invalid");
            } else if (turn != 1 && turn != 3) {
                Printer.print("number of rounds is not supported");
            } else {
                Gamer firstGamer = new Gamer(user);
                Gamer secondGamer = new Gamer(rival);
                GameData gameData = new GameData(firstGamer, secondGamer);
                new PhaseController(gameData).run();
            }
        }
    }

    private void startDuelWithAi(Matcher matcher) {
        if (matcher.find()) {
            commandIsDone = true;
            int turn = Integer.parseInt(matcher.group(1));
            if (user.getActiveDeckName() == null) {
                Printer.print(user.getUsername() + "has no active deck");
            } else if (!DeckDataBaseController.getDeckByName(user.getUsername() + "_" + user.getActiveDeckName()).isDeckValid()) {
                Printer.print(user.getUsername() + "’s deck is invalid");
            } else if (turn != 1 && turn != 3) {
                Printer.print("number of rounds is not supported");
            } else {

            }
        }
    }

    public void handleDuelMenu(User user) {
        this.user = user;
        String command = GetInput.getString();
        while (true) {
            commandIsDone = false;
            startDuel(Utils.getMatcher(command, "duel --new --second-player (\\S) --rounds (\\d)"));
            startDuelWithAi(Utils.getMatcher(command, "duel --new --ai --rounds \\d"));
        }
    }


}
