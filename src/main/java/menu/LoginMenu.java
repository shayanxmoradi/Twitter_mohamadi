package menu;

import menu.util.Input;
import menu.util.Message;
import util.ApplicationContext;
import util.AuthHolder;

import java.sql.SQLException;

public class LoginMenu {
    public static void showLoginMenu() throws SQLException {
        System.out.println("welcome to the login menu");

        login:
        while (true) {
            System.out.println("""
                    1 -> Enter Information
                    2 -> previous Menu
                    """);
            switch (Input.scanner.next()) {
                case "1" -> {
                    System.out.println(Message.getInputMessage("userName"));
                    String userNameInputed = Input.scanner.next();
                    System.out.println(Message.getInputMessage("password"));
                    String passwordInputed = Input.scanner.next();
                    if (ApplicationContext.getInstance().getUserService().login(userNameInputed, passwordInputed)) {
                        System.out.println("login successful");
                        break login;

                    } else System.out.println("login failed");

                }
                case "2" -> {
                    break login;
                }
                default -> System.out.println("invalid input");
            }

        }


    }
}
