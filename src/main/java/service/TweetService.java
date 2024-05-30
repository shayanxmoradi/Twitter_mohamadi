package service;

import entity.Tweet;

import java.sql.SQLException;

public interface TweetService {
    Tweet save(Tweet tweet) throws SQLException;
    Tweet update(String newContent,Integer tweetId) throws SQLException;
    Tweet findById(Integer id) throws SQLException;
    boolean deleteById(Integer id) throws SQLException;
}
