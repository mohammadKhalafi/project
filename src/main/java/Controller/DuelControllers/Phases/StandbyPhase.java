package Controller.DuelControllers.Phases;

import Controller.DuelControllers.GameData;
import View.GetInput;
import View.Printer.Printer;

public class StandbyPhase extends AllPhases{
    private GameData gamedata;

    public StandbyPhase(GameData gamedata) {
        this.gamedata = gamedata;
    }

    public String run() {
        while (true) {
            String command;
            command = GetInput.getString();
            if (command.matches("next phase")) {
                break;
            } else if (command.matches("help")) {
//                help();
            } else {
                Printer.printInvalidCommand();
            }
        }
        return "";
    }
}
