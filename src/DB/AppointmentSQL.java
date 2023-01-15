package DB;

import Main.JDBC;
import Model.Appointment;
import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentSQL {

    /**
     * Function to make the appointment list based on all, week, or month
     *
     * @param i 1=ALl. 2=by week. 3 = by month
     * @return observable appointment list
     */
    public static ObservableList<Appointment> makeAppointmentListForMain(int i) {
        String sql = "";
        if (i == 1) {
            sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID;";
        }
        if (i == 2) {
            sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE week(Start)=week(now());";
        }
        if (i == 3) {
            sql = "SELECT * FROM appointments INNER JOIN contacts ON appointments.Contact_ID = contacts.Contact_ID WHERE month(Start)=month(now());";
        }
        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        try {
            Connection conn = JDBC.getConnection();
            Statement st = conn.createStatement();
            ResultSet r = st.executeQuery(sql);
            while (r.next()) {
                Appointment appt = new Appointment(
                        r.getInt("Appointment_ID"),
                        r.getString("Title"),
                        r.getString("Type"),
                        r.getString("Description"),
                        r.getString("Location"),
                        r.getDate("Start").toLocalDate(),
                        r.getTimestamp("Start").toLocalDateTime(),
                        r.getDate("End").toLocalDate(),
                        r.getTimestamp("End").toLocalDateTime(),
                        r.getInt("Customer_ID"),
                        r.getInt("User_ID"),
                        r.getString("Contact_Name"),
                        r.getInt("Contact_ID"));
                appointments.add(appt);

            }
            return appointments;
        } catch (SQLException sqlexc) {
            sqlexc.printStackTrace();
        }
        return null;
    }


    /**
     * delete appointment from database
     *
     * @param appointmentID int
     * @return boolean
     */
    public static boolean deleteAppointmentFromDB(int appointmentID) {
        String sql = "DELETE FROM appointments WHERE Appointment_ID=?;";

        try {
            Connection connection = JDBC.getConnection();
            PreparedStatement s = connection.prepareStatement(sql);
            s.setInt(1, appointmentID);
            s.execute();
            return true;
        } catch (SQLException sqlexc) {
            sqlexc.printStackTrace();
        }
        return false;
    }

    /**
     * Function to make appointment list by customer ID
     *
     * @param customerID
     * @return observable appointment list
     * @throws SQLException
     */
    public static ObservableList<Appointment> appointmentByCustID(int customerID) throws SQLException {
        try {
            Connection connection = JDBC.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT a.*, c.Contact_Name FROM appointments a JOIN contacts c ON a.Contact_ID=c.Contact_ID WHERE a.Customer_ID=?");

            statement.setInt(1, customerID);
            ResultSet r = statement.executeQuery();
            ObservableList<Appointment> appointments = FXCollections.observableArrayList();

            while (r.next()) {
                appointments.add(new Appointment(r.getInt("Appointment_ID"), r.getString("Title"),
                        r.getString("Type"), r.getString("Description"), r.getString("Location"),
                        r.getDate("Start").toLocalDate(), r.getTimestamp("Start").toLocalDateTime(),
                        r.getDate("End").toLocalDate(), r.getTimestamp("End").toLocalDateTime(),
                        r.getInt("Customer_ID"), r.getInt("User_ID"), r.getString("Contact_Name"), r.getInt("Contact_ID")));

            }
            return appointments;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    /**
     * Makes an appointment for the database
     *
     * @param title
     * @param type
     * @param description
     * @param location
     * @param start
     * @param end
     * @param customer_ID
     * @param user_ID
     * @param contact_name
     * @return
     * @throws SQLException
     */
    public static boolean makeAppointmentForDB(String title, String type, String description, String location, LocalDateTime start, LocalDateTime end, int customer_ID, int user_ID, int contact_name) throws SQLException {
        String statement = "INSERT INTO appointments(Title, Type, Description, Location, Start, End, Customer_ID, User_ID, Contact_ID) VALUES (?,?,?,?,?,?,?,?,?)";

        try {
            Connection connection = JDBC.getConnection();
            PreparedStatement s = connection.prepareStatement(statement);

            s.setString(1, title);
            s.setString(2, type);
            s.setString(3, description);
            s.setString(4, location);
            s.setTimestamp(5, Timestamp.valueOf(start));
            s.setTimestamp(6, Timestamp.valueOf(end));
            s.setInt(7, customer_ID);
            s.setInt(8, user_ID);
            s.setInt(9, contact_name);
            s.execute();
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
        }
        return false;
    }

    /**
     * Function to update/modify appointments in the database
     *
     * @param title
     * @param type
     * @param description
     * @param location
     * @param start
     * @param end
     * @param customer_ID
     * @param user_ID
     * @param contact_name
     * @param appointment_ID
     * @return
     * @throws SQLException
     */
    public static boolean updateAppointmentInDB(String title, String type, String description, String location, LocalDateTime start, LocalDateTime end, int customer_ID, int user_ID, int contact_name, int appointment_ID) throws SQLException {
        String statement = "UPDATE appointments SET Title = ?, Type=?, Description =?, Location=?, Start=?, End=?, Customer_ID=?, User_ID=?, Contact_ID=? WHERE Appointment_ID=?; ";
        try {
            Connection connection = JDBC.getConnection();
            PreparedStatement s = connection.prepareStatement(statement);
            s.setString(1, title);
            s.setString(2, type);
            s.setString(3, description);
            s.setString(4, location);
            s.setTimestamp(5, Timestamp.valueOf(start));
            s.setTimestamp(6, Timestamp.valueOf(end));
            s.setInt(7, customer_ID);
            s.setInt(8, user_ID);
            s.setInt(9, contact_name);
            s.setInt(10, appointment_ID);
            s.execute();
            return true;
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
        }
        return false;
    }

    /**
     * Function to find appointments that start within 15 minutes from now
     *
     * @return observable list of appointments starting withing 15 minutes
     */
    public static ObservableList<Appointment> FifteenMinutes() {
        String statement = "SELECT Appointment_ID, Start FROM appointments WHERE appointments.start >= now() and appointments.start <= date_add(now(), interval 15 minute);";
        ObservableList<Appointment> appts = FXCollections.observableArrayList();

        try {
            Connection connection = JDBC.getConnection();
            ResultSet s = connection.createStatement().executeQuery(statement);
            while (s.next()) {
                Appointment a = new Appointment(s.getInt("Appointment_ID"), s.getDate("Start").toLocalDate(), s.getTimestamp("Start").toLocalDateTime());

                appts.add(a);
            }
            return appts;
        } catch (SQLException sqlExc) {
            sqlExc.printStackTrace();
        }
        return null;
    }
}

