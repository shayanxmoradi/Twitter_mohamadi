package menu;

import menu.login.LoginMenu;
import menu.main.MainMenu;
import menu.signup.SignupMenu;
import menu.util.Input;
import menu.util.Message;

import java.sql.SQLException;

public class Menu {

    private final Input INPUT;
    private final Message MESSAGE;
    private final MainMenu MAINMENU;
    private final SignupMenu SIGNUP_MENU;
    private final LoginMenu LOGIN_MENU;

    public Menu(Input input, Message message, MainMenu mainMenu, SignupMenu signupMenu, LoginMenu loginMenu) {
        this.INPUT = input;
        this.MESSAGE = message;
        this.MAINMENU = mainMenu;
        this.SIGNUP_MENU = signupMenu;
        this.LOGIN_MENU = loginMenu;
    }

    public void show() throws SQLException {
        System.out.println("welcome to the our program");
        while (true) {
            MAINMENU.show();
            switch (INPUT.scanner.next()) {
                case "1":
                    SIGNUP_MENU.show();
                    break;
                case "2":
                    LOGIN_MENU.show();
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    System.out.println(MESSAGE.getInvalidInputMessage());
            }
        }
    }
}
