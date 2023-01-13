package DB;

import java.sql.SQLException;

/**
 * A class for the SQl queries made on the Users table
 */
public class UsersSQL {

    public static boolean validLogin(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ?" + username + "'AND Password = ?" + password
    }
}
