/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

//<editor-fold defaultstate="collapsed" desc="imports">
import BLL.Interfaces.DatabaseOperations;
import DAL.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//</editor-fold>

/**
 *
 * @author Stephan
 */
public class Stock implements DatabaseOperations {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private int stockID;
    private Category category;
    private String model;
    private double price;
    private String itemName;
    private Date dateAdded;
    private int stockCount;
    private String status;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Stock(int stockID, Category category, String model, double price, String itemName, Date dateAdded, int stockCount, String status) {
        this.stockID = stockID;
        this.category = category;
        this.model = model;
        this.price = price;
        this.itemName = itemName;
        this.dateAdded = dateAdded;
        this.stockCount = stockCount;
        this.status = status;
    }

    public Stock(int stockID, int categoryId, String model, double price, String itemName, Date dateAdded, int stockCount, String status) {
        this.stockID = stockID;
        this.category = Category.read(categoryId);
        this.model = model;
        this.price = price;
        this.itemName = itemName;
        this.dateAdded = dateAdded;
        this.stockCount = stockCount;
        this.status = status;
    }

    public Stock(int stockID, Category category, double price, String itemName, Date dateAdded, int stockCount, String status) {
        this.stockID = stockID;
        this.category = category;
        this.price = price;
        this.itemName = itemName;
        this.dateAdded = dateAdded;
        this.stockCount = stockCount;
        this.status = status;
    }

