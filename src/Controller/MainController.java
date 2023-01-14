package Controller;

import Model.Appointment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;

import static Main.helpers.alert;


/**
 * Controller for the main screen
 */
public class MainController {

    public TableView<Appointment> appointmentsTable;

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

