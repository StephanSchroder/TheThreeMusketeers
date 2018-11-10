/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nico
 */
public class Exchange {
    public static <I> List<I> DataTableToList(Class<?> t, ResultSet data) {
        //Validate ResultSet for valid data
        List<I> results = new ArrayList<>();
        ResultSetMetaData rsmd = null;
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
        
        if (rowCount > 0) {
            //Get basic variables to be used
            Constructor[] constructors = t.getConstructors();
            
            //Validate constructors for valid constructors
            try {
                //Iterating per row
                while (data.next()) {
                    //First creating parameters array with row information
                    Object[] tempParameters = new Object[columnCount];
                    int properParameterCount = 0;
                    
                    for (int i = 1; i <= columnCount; i++) {
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
                            properParameterCount++;
                        } else {
                            if (!rsmd.getColumnTypeName(i).equals("INT")) {
                                tempParameters[properParameterCount] = null;
                                properParameterCount++;
                            }
                        }
                    }
                    
                    Object[] parameters = new Object[properParameterCount];
                    for (int i = 0; i < properParameterCount; i++) {
                        parameters[i] = tempParameters[i];
                    }
                    
                    //Now checking for constructor data type match
                    int constructorIndex = 0;
                    boolean constructorFound = false;
                    
                    while (!constructorFound && constructorIndex < constructors.length) {
                        Constructor constructor = constructors[constructorIndex];
                        if (constructor.getParameterCount() == parameters.length) {
                            constructorFound = constructorMatch(parameters, constructor);
                        }
                        if (!constructorFound) {
                            constructorIndex++;
                        }
                    }
                    if (constructorFound) {
                        //Invoke that constructor
                        try {
                            results.add((I)constructors[constructorIndex].<I>newInstance(parameters));
                        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            Logger.getLogger(Exchange.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Exchange.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        
        return results;
    }
    
    private static boolean constructorMatch(Object[] parameters, Constructor constructor) {
        boolean result = true;
        for (int i = 0; i < constructor.getParameterCount(); i++) {
            if (parameters[i] != null) {
                if (!parameters[i].getClass().getName().equals(constructor.getParameters()[i].getParameterizedType().getTypeName())) {
                    switch (parameters[i].getClass().getName()) {
                        case "java.lang.Integer":
                            if (!constructor.getParameters()[i].getParameterizedType().getTypeName().equals("int")) {
                                result = false;
                            }
                            break;
                        case "java.sql.Timestamp":
                            if (!constructor.getParameters()[i].getParameterizedType().getTypeName().equals("java.util.Date")) {
                                result = false;
                            }
                            break;
                        case "java.sql.Date":
                            if (!constructor.getParameters()[i].getParameterizedType().getTypeName().equals("java.util.Date")) {
                                result = false;
                            }
                            break;
                        case "java.lang.Double":
                            if (!constructor.getParameters()[i].getParameterizedType().getTypeName().equals("double")) {
                                result = false;
                            }
                            break;
                        case "java.math.BigDecimal":
                            if (!constructor.getParameters()[i].getParameterizedType().getTypeName().equals("double")) {
                                result = false;
                            }
                            break;
                        case "java.lang.Boolean":
                            if (!constructor.getParameters()[i].getParameterizedType().getTypeName().equals("boolean")) {
                                result = false;
                            }
                            break;
                        default:
                            result = false;
                            break;
                    }
                }
            }
            else {
                if (constructor.getParameters()[i].getParameterizedType().getTypeName().equals("byte") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("short") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("int") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("long") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("float") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("double") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("boolean") || constructor.getParameters()[i].getParameterizedType().getTypeName().equals("char")) {
                    result = false;
                }
            }
        }
        
        return result;
    }
}