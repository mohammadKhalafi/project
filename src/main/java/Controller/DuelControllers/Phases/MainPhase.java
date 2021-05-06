package Controller.DuelControllers.Phases;

import Controller.DuelControllers.GameData;
import Controller.Utils;
import View.GetInput;
import View.Printer.Printer;

import java.util.regex.Matcher;

public class MainPhase {
    private GameData gamedata;
    private int mainPhaseNumber;

    public MainPhase(GameData gamedata, int number) {
        this.gamedata = gamedata;
        this.mainPhaseNumber = number;
    }

    public String run() {
        while (true) {
            String command;
            command = GetInput.getString();
            if (command.matches("summon")) {
                summonMonster();
            } else if (command.matches("set")) {
                setCard(); } else if (command.startsWith("select ")) {
                /* select(); */
            } else if (command.matches("set --position (attack|defence)")) {
                setPosition(Utils.getMatcher(command, "set --position (.*)"));
            } else if (command.matches("flip-summon")) {
                flip();
            } else if (command.matches("next phase")) {
                break;
            } else if (command.matches("activate effect") && mainPhaseNumber == 1) {
                activate();
            } else if (command.matches("help")) {
//                help();
            } else {
                Printer.printInvalidCommand();
            }
        }
        return "";
    }

    private String flip() {
        return "";
    }

    private String activate() {
        return "";
    }

    private String setPosition(Matcher matcher) {
        return "";
    }

    private String setCard() {
        return "";
    }

    private String summonMonster() {
        return "";
    }


}
