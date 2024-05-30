package service.impl;

import entity.Tweet;
import repository.TweetRepository;
import service.TweetService;

import java.sql.SQLException;

public class TweetServiceImpl implements TweetService {
    private final TweetRepository tweetRepository;

    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Tweet save(Tweet tweet) throws SQLException {
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet update(String newContent,Integer tweetId) throws SQLException {
        return tweetRepository.update(newContent,tweetId);
    }

    @Override
    public Tweet findById(Integer id) throws SQLException {
        return tweetRepository.findById(id);
    }
}
