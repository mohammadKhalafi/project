package View.Menu;



import View.GetInput;
import View.Printer.Printer;

public class MainMenu extends Menu{


    private static MainMenu instance = null;


    private MainMenu() {
        super("Main Menu");
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            return new MainMenu();
        }
        return instance;
    }


    public void run(String username) {

        setUsername(username);

        String command;
        while (true) {
            command = GetInput.getString();

            if (command.matches("menu exit")) {
                break;
            } else if (command.startsWith("menu ")) {
                handleMenuOrders(command);
            }else if (command.matches("help")){
                help();
            }
            else {
                Printer.printInvalidCommand();
            }
        }
    }

    private void help(){

        System.out.println("""
                help
                menu show-current
                menu [menu name]
                menu exit""");

    }

}
