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

public class LoginController implements Initializable{

    public Label LoginLocation;
    public Label InfoLabel;
    public Button loginButton;
    public Label usernameLogin;
    public Label PasswordLogin;
    public Label Location;
    public TextField usernameLoginText;
    public TextField PasswordLoginText;
    private ResourceBundle res = ResourceBundle.getBundle("lang/lang", Locale.getDefault());



    public Boolean missing() {
        if (usernameLoginText.getText().isEmpty() || PasswordLoginText.getText().isEmpty()) {
            InfoLabel.setText(res.getString("missing"));
            return false;
        }
        return true;
    }


    public void ClickLoginButton(ActionEvent event) throws IOException, SQLException {

        if (!missing()) return;
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

    /**
     * Changes the language of the login screen
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            LoginLocation.setText(String.valueOf(ZoneId.of(TimeZone.getDefault().getID())));
            loginButton.setText(res.getString("login"));
            usernameLogin.setText(res.getString("username"));
            PasswordLogin.setText(res.getString("password"));
        }
    }

