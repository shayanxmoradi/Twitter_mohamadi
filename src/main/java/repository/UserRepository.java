package repository;

import base.repository.BaseRepository;
import base.repository.BaseUserRepository;
import entity.User;

import java.sql.SQLException;
//there is no connectin between BaseRepository, BaseUserRepository
public interface UserRepository extends BaseRepository, BaseUserRepository {
    User save(User user) throws SQLException;

    User findById(int id) throws SQLException;

    User findByUsernameAndPassword(String username, String password) throws SQLException;

    boolean existsByUsername(String username) throws SQLException;

}
