package repository;

import entity.Tweet;

import java.sql.SQLException;

public interface TweetRepository {
    Tweet save(Tweet tweet) throws SQLException;
    Tweet update(String newContent,Integer tweetId) throws SQLException;
    Tweet findById(Integer id) throws SQLException;
    boolean deleteTweetById(Integer id) throws SQLException;
}
