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
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    //<editor-fold defaultstate="collapsed" desc="Override Methods">
    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", department=" + department + ", username=" + username + ", password=" + password + ", accountType=" + accountType + "}, " + super.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.userID;
        hash = 23 * hash + Objects.hashCode(this.department);
        hash = 23 * hash + Objects.hashCode(this.username);
        hash = 23 * hash + Objects.hashCode(this.password);
        hash = 23 * hash + Objects.hashCode(this.accountType);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userID != other.userID) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.department, other.department)) {
            return false;
        }
        if (this.accountType != other.accountType) {
            return false;
        }
        return true;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="read Methods">
    public static List<User> read() {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList());
    }

    public static User read(int userId) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("UserID=" + userId)).get(0);
    }

    public static List<User> readByAccountType(String accountType) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType='" + accountType + "'"));
    }

    public static List<User> readNonAdminUsers() {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType!='Admin'"));
    }

    public static User readByUsername(String username) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Username='" + username + "'")).get(0);
    }

    public static User readByIdNumber(String idNumber) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("PersonID='" + idNumber + "'")).get(0);
    }

    public static User readByLoginDetails(String username, String password) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Username= '" + username + "'", "Password= '" + password + "'")).get(0);
    }

    public static List<User> readByParameterExplicit(String columnToSearch, String parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList(columnToSearch + " = '" + parameter + "'"));
    }

    public static List<User> readNonAdminUsersByParameterExplicit(String columnToSearch, String parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType!='Admin'", columnToSearch + " = '" + parameter + "'"));
    }

    public static List<User> readByParameter(String columnToSearch, int parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList(columnToSearch + " = " + parameter));
    }

    public static List<User> readNonAdminUsersByParameter(String columnToSearch, int parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType!='Admin'", columnToSearch + " = " + parameter));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="search Methods">
    public static List<User> searchByParameter(String columnToSearch, String parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList(columnToSearch + " LIKE '%" + parameter + "%'"));
    }

    public static List<User> getNonAdminUsersByParameter(String columnToSearch, String parameter) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("AccountType!='Admin'", columnToSearch + " LIKE '%" + parameter + "%'"));
    }

    public static List<User> getUsersByFirstName(String firstName) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("FirstName LIKE '%" + firstName + "%'"));
    }

    public static List<User> getUsersByLastName(String lastName) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("LastName LIKE '%" + lastName + "%'"));
    }

    public static List<User> getUsersByFullName(String Fullname) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("CONCAT(FirstName, ' ', LastName) LIKE '%" + Fullname + "%'"));
    }

    public static List<User> getUsersByCountry(String country) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Country LIKE '%" + country + "%'"));
    }

    public static List<User> getUsersByCity(String city) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("City LIKE '%" + city + "%'"));
    }

    public static List<User> getUsersByProvince(String province) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Province LIKE '%" + province + "%'"));
    }

    public static List<User> getUsersByTelNumber(String telNum) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("TelNumber LIKE '%" + telNum + "%'"));
    }

    public static List<User> getUsersByCellNumber(String celNum) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("CellNumber LIKE '%" + celNum + "%'"));
    }

    public static List<User> getUsersByEmail(String email) {
        return DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Email LIKE '%" + email + "%'"));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="create Methods">
    @Override
    public void create() {
        try {
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
            values += ";string#" + Common.encryptPassword(this.getPassword());
            if (this.getAccountType() != accountTypeState.NOT_SET) {
                values += ";string#" + this.getAccountType();
            }
            
            //Execute
            DataHandler.createRecords(columns, "User", Arrays.asList(values));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void create(User user) {
        try {
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
            values += ";string#" + Common.encryptPassword(user.getPassword());
            if (user.getAccountType() != accountTypeState.NOT_SET) {
                values += ";string#" + user.getAccountType();
            }
            
            //Execute
            DataHandler.createRecords(columns, "User", Arrays.asList(values));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="update Methods">
    @Override
    public void update() {
        try {
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
                    values.add("string;" + Common.encryptPassword(this.getPassword()));
            }
            values.add("string;" + this.getAccountType());

            //Conditions
            ArrayList<String> conditions = new ArrayList<>();
            conditions.add("UserID=" + this.getUserID());

            //Execute
            DataHandler.updateRecords("User", columns, values, conditions);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void update(User user) {
        try {
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
                values.add("string;" + Common.encryptPassword(user.getPassword()));
            }
            values.add("string;" + user.getAccountType());

            //Conditions
            ArrayList<String> conditions = new ArrayList<>();
            conditions.add("UserID=" + user.getUserID());

            //Execute
            DataHandler.updateRecords("User", columns, values, conditions);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    @Override
    public boolean synchronise() {
        //User
        boolean foundInDatabase = false;

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        if (this.getUserID() > 0) {
            conditions.add("UserID=" + this.getUserID());
        } else {
            conditions.add("Department='" + this.getDepartment().getDepartmentID() + "'");
            conditions.add("Username='" + this.getUsername() + "'");
            conditions.add("AccountType='" + this.getAccountType().name() + "'");
        }

        //Retrieving new instance
        List<User> users = DataHandler.<User>readRecords(User.class, Arrays.asList("UserID", "Department", "Username", "Password", "AccountType", "IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), conditions);

        //Synchronising
        if (users.size() == 1) {
            foundInDatabase = true;
            User user = users.get(0);

            this.setUserID(user.getUserID());
            this.setDepartment(user.getDepartment());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setAccountType(user.getAccountType());
        }

        return foundInDatabase;
    }
    
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
        //int passwordInputCode = Common.checkInput(password);
        boolean proceed = false;
        if ((usernameInputCode == 1)){
            try {
                String[][] pass = DataHandler.readRecords(Arrays.asList("Password"), Arrays.<DataTablesCollection>asList(new DataTablesCollection("User")), Arrays.asList("Username='" + username + "'"));
                proceed = Common.validatePassword(password, pass[0][0]);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            userAuthed = 1;
        }
            
        if ((usernameInputCode == 1) && proceed) {
            userAuthed = 2;
            String[][] dbData = DataHandler.readRecords(Arrays.asList("UserID", "AccountType"), Arrays.<DataTablesCollection>asList(new DataTablesCollection("User")), Arrays.asList("Username='" + username + "'"));
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
