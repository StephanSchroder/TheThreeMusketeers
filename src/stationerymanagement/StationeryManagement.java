/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stationerymanagement;

import BLL.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
//        System.out.println("Reading started...");
//        System.out.println("Address");
//        List<Address> addresses = Address.read();
//        System.out.println("Campus");
//        List<Campus> campuses = Campus.read();
//        System.out.println("Category");
//        List<Category> categories = Category.read();
//        System.out.println("Contact");
//        List<Contact> contacts = Contact.read();
//        System.out.println("Department");
//        List<Department> departments = Department.read();
//        System.out.println("Order");
//        List<Order> orders = Order.read();
//        System.out.println("Stock");
//        List<Stock> stocks = Stock.read();
//        System.out.println("StockOrder");
//        List<StockOrder> stockorders = StockOrder.read();
//        System.out.println("User");
//        List<User> users = User.read();
//        System.out.println("Reading done...");
//        
//        System.out.println("Display started...");
//        System.out.println("Address");
//        for (Address address : addresses) {
//            System.out.println(address.toString());
//        }
//        System.out.println("Campus");
//        for (Campus campus : campuses) {
//            System.out.println(campus.toString());
//        }
//        System.out.println("Category");
//        for (Category category : categories) {
//            System.out.println(category.toString());
//        }
//        System.out.println("Contact");
//        for (Contact contact : contacts) {
//            System.out.println(contact.toString());
//        }
//        System.out.println("Department");
//        for (Department department : departments) {
//            System.out.println(department.toString());
//        }
//        System.out.println("Order");
//        for (Order order : orders) {
//            System.out.println(order.toString());
//        }
//        System.out.println("Stock");
//        for (Stock stock : stocks) {
//            System.out.println(stock.toString());
//        }
//        System.out.println("StockOrder");
//        for (StockOrder stockorder : stockorders) {
//            System.out.println(stockorder.toString());
//        }
//        System.out.println("User");
//        for (User user : users) {
//            System.out.println(user.toString());
//        }
//        System.out.println("Display done...");


