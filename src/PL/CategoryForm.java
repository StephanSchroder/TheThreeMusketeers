/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import BLL.Category;
import BLL.Common;
import BLL.Exceptions.UserDoesNotExistException;
import BLL.User;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Stephan
 */
public class CategoryForm extends javax.swing.JFrame {

        private User currentUser;
    private List<Category> categories = new ArrayList<>();
    private int insertClick = 0;
    private int updateClick = 0;

    
    /**
     * Creates new form CategoryForm
     */
    public CategoryForm() {
        initComponents();
        categories =Category.read();
        initModel();

        currentUser = null;
        lbLoginedInUser.setText("No User Logged In");
        this.setLocationRelativeTo(null);
        Common.playMusic(2);
    }
    
     public CategoryForm(User u) {
        initComponents();
        try {
            if (u == null) {
                currentUser = null;
                throw new UserDoesNotExistException(this);
            }
            categories = Category.read();
            initModel();

            currentUser = u;
            lbLoginedInUser.setText("Logged in as: " + u.getFullName() + ((u.getAccountType().equals(User.accountTypeState.ADMIN)) ? " with Admin privileges" : ""));

            
        } catch (UserDoesNotExistException ex) {
            ex.showMessage();
            
        }
        this.setLocationRelativeTo(null);
        Common.playMusic(2);
    }

     
        private void initModel() {
        disableAllFields();
        setModel();
        clearAllFields();
        
    }

    public void setModel() {
        DefaultTableModel model = (DefaultTableModel) tblData.getModel();
        model.setNumRows(0);
        Object rowData[] = new Object[6];
        Object columnData[] = new Object[6];
        columnData[0] = "CategoryID";
        columnData[1] = "CategoryName";
        columnData[2] = "Description";

        model.setColumnCount(6);
        model.setColumnIdentifiers(columnData);
        for (int i = 0; i < categories.size(); i++) {
            rowData[0] = categories.get(i).getCategoryID();
            rowData[1] = categories.get(i).getName();
            rowData[2] = categories.get(i).getDescription();
            model.addRow(rowData);
        }
    }
    
        public void disableAllFields() {
        txtCategoryID.setEnabled(false);
        txtDescription.setEnabled(false);
        txtCategoryName.setEnabled(false);
    }
    
    
        public void prepareInsert() {
        txtCategoryID.setEnabled(false);
        txtDescription.setEnabled(true);
        txtCategoryName.setEnabled(true);

    }

    public void prepareUpdate() {
        txtCategoryID.setEnabled(true);
        txtDescription.setEnabled(true);
        txtCategoryName.setEnabled(true);
    }

    private void clearAllFields() {
        txtCategoryID.setText("");
        txtCategoryID.setToolTipText(null);
        txtCategoryName.setText("");
        txtCategoryName.setToolTipText(null);
        txtDescription.setText("");
        txtDescription.setToolTipText(null);
        
    }

      public void resetColor() {
        txtCategoryID.setBackground(Color.white);
        txtDescription.setBackground(Color.white);
        txtCategoryName.setBackground(Color.white);

    }
    
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        staffMenu = new javax.swing.JMenu();
        mnOpenStaff = new javax.swing.JMenuItem();
        stockMenu = new javax.swing.JMenu();
        mnOpenStockForm = new javax.swing.JMenuItem();
        orderStockMenu = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        lbLoginedInUser = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnLogOff = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCategoryID = new javax.swing.JTextField();
        txtCategoryName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jMenuBar2 = new javax.swing.JMenuBar();
        staffMenu1 = new javax.swing.JMenu();
        mnOpenStaff1 = new javax.swing.JMenuItem();
        stockMenu1 = new javax.swing.JMenu();
        mnOpenStockForm1 = new javax.swing.JMenuItem();
        orderStockMenu1 = new javax.swing.JMenu();
        orderStockMenu2 = new javax.swing.JMenu();

        staffMenu.setText("Staff");
        staffMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        staffMenu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        staffMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        staffMenu.setIconTextGap(20);

