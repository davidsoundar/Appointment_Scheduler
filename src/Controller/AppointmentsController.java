package Controller;

import Model.Appointment;
import javafx.event.ActionEvent;

import java.io.IOException;

import static Controller.MainController.returnToMain;

public class AppointmentsController {
    public void ClickCancel(ActionEvent actionEvent) throws IOException {
        returnToMain(actionEvent);
    }

    public void appointmentData(Appointment appointment) {
    }
}
