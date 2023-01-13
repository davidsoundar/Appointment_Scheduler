package DB;

import Main.JDBC;
import Model.Contact;
import Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactSQL {

    /**
     * retrieves contacts from db and their emails
     * @return
     */
    public static ObservableList<Contact> contactList() {
        ObservableList<Contact> contacts = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts;";

        try (Connection connection = JDBC.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(sql)) {

            while (result.next()) {
                Contact contact = new Contact(result.getInt("Contact_ID"), result.getString("Contact_Name"), result.getString("Email"));
                contacts.add(contact);
            }
            return contacts;
        }
        catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
