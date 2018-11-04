/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.Interfaces.IUser;
import DAL.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Stephan
 */
public class User extends Person implements Serializable, IUser {

    private int userID;
    private String username;
    private String password;
    private accountTypeState accountType;
    public enum accountTypeState { NORMAL, ADMIN, ADMIN_REQUESTED, ADMIN_REJECTED, ADMIN_NORMAL, BANNED, RESTRICTED, NOT_SET };

    public User(String firstName, String lastName, String title, Date dateOfBirth, String gender, String country, String province, String city, String street,
            String postalCode, String addressLine, String email, String cellNumber, String telNumber, Date dateAdded, int userID,
            String username, String password, String accountType, String idNumber) {
        super(idNumber, firstName, lastName, title, dateOfBirth, gender, country, province, city, street, postalCode, addressLine, email, cellNumber, telNumber, dateAdded);
        this.userID = userID;
        this.username = username;
        this.password = password;
        switch (accountType.toUpperCase()) {
            case "NORMAL":
                this.accountType = accountTypeState.NORMAL;
                break;
            case "ADMIN":
                this.accountType = accountTypeState.ADMIN;
                break;
            case "ADMINREQUESTED":
                this.accountType = accountTypeState.ADMIN_REQUESTED;
                break;
            case "ADMIN_REQUESTED":
                this.accountType = accountTypeState.ADMIN_REQUESTED;
                break;
            case "ADMINREJECTED":
                this.accountType = accountTypeState.ADMIN_REJECTED;
                break;
            case "ADMIN_REJECTED":
                this.accountType = accountTypeState.ADMIN_REJECTED;
                break;
            case "ADMINNORMAL":
                this.accountType = accountTypeState.ADMIN_NORMAL;
                break;
            case "ADMIN_NORMAL":
                this.accountType = accountTypeState.ADMIN_NORMAL;
                break;
            case "BANNED":
                this.accountType = accountTypeState.BANNED;
                break;
            case "RESTRICTED":
                this.accountType = accountTypeState.RESTRICTED;
                break;
            default:
                this.accountType = accountTypeState.NOT_SET;
                break;
        }
    }

