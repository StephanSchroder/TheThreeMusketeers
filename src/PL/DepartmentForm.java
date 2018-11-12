/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import BLL.User;
import BLL.Common;
import BLL.Exceptions.UserDoesNotExistException;

/**
 *
 * @author Stephan
 */
public class DepartmentForm extends javax.swing.JFrame {

    /**
     * Creates new form DepartmentForm
     */
    private User currentUser;
    
    public DepartmentForm() {
        initComponents();
        lblLoggedInUser.setText("New user registration");
    }
    public DepartmentForm(User user) {
        initComponents();
        if (user == null) {
            try {
                currentUser = null;
                throw new UserDoesNotExistException(this);
            } catch (UserDoesNotExistException ex) {
                ex.showMessage();
            }
        } else {
            this.currentUser = user;
            lblLoggedInUser.setText("Logged in as: " + user.getFullName() + ((user.getAccountType().equals(User.accountTypeState.ADMIN)) ? " with Admin privileges" : ""));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtDepartmentID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        txtDepartmentName = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        cmbCampus = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        btnCampus = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        lblLoggedInUser = new javax.swing.JLabel();
        btnLogOff = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        miStaffMenu1 = new javax.swing.JMenu();
        mnOpenStaffForm1 = new javax.swing.JMenuItem();
        miStockMenu1 = new javax.swing.JMenu();
        mnOpenStockForm1 = new javax.swing.JMenuItem();
        miOrderMenu1 = new javax.swing.JMenu();
        mnOpenOrderForm1 = new javax.swing.JMenuItem();
        orderStockMenu3 = new javax.swing.JMenu();
        mnOpenCategoryForm = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        mnOpenMyProfileForm = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(0, 115, 56));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 44)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(249, 236, 238));
        jLabel2.setText("Stationery ...");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 162, 7));
        jLabel1.setText("Managing systems PTY ltd.");

        jPanel1.setBackground(new java.awt.Color(19, 54, 57));

        txtDepartmentID.setBackground(new java.awt.Color(19, 54, 57));
        txtDepartmentID.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtDepartmentID.setBorder(null);
        txtDepartmentID.setName("txtUserName"); // NOI18N
        txtDepartmentID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDepartmentIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDepartmentIDFocusLost(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Department ID:");

        jLabel6.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Department Name:");

        txtDepartmentName.setBackground(new java.awt.Color(19, 54, 57));
        txtDepartmentName.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtDepartmentName.setBorder(null);
        txtDepartmentName.setName("txtUserName"); // NOI18N
        txtDepartmentName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDepartmentNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDepartmentNameFocusLost(evt);
            }
        });

        cmbCampus.setBackground(new java.awt.Color(19, 54, 57));
        cmbCampus.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbCampus.setBorder(null);

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Campus: ");

        btnCampus.setBackground(new java.awt.Color(0, 115, 56));
        btnCampus.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnCampus.setForeground(new java.awt.Color(255, 255, 255));
        btnCampus.setText("Campuses");
        btnCampus.setActionCommand("Log Off");
        btnCampus.setName("btnInsertRecord"); // NOI18N
        btnCampus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCampusActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("Department Form:");

        lblLoggedInUser.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lblLoggedInUser.setForeground(new java.awt.Color(255, 255, 255));
        lblLoggedInUser.setText("Logged In User");

        btnLogOff.setBackground(new java.awt.Color(0, 115, 56));
        btnLogOff.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnLogOff.setForeground(new java.awt.Color(255, 255, 255));
        btnLogOff.setText("Log Off");
        btnLogOff.setName("btnInsertRecord"); // NOI18N
        btnLogOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLoggedInUser)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLogOff, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addGap(25, 25, 25)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDepartmentName)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtDepartmentID)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addGap(88, 88, 88)
                            .addComponent(cmbCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(155, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(189, 189, 189))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitle)
                    .addComponent(btnLogOff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLoggedInUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDepartmentID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDepartmentName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(cmbCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(133, 133, 133))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        miStaffMenu1.setText("Staff");
        miStaffMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        miStaffMenu1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        miStaffMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        miStaffMenu1.setIconTextGap(20);

        mnOpenStaffForm1.setText("Open Staff Form");
        mnOpenStaffForm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenStaffForm1ActionPerformed(evt);
            }
        });
        miStaffMenu1.add(mnOpenStaffForm1);

        jMenuBar1.add(miStaffMenu1);

        miStockMenu1.setText("Stock");
        miStockMenu1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        miStockMenu1.setIconTextGap(10);

        mnOpenStockForm1.setText("Open Stock Form");
        mnOpenStockForm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenStockForm1ActionPerformed(evt);
            }
        });
        miStockMenu1.add(mnOpenStockForm1);

        jMenuBar1.add(miStockMenu1);

        miOrderMenu1.setText("Order Stock");
        miOrderMenu1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        miOrderMenu1.setIconTextGap(10);

        mnOpenOrderForm1.setText("Open Order Form");
        mnOpenOrderForm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenOrderForm1ActionPerformed(evt);
            }
        });
        miOrderMenu1.add(mnOpenOrderForm1);

        jMenuBar1.add(miOrderMenu1);

        orderStockMenu3.setText("Category");
        orderStockMenu3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        orderStockMenu3.setIconTextGap(10);

        mnOpenCategoryForm.setText("Open Category Form");
        mnOpenCategoryForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenCategoryFormActionPerformed(evt);
            }
        });
        orderStockMenu3.add(mnOpenCategoryForm);

        jMenuBar1.add(orderStockMenu3);

        jMenu4.setText("Department");
        jMenu4.setFocusable(false);
        jMenu4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jMenuBar1.add(jMenu4);

        jMenu5.setText("My Profile");
        jMenu5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        mnOpenMyProfileForm.setText("Open My Profile Form");
        mnOpenMyProfileForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenMyProfileFormActionPerformed(evt);
            }
        });
        jMenu5.add(mnOpenMyProfileForm);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 821, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDepartmentIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDepartmentIDFocusGained
        // TODO add your handling code here:
        Common.focusGain("ID number", txtDepartmentID);
    }//GEN-LAST:event_txtDepartmentIDFocusGained

    private void txtDepartmentIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDepartmentIDFocusLost
        // TODO add your handling code here:

        Common.focusLost("ID number", txtDepartmentID);
    }//GEN-LAST:event_txtDepartmentIDFocusLost

    private void txtDepartmentNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDepartmentNameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartmentNameFocusGained

    private void txtDepartmentNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDepartmentNameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartmentNameFocusLost

    private void btnCampusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCampusActionPerformed

    }//GEN-LAST:event_btnCampusActionPerformed

    private void mnOpenStaffForm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenStaffForm1ActionPerformed
        // TODO add your handling code here:
        new StaffForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenStaffForm1ActionPerformed

    private void mnOpenStockForm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenStockForm1ActionPerformed
        // TODO add your handling code here:
        StockForm stockForm = new StockForm(currentUser);
        stockForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenStockForm1ActionPerformed

    private void mnOpenOrderForm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenOrderForm1ActionPerformed
        // TODO add your handling code here:
        OrderForm orderForm = new OrderForm(currentUser);
        orderForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenOrderForm1ActionPerformed

    private void mnOpenMyProfileFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenMyProfileFormActionPerformed
        // TODO add your handling code here:
        new MyProfileForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenMyProfileFormActionPerformed

    private void btnLogOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOffActionPerformed
        // TODO add your handling code here:
        if (currentUser != null) {
            Common.logOff(this);
        } else {
            new LoginForm().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_btnLogOffActionPerformed

    private void mnOpenCategoryFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenCategoryFormActionPerformed
        // TODO add your handling code here:
        new CategoryForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenCategoryFormActionPerformed

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
            java.util.logging.Logger.getLogger(DepartmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DepartmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DepartmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DepartmentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DepartmentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCampus;
    private javax.swing.JButton btnLogOff;
    private javax.swing.JComboBox<String> cmbCampus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lblLoggedInUser;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JMenu miOrderMenu1;
    private javax.swing.JMenu miStaffMenu1;
    private javax.swing.JMenu miStockMenu1;
    private javax.swing.JMenuItem mnOpenCategoryForm;
    private javax.swing.JMenuItem mnOpenMyProfileForm;
    private javax.swing.JMenuItem mnOpenOrderForm1;
    private javax.swing.JMenuItem mnOpenStaffForm1;
    private javax.swing.JMenuItem mnOpenStockForm1;
    private javax.swing.JMenu orderStockMenu3;
    private javax.swing.JTextField txtDepartmentID;
    private javax.swing.JTextField txtDepartmentName;
    // End of variables declaration//GEN-END:variables
}
