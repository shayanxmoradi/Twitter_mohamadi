package repository;

import entity.User;

import java.sql.SQLException;

public interface UserRepository {
    User save(User user) throws SQLException;

    User findById(int id) throws SQLException;

    User findByUsernameAndPassword(String username, String password) throws SQLException;

    boolean existsByUsername(String username) throws SQLException;

}