    public User(int userID, String username, String password, String accountType, String idNumber, String firstName, String lastName, String title, Date dateOfBirth, String gender, String country, Date dateAdded) {
        super(idNumber, firstName, lastName, title, dateOfBirth, gender, country, dateAdded);
        this.userID = userID;
        this.username = username;
        this.password = password;
        switch (accountType.toUpperCase()) {
            case "NORMAL":
                this.accountType = accountTypeState.NORMAL;
                break;
            case "ADMIN":
                this.accountType = accountTypeState.ADMIN;
                break;
            case "ADMINREQUESTED":
                this.accountType = accountTypeState.ADMIN_REQUESTED;
                break;
            case "ADMIN_REQUESTED":
                this.accountType = accountTypeState.ADMIN_REQUESTED;
                break;
            case "ADMINREJECTED":
                this.accountType = accountTypeState.ADMIN_REJECTED;
                break;
            case "ADMIN_REJECTED":
                this.accountType = accountTypeState.ADMIN_REJECTED;
                break;
            case "ADMINNORMAL":
                this.accountType = accountTypeState.ADMIN_NORMAL;
                break;
            case "ADMIN_NORMAL":
                this.accountType = accountTypeState.ADMIN_NORMAL;
                break;
            case "BANNED":
                this.accountType = accountTypeState.BANNED;
                break;
            case "RESTRICTED":
                this.accountType = accountTypeState.RESTRICTED;
                break;
            default:
                this.accountType = accountTypeState.NOT_SET;
                break;
        }
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

    public accountTypeState getAccountType() {
        return accountType;
    }
    
    public void setAccountType(accountTypeState accountType) {
        this.accountType = accountType;
    }

//    public String getAccountType() {
//        switch (accountType) {
//            case NORMAL:
//                return "Normal";
//            case ADMIN:
//                return "Admin";
//            case ADMIN_REQUESTED:
//                return "AdminRequested";
//            case ADMIN_REJECTED:
//                return "AdminRejected";
//            case ADMIN_NORMAL:
//                return "AdminNormal";
//            case BANNED:
//                return "Banned";
//            case RESTRICTED:
//                return "Restricted";
//            default:
//                return "NotSet";
//        }
//    }

//    public void setAccountType(String accountType) {
//        switch (accountType) {
//            case "Normal":
//                this.accountType = accountTypeState.NORMAL;
//                break;
//            case "Admin":
//                this.accountType = accountTypeState.ADMIN;
//                break;
//            case "AdminRequested":
//                this.accountType = accountTypeState.ADMIN_REQUESTED;
//                break;
//            case "AdminRejected":
//                this.accountType = accountTypeState.ADMIN_REJECTED;
//                break;
//            case "AdminNormal":
//                this.accountType = accountTypeState.ADMIN_NORMAL;
//                break;
//            case "Banned":
//                this.accountType = accountTypeState.BANNED;
//                break;
//            case "Restricted":
//                this.accountType = accountTypeState.RESTRICTED;
//                break;
//            default:
//                this.accountType = accountTypeState.NOT_SET;
//                break;
//        }
//    }

    //METHODS
    //This method will return a integer code that will indicate the result of the authentication:
    //0 = Unknown unsuccessful login
    //1 = Invalid Username or Password characters
    //2 = User not found (database did not retrieve the user)
    //3 = User found but not authorized to login
    //4 = User login successful
    //5 = Admin login successful
    public static int AuthenticateLogin(String username, String password) {
        int userAuthed = 0;
        int usernameInputCode = Common.checkInput(username);
        int passwordInputCode = Common.checkInput(password);
        if ((usernameInputCode == 1) && ((passwordInputCode == 1) || (passwordInputCode == 4) || (passwordInputCode == 5) || (passwordInputCode == 7))) {
            userAuthed = 2;
            String[][] dbData = DataHandler.readRecords(Arrays.asList("UserID", "AccountType"), Arrays.<DataTablesCollection>asList(new DataTablesCollection("User")), Arrays.asList("Username='" + username + "'", "Password='" + password + "'"));
            if (dbData.length == 1) {
                userAuthed = 3;
                String AccountType = dbData[0][1].toUpperCase();
                if (AccountType.equals("NORMAL") || AccountType.equals("ADMIN_REQUESTED") || AccountType.equals("ADMIN_REJECTED")) {
                    userAuthed = 4;
                }
                if (AccountType.equals("ADMIN")) {
                    userAuthed = 5;
                }
            }
        } else {
            userAuthed = 1;
        }

        return userAuthed;
    }

    public static List<User> getUsers() {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList());
    }

