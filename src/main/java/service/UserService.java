package service;

import entity.Tweet;
import entity.User;
import java.sql.SQLException;

public interface UserService {
    User save(User user) throws SQLException;
    User findById(Integer id) throws SQLException;
    Tweet updateTweet(String newContent,Integer tweetId) throws SQLException;
    Tweet createTweet(Tweet tweet) throws SQLException;
    boolean deleteTweet(Integer id) throws SQLException;
    boolean login (String username, String password) throws SQLException;
    boolean signUp(String username, String password) throws SQLException;



}
