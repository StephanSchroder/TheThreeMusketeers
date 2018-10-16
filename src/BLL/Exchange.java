/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Nico
 */
public class Exchange<T> {
    private Class<T> typeOfT;
 
    @SuppressWarnings("unchecked")
    public Exchange() {
        this.typeOfT = (Class<T>)
                ((ParameterizedType)getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }
    
    public List<T> DataTableToList(ResultSet rs) {
        List<T> returnData = new ArrayList<T>();
        Class t = typeOfT;
        Constructor[] constructorInfos = t.getConstructors();
        int rowCount = -1;
        int columnCount = -1;
        try {
            columnCount = rs.getMetaData().getColumnCount();
            boolean b = rs.last();
            rowCount = rs.getRow();
            rs.beforeFirst();
        } catch (SQLException sqle) {
            System.out.println("SQL Exception thrown and caught");
        }
        Object[] parameters = new Object[columnCount];
        
        try {
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++)
                {
                    Object column = rs.getObject(i);
                    if (column == null)
                    {
                        parameters[i] = null;
                    }
                    else
                    {
                        switch (column.getClass().getSimpleName())
                        {
                            case "Byte":
                                parameters[i] = (rs.getBigDecimal(i) == BigDecimal.valueOf(0));
                                break;
                            default:
                                parameters[i] = column;
                                break;
                        }
                    }
                }
                int index = 0;
                int paramindex = 0;
                boolean constructorFound = false;
                while((!constructorFound) && (index < constructorInfos.length))
                {
                    Constructor constructor = constructorInfos[index];
                    if (constructor.getParameters().length == parameters.length)
                    {
                        paramindex = 0;
                        boolean parametersValidated = true;
                        while ((parametersValidated) && (paramindex < constructor.getParameters().length))
                        {
                            Parameter parameter = constructor.getParameters()[paramindex];
                            if ((parameters[paramindex] != null) && (parameter.getClass() != parameters[paramindex].getClass()))
                            {
                                if (!parameter.getClass().isPrimitive())
                                {
                                    parametersValidated = false;
                                }
                            }
                            paramindex++;
                        }
                        if (parametersValidated)
                        {
                            constructorFound = true;
                        }
                        else
                        {
                            index++;
                        }
                    }
                }
                if (constructorFound)
                {
                    returnData.add((T)constructorInfos[index].newInstance(parameters));
                }
                else
                {
                    System.out.println("There was error");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception occured");
        } catch (InstantiationException e) {
            System.out.println("Instantiation Exception occured");
        } catch (IllegalAccessException e) {
            System.out.println("Illegal Access Exception occured");
        } catch (InvocationTargetException e) {
            System.out.println("Invocation Target Exception occured");
        }

        return returnData;
    }
}
