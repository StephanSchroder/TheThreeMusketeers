/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.sql.Date;

/**
 *
 * @author Stephan
 */
public class User extends Person {
    private int userID;
    private String username;
    private String password;
    private Date lastLoggedIn;
    private String accountType;

    public User(String firstName, String lastName, String title, Date dateOfBirth, String gender, String country, String province, String city, String street, 
            String postalCode, String addressLine, String email, String cellNumber, String telNumber, Date dateAdded, Date lastUpdated, int userID, 
            String username, String password, Date lastLoggedIn, String accountType, String idNumber) {
        super(idNumber, firstName, lastName, title, dateOfBirth, gender, country, province, city, street, postalCode, addressLine, email, cellNumber, telNumber, dateAdded, lastUpdated);
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.lastLoggedIn = lastLoggedIn;
        this.accountType = accountType;
    }

    public User(int userID, String username, String password, Date lastLoggedIn, String accountType, String idNumber, String firstName, String lastName, String title, Date dateOfBirth, String gender, String country, Date dateAdded, Date lastUpdated) {
        super(idNumber, firstName, lastName, title, dateOfBirth, gender, country, dateAdded, lastUpdated);
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.lastLoggedIn = lastLoggedIn;
        this.accountType = accountType;
    }
    
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoggedIn() {
        return lastLoggedIn;
    }

    public void setLastLoggedIn(Date lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
