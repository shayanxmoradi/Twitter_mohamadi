package service.impl;

import entity.Tweet;
import entity.User;
import repository.UserRepository;
import service.TweetService;
import service.UserService;
import util.AuthHolder;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TweetService tweetService;
    private final AuthHolder authHolder;

    public UserServiceImpl(UserRepository userRepository, TweetService tweetService, AuthHolder authHolder) {
        this.userRepository = userRepository;
        this.tweetService = tweetService;
        this.authHolder = authHolder;
    }

    @Override
    public User save(User user) throws SQLException {
        //business code
        return userRepository.save(user);
    }

    @Override
    public User findById(Integer id) throws SQLException {
        return userRepository.findById(id);
    }

    @Override
    public Tweet createTweet(Tweet tweet) throws SQLException {
        return tweetService.save(tweet);
    }

    @Override
    public boolean deleteTweet(Integer id) throws SQLException {

        return tweetService.deleteById(id);
    }

    @Override
    public Tweet updateTweet(String newContent, Integer tweetId) throws SQLException {
        return tweetService.update(newContent, tweetId);
    }

    @Override
    public boolean login(String username, String password) throws SQLException {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            authHolder.setTokenId(user.getId());
            authHolder.setTokenName(user.getUsername());
            return true;
        }
        return false;
    }

    @Override
    public boolean signUp(String username, String password) throws SQLException {
        if (!userRepository.existsByUsername(username)) {
            userRepository.save(new User(username, password));
            return true;
        }
        return false;
    }
}
