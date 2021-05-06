package View.Menu;

import Controller.Utils;
import Model.Data.DataForServerFromClient;
import View.GetInput;
import View.Printer.Printer;

import java.util.regex.Matcher;

public class ScoreBoardMenu extends Menu {

    static ScoreBoardMenu instance = null;

    private boolean commandIsDone;

    private ScoreBoardMenu() {
        super("Scoreboard Menu");
    }

    static ScoreBoardMenu getInstance() {

        if (instance == null) {
            instance = new ScoreBoardMenu();
        }
        return instance;
    }

    public void run() {

        String command = GetInput.getString();

        while (!command.equals("menu exit")) {

            commandIsDone = false;

            showScores(Utils.getMatcher(command, "scoreboard show"));

            handleCommandsStartWithMenu(command);

            help(command);

            if (!commandIsDone) {
                Printer.printInvalidCommand();
            }
            command = GetInput.getString();
        }
    }

    private void showScores(Matcher matcher) {

        if (!matcher.matches()) {
            return;
        }

        commandIsDone = true;

        Printer.print(sendDataToServer(
                new DataForServerFromClient(matcher.group(0), menuName)).getMessage());

    }

    private void handleCommandsStartWithMenu(String command) {
        if (command.startsWith("menu ")) {
            commandIsDone = true;
            handleMenuOrders(command);
        }
    }

    private void help(String command){
        if(command.equals("help")) {
            commandIsDone=true;
            System.out.println("""
                    scoreboard show
                    help
                    menu show-current
                    menu [menu name]
                    menu exit""");
        }
    }

}