    public static List<User> getUsersByAccountType(String accountType) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType='" + accountType + "'"));
    }

    public static List<User> getNonAdminUsers() {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType!='Admin'"));
    }

    public static User GetUser(String username) {
        User user = null;
        int usernameInputCode = Common.checkInput(username);
        if ((usernameInputCode == 1) || (usernameInputCode == 4)) {
            user = DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Username='" + username + "'")).get(0);
        }

        return user;
    }

    public static User GetUserByIdNumber(String idNumber) {
        User user = null;
        int usernameInputCode = Common.checkInput(idNumber);
        if (usernameInputCode == 2 || usernameInputCode == 10) {
            user = DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "IDNumber"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("PersonID='" + idNumber + "'")).get(0);
        }

        return user;
    }

    public static User GetUserByUserId(int userId) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "IDNumber"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("UserID=" + userId)).get(0);
    }

    public static User GetUserByLoginDetails(String username, String password) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "IDNumber"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Username= '" + username + "' AND  Password= '" + password + "'")).get(0);
    }

    public void registerUser() {
        //Person
        this.registerPerson();

        //User
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("PersonID");
        columns.add("Username");
        columns.add("Password");
        if (this.getAccountType() != accountTypeState.NOT_SET) {
            columns.add("AccountType");
        }

        //Values
        String values = "";
        values += "string#" + this.getIdNumber();
        values += ";string#" + this.getUsername();
        values += ";string#" + this.getPassword();
        if (this.getAccountType() != accountTypeState.NOT_SET) {
            values += ";string#" + this.getAccountType();
        }

        //Execute
        DataHandler.createRecords(columns, "User", Arrays.asList(values));
    }

    public static void registerUser(User user) {
        //Person
        user.registerPerson();

        //User
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("PersonID");
        columns.add("Username");
        columns.add("Password");
        if (user.getAccountType() != accountTypeState.NOT_SET) {
            columns.add("AccountType");
        }

        //Values
        String values = "";
        values += "string#" + user.getIdNumber();
        values += ";string#" + user.getUsername();
        values += ";string#" + user.getPassword();
        if (user.getAccountType() != accountTypeState.NOT_SET) {
            values += ";string#" + user.getAccountType();
        }

        //Execute
        DataHandler.createRecords(columns, "User", Arrays.asList(values));
    }

    public void updateUser() {
        //Person
        this.updatePerson();

        //User
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Username");
        if (this.getPassword().length() > 0) {
            columns.add("Password");
        }
        columns.add("AccountType");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + this.getUsername());
        if (this.getPassword().length() > 0) {
            values.add("string;" + this.getPassword());
        }
        values.add("string;" + this.getAccountType());

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("UserID=" + this.getUserID());

        //Execute
        DataHandler.updateRecords("User", columns, values, conditions);
    }

    public static void updateUser(User user) {
        //Person
        user.updatePerson();

        //User
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Username");
        columns.add("Password");
        columns.add("AccountType");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + user.getUsername());
        values.add("string;" + user.getPassword());
        values.add("string;" + user.getAccountType());

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("UserID=" + user.getUserID());

        //Execute
        DataHandler.updateRecords("User", columns, values, conditions);
    }

    public void deleteUser() {
        //Person
        this.deletePerson();

        //User        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("UserID=" + this.getUserID());
        conditions.add("PersonID='" + this.getIdNumber() + "'");
        conditions.add("Username='" + this.getUsername() + "'");
        conditions.add("Password='" + this.getPassword() + "'");
        conditions.add("AccountType='" + this.getAccountType() + "'");

        //Execute
        DataHandler.deleteRecords("User", conditions);
    }

    public static void deleteUser(User user) {
        //Person
        user.deletePerson();

        //User        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("UserID=" + user.getUserID());
        conditions.add("PersonID='" + user.getIdNumber() + "'");
        conditions.add("Username='" + user.getUsername() + "'");
        conditions.add("Password='" + user.getPassword() + "'");
        conditions.add("AccountType='" + user.getAccountType() + "'");

        //Execute
        DataHandler.deleteRecords("User", conditions);
    }

    public static void deleteUser(String idNumber, int userID) {
        //Person
        Person.deletePerson(idNumber);

        //User        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("UserID=" + userID);

        //Execute
        DataHandler.deleteRecords("User", conditions);
    }

    public static List<User> getUsersByParameterExplicit(String columnToSearch, String parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList(columnToSearch + " = '" + parameter + "'"));
    }

    public static List<User> getNonAdminUsersByParameterExplicit(String columnToSearch, String parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType!='Admin'", columnToSearch + " = '" + parameter + "'"));
    }

    public static List<User> getUsersByParameter(String columnToSearch, String parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList(columnToSearch + " LIKE '%" + parameter + "%'"));
    }

    public static List<User> getNonAdminUsersByParameter(String columnToSearch, String parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType!='Admin'", columnToSearch + " LIKE '%" + parameter + "%'"));
    }

    public static List<User> getUsersByParameter(String columnToSearch, int parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList(columnToSearch + " = " + parameter));
    }

    public static List<User> getNonAdminUsersByParameter(String columnToSearch, int parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType!='Admin'", columnToSearch + " = " + parameter));
    }

    public static List<User> getUsersByFirstName(String firstName) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("FirstName LIKE '%" + firstName + "%'"));
    }

    public static List<User> getUsersByLastName(String lastName) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("LastName LIKE '%" + lastName + "%'"));
    }

    public static List<User> getUsersByFullName(String Fullname) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("CONCAT(FirstName, ' ', LastName) LIKE '%" + Fullname + "%'"));
    }

    public static List<User> getUsersByCountry(String country) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Country LIKE '%" + country + "%'"));
    }

    public static List<User> getUsersByCity(String city) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("City LIKE '%" + city + "%'"));
    }

    public static List<User> getUsersByProvince(String province) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Province LIKE '%" + province + "%'"));
    }

    public static List<User> getUsersByTelNumber(String telNum) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("TelNumber LIKE '%" + telNum + "%'"));
    }

    public static List<User> getUsersByCellNumber(String celNum) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("CellNumber LIKE '%" + celNum + "%'"));
    }

    public static List<User> getUsersByEmail(String email) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Email LIKE '%" + email + "%'"));
    }
}
