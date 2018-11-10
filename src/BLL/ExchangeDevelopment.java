/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nico
 */
public class ExchangeDevelopment {
    public static <I> List<I> DataTableToList(Class<?> t, ResultSet data) {
        //Validate ResultSet for valid data
        List<I> results = new ArrayList<I>();
        ResultSetMetaData rsmd = null;;
        int columnCount = -1;
        int rowCount = -1;
        try {
            rsmd = data.getMetaData();
            columnCount = rsmd.getColumnCount();
            data.last();
            rowCount = data.getRow();
            data.beforeFirst();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
        //System.out.println(rsmd.toString() + " " + rowCount + " " + columnCount);
        
        if (rowCount > 0) {
            //Get basic variables to be used
            Constructor[] constructors = t.getConstructors();
            
            //Validate constructors for valid constructors
            try {
                //Iterating per row
                while (data.next()) {
                    //First creating parameters array with row information
                    System.out.println("Now iterating row: " + data.getRow());
                    Object[] tempParameters = new Object[columnCount];
                    int properParameterCount = 0;
                    
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.println("Now iterating column: " + i);
                        Object value = data.getObject(i);
                        if (!data.wasNull()) {
                            switch (rsmd.getColumnTypeName(i)) {
                                case "DATETIME":
                                    tempParameters[properParameterCount] = new java.util.Date(((java.sql.Timestamp)value).getTime());
                                    break;
                                case "DATE":
                                    tempParameters[properParameterCount] = new java.util.Date(((java.sql.Date)value).getTime());
                                    break;
                                case "DECIMAL":
                                    tempParameters[properParameterCount] = ((BigDecimal)value).doubleValue();
                                    break;
                                default:
                                    tempParameters[properParameterCount] = value;
                                    break;
                            }
                            System.out.println(tempParameters[properParameterCount].getClass().getName());
                            properParameterCount++;
                        } else {
                            System.out.println("null");
                            if (!rsmd.getColumnTypeName(i).equals("INT")) {
                                tempParameters[properParameterCount] = null;
                                properParameterCount++;
                            }
                        }
                        System.out.println("rsmd data type - " + rsmd.getCatalogName(i) + "|" + rsmd.getColumnClassName(i) + "|" + rsmd.getColumnLabel(i) + "|" + rsmd.getColumnName(i) + "|" + rsmd.getColumnTypeName(i) + "|" + rsmd.getColumnType(i) + "|" + rsmd.isNullable(i));
                    }
                    
                    Object[] parameters = new Object[properParameterCount];
                    for (int i = 0; i < properParameterCount; i++) {
                        parameters[i] = tempParameters[i];
                    }
                    
                    //Now checking for constructor data type match
                    System.out.println("Now checking constructor match");
                    int constructorIndex = 0;
                    boolean constructorFound = false;
                    
                    while (!constructorFound && constructorIndex < constructors.length) {
                        Constructor constructor = constructors[constructorIndex];
                        printConstructorParameters(constructor);
                        if (constructor.getParameterCount() == parameters.length) {
                            System.out.println("Checking potential constructor " + constructorIndex);
                            constructorFound = constructorMatch(parameters, constructor);
                        }
                        if (!constructorFound) {
                            constructorIndex++;
                            System.out.println("Not valid constructor");
                        }
                    }
                    if (constructorFound) {
                        System.out.println("Constructor found !");
                        //Invoke that constructor
                        try {
                            System.out.println("Invoking constructor: " + constructorIndex);
                            results.add((I)constructors[constructorIndex].<I>newInstance(parameters));
                        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            Logger.getLogger(ExchangeDevelopment.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    System.out.println();
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(ExchangeDevelopment.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        return results;
    }
    
    private static void printParameters(Object[] parameters) {
        for (int i = 0; i < parameters.length; i++) {
            System.out.println("Parameter " + i + ": " + parameters[i].getClass().getName());
        }
    }
    
    private static void printConstructorParameters(Constructor constructor) {
        for (int i = 0; i < constructor.getParameterCount(); i++) {
            System.out.println("Constructor parameter " + (i+1) + "/" + constructor.getParameterCount() + ": " + constructor.getParameters()[i].getParameterizedType().getTypeName());
        }
    }
    
    private static Object[] properDataTypes(Object[] parameters, Constructor constructor) {
        System.out.println("Converting proper types start");
        Object[] newParameters = new Object[parameters.length];
        for (int i = 0; i < constructor.getParameterCount(); i++) {
            if (parameters[i] != null) {
                if (!parameters[i].getClass().getName().equals(constructor.getParameters()[i].getParameterizedType().getTypeName())) {
                    switch (parameters[i].getClass().getName()) {
                        case "java.sql.Timestamp":
                            System.out.println("Timestamp " + (i+1) + " : parameter - " + parameters[i].toString() + ",parameter type - " + parameters[i].getClass().getName() + ", constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
                            if (constructor.getParameters()[i].getParameterizedType().getTypeName().equals("java.util.Date")) {
                                newParameters[i] = new java.util.Date(((java.sql.Timestamp)parameters[i]).getTime());
                            }
                            break;
                        case "java.sql.Date":
                            System.out.println("Date " + (i+1) + " : parameter - " + parameters[i].toString() + ",parameter type - " + parameters[i].getClass().getName() + ", constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
                            if (constructor.getParameters()[i].getParameterizedType().getTypeName().equals("java.util.Date")) {
                                newParameters[i] = new java.util.Date(((java.sql.Date)parameters[i]).getTime());
                            }
                            break;
                        case "java.math.BigDecimal":
                            System.out.println("BigDecimal " + (i+1) + " : parameter - " + parameters[i].toString() + ",parameter type - " + parameters[i].getClass().getName() + ", constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
                            if (constructor.getParameters()[i].getParameterizedType().getTypeName().equals("double")) {
                                newParameters[i] = ((BigDecimal)parameters[i]).doubleValue();
                            }
                            break;
                        default:
                            newParameters[i] = parameters[i];
                            System.out.println("Default " + (i+1) + " : parameter - " + parameters[i].toString() + ",parameter type - " + parameters[i].getClass().getName() + ", constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
                            break;
                    }
                }
                else {
                    newParameters[i] = parameters[i];
                }
            }
            else {
                newParameters[i] = null;
            }
        }
        System.out.println("Converting proper types end");
        return newParameters;
    }
    
    private static boolean constructorMatch(Object[] parameters, Constructor constructor) {
        System.out.println("Constructor match start");
        boolean result = true;
        for (int i = 0; i < constructor.getParameterCount(); i++) {
            System.out.println(result);
            if (parameters[i] != null) {
                if (!parameters[i].getClass().getName().equals(constructor.getParameters()[i].getParameterizedType().getTypeName())) {
                    switch (parameters[i].getClass().getName()) {
                        case "java.lang.Integer":
                            System.out.println("Integer " + (i+1) + " : parameter - " + parameters[i].toString() + ",parameter type - " + parameters[i].getClass().getName() + ", constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
                            if (!constructor.getParameters()[i].getParameterizedType().getTypeName().equals("int")) {
                                result = false;
                            }
                            break;
                        case "java.sql.Timestamp":
                            System.out.println("Timestamp " + (i+1) + " : parameter - " + parameters[i].toString() + ",parameter type - " + parameters[i].getClass().getName() + ", constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
                            if (!constructor.getParameters()[i].getParameterizedType().getTypeName().equals("java.util.Date")) {
                                result = false;
                            }
                            break;
                        case "java.sql.Date":
                            System.out.println("Date " + (i+1) + " : parameter - " + parameters[i].toString() + ",parameter type - " + parameters[i].getClass().getName() + ", constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
                            if (!constructor.getParameters()[i].getParameterizedType().getTypeName().equals("java.util.Date")) {
                                result = false;
                            }
                            break;
                        case "java.lang.Double":
                            System.out.println("Double " + (i+1) + " : parameter - " + parameters[i].toString() + ",parameter type - " + parameters[i].getClass().getName() + ", constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
                            if (!constructor.getParameters()[i].getParameterizedType().getTypeName().equals("double")) {
                                result = false;
                            }
                            break;
                        case "java.math.BigDecimal":
                            System.out.println("BigDecimal " + (i+1) + " : parameter - " + parameters[i].toString() + ",parameter type - " + parameters[i].getClass().getName() + ", constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
                            if (!constructor.getParameters()[i].getParameterizedType().getTypeName().equals("double")) {
                                result = false;
                            }
                            break;
                        case "java.lang.Boolean":
                            System.out.println("Boolean " + (i+1) + " : parameter - " + parameters[i].toString() + ",parameter type - " + parameters[i].getClass().getName() + ", constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
                            if (!constructor.getParameters()[i].getParameterizedType().getTypeName().equals("boolean")) {
                                result = false;
                            }
                            break;
                        default:
                            result = false;
                            System.out.println("Default " + (i+1) + " : parameter - " + parameters[i].toString() + ",parameter type - " + parameters[i].getClass().getName() + ", constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
                            break;
                    }
                }
                System.out.println("Check " + (i+1) + " : parameter - " + parameters[i].toString() + ",parameter type - " + parameters[i].getClass().getName() + ", constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
            }
            else {
                if (constructor.getParameters()[i].getParameterizedType().getTypeName().equals("byte") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("short") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("int") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("long") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("float") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("double") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("boolean") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("char")) {
                    result = false;
                }
                System.out.println("Default " + (i+1) + " : parameter - null,parameter type - null, constructor parameter type - " + constructor.getParameters()[i].getParameterizedType().getTypeName());
            }
            System.out.println(result);
        }
        System.out.println("Constructor match end");
        return result;
    }
}
