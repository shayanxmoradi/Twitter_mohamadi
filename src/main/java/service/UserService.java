package service;

import entity.Tweet;
import entity.User;

import java.sql.SQLException;

public interface UserService {
    User save(User user) throws SQLException;
    Tweet updateTweet(String newContent,Integer tweetId) throws SQLException;
    Tweet createTweet(Tweet tweet) throws SQLException;
    boolean deleteTweetById(Integer id) throws SQLException;
    Tweet findTweetById(Integer id) throws SQLException;
}