//        try {
//            String password = "Frikkie@52";
//            String encryptedPassword = Common.encryptPassword(password);
//            String password2 = "Frikkie@42";
//            String encryptedPassword2 = Common.encryptPassword(password2);
//            String password3 = "Frikkie@52";
//            String encryptedPassword3 = Common.encryptPassword(password3);
//            String password4 = "Frikkie@52";
//            String encryptedPassword4 = Common.encryptPassword(password4);
//            String password5 = "Frikkie@52";
//            String encryptedPassword5 = Common.encryptPassword(password5);
//            String encryptedPassword6 = Common.encryptPassword(password);
//            String encryptedPassword7 = Common.encryptPassword(password);
//            String encryptedPassword8 = Common.encryptPassword(password);
//            String encryptedPassword9 = Common.encryptPassword(password);
//            String encryptedPassword10 = Common.encryptPassword(password);
//            String encryptedPassword11 = Common.encryptPassword(password);
//            String encryptedPassword12 = Common.encryptPassword(password);
//            String encryptedPassword13 = Common.encryptPassword(password);
//            String encryptedPassword14 = Common.encryptPassword(password);
//            String encryptedPassword15 = Common.encryptPassword(password);
//            String encryptedPassword16 = Common.encryptPassword(password);
//            String encryptedPassword17 = Common.encryptPassword(password);
//            String encryptedPassword18 = Common.encryptPassword(password);
//            String encryptedPassword19 = Common.encryptPassword(password);
//            String encryptedPassword20 = Common.encryptPassword(password);
//            String encryptedPassword21 = Common.encryptPassword(password);
//            String encryptedPassword22 = Common.encryptPassword(password);
//            String encryptedPassword23 = Common.encryptPassword(password);
//            
//            System.out.println("Password 1: " + password);
//            System.out.println("Password 2: " + password2);
//            System.out.println("Password 3: " + password3);
//            System.out.println("Password 4: " + password4);
//            System.out.println("Password 5: " + password5);
//            System.out.println("Encrypted 1: " + encryptedPassword);
//            System.out.println("Encrypted 2: " + encryptedPassword2);
//            System.out.println("Encrypted 3: " + encryptedPassword3);
//            System.out.println("Encrypted 4: " + encryptedPassword4);
//            System.out.println("Encrypted 5: " + encryptedPassword5);
//            System.out.println("Password 1 length: " + password.length());
//            System.out.println("Password 2 length: " + password2.length());
//            System.out.println("Password 3 length: " + password3.length());
//            System.out.println("Password 4 length: " + password4.length());
//            System.out.println("Password 5 length: " + password5.length());
//            System.out.println("Encrypted 1 length: " + encryptedPassword.length());
//            System.out.println("Encrypted 2 length: " + encryptedPassword2.length());
//            System.out.println("Encrypted 3 length: " + encryptedPassword3.length());
//            System.out.println("Encrypted 4 length: " + encryptedPassword4.length());
//            System.out.println("Encrypted 5 length: " + encryptedPassword5.length());
//            System.out.println("Encrypted 6 length: " + encryptedPassword6.length());
//            System.out.println("Encrypted 7 length: " + encryptedPassword7.length());
//            System.out.println("Encrypted 8 length: " + encryptedPassword8.length());
//            System.out.println("Encrypted 9 length: " + encryptedPassword9.length());
//            System.out.println("Encrypted 10 length: " + encryptedPassword10.length());
//            System.out.println("Encrypted 11 length: " + encryptedPassword11.length());
//            System.out.println("Encrypted 12 length: " + encryptedPassword12.length());
//            System.out.println("Encrypted 13 length: " + encryptedPassword13.length());
//            System.out.println("Encrypted 14 length: " + encryptedPassword14.length());
//            System.out.println("Encrypted 15 length: " + encryptedPassword15.length());
//            System.out.println("Encrypted 16 length: " + encryptedPassword16.length());
//            System.out.println("Encrypted 17 length: " + encryptedPassword17.length());
//            System.out.println("Encrypted 18 length: " + encryptedPassword18.length());
//            System.out.println("Encrypted 19 length: " + encryptedPassword19.length());
//            System.out.println("Encrypted 20 length: " + encryptedPassword20.length());
//            System.out.println("Encrypted 21 length: " + encryptedPassword21.length());
//            System.out.println("Encrypted 22 length: " + encryptedPassword22.length());
//            System.out.println("Encrypted 23 length: " + encryptedPassword23.length());
//            System.out.println("== method (e1 on e3): " + (encryptedPassword==encryptedPassword3));
//            System.out.println("Equals method (e1 on e3): " + encryptedPassword.equals(encryptedPassword3));
//            System.out.println("Verify Password method (1 on e1): " + Common.validatePassword(password, encryptedPassword));
//            System.out.println("Verify Password method (2 on e2): " + Common.validatePassword(password2, encryptedPassword2));
//            System.out.println("Verify Password method (3 on e3): " + Common.validatePassword(password3, encryptedPassword3));
//            System.out.println("Verify Password method (1 on e3): " + Common.validatePassword(password, encryptedPassword3));
//            System.out.println("Verify Password method (3 on e1): " + Common.validatePassword(password3, encryptedPassword));
//            System.out.println("Verify Password method (5 on e1): " + Common.validatePassword(password5, encryptedPassword));
//            System.out.println("Verify Password method (1 on e5): " + Common.validatePassword(password, encryptedPassword5));
//            System.out.println("Verify Password method (1 on e2): " + Common.validatePassword(password, encryptedPassword2));
//            System.out.println("Verify Password method (2 on e1): " + Common.validatePassword(password2, encryptedPassword));
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(StationeryManagement.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (InvalidKeySpecException ex) {
//            Logger.getLogger(StationeryManagement.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {
            String realPassword = "Admin";
            String realPasswordEncrypted = Common.encryptPassword(realPassword);
            System.out.println(realPassword.length() + " : " + realPasswordEncrypted.length() + " : " + realPasswordEncrypted);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(StationeryManagement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(StationeryManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
