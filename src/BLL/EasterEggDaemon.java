/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import PL.EasterEgg;

/**
 *
 * @author Student5
 */
public class EasterEggDaemon implements Runnable {

    static int counter = 0;
    EasterEgg e;
    @Override
    public void run() {
        e = new EasterEgg();
        e.setVisible(true);
        
        if(counter==0 || counter==1)
            Common.playMusic(counter);
        counter++;
    }
    
    public void dispose(){
        e.dispose();
    }

    
    
    

}
