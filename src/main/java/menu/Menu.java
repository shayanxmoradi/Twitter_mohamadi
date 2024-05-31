package menu;

import menu.login.LoginMenu;
import menu.main.MainMenu;
import menu.signup.SignupMenu;
import menu.util.Input;
import menu.util.Message;

import java.sql.SQLException;

public class Menu {

    public static void show() throws SQLException {
        System.out.println("welcome to the our program");
        while (true) {
            MainMenu.show();

            switch (Input.scanner.next()) {
                case "1":
                    SignupMenu.show();
                    break;
                case "2":
                    LoginMenu.show();
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println(Message.getInvalidInputMessage());
            }

        }
    }
}
