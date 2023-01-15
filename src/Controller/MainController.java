package Controller;

import Model.Appointment;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static DB.AppointmentSQL.*;
import static DB.CustomerSQL.*;
import static Main.helpers.alert;
import static javafx.fxml.FXMLLoader.load;


/**
 * Controller for the main screen
 */
public class MainController {

    public TableView<Appointment> appointmentsTable;
    public TableColumn MainAppointmentIDCol;
    public TableColumn MainAppointmentTitleCol;
    public TableColumn MainAppointmentTypeCol;
    public TableColumn MainAppointmentDescriptionCol;
    public TableColumn MainAppointmentLocationCol;
    public TableColumn MainAppointmentStartCol;
    public TableColumn MainAppointmentEndCol;
    public TableColumn MainAppointmentCustomerCol;
    public TableColumn MainAppointmentUserCol;
    public TableColumn MainAppointmentContactCol;
    public TableColumn MainCustomerIDCol;
    public TableColumn MainCustomerNameCol;
    public TableColumn MainCustomerAddressCol;
    public TableColumn MainCustomerPostalCol;
    public TableColumn MainCustomerCountryCol;
    public TableColumn MainCustomerDivisionCol;
    public TableColumn MainCustomerPhoneCol;
    public TableView<Customer> customersTable;
    public RadioButton MainAll;
    public RadioButton MainWeek;
    public RadioButton MainMonth;
    public Label immediate;

    public void initialize() {
        appointmentsTable.setItems(makeAppointmentListForMain(1));
        MainAppointmentIDCol.setCellValueFactory(new PropertyValueFactory<>("appointment_ID"));
        MainAppointmentTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        MainAppointmentTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        MainAppointmentDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("Description"));
        MainAppointmentLocationCol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        MainAppointmentStartCol.setCellValueFactory(new PropertyValueFactory<>("start_time"));
        MainAppointmentEndCol.setCellValueFactory(new PropertyValueFactory<>("end_time"));
        MainAppointmentCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        MainAppointmentUserCol.setCellValueFactory(new PropertyValueFactory<>("user_ID"));
        MainAppointmentContactCol.setCellValueFactory(new PropertyValueFactory<>("contact_name"));

        customersTable.setItems(customersData());
        MainCustomerIDCol.setCellValueFactory(new PropertyValueFactory<>("customer_ID"));
        MainCustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customer_Name"));
        MainCustomerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        MainCustomerPostalCol.setCellValueFactory(new PropertyValueFactory<>("Postal_Code"));
        MainCustomerCountryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        MainCustomerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
        MainCustomerDivisionCol.setCellValueFactory(new PropertyValueFactory<>("division"));

        ObservableList<Appointment> a = FXCollections.observableArrayList();
        ObservableList<Appointment> fifteenMinutes = FifteenMinutes();

        if (fifteenMinutes != null) {
            for (Appointment appt : fifteenMinutes) {
                a.add(appt);
                String s = "Appointment ID - " + appt.getAppointment_ID() + "\nDate-  " + appt.getStart_date() + "\nTime- " + appt.getStart_time().toLocalTime();
                immediate.setText(s);
            }
        }
        }

    /**
     * public function to return to the main screen
     *
     * @param actionEvent button click
     * @throws IOException
     */
    public static void returnToMain(ActionEvent actionEvent) throws IOException {
        Parent root = load(MainController.class.getResource("/Views/MainView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Function to go to the appointment view when update appointment is clicked
     *
     * @param event clicking update appointment
     * @throws IOException
     */
    @FXML
    void ClickUpdateAppointmentButton(ActionEvent event) throws IOException {
        Appointment appointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if (appointment != null) {
            FXMLLoader loader = new FXMLLoader((getClass()).getResource("/Views/AppointmentsView.fxml"));
            Parent root = loader.load();
            AppointmentsController appointmentsController = loader.getController();
            appointmentsController.appointmentInfo(appointment);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            alert("Select an appointment");
        }
    }

    /**
     * Clicking add appointment takes user to appointment view
     * @param event add click
     * @throws IOException
     */
    public void ClickAddAppointmentButton(ActionEvent event) throws IOException {
        Appointment appointment = null;
        FXMLLoader loader = new FXMLLoader((getClass()).getResource("/Views/AppointmentsView.fxml"));
        Parent root = loader.load();
        AppointmentsController appointmentsController = loader.getController();
        appointmentsController.appointmentInfo(appointment);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Deletes appointment if selected
     * @param event click delete
     */
    public void ClickDeleteAppointmentButton(ActionEvent event) {
        Appointment appt = appointmentsTable.getSelectionModel().getSelectedItem();
        if (appt != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Delete appointment " + appt.getAppointment_ID() +"?\nType: " + appt.getType());
            Optional<ButtonType> confirm = alert.showAndWait();

            if (confirm.get() == ButtonType.OK) {
                deleteAppointmentFromDB(appt.getAppointment_ID());
                appointmentsTable.setItems(makeAppointmentListForMain(1));
            }
        } else {
            alert("Select Appointment");
        }
    }


    /**
     * Function to go to the reports view when reports button is clicked
     *
     * @param event reports button clicked
     * @throws IOException
     */
    public void ClickReports(ActionEvent event) throws IOException {
        Parent root = load(getClass().getResource("/Views/ReportsView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void tg() {
        if (MainAll.isSelected()) {
            appointmentsTable.setItems(makeAppointmentListForMain(1));
        }
        if (MainWeek.isSelected()) {
            appointmentsTable.setItems(makeAppointmentListForMain(2));
        }
        if (MainMonth.isSelected()) {
            appointmentsTable.setItems(makeAppointmentListForMain(3));
        }

    }

    /**
     * Function to make clicking Add Customer button go to customer view
     *
     * @param event add click
     * @throws IOException
     * @throws SQLException
     */
    public void ClickCustAddButton(ActionEvent event) throws IOException, SQLException {
        Customer c = null;
        FXMLLoader loader = new FXMLLoader((getClass()).getResource("/Views/CustomerView.fxml"));
        Parent root = loader.load();
        CustomerController customerController = loader.getController();
        customerController.CustomerInfo(c);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * If a customer is selected will go to customer view. also sends data
     *
     * @param event update customer click
     */
    public void ClickCustUpdateButton(ActionEvent event) throws IOException {
        Customer c = customersTable.getSelectionModel().getSelectedItem();
        if (c != null) {
            FXMLLoader loader = new FXMLLoader((getClass()).getResource("/Views/CustomerView.fxml"));
            Parent root = loader.load();
            CustomerController customerController = loader.getController();
            customerController.CustomerInfo(c);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } else {
            alert("Select a customer");
        }
    }

    /**
     * Deletes customer and their appointments
     *
     * @param event
     */
    public void ClickCustDeleteButton(ActionEvent event) throws SQLException {
        Customer c = customersTable.getSelectionModel().getSelectedItem();

        if (c != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Delete customer " + c.getCustomer_Name() + " and their appointments?");
            Optional<ButtonType> confirm = alert.showAndWait();

            if (confirm.get() == ButtonType.OK) {
                CustRemoveSQL(c.getCustomer_ID());
                customersTable.setItems(customersData());
                appointmentsTable.setItems(makeAppointmentListForMain(1));
            }
        } else {
            alert("Select a Customer");
        }
    }
}

