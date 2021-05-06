package View.Menu;


import Controller.Utils;
import View.GetInput;
import View.Printer.Printer;

import java.util.regex.Matcher;

public class ShopMenu extends Menu{

    public ShopMenu() {
        super("Shop Menu");
    }

    private static ShopMenu instance = null;

    public static ShopMenu getInstance(){

        if(instance == null){
            instance = new ShopMenu();
        }
        return instance;
    }


    public void run(String username){

        setUsername(username);

        String command;

        while(true){

            command = GetInput.getString();
            if(command.matches("shop buy (\\S+)")){
                buyGoods(Utils.getMatcher(command,"shop buy (\\S+)"));
            }
            else if (command.equals("shop show --all")){
                showAllCards(Utils.getMatcher(command, "shop show --all"));
            }
            else if (command.startsWith("menu ")){
                handleMenuOrders(command);
            }
            else if (command.equals("help")){
                help();
            }
            else if (command.equals("menu exit")){
                break;
            }
            else{
                Printer.printInvalidCommand();
            }
        }
    }

    private void buyGoods(Matcher matcher){

        sendCommandToServer1(matcher);
    }

    private void showAllCards(Matcher matcher){
        sendCommandToServer1(matcher);
    }

    private void help(){
        System.out.println("""
                shop buy <card name>
                shop show --all
                help
                menu show-current
                menu [menu name]
                menu exit""");
    }
}
