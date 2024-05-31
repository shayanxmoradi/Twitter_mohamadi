package util;

import config.DataSource;
import repository.TweetRepository;
import repository.UserRepository;
import repository.impl.TweetRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.TweetService;
import service.UserService;
import service.impl.TweetServiceImpl;
import service.impl.UserServiceImpl;

import java.sql.Connection;

/**
 * this class is using singleton(Eager)
 * has private constractor
 * we use static block
 * we definde servieces static so we can iniate them in static block
 */
public class ApplicationContext {

    private static final ApplicationContext INSTANCE=new ApplicationContext();
    private static final   UserService userService;
    private static final TweetService tweetService;

    private ApplicationContext() {
    }
    public  static ApplicationContext getInstance(){
        return INSTANCE;
    }
    static {
        Connection connection = DataSource.getConnection();

        UserRepository userRepository=new UserRepositoryImpl(connection);
        TweetRepository tweetRepository=new TweetRepositoryImpl(connection);

        tweetService = new TweetServiceImpl(tweetRepository);
        userService = new UserServiceImpl(userRepository,tweetService);


    }
    public UserService getUserService(){
        return userService;
    }
    public TweetService getTweetService(){
        return tweetService;
    }

}
