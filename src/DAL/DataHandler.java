/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BLL.Exchange;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Nico
 */
public class DataHandler {
    private static volatile DataHandler onlyInstance = new DataHandler();
    private static final String connectionString = "jdbc:mysql://localhost:3306/stationerymanagementdb?";
    
    private DataHandler(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException cnfe) {
            System.out.println(cnfe.getMessage());
        }
    }
    
    public static DataHandler getInstance() {
        return onlyInstance;
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
            Connection con = DriverManager.getConnection(connectionString, "root", "");
            boolean canExecute = false;
            String baseQuery = "INSERT INTO `" + table + "`(`";
            
            for (int i = 0; i < columns.size() - 1; i++)
            {
                baseQuery += columns.get(i) + "`, `";
            }
            baseQuery += columns.get(columns.size()-1) + "`) VALUES(";
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
            System.out.println(sqle.getMessage());
        }
    }
    
    //---SELECT QUERY:
    //---Query to replicate:
    //SELECT * FROM MainTable
    //---Command to call:
    //readRecords(new List<string>{"*"}, new List<Classes.DataTablesCollection>{new Classes.DataTablesCollection("MainTable")}, new List<string>());
    public static String[][] readRecords(List<String> columns, List<DataTablesCollection> tables, List<String> conditions) {
        String[][] returnData = null;
        try
        {
            Connection con = DriverManager.getConnection(connectionString, "root", "");
            String query = "SELECT `";
            
            for (int i = 0; i < columns.size() - 1; i++)
            {
                query += columns.get(i) + "`, `";
            }
            query += columns.get(columns.size()-1) + "` FROM `" + tables.get(0).getMainTable() + "` ";
            for (int i = 1; i < tables.size(); i++)
            {
                query += tables.get(i).getJoinType() + " `" + tables.get(i).getMainTable() + "` ON `" + tables.get(i).getMainTable() + "`.`" + tables.get(i).getMainTableColumn() + "`=`" + tables.get(i).getJoiningTable() + "`.`" + tables.get(i).getJoiningTableColumn() + "` ";
            }
            if (conditions.size() > 0)
            {
                query += "WHERE (";
                for (int i = 0; i < conditions.size() - 1; i++)
                {
                    query += conditions.get(i) + ") AND (";
                }
                query += conditions.get(conditions.size()-1) + ")";
            }
            
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet dbReturnData = statement.executeQuery(query);
            
            int totalColumnCount = dbReturnData.getMetaData().getColumnCount();
            int totalRowCount = 0;
            try {
                boolean b = dbReturnData.last();
                totalRowCount = dbReturnData.getRow();
                dbReturnData.beforeFirst();
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
            }
            
            returnData = new String[totalRowCount][totalColumnCount];
            while (dbReturnData.next()) {
                for (int j = 0; j < totalColumnCount; j++) {
                    returnData[dbReturnData.getRow()-1][j] = dbReturnData.getString(j+1);
                }
            }
            
            con.close();
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle.getMessage());
        }
        
        return returnData;
    }
    
    public static <T> List<T> readRecords(Class<?> t, List<String> columns, List<DataTablesCollection> tables, List<String> conditions) {
        List<T> returnData = new ArrayList<>();
        try
        {
            Connection con = DriverManager.getConnection(connectionString, "root", "");
            String query = "SELECT `";
            
            for (int i = 0; i < columns.size() - 1; i++)
            {
                query += columns.get(i) + "`, `";
            }
            query += columns.get(columns.size()-1) + "` FROM `" + tables.get(0).getMainTable() + "` ";
            for (int i = 1; i < tables.size(); i++)
            {
                query += tables.get(i).getJoinType() + " `" + tables.get(i).getMainTable() + "` ON `" + tables.get(i).getMainTable() + "`.`" + tables.get(i).getMainTableColumn() + "`=`" + tables.get(i).getJoiningTable() + "`.`" + tables.get(i).getJoiningTableColumn() + "` ";
            }
            if (conditions.size() > 0)
            {
                query += "WHERE (";
                for (int i = 0; i < conditions.size() - 1; i++)
                {
                    query += conditions.get(i) + ") AND (";
                }
                query += conditions.get(conditions.size()-1) + ")";
            }
            
            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet dbReturnData = statement.executeQuery(query);
            
            returnData = Exchange.<T>DataTableToList(t, dbReturnData);
            
            con.close();
        }
        catch (SQLException sqle)
        {
            System.out.println(sqle.getMessage());
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
            Connection con = DriverManager.getConnection(connectionString, "root", "");
            boolean canExecute = false;
            String baseQuery = "UPDATE `" + table + "` SET ";

            if (columnsToUpdate.size() == dataToUpdate.size())
            {
                int numberOfUpdates = columnsToUpdate.size();
                String[] dataWithType;
                for (int i = 0; i < numberOfUpdates - 1; i++)
                {
                    dataWithType = dataToUpdate.get(i).split(";");
                    if (dataWithType[0].equals("int"))
                    {
                        baseQuery += "`" + columnsToUpdate.get(i) + "`=" + dataWithType[1] + ", ";
                    }
                    else
                    {
                        baseQuery += "`" + columnsToUpdate.get(i) + "`='" + dataWithType[1] + "', ";
                    }
                }
                dataWithType = dataToUpdate.get(numberOfUpdates - 1).split(";");
                if (dataWithType[0].equals("int"))
                {
                    baseQuery += "`" + columnsToUpdate.get(numberOfUpdates - 1) + "`=" + dataWithType[1];
                }
                else
                {
                    baseQuery += "`" + columnsToUpdate.get(numberOfUpdates - 1) + "`='" + dataWithType[1] + "'";
                }
                if (conditions.size() > 0)
                {
                    baseQuery += " WHERE (";
                    for (int i = 0; i < conditions.size() - 1; i++)
                    {
                        baseQuery += conditions.get(i) + ") AND (";
                    }
                    baseQuery += conditions.get(conditions.size()-1) + ")";
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
            System.out.println(sqle.getMessage());
        }
    }
    //---DELETE QUERY:
    //---Query to replicate:
    //DELETE FROM MainTable WHERE FirstColumn=5 AND SecondColumn='Yes'
    //---Command to call:
    //deleteRecords("MainTable", new List<string> { "FirstColumn=5", "SecondColumn='Yes'" });
    public static void deleteRecords(String table, List<String> conditions) {
        try {
            Connection con = DriverManager.getConnection(connectionString, "root", "");
            boolean canExecute = false;
            String baseQuery = "DELETE FROM `" + table + "` WHERE (";
            Statement statement = con.createStatement();
            
            if (conditions.size() > 0)
            {
                for (int i = 0; i < conditions.size() - 1; i++)
                {
                    baseQuery += conditions.get(i) + ") AND (";
                }
                baseQuery += conditions.get(conditions.size()-1) + ")";
                canExecute = true;
            }
            
            if (canExecute) {
                int rowsAffected = statement.executeUpdate(baseQuery);
                System.out.println(rowsAffected + " rows were affected during delete");
            }
            
            con.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
}
