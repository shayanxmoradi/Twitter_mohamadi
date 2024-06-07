package repository.impl;

import entity.User;
import exception.UserNotFoundException;
import repository.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.UIManager.getInt;

public class UserRepositoryImpl implements UserRepository {
    private Connection connection;

    public UserRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User save(User user) throws SQLException {
        String insertQuery = """
                insert into users(username,password) values (?,?)
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
                PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        if (preparedStatement.executeUpdate() > 0) {
            ResultSet resultset = preparedStatement.getGeneratedKeys();
            if (resultset.next()) {
                user.setId(resultset.getInt("id"));
                resultset.close();
            }
            resultset.close();
        }
        preparedStatement.close();
        return user;
    }

    @Override
    public User findById(int id) throws SQLException {
        String insertQuery = """
                SELECT * from users where id=?
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery,
                PreparedStatement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            resultSet.close();
            preparedStatement.close();
            return user;
        }
        resultSet.close();
        preparedStatement.close();
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws SQLException  {
        User user = null;

        String insertQuery = """
                SELECT * from users where username =? and password=?
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));

        }
        resultSet.close();
        preparedStatement.close();
        return user;
    }

    @Override
    public boolean existsByUsername(String username) throws SQLException {
        String insertQuery = """
                SELECT count(id)  from users where username =? 
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        boolean result = resultSet.next() && resultSet.getInt(1) > 0;
        resultSet.close();
        preparedStatement.close();
        return result;

    }
}
