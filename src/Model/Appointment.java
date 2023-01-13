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

}
