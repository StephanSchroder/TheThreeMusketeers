/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Nico
 */
public class DataHandler {
    //TODO: Fix connection String
    private static final String connectionString = "";
    
    public DataHandler(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Class was not found");
        }
    }
    
    //---INSERT QUERY:
    //---Query to replicate:
    //INSERT INTO MainTable(FirstColumn, SecondColumn) VALUES('FirstValue', SecondValue)
    //INSERT INTO MainTable(FirstColumn, SecondColumn) VALUES('ThirdValue', FourthValue)
    //---Command to call:
    //createRecords(new List<string> { "FirstColumn", "SecondColumn" }, "MainTable", new List<string> { "string#FirstValue;int#SecondValue", "string#ThirdValue;int#FourthValue" });
    //---Information:
    //You can insert multiple records, but only in one table, with this command.
    public static void createRecords(List<String> columns, String table, List<String> itemsToStore){
        try
        {
            Connection con = DriverManager.getConnection(connectionString);
            boolean canExecute = false;
            String baseQuery = "INSERT INTO [" + table + "]([";
            
            for (int i = 0; i < columns.size() - 1; i++)
            {
                baseQuery += columns.get(i) + "], [";
            }
            baseQuery += columns.get(columns.size()-1) + "]) VALUES(";
            if (columns.size() == itemsToStore.get(0).split(";").length)
            {
                canExecute = true;
            }
            Statement statement = con.createStatement();
            if (canExecute)
            {
                for(String item:itemsToStore)
                {
                    String valueQuery = "";
                    String[] data = item.split(";");
                    String[] dataWithType;
                    for (int i = 0; i < data.length - 1; i++)
                    {
                        dataWithType = data[i].split("#");
                        if (dataWithType[0].equals("int"))
                        {
                            valueQuery += dataWithType[1] + ", ";
                        }
                        else
                        {
                            valueQuery += "'" + dataWithType[1] + "', ";
                        }
                    }
                    dataWithType = data[data.length - 1].split("#");
                    if (dataWithType[0].equals("int"))
                    {
                        valueQuery += dataWithType[1];
                    }
                    else
                    {
                        valueQuery += "'" + dataWithType[1] + "'";
                    }
                    valueQuery += ")";
                    statement.addBatch(baseQuery + valueQuery);
                }
                int[] rowsAffected = statement.executeBatch();
                for (int i = 0; i < rowsAffected.length; i++) {
                    System.out.println(rowsAffected[i] + " rows were affected during inserting");
                }
            }
            con.close();
        }
        catch (SQLException sqle)
        {
            System.out.println("While creating records, SQL Exception got thrown");
        }
    }
    
    //---SELECT QUERY:
    //---Query to replicate:
    //SELECT * FROM MainTable
    //---Command to call:
    //readRecords(new List<string>{"*"}, new List<Classes.DataTablesCollection>{new Classes.DataTablesCollection("MainTable")}, new List<string>());
    public static ResultSet readRecords(List<String> columns, List<DataTablesCollection> tables, List<String> conditions) {
        ResultSet returnData = null;
        try
        {
            Connection con = DriverManager.getConnection(connectionString);
            String query = "SELECT [";
            
            for (int i = 0; i < columns.size() - 1; i++)
            {
                query += columns.get(i) + "], [";
            }
            query += columns.get(columns.size()-1) + "] FROM [" + tables.get(0).getMainTable() + "] ";
            for (int i = 1; i < tables.size(); i++)
            {
                query += tables.get(i).getJoinType() + " [" + tables.get(i).getMainTable() + "] ON [" + tables.get(i).getMainTable() + "].[" + tables.get(i).getMainTableColumn() + "]=[" + tables.get(i).getJoiningTable() + "].[" + tables.get(i).getJoiningTableColumn() + "] ";
            }
            if (conditions.size() > 0)
            {
                query += "WHERE ";
                for (int i = 0; i < conditions.size() - 1; i++)
                {
                    query += conditions.get(i) + " AND ";
                }
                query += conditions.get(conditions.size()-1);
            }
            
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            returnData = statement.executeQuery(query);
            con.close();
        }
        catch (SQLException sqle)
        {
            System.out.println("While reading records, SQL Exception got thrown");
        }
        
        return returnData;
    }
    
    //---UPDATE QUERY:
    //---Query to replicate:
    //UPDATE MainTable SET FirstColumn='FirstValue', SecondColumn=SecondValue WHERE ThirdColumn=5
    //---Command to call:
    //updateRecords("MainTable", new List<string> { "FirstColumn", "SecondColumn" }, new List<string> { "string;FirstValue", "int;SecondValue" }, new List<string> { "ThirdColumn=5" });
    public static void updateRecords(String table, List<String> columnsToUpdate, List<String> dataToUpdate, List<String> conditions) {
        try {
            Connection con = DriverManager.getConnection(connectionString);
            boolean canExecute = false;
            String baseQuery = "UPDATE [" + table + "] SET ";

            if (columnsToUpdate.size() == dataToUpdate.size())
            {
                int numberOfUpdates = columnsToUpdate.size();
                String[] dataWithType;
                for (int i = 0; i < numberOfUpdates - 1; i++)
                {
                    dataWithType = dataToUpdate.get(i).split(";");
                    if (dataWithType[0].equals("int"))
                    {
                        baseQuery += "[" + columnsToUpdate.get(i) + "]=" + dataWithType[1] + ", ";
                    }
                    else
                    {
                        baseQuery += "[" + columnsToUpdate.get(i) + "]='" + dataWithType[1] + "', ";
                    }
                }
                dataWithType = dataToUpdate.get(numberOfUpdates - 1).split(";");
                if (dataWithType[0].equals("int"))
                {
                    baseQuery += "[" + columnsToUpdate.get(numberOfUpdates - 1) + "]=" + dataWithType[1];
                }
                else
                {
                    baseQuery += "[" + columnsToUpdate.get(numberOfUpdates - 1) + "]='" + dataWithType[1] + "'";
                }
                if (conditions.size() > 0)
                {
                    baseQuery += " WHERE ";
                    for (int i = 0; i < conditions.size() - 1; i++)
                    {
                        baseQuery += conditions.get(i) + " AND ";
                    }
                    baseQuery += conditions.get(conditions.size()-1);
                }
                canExecute = true;
            }
            
            Statement statement = con.createStatement();
            if (canExecute)
            {
                int rowsAffected = statement.executeUpdate(baseQuery);
                System.out.println(rowsAffected + " rows were affected during update");
            }
            con.close();

        } catch (SQLException sqle) {
            System.out.println("While updating records, SQL Exception got thrown");
        }
    }
    //---DELETE QUERY:
    //---Query to replicate:
    //DELETE FROM MainTable WHERE FirstColumn=5 AND SecondColumn='Yes'
    //---Command to call:
    //deleteRecords("MainTable", new List<string> { "FirstColumn=5", "SecondColumn='Yes'" });
    public static void deleteRecords(String table, List<String> conditions) {
        try {
            Connection con = DriverManager.getConnection(connectionString);
            boolean canExecute = false;
            String baseQuery = "DELETE FROM [" + table + "] WHERE ";
            Statement statement = con.createStatement();
            
            if (conditions.size() > 0)
            {
                for (int i = 0; i < conditions.size() - 1; i++)
                {
                    baseQuery += conditions.get(i) + " AND ";
                }
                baseQuery += conditions.get(conditions.size()-1);
                canExecute = true;
            }
            
            if (canExecute) {
                int rowsAffected = statement.executeUpdate(baseQuery);
                System.out.println(rowsAffected + " rows were affected during delete");
            }
            
            con.close();

        } catch (SQLException sqle) {
            System.out.println("While deleting records, SQL Exception got thrown");
        }
    }
}