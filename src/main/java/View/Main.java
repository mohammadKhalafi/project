package View;

import Controller.DataBaseControllers.DataBaseController;
import View.Menu.LoginMenu;



public class Main {

    public static void main(String[] arg){

        DataBaseController.makeResourceDirectory();

        LoginMenu.getInstance().run();
    }

}
