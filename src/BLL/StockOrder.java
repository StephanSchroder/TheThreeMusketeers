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
//</editor-fold>

/**
 *
 * @author Stephan
 */
public class StockOrder implements DatabaseOperations {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private Stock stock;
    private Order order;
    private int quantity;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructor">
    public StockOrder(Stock stock, Order order, int quantity) {
        this.stock = stock;
        this.order = order;
        this.quantity = quantity;
    }

    public StockOrder(int stock, int order, int quantity) {
        this.stock = Stock.read(stock);
        this.order = Order.read(order);
        this.quantity = quantity;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="read Methods">
    public static List<StockOrder> read(int orderID) {
        return DataHandler.<StockOrder>readRecords(StockOrder.class, Arrays.asList("StockID", "OrderID", "Quantity"), Arrays.asList(new DataTablesCollection("StockOrder")), Arrays.asList("OrderID=" + orderID));
    }

    public static StockOrder read(int orderID, int stockID) {
        return DataHandler.<StockOrder>readRecords(StockOrder.class, Arrays.asList("StockID", "OrderID", "Quantity"), Arrays.asList(new DataTablesCollection("StockOrder")), Arrays.asList("OrderID=" + orderID, "StockID=" + stockID)).get(0);
    }

    public static List<StockOrder> readByStock(int stockID) {
        return DataHandler.<StockOrder>readRecords(StockOrder.class, Arrays.asList("StockID", "OrderID", "Quantity"), Arrays.asList(new DataTablesCollection("StockOrder")), Arrays.asList("StockID=" + stockID));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="create Methods">
    @Override
    public void create() {
        //StockOrder
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("OrderID");
        columns.add("StockID");
        columns.add("Quantity");

        //Values
        String values = "";
        values += "int#" + this.getOrder().getOrderID();
        values += ";int#" + this.getStock().getStockID();
        values += ";int#" + this.getQuantity();

        //Execute
        DataHandler.createRecords(columns, "StockOrder", Arrays.asList(values));
    }

    public static void create(StockOrder stockOrder) {
        //StockOrder
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("OrderID");
        columns.add("StockID");
        columns.add("Quantity");

        //Values
        String values = "";
        values += "int#" + stockOrder.getOrder().getOrderID();
        values += ";int#" + stockOrder.getStock().getStockID();
        values += ";int#" + stockOrder.getQuantity();

        //Execute
        DataHandler.createRecords(columns, "StockOrder", Arrays.asList(values));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="update Methods">
    @Override
    public void update() {
        //StockOrder
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Quantity");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("int;" + this.getQuantity());

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + this.getOrder().getOrderID());
        conditions.add("StockID=" + this.getStock().getStockID());

        //Execute
        DataHandler.updateRecords("StockOrder", columns, values, conditions);
    }

    public static void update(StockOrder stockOrder) {
        //StockOrder
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Quantity");

        //Values
        ArrayList<String> values = new ArrayList<>();
        values.add("int;" + stockOrder.getQuantity());

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + stockOrder.getOrder().getOrderID());
        conditions.add("StockID=" + stockOrder.getStock().getStockID());

        //Execute
        DataHandler.updateRecords("StockOrder", columns, values, conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="delete Methods">
    @Override
    public void delete() {
        //StockOrder
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + this.getOrder().getOrderID());
        conditions.add("StockID=" + this.getStock().getStockID());
        conditions.add("Quantity=" + this.getQuantity());

        //Execute
        DataHandler.deleteRecords("StockOrder", conditions);
    }

    public static void delete(StockOrder stockOrder) {
        //StockOrder
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + stockOrder.getOrder().getOrderID());
        conditions.add("StockID=" + stockOrder.getStock().getStockID());
        conditions.add("Quantity=" + stockOrder.getQuantity());

        //Execute
        DataHandler.deleteRecords("StockOrder", conditions);
    }

    public static void delete(int orderID, int stockID) {
        //StockOrder
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + orderID);
        conditions.add("StockID=" + stockID);

        //Execute
        DataHandler.deleteRecords("StockOrder", conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="synchronise Methods">
    @Override
    public boolean synchronise() {
        //Campus
        boolean foundInDatabase = false;

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        if (this.getStock().getStockID() > 0) {
            conditions.add("StockID=" + this.getStock().getStockID());
        }
        if (this.getOrder().getOrderID() > 0) {
            conditions.add("OrderID=" + this.getOrder().getOrderID());
        }

        if (!conditions.isEmpty()) {
            //Retrieving new instance
            List<StockOrder> stockOrders = DataHandler.<StockOrder>readRecords(StockOrder.class, Arrays.asList("StockID", "OrderID", "Quantity"), Arrays.asList(new DataTablesCollection("StockOrder")), conditions);

            //Synchronising
            if (stockOrders.size() == 1) {
                foundInDatabase = true;
                StockOrder stockOrder = stockOrders.get(0);

                this.setStock(stockOrder.getStock());
                this.setOrder(stockOrder.getOrder());
                this.setQuantity(stockOrder.getQuantity());
            }
        }

        return foundInDatabase;
    }
    //</editor-fold>

}
