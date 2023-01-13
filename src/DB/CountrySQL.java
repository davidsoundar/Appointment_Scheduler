package DB;

import Main.JDBC;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Country DAO
 */
public class CountrySQL {

    public static ObservableList<Country> countryList() {
        ObservableList<Country> countries = FXCollections.observableArrayList();
        String sql = "SELECT * FROM countries;";

        try (Connection connection = JDBC.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                Country country = new Country(result.getInt("Country_ID"), result.getString("Country"));
                countries.add(country);
            }
            return countries;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
