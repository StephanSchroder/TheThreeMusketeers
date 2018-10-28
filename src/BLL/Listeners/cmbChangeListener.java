/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Listeners;

import BLL.Sorting.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.Normalizer;
import java.util.Collections;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Stephan
 */
public class cmbChangeListener implements ItemListener{

    public List itemStateChanged(ItemEvent e, String txt, List<Object> data) {
        JComboBox cb = (JComboBox) e.getSource();

    Object item = e.getItem();

    if (e.getStateChange() == ItemEvent.SELECTED) {
       switch (txt)
        {
           case "Category" :{ Collections.sort(data, new SortCategory());};
           case "Date" :{ Collections.sort(data, new SortDate());};
           case "Stock Quantity" :{ Collections.sort(data, new SortStockQuantity());};
            case "Date of Birth" :{ Collections.sort(data, new SortDOB());};
            case "Name" :{ Collections.sort(data, new NameSort());};
            case "Surname" :{ Collections.sort(data, new SortSurname());};
        }
    }
    
    return data;

    
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

   

    
    
    
}
