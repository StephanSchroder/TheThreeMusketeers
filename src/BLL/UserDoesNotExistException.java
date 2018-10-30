/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Student5
 */
public class UserDoesNotExistException extends Exception{
    JFrame parent;
    public UserDoesNotExistException(JFrame j){
        parent = j;
    }
    
    public void showMessage(){
        JOptionPane.showMessageDialog(parent, "User does not exist", "PRG 321", JOptionPane.ERROR_MESSAGE);
    }
}
