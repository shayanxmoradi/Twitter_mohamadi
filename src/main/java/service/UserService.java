package service;

import entity.Tweet;
import entity.User;
import service.auth.AuthService;

import java.sql.SQLException;

public interface UserService  extends AuthService {
    User save(User user) throws SQLException;
    Tweet updateTweet(String newContent,Integer tweetId) throws SQLException;
    Tweet createTweet(Tweet tweet) throws SQLException;
    boolean deleteTweet(Integer id) throws SQLException;
    Tweet findTweetById(Integer id) throws SQLException;

}
