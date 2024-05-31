package service.auth;

import base.repository.BaseRepository;
import base.repository.BaseUserRepository;

import java.sql.SQLException;

public class AuthServiceImpl implements AuthService {
    private final BaseUserRepository repository;

     public AuthServiceImpl(BaseUserRepository repository) {
         this.repository = repository;
     }

    @Override
    public boolean login(String username, String password) throws SQLException {

        return false;
    }

    @Override
    public boolean signUp(String username, String password) throws SQLException {
        return false;
    }
}
