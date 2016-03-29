package hsenid.webapp;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class User {
    private String userName;
    private String password;

    /**
     * @param username userName for the new user object
     * @param password password for the new user object
     */
    public User(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}