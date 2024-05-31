package menu.login;

import entity.Tweet;
import entity.User;
import menu.util.Input;
import menu.util.Message;
import util.ApplicationContext;
import util.AuthHolder;

import java.sql.Date;
import java.sql.SQLException;

public class LoginSubmenu {
    public static void show() throws SQLException {
        login_submenu:
        while (true) {
            System.out.println("""
                    Enter one of the following options:
                    1 -> Create Tweet
                    2 -> Update Tweet
                    3 -> Delete Tweet
                    4 -> Previous Menu
                    """);
            switch (Input.scanner.next()) {
                case "1": {
                    System.out.println("enter text of  Your Tweet ");
                    String text = Input.scanner.next();
                    if (ApplicationContext.getInstance().getUserService().createTweet(new Tweet(text)) != null) {
                        System.out.println(Message.getSuccessfulMessage("create tweet"));
                        break;
                    }
                    System.out.println(Message.getNotFoundMessage("create tweet"));
                    break;
                }
                case "2": {
                    System.out.println(Message.getInputMessage(" tweet id"));
                    String id = Input.scanner.next();
                    System.out.println(Message.getInputMessage("new tweet content"));
                    String newContent = Input.scanner.next();
                    if (ApplicationContext.getInstance().getUserService().updateTweet(newContent, Integer.valueOf(id)) != null) {
                        System.out.println(Message.getSuccessfulMessage(" update tweet by id: " + id));
                        break;
                    }
                    System.out.println(Message.getNotFoundMessage("tweet by this id: ") + id);
                    break;
                }
                case "3": {
                    System.out.println(Message.getInputMessage(" tweet id"));
                    String id = Input.scanner.next();

                    if (ApplicationContext.getInstance().getUserService().deleteTweet(Integer.valueOf(id))) {
                        System.out.println(Message.getSuccessfulMessage(" delete tweet by id: " + id));
                    }
                    System.out.println(Message.getNotFoundMessage("tweet by id: ") + id);
                    break;
                }
                case "4": {

                    break login_submenu;
                }
                default:
                    System.out.println(Message.getInvalidInputMessage());
            }
        }
    }
}