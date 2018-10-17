/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.DataHandler;
import DAL.DataTablesCollection;
import java.io.Serializable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Stephan
 */
public class User extends Person implements Serializable {
    private int userID;
    private String username;
    private String password;
    private String accountType;
    private static DataHandler db = new DataHandler();

    public User(String firstName, String lastName, String title, Date dateOfBirth, String gender, String country, String province, String city, String street, 
            String postalCode, String addressLine, String email, String cellNumber, String telNumber, Date dateAdded, int userID, 
            String username, String password, String accountType, String idNumber) {
        super(idNumber, firstName, lastName, title, dateOfBirth, gender, country, province, city, street, postalCode, addressLine, email, cellNumber, telNumber, dateAdded);
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.accountType = accountType;
    }

    public User(int userID, String username, String password, String accountType, String idNumber, String firstName, String lastName, String title, Date dateOfBirth, String gender, String country, Date dateAdded) {
        super(idNumber, firstName, lastName, title, dateOfBirth, gender, country, dateAdded);
        this.userID = userID;
        this.username = username;
        this.password = password;
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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    //METHODS
    //This method will return a integer code that will indicate the result of the authentication:
    //0 = Unknown unsuccessful login
    //1 = Invalid Username or Password characters
    //2 = User not found (database did not retrieve the user)
    //3 = User found but not authorized to login
    //4 = User login successful
    //4 = Admin login successful
    public static int AuthenticateLogin(String username, String password){
            int userAuthed = 0;
            int usernameInputCode = Common.CheckInput(username);
            int passwordInputCode = Common.CheckInput(password);
            if (((usernameInputCode == 1) || (usernameInputCode == 4)) && ((passwordInputCode == 1) || (passwordInputCode == 4) || (passwordInputCode == 5) || (passwordInputCode == 7)))
            {
                ResultSet dbData = db.readRecords(Arrays.asList("UserID", "AccountType"), Arrays.<DataTablesCollection>asList(new DataTablesCollection("User")), Arrays.asList("Username='" + username + "'", "Password='" + password + "'" ));
                int count = -1;
                try {
                    if (dbData.isBeforeFirst()) {
                        boolean b = dbData.last();
                        count = dbData.getRow();
                        dbData.beforeFirst();
                    }
                } catch (SQLException sqle) {
                    System.out.println("SQL Exception thrown and caught");
                }
                if (count == 1)
                {
                    userAuthed = 3;
                    String AccountType = "None";
                    try {
                        AccountType = dbData.getString(1);
                    } catch (SQLException sqle) {
                        System.out.println("SQL Exception thrown and caught");
                    }
                    if ((AccountType.equals("User")))
                    {
                        userAuthed = 4;
                    }
                    if ((AccountType.equals("Admin")))
                    {
                        userAuthed = 5;
                    }
                }
                else
                {
                    userAuthed = 2;
                }
            }
            else
            {
                userAuthed = 1;
            }
            return userAuthed;
    }
    
    public static User GetUser(String username) {
        User user = null;
        int usernameInputCode = Common.CheckInput(username);
        if ((usernameInputCode == 1) || (usernameInputCode == 4))
        {
            ResultSet dbData = db.readRecords(Arrays.asList("PersonID", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "ProvinceState", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("Username='" + username + "'"));
            int count = -1;
            try {
                boolean b = dbData.last();
                count = dbData.getRow();
                dbData.beforeFirst();
            } catch (SQLException sqle) {
                System.out.println("SQL Exception thrown and caught");
            }
            if (count == 1)
            {
                try {
                    user = new User(dbData.getString("FirstName"), dbData.getString("LastName"), dbData.getString("Title"), dbData.getDate("DateOfBirth"), dbData.getString("Gender"), dbData.getString("Country"), dbData.getString("Province"), dbData.getString("City"), dbData.getString("Street"), dbData.getString("PostalCode"), dbData.getString("AddressLine"), dbData.getString("Email"), dbData.getString("CellNumber"), dbData.getString("TelNumber"), dbData.getDate("DateAdded"), dbData.getInt("UserID"), dbData.getString("Username"), dbData.getString("Password"), dbData.getString("AccountType"), dbData.getString("IDNumber"));
                } catch (SQLException sqle) {
                    System.out.println("SQL Exception was thrown and caught");
                }
            }
        }

        return user;
    }
    
    public static User GetUserByIdNumber(String idNumber) {
        User user = null;
        int usernameInputCode = Common.CheckInput(idNumber);
        if (usernameInputCode == 2)
        {
            ResultSet dbData = db.readRecords(Arrays.asList("IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Email", "CellNumber", "TelNumber", "DateAdded", "UserID", "Username", "Password", "AccountType"), Arrays.asList(new DataTablesCollection("Person"), new DataTablesCollection("User", "Person", "PersonID", "IDNumber", "INNER JOIN")), Arrays.asList("IDNumber='" + idNumber + "'"));
            int count = -1;
            try {
                boolean b = dbData.last();
                count = dbData.getRow();
                dbData.beforeFirst();
            } catch (SQLException sqle) {
                System.out.println("SQL Exception thrown and caught");
            }
            if (count == 1)
            {
                try {
                    user = new User(dbData.getString("FirstName"), dbData.getString("LastName"), dbData.getString("Title"), dbData.getDate("DateOfBirth"), dbData.getString("Gender"), dbData.getString("Country"), dbData.getString("Province"), dbData.getString("City"), dbData.getString("Street"), dbData.getString("PostalCode"), dbData.getString("AddressLine"), dbData.getString("Email"), dbData.getString("CellNumber"), dbData.getString("TelNumber"), dbData.getDate("DateAdded"), dbData.getInt("UserID"), dbData.getString("Username"), dbData.getString("Password"), dbData.getString("AccountType"), dbData.getString("IDNumber"));
                } catch (SQLException sqle) {
                    System.out.println("SQL Exception was thrown and caught");
                }
            }
        }

        return user;
    }
    
    public LinkedList<User> readUsers() {
        LinkedList<User> list = null;
        return null;
    }
    
    public User readUser() {
        User user = null;
        return user;
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
        if (!this.getAccountType().isEmpty()) { columns.add("AccountType"); }
        
        //Values
        String values = "";
        values += "string#" + this.getIdNumber();
        values += ";string#" + this.getUsername();
        values += ";string#" + this.getPassword();
        if (!this.getAccountType().isEmpty()) { values += ";string#" + this.getAccountType(); }
        
        //Execute
        db.createRecords(columns, "User", Arrays.asList(values));
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
        if (!user.getAccountType().isEmpty()) { columns.add("AccountType"); }
        
        //Values
        String values = "";
        values += "string#" + user.getIdNumber();
        values += ";string#" + user.getUsername();
        values += ";string#" + user.getPassword();
        if (!user.getAccountType().isEmpty()) { values += ";string#" + user.getAccountType(); }
        
        //Execute
        db.createRecords(columns, "User", Arrays.asList(values));
    }
    
    public void updateUser() {
        //Person
        this.updatePerson();
        
        //User
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Username");
        columns.add("Password");
        columns.add("AccountType");
        
        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + this.getUsername());
        values.add("string;" + this.getPassword());
        values.add("string;" + this.getAccountType());
        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("UserID=" + this.getUserID());
        conditions.add("PersonID='" + this.getIdNumber()+ "'");
        conditions.add("Username='" + this.getUsername()+ "'");
        conditions.add("Password='" + this.getPassword()+ "'");
        conditions.add("AccountType='" + this.getAccountType()+ "'");
        
        //Execute
        db.updateRecords("User", columns, values, conditions);
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
        conditions.add("PersonID='" + user.getIdNumber()+ "'");
        conditions.add("Username='" + user.getUsername()+ "'");
        conditions.add("Password='" + user.getPassword()+ "'");
        conditions.add("AccountType='" + user.getAccountType()+ "'");
        
        //Execute
        db.updateRecords("User", columns, values, conditions);
    }
    
    public void deleteUser() {
        //Person
        this.deletePerson();
        
        //User        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("UserID=" + this.getUserID());
        conditions.add("PersonID='" + this.getIdNumber()+ "'");
        conditions.add("Username='" + this.getUsername()+ "'");
        conditions.add("Password='" + this.getPassword()+ "'");
        conditions.add("AccountType='" + this.getAccountType()+ "'");
        
        //Execute
        db.deleteRecords("User", conditions);
    }
    
    public static void deleteUser(User user) {
        //Person
        user.deletePerson();
        
        //User        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("UserID=" + user.getUserID());
        conditions.add("PersonID='" + user.getIdNumber()+ "'");
        conditions.add("Username='" + user.getUsername()+ "'");
        conditions.add("Password='" + user.getPassword()+ "'");
        conditions.add("AccountType='" + user.getAccountType()+ "'");
        
        //Execute
        db.deleteRecords("User", conditions);
    }
    
    public static void deleteUser(int idNumber, int userID) {
        //Person
        Person.deletePerson(idNumber);
        
        //User        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("UserID=" + userID);
        
        //Execute
        db.deleteRecords("User", conditions);
    }
}
