/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import BLL.EasterEggDaemon;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;

/**
 *
 * @author Student5
 */
public class EasterEgg extends javax.swing.JFrame {

    /**
     * Creates new form EasterEgg
     */
    public EasterEgg() {
        initComponents();
    }
    int xPos[] = new int [25];
    int yPos[] = new int [25];
    int populated = 0;
    
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        super.setSize(400, 200);
        if (visible) {
            Random r = new Random();
            // Find the screen size
            Toolkit tk = Toolkit.getDefaultToolkit();
            Dimension d = tk.getScreenSize();
            // randomize new location taking into account
            // the screen size, and current size of the window
            int x = r.nextInt((int)d.getWidth());
            int y = r.nextInt((int)d.getHeight());
            if(addPosition(x, y))
                    populated++;
            
        }

    }
    
    public boolean addPosition(int x, int y){
        boolean xFound = false, yFound = false;
        int i = 0;
        int xLogin=445+390,yLogin=350+284;
        //445 350
        //390 284
        do {
            if((xPos[i]<=xLogin-10 || xPos[i]>=xLogin+10) && (yPos[i]<=yLogin-10 || yPos[i]>=yLogin+10)){
                xFound = true;
                yFound = true;
            }
            
            if((xPos[i]<=x-200 || xPos[i]>=x+200) && (yPos[i]<=y-200 || yPos[i]>=y+200)){
                xFound = true;
                yFound = true;
            }
            
            setLocation(x, y);
            i++;
        }while (xFound==false && yFound==false && i<populated);
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbView = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Deal with it");
        setResizable(false);
        setSize(new java.awt.Dimension(400, 200));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        lbView.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tenor.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbView)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbView)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        EasterEggDaemon eg = new EasterEggDaemon();
                Thread t1 = new Thread(eg);
                t1.setDaemon(true);
                t1.start();
    }//GEN-LAST:event_formWindowClosed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        // TODO add your handling code here:
        EasterEggDaemon eg = new EasterEggDaemon();
                Thread t1 = new Thread(eg);
                t1.setDaemon(true);
                t1.start();
    }//GEN-LAST:event_formMouseClicked

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()=='S')
            this.dispose();
    }//GEN-LAST:event_formKeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EasterEgg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EasterEgg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EasterEgg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EasterEgg.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EasterEgg().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbView;
    // End of variables declaration//GEN-END:variables
}
