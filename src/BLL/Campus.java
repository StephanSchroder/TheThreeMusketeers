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
//</editor-fold>

/**
 *
 * @author Nico
 */
public class Campus implements DatabaseOperations {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private int campusID;
    private String name;
    private Address location;
    private Contact contactDetails;
    private String notes;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Campus(int campusID, String name, Address location, Contact contactDetails, String notes) {
        this.campusID = campusID;
        this.name = name;
        this.location = location;
        this.contactDetails = contactDetails;
        this.notes = notes;
    }

    public Campus(int campusID, String name, int location, int contactDetails, String notes) {
        this.campusID = campusID;
        this.name = name;
        this.location = Address.read(location);
        this.contactDetails = Contact.read(contactDetails);
        this.notes = notes;
    }

    public Campus(int campusID, String name, Address location, Contact contactDetails) {
        this.campusID = campusID;
        this.name = name;
        this.location = location;
        this.contactDetails = contactDetails;
    }

    public Campus(int campusID, String name, int location, int contactDetails) {
        this.campusID = campusID;
        this.name = name;
        this.location = Address.read(location);
        this.contactDetails = Contact.read(contactDetails);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public int getCampusID() {
        return campusID;
    }

    public void setCampusID(int campusID) {
        this.campusID = campusID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public Contact getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(Contact contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="read Methods">
    public static List<Campus> read() {
        return DataHandler.<Campus>readRecords(Campus.class, Arrays.asList("CampusID", "Name", "Location", "ContactDetails", "Notes"), Arrays.asList(new DataTablesCollection("Campus")), Arrays.asList());
    }

    public static Campus read(int campusID) {
        return DataHandler.<Campus>readRecords(Campus.class, Arrays.asList("CampusID", "Name", "Location", "ContactDetails", "Notes"), Arrays.asList(new DataTablesCollection("Campus")), Arrays.asList("CampusID=" + campusID)).get(0);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="create Methods">
    @Override
    public void create() {
        //Campus
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Name");
        columns.add("Location");
        columns.add("ContactDetails");
        if (!this.getNotes().isEmpty()) {
            columns.add("Notes");
        }

        //Values
        String values = "";
        values += "string#" + this.getName();
        values += ";int#" + this.getLocation().getAddressID();
        values += ";int#" + this.getContactDetails().getContactID();
        if (!this.getNotes().isEmpty()) {
            values += ";string#" + this.getNotes();
        }

        //Execute
        DataHandler.createRecords(columns, "Campus", Arrays.asList(values));
    }

    public static void create(Campus campus) {
        //Campus
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Name");
        columns.add("Location");
        columns.add("ContactDetails");
        if (!campus.getNotes().isEmpty()) {
            columns.add("Notes");
        }

        //Values
        String values = "";
        values += "string#" + campus.getName();
        values += ";int#" + campus.getLocation().getAddressID();
        values += ";int#" + campus.getContactDetails().getContactID();
        if (!campus.getNotes().isEmpty()) {
            values += ";string#" + campus.getNotes();
        }

        //Execute
        DataHandler.createRecords(columns, "Campus", Arrays.asList(values));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="update Methods">
    @Override
    public void update() {
        //Campus
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Name");
        columns.add("Location");
        columns.add("ContactDetails");
        if (!this.getNotes().isEmpty()) {
            columns.add("Notes");
        }

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + this.getName());
        values.add("int;" + this.getLocation().getAddressID());
        values.add("int;" + this.getContactDetails().getContactID());
        if (!this.getNotes().isEmpty()) {
            values.add("string;" + this.getNotes());
        }

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CampusID=" + this.getCampusID());

        //Execute
        DataHandler.updateRecords("Campus", columns, values, conditions);
    }

    public static void update(Campus campus) {
        //Campus
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Name");
        columns.add("Location");
        columns.add("ContactDetails");
        if (!campus.getNotes().isEmpty()) {
            columns.add("Notes");
        }

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + campus.getName());
        values.add("int;" + campus.getLocation().getAddressID());
        values.add("int;" + campus.getContactDetails().getContactID());
        if (!campus.getNotes().isEmpty()) {
            values.add("string;" + campus.getNotes());
        }

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CampusID=" + campus.getCampusID());

        //Execute
        DataHandler.updateRecords("Campus", columns, values, conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="delete Methods">
    @Override
    public void delete() {
        //Campus
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("Name='" + this.getName() + "'");
        conditions.add("Location=" + this.getLocation().getAddressID());
        conditions.add("ContactDetails=" + this.getContactDetails().getContactID());
        if (!this.getNotes().isEmpty()) {
            conditions.add("Notes='" + this.getNotes() + "'");
        }

        //Execute
        DataHandler.deleteRecords("Campus", conditions);
    }

    public static void delete(Campus campus) {
        //Campus
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("Name='" + campus.getName() + "'");
        conditions.add("Location=" + campus.getLocation().getAddressID());
        conditions.add("ContactDetails=" + campus.getContactDetails().getContactID());
        if (!campus.getNotes().isEmpty()) {
            conditions.add("Notes='" + campus.getNotes() + "'");
        }

        //Execute
        DataHandler.deleteRecords("Campus", conditions);
    }

    public static void delete(int campusID) {
        //Campus
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CampusID=" + campusID);

        //Execute
        DataHandler.deleteRecords("Campus", conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="synchronise Methods">
    @Override
    public boolean synchronise() {
        //Campus
        boolean foundInDatabase = false;

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        if (this.getCampusID() > 0) {
            conditions.add("CampusID=" + this.getCampusID());
        } else {
            conditions.add("Name='" + this.getName() + "'");
            conditions.add("Location=" + this.getLocation().getAddressID());
            conditions.add("ContactDetails=" + this.getContactDetails().getContactID());
            if (!this.getNotes().isEmpty()) {
                conditions.add("Notes='" + this.getNotes() + "'");
            }
        }

        //Retrieving new instance
        List<Campus> campuses = DataHandler.<Campus>readRecords(Campus.class, Arrays.asList("CampusID", "Name", "Location", "ContactDetails", "Notes"), Arrays.asList(new DataTablesCollection("Campus")), conditions);

        //Synchronising
        if (campuses.size() == 1) {
            foundInDatabase = true;
            Campus campus = campuses.get(0);

            this.setCampusID(campus.getCampusID());
            this.setName(campus.getName());
            this.setLocation(campus.getLocation());
            this.setContactDetails(campus.getContactDetails());
            if (!campus.getNotes().isEmpty()) {
                this.setNotes(campus.getNotes());
            }
        }

        return foundInDatabase;
    }
    //</editor-fold>

}
