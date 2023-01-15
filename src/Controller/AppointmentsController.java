package Controller;

import DB.AppointmentSQL;
import DB.ContactSQL;
import Model.Appointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static Controller.MainController.returnToMain;
import static DB.AppointmentSQL.*;
import static DB.ContactSQL.contactList;
import static DB.CustomerSQL.customerList;
import static DB.UsersSQL.getUsers;
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

    /**
     * Checks if fields are missing, appointments conflicting, and if the start and end times are valid before updating database
     * @param event Click save button
     * @throws IOException
     * @throws SQLException
     */
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
        else if (AppointmentIDText.getText().isEmpty()) {
            makeAppointmentForDB(titleTextField.getText(), typeTextField.getText(), descriptionTextField.getText(), locationTextField.getText(), LocalDateTime.of(AppointmentStartDatePick.getValue(), LocalTime.parse(AppointmentStartTimeBox.getSelectionModel().getSelectedItem().toString())), LocalDateTime.of(AppointmentEndDatePick.getValue(), LocalTime.parse(AppointmentEndTimeBox.getSelectionModel().getSelectedItem().toString())), OnlyIntAppointmentBox(customerBox), OnlyIntAppointmentBox(userBox), OnlyIntAppointmentBox(contactBox));
        }
        else try {
                updateAppointmentInDB(titleTextField.getText(), typeTextField.getText(), descriptionTextField.getText(), locationTextField.getText(), LocalDateTime.of(AppointmentStartDatePick.getValue(), LocalTime.parse(AppointmentStartTimeBox.getSelectionModel().getSelectedItem().toString())), LocalDateTime.of(AppointmentEndDatePick.getValue(), LocalTime.parse(AppointmentEndTimeBox.getSelectionModel().getSelectedItem().toString())), OnlyIntAppointmentBox(customerBox), OnlyIntAppointmentBox(userBox), OnlyIntAppointmentBox(contactBox), Integer.parseInt(AppointmentIDText.getText()));
            } catch (Exception exception) {
            exception.printStackTrace();
            }
        returnToMain(event);

    }

    /**
     * Lambda expressions used to iterate through contacts, users, and customers to create new lists containing ID and name. Function sends appointment info into the fields of the appointment view
     * @param appointment
     */
    public void appointmentInfo(Appointment appointment) {
        ObservableList<String> time = FXCollections.observableArrayList();
        try {
            ObservableList<String> con = contactList().stream().map(contact -> contact.getContact_ID() + ":" + contact.getContact_name()).collect(Collectors.toCollection(FXCollections::observableArrayList));
            contactBox.setItems(con);

            ObservableList<String> cus = customerList().stream().map(customer -> customer.getCustomer_ID() + ":" + customer.getCustomer_Name()).collect(Collectors.toCollection(FXCollections::observableArrayList));
            customerBox.setItems(cus);

            ObservableList<String> user = getUsers().stream().map(u -> u.getUserID() + ":" + u.getUserName()).collect(Collectors.toCollection(FXCollections::observableArrayList));
            userBox.setItems(user);

            LocalTime start = LocalTime.of(8, 0);
            LocalTime end = LocalTime.of(22, 0);
            time.add(start.toString());
            while (start.isBefore(end)) {
                start = start.plusMinutes(5);
                time.add(start.toString());
            }
        } catch (Exception exception) {
            exception.printStackTrace(); }

            AppointmentStartTimeBox.setItems(time);
            AppointmentEndTimeBox.setItems(time);

            if (appointment != null) try{
                AppointmentIDText.setText(Integer.toString(appointment.getAppointment_ID()));
                titleTextField.setText(appointment.getTitle());
                typeTextField.setText(appointment.getType());
                descriptionTextField.setText(appointment.getDescription());
                locationTextField.setText(appointment.getLocation());
                contactBox.setValue(appointment.getContact_ID());
                AppointmentStartDatePick.setValue(appointment.getStart_date());
                AppointmentStartTimeBox.setValue(appointment.getStart_time().toLocalTime());
                AppointmentEndDatePick.setValue(appointment.getEnd_date());
                AppointmentEndTimeBox.setValue(appointment.getEnd_time().toLocalTime());
                customerBox.setValue(appointment.getCustomer_ID());
                userBox.setValue(appointment.getUser_ID());

            } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
