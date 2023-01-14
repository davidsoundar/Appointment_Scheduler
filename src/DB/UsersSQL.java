package DB;

import Main.JDBC;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

/**
 * A class for the SQl queries made on the Users table
 */
public class UsersSQL {

    /**
     * Checks the validity of the login info aginst the database
     * @param username
     * @param password
     * @return
     */
    public static boolean validuserpass(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_name=? AND Password=?";
        PreparedStatement statement = JDBC.getConnection().prepareStatement(sql);

        statement.setString(1, username);
        statement.setString(2, password);

        try {
            statement.execute();
            ResultSet r = statement.getResultSet();
            return (r.next());
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    /**
     * Gets Users from the users table
     * @return Username and user ID
     */
    public static ObservableList<User> getUsers() {
        ObservableList<User> users = FXCollections.observableArrayList();
        String sql = "SELECT User_ID, User_Name FROM users;";

        try (Connection connection = JDBC.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                User user = new User(result.getInt("User_ID"), result.getString("User_Name"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
