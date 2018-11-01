/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BLL.Interfaces.IStock;
import DAL.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Stephan
 */
public class Stock implements IStock {
    private int stockID;
    private Category category;
    private String itemName;
    private Date dateAdded;
    private int stockCount;
    private String status;
    
    public Stock(int stockID, Category category, String itemName, Date dateAdded, int stockCount, String status) {
        this.stockID = stockID;
        this.category = category;
        this.itemName = itemName;
        this.dateAdded = dateAdded;
        this.stockCount = stockCount;
        this.status = status;
    }
    
    public Stock(int stockID, int categoryId, String itemName, Date dateAdded, int stockCount, String status) {
        this.stockID = stockID;
        this.category = Category.getCategory(categoryId);
        this.itemName = itemName;
        this.dateAdded = dateAdded;
        this.stockCount = stockCount;
        this.status = status;
    }
    
    public int getStockID() {
        return stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getStockCount() {
        return stockCount;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public static List<Stock> getStocksSearch(String itemName){
        return DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), Arrays.asList("ItemName LIKE '%" + itemName + "%'"));
    }
    
    public static List<Stock> getStocks(){
        return DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), Arrays.asList());
    }
    
    public static List<Stock> getStocksByCategory(String categoryID){
        return DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), Arrays.asList("CategoryID=" + categoryID));
    }
    
    public static List<Stock> getStocksByStatus(String status){
        return DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), Arrays.asList("Status='" + status + "'"));
    }
    
    public static Stock getStock(int stockID){
        return DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), Arrays.asList("StockID=" + stockID)).get(0);
    }
    
    public void registerStock() {
        //Stock
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryID");
        columns.add("ItemName");
        columns.add("StockCount");
        if (!this.getStatus().isEmpty()) { columns.add("Status"); }
        
        //Values
        String values = "";
        values += "int#" + this.getCategory().getCategoryID();
        values += ";string#" + this.getItemName();
        values += ";int#" + this.getStockCount();
        if (!this.getStatus().isEmpty()) { values += ";string#" + this.getStatus(); }
        
        //Execute
        DataHandler.createRecords(columns, "Stock", Arrays.asList(values));
    }
    
    public static void registerStock(Stock stock) {
        //Stock
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryID");
        columns.add("ItemName");
        columns.add("StockCount");
        if (!stock.getStatus().isEmpty()) { columns.add("Status"); }
        
        //Values
        String values = "";
        values += "int#" + stock.getCategory().getCategoryID();
        values += ";string#" + stock.getItemName();
        values += ";int#" + stock.getStockCount();
        if (!stock.getStatus().isEmpty()) { values += ";string#" + stock.getStatus(); }
        
        //Execute
        DataHandler.createRecords(columns, "Stock", Arrays.asList(values));
    }
    
    public void updateStock() {
        //Stock
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryID");
        columns.add("ItemName");
        columns.add("StockCount");
        columns.add("Status");
        
        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("int;" + this.getCategory().getCategoryID());
        values.add("string;" + this.getItemName());
        values.add("int;" + this.getStockCount());
        values.add("string;" + this.getStatus());
        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("StockID=" + this.getStockID());
        
        //Execute
        DataHandler.updateRecords("Stock", columns, values, conditions);
    }
    
    public static void updateStock(Stock stock) {
        //Stock
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryID");
        columns.add("ItemName");
        columns.add("StockCount");
        columns.add("Status");
        
        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("int;" + stock.getCategory().getCategoryID());
        values.add("string;" + stock.getItemName());
        values.add("int;" + stock.getStockCount());
        values.add("string;" + stock.getStatus());
        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("StockID=" + stock.getStockID());
        
        //Execute
        DataHandler.updateRecords("Stock", columns, values, conditions);
    }
    
    public void deleteStock() {
        //Stock
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("StockID=" + this.getStockID());
        conditions.add("ItemName='" + this.getItemName()+ "'");
        conditions.add("StockCount=" + this.getStockCount());
        conditions.add("Status='" + this.getStatus()+ "'");
        
        //Execute
        DataHandler.deleteRecords("Stock", conditions);
    }
    
    public static void deleteStock(Stock stock) {
        //Stock
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("StockID=" + stock.getStockID());
        conditions.add("CategoryID=" + stock.getCategory().getCategoryID());
        conditions.add("ItemName='" + stock.getItemName()+ "'");
        conditions.add("StockCount=" + stock.getStockCount());
        conditions.add("Status='" + stock.getStatus()+ "'");
        
        //Execute
        DataHandler.deleteRecords("Stock", conditions);
    }
    
    public static void deleteStock(int stockID) {
        //Stock
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("StockID=" + stockID);
        
        //Execute
        DataHandler.deleteRecords("Stock", conditions);
    }

    @Override
    public String toString() {
        return "stockID=" + stockID + ", category=" + category + ", itemName=" + itemName + ", stockCount=" + stockCount;
    }
    
     public static void generateReport(String filename, List<Stock> data )
    {
        Reports report = new Reports();
        ArrayList<Stock> localData = (ArrayList<Stock>) data;
        
        report.createReport( (filename), localData);
    }
    
    public String getReportFormat()
    {
        String format =String.format("Product Name: %1$5s  Quantity:  %2$5d", this.getItemName(),this.getStockCount());
        return format;
    }
    
    
}
