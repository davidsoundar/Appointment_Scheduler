package Controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

import static Main.helpers.alert;

public class LoginController {

    public Label LoginLocation;
    private ResourceBundle res = ResourceBundle.getBundle("lang", Locale.getDefault());

    /**
     * Changes the language of the login screen
     */
    public void initialize() {
        LoginLocation.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));


    }
}
