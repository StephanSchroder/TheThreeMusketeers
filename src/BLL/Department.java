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
public class Department implements DatabaseOperations {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private int departmentID;
    private String name;
    private Campus campus;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Department(int departmentID, String name, Campus campus) {
        this.departmentID = departmentID;
        this.name = name;
        this.campus = campus;
    }

    public Department(int departmentID, String name, int campus) {
        this.departmentID = departmentID;
        this.name = name;
        this.campus = Campus.read(campus);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Override Methods">
    @Override
    public String toString() {
        return "Department{" + "departmentID=" + departmentID + ", name=" + name + ", campus=" + campus + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.departmentID;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.campus);
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
        final Department other = (Department) obj;
        if (this.departmentID != other.departmentID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.campus, other.campus)) {
            return false;
        }
        return true;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="read Methods">
    public static List<Department> read() {
        return DataHandler.<Department>readRecords(Department.class, Arrays.asList("DepartmentID", "Name", "CampusID"), Arrays.asList(new DataTablesCollection("Department")), Arrays.asList());
    }

    public static Department read(int departmentID) {
        return DataHandler.<Department>readRecords(Department.class, Arrays.asList("DepartmentID", "Name", "CampusID"), Arrays.asList(new DataTablesCollection("Department")), Arrays.asList("DepartmentID=" + departmentID)).get(0);
    }

    public static Department read(String departmentName, String campusName) {
        return DataHandler.<Department>readRecords(Department.class, Arrays.asList("DepartmentID", "Name", "CampusID"), Arrays.asList(new DataTablesCollection("Department")), Arrays.asList("Name='" + departmentName + "'", "CampusID=" + Campus.read(campusName).getCampusID())).get(0);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="create Methods">
    @Override
    public void create() {
        //Department
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Name");
        columns.add("CampusID");

        //Values
        String values = "";
        values += "string#" + this.getName();
        values += ";int#" + this.getCampus().getCampusID();

        //Execute
        DataHandler.createRecords(columns, "Department", Arrays.asList(values));
    }

    public static void create(Department department) {
        //Department
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Name");
        columns.add("CampusID");

        //Values
        String values = "";
        values += "string#" + department.getName();
        values += ";int#" + department.getCampus().getCampusID();

        //Execute
        DataHandler.createRecords(columns, "Department", Arrays.asList(values));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="update Methods">
    @Override
    public void update() {
        //Department
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Name");
        columns.add("CampusID");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + this.getName());
        values.add("int;" + this.getCampus().getCampusID());

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("DepartmentID=" + this.getDepartmentID());

        //Execute
        DataHandler.updateRecords("Department", columns, values, conditions);
    }

    public static void update(Department department) {
        //Department
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Name");
        columns.add("CampusID");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + department.getName());
        values.add("int;" + department.getCampus().getCampusID());

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("DepartmentID=" + department.getDepartmentID());

        //Execute
        DataHandler.updateRecords("Department", columns, values, conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="delete Methods">
    @Override
    public void delete() {
        //Department
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("Name='" + this.getName() + "'");
        conditions.add("CampusID=" + this.getCampus().getCampusID());

        //Execute
        DataHandler.deleteRecords("Department", conditions);
    }

    public static void delete(Department department) {
        //Department
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("Name='" + department.getName() + "'");
        conditions.add("CampusID=" + department.getCampus().getCampusID());

        //Execute
        DataHandler.deleteRecords("Department", conditions);
    }

    public static void delete(int departmentID) {
        //Department
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("DepartmentID=" + departmentID);

        //Execute
        DataHandler.deleteRecords("Department", conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="synchronise Methods">
    @Override
    public boolean synchronise() {
        //Department
        boolean foundInDatabase = false;

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        if (this.getDepartmentID() > 0) {
            conditions.add("DepartmentID=" + this.getDepartmentID());
        } else {
            conditions.add("Name='" + this.getName() + "'");
            conditions.add("CampusID=" + this.getCampus().getCampusID());
        }

        //Retrieving new instance
        List<Department> departments = DataHandler.<Department>readRecords(Department.class, Arrays.asList("DepartmentID", "Name", "CampusID"), Arrays.asList(new DataTablesCollection("Department")), conditions);

        //Synchronising
        if (departments.size() == 1) {
            foundInDatabase = true;
            Department department = departments.get(0);

            this.setDepartmentID(department.getDepartmentID());
            this.setName(department.getName());
            this.setCampus(department.getCampus());
        }

        return foundInDatabase;
    }
    //</editor-fold>

}
