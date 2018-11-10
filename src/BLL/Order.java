/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

//<editor-fold defaultstate="collapsed" desc="imports">
import BLL.Interfaces.DatabaseOperations;
import DAL.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
//</editor-fold>

/**
 *
 * @author Stephan
 */
public class Order implements DatabaseOperations {

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private int orderID;
    private Date orderDate;
    private Date receiveDate;
    private String status;
    private User placedByEmployee;
    private User approvedByEmployee;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Order(int orderID, Date orderDate, Date receiveDate, String status, User placedByEmployee, User approvedByEmployee) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.receiveDate = receiveDate;
        this.status = status;
        this.placedByEmployee = placedByEmployee;
        this.approvedByEmployee = approvedByEmployee;
    }
    
    public Order(int orderID, Date orderDate, Date receiveDate, String status, User placedByEmployee) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.receiveDate = receiveDate;
        this.status = status;
        this.placedByEmployee = placedByEmployee;
    }

    public Order(int orderID, Date orderDate, String status, User placedByEmployee) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.status = status;
        this.placedByEmployee = placedByEmployee;
    }

    public Order(int orderID, Date orderDate, Date receiveDate, String status, int placedByEmployee, int approvedByEmployee) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.receiveDate = receiveDate;
        this.status = status;
        this.placedByEmployee = User.read(placedByEmployee);
        this.approvedByEmployee = User.read(approvedByEmployee);
    }

    public Order(int orderID, Date orderDate, Date receiveDate, String status, int placedByEmployee) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.receiveDate = receiveDate;
        this.status = status;
        this.placedByEmployee = User.read(placedByEmployee);
    }

    public Order(int orderID, Date orderDate, String status, int placedByEmployee) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.status = status;
        this.placedByEmployee = User.read(placedByEmployee);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Properties">
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getOrderDateString() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(orderDate);
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public String getReceiveDateString() {
        if (receiveDate == null) {
            return null;
        }
        else {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(receiveDate);
        }
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Override Methods">
    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", orderDate=" + this.getOrderDateString() + ", receiveDate=" + this.getReceiveDateString() + ", status=" + status + ", placedByEmployee=" + placedByEmployee + ", approvedByEmployee=" + approvedByEmployee + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.orderID;
        hash = 67 * hash + Objects.hashCode(this.orderDate);
        hash = 67 * hash + Objects.hashCode(this.receiveDate);
        hash = 67 * hash + Objects.hashCode(this.status);
        hash = 67 * hash + Objects.hashCode(this.placedByEmployee);
        hash = 67 * hash + Objects.hashCode(this.approvedByEmployee);
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
        final Order other = (Order) obj;
        if (this.orderID != other.orderID) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.receiveDate, other.receiveDate)) {
            return false;
        }
        if (!Objects.equals(this.placedByEmployee, other.placedByEmployee)) {
            return false;
        }
        if (!Objects.equals(this.approvedByEmployee, other.approvedByEmployee)) {
            return false;
        }
        return true;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="read Methods">
    public static List<Order> read() {
        return DataHandler.<Order>readRecords(Order.class, Arrays.asList("OrderID", "OrderDate", "ReceiveDate", "Status", "PlacedByEmployee", "ApprovedByEmployee"), Arrays.asList(new DataTablesCollection("Order")), Arrays.asList());
    }

    public static Order read(int orderID) {
        return DataHandler.<Order>readRecords(Order.class, Arrays.asList("OrderID", "OrderDate", "ReceiveDate", "Status", "PlacedByEmployee", "ApprovedByEmployee"), Arrays.asList(new DataTablesCollection("Order")), Arrays.asList("OrderID=" + orderID)).get(0);
    }

    public static List<Order> read(String status) {
        return DataHandler.<Order>readRecords(Order.class, Arrays.asList("OrderID", "OrderDate", "ReceiveDate", "Status", "PlacedByEmployee", "ApprovedByEmployee"), Arrays.asList(new DataTablesCollection("Order")), Arrays.asList("Status='" + status + "'"));
    }

    public static List<Order> readByPlacedByEmployee(int placedByEmployeeID) {
        return DataHandler.<Order>readRecords(Order.class, Arrays.asList("OrderID", "OrderDate", "ReceiveDate", "Status", "PlacedByEmployee", "ApprovedByEmployee"), Arrays.asList(new DataTablesCollection("Order")), Arrays.asList("PlacedByEmployee=" + placedByEmployeeID));
    }

    public static List<Order> readByApprovedByEmployee(int approvedByEmployeeID) {
        return DataHandler.<Order>readRecords(Order.class, Arrays.asList("OrderID", "OrderDate", "ReceiveDate", "Status", "PlacedByEmployee", "ApprovedByEmployee"), Arrays.asList(new DataTablesCollection("Order")), Arrays.asList("ApprovedByEmployee=" + approvedByEmployeeID));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="create Methods">
    @Override
    public void create() {
        //Order
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("PlacedByEmployee");
        if (!this.getStatus().isEmpty()) {
            columns.add("Status");
        }

        //Values
        String values = "";
        values += "int#" + this.getPlacedByEmployee().getUserID();
        if (!this.getStatus().isEmpty()) {
            values += ";string#" + this.getStatus();
        }

        //Execute
        DataHandler.createRecords(columns, "Order", Arrays.asList(values));
    }

    public static void create(Order order) {
        //Order
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        columns.add("PlacedByEmployee");
        if (!order.getStatus().isEmpty()) {
            columns.add("Status");
        }

        //Values
        String values = "";
        values += "int#" + order.getPlacedByEmployee().getUserID();
        if (!order.getStatus().isEmpty()) {
            values += ";string#" + order.getStatus();
        }

        //Execute
        DataHandler.createRecords(columns, "Order", Arrays.asList(values));
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="update Methods">
    @Override
    public void update() {
        //Order
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        if (this.getReceiveDate() != null) {
            columns.add("ReceiveDate");
        }
        if (!this.getStatus().isEmpty()) {
            columns.add("Status");
        }
        if (this.getApprovedByEmployee() != null) {
            columns.add("ApprovedByEmployee");
        }

        //Values
        ArrayList<String> values = new ArrayList<>();
        if (this.getReceiveDate() != null) {
            values.add("string;" + this.getReceiveDateString());
        }
        if (!this.getStatus().isEmpty()) {
            values.add("string;" + this.getStatus());
        }
        if (this.getApprovedByEmployee() != null) {
            values.add("string;" + this.getApprovedByEmployee().getUserID());
        }

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + this.getOrderID());

        //Execute
        DataHandler.updateRecords("Order", columns, values, conditions);
    }

    public static void update(Order order) {
        //Order
        //Columns
        ArrayList<String> columns = new ArrayList<>();
        if (order.getReceiveDate() != null) {
            columns.add("ReceiveDate");
        }
        if (!order.getStatus().isEmpty()) {
            columns.add("Status");
        }
        if (order.getApprovedByEmployee() != null) {
            columns.add("ApprovedByEmployee");
        }

        //Values
        ArrayList<String> values = new ArrayList<>();
        if (order.getReceiveDate() != null) {
            values.add("string;" + order.getReceiveDateString());
        }
        if (!order.getStatus().isEmpty()) {
            values.add("string;" + order.getStatus());
        }
        if (order.getApprovedByEmployee() != null) {
            values.add("string;" + order.getApprovedByEmployee().getUserID());
        }

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + order.getOrderID());

        //Execute
        DataHandler.updateRecords("Order", columns, values, conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="delete Methods">
    @Override
    public void delete() {
        //Order
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + this.getOrderID());
        conditions.add("OrderDate='" + this.getOrderDateString() + "'");
        if (this.getReceiveDate() != null) {
            conditions.add("ReceiveDate='" + this.getReceiveDateString() + "'");
        }
        if (!this.getStatus().isEmpty()) {
            conditions.add("Status='" + this.getStatus() + "'");
        }
        if (this.getPlacedByEmployee() != null) {
            conditions.add("PlacedByEmployee=" + this.getPlacedByEmployee().getUserID());
        }
        if (this.getApprovedByEmployee() != null) {
            conditions.add("ApprovedByEmployee=" + this.getApprovedByEmployee().getUserID());
        }

        //Execute
        DataHandler.deleteRecords("Order", conditions);
    }

    public static void delete(Order order) {
        //Order
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + order.getOrderID());
        conditions.add("OrderDate='" + order.getOrderDateString() + "'");
        if (order.getReceiveDate() != null) {
            conditions.add("ReceiveDate='" + order.getReceiveDateString() + "'");
        }
        if (!order.getStatus().isEmpty()) {
            conditions.add("Status='" + order.getStatus() + "'");
        }
        if (order.getPlacedByEmployee() != null) {
            conditions.add("PlacedByEmployee=" + order.getPlacedByEmployee().getUserID());
        }
        if (order.getApprovedByEmployee() != null) {
            conditions.add("ApprovedByEmployee=" + order.getApprovedByEmployee().getUserID());
        }

        //Execute
        DataHandler.deleteRecords("Order", conditions);
    }

    public static void delete(int orderID) {
        //Order
        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        conditions.add("OrderID=" + orderID);

        //Execute
        DataHandler.deleteRecords("Order", conditions);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="synchronise Methods">
    @Override
    public boolean synchronise() {
        //Campus
        boolean foundInDatabase = false;

        //Conditions
        ArrayList<String> conditions = new ArrayList<>();
        if (this.getOrderID() > 0) {
            conditions.add("OrderID=" + this.getOrderID());
        } else {
            conditions.add("OrderDate='" + this.getOrderDateString() + "'");
            if (this.getReceiveDate() != null) {
                conditions.add("ReceiveDate='" + this.getReceiveDateString() + "'");
            }
            conditions.add("Status='" + this.getStatus() + "'");
            conditions.add("PlacedByEmployee=" + this.getPlacedByEmployee().getUserID());
            if (this.getApprovedByEmployee() != null) {
                conditions.add("ApprovedByEmployee=" + this.getApprovedByEmployee().getUserID());
            }
        }

        //Retrieving new instance
        List<Order> orders = DataHandler.<Order>readRecords(Order.class, Arrays.asList("OrderID", "OrderDate", "ReceiveDate", "Status", "PlacedByEmployee", "ApprovedByEmployee"), Arrays.asList(new DataTablesCollection("Order")), conditions);

        //Synchronising
        if (orders.size() == 1) {
            foundInDatabase = true;
            Order order = orders.get(0);

            this.setOrderID(order.getOrderID());
            this.setOrderDate(order.getOrderDate());
            if (order.getReceiveDate() != null) {
                this.setReceiveDate(order.getReceiveDate());
            }
            this.setStatus(order.getStatus());
            this.setPlacedByEmployee(order.getPlacedByEmployee());
            if (order.getApprovedByEmployee() != null) {
                this.setApprovedByEmployee(order.getApprovedByEmployee());
            }
        }

        return foundInDatabase;
    }
    //</editor-fold>

}

//NOTES:
//METHODS NEED TO BE REVISED
//METHODS NEED TO BE CHECKED FOR WORKING CONDITION
//MAKE SURE DATES USE 'new SimpleDateFormat("yyyy-MM-dd").format(date);' OR 'new SimpleDateFormat("yyyy-MM-dd").parse(date);'
//MAKE SURE DATETIMES USE 'new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").format(dateTime);' OR 'new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").parse(dateTime);'
