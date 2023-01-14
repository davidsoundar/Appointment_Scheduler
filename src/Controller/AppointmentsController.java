package Controller;

import DB.AppointmentSQL;
import Model.Appointment;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static Controller.MainController.returnToMain;
import static DB.AppointmentSQL.appointmentByCustID;
import static Main.helpers.alert;

public class AppointmentsController {

    public TextField descriptionTextField;
    public TextField typeTextField;
    public TextField titleTextField;
    public TextField locationTextField;
    public DatePicker AppointmentStartDatePick;
    public DatePicker AppointmentEndDatePick;
    public ComboBox customerBox;
    public ComboBox contactBox;
    public ComboBox userBox;
    public ComboBox AppointmentStartTimeBox;
    public ComboBox AppointmentEndTimeBox;
    public Button SaveButtonAppointment;
    public TextField AppointmentIDText;

    /**
     * Clicking the cancel button returns to the main menu
     * @param actionEvent cancel button click
     * @throws IOException
     */
    public void ClickCancel(ActionEvent actionEvent) throws IOException {
        returnToMain(actionEvent);
    }

    /**
     * Makes sure box has only int
     * @param box which box
     * @return int
     */
    public int OnlyIntAppointmentBox(ComboBox box) {
        String string = String.valueOf(box.getValue());
        String string2 = string.replaceAll("\\D+","");
        return Integer.parseInt(string2);
    }

    /**
     * Used in the AppointmentConflict function to set ID
     * @return ID
     */
    public int ID() {
        int ID;
        if (AppointmentIDText.getText().isEmpty()) {
            ID = 0;
        }
        else {
            ID = Integer.parseInt(AppointmentIDText.getText());
        }
        return ID;
    }

    /**
     * Lambda function is used inside the anyMatch() method. It compares the start and end time of the new  appointment to the input appointment and returns true if appointments are conflicting.
     * Checks if two appointments are conflicting
     * @return true if conflicting
     */
   public boolean AppointmentConflict() throws SQLException {
        LocalDateTime start = LocalDateTime.of(AppointmentStartDatePick.getValue(), LocalTime.parse(AppointmentStartTimeBox.getSelectionModel().getSelectedItem().toString()));
        LocalDateTime end = LocalDateTime.of(AppointmentEndDatePick.getValue(), LocalTime.parse(AppointmentEndTimeBox.getSelectionModel().getSelectedItem().toString()));

        int appointment_ID = ID();
       ObservableList<Appointment> appointments = appointmentByCustID(OnlyIntAppointmentBox(customerBox));

       if (appointments == null) {
           return false;
       }
       return appointments.stream().anyMatch(appointment -> appointment.getAppointment_ID() != appointment_ID && appointment.getStart_time().isBefore(end) && (start.isBefore(appointment.getEnd_time())));
    }

    public void ClickSaveButtonAppointment(ActionEvent event) throws IOException, SQLException {
        if (titleTextField.getText().isEmpty() ||
                descriptionTextField.getText().isEmpty() ||
                locationTextField.getText().isEmpty() ||
                typeTextField.getText().isEmpty() ||
                contactBox.getValue().toString().isEmpty() ||
                AppointmentStartTimeBox.getValue().toString().isEmpty() ||
                AppointmentEndTimeBox.getValue().toString().isEmpty() ||
                customerBox.getValue().toString().isEmpty() ||
                userBox.getValue().toString().isEmpty() ||
                AppointmentStartDatePick.getValue() == null ||
                AppointmentEndDatePick == null) {
            alert("Missing field information");
            return;
        }
        else if ((LocalDateTime.of(AppointmentStartDatePick.getValue(), LocalTime.parse(AppointmentStartTimeBox.getSelectionModel().getSelectedItem().toString()))).isAfter(LocalDateTime.of(AppointmentEndDatePick.getValue(), LocalTime.parse(AppointmentEndTimeBox.getSelectionModel().getSelectedItem().toString())))) {
            alert("Start time must be before end time");
            return;
        }
        else if (AppointmentConflict()) {
            alert("There are conflicting appointment times");
            return;
        }
        else

    }

    /**
     *
     * @param appointment
     */
    public void appointmentData(Appointment appointment) {
    }


}
