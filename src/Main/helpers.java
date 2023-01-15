package Main;

import javafx.scene.control.Alert;

/**
 * Class that contains the alert function
 */
public class helpers {

    /**
     * Function that turns a string into an alert
     * @param text input text to turn into alert
     */
    public static void alert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
