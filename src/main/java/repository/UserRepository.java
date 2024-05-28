package repository;

import entity.User;

import java.sql.SQLException;

public interface UserRepository {
    User save(User user) throws SQLException;

}
