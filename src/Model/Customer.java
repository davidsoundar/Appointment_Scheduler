package Model;

/**
 * Class for a customer
 */
public class Customer {
    private int customer_ID;
    private String customer_Name;
    private String address;
    private String Postal_Code;
    private String country;
    private String Phone;
    private String division;
    private int division_ID;
    private int country_ID;

    /**
     * Constructor for a customer
     * @param customer_ID
     * @param customer_Name
     * @param address
     * @param postal_Code
     * @param country
     * @param phone
     * @param division_ID
     */
    public Customer(int customer_ID, String customer_Name, String address, String postal_Code, String country, String phone, String division, int division_ID, int country_ID) {
        this.customer_ID = customer_ID;
        this.customer_Name = customer_Name;
        this.address = address;
        Postal_Code = postal_Code;
        this.country = country;
        Phone = phone;
        this.division = division;
        this.division_ID = division_ID;
        this.country_ID = country_ID;
    }

    /**
     * Constructor for
     * @param customerID
     * @param customerName
     */
    public Customer(int customerID, String customerName) {
        customer_ID = customerID;
        customer_Name = customerName;
    }

    /**
     * customer ID getter
     * @return customer ID
     */
    public int getCustomer_ID() {
        return customer_ID;
    }

    /**
     * customer ID setter
     * @param customer_ID
     */
    public void setCustomer_ID(int customer_ID) {
        this.customer_ID = customer_ID;
    }

    /**
     * customer name getter
     * @return customer name
     */
    public String getCustomer_Name() {
        return customer_Name;
    }

    /**
     * Customer name setter
     * @param customer_Name
     */
    public void setCustomer_Name(String customer_Name) {
        this.customer_Name = customer_Name;
    }

    /**
     * Address getter
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Address setter
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * postal code getter
     * @return postal code
     */
    public String getPostal_Code() {
        return Postal_Code;
    }

    /**
     * postal code setter
     * @param postal_Code
     */
    public void setPostal_Code(String postal_Code) {
        Postal_Code = postal_Code;
    }

    /**
     * country getter
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Country setter
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * phone getter
     * @return phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * phone setter
     * @param phone
     */
    public void setPhone(String phone) {
        Phone = phone;
    }

    /**
     * division ID getter
     * @return
     */
    public int getDivision_ID() {
        return division_ID;
    }

    /**
     * division ID setter
     * @param division_ID
     */
    public void setDivision_ID(int division_ID) {
        this.division_ID = division_ID;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public int getCountry_ID() {
        return country_ID;
    }

    public void setCountry_ID(int country_ID) {
        this.country_ID = country_ID;
    }
}
