/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

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
    private User isssuedByEmployee;
    private User approvedByEmployee;
    private LinkedList<StockOrder> stock;

    public Order(int orderID, Date orderDate, Date receiveDate, String status, User isssuedByEmployee, User approvedByEmployee, LinkedList<StockOrder> stock) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.receiveDate = receiveDate;
        this.status = status;
        this.isssuedByEmployee = isssuedByEmployee;
        this.approvedByEmployee = approvedByEmployee;
        this.stock = stock;
    }

    public Order(int orderID, Date orderDate, String status, User isssuedByEmployee) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.status = status;
        this.isssuedByEmployee = isssuedByEmployee;
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

    public User getIssuedByEmployee() {
        return isssuedByEmployee;
    }

    public void setIssuedByEmployee(User isssuedByEmployee) {
        this.isssuedByEmployee = isssuedByEmployee;
    }

    public User getApprovedByEmployee() {
        return approvedByEmployee;
    }

    public void setApprovedByEmployee(User approvedByEmployee) {
        this.approvedByEmployee = approvedByEmployee;
    }

    public LinkedList<StockOrder> getStock() {
        return stock;
    }

    public void setStock(LinkedList<StockOrder> stock) {
        this.stock = stock;
    }
}
