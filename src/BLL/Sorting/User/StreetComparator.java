/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Sorting.User;

import BLL.Address;
import java.util.Comparator;

/**
 *
 * @author Stephan
 */
public class StreetComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        Address address1 = (Address) o1;
        Address address2 = (Address) o2;
        return address1.getStreet().toLowerCase().compareTo(address2.getStreet().toLowerCase());
    }
}
