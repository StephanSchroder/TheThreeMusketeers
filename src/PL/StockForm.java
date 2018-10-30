/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import BLL.Category;
import BLL.Common;
import BLL.Sorting.SortName;
import BLL.Sorting.SortCategory;

import BLL.Sorting.SortStockQuantity;
import BLL.Sorting.SortSurname;
import BLL.Stock;
import BLL.User;
import BLL.Exceptions.UserDoesNotExistException;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Stephan
 */
public class StockForm extends javax.swing.JFrame {

    /**
     * Creates new form StockForm
     */
    private User currentUser;
    private List<Stock> stocks = new ArrayList<>();
    private int insertClick = 0;
    private int updateClick = 0;

    public StockForm() {
        initComponents();
        stocks = Stock.getStocks();
        initModel();

        currentUser = null;
        lbLoginedInUser.setText("No User Selected");
        cmbChangeListener changeListener = new cmbChangeListener();
        cmbSorting.addItemListener(changeListener);
    }

    public StockForm(User u) {
        initComponents();
        try {
            if (u == null) {
                currentUser = null;
                throw new UserDoesNotExistException(this);
            }
            stocks = Stock.getStocks();
            initModel();

            currentUser = u;
            lbLoginedInUser.setText(lbLoginedInUser.getText()+u.getFullname());

            cmbChangeListener changeListener = new cmbChangeListener();
            cmbSorting.addItemListener(changeListener);
        } catch (UserDoesNotExistException ex) {
            ex.showMessage();
            
        }
    }

    private void initModel() {
        disableAllFields();
        setModel();
        clearAllFields();
    }

    public void setModel() {
        DefaultTableModel model = (DefaultTableModel) tblData.getModel();
        model.setNumRows(0);
        populateCategoryCMB();
        Object rowData[] = new Object[6];
        Object columnData[] = new Object[6];
        columnData[0] = "StockID";
        columnData[1] = "CategoryName";
        columnData[2] = "ItemName";
        columnData[3] = "DateAdded";
        columnData[4] = "StockCount";
        columnData[5] = "Status";
        model.setColumnCount(6);
        model.setColumnIdentifiers(columnData);
        for (int i = 0; i < stocks.size(); i++) {
            rowData[0] = stocks.get(i).getStockID();
            rowData[1] = stocks.get(i).getCategory().getName();
            rowData[2] = stocks.get(i).getItemName();
            rowData[3] = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").format(stocks.get(i).getDateAdded());
            rowData[4] = stocks.get(i).getStockCount();
            rowData[5] = stocks.get(i).getStatus();
            model.addRow(rowData);
        }
    }

    private class cmbChangeListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            JComboBox cb = (JComboBox) e.getSource();

            Object item = e.getItem();

