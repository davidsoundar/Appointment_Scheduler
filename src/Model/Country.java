package Model;

/**
 * model for country
 */
public class Country {
    private int country_ID;
    private String country;

    /**
     * Constructor for a country
     * @param country_ID int
     * @param country string
     */
    public Country(int country_ID, String country) {
        this.country_ID = country_ID;
        this.country = country;
    }

    /**
     * country ID getter
     * @return country id
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
     * country getter
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * country setter
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }
}

