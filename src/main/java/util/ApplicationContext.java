package util;

import config.DataSource;
import menu.Menu;
import menu.login.LoginMenu;
import menu.login.LoginSubmenu;
import menu.main.MainMenu;
import menu.signup.SignupMenu;
import menu.util.Input;
import menu.util.Message;
import repository.TweetRepository;
import repository.UserRepository;
import repository.impl.TweetRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.TweetService;
import service.UserService;
import service.impl.TweetServiceImpl;
import service.impl.UserServiceImpl;

import java.sql.Connection;

public class ApplicationContext {

    private static final ApplicationContext INSTANCE = new ApplicationContext();
    private static Menu menu;

    private ApplicationContext() {

        Connection connection = DataSource.getConnection();
        UserRepository userRepository = new UserRepositoryImpl(connection);
        AuthHolder authHolder = new AuthHolder();
        TweetRepository tweetRepository = new TweetRepositoryImpl(connection,authHolder);

        TweetService tweetService = new TweetServiceImpl(tweetRepository);
        UserService userService = new UserServiceImpl(userRepository, tweetService, authHolder);

        Input input = new Input();
        Message message = new Message();
        MainMenu mainMenu = new MainMenu();
        SignupMenu signupMenu = new SignupMenu(input, message, userService);
        LoginSubmenu loginSubmenu = new LoginSubmenu(input, message, userService);
        LoginMenu loginMenu = new LoginMenu(input, message, loginSubmenu, userService, authHolder);
        menu = new Menu(input, message, mainMenu, signupMenu, loginMenu);
    }

    public static ApplicationContext getInstance() {
        return INSTANCE;
    }

    public Menu getMenu() {
        return menu;
    }



}
