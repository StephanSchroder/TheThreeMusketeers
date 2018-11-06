/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

//<editor-fold defaultstate="collapsed" desc="imports">
import BLL.Interfaces.DatabaseOperations;
import DAL.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//</editor-fold>

/**
 *
 * @author Stephan
 */
public abstract class Person implements DatabaseOperations {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private String idNumber;
    private String firstName;
    private String lastName;
    private String title;
    private Date dateOfBirth;
    private String gender;
    private Address address;
    private Contact contact;
    private Date dateAdded;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Person(String idNumber, String firstName, String lastName, String title, Date dateOfBirth, String gender, Address address, Contact contact, Date dateAdded) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
        this.dateAdded = dateAdded;
    }

    public Person(String idNumber, String firstName, String lastName, String title, Date dateOfBirth, String gender, int address, int contact, Date dateAdded) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = Address.read(address);
        this.contact = Contact.read(contact);
        this.dateAdded = dateAdded;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="create Methods">
    @Override
    public void create() {
        //Person
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("IDNumber");
        columns.add("FirstName");
        columns.add("LastName");
        columns.add("Title");
        columns.add("DateOfBirth");
        columns.add("Gender");
        columns.add("AddressID");
        columns.add("ContactID");

        //Values
        String values = "";
        values += "string#" + this.getIdNumber();
        values += ";string#" + this.getFirstName();
        values += ";string#" + this.getLastName();
        values += ";string#" + this.getTitle();
        values += ";string#" + new SimpleDateFormat("yyyy-MM-dd").format(this.getDateOfBirth());
        values += ";string#" + this.getGender();
        values += ";int#" + this.getAddress().getAddressID();
        values += ";int#" + this.getContact().getContactID();

        //Execute
        DataHandler.createRecords(columns, "Person", Arrays.asList(values));
    }

    public static void create(Person person) {
        //Person
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("IDNumber");
        columns.add("FirstName");
        columns.add("LastName");
        columns.add("Title");
        columns.add("DateOfBirth");
        columns.add("Gender");
        columns.add("AddressID");
        columns.add("ContactID");

        //Values
        String values = "";
        values += "string#" + person.getIdNumber();
        values += ";string#" + person.getFirstName();
        values += ";string#" + person.getLastName();
        values += ";string#" + person.getTitle();
        values += ";string#" + new SimpleDateFormat("yyyy-MM-dd").format(person.getDateOfBirth());
        values += ";string#" + person.getGender();
        values += ";int#" + person.getAddress().getAddressID();
        values += ";int#" + person.getContact().getContactID();

        //Execute
        DataHandler.createRecords(columns, "Person", Arrays.asList(values));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="update Methods">
    @Override
    public void update() {
        //Person
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("FirstName");
        columns.add("LastName");
        columns.add("Title");
        columns.add("DateOfBirth");
        columns.add("Gender");
        columns.add("AddressID");
        columns.add("ContactID");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + this.getFirstName());
        values.add("string;" + this.getLastName());
        values.add("string;" + this.getTitle());
        values.add("string;" + this.getDateOfBirth());
        values.add("string;" + this.getGender());
        values.add("int;" + this.getAddress().getAddressID());
        values.add("int;" + this.getContact().getContactID());

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("IDNumber='" + this.getIdNumber() + "'");

        //Execute
        DataHandler.updateRecords("Person", columns, values, conditions);
    }

    public static void update(Person person) {
        //Person
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("FirstName");
        columns.add("LastName");
        columns.add("Title");
        columns.add("DateOfBirth");
        columns.add("Gender");
        columns.add("AddressID");
        columns.add("ContactID");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + person.getFirstName());
        values.add("string;" + person.getLastName());
        values.add("string;" + person.getTitle());
        values.add("string;" + person.getDateOfBirth());
        values.add("string;" + person.getGender());
        values.add("int;" + person.getAddress().getAddressID());
        values.add("int;" + person.getContact().getContactID());

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("IDNumber='" + person.getIdNumber() + "'");

        //Execute
        DataHandler.updateRecords("Person", columns, values, conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="delete Methods">
    @Override
    public void delete() {
        //Person
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("FirstName='" + this.getFirstName() + "'");
        conditions.add("LastName='" + this.getLastName() + "'");
        conditions.add("Title='" + this.getTitle() + "'");
        conditions.add("DateOfBirth='" + this.getDateOfBirth() + "'");
        conditions.add("Gender='" + this.getGender() + "'");
        conditions.add("AddressID=" + this.getAddress().getAddressID());
        conditions.add("ContactID=" + this.getContact().getContactID());

        //Execute
        DataHandler.deleteRecords("Person", conditions);
    }

    public static void delete(Person person) {
        //Person       
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("FirstName='" + person.getFirstName() + "'");
        conditions.add("LastName='" + person.getLastName() + "'");
        conditions.add("Title='" + person.getTitle() + "'");
        conditions.add("DateOfBirth='" + person.getDateOfBirth() + "'");
        conditions.add("Gender='" + person.getGender() + "'");
        conditions.add("AddressID=" + person.getAddress().getAddressID());
        conditions.add("ContactID=" + person.getContact().getContactID());

        //Execute
        DataHandler.deleteRecords("Person", conditions);
    }

    public static void delete(String idNumber) {
        //Person       
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("IDNumber='" + idNumber + "'");

        //Execute
        DataHandler.deleteRecords("Person", conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="synchronise Methods">
    @Override
    public boolean synchronise() {
        //Campus
        boolean foundInDatabase = false;

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        if (!this.getIdNumber().isEmpty()) {
            conditions.add("IDNumber='" + this.getIdNumber() + "'");
        } else {
            conditions.add("FirstName='" + this.getFirstName() + "'");
            conditions.add("LastName='" + this.getLastName() + "'");
            conditions.add("Title='" + this.getTitle() + "'");
            conditions.add("DateOfBirth='" + this.getDateOfBirth() + "'");
            conditions.add("Gender='" + this.getGender() + "'");
            conditions.add("AddressID=" + this.getAddress().getAddressID());
            conditions.add("ContactID=" + this.getContact().getContactID());
        }

        //Retrieving new instance
        List<Person> persons = DataHandler.<Person>readRecords(Person.class, Arrays.asList("IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person")), conditions);

        //Synchronising
        if (persons.size() == 1) {
            foundInDatabase = true;
            Person person = persons.get(0);

            this.setIdNumber(person.getIdNumber());
            this.setFirstName(person.getFirstName());
            this.setLastName(person.getLastName());
            this.setTitle(person.getTitle());
            this.setDateOfBirth(person.getDateOfBirth());
            this.setGender(person.getGender());
            this.setAddress(person.getAddress());
            this.setContact(person.getContact());
            this.setDateAdded(person.getDateAdded());
        }

        return foundInDatabase;
    }
    //</editor-fold>

}

//NOTES:
//MAKE SURE DATES USE 'new SimpleDateFormat("yyyy-MM-dd").format(date);' OR 'new SimpleDateFormat("yyyy-MM-dd").parse(date);'
//MAKE SURE DATETIMES USE 'new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").format(dateTime);' OR 'new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").parse(dateTime);'
