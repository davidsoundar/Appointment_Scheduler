package DB;

import Main.JDBC;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstLevelDivisionSQL {

    /**
     * Makes division object
     * @param result
     * @return
     * @throws SQLException
     */
    private Division newDivision(ResultSet result) throws SQLException {
        return new Division(result.getInt("Division_ID"), result.getInt("Country_ID"), result.getString("Division"));
    }

    /**
     * Gets division info
     * @return Division, Division ID, and Country ID
     */
    public ObservableList<Division> getDivisionInfo(String country) {
        String sql = "SELECT first_level_divisions.Division_ID, countries.Country_ID, first_level_divisions.Division FROM countries, first_level_divisions WHERE countries.Country=? AND countries.Country_ID = first_level_divisions.Country_ID;";
        ObservableList<Division> divisions = FXCollections.observableArrayList();

        try (Connection connection = JDBC.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, country);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                divisions.add(newDivision(result));
            }
            return divisions;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
