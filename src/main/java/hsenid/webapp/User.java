package hsenid.webapp;

import java.security.PrivateKey;

/**
 * Created by hsenid.
 *
 * @author Kasun Dinesh
 */
public class User {
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String dob;
    private String userName;
    private String password;
    private String email;
    private long contactNo;

    public User(String userName) {
        this.userName = userName;
    }

    /**
     * @param username userName for the new user object
     * @param password password for the new user object
     */
    public User(String username, String password) {
        this.userName = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String country, String city, String dob, String userName, String password, String email, long contactNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.dob = dob;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.contactNo = contactNo;
    }

    public User(String firstName, String country, String dob, String userName, String password, String email, int contactNo) {
        this.firstName = firstName;
        this.country = country;
        this.dob = dob;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.contactNo = contactNo;
    }

    public User(String firstName, String lastName, String country, String dob, String userName, String email, long contactNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.dob = dob;
        this.userName = userName;
        this.email = email;
        this.contactNo = contactNo;
    }

    public User(String firstName, String lastName, String country, String dob, String email, long contactNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.dob = dob;
        this.email = email;
        this.contactNo = contactNo;
    }

    /**
     * @return the first name of the user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the last name of the user
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the country of the user
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country of the user
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the date of birth of the user
     */
    public String getDob() {
        return dob;
    }

    /**
     * @param dob the date of birth of the user
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * @return the contact no of the user
     */
    public long getContactNo() {
        return contactNo;
    }

    /**
     * @param contactNo the contact no of the user
     */
    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
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

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}