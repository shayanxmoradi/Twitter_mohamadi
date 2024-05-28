package repository.impl;

import entity.Tweet;
import repository.TweetRepository;

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

    @Override
    public Tweet update(Tweet tweet) throws SQLException {
        String updateQuery = """
                UPDATE tweet SET content = ?  WHERE id = ?
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setString(1, tweet.getContent());
        preparedStatement.setInt(2, tweet.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        return tweet;
    }

    @Override
    public Tweet findById(Integer id) throws SQLException {
        Tweet tweet = new Tweet();
        String selectQuery = """
                                SELECT *
                From Tweet t where t.id = ?
                                """;
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            tweet.setId(resultSet.getInt("id"));
            tweet.setContent(resultSet.getString("content"));
            tweet.setDate(resultSet.getDate("create_date").toLocalDate());
            tweet.setTime(resultSet.getTime("create_time").toLocalTime());
        }
        // preparedStatement.executeUpdate();
        preparedStatement.close();
        return tweet;
    }
}
