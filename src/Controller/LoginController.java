package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

import static Controller.MainController.returnToMain;
import static DB.UsersSQL.validuserpass;
import static Main.helpers.alert;

public class LoginController {

    public Label LoginLocation;
    public Label InfoLabel;
    public Button loginButton;
    public Label userLogin;
    public Label PassLogin;
    public Label Location;
    public TextField usernameLoginText;
    public TextField PasswordLoginText;

    private final ResourceBundle res = ResourceBundle.getBundle("lang/lang", Locale.getDefault());
    public Label Login2;

    /**
     * Changes the language of the login screen
     */
    public void initialize() {
        LoginLocation.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
        loginButton.setText(res.getString("login"));
        userLogin.setText(res.getString("username"));
        PassLogin.setText(res.getString("password"));
        Login2.setText(res.getString("login2"));
    }

    /**
     * Checks if username or password is missing
     * @return bool
     */
    public Boolean notMissing() {
        if (usernameLoginText.getText().isEmpty() || PasswordLoginText.getText().isEmpty()) {
            InfoLabel.setText(res.getString("missing"));
            return false;
        }
        return true;
    }


    public void ClickLoginButton(ActionEvent event) throws IOException, SQLException {

        if (!notMissing()) return;
        boolean check = validuserpass(usernameLoginText.getText(), PasswordLoginText.getText());
        if (check) {
            validuserpass(usernameLoginText.getText(), PasswordLoginText.getText());
            textfile(usernameLoginText.getText() + " logged in in at ");

            returnToMain(event);
        }
        else {
            InfoLabel.setText(res.getString("error"));
            textfile(usernameLoginText.getText() + " failed to login at ");
        }
    }

    private void textfile(String s) throws IOException {
        try (FileWriter file = new FileWriter("login-activity.txt", true)) {
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat t = new SimpleDateFormat("MM-dd-yyyy HH:mm");
            file.write(s + t.format(date) + "\n");
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }


    }

