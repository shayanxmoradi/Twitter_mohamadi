package menu;

import menu.util.Input;
import menu.util.Message;
import util.ApplicationContext;

import java.sql.SQLException;

public class SignupMenu {
    public static void show() throws SQLException {
        signupMenu:
        while (true) {
            System.out.println("""
                    1 -> Enter Information
                    2 -> previous Menu
                    """);

            switch (Input.scanner.next()) {
                case "1" -> {
                    System.out.println(Message.getExistMessage("Username"));
                    String inputedUsername = Input.scanner.next();
                    if (inputedUsername.equals("0")) break;
                    System.out.println(Message.getInputMessage("password"));
                    String inputedPassword = Input.scanner.next();
                    if (inputedPassword.equals("0")) break;

                    if (ApplicationContext.getInstance().getUserService().signUp(inputedUsername, inputedPassword)
                    ) {
                        System.out.println(" your account has been created \n");

                        break signupMenu;
                    }
                    System.out.println(Message.getExistMessage("username"));
                }
                case "2" -> {
                    break signupMenu;
                }
                default -> System.out.println(Message.getInvalidInputMessage());

            }


        }
    }
}
