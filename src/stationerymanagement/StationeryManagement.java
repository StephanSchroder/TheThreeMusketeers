/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stationerymanagement;

import BLL.*;
import java.util.List;

/**
 *
 * @author Stephan
 */
public class StationeryManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Reading started...");
        System.out.println("Address");
        List<Address> addresses = Address.read();
        System.out.println("Campus");
        List<Campus> campuses = Campus.read();
        System.out.println("Category");
        List<Category> categories = Category.read();
        System.out.println("Contact");
        List<Contact> contacts = Contact.read();
        System.out.println("Department");
        List<Department> departments = Department.read();
        System.out.println("Order");
        List<Order> orders = Order.read();
        System.out.println("Stock");
        List<Stock> stocks = Stock.read();
        System.out.println("StockOrder");
        List<StockOrder> stockorders = StockOrder.read();
        System.out.println("User");
        List<User> users = User.read();
        System.out.println("Reading done...");
        
        System.out.println("Display started...");
        System.out.println("Address");
        for (Address address : addresses) {
            System.out.println(address.toString());
        }
        System.out.println("Campus");
        for (Campus campus : campuses) {
            System.out.println(campus.toString());
        }
        System.out.println("Category");
        for (Category category : categories) {
            System.out.println(category.toString());
        }
        System.out.println("Contact");
        for (Contact contact : contacts) {
            System.out.println(contact.toString());
        }
        System.out.println("Department");
        for (Department department : departments) {
            System.out.println(department.toString());
        }
        System.out.println("Order");
        for (Order order : orders) {
            System.out.println(order.toString());
        }
        System.out.println("Stock");
        for (Stock stock : stocks) {
            System.out.println(stock.toString());
        }
        System.out.println("StockOrder");
        for (StockOrder stockorder : stockorders) {
            System.out.println(stockorder.toString());
        }
        System.out.println("User");
        for (User user : users) {
            System.out.println(user.toString());
        }
        System.out.println("Display done...");
        
    }
    
}
