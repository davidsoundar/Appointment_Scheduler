package DB;

import Main.JDBC;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Customer DAO
 */
public class CustomerSQL {

    /**
     * Returns list of customer data
     * @return observable list of customer data
     * @throws SQLException
     */
    public static ObservableList<Customer> customersData() throws SQLException{
        Connection connection = JDBC.getConnection();
        ObservableList<Customer> customers = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customers INNER JOIN first_level_divisions ON customers.Division_ID = first_level_divisions.Division_ID INNER JOIN countries ON country.Country_ID=first_level_divisions.COUNTRY_ID;";
        ResultSet r = connection.createStatement().executeQuery(sql);
        while (r.next()) {
            Customer customer = new Customer(r.getInt("Customer_ID"), r.getString("Customer_Name"),
                            r.getString("Address"), r.getString("Postal Code"),
                            r.getString("Country"), r.getString("Phone"), r.getString("Division_ID"));
            customers.add(customer);
        }
        return customers;
    }

    /**
     * Returns a list of customers
     * @return observable list
     * @throws SQLException
     */
    public static ObservableList<Customer> customerList() throws SQLException {
        Connection connection = JDBC.getConnection();
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        String sql = "SELECT Customer_ID, Customer_Name FROM customers;";
        ResultSet result = connection.createStatement().executeQuery(sql);
        while (result.next()) {
            int customerID = result.getInt("Customer_ID");
            String customerName = result.getString("Customer_Name");
            Customer customer = new Customer(customerID, customerName);
            customers.add(customer);
        }
        return customers;
    }

    /**
     * Adds a customer into the database
     * @param name
     * @param address
     * @param postal
     * @param phone
     * @param divisionID
     * @return
     * @throws SQLException
     */
    public static boolean addCustomer(String name, String address, String postal, String phone, String divisionID) throws SQLException {
        String sql = "INSERT INTO customers(Customer_Name. Address, Postal_Code, Phone, Division_ID) VALUES (?,?,?,?,?)";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement s = connection.prepareStatement(sql)) {
            s.setString(1, name);
            s.setString(2, address);
            s.setString(3, postal);
            s.setString(4, phone);
            s.setInt(5, Integer.parseInt(divisionID));
            s.executeUpdate();
            return true;
        }
    }

    /**
     * function to update customer data
     * @param customerID
     * @param name
     * @param address
     * @param postal
     * @param phone
     * @param divisionID
     * @return
     * @throws SQLException
     */
    public static boolean updateCustomer(int customerID, String name, String address, String postal, String phone, String divisionID) throws SQLException {
        Connection connection = JDBC.getConnection();
        String sql = "UPDATE customers SET Customer_Name=?, Address=?, Postal_Code=?, Phone=?, Division_ID=?, WHERE Customer_ID=?";

        PreparedStatement s = connection.prepareStatement(sql);
        s.setString(1, name);
        s.setString(2, address);
        s.setString(3, postal);
        s.setString(4, phone);
        s.setInt(5, Integer.parseInt(divisionID));
        s.setInt(6, customerID);
        s.execute();
        return true;
    }

    /**
     * Deletes customer  from database
     * @param customerID int value
     * @return boolean
     * @throws SQLException
     */
    public static boolean deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID=?;";

        try(Connection connection = JDBC.getConnection();
        PreparedStatement s = connection.prepareStatement(sql)) {
            s.setInt(1, customerID);
            s.executeUpdate();

            return true;
        }
    }
}
