package hsenid.webapp;

/**
 * Created by hsenid.
 * @author Kasun Dinesh
 */
public class User {
    private String username;
    private String password;

    /**
     * @param username username for the new user object
     * @param password password for the new user object
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
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