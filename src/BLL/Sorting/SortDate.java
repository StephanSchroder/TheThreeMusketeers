/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Sorting;

import BLL.Stock;
import BLL.User;
import java.util.Comparator;

/**
 *
 * @author Stephan
 */
public class SortDate implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        if (o1 instanceof  User) {
            User user1 = (User) o1;
            User user2 = (User) o2;
            return user1.getDateAdded().compareTo(user2.getDateAdded());
        }
        if (o1 instanceof Stock) {
            Stock stock1 = (Stock) o1;
            Stock stock2 = (Stock) o2;
            return stock1.getDateAdded().compareTo(stock2.getDateAdded());
        }
        return -2;
    }
    
}
