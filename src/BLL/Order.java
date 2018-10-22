/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import DAL.*;
import java.sql.Date;
import java.util.*;

/**
 *
 * @author Stephan
 */
public class Order {
    private int orderID;
    private Date orderDate;
    private Date receiveDate;
    private String status;
    private User placedByEmployee;
    private User approvedByEmployee;
    private List<StockOrder> stock;

    public Order(int orderID, Date orderDate, Date receiveDate, String status, User placedByEmployee, User approvedByEmployee, List<StockOrder> stock) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.receiveDate = receiveDate;
        this.status = status;
        this.placedByEmployee = placedByEmployee;
        this.approvedByEmployee = approvedByEmployee;
        this.stock = stock;
    }

    public Order(int orderID, Date orderDate, Date receiveDate, String status, User placedByEmployee, User approvedByEmployee) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.receiveDate = receiveDate;
        this.status = status;
        this.placedByEmployee = placedByEmployee;
        this.approvedByEmployee = approvedByEmployee;
    }

    public Order(int orderID, Date orderDate, String status, User placedByEmployee) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.status = status;
        this.placedByEmployee = placedByEmployee;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getPlacedByEmployee() {
        return placedByEmployee;
    }

    public void setPlacedByEmployee(User placedByEmployee) {
        this.placedByEmployee = placedByEmployee;
    }

    public User getApprovedByEmployee() {
        return approvedByEmployee;
    }

    public void setApprovedByEmployee(User approvedByEmployee) {
        this.approvedByEmployee = approvedByEmployee;
    }

    public List<StockOrder> getStock() {
        return stock;
    }

    public void setStock(List<StockOrder> stock) {
        this.stock = stock;
    }
    
    public static List<Order> getOrders(){
        List<Order> orders = new ArrayList<>();
        String[][] dbData = DataHandler.readRecords(Arrays.asList("OrderID", "OrderDate", "ReceiveDate", "Status", "PlacedByEmployee", "ApprovedByEmployee"), Arrays.asList(new DataTablesCollection("Order")), Arrays.asList());
        int count = dbData.length;
        for (int i = 0; i < count; i++) {
            orders.add(new Order(Integer.valueOf(dbData[i][0]), Date.valueOf(dbData[i][1]), Date.valueOf(dbData[i][2]), dbData[i][3], User.GetUserByUserId(Integer.valueOf(dbData[i][4])), User.GetUserByUserId(Integer.valueOf(dbData[i][5]))));
        }
        
        for (Order order:orders){
            order.stock = StockOrder.getStockOrders(order.orderID);
        }

        return orders;
    }
    
    public static List<Order> getOrdersPlacedByEmployee(int placedByEmployeeID){
        List<Order> orders = new ArrayList<>();
        String[][] dbData = DataHandler.readRecords(Arrays.asList("OrderID", "OrderDate", "ReceiveDate", "Status", "PlacedByEmployee", "ApprovedByEmployee"), Arrays.asList(new DataTablesCollection("Order")), Arrays.asList("PlacedByEmployee=" + placedByEmployeeID));
        int count = dbData.length;
        for (int i = 0; i < count; i++) {
            orders.add(new Order(Integer.valueOf(dbData[i][0]), Date.valueOf(dbData[i][1]), Date.valueOf(dbData[i][2]), dbData[i][3], User.GetUserByUserId(Integer.valueOf(dbData[i][4])), User.GetUserByUserId(Integer.valueOf(dbData[i][5]))));
        }
        
        for (Order order:orders){
            order.stock = StockOrder.getStockOrders(order.orderID);
        }

        return orders;
    }
    
    public static List<Order> getOrdersApprovedByEmployee(int approvedByEmployeeID){
        List<Order> orders = new ArrayList<>();
        String[][] dbData = DataHandler.readRecords(Arrays.asList("OrderID", "OrderDate", "ReceiveDate", "Status", "PlacedByEmployee", "ApprovedByEmployee"), Arrays.asList(new DataTablesCollection("Order")), Arrays.asList("ApprovedByEmployee=" + approvedByEmployeeID));
        int count = dbData.length;
        for (int i = 0; i < count; i++) {
            orders.add(new Order(Integer.valueOf(dbData[i][0]), Date.valueOf(dbData[i][1]), Date.valueOf(dbData[i][2]), dbData[i][3], User.GetUserByUserId(Integer.valueOf(dbData[i][4])), User.GetUserByUserId(Integer.valueOf(dbData[i][5]))));
        }
        
        for (Order order:orders){
            order.stock = StockOrder.getStockOrders(order.orderID);
        }

        return orders;
    }
    
    public static List<Order> getOrdersByStatus(String status){
        List<Order> orders = new ArrayList<>();
        String[][] dbData = DataHandler.readRecords(Arrays.asList("OrderID", "OrderDate", "ReceiveDate", "Status", "PlacedByEmployee", "ApprovedByEmployee"), Arrays.asList(new DataTablesCollection("Order")), Arrays.asList("Status='" + status + "'"));
        int count = dbData.length;
        for (int i = 0; i < count; i++) {
            orders.add(new Order(Integer.valueOf(dbData[i][0]), Date.valueOf(dbData[i][1]), Date.valueOf(dbData[i][2]), dbData[i][3], User.GetUserByUserId(Integer.valueOf(dbData[i][4])), User.GetUserByUserId(Integer.valueOf(dbData[i][5]))));
        }
        
        for (Order order:orders){
            order.stock = StockOrder.getStockOrders(order.orderID);
        }

        return orders;
    }
    
    public static Order getOrder(int orderID){
        Order order = null;
        String[][] dbData = DataHandler.readRecords(Arrays.asList("OrderID", "OrderDate", "ReceiveDate", "Status", "PlacedByEmployee", "ApprovedByEmployee"), Arrays.asList(new DataTablesCollection("Order")), Arrays.asList("OrderID=" + orderID));
        int count = dbData.length;
        if (count == 1)
        {
            order = new Order(Integer.valueOf(dbData[0][0]), Date.valueOf(dbData[0][1]), Date.valueOf(dbData[0][2]), dbData[0][3], User.GetUserByUserId(Integer.valueOf(dbData[0][4])), User.GetUserByUserId(Integer.valueOf(dbData[0][5])));
        }
        
        order.stock = StockOrder.getStockOrders(order.orderID);

        return order;
    }
    
    public void registerOrder() {
        //Order
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("PlacedByEmployee");
        if (!this.getStatus().isEmpty()) { columns.add("Status"); }
        
        //Values
        String values = "";
        values += "int#" + this.getPlacedByEmployee().getUserID();
        if (!this.getStatus().isEmpty()) { values += ";string#" + this.getStatus(); }
        
        //Execute
        DataHandler.createRecords(columns, "Order", Arrays.asList(values));
    }
    
    public static void registerOrder(Order order) {
        //Order
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("PlacedByEmployee");
        if (!order.getStatus().isEmpty()) { columns.add("Status"); }
        
        //Values
        String values = "";
        values += "int#" + order.getPlacedByEmployee().getUserID();
        if (!order.getStatus().isEmpty()) { values += ";string#" + order.getStatus(); }
        
        //Execute
        DataHandler.createRecords(columns, "Order", Arrays.asList(values));
    }
    
    public void updateOrder() {
        //Order
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        if (this.getReceiveDate() != null) { columns.add("ReceiveDate"); }
        if (!this.getStatus().isEmpty()) { columns.add("Status"); }
        if (this.getApprovedByEmployee() != null) { columns.add("ApprovedByEmployee"); }
        
        //Values
        ArrayList<String> values = new ArrayList<>();
        if (this.getReceiveDate() != null) { values.add("string;" + this.getReceiveDate()); }
        if (!this.getStatus().isEmpty()) { values.add("string;" + this.getStatus()); }
        if (this.getApprovedByEmployee() != null) { values.add("string;" + this.getApprovedByEmployee().getUserID()); }
        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + this.getOrderID());
        
        //Execute
        DataHandler.updateRecords("Order", columns, values, conditions);
    }
    
    public static void updateOrder(Order order) {
        //Order
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        if (order.getReceiveDate() != null) { columns.add("ReceiveDate"); }
        if (!order.getStatus().isEmpty()) { columns.add("Status"); }
        if (order.getApprovedByEmployee() != null) { columns.add("ApprovedByEmployee"); }
        
        //Values
        ArrayList<String> values = new ArrayList<>();
        if (order.getReceiveDate() != null) { values.add("string;" + order.getReceiveDate()); }
        if (!order.getStatus().isEmpty()) { values.add("string;" + order.getStatus()); }
        if (order.getApprovedByEmployee() != null) { values.add("string;" + order.getApprovedByEmployee().getUserID()); }
        
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + order.getOrderID());
        
        //Execute
        DataHandler.updateRecords("Order", columns, values, conditions);
    }
    
    public void deleteOrder() {
        //Order
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + this.getOrderID());
        conditions.add("OrderDate='" + this.getOrderDate()+ "'");
        if (this.getReceiveDate() != null) { conditions.add("ReceiveDate='" + this.getReceiveDate()+ "'"); }
        if (!this.getStatus().isEmpty()) { conditions.add("Status='" + this.getStatus()+ "'"); }
        if (this.getPlacedByEmployee() != null) { conditions.add("PlacedByEmployee=" + this.getPlacedByEmployee().getUserID()); }
        if (this.getApprovedByEmployee() != null) { conditions.add("ApprovedByEmployee=" + this.getApprovedByEmployee().getUserID()); }
        
        //Execute
        DataHandler.deleteRecords("Order", conditions);
    }
    
    public static void deleteOrder(Order order) {
        //Order
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + order.getOrderID());
        conditions.add("OrderDate='" + order.getOrderDate()+ "'");
        if (order.getReceiveDate() != null) { conditions.add("ReceiveDate='" + order.getReceiveDate()+ "'"); }
        if (!order.getStatus().isEmpty()) { conditions.add("Status='" + order.getStatus()+ "'"); }
        if (order.getPlacedByEmployee() != null) { conditions.add("PlacedByEmployee=" + order.getPlacedByEmployee().getUserID()); }
        if (order.getApprovedByEmployee() != null) { conditions.add("ApprovedByEmployee=" + order.getApprovedByEmployee().getUserID()); }
        
        //Execute
        DataHandler.deleteRecords("Order", conditions);
    }
    
    public static void deleteOrder(int orderID) {
        //Order
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + orderID);
        
        //Execute
        DataHandler.deleteRecords("Order", conditions);
    }
}
