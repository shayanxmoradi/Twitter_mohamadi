package service.impl;

import entity.Tweet;
import entity.User;
import repository.TweetRepository;
import repository.UserRepository;
import service.TweetService;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TweetService tweetService;

    public UserServiceImpl(UserRepository userRepository, TweetService tweetService) {
        this.userRepository = userRepository;
        this.tweetService = tweetService;
    }

    @Override
    public User save(User user) throws SQLException {
        //business code
        return userRepository.save(user);
    }

    @Override
    public Tweet updateTweet(Tweet tweet, String newContent, Integer tweetId) throws SQLException {
        tweet.setContent(newContent);
        tweet.setId(tweetId);
        return tweetService.update(tweet);
    }

    @Override
    public Tweet createTweet(Tweet tweet) throws SQLException {
        return tweetService.save(tweet);
    }

    @Override
    public boolean deleteTweetById(Integer id) throws SQLException {
        return false;
    }

    @Override
    public Tweet findTweetById(Integer id) throws SQLException {
        return tweetService.findById(id);
    }
}
