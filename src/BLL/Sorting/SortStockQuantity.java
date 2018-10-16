/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Sorting;

import BLL.Stock;
import java.util.Comparator;

/**
 *
 * @author Stephan
 */
public class SortStockQuantity implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        Stock stock1 = (Stock)o1;
        Stock stock2 = (Stock)o2;
        
        return Integer.compare(stock1.getStockCount(), stock2.getStockCount());
    }
    
}
