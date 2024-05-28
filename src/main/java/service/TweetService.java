package service;

import entity.Tweet;

import java.sql.SQLException;

public interface TweetService {
    Tweet save(Tweet tweet) throws SQLException;
    Tweet update(Tweet tweet) throws SQLException;
    Tweet findById(Integer id) throws SQLException;
}
