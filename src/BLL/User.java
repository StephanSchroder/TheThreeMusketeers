/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

//<editor-fold defaultstate="collapsed" desc="imports">
import BLL.Interfaces.DatabaseOperations;
import DAL.*;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//</editor-fold>

/**
 *
 * @author Stephan
 */
public class User extends Person implements Serializable, DatabaseOperations {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private int userID;
    private Department department;
    private String username;
    private String password;
    private accountTypeState accountType;

    public enum accountTypeState {
        NORMAL, ADMIN, ADMIN_REQUESTED, ADMIN_REJECTED, ADMIN_NORMAL, BANNED, RESTRICTED, NOT_SET
    };
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public User(int userID, Department department, String username, String password, String accountType, String idNumber, String firstName, String lastName, String title, Date dateOfBirth, String gender, Address address, Contact contact, Date dateAdded) {
        super(idNumber, firstName, lastName, title, dateOfBirth, gender, address, contact, dateAdded);
        this.userID = userID;
        this.department = department;
        this.username = username;
        this.password = password;
        this.accountType = accountTypeState.NOT_SET;
        this.accountType = accountTypeState.valueOf(accountType.toUpperCase());
    }

    public User(int userID, int department, String username, String password, String accountType, String idNumber, String firstName, String lastName, String title, Date dateOfBirth, String gender, int address, int contact, Date dateAdded) {
        super(idNumber, firstName, lastName, title, dateOfBirth, gender, address, contact, dateAdded);
        this.userID = userID;
        this.department = Department.read(department);
        this.username = username;
        this.password = password;
        this.accountType = accountTypeState.NOT_SET;
        this.accountType = accountTypeState.valueOf(accountType.toUpperCase());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="read Methods">
    public static List<User> read() {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList());
    }

    public static User read(int userId) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "IDNumber"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("UserID=" + userId)).get(0);
    }

    public static List<User> readByAccountType(String accountType) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType='" + accountType + "'"));
    }

    public static List<User> readNonAdminUsers() {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType!='Admin'"));
    }

    public static User readByUsername(String username) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Username='" + username + "'")).get(0);
    }

    public static User readByIdNumber(String idNumber) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "IDNumber"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("PersonID='" + idNumber + "'")).get(0);
    }

    public static User readByLoginDetails(String username, String password) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "IDNumber"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Username= '" + username + "'", "Password= '" + password + "'")).get(0);
    }

    public static List<User> readByParameterExplicit(String columnToSearch, String parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList(columnToSearch + " = '" + parameter + "'"));
    }

    public static List<User> readNonAdminUsersByParameterExplicit(String columnToSearch, String parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType!='Admin'", columnToSearch + " = '" + parameter + "'"));
    }

    public static List<User> readByParameter(String columnToSearch, int parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList(columnToSearch + " = " + parameter));
    }

    public static List<User> readNonAdminUsersByParameter(String columnToSearch, int parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType!='Admin'", columnToSearch + " = " + parameter));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="search Methods">
    public static List<User> searchByParameter(String columnToSearch, String parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList(columnToSearch + " LIKE '%" + parameter + "%'"));
    }

    public static List<User> getNonAdminUsersByParameter(String columnToSearch, String parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType", "PersonID"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType!='Admin'", columnToSearch + " LIKE '%" + parameter + "%'"));
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="create Methods">
    @Override
    public void create() {
        //User
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("PersonID");
        columns.add("Department");
        columns.add("Username");
        columns.add("Password");
        if (this.getAccountType() != accountTypeState.NOT_SET) {
            columns.add("AccountType");
        }

        //Values
        String values = "";
        values += "string#" + this.getIdNumber();
        values += ";int#" + this.getDepartment().getDepartmentID();
        values += ";string#" + this.getUsername();
        values += ";string#" + this.getPassword();
        if (this.getAccountType() != accountTypeState.NOT_SET) {
            values += ";string#" + this.getAccountType();
        }

        //Execute
        DataHandler.createRecords(columns, "User", Arrays.asList(values));
    }

    public static void create(User user) {
        //User
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("PersonID");
        columns.add("Department");
        columns.add("Username");
        columns.add("Password");
        if (user.getAccountType() != accountTypeState.NOT_SET) {
            columns.add("AccountType");
        }

        //Values
        String values = "";
        values += "string#" + user.getIdNumber();
        values += ";int#" + user.getDepartment().getDepartmentID();
        values += ";string#" + user.getUsername();
        values += ";string#" + user.getPassword();
        if (user.getAccountType() != accountTypeState.NOT_SET) {
            values += ";string#" + user.getAccountType();
        }

        //Execute
        DataHandler.createRecords(columns, "User", Arrays.asList(values));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="update Methods">
    @Override
    public void update() {
        //User
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Department");
        if (this.getPassword().length() > 0) {
            columns.add("Password");
        }
        columns.add("AccountType");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("int;" + this.getDepartment().getDepartmentID());
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

    public static void update(User user) {
        //User
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Department");
        if (user.getPassword().length() > 0) {
            columns.add("Password");
        }
        columns.add("AccountType");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("int;" + user.getDepartment().getDepartmentID());
        if (user.getPassword().length() > 0) {
            values.add("string;" + user.getPassword());
        }
        values.add("string;" + user.getAccountType());

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("UserID=" + user.getUserID());

        //Execute
        DataHandler.updateRecords("User", columns, values, conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="delete Methods">
    @Override
    public void delete() {
        //User        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("PersonID='" + this.getIdNumber() + "'");
        conditions.add("Department=" + this.getDepartment().getDepartmentID());
        conditions.add("Username='" + this.getUsername() + "'");
        conditions.add("Password='" + this.getPassword() + "'");
        conditions.add("AccountType='" + this.getAccountType() + "'");

        //Execute
        DataHandler.deleteRecords("User", conditions);
    }

    public static void delete(User user) {
        //User        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("PersonID='" + user.getIdNumber() + "'");
        conditions.add("Department=" + user.getDepartment().getDepartmentID());
        conditions.add("Username='" + user.getUsername() + "'");
        conditions.add("Password='" + user.getPassword() + "'");
        conditions.add("AccountType='" + user.getAccountType() + "'");

        //Execute
        DataHandler.deleteRecords("User", conditions);
    }

    public static void delete(int userID) {
        //User        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("UserID=" + userID);

        //Execute
        DataHandler.deleteRecords("User", conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="other Methods">
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
    //</editor-fold>

}
