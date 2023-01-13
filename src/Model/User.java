package Model;

/**
 * A model for users
 */
public class User {

    private int User_ID;
    private String User_Name;

    /**
     * Constructor for a user
     * @param userID
     * @param User_Name
     */
    public User(int userID, String User_Name) {
        this.User_ID = userID;
        this.User_Name = User_Name;
    }

    /**
     * User_ID getter
     * @return userID
     */
    public int getUserID() {
        return User_ID;
    }

    /**
     * Username getter
     * @return username
     */
    public String getUserName() {
        return User_Name;
    }
}

