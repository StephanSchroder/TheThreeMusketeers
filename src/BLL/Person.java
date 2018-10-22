/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Stephan
 */
public abstract class Person {
    private String idNumber;
    private String firstName;
    private String lastName;
    private String title;
    private Date dateOfBirth;
    private String gender;
    private String country;
    private String province;
    private String city;
    private String street;
    private String postalCode;
    private String addressLine;
    private String email;
    private String cellNumber;
    private String telNumber;
    private Date dateAdded;

    public Person(String idNumber, String firstName, String lastName, String title, Date dateOfBirth, String gender, String country, String province, String city, String street, String postalCode, String addressLine, String email, String cellNumber, String telNumber, Date dateAdded) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.addressLine = addressLine;
        this.email = email;
        this.cellNumber = cellNumber;
        this.telNumber = telNumber;
        this.dateAdded = dateAdded;
    }

    public Person(String idNumber, String firstName, String lastName, String title, Date dateOfBirth, String gender, String country, Date dateAdded) {
        this.idNumber = idNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.country = country;
        this.dateAdded = dateAdded;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellNumber() {
        return cellNumber;
    }

    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    public void registerPerson() {
        //Person
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("IDNumber");
        columns.add("FirstName");
        columns.add("LastName");
        columns.add("Title");
        columns.add("DateOfBirth");
        columns.add("Gender");
        columns.add("Country");
        if (!this.getProvince().isEmpty()) { columns.add("Province"); }
        if (!this.getCity().isEmpty()) { columns.add("City"); }
        if (!this.getStreet().isEmpty()) { columns.add("Street"); }
        if (!this.getPostalCode().isEmpty()) { columns.add("PostalCode"); }
        if (!this.getAddressLine().isEmpty()) { columns.add("AddressLine"); }
        if (!this.getEmail().isEmpty()) { columns.add("Email"); }
        if (!this.getCellNumber().isEmpty()) { columns.add("CellNumber"); }
        if (!this.getTelNumber().isEmpty()) { columns.add("TelNumber"); }
        
        //Values
        String values = "";
        values += "string#" + this.getIdNumber();
        values += ";string#" + this.getFirstName();
        values += ";string#" + this.getLastName();
        values += ";string#" + this.getTitle();
        values += ";string#" + new SimpleDateFormat("yyyy-MM-dd").format(this.getDateOfBirth());
        values += ";string#" + this.getGender();
        values += ";string#" + this.getCountry();
        if (!this.getProvince().isEmpty()) { values += ";string#" + this.getProvince(); }
        if (!this.getCity().isEmpty()) { values += ";string#" + this.getCity(); }
        if (!this.getStreet().isEmpty()) { values += ";string#" + this.getStreet(); }
        if (!this.getPostalCode().isEmpty()) { values += ";string#" + this.getPostalCode(); }
        if (!this.getAddressLine().isEmpty()) { values += ";string#" + this.getAddressLine(); }
        if (!this.getEmail().isEmpty()) { values += ";string#" + this.getEmail(); }
        if (!this.getCellNumber().isEmpty()) { values += ";string#" + this.getCellNumber(); }
        if (!this.getTelNumber().isEmpty()) { values += ";string#" + this.getTelNumber(); }
        
        //Execute
        DataHandler.createRecords(columns, "Person", Arrays.asList(values));
    }
    
    public static void registerPerson(Person person) {
        //Person
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("IDNumber");
        columns.add("FirstName");
        columns.add("LastName");
        columns.add("Title");
        columns.add("DateOfBirth");
        columns.add("Gender");
        columns.add("Country");
        if (!person.getProvince().isEmpty()) { columns.add("Province"); }
        if (!person.getCity().isEmpty()) { columns.add("City"); }
        if (!person.getStreet().isEmpty()) { columns.add("Street"); }
        if (!person.getPostalCode().isEmpty()) { columns.add("PostalCode"); }
        if (!person.getAddressLine().isEmpty()) { columns.add("AddressLine"); }
        if (!person.getEmail().isEmpty()) { columns.add("Email"); }
        if (!person.getCellNumber().isEmpty()) { columns.add("CellNumber"); }
        if (!person.getTelNumber().isEmpty()) { columns.add("TelNumber"); }
        
        //Values
        String values = "";
        values += "string#" + person.getIdNumber();
        values += ";string#" + person.getFirstName();
        values += ";string#" + person.getLastName();
        values += ";string#" + person.getTitle();
        values += ";string#" + person.getDateOfBirth();
        values += ";string#" + person.getGender();
        values += ";string#" + person.getCountry();
        if (!person.getProvince().isEmpty()) { values += ";string#" + person.getProvince(); }
        if (!person.getCity().isEmpty()) { values += ";string#" + person.getCity(); }
        if (!person.getStreet().isEmpty()) { values += ";string#" + person.getStreet(); }
        if (!person.getPostalCode().isEmpty()) { values += ";string#" + person.getPostalCode(); }
        if (!person.getAddressLine().isEmpty()) { values += ";string#" + person.getAddressLine(); }
        if (!person.getEmail().isEmpty()) { values += ";string#" + person.getEmail(); }
        if (!person.getCellNumber().isEmpty()) { values += ";string#" + person.getCellNumber(); }
        if (!person.getTelNumber().isEmpty()) { values += ";string#" + person.getTelNumber(); }
        
        //Execute
        DataHandler.createRecords(columns, "Person", Arrays.asList(values));
    }
    
    public void updatePerson() {
        //Person
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("FirstName");
        columns.add("LastName");
        columns.add("Title");
        columns.add("Country");
        if (!this.getProvince().isEmpty()) { columns.add("Province"); }
        if (!this.getCity().isEmpty()) { columns.add("City"); }
        if (!this.getStreet().isEmpty()) { columns.add("Street"); }
        if (!this.getPostalCode().isEmpty()) { columns.add("PostalCode"); }
        if (!this.getAddressLine().isEmpty()) { columns.add("AddressLine"); }
        if (!this.getEmail().isEmpty()) { columns.add("Email"); }
        if (!this.getCellNumber().isEmpty()) { columns.add("CellNumber"); }
        if (!this.getTelNumber().isEmpty()) { columns.add("TelNumber"); }
        
        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + this.getFirstName());
        values.add("string;" + this.getLastName());
        values.add("string;" + this.getTitle());
        values.add("string;" + this.getCountry());
        if (!this.getProvince().isEmpty()) { values.add("string;" + this.getProvince()); }
        if (!this.getCity().isEmpty()) { values.add("string;" + this.getCity()); }
        if (!this.getStreet().isEmpty()) { values.add("string;" + this.getStreet()); }
        if (!this.getPostalCode().isEmpty()) { values.add("string;" + this.getPostalCode()); }
        if (!this.getAddressLine().isEmpty()) { values.add("string;" + this.getAddressLine()); }
        if (!this.getEmail().isEmpty()) { values.add("string;" + this.getEmail()); }
        if (!this.getCellNumber().isEmpty()) { values.add("string;" + this.getCellNumber()); }
        if (!this.getTelNumber().isEmpty()) { values.add("string;" + this.getTelNumber()); }
        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("IDNumber='" + this.getIdNumber() + "'");
        
        //Execute
        DataHandler.updateRecords("Person", columns, values, conditions);
    }
    
    public static void updatePerson(Person person) {
        //Person
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("FirstName");
        columns.add("LastName");
        columns.add("Title");
        columns.add("Country");
        if (!person.getProvince().isEmpty()) { columns.add("Province"); }
        if (!person.getCity().isEmpty()) { columns.add("City"); }
        if (!person.getStreet().isEmpty()) { columns.add("Street"); }
        if (!person.getPostalCode().isEmpty()) { columns.add("PostalCode"); }
        if (!person.getAddressLine().isEmpty()) { columns.add("AddressLine"); }
        if (!person.getEmail().isEmpty()) { columns.add("Email"); }
        if (!person.getCellNumber().isEmpty()) { columns.add("CellNumber"); }
        if (!person.getTelNumber().isEmpty()) { columns.add("TelNumber"); }
        
        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + person.getFirstName());
        values.add("string;" + person.getLastName());
        values.add("string;" + person.getTitle());
        values.add("string;" + person.getCountry());
        if (!person.getProvince().isEmpty()) { values.add("string;" + person.getProvince()); }
        if (!person.getCity().isEmpty()) { values.add("string;" + person.getCity()); }
        if (!person.getStreet().isEmpty()) { values.add("string;" + person.getStreet()); }
        if (!person.getPostalCode().isEmpty()) { values.add("string;" + person.getPostalCode()); }
        if (!person.getAddressLine().isEmpty()) { values.add("string;" + person.getAddressLine()); }
        if (!person.getEmail().isEmpty()) { values.add("string;" + person.getEmail()); }
        if (!person.getCellNumber().isEmpty()) { values.add("string;" + person.getCellNumber()); }
        if (!person.getTelNumber().isEmpty()) { values.add("string;" + person.getTelNumber()); }
        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("IDNumber='" + person.getIdNumber() + "'");
        
        //Execute
        DataHandler.updateRecords("Person", columns, values, conditions);
    }
    
    public void deletePerson() {
        //Person
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("IDNumber='" + this.getIdNumber() + "'");
        conditions.add("FirstName='" + this.getFirstName()+ "'");
        conditions.add("LastName='" + this.getLastName()+ "'");
        conditions.add("Title='" + this.getTitle()+ "'");
        conditions.add("DateOfBirth='" + this.getDateOfBirth()+ "'");
        conditions.add("Gender='" + this.getGender()+ "'");
        conditions.add("Country='" + this.getCountry()+ "'");
        if (!this.getProvince().isEmpty()) { conditions.add("Province='" + this.getProvince()+ "'"); }
        if (!this.getCity().isEmpty()) { conditions.add("City='" + this.getCity()+ "'"); }
        if (!this.getStreet().isEmpty()) { conditions.add("Street='" + this.getStreet()+ "'"); }
        if (!this.getPostalCode().isEmpty()) { conditions.add("PostalCode='" + this.getPostalCode()+ "'"); }
        if (!this.getAddressLine().isEmpty()) { conditions.add("AddressLine='" + this.getAddressLine()+ "'"); }
        if (!this.getEmail().isEmpty()) { conditions.add("Email='" + this.getEmail()+ "'"); }
        if (!this.getCellNumber().isEmpty()) { conditions.add("CellNumber='" + this.getCellNumber()+ "'"); }
        if (!this.getTelNumber().isEmpty()) { conditions.add("TelNumber='" + this.getTelNumber() + "'"); }
        
        //Execute
        DataHandler.deleteRecords("Person", conditions);
    }
    
    public static void deletePerson(Person person) {
        //Person       
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("IDNumber='" + person.getIdNumber() + "'");
        conditions.add("FirstName='" + person.getFirstName()+ "'");
        conditions.add("LastName='" + person.getLastName()+ "'");
        conditions.add("Title='" + person.getTitle()+ "'");
        conditions.add("DateOfBirth='" + person.getDateOfBirth()+ "'");
        conditions.add("Gender='" + person.getGender()+ "'");
        conditions.add("Country='" + person.getCountry()+ "'");
        if (!person.getProvince().isEmpty()) { conditions.add("Province='" + person.getProvince()+ "'"); }
        if (!person.getCity().isEmpty()) { conditions.add("City='" + person.getCity()+ "'"); }
        if (!person.getStreet().isEmpty()) { conditions.add("Street='" + person.getStreet()+ "'"); }
        if (!person.getPostalCode().isEmpty()) { conditions.add("PostalCode='" + person.getPostalCode()+ "'"); }
        if (!person.getAddressLine().isEmpty()) { conditions.add("AddressLine='" + person.getAddressLine()+ "'"); }
        if (!person.getEmail().isEmpty()) { conditions.add("Email='" + person.getEmail()+ "'"); }
        if (!person.getCellNumber().isEmpty()) { conditions.add("CellNumber='" + person.getCellNumber()+ "'"); }
        if (!person.getTelNumber().isEmpty()) { conditions.add("TelNumber='" + person.getTelNumber() + "'"); }
        
        //Execute
        DataHandler.deleteRecords("Person", conditions);
    }
    
    public static void deletePerson(String idNumber) {
        //Person       
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("IDNumber='" + idNumber + "'");
        
        //Execute
        DataHandler.deleteRecords("Person", conditions);
    }
}
