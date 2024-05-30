package repository.impl;

import entity.Tweet;
import entity.User;
import repository.TweetRepository;
import util.AuthHolder;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class TweetRepositoryImpl implements TweetRepository {
    private final Connection connection;

    public TweetRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public Tweet save(Tweet tweet) throws SQLException {
        String insertQuery = """
                insert into tweet(content,create_time,create_date,user_id) values (?,?,?,?)
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
                PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, tweet.getContent());
        preparedStatement.setTime(2, Time.valueOf(tweet.getTime()));
        preparedStatement.setDate(3, Date.valueOf(tweet.getDate()));
        preparedStatement.setInt(4, tweet.getUser().getId());
        if (preparedStatement.executeUpdate() > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                tweet.setId(generatedKeys.getInt("id"));
            }

        }
        return tweet;
    }

    /**
     * TYPE_SCROLL_INSENSITIVE +CONCUR_UPDATABLE -> we can change(update) in Database too. its
     * not just normal select Query
     *
     * @param
     * @param tweetId
     * @return
     * @throws SQLException
     */
    @Override
    public Tweet update(String newContent, Integer tweetId) throws SQLException {
        Tweet returnTweet = null;
        String selectQuery = """
                                SELECT *
                From Tweet t where t.id = ?
                                """;
        PreparedStatement preparedStatement = connection.prepareStatement(
                selectQuery,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);

        preparedStatement.setInt(1, tweetId);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            returnTweet = new Tweet();
            returnTweet.setContent(newContent);
            returnTweet.setTime(resultSet.getTime("create_time").toLocalTime());
            returnTweet.setDate(resultSet.getDate("create_date").toLocalDate());
         //   returnTweet.setUser(new User(AuthHolder.tokenName));
            returnTweet.setId(resultSet.getInt("id"));
        }
        preparedStatement.close();
        return returnTweet;
    }

    @Override
    public Tweet findById(Integer id) throws SQLException {
        Tweet tweet = null;
        String selectQuery = """
                                SELECT *
                From Tweet t where t.id = ?
                                """;
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            tweet = new Tweet();
            tweet.setId(resultSet.getInt("id"));
            tweet.setContent(resultSet.getString("content"));
            tweet.setDate(resultSet.getDate("create_date").toLocalDate());
            tweet.setTime(resultSet.getTime("create_time").toLocalTime());
        }
        preparedStatement.close();
        return tweet;
    }
}
