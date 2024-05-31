package service.auth;

import java.sql.SQLException;

public interface AuthService {
    boolean login (String username, String password) throws SQLException;
    boolean signUp(String username, String password) throws SQLException;
}
