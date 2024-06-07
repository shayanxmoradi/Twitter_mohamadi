package repository.impl;

import entity.Tweet;
import entity.User;
import repository.TweetRepository;
import util.AuthHolder;

import java.sql.*;


public class TweetRepositoryImpl implements TweetRepository {
    private final Connection connection;
    private final AuthHolder authHolder;

    public TweetRepositoryImpl(Connection connection, AuthHolder authHolder) {
        this.connection = connection;
        this.authHolder = authHolder;
    }


    @Override
    public Tweet save(Tweet tweet) {
        String insertQuery = """
            insert into tweet(content,create_time,create_date,user_id) values (?,?,?,?)
            """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, tweet.getContent());
            preparedStatement.setTime(2, Time.valueOf(tweet.getTime()));
            preparedStatement.setDate(3, Date.valueOf(tweet.getDate()));
            preparedStatement.setInt(4, authHolder.getTokenId());

            if (preparedStatement.executeUpdate() > 0) {
                try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                    if (keys.next()) {
                        tweet.setId(keys.getInt("id"));
                        return tweet;
                    }
                }
            }
        } catch (SQLException SqlE) {
            SqlE.printStackTrace();
            throw new RuntimeException("Error saving tweet", SqlE);
        }
        return null;
    }



    @Override
    public Tweet update(String newContent, Integer tweetId) throws SQLException {
        Tweet returnTweet = null;
        String selectQuery = """
                           SELECT *
                                From tweet as t 
                                inner join twitter2.users as u on u.id = t.user_id
                where t.id = ? AND t.user_id = ?
                                                """;
        PreparedStatement preparedStatement = connection.prepareStatement(
                selectQuery,
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);

        preparedStatement.setInt(1, tweetId);
        preparedStatement.setInt(2, authHolder.getTokenId());
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            returnTweet = new Tweet();
            resultSet.updateString("content", newContent);
            resultSet.updateRow();
            returnTweet.setId(resultSet.getInt("id"));
            returnTweet.setContent(resultSet.getString("content"));
            returnTweet.setTime(resultSet.getTime("create_time").toLocalTime());
            returnTweet.setDate(resultSet.getDate("create_date").toLocalDate());
            //mapping : converting databse datas to obj.
            returnTweet.setUser(userMapper(resultSet));
        }
        resultSet.close();
        preparedStatement.close();


        //todo: close connection
        return returnTweet;
    }

    private User userMapper(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        return user;
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
            tweet.setId(resultSet.getInt("user_id"));
            tweet.setContent(resultSet.getString("content"));
            tweet.setDate(resultSet.getDate("create_date").toLocalDate());
            tweet.setTime(resultSet.getTime("create_time").toLocalTime());
        }
        resultSet.close();
        preparedStatement.close();
        return tweet;
    }

    @Override
    public boolean deleteTweetById(Integer id) throws SQLException {
        String selectQuery = """
                                Delete  
                From Tweet t where t.id = ?
                                """;
        PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
        preparedStatement.setInt(1, id);
        int affectedRows = preparedStatement.executeUpdate();
        preparedStatement.close();
        return affectedRows > 0;
    }
}
