
package Main;

import DBM.DatabaseManager;
import UserInterface.MainFrame;

public class Main {

    public static void main(String[] args) {

        MainFrame frame=new MainFrame();
        frame.setVisible(true);

        DatabaseManager dbm =new DatabaseManager();
        dbm.createTable();

    }
}
