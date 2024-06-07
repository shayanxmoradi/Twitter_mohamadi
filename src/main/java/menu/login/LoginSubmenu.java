package menu.login;

import entity.Tweet;
import menu.util.Input;
import menu.util.Message;
import service.UserService;

import java.sql.SQLException;

public class LoginSubmenu {

    private final Input INPUT;
    private final Message MESSAGE;
    private final UserService USER_SERVICE;

    public LoginSubmenu(Input INPUT, Message MESSAGE, UserService userService) {
        this.INPUT = INPUT;
        this.MESSAGE = MESSAGE;
        this.USER_SERVICE = userService;
    }

    public void show() throws SQLException {
        login_submenu:
        while (true) {
            System.out.println("""
                    Enter one of the following options:
                    1 -> Create Tweet
                    2 -> Update Tweet
                    3 -> Delete Tweet
                    4 -> Previous Menu
                    """);
            switch (INPUT.scanner.next()) {
                case "1": {
                    System.out.println("enter text of  Your Tweet ");
                    String text = INPUT.scanner.next();
                    if (USER_SERVICE.createTweet(new Tweet(text)) != null) {
                        System.out.println(MESSAGE.getSuccessfulMessage("create tweet"));
                        break;
                    }
                    System.out.println(MESSAGE.getNotFoundMessage("create tweet"));
                    break;
                }
                case "2": {
                    System.out.println(MESSAGE.getInputMessage(" tweet id"));
                    String id = INPUT.scanner.next();
                    System.out.println(MESSAGE.getInputMessage("new tweet content"));
                    String newContent = INPUT.scanner.next();
                    if (USER_SERVICE.updateTweet(newContent, Integer.valueOf(id)) != null) {
                        System.out.println(MESSAGE.getSuccessfulMessage(" update tweet by id: " + id));
                        break;
                    }
                    System.out.println(MESSAGE.getNotFoundMessage("tweet by this id: ") + id);
                    break;
                }
                case "3": {
                    System.out.println(MESSAGE.getInputMessage(" tweet id"));
                    String id = INPUT.scanner.next();

                    if (USER_SERVICE.deleteTweet(Integer.valueOf(id))) {
                        System.out.println(MESSAGE.getSuccessfulMessage(" delete tweet by id: " + id));
                    }
                    System.out.println(MESSAGE.getNotFoundMessage("tweet by id: ") + id);
                    break;
                }
                case "4": {

                    break login_submenu;
                }
                default:
                    System.out.println(MESSAGE.getInvalidInputMessage());
            }
        }
    }
}