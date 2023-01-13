package Model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * model for a appointments
 */
public class Appointment {
    private int appointment_ID;
    private String title;
    private String type;
    private String description;
    private String location;
    private LocalDate start_date;
    private LocalDateTime start_time;
    private LocalDate end_date;
    private LocalDateTime end_time;
    private int customer_ID;
    private int User_ID;
    private String contact_name;
    private int contact_ID;

    public Appointment(int appointment_ID, String title, String type, String description, String location, LocalDate start_date, LocalDateTime start_time, LocalDate end_date, LocalDateTime end_time, int customer_ID, int user_ID, String contact_name, int contact_ID) {
        this.appointment_ID = appointment_ID;
        this.title = title;
        this.type = type;
        this.description = description;
        this.location = location;
        this.start_date = start_date;
        this.start_time = start_time;
        this.end_date = end_date;
        this.end_time = end_time;
        this.customer_ID = customer_ID;
        User_ID = user_ID;
        this.contact_name = contact_name;
        this.contact_ID = contact_ID;
    }

    /**
     * appointment ID getter
     * @return appointment ID
     */
    public int getAppointment_ID() {
        return appointment_ID;
    }

    /**
     * appointment ID setter
     * @param appointment_ID
     */
    public void setAppointment_ID(int appointment_ID) {
        this.appointment_ID = appointment_ID;
    }

    /**
     * title getter
     * @return appointment title
     */
    public String getTitle() {
        return title;
    }

    /**
     * title setter
     * @param title appointment title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * appointment type getter
     * @return appointment type
     */
    public String getType() {
        return type;
    }

    /**
     * appointment type setter
     * @param type appointment type
     */
    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDateTime getStart_time() {
        return start_time;
    }

    public void setStart_time(LocalDateTime start_time) {
        this.start_time = start_time;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public LocalDateTime getEnd_time() {
        return end_time;
    }

    public void setEnd_time(LocalDateTime end_time) {
        this.end_time = end_time;
    }

    public int getCustomer_ID() {
        return customer_ID;
    }

    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public int getContact_ID() {
        return contact_ID;
    }

    public void setContact_ID(int contact_ID) {
        this.contact_ID = contact_ID;
    }
}
