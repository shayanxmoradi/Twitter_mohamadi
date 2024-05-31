package util;

import base.repository.BaseRepository;
import base.repository.BaseUserRepository;
import config.DataSource;
import repository.TweetRepository;
import repository.UserRepository;
import repository.impl.TweetRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.TweetService;
import service.UserService;
import service.auth.AuthServiceImpl;
import service.impl.TweetServiceImpl;
import service.impl.UserServiceImpl;

import java.sql.Connection;

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
        AuthServiceImpl authenticationService=new AuthServiceImpl();

        tweetService = new TweetServiceImpl(tweetRepository);
        userService = new UserServiceImpl(userRepository,tweetService,authenticationService);


    }
    public UserService getUserService(){
        return userService;
    }
    public TweetService getTweetService(){
        return tweetService;
    }

}
