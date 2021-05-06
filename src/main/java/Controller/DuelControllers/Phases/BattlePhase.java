package Controller.DuelControllers.Phases;

import Controller.DuelControllers.GameData;
import Controller.Utils;
import View.GetInput;
import View.Printer.Printer;

import java.util.regex.Matcher;

public class BattlePhase extends AllPhases{
    private GameData gamedata;

    public BattlePhase(GameData gamedata) {
        this.gamedata = gamedata;
    }

    public String run() {
        while (true) {
            String command;
            command = GetInput.getString();
            if (command.matches("attack (\\d+)")) {
                attackMonster(Utils.getMatcher(command, "attack (\\d+)"));
            } else if (command.matches("attack direct")) {
                directAttack();
            } else if (command.matches("next phase")) {
                break;
            } else if (command.matches("help")) {
//                help();
            } else {
                Printer.printInvalidCommand();
            }
        }
        return "";
    }


    private String attackMonster(Matcher matcher) {
        return "";
    }

    private String directAttack() {
        return "";
    }
}
