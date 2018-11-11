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
public class Contact implements DatabaseOperations {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private int contactID;
    private String email;
    private String cellNumber;
    private String telNumber;
    private String notes;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Contact(int contactID, String email, String cellNumber, String telNumber, String notes) {
        this.contactID = contactID;
        this.email = email;
        this.cellNumber = cellNumber;
        this.telNumber = telNumber;
        this.notes = notes;
    }
    
    public Contact(String email, String cellNumber, String telNumber) {
        this.email = email;
        this.cellNumber = cellNumber;
        this.telNumber = telNumber;
    }

    public Contact(int contactID, String email, String cellNumber) {
        this.contactID = contactID;
        this.email = email;
        this.cellNumber = cellNumber;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
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
        return "Contact{" + "contactID=" + contactID + ", email=" + email + ", cellNumber=" + cellNumber + ", telNumber=" + telNumber + ", notes=" + notes + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.contactID;
        hash = 61 * hash + Objects.hashCode(this.email);
        hash = 61 * hash + Objects.hashCode(this.cellNumber);
        hash = 61 * hash + Objects.hashCode(this.telNumber);
        hash = 61 * hash + Objects.hashCode(this.notes);
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
        final Contact other = (Contact) obj;
        if (this.contactID != other.contactID) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.cellNumber, other.cellNumber)) {
            return false;
        }
        if (!Objects.equals(this.telNumber, other.telNumber)) {
            return false;
        }
        if (!Objects.equals(this.notes, other.notes)) {
            return false;
        }
        return true;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="read Methods">
    public static List<Contact> read() {
        return DataHandler.<Contact>readRecords(Contact.class, Arrays.asList("ContactID", "Email", "CellNumber", "TelNumber", "Notes"), Arrays.asList(new DataTablesCollection("Contact")), Arrays.asList());
    }

    public static Contact read(int contactID) {
        return DataHandler.<Contact>readRecords(Contact.class, Arrays.asList("ContactID", "Email", "CellNumber", "TelNumber", "Notes"), Arrays.asList(new DataTablesCollection("Contact")), Arrays.asList("ContactID=" + contactID)).get(0);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="create Methods">
    @Override
    public void create() {
        //Contact
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Email");
        columns.add("CellNumber");
        if (!this.getTelNumber().isEmpty()) {
            columns.add("TelNumber");
        }
        if (!this.getNotes().isEmpty()) {
            columns.add("Notes");
        }

        //Values
        String values = "";
        values += "string#" + this.getEmail();
        values += ";string#" + this.getCellNumber();
        if (!this.getTelNumber().isEmpty()) {
            values += ";string#" + this.getTelNumber();
        }
        if (!this.getNotes().isEmpty()) {
            values += ";string#" + this.getNotes();
        }

        //Execute
        DataHandler.createRecords(columns, "Contact", Arrays.asList(values));
    }

    public static void create(Contact contact) {
        //Contact
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Email");
        columns.add("CellNumber");
        if (!contact.getTelNumber().isEmpty()) {
            columns.add("TelNumber");
        }
        if (!contact.getNotes().isEmpty()) {
            columns.add("Notes");
        }

        //Values
        String values = "";
        values += "string#" + contact.getEmail();
        values += ";string#" + contact.getCellNumber();
        if (!contact.getTelNumber().isEmpty()) {
            values += ";string#" + contact.getTelNumber();
        }
        if (!contact.getNotes().isEmpty()) {
            values += ";string#" + contact.getNotes();
        }

        //Execute
        DataHandler.createRecords(columns, "Contact", Arrays.asList(values));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="update Methods">
    @Override
    public void update() {
        //Contact
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Email");
        columns.add("CellNumber");
        if (!this.getTelNumber().isEmpty()) {
            columns.add("TelNumber");
        }
        if (!this.getNotes().isEmpty()) {
            columns.add("Notes");
        }

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + this.getEmail());
        values.add("string;" + this.getCellNumber());
        if (!this.getTelNumber().isEmpty()) {
            values.add("string;" + this.getTelNumber());
        }
        if (!this.getNotes().isEmpty()) {
            values.add("string;" + this.getNotes());
        }

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("ContactID=" + this.getContactID());

        //Execute
        DataHandler.updateRecords("Contact", columns, values, conditions);
    }

    public static void update(Contact contact) {
        //Contact
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Email");
        columns.add("CellNumber");
        if (!contact.getTelNumber().isEmpty()) {
            columns.add("TelNumber");
        }
        if (!contact.getNotes().isEmpty()) {
            columns.add("Notes");
        }

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + contact.getEmail());
        values.add("string;" + contact.getCellNumber());
        if (!contact.getTelNumber().isEmpty()) {
            values.add("string;" + contact.getTelNumber());
        }
        if (!contact.getNotes().isEmpty()) {
            values.add("string;" + contact.getNotes());
        }

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("ContactID=" + contact.getContactID());

        //Execute
        DataHandler.updateRecords("Contact", columns, values, conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="delete Methods">
    @Override
    public void delete() {
        //Contact
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("Email='" + this.getEmail() + "'");
        conditions.add("CellNumber='" + this.getCellNumber() + "'");
        if (!this.getTelNumber().isEmpty()) {
            conditions.add("TelNumber='" + this.getTelNumber() + "'");
        }
        if (!this.getNotes().isEmpty()) {
            conditions.add("Notes='" + this.getNotes() + "'");
        }

        //Execute
        DataHandler.deleteRecords("Contact", conditions);
    }

    public static void delete(Contact contact) {
        //Contact
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("Email='" + contact.getEmail() + "'");
        conditions.add("CellNumber='" + contact.getCellNumber() + "'");
        if (!contact.getTelNumber().isEmpty()) {
            conditions.add("TelNumber='" + contact.getTelNumber() + "'");
        }
        if (!contact.getNotes().isEmpty()) {
            conditions.add("Notes='" + contact.getNotes() + "'");
        }

        //Execute
        DataHandler.deleteRecords("Contact", conditions);
    }

    public static void delete(int contactID) {
        //Contact
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("ContactID=" + contactID);

        //Execute
        DataHandler.deleteRecords("Contact", conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="synchronise Methods">
    @Override
    public boolean synchronise() {
        //Contact
        boolean foundInDatabase = false;

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        if (this.getContactID() > 0) {
            conditions.add("ContactID=" + this.getContactID());
        } else {
            conditions.add("Email='" + this.getEmail() + "'");
            conditions.add("CellNumber='" + this.getCellNumber() + "'");
            if (!this.getTelNumber().isEmpty()) {
                conditions.add("TelNumber='" + this.getTelNumber() + "'");
            }
            if (!this.getNotes().isEmpty()) {
                conditions.add("Notes='" + this.getNotes() + "'");
            }
        }

        //Retrieving new instance
        List<Contact> contacts = DataHandler.<Contact>readRecords(Contact.class, Arrays.asList("ContactID", "Email", "CellNumber", "TelNumber", "Notes"), Arrays.asList(new DataTablesCollection("Contact")), conditions);

        //Synchronising
        if (contacts.size() == 1) {
            foundInDatabase = true;
            Contact contact = contacts.get(0);

            this.setContactID(contact.getContactID());
            this.setEmail(contact.getEmail());
            this.setCellNumber(contact.getCellNumber());
            if (!contact.getTelNumber().isEmpty()) {
                this.setTelNumber(contact.getTelNumber());
            }
            if (!contact.getNotes().isEmpty()) {
                this.setNotes(contact.getNotes());
            }
        }

        return foundInDatabase;
    }
    //</editor-fold>

}
