/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import PL.EasterEgg;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author Student5
 */
public class EasterEggDaemon implements Runnable {

    static int counter = 0;

    @Override
    public void run() {
        EasterEgg e = new EasterEgg();
        e.setVisible(true);
        counter++;
        if(counter==1)
            playMusic();
    }

    private void playMusic() {
        String name = "Gandalf.wav";
        InputStream is = null;
        
        try {
            is = new FileInputStream(new File(name));
            AudioStream as = new AudioStream(is);
            AudioPlayer.player.start(as);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EasterEggDaemon.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EasterEggDaemon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

}
