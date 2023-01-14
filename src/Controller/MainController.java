package Controller;

import Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

import static DB.AppointmentSQL.makeAppointmentListForMain;
import static DB.CustomerSQL.customersData;
import static Main.helpers.alert;


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

    @FXML
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

    }

    /**
     * public function to return to the main screen
     * @param actionEvent button click
     * @throws IOException
     */
    public static void returnToMain(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(MainController.class.getResource("/Views/MainView.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ClickUpdateAppointment(ActionEvent event) throws IOException {
        Appointment appointment = appointmentsTable.getSelectionModel().getSelectedItem();
        if (appointment != null) {
            FXMLLoader loader = new FXMLLoader((getClass()).getResource("Appointment.fxml"));
            Parent root = loader.load();
            AppointmentsController appointmentsController = loader.getController();
            appointmentsController.appointmentInfo(appointment);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
        else {
            alert("Select an appointment");
        }
    }
}

