/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import BLL.Category;
import java.awt.Color;
import java.sql.Date;
import BLL.Common;
import BLL.Stock;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JOptionPane;
/**
 *
 * @author Stephan
 */
public class frmStock extends javax.swing.JPanel {

    /**
     * Creates new form frmStock
     */
    public frmStock() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtStockID = new javax.swing.JTextField();
        txtItemName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        spStockCount = new javax.swing.JSpinner();
        dobPicker = new org.jdesktop.swingx.JXDatePicker();

        setBackground(new java.awt.Color(102, 153, 255));

        tblData.setBackground(new java.awt.Color(204, 204, 204));
        tblData.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 102, 102), new java.awt.Color(204, 204, 204), null, null)));
        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblData);

        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAdd.setText("Insert Record");
        btnAdd.setName("btnInsertRecord"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnUpdate.setText("Update Record");
        btnUpdate.setName("btnUpdateRecord"); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnDelete.setText("Delete Record");
        btnDelete.setToolTipText("");
        btnDelete.setName("btnDeleteRecord"); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("stock ID");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Category:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Item name:");

        txtStockID.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtStockID.setName("txtUserName"); // NOI18N
        txtStockID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStockIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStockIDFocusLost(evt);
            }
        });

        txtItemName.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtItemName.setName("txtUserName"); // NOI18N
        txtItemName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtItemNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtItemNameFocusLost(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Status:");

        txtStatus.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtStatus.setName("txtUserName"); // NOI18N
        txtStatus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStatusFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStatusFocusLost(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Stock Count:");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Date Added:");

        cmbCategory.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cmbCategory.setName("cmbCategory"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtStockID, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(cmbCategory, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(spStockCount, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                    .addComponent(txtItemName, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(48, 48, 48)
                        .addComponent(dobPicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtStockID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(spStockCount, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(dobPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(182, 182, 182)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        String category =null;
        String itemName=null;
        int stockCount=0;
        String status =null;
        Date dateAdded=null;
        
        category=cmbCategory.getSelectedItem().toString();
        itemName=txtItemName.getText();
        stockCount=(int) spStockCount.getValue();
        status=txtStatus.getText();
        dateAdded=(Date) dobPicker.getDate();
        
        String[] checkValues = new String[]{"1","124","2","1"};
        String[] correctValues= new String[]{String.valueOf(Common.CheckInput(category)),String.valueOf(Common.CheckInput(itemName)),String.valueOf(Common.CheckInput(Integer.toString(stockCount))),String.valueOf(Common.CheckInput(status))};
        
        
        for (int i = 0; i < checkValues.length; i++) {
                     for (int j = 0; j < correctValues[i].length(); j++) {
                if (correctValues[j]!=checkValues[i]&& j==correctValues[i].length()) {
                    checkValues[i]=checkValues[i]+"!";
                }
                
            }
            
           
        }
        boolean check = true;
        for (int i = 0; i < checkValues.length; i++) {
            if (checkValues[i].contains("*!")) {
                check=false;
                switch(i)
                {
                    case 0:{cmbCategory.setBackground(Color.red);}
                    case 1:{txtItemName.setBackground(Color.red);}
                    case 2:{spStockCount.setBackground(Color.red);}
                    case 3:{txtStatus.setBackground(Color.red);}
                }
            }
        }
        if (check==true) {
            Stock stock = new Stock(0, (Category)cmbCategory.getSelectedItem(), itemName, dateAdded, stockCount, status);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "The items in red contains errors");
        }
        
     
        
        
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        //Update record

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // Delete Record

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtStockIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockIDFocusGained
        // TODO add your handling code here:
        if (txtStockID.getText().trim().equals("")) {
            txtStockID.setText("Stock ID");

        }
        txtStockID.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtStockIDFocusGained

    private void txtStockIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockIDFocusLost
        // TODO add your handling code here:
        if (txtStockID.getText().trim().equals("Stock ID")) {
            txtStockID.setText("");

        }
        txtStockID.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtStockIDFocusLost

    private void txtItemNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtItemNameFocusGained
        // TODO add your handling code here:
        if (txtItemName.getText().trim().equals("")) {
            txtItemName.setText("Item Name");

        }
        txtItemName.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtItemNameFocusGained

    private void txtItemNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtItemNameFocusLost
        // TODO add your handling code here:
        if (txtItemName.getText().trim().equals("Item Name")) {
            txtItemName.setText("");

        }
        txtItemName.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtItemNameFocusLost

    private void txtStatusFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStatusFocusGained
        // TODO add your handling code here:
        if (txtStatus.getText().trim().equals("")) {
            txtStatus.setText("Item Status");

        }
        txtStatus.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtStatusFocusGained

    private void txtStatusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStatusFocusLost
        // TODO add your handling code here:
        if (txtStatus.getText().trim().equals("Item Status")) {
            txtStatus.setText("");

        }
        txtStatus.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtStatusFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbCategory;
    private org.jdesktop.swingx.JXDatePicker dobPicker;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spStockCount;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtStockID;
    // End of variables declaration//GEN-END:variables
}
