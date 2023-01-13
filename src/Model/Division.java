package Model;

/**
 * model for divisions
 */
public class Division {
    private int division_ID;
    private int country_ID;
    private String Division;

    /**
     * Constructor for division
     * @param division_ID
     * @param country_ID
     * @param division
     */
    public Division(int division_ID, int country_ID, String division) {
        this.division_ID = division_ID;
        this.country_ID = country_ID;
        Division = division;
    }

    /**
     * division ID getter
     * @return division ID
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

    /**
     * country ID getter
     * @return country ID
     */
    public int getCountry_ID() {
        return country_ID;
    }

    /**
     * country ID setter
     * @param country_ID
     */
    public void setCountry_ID(int country_ID) {
        this.country_ID = country_ID;
    }

    /**
     * Getter for division
     * @return division
     */
    public String getDivision() {
        return Division;
    }

    /**
     * Setter for division
     * @param division
     */
    public void setDivision(String division) {
        Division = division;
    }
}