            if (e.getStateChange() == ItemEvent.SELECTED) {
                String txt = cmbSorting.getSelectedItem().toString();
                switch (txt) {
                    case "Category":
                        stocks.sort(new SortCategory());
                        break;
                    case "Stock Quantity":
                        stocks.sort(new SortStockQuantity());
                        break;
                    case "Name":
                        stocks.sort(new SortName());
                        break;
                    case "Surname":
                        stocks.sort(new SortSurname());
                        break;
                }
                setModel();
            }
        }
    }

    public void disableAllFields() {
        txtStockID.setEnabled(false);
        cmbCategory.setEnabled(false);
        txtItemName.setEnabled(false);
        spStockCount.setEnabled(false);
        txtStatus.setEnabled(false);
        dobPicker.setEnabled(false);
    }

    public void prepareInsert() {
        txtStockID.setEnabled(false);
        cmbCategory.setEnabled(true);
        txtItemName.setEnabled(true);
        spStockCount.setEnabled(true);
        txtStatus.setEnabled(true);
        txtStatus.setText("Added");
        dobPicker.setEnabled(false);
        dobPicker.setDate(new Date());
    }

    public void prepareUpdate() {
        txtStockID.setEnabled(true);
        cmbCategory.setEnabled(true);
        txtItemName.setEnabled(true);
        spStockCount.setEnabled(true);
        txtStatus.setEnabled(true);
        dobPicker.setEnabled(false);
    }

    private void clearAllFields() {
        txtStockID.setText("");
        txtStockID.setToolTipText(null);
        txtItemName.setText("");
        txtItemName.setToolTipText(null);
        cmbCategory.setSelectedIndex((cmbCategory.getItemCount() > 0) ? 0 : -1);
        cmbCategory.setToolTipText(null);
        spStockCount.setValue(1);
        spStockCount.setToolTipText(null);
        txtStatus.setText("");
        txtStatus.setToolTipText(null);
        dobPicker.setDate(new Date());
        dobPicker.setToolTipText(null);
    }

    public void populateCategoryCMB() {
        List<Category> listCategories = Category.getCategories();
        List<String> cmbData = new ArrayList<String>();
        for (Category item : listCategories) {
            cmbData.add(item.getName());
        }
        cmbCategory.setModel(new DefaultComboBoxModel(cmbData.toArray()));
    }

    public void resetColor() {
        txtStockID.setBackground(Color.white);
        cmbCategory.setBackground(Color.white);
        txtItemName.setBackground(Color.white);
        spStockCount.setBackground(Color.white);
        txtStatus.setBackground(Color.white);
        dobPicker.setBackground(Color.white);
    }
    
    public void setSorting(boolean value) {
        cmbSorting.setEnabled(value);
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
    
    public void setReport(boolean value) {
        btnReport.setEnabled(value);
    }
    
    public void setUIAccess(boolean value) {
        setSorting(value);
        setSearching(value);
        setCRUDOperations(value);
        setReport(value);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
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
        cmbSorting = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnLogOff = new javax.swing.JButton();
        lbLoginedInUser = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        staffMenu = new javax.swing.JMenu();
        mnOpenStaffForm = new javax.swing.JMenuItem();
        stockMenu = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        mnOpenOrderForm = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));
        jPanel1.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jPanel1formHierarchyChanged(evt);
            }
        });

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

        btnReport.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnReport.setText("Create Report");
        btnReport.setToolTipText("");
        btnReport.setName("btnDeleteRecord"); // NOI18N
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReport(evt);
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
        txtStockID.setEnabled(false);
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(spStockCount, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                    .addComponent(txtItemName)))))
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

        cmbSorting.setEditable(true);
        cmbSorting.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmbSorting.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sorting...", "Category", "Name", "Stock Quantity" }));

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

        lbLoginedInUser.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbLoginedInUser.setText("Logged In As: ");

        btnDelete.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnDelete.setText("Delete Record");
        btnDelete.setToolTipText("");
        btnDelete.setName("btnDeleteRecord"); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel14.setText("Stock Form:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbLoginedInUser)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogOff))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(140, 140, 140)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cmbSorting, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnLogOff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbLoginedInUser)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbSorting, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        staffMenu.setText("Staff");
        staffMenu.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        staffMenu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        staffMenu.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        staffMenu.setIconTextGap(20);

        mnOpenStaffForm.setText("Open Staff Form");
        mnOpenStaffForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenStaffFormActionPerformed(evt);
            }
        });
        staffMenu.add(mnOpenStaffForm);

        jMenuBar1.add(staffMenu);

        stockMenu.setText("Stock");
        stockMenu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        stockMenu.setIconTextGap(10);
        jMenuBar1.add(stockMenu);

        jMenu3.setText("Order Stock");
        jMenu3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jMenu3.setIconTextGap(10);

        mnOpenOrderForm.setText("Open Order Form");
        mnOpenOrderForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenOrderFormActionPerformed(evt);
            }
        });
        jMenu3.add(mnOpenOrderForm);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        int i = tblData.getSelectedRow();

        Stock selectedStock;
        try {
            selectedStock = new Stock(Integer.valueOf(tblData.getValueAt(i, 0).toString()),
                    Category.getCategory(tblData.getValueAt(i, 1).toString()),
                    tblData.getValueAt(i, 2).toString(),
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").parse(tblData.getValueAt(i, 3).toString()),
                    Integer.valueOf(tblData.getValueAt(i, 4).toString()),
                    tblData.getValueAt(i, 5).toString());

            txtStockID.setText(String.valueOf(selectedStock.getStockID()));
            txtItemName.setText(selectedStock.getItemName());
            cmbCategory.setSelectedItem(selectedStock.getCategory().getName());
            spStockCount.setValue(selectedStock.getStockCount());
            txtStatus.setText(selectedStock.getStatus());
            dobPicker.setDate(selectedStock.getDateAdded());
        } catch (ParseException ex) {
            Logger.getLogger(StockForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblDataMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (insertClick == 0) {
            insertClick++;
            setUIAccess(false);
            btnAdd.setEnabled(true);
            clearAllFields();
            prepareInsert();
        } else {
            resetColor();
            String category = null;
            String itemName = null;
            int stockCount = 0;
            String status = null;

            category = (cmbCategory.getItemCount() > 0) ? cmbCategory.getSelectedItem().toString() : "";
            itemName = txtItemName.getText();
            stockCount = (int) spStockCount.getValue();
            status = txtStatus.getText();

            boolean check = true;
            if (Common.checkInput(category) != 1 || category.length() > 50) {
                check = false;
                cmbCategory.setBackground(Color.red);
                cmbCategory.setToolTipText("Only alphabetical characters. Max 50 characters");
            }
            if (Common.checkInput(itemName) != 1 || itemName.length() > 50) {
                check = false;
                txtItemName.setBackground(Color.red);
                txtItemName.setToolTipText("Only alphabetical characters. Max 50 characters");
            }
            if (Common.checkInput(String.valueOf(stockCount)) != 2 || stockCount == 0 || stockCount > 10000) {
                check = false;
                spStockCount.setBackground(Color.red);
                spStockCount.setToolTipText("Only numerical value between 1 and 10000");
            }
            if (Common.checkInput(status) != 1 || status.length() > 20) {
                check = false;
                txtStatus.setBackground(Color.red);
                txtStatus.setToolTipText("Only alphabetical characters. Max 20 characters");
            }

            if (check == true) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    new Stock(0, Category.getCategory(category), itemName, new Date(), stockCount, status).registerStock();
                    stocks = Stock.getStocks();
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
            String stockID = null;
            String category = null;
            String itemName = null;
            int stockCount = 0;
            String status = null;
            
            stockID = txtStockID.getText();
            category = (cmbCategory.getItemCount() > 0) ? cmbCategory.getSelectedItem().toString() : "";
            itemName = txtItemName.getText();
            stockCount = (int) spStockCount.getValue();
            status = txtStatus.getText();

            boolean check = true;
            if (Common.checkInput(stockID) != 2 || Stock.getStock(Integer.parseInt(stockID)) == null) {
                check = false;
                txtStockID.setBackground(Color.red);
                txtStockID.setToolTipText("Invalid Stock ID");
            }
            if (Common.checkInput(category) != 1 || category.length() > 50) {
                check = false;
                cmbCategory.setBackground(Color.red);
                cmbCategory.setToolTipText("Only alphabetical characters. Max 50 characters");
            }
            if (Common.checkInput(itemName) != 1 || itemName.length() > 50) {
                check = false;
                txtItemName.setBackground(Color.red);
                txtItemName.setToolTipText("Only alphabetical characters. Max 50 characters");
            }
            if (Common.checkInput(String.valueOf(stockCount)) != 2 || stockCount == 0 || stockCount > 10000) {
                check = false;
                spStockCount.setBackground(Color.red);
                spStockCount.setToolTipText("Only numerical value between 1 and 10000");
            }
            if (Common.checkInput(status) != 1 || status.length() > 20) {
                check = false;
                txtStatus.setBackground(Color.red);
                txtStatus.setToolTipText("Only alphabetical characters. Max 20 characters");
            }

            if (check == true) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    new Stock(Integer.valueOf(stockID), Category.getCategory(category), itemName, new Date(), stockCount, status).updateStock();
                    stocks = Stock.getStocks();
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

    private void jPanel1formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jPanel1formHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1formHierarchyChanged

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        // TODO add your handling code here:
        if (txtSearch.getText().trim().equals("")) {
            txtSearch.setText("Search data");

        }
        txtSearch.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        // TODO add your handling code here:
        if (txtSearch.getText().trim().equals("Search data")) {
            txtSearch.setText("");

        }
        txtSearch.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtSearchFocusLost

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        stocks = Stock.getStocksSearch(txtSearch.getText());
        setModel();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnLogOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOffActionPerformed
        // TODO add your handling code here:
        Common.logOff(this);
    }//GEN-LAST:event_btnLogOffActionPerformed

    private void generateReport(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateReport
        // TODO add your handling code here:
        boolean retry = false;
        do {
            String fileName = JOptionPane.showInputDialog("Enter your desired file name:");
            if (fileName != null) {
                if (Common.checkInput(fileName) == 1 && fileName.length() > 0) {
                    Stock.generateReport(fileName, stocks);
                    retry = false;
                }
                else{
                    if (JOptionPane.showConfirmDialog(this, "Invalid file name (must only be alphabetical characters), would you like to try again?", "Error", JOptionPane.YES_NO_OPTION)==0) {
                        retry = true;
                    }
                    else {
                        retry = false;
                    }
                }
            }
            else {
                retry = false;
            }
        } while (retry);

    }//GEN-LAST:event_generateReport

    private void mnOpenStaffFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenStaffFormActionPerformed
        // TODO add your handling code here:
        StaffForm staff = new StaffForm(currentUser);
        staff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenStaffFormActionPerformed

    private void mnOpenOrderFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenOrderFormActionPerformed
        // TODO add your handling code here:
        OrderForm orderForm = new OrderForm(currentUser);
        orderForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenOrderFormActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // Delete Record
        if (Common.checkInput(txtStockID.getText()) == 2 && Stock.getStock(Integer.parseInt(txtStockID.getText())) != null) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to Delete this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                new Stock(Integer.valueOf(txtStockID.getText()), null, txtItemName.getText(), (Date) dobPicker.getDate(), (int) spStockCount.getValue(), txtStatus.getText()).deleteStock();
                clearAllFields();
                stocks = Stock.getStocks();
                setModel();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No valid stock item selected");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(StockForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StockForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StockForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StockForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StockForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLogOff;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbSorting;
    private org.jdesktop.swingx.JXDatePicker dobPicker;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbLoginedInUser;
    private javax.swing.JMenuItem mnOpenOrderForm;
    private javax.swing.JMenuItem mnOpenStaffForm;
    private javax.swing.JSpinner spStockCount;
    private javax.swing.JMenu staffMenu;
    private javax.swing.JMenu stockMenu;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtStockID;
    // End of variables declaration//GEN-END:variables
}
