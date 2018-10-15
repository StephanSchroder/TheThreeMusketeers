/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Stephan
 */
public class DataHandlerOld {
    
    
    Connection connection = null;

    public DataHandlerOld() {
    }
    
    
    
    public void getConnection()
    {
       
        System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println(" Oracle JDBC Driver not found");
            e.printStackTrace();
            return;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        

        try {

            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "password");

        } catch (SQLException e) {

            System.out.println("Connection Failed!");
            e.printStackTrace();
            return;

        }

        if (connection != null) {
            System.out.println("Success");
        } else {
            System.out.println("Aww Shnap, it failed!");
        }
    }
}
