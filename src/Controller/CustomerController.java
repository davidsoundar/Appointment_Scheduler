package Controller;

import DB.FirstLevelDivisionSQL;
import Model.Country;
import Model.Customer;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

import static Controller.MainController.returnToMain;
import static DB.CountrySQL.countryList;


public class CustomerController {

    public ComboBox<String> CustomerCountryBox;
    public ComboBox<String> CustomerDivisionBox;
    public TextField CustomerPhoneText;
    public TextField CustomerPostalText;
    public TextField CustomerAddressText;
    public TextField CustomerIDText;
    public TextField CustomerNameText;
    ObservableList<String> countries = FXCollections.observableArrayList();

    /**
     * Puts the customer info in place in the customer view
     * @param cust
     */
    public void CustomerInfo (Customer cust) {
        try {
            ObservableList<Country> counts = countryList();
            if (counts != null) {
                for (Country co : counts) {
                    countries.add(co.getCountry());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        CustomerCountryBox.setItems(countries);

        if (cust != null) try {
            CustomerIDText.setText(Integer.toString(cust.getCustomer_ID()));
            CustomerNameText.setText(cust.getCustomer_Name());
            CustomerAddressText.setText(cust.getAddress());
            CustomerPostalText.setText(cust.getPostal_Code());
            CustomerCountryBox.setValue(cust.getCountry_ID() + ": " + cust.getCountry());
            CustomerPhoneText.setText(cust.getPhone());
            CustomerDivisionBox.setValue(cust.getDivision_ID() + ": " + cust.getDivision());

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Saves updates or adds new customers when save buttcn is clicked
     * @param event save button clicked
     */
    public void ClickCustomerSaveButton(ActionEvent event) {

    }

    /**
     * Cicking the cancel button sends the user back to main menu
     * @param event cancel button click
     * @throws IOException
     */
    public void ClickCustomerCancelButton(ActionEvent event) throws IOException {
        returnToMain(event);
    }

    /**
     * Clicking the country box fills it with values
     * @param event clicking country box
     */
    public void ClickCustomerCountryBox(ActionEvent event) {
        ObservableList<String> divisions = FXCollections.observableArrayList();

        try{
            ObservableList<Division> dsl = new FirstLevelDivisionSQL().getDivisionInfo(CustomerCountryBox.getSelectionModel().getSelectedItem());
            if (dsl != null) {
                for (Division division : dsl) {
                    divisions.add(division.getDivision_ID() + ": " + division.getDivision());
                }
            }
            CustomerDivisionBox.setItems(divisions);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
