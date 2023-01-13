package Model;

/**
 * model for a contact
 */
public class Contact {
    private int contact_ID;
    private String contact_name;
    private String email;

    /**
     * Constructor for a contact
     * @param contact_ID
     * @param contact_name
     * @param email
     */
    public Contact(int contact_ID, String contact_name, String email) {
        this.contact_ID = contact_ID;
        this.contact_name = contact_name;
        this.email = email;
    }

    /**
     * contact Id getter
     * @return contact ID
     */
    public int getContact_ID() {
        return contact_ID;
    }

    /**
     * contact ID setter
     * @param contact_ID
     */
    public void setContact_ID(int contact_ID) {
        this.contact_ID = contact_ID;
    }

    /**
     * contact name getter
     * @return contact name
     */
    public String getContact_name() {
        return contact_name;
    }

    /**
     * contact name setter
     * @param contact_name
     */
    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    /**
     * email getter
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * email setter
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
