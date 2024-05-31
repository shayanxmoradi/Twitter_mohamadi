package menu;

import menu.util.Input;
import menu.util.Message;
import util.ApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) throws SQLException {
        show();
    }
    public static void show() throws SQLException {

            System.out.println("welcome to the menu");
            while (true) {
                MainMenu.show();
                int inputedValue = Input.scanner.nextInt();

                switch (inputedValue) {
                    case 1 -> SignupMenu.show();
                    case 2 -> LoginMenu.showLoginMenu();
                    case 3 -> System.exit(0);
                    default -> System.out.println(Message.getInvalidInputMessage());
                }

            }
    }

    }

