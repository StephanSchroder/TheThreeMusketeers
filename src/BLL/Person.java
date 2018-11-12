/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

//<editor-fold defaultstate="collapsed" desc="imports">
import BLL.Interfaces.DatabaseOperations;
import DAL.*;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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
    
    public Person(String idNumber, String firstName, String lastName, String title, Date dateOfBirth, String gender, Address address, Contact contact) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = address;
        this.contact = contact;
    }

    public Person(String idNumber, String firstName, String lastName, String title, Date dateOfBirth, String gender, int address, int contact) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.address = Address.read(address);
        this.contact = Contact.read(contact);
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

    public String getDateOfBirthString() {
        return new SimpleDateFormat("yyyy-MM-dd").format(dateOfBirth);
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

    public String getDateAddedString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateAdded);
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Override Methods">
    @Override    
    public String toString() {
        return "Person{" + "idNumber=" + idNumber + ", firstName=" + firstName + ", lastName=" + lastName + ", title=" + title + ", dateOfBirth=" + this.getDateOfBirthString() + ", gender=" + gender + ", address=" + address + ", contact=" + contact + ", dateAdded=" + this.getDateAddedString()+ '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.idNumber);
        hash = 29 * hash + Objects.hashCode(this.firstName);
        hash = 29 * hash + Objects.hashCode(this.lastName);
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.dateOfBirth);
        hash = 29 * hash + Objects.hashCode(this.gender);
        hash = 29 * hash + Objects.hashCode(this.address);
        hash = 29 * hash + Objects.hashCode(this.contact);
        hash = 29 * hash + Objects.hashCode(this.dateAdded);
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.idNumber, other.idNumber)) {
            return false;
        }
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.gender, other.gender)) {
            return false;
        }
        if (!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        return true;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="read Methods">
    public static List<Person> readPerson() {
        return DataHandler.<Person>readRecords(Person.class, Arrays.asList("IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person")), Arrays.asList());
    }
    
    public static Person readPerson(String idNumber) {
        return DataHandler.<Person>readRecords(Person.class, Arrays.asList("IDNumber", "FirstName", "LastName", "Title", "DateOfBirth", "Gender", "AddressID", "ContactID", "DateAdded"), Arrays.asList(new DataTablesCollection("Person")), Arrays.asList("IDNumber='" + idNumber + "'")).get(0);
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="create Methods">
    @Override
    public void create() {
        this.getAddress().create();
        this.getAddress().synchronise();
        this.getContact().create();
        this.getContact().synchronise();
        
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
        values += ";string#" + this.getDateOfBirthString();
        values += ";string#" + this.getGender();
        values += ";int#" + this.getAddress().getAddressID();
        values += ";int#" + this.getContact().getContactID();

        //Execute
        DataHandler.createRecords(columns, "Person", Arrays.asList(values));
    }

    public static void create(Person person) {
        person.getAddress().create();
        person.getAddress().synchronise();
        person.getContact().create();
        person.getContact().synchronise();
        
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
        values += ";string#" + person.getDateOfBirthString();
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
        this.getAddress().update();
        this.getAddress().synchronise();
        this.getContact().update();
        this.getContact().synchronise();
        
        //Person
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("FirstName");
        columns.add("LastName");
        columns.add("Title");
        columns.add("Gender");
        columns.add("AddressID");
        columns.add("ContactID");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + this.getFirstName());
        values.add("string;" + this.getLastName());
        values.add("string;" + this.getTitle());
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
        person.getAddress().update();
        person.getAddress().synchronise();
        person.getContact().update();
        person.getContact().synchronise();
        
        //Person
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("FirstName");
        columns.add("LastName");
        columns.add("Title");
        columns.add("Gender");
        columns.add("AddressID");
        columns.add("ContactID");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + person.getFirstName());
        values.add("string;" + person.getLastName());
        values.add("string;" + person.getTitle());
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
        this.getAddress().delete();
        this.getContact().delete();
        
        //Person
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("FirstName='" + this.getFirstName() + "'");
        conditions.add("LastName='" + this.getLastName() + "'");
        conditions.add("Title='" + this.getTitle() + "'");
        conditions.add("DateOfBirth='" + this.getDateOfBirthString() + "'");
        conditions.add("Gender='" + this.getGender() + "'");
        conditions.add("AddressID=" + this.getAddress().getAddressID());
        conditions.add("ContactID=" + this.getContact().getContactID());

        //Execute
        DataHandler.deleteRecords("Person", conditions);
    }

    public static void delete(Person person) {
        person.getAddress().delete();
        person.getContact().delete();
        
        //Person       
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("FirstName='" + person.getFirstName() + "'");
        conditions.add("LastName='" + person.getLastName() + "'");
        conditions.add("Title='" + person.getTitle() + "'");
        conditions.add("DateOfBirth='" + person.getDateOfBirthString() + "'");
        conditions.add("Gender='" + person.getGender() + "'");
        conditions.add("AddressID=" + person.getAddress().getAddressID());
        conditions.add("ContactID=" + person.getContact().getContactID());

        //Execute
        DataHandler.deleteRecords("Person", conditions);
    }

    public static void delete(String idNumber) {
        Person person = readPerson(idNumber);
        person.getAddress().delete();
        person.getContact().delete();
        
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
        //Person
        boolean foundInDatabase = false;

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        if (!this.getIdNumber().isEmpty()) {
            conditions.add("IDNumber='" + this.getIdNumber() + "'");
        } else {
            conditions.add("FirstName='" + this.getFirstName() + "'");
            conditions.add("LastName='" + this.getLastName() + "'");
            conditions.add("Title='" + this.getTitle() + "'");
            conditions.add("DateOfBirth='" + this.getDateOfBirthString() + "'");
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
