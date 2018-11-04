/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Sorting.User;

import BLL.User;
import java.util.Comparator;

/**
 *
 * @author Stephan
 */
public class UserIDComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        User user1 = (User) o1;
        User user2 = (User) o2;
        if (user1.getUserID() < user2.getUserID()) {
            return -1;
        }
        else if (user1.getUserID() > user2.getUserID()) {
            return 1;
        }
        else {
            return 0;
        }
    }
}
