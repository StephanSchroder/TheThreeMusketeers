/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Stephan
 */
public class StockOrder {
    private Stock stock;
    private int quantity;

    public StockOrder(Stock stock, int quantity) {
        this.stock = stock;
        this.quantity = quantity;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public static List<StockOrder> getStockOrder(Order order){
        List<StockOrder> stockOrders = new ArrayList<>();
        String[][] dbData = DataHandler.readRecords(Arrays.asList("OrderID", "StockID", "Quantity"), Arrays.asList(new DataTablesCollection("StockOrder")), Arrays.asList());
        int count = dbData.length;
        for (int i = 0; i < count; i++) {
            stockOrders.add(new StockOrder(Integer.valueOf(dbData[i][0]), dbData[i][1], dbData[i][2]));
        }

        return stockOrders;
    }
    
    public static Category getCategory(int categoryID){
        Category category = null;
        String[][] dbData = DataHandler.readRecords(Arrays.asList("CategoryID", "CategoryName", "Description"), Arrays.asList(new DataTablesCollection("Category")), Arrays.asList("CategoryID=" + categoryID));
        int count = dbData.length;
        if (count == 1)
        {
            category = new Category(Integer.valueOf(dbData[0][0]), dbData[0][1], dbData[0][2]);
        }

        return category;
    }
    
    public void registerStockOrder(Order order) {
        //StockOrder
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("OrderID");
        columns.add("StockID");
        columns.add("Quantity");
        
        //Values
        String values = "";
        values += "int#" + order.getOrderID();
        values += ";int#" + this.getStock().getStockID();
        values += ";int#" + this.getQuantity();
        
        //Execute
        DataHandler.createRecords(columns, "StockOrder", Arrays.asList(values));
    }
    
    public static void registerStockOrder(Order order, StockOrder stockOrder) {
        //StockOrder
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("OrderID");
        columns.add("StockID");
        columns.add("Quantity");
        
        //Values
        String values = "";
        values += "int#" + order.getOrderID();
        values += ";int#" + stockOrder.getStock().getStockID();
        values += ";int#" + stockOrder.getQuantity();
        
        //Execute
        DataHandler.createRecords(columns, "StockOrder", Arrays.asList(values));
    }
    
    public void updateStockOrder(Order order) {
        //StockOrder
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Quantity");
        
        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("int;" + this.getQuantity());
        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + order.getOrderID());
        conditions.add("StockID=" + this.getStock().getStockID());
        conditions.add("Quantity=" + this.getQuantity());
        
        //Execute
        DataHandler.updateRecords("StockOrder", columns, values, conditions);
    }
    
    public static void updateStockOrder(Order order, StockOrder stockOrder) {
        //StockOrder
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Quantity");
        
        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("int;" + stockOrder.getQuantity());
        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + order.getOrderID());
        conditions.add("StockID=" + stockOrder.getStock().getStockID());
        conditions.add("Quantity=" + stockOrder.getQuantity());
        
        //Execute
        DataHandler.updateRecords("StockOrder", columns, values, conditions);
    }
    
    public void deleteStockOrder(Order order) {
        //StockOrder
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + order.getOrderID());
        conditions.add("StockID=" + this.getStock().getStockID());
        conditions.add("Quantity=" + this.getQuantity());
        
        //Execute
        DataHandler.deleteRecords("StockOrder", conditions);
    }
    
    public static void deleteStockOrder(Order order, StockOrder stockOrder) {
        //StockOrder
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + order.getOrderID());
        conditions.add("StockID=" + stockOrder.getStock().getStockID());
        conditions.add("Quantity=" + stockOrder.getQuantity());
        
        //Execute
        DataHandler.deleteRecords("StockOrder", conditions);
    }
    
    public static void deleteStockOrder(int orderID, int stockID) {
        //StockOrder
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + orderID);
        conditions.add("StockID=" + stockID);
        
        //Execute
        DataHandler.deleteRecords("StockOrder", conditions);
    }
}
