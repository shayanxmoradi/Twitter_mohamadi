package menu.login;

import menu.util.Input;
import menu.util.Message;
import service.UserService;
import util.AuthHolder;

import java.sql.SQLException;

public class LoginMenu {

    private final Input INPUT;
    private final Message MESSAGE;
    private final UserService USER_SERVICE;
    private final LoginSubmenu LOGIN_SUBMENU;
    private final AuthHolder authHolder;

    public LoginMenu(Input INPUT, Message MESSAGE, LoginSubmenu loginSubmenu, UserService userService, AuthHolder authHolder) {
        this.INPUT = INPUT;
        this.MESSAGE = MESSAGE;
        this.LOGIN_SUBMENU = loginSubmenu;
        this.USER_SERVICE = userService;
        this.authHolder = authHolder;
    }

    public void show() throws SQLException {
        login:
        while (true) {
            System.out.println("""
                    1 -> Enter Information
                    2 -> Previous Menu
                    """);
            switch (INPUT.scanner.next()) {
                case "1": {
                    System.out.println(MESSAGE.getInputMessage("userName"));
                    String username = INPUT.scanner.next();
                    System.out.println(MESSAGE.getInputMessage("password"));
                    String password = INPUT.scanner.next();
                    if (USER_SERVICE.login(username, password)) {
                        System.out.println(MESSAGE.getSuccessfulMessage("login "));
                        LOGIN_SUBMENU.show();
                        authHolder.reset();
                        break login;
                    }
                    System.out.println(MESSAGE.getNotFoundMessage(username));
                    break;
                }
                case "2": {
                    break login;
                }
                default:
                    System.out.println(MESSAGE.getInvalidInputMessage());
            }

        }


    }
}