    public Stock(int stockID, int categoryId, double price, String itemName, Date dateAdded, int stockCount, String status) {
        this.stockID = stockID;
        this.category = Category.read(categoryId);
        this.price = price;
        this.itemName = itemName;
        this.dateAdded = dateAdded;
        this.stockCount = stockCount;
        this.status = status;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="read Methods">
    public static List<Stock> read() {
        return DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "Model", "Price", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), Arrays.asList());
    }

    public static Stock read(int stockID) {
        return DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "Model", "Price", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), Arrays.asList("StockID=" + stockID)).get(0);
    }

    public static List<Stock> readByCategory(String categoryID) {
        return DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "Model", "Price", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), Arrays.asList("CategoryID=" + categoryID));
    }

    public static List<Stock> readByModel(String model) {
        return DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "Model", "Price", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), Arrays.asList("Model='" + model + "'"));
    }

    public static List<Stock> readByItemName(String itemName) {
        return DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "Model", "Price", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), Arrays.asList("ItemName='" + itemName + "'"));
    }

    public static List<Stock> readByStatus(String status) {
        return DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "Model", "Price", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), Arrays.asList("Status='" + status + "'"));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="search Methods">
    public static List<Stock> search(String itemName) {
        return DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "Model", "Price", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), Arrays.asList("ItemName LIKE '%" + itemName + "%'"));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="create Methods">
    @Override
    public void create() {
        //Stock
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryID");
        if (!this.getModel().isEmpty()) {
            columns.add("Model");
        }
        columns.add("Price");
        columns.add("ItemName");
        columns.add("StockCount");
        columns.add("Status");

        //Values
        String values = "";
        values += "int#" + this.getCategory().getCategoryID();
        if (!this.getModel().isEmpty()) {
            values += ";string#" + this.getModel();
        }
        values += ";int#" + this.getPrice();
        values += ";string#" + this.getItemName();
        values += ";int#" + this.getStockCount();
        values += ";string#" + this.getStatus();

        //Execute
        DataHandler.createRecords(columns, "Stock", Arrays.asList(values));
    }

    public static void create(Stock stock) {
        //Stock
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryID");
        if (!stock.getModel().isEmpty()) {
            columns.add("Model");
        }
        columns.add("Price");
        columns.add("ItemName");
        columns.add("StockCount");
        columns.add("Status");

        //Values
        String values = "";
        values += "int#" + stock.getCategory().getCategoryID();
        if (!stock.getModel().isEmpty()) {
            values += ";string#" + stock.getModel();
        }
        values += ";int#" + stock.getPrice();
        values += ";string#" + stock.getItemName();
        values += ";int#" + stock.getStockCount();
        values += ";string#" + stock.getStatus();

        //Execute
        DataHandler.createRecords(columns, "Stock", Arrays.asList(values));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="update Methods">
    @Override
    public void update() {
        //Stock
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryID");
        if (!this.getModel().isEmpty()) {
            columns.add("Model");
        }
        columns.add("Price");
        columns.add("ItemName");
        columns.add("StockCount");
        columns.add("Status");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("int;" + this.getCategory().getCategoryID());
        if (!this.getModel().isEmpty()) {
            values.add("string;" + this.getModel());
        }
        values.add("int;" + this.getPrice());
        values.add("string;" + this.getItemName());
        values.add("int;" + this.getStockCount());
        values.add("string;" + this.getStatus());

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("StockID=" + this.getStockID());

        //Execute
        DataHandler.updateRecords("Stock", columns, values, conditions);
    }

    public static void update(Stock stock) {
        //Stock
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("CategoryID");
        if (!stock.getModel().isEmpty()) {
            columns.add("Model");
        }
        columns.add("Price");
        columns.add("ItemName");
        columns.add("StockCount");
        columns.add("Status");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("int;" + stock.getCategory().getCategoryID());
        if (!stock.getModel().isEmpty()) {
            values.add("string;" + stock.getModel());
        }
        values.add("int;" + stock.getPrice());
        values.add("string;" + stock.getItemName());
        values.add("int;" + stock.getStockCount());
        values.add("string;" + stock.getStatus());

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("StockID=" + stock.getStockID());

        //Execute
        DataHandler.updateRecords("Stock", columns, values, conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="delete Methods">
    @Override
    public void delete() {
        //Stock
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CategoryID=" + this.getCategory().getCategoryID());
        if (!this.getModel().isEmpty()) {
            conditions.add("Model='" + this.getModel() + "'");
        }
        conditions.add("Price=" + this.getPrice());
        conditions.add("ItemName='" + this.getItemName() + "'");
        conditions.add("StockCount=" + this.getStockCount());
        conditions.add("Status='" + this.getStatus() + "'");

        //Execute
        DataHandler.deleteRecords("Stock", conditions);
    }

    public static void delete(Stock stock) {
        //Stock
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("CategoryID=" + stock.getCategory().getCategoryID());
        if (!stock.getModel().isEmpty()) {
            conditions.add("Model='" + stock.getModel() + "'");
        }
        conditions.add("Price=" + stock.getPrice());
        conditions.add("ItemName='" + stock.getItemName() + "'");
        conditions.add("StockCount=" + stock.getStockCount());
        conditions.add("Status='" + stock.getStatus() + "'");

        //Execute
        DataHandler.deleteRecords("Stock", conditions);
    }

    public static void delete(int stockID) {
        //Stock
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("StockID=" + stockID);

        //Execute
        DataHandler.deleteRecords("Stock", conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="synchronise Methods">
    @Override
    public boolean synchronise() {
        //Campus
        boolean foundInDatabase = false;

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        if (this.getStockID() > 0) {
            conditions.add("StockID=" + this.getStockID());
        } else {
            conditions.add("CategoryID=" + this.getCategory().getCategoryID());
            if (!this.getModel().isEmpty()) {
                conditions.add("Model='" + this.getModel() + "'");
            }
            conditions.add("Price=" + this.getPrice());
            conditions.add("ItemName='" + this.getItemName() + "'");
            conditions.add("StockCount=" + this.getStockCount());
            conditions.add("Status='" + this.getStatus() + "'");
        }

        //Retrieving new instance
        List<Stock> stocks = DataHandler.<Stock>readRecords(Stock.class, Arrays.asList("StockID", "CategoryID", "Model", "Price", "ItemName", "DateAdded", "StockCount", "Status"), Arrays.asList(new DataTablesCollection("Stock")), conditions);

        //Synchronising
        if (stocks.size() == 1) {
            foundInDatabase = true;
            Stock stock = stocks.get(0);

            this.setStockID(stock.getStockID());
            this.setCategory(stock.getCategory());
            if (!stock.getModel().isEmpty()) {
                this.setModel(stock.getModel());
            }
            this.setPrice(stock.getPrice());
            this.setItemName(stock.getItemName());
            this.setDateAdded(stock.getDateAdded());
            this.setStockCount(stock.getStockCount());
            this.setStatus(stock.getStatus());
        }

        return foundInDatabase;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="other Methods">
    public static void generateReport(String filename, List<Stock> data) {
        Reports report = new Reports();
        ArrayList<Stock> localData = (ArrayList<Stock>) data;

        report.createReport((filename), localData);
    }

    public String getReportFormat() {
        String format = String.format("||Product Name:|| %1$5s  ||Quantity:||  %2$5d", this.getItemName(), this.getStockCount());
        return format;
    }
    //</editor-fold>

}

//NOTES:
//MAKE SURE DATES USE 'new SimpleDateFormat("yyyy-MM-dd").format(date);' OR 'new SimpleDateFormat("yyyy-MM-dd").parse(date);'
//MAKE SURE DATETIMES USE 'new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").format(dateTime);' OR 'new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").parse(dateTime);'