        mnOpenStaff.setText("Open Staff Form");
        mnOpenStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenStaffActionPerformed(evt);
            }
        });
        staffMenu.add(mnOpenStaff);

        jMenuBar1.add(staffMenu);

        stockMenu.setText("Stock");
        stockMenu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        stockMenu.setIconTextGap(10);

        mnOpenStockForm.setText("Open Stock Form");
        mnOpenStockForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenStockFormActionPerformed(evt);
            }
        });
        stockMenu.add(mnOpenStockForm);

        jMenuBar1.add(stockMenu);

        orderStockMenu.setText("Order Stock");
        orderStockMenu.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        orderStockMenu.setIconTextGap(10);
        jMenuBar1.add(orderStockMenu);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 153, 255));

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        lbLoginedInUser.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbLoginedInUser.setText("Logged In As: ");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel14.setText("Category Form:");

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
        tblData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblData);

        txtSearch.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtSearch.setName("txtUserName"); // NOI18N
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });

        btnSearch.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.setActionCommand("Log Off");
        btnSearch.setName("btnInsertRecord"); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnLogOff.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLogOff.setText("Log Off");
        btnLogOff.setName("btnInsertRecord"); // NOI18N
        btnLogOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOffActionPerformed(evt);
            }
        });

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
        jLabel11.setText("Category ID:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("Category name:");

        txtCategoryID.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtCategoryID.setEnabled(false);
        txtCategoryID.setName("txtUserName"); // NOI18N
        txtCategoryID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCategoryIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCategoryIDFocusLost(evt);
            }
        });

        txtCategoryName.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtCategoryName.setName("txtUserName"); // NOI18N
        txtCategoryName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCategoryNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCategoryNameFocusLost(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Description:");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel11))
                .addGap(62, 62, 62)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCategoryName)
                            .addComponent(jScrollPane2))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCategoryID, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 25, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCategoryID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbLoginedInUser)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogOff)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(117, 117, 117)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(btnLogOff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbLoginedInUser)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        staffMenu1.setText("Staff");
        staffMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        staffMenu1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        staffMenu1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        staffMenu1.setIconTextGap(20);

        mnOpenStaff1.setText("Open Staff Form");
        mnOpenStaff1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenStaff1ActionPerformed(evt);
            }
        });
        staffMenu1.add(mnOpenStaff1);

        jMenuBar2.add(staffMenu1);

        stockMenu1.setText("Stock");
        stockMenu1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        stockMenu1.setIconTextGap(10);

        mnOpenStockForm1.setText("Open Stock Form");
        mnOpenStockForm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenStockForm1ActionPerformed(evt);
            }
        });
        stockMenu1.add(mnOpenStockForm1);

        jMenuBar2.add(stockMenu1);

        orderStockMenu1.setText("Order Stock");
        orderStockMenu1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        orderStockMenu1.setIconTextGap(10);
        jMenuBar2.add(orderStockMenu1);

        orderStockMenu2.setText("Category");
        orderStockMenu2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        orderStockMenu2.setIconTextGap(10);
        jMenuBar2.add(orderStockMenu2);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnOpenStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenStaffActionPerformed
        // TODO add your handling code here:
        StaffForm staff = new StaffForm(currentUser);
        staff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenStaffActionPerformed

    private void mnOpenStockFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenStockFormActionPerformed
        // TODO add your handling code here:
        new StockForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenStockFormActionPerformed

    private void mnOpenStaff1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenStaff1ActionPerformed
        // TODO add your handling code here:
        StaffForm staff = new StaffForm(currentUser);
        staff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenStaff1ActionPerformed

    private void mnOpenStockForm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenStockForm1ActionPerformed
        // TODO add your handling code here:
        new StockForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenStockForm1ActionPerformed

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        int i = tblData.getSelectedRow();

        Category selectedCategory;
        selectedCategory = new Category(Integer.valueOf(tblData.getValueAt(i, 0).toString()),
                tblData.getValueAt(i, 1).toString(),
                tblData.getValueAt(i, 2).toString());
        txtCategoryID.setText(String.valueOf(selectedCategory.getCategoryID()));
        txtCategoryName.setText(selectedCategory.getName());
        txtDescription.setText(selectedCategory.getDescription());
    }//GEN-LAST:event_tblDataMouseClicked

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        // TODO add your handling code here:
        Common.focusGain("Serach data", txtSearch);
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        // TODO add your handling code here:
        Common.focusLost("Serach data", txtSearch);
    }//GEN-LAST:event_txtSearchFocusLost

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        categories = Category.search(txtSearch.getText());
        setModel();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnLogOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOffActionPerformed
        // TODO add your handling code here:
        Common.logOff(this);
    }//GEN-LAST:event_btnLogOffActionPerformed

      public void setUIAccess(boolean value) {
        setSearching(value);
        setCRUDOperations(value);
    }
      
          public void setSearching(boolean value) {
        txtSearch.setEnabled(value);
        btnSearch.setEnabled(value);
    }
    
    public void setCRUDOperations(boolean value) {
        btnAdd.setEnabled(value);
        btnUpdate.setEnabled(value);
        btnDelete.setEnabled(value);
    }
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (insertClick == 0) {
            insertClick++;
            setUIAccess(false);
            btnAdd.setEnabled(true);
            clearAllFields();
            prepareInsert();
        } else {
            resetColor();
            
            String itemName = null;
            String description = null;
            

           
            itemName = txtCategoryName.getText();
             description = txtDescription.getText();

            boolean check = true;

            if (Common.checkInput(itemName) != 1 || itemName.length() > 50) {
                check = false;
                txtCategoryName.setBackground(Color.red);
                txtCategoryName.setToolTipText("Only alphabetical characters. Max 50 characters");
            }

            if (check == true) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    Category.create(new Category(0, itemName,description));
                    categories = Category.read();
                    setModel();
                }
                clearAllFields();
                disableAllFields();
                resetColor();
                insertClick = 0;
                setUIAccess(true);
            } else {
                int option = JOptionPane.showConfirmDialog(this, "There were some errors, would you like to fix them?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 1) {
                    clearAllFields();
                    disableAllFields();
                    resetColor();
                    insertClick = 0;
                    setUIAccess(true);
                }
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        //Update record
        if (updateClick == 0) {
            updateClick++;
            setUIAccess(false);
            btnUpdate.setEnabled(true);
            prepareUpdate();
        } else {
            resetColor();
            String catID = null;
            String description = null;
            String itemName = null;
            int stockCount = 0;
            String status = null;

            catID = txtCategoryID.getText();
            itemName = txtCategoryName.getText();
            itemName = txtDescription.getText();

            boolean check = true;
            if (Common.checkInput(catID) != 2 || Category.read(Integer.parseInt(catID)) == null) {
                check = false;
                txtCategoryID.setBackground(Color.red);
                txtCategoryID.setToolTipText("Invalid Stock ID");
            }
          
            if (Common.checkInput(itemName) != 1 || itemName.length() > 50) {
                check = false;
                txtCategoryName.setBackground(Color.red);
                txtCategoryName.setToolTipText("Only alphabetical characters. Max 50 characters");
            }
         
            if (check == true) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    Category.update(new Category(Integer.valueOf(catID), itemName,description));
                    categories = Category.read();
                    setModel();
                }
                clearAllFields();
                disableAllFields();
                resetColor();
                updateClick = 0;
                setUIAccess(true);
            } else {
                int option = JOptionPane.showConfirmDialog(this, "There were some errors, would you like to fix them?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 1) {
                    clearAllFields();
                    disableAllFields();
                    resetColor();
                    updateClick = 0;
                    setUIAccess(true);
                }
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // Delete Record
        if (Common.checkInput(txtCategoryID.getText()) == 2 && Category.read(Integer.parseInt(txtCategoryID.getText())) != null) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to Delete this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                Category.delete(new Category(Integer.valueOf(txtCategoryID.getText()),txtCategoryName.getText(),txtDescription.getText() ));
                clearAllFields();
                categories = Category.read();
                setModel();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No valid stock item selected");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtCategoryIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCategoryIDFocusGained
        // TODO add your handling code here:
        Common.focusGain("Stock ID", txtCategoryID);
    }//GEN-LAST:event_txtCategoryIDFocusGained

    private void txtCategoryIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCategoryIDFocusLost
        // TODO add your handling code here:
        Common.focusLost("Stock ID", txtCategoryID);
    }//GEN-LAST:event_txtCategoryIDFocusLost

    private void txtCategoryNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCategoryNameFocusGained
        // TODO add your handling code here:
        Common.focusGain("Item Name", txtCategoryName);
    }//GEN-LAST:event_txtCategoryNameFocusGained

    private void txtCategoryNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCategoryNameFocusLost
        // TODO add your handling code here:
        Common.focusLost("Item Name", txtCategoryName);
    }//GEN-LAST:event_txtCategoryNameFocusLost

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
            java.util.logging.Logger.getLogger(CategoryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CategoryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CategoryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CategoryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CategoryForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLogOff;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbLoginedInUser;
    private javax.swing.JMenuItem mnOpenStaff;
    private javax.swing.JMenuItem mnOpenStaff1;
    private javax.swing.JMenuItem mnOpenStockForm;
    private javax.swing.JMenuItem mnOpenStockForm1;
    private javax.swing.JMenu orderStockMenu;
    private javax.swing.JMenu orderStockMenu1;
    private javax.swing.JMenu orderStockMenu2;
    private javax.swing.JMenu staffMenu;
    private javax.swing.JMenu staffMenu1;
    private javax.swing.JMenu stockMenu;
    private javax.swing.JMenu stockMenu1;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtCategoryID;
    private javax.swing.JTextField txtCategoryName;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
