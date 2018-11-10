/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

//<editor-fold defaultstate="collapsed" desc="imports">
import BLL.Interfaces.DatabaseOperations;
import DAL.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
//</editor-fold>

/**
 *
 * @author Stephan
 */
public class Category implements DatabaseOperations {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private int categoryID;
    private String name;
    private String description;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Category(int categoryID, String name, String description) {
        this.categoryID = categoryID;
        this.name = name;
        this.description = description;
    }

    public Category(int categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Override Methods">
    @Override
    public String toString() {
        return "Category{" + "categoryID=" + categoryID + ", name=" + name + ", description=" + description + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.categoryID;
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.description);
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
        final Category other = (Category) obj;
        if (this.categoryID != other.categoryID) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="read Methods">
    public static List<Category> read() {
        return DataHandler.<Category>readRecords(Category.class, Arrays.asList("CategoryID", "CategoryName", "Description"), Arrays.asList(new DataTablesCollection("Category")), Arrays.asList());
    }

    public static Category read(int categoryID) {
        return DataHandler.<Category>readRecords(Category.class, Arrays.asList("CategoryID", "CategoryName", "Description"), Arrays.asList(new DataTablesCollection("Category")), Arrays.asList("CategoryID=" + categoryID)).get(0);
    }

    public static Category read(String categoryName) {
        return DataHandler.<Category>readRecords(Category.class, Arrays.asList("CategoryID", "CategoryName", "Description"), Arrays.asList(new DataTablesCollection("Category")), Arrays.asList("CategoryName='" + categoryName + "'")).get(0);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="search Methods">
    public static List<Category> search(String categoryName) {
        return DataHandler.<Category>readRecords(Category.class, Arrays.asList("CategoryID", "CategoryName", "Description"), Arrays.asList(new DataTablesCollection("Category")), Arrays.asList("CategoryName LIKE '%" + categoryName + "%'"));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="create Methods">
    @Override
    public void create() {
        //Category
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryName");
        if (!this.getDescription().isEmpty()) {
            columns.add("Description");
        }

        //Values
        String values = "";
        values += "string#" + this.getName();
        if (!this.getDescription().isEmpty()) {
            values += ";string#" + this.getDescription();
        }

        //Execute
        DataHandler.createRecords(columns, "Category", Arrays.asList(values));
    }

    public static void create(Category category) {
        //Category
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryName");
        if (!category.getDescription().isEmpty()) {
            columns.add("Description");
        }

        //Values
        String values = "";
        values += "string#" + category.getName();
        if (!category.getDescription().isEmpty()) {
            values += ";string#" + category.getDescription();
        }

        //Execute
        DataHandler.createRecords(columns, "Category", Arrays.asList(values));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="update Methods">
    @Override
    public void update() {
        //Category
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryName");
        if (!this.getDescription().isEmpty()) {
            columns.add("Description");
        }

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + this.getName());
        if (!this.getDescription().isEmpty()) {
            values.add("string;" + this.getDescription());
        }

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CategoryID=" + this.getCategoryID());

        //Execute
        DataHandler.updateRecords("Category", columns, values, conditions);
    }

    public static void update(Category category) {
        //Category
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryName");
        if (!category.getDescription().isEmpty()) {
            columns.add("Description");
        }

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + category.getName());
        if (!category.getDescription().isEmpty()) {
            values.add("string;" + category.getDescription());
        }

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CategoryID=" + category.getCategoryID());

        //Execute
        DataHandler.updateRecords("Category", columns, values, conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="delete Methods">
    @Override
    public void delete() {
        //Category
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CategoryName='" + this.getName() + "'");
        if (!this.getDescription().isEmpty()) {
            conditions.add("Description='" + this.getDescription() + "'");
        }

        //Execute
        DataHandler.deleteRecords("Category", conditions);
    }

    public static void delete(Category category) {
        //Category
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CategoryName='" + category.getName() + "'");
        if (!category.getDescription().isEmpty()) {
            conditions.add("Description='" + category.getDescription() + "'");
        }

        //Execute
        DataHandler.deleteRecords("Category", conditions);
    }

    public static void delete(int categoryID) {
        //Category
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CategoryID=" + categoryID);

        //Execute
        DataHandler.deleteRecords("Category", conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="synchronise Methods">
    @Override
    public boolean synchronise() {
        //Campus
        boolean foundInDatabase = false;

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        if (this.getCategoryID() > 0) {
            conditions.add("CategoryID=" + this.getCategoryID());
        } else {
            conditions.add("CategoryName='" + this.getName() + "'");
            if (!this.getDescription().isEmpty()) {
                conditions.add("Description='" + this.getDescription() + "'");
            }
        }

        //Retrieving new instance
        List<Category> categories = DataHandler.<Category>readRecords(Category.class, Arrays.asList("CategoryID", "CategoryName", "Description"), Arrays.asList(new DataTablesCollection("Category")), conditions);

        //Synchronising
        if (categories.size() == 1) {
            foundInDatabase = true;
            Category category = categories.get(0);

            this.setCategoryID(category.getCategoryID());
            this.setName(category.getName());
            if (!category.getDescription().isEmpty()) {
                this.setDescription(category.getDescription());
            }
        }

        return foundInDatabase;
    }
    //</editor-fold>

}
