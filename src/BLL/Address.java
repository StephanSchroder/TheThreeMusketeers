/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

//<editor-fold defaultstate="collapsed" desc="imports">
import BLL.Interfaces.DatabaseOperations;
import DAL.DataHandler;
import DAL.DataTablesCollection;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
//</editor-fold>

/**
 *
 * @author Nico
 */
public class Address implements DatabaseOperations {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private int addressID;
    private String country;
    private String province;
    private String city;
    private String street;
    private String postalCode;
    private String addressLine;
    private String notes;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Address(int addressID, String country, String province, String city, String street, String postalCode, String addressLine, String notes) {
        this.addressID = addressID;
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.addressLine = addressLine;
        this.notes = notes;
    }

    public Address(int addressID, String country) {
        this.addressID = addressID;
        this.country = country;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Override Methods">
    @Override
    public String toString() {
        return "Address{" + "addressID=" + addressID + ", country=" + country + ", province=" + province + ", city=" + city + ", street=" + street + ", postalCode=" + postalCode + ", addressLine=" + addressLine + ", notes=" + notes + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.addressID;
        hash = 23 * hash + Objects.hashCode(this.country);
        hash = 23 * hash + Objects.hashCode(this.province);
        hash = 23 * hash + Objects.hashCode(this.city);
        hash = 23 * hash + Objects.hashCode(this.street);
        hash = 23 * hash + Objects.hashCode(this.postalCode);
        hash = 23 * hash + Objects.hashCode(this.addressLine);
        hash = 23 * hash + Objects.hashCode(this.notes);
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
        final Address other = (Address) obj;
        if (this.addressID != other.addressID) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.province, other.province)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.postalCode, other.postalCode)) {
            return false;
        }
        if (!Objects.equals(this.addressLine, other.addressLine)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        return true;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="read Methods">
    public static List<Address> read() {
        return DataHandler.<Address>readRecords(Address.class, Arrays.asList("AddressID", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Notes"), Arrays.asList(new DataTablesCollection("Address")), Arrays.asList());
    }

    public static Address read(int addressID) {
        return DataHandler.<Address>readRecords(Address.class, Arrays.asList("AddressID", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Notes"), Arrays.asList(new DataTablesCollection("Address")), Arrays.asList("AddressID=" + addressID)).get(0);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="create Methods">
    @Override
    public void create() {
        //Address
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Country");
        if (!this.getProvince().isEmpty()) {
            columns.add("Province");
        }
        if (!this.getCity().isEmpty()) {
            columns.add("City");
        }
        if (!this.getStreet().isEmpty()) {
            columns.add("Street");
        }
        if (!this.getPostalCode().isEmpty()) {
            columns.add("PostalCode");
        }
        if (!this.getAddressLine().isEmpty()) {
            columns.add("AddressLine");
        }
        if (!this.getNotes().isEmpty()) {
            columns.add("Notes");
        }

        //Values
        String values = "";
        values += "string#" + this.getCountry();
        if (!this.getProvince().isEmpty()) {
            values += ";string#" + this.getProvince();
        }
        if (!this.getCity().isEmpty()) {
            values += ";string#" + this.getCity();
        }
        if (!this.getStreet().isEmpty()) {
            values += ";string#" + this.getStreet();
        }
        if (!this.getPostalCode().isEmpty()) {
            values += ";string#" + this.getPostalCode();
        }
        if (!this.getAddressLine().isEmpty()) {
            values += ";string#" + this.getAddressLine();
        }
        if (!this.getNotes().isEmpty()) {
            values += ";string#" + this.getNotes();
        }

        //Execute
        DataHandler.createRecords(columns, "Address", Arrays.asList(values));
    }

    public static void create(Address address) {
        //Address
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Country");
        if (!address.getProvince().isEmpty()) {
            columns.add("Province");
        }
        if (!address.getCity().isEmpty()) {
            columns.add("City");
        }
        if (!address.getStreet().isEmpty()) {
            columns.add("Street");
        }
        if (!address.getPostalCode().isEmpty()) {
            columns.add("PostalCode");
        }
        if (!address.getAddressLine().isEmpty()) {
            columns.add("AddressLine");
        }
        if (!address.getNotes().isEmpty()) {
            columns.add("Notes");
        }

        //Values
        String values = "";
        values += "string#" + address.getCountry();
        if (!address.getProvince().isEmpty()) {
            values += ";string#" + address.getProvince();
        }
        if (!address.getCity().isEmpty()) {
            values += ";string#" + address.getCity();
        }
        if (!address.getStreet().isEmpty()) {
            values += ";string#" + address.getStreet();
        }
        if (!address.getPostalCode().isEmpty()) {
            values += ";string#" + address.getPostalCode();
        }
        if (!address.getAddressLine().isEmpty()) {
            values += ";string#" + address.getAddressLine();
        }
        if (!address.getNotes().isEmpty()) {
            values += ";string#" + address.getNotes();
        }

        //Execute
        DataHandler.createRecords(columns, "Address", Arrays.asList(values));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="update Methods">
    @Override
    public void update() {
        //Address
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Country");
        if (!this.getProvince().isEmpty()) {
            columns.add("Province");
        }
        if (!this.getCity().isEmpty()) {
            columns.add("City");
        }
        if (!this.getStreet().isEmpty()) {
            columns.add("Street");
        }
        if (!this.getPostalCode().isEmpty()) {
            columns.add("PostalCode");
        }
        if (!this.getAddressLine().isEmpty()) {
            columns.add("AddressLine");
        }
        if (!this.getNotes().isEmpty()) {
            columns.add("Notes");
        }

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + this.getCountry());
        if (!this.getProvince().isEmpty()) {
            values.add("string;" + this.getProvince());
        }
        if (!this.getCity().isEmpty()) {
            values.add("string;" + this.getCity());
        }
        if (!this.getStreet().isEmpty()) {
            values.add("string;" + this.getStreet());
        }
        if (!this.getPostalCode().isEmpty()) {
            values.add("string;" + this.getPostalCode());
        }
        if (!this.getAddressLine().isEmpty()) {
            values.add("string;" + this.getAddressLine());
        }
        if (!this.getNotes().isEmpty()) {
            values.add("string;" + this.getNotes());
        }

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("AddressID=" + this.getAddressID());

        //Execute
        DataHandler.updateRecords("Address", columns, values, conditions);
    }

    public static void update(Address address) {
        //Address
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Country");
        if (!address.getProvince().isEmpty()) {
            columns.add("Province");
        }
        if (!address.getCity().isEmpty()) {
            columns.add("City");
        }
        if (!address.getStreet().isEmpty()) {
            columns.add("Street");
        }
        if (!address.getPostalCode().isEmpty()) {
            columns.add("PostalCode");
        }
        if (!address.getAddressLine().isEmpty()) {
            columns.add("AddressLine");
        }
        if (!address.getNotes().isEmpty()) {
            columns.add("Notes");
        }

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + address.getCountry());
        if (!address.getProvince().isEmpty()) {
            values.add("string;" + address.getProvince());
        }
        if (!address.getCity().isEmpty()) {
            values.add("string;" + address.getCity());
        }
        if (!address.getStreet().isEmpty()) {
            values.add("string;" + address.getStreet());
        }
        if (!address.getPostalCode().isEmpty()) {
            values.add("string;" + address.getPostalCode());
        }
        if (!address.getAddressLine().isEmpty()) {
            values.add("string;" + address.getAddressLine());
        }
        if (!address.getNotes().isEmpty()) {
            values.add("string;" + address.getNotes());
        }

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("AddressID=" + address.getAddressID());

        //Execute
        DataHandler.updateRecords("Address", columns, values, conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="delete Methods">
    @Override
    public void delete() {
        //Address
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("Country='" + this.getCountry() + "'");
        if (!this.getProvince().isEmpty()) {
            conditions.add("Province='" + this.getProvince() + "'");
        }
        if (!this.getCity().isEmpty()) {
            conditions.add("City='" + this.getCity() + "'");
        }
        if (!this.getStreet().isEmpty()) {
            conditions.add("Street='" + this.getStreet() + "'");
        }
        if (!this.getPostalCode().isEmpty()) {
            conditions.add("PostalCode='" + this.getPostalCode() + "'");
        }
        if (!this.getAddressLine().isEmpty()) {
            conditions.add("AddressLine='" + this.getAddressLine() + "'");
        }
        if (!this.getNotes().isEmpty()) {
            conditions.add("Notes='" + this.getNotes() + "'");
        }

        //Execute
        DataHandler.deleteRecords("Address", conditions);
    }

    public static void delete(Address address) {
        //Address
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("Country='" + address.getCountry() + "'");
        if (!address.getProvince().isEmpty()) {
            conditions.add("Province='" + address.getProvince() + "'");
        }
        if (!address.getCity().isEmpty()) {
            conditions.add("City='" + address.getCity() + "'");
        }
        if (!address.getStreet().isEmpty()) {
            conditions.add("Street='" + address.getStreet() + "'");
        }
        if (!address.getPostalCode().isEmpty()) {
            conditions.add("PostalCode='" + address.getPostalCode() + "'");
        }
        if (!address.getAddressLine().isEmpty()) {
            conditions.add("AddressLine='" + address.getAddressLine() + "'");
        }
        if (!address.getNotes().isEmpty()) {
            conditions.add("Notes='" + address.getNotes() + "'");
        }

        //Execute
        DataHandler.deleteRecords("Address", conditions);
    }

    public static void delete(int addressID) {
        //Address
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("AddressID=" + addressID);

        //Execute
        DataHandler.deleteRecords("Address", conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="synchronise Methods">
    @Override
    public boolean synchronise() {
        //Address
        boolean foundInDatabase = false;

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        if (this.getAddressID() > 0) {
            conditions.add("AddressID=" + this.getAddressID());
        } else {
            conditions.add("Country='" + this.getCountry() + "'");
            if (!this.getProvince().isEmpty()) {
                conditions.add("Province='" + this.getProvince() + "'");
            }
            if (!this.getCity().isEmpty()) {
                conditions.add("City='" + this.getCity() + "'");
            }
            if (!this.getStreet().isEmpty()) {
                conditions.add("Street='" + this.getStreet() + "'");
            }
            if (!this.getPostalCode().isEmpty()) {
                conditions.add("PostalCode='" + this.getPostalCode() + "'");
            }
            if (!this.getAddressLine().isEmpty()) {
                conditions.add("AddressLine='" + this.getAddressLine() + "'");
            }
            if (!this.getNotes().isEmpty()) {
                conditions.add("Notes='" + this.getNotes() + "'");
            }
        }

        //Retrieving new instance
        List<Address> addresses = DataHandler.<Address>readRecords(Address.class, Arrays.asList("AddressID", "Country", "Province", "City", "Street", "PostalCode", "AddressLine", "Notes"), Arrays.asList(new DataTablesCollection("Address")), conditions);

        //Synchronising
        if (addresses.size() == 1) {
            foundInDatabase = true;
            Address address = addresses.get(0);

            this.setAddressID(address.getAddressID());
            this.setCountry(address.getCountry());
            if (!address.getProvince().isEmpty()) {
                this.setProvince(address.getProvince());
            }
            if (!address.getCity().isEmpty()) {
                this.setCity(address.getCity());
            }
            if (!address.getStreet().isEmpty()) {
                this.setStreet(address.getStreet());
            }
            if (!address.getPostalCode().isEmpty()) {
                this.setPostalCode(address.getPostalCode());
            }
            if (!address.getAddressLine().isEmpty()) {
                this.setAddressLine(address.getAddressLine());
            }
            if (!address.getNotes().isEmpty()) {
                this.setNotes(address.getNotes());
            }
        }

        return foundInDatabase;
    }
    //</editor-fold>

}
