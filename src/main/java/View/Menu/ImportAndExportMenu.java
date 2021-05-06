package View.Menu;

import Model.Data.DataForClientFromServer;
import Model.Data.DataForServerFromClient;
import View.GetInput;
import View.Printer.Printer;

public class ImportAndExportMenu extends Menu {

    private static ImportAndExportMenu instance = null;

    private ImportAndExportMenu() {
        super("Import/Export Menu");
    }

    public static ImportAndExportMenu getInstance() {
        if (instance == null) {
            instance = new ImportAndExportMenu();
        }
        return instance;
    }


    public void run() {

        String command;
        command = GetInput.getString();

        while (true) {

            if (command.matches("import card .+")) {
                importCard(command);
            } else if (command.matches("export card .+")) {
                exportCard(command);
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

    private void importCard(String command) {
        DataForClientFromServer data = sendDataToServer
                (new DataForServerFromClient(command, menuName));

        Printer.print(data.getMessage());
    }

    private void exportCard(String command) {

        DataForClientFromServer data = sendDataToServer
                (new DataForServerFromClient(command, menuName));

        Printer.print(data.getMessage());
    }


    private void help() {
        System.out.print("""
                import card [card name]
                export card [card name]
                 help
                menu show-current
                menu [menu name]
                menu exit               
                """);
    }
}
