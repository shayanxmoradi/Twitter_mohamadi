package menu;

import menu.util.Input;
import util.ApplicationContext;

import java.sql.SQLException;

public class SignupMenu {
    public static void show() throws SQLException {
        signupMenu:while (true) {
            System.out.println("""
                    1 -> Enter Information
                    2 -> previous Menu
                    """);

            switch (Input.scanner.next()) {
                case "1" -> {
                    doLoginProcess();
                }
                case "2" -> {
                    break signupMenu;
                }
                default -> System.out.println("Invalid input");

            }

        }
    }

    private static void doLoginProcess() throws SQLException {
        System.out.println("enter username ");
        String inputedUsername = Input.scanner.next();
        if (inputedUsername.equals("0")) return;
        System.out.println("enter password ");
        String inputedPassword = Input.scanner.next();
        if (inputedPassword.equals("0")) return;

        if (ApplicationContext.getInstance().getUserService().signUp(inputedUsername, inputedPassword)
        ) return;
        System.out.println("invalid username or password, try again ...");
    }
}
