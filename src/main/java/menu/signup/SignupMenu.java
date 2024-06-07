package menu.signup;

import menu.util.Input;
import menu.util.Message;
import service.UserService;

import java.sql.SQLException;

public class SignupMenu {

    private final Input INPUT;
    private final Message MESSAGE;
    private final UserService USER_SERVICE;

    public SignupMenu(Input INPUT, Message MESSAGE, UserService userService) {
        this.INPUT = INPUT;
        this.MESSAGE = MESSAGE;
        this.USER_SERVICE = userService;
    }

    public void show() throws SQLException {
        signup:
        while (true) {
            System.out.println("""
                    1 -> Enter Information
                    2 -> Previous Menu
                    """);

            switch (INPUT.scanner.next()) {
                case "1": {
                    System.out.println(MESSAGE.getInputMessage("Username"));
                    String username = INPUT.scanner.next();
                    System.out.println(MESSAGE.getInputMessage("password"));
                    String password = INPUT.scanner.next();
                    if (USER_SERVICE.signUp(username, password)) {
                        System.out.println(MESSAGE.getSuccessfulMessage("sign up"));
                        break signup;
                    }
                    System.out.println(MESSAGE.getExistMessage("username"));
                    break;
                }
                case "2": {
                    break signup;
                }
                default:
                    System.out.println(MESSAGE.getInvalidInputMessage());

            }

        }
    }
}
