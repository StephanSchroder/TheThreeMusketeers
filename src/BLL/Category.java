/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.Interfaces.ICategory;
import DAL.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Stephan
 */
public class Category implements ICategory {
    private int categoryID;
    private String name;
    private String description;

    public Category(int categoryID, String name, String description) {
        this.categoryID = categoryID;
        this.name = name;
        this.description = description;
    }

    public Category(int categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }

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
    
    public static List<Category> getCategories(){
        return DataHandler.<Category>readRecords(Category.class, Arrays.asList("CategoryID", "CategoryName", "Description"), Arrays.asList(new DataTablesCollection("Category")), Arrays.asList());
    }
    
    public static Category getCategory(int categoryID){
        return DataHandler.<Category>readRecords(Category.class, Arrays.asList("CategoryID", "CategoryName", "Description"), Arrays.asList(new DataTablesCollection("Category")), Arrays.asList("CategoryID=" + categoryID)).get(0);
    }
    
    public static Category getCategory(String categoryName){
        return DataHandler.<Category>readRecords(Category.class, Arrays.asList("CategoryID", "CategoryName", "Description"), Arrays.asList(new DataTablesCollection("Category")), Arrays.asList("CategoryName='" + categoryName + "'")).get(0);
    }
    
    public void registerCategory() {
        //Category
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Name");
        if (!this.getDescription().isEmpty()) { columns.add("Description"); }
        
        //Values
        String values = "";
        values += "string#" + this.getName();
        if (!this.getDescription().isEmpty()) { values += ";string#" + this.getDescription(); }
        
        //Execute
        DataHandler.createRecords(columns, "Category", Arrays.asList(values));
    }
    
    public static void registerCategory(Category category) {
        //Category
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Name");
        if (!category.getDescription().isEmpty()) { columns.add("Description"); }
        
        //Values
        String values = "";
        values += "string#" + category.getName();
        if (!category.getDescription().isEmpty()) { values += ";string#" + category.getDescription(); }
        
        //Execute
        DataHandler.createRecords(columns, "Category", Arrays.asList(values));
    }
    
    public void updateCategory() {
        //Category
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryName");
        if (!this.getDescription().isEmpty()) { columns.add("Description"); }
        
        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + this.getName());
        if (!this.getDescription().isEmpty()) { values.add("string;" + this.getDescription()); }
        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CategoryID=" + this.getCategoryID());
        
        //Execute
        DataHandler.updateRecords("Category", columns, values, conditions);
    }
    
    public static void updateCategory(Category category) {
        //Category
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryName");
        if (!category.getDescription().isEmpty()) { columns.add("Description"); }
        
        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("string;" + category.getName());
        if (!category.getDescription().isEmpty()) { values.add("string;" + category.getDescription()); }
        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CategoryID=" + category.getCategoryID());
        
        //Execute
        DataHandler.updateRecords("Category", columns, values, conditions);
    }
    
    public void deleteCategory() {
        //Category
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CategoryID=" + this.getCategoryID());
        conditions.add("CategoryName='" + this.getName()+ "'");
        if (!this.getDescription().isEmpty()) { conditions.add("Description='" + this.getDescription()+ "'"); }
        
        //Execute
        DataHandler.deleteRecords("Category", conditions);
    }
    
    public static void deleteCategory(Category category) {
        //Category
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CategoryID=" + category.getCategoryID());
        conditions.add("CategoryName='" + category.getName()+ "'");
        if (!category.getDescription().isEmpty()) { conditions.add("Description='" + category.getDescription()+ "'"); }
        
        //Execute
        DataHandler.deleteRecords("Category", conditions);
    }
    
    public static void deleteCategory(int categoryID) {
        //Category
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CategoryID=" + categoryID);
        
        //Execute
        DataHandler.deleteRecords("Category", conditions);
    }
}
