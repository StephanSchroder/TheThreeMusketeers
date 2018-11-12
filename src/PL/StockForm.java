/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import BLL.Category;
import BLL.Common;
import BLL.Stock;
import BLL.User;
import BLL.Exceptions.UserDoesNotExistException;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import BLL.Interfaces.FormSetUp;

/**
 *
 * @author Stephan
 */
public class StockForm extends javax.swing.JFrame implements FormSetUp {

    /**
     * Creates new form StockForm
     */
    private User currentUser;
    private List<Stock> stocks = new ArrayList<>();
    private int insertClick = 0;
    private int updateClick = 0;

    public StockForm() {
        initComponents();
        stocks = Stock.read();
        initModel();

        currentUser = null;
        lbLoginedInUser.setText("No User Logged In");
        cmbChangeListener changeListener = new cmbChangeListener();
        cmbSorting.addItemListener(changeListener);
        this.setLocationRelativeTo(null);
        if (LoginForm.enableEasterEggs) {
            Common.playMusic(2);
        }
    }

    public StockForm(User u) {
        initComponents();
        try {
            if (u == null) {
                currentUser = null;
                throw new UserDoesNotExistException(this);
            }
            stocks = Stock.read();
            initModel();

            currentUser = u;
            lbLoginedInUser.setText("Logged in as: " + u.getFullName() + ((u.getAccountType().equals(User.accountTypeState.ADMIN)) ? " with Admin privileges" : ""));

            cmbChangeListener changeListener = new cmbChangeListener();
            cmbSorting.addItemListener(changeListener);
        } catch (UserDoesNotExistException ex) {
            ex.showMessage();
            
        }
        this.setLocationRelativeTo(null);
        if (LoginForm.enableEasterEggs) {
            Common.playMusic(2);
        }
    }
    
    @Override
    public void setNavigation(boolean flag){
        mnOpenOrderForm.setEnabled(flag);
        mnOpenStaffForm.setEnabled(flag);
        
        mnOpenOrderForm.setVisible(flag);
        mnOpenStaffForm.setVisible(flag);
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
        Object rowData[] = new Object[8];
        Object columnData[] = new Object[8];
        columnData[0] = "StockID";
        columnData[1] = "CategoryName";
        columnData[2] = "Model";
        columnData[3] = "Price";
        columnData[4] = "ItemName";
        columnData[5] = "DateAdded";
        columnData[6] = "StockCount";
        columnData[7] = "Status";
        
        model.setColumnCount(8);
        model.setColumnIdentifiers(columnData);
        for (int i = 0; i < stocks.size(); i++) {
            rowData[0] = stocks.get(i).getStockID();
            rowData[1] = stocks.get(i).getCategory().getName();
            rowData[2] = stocks.get(i).getModel();
            rowData[3] = stocks.get(i).getPrice();
            rowData[4] = stocks.get(i).getItemName();
            rowData[5] = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").format(stocks.get(i).getDateAdded());
            rowData[6] = stocks.get(i).getStockCount();
            rowData[7] = stocks.get(i).getStatus();
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
                        //stocks.sort(new SortCategory());
                        break;
                    case "Stock Quantity":
                        //stocks.sort(new SortStockQuantity());
                        break;
                    case "Name":
                        //stocks.sort(new SortName());
                        break;
                    case "Surname":
                        //stocks.sort(new SortSurname());
                        break;
                    case "Price":
                        //stocks.sort(new SortSurname());
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
        txtPrice.setText("");
        txtModel.setText("");
    }

    public void populateCategoryCMB() {
        List<Category> listCategories = Category.read();
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
        txtModel.setBackground(Color.white);
        txtPrice.setBackground(Color.white);
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

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        cmbSorting = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnLogOff = new javax.swing.JButton();
        lbLoginedInUser = new javax.swing.JLabel();
        btnDelete = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtStockID = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        spStockCount = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtModel = new javax.swing.JTextField();
        dobPicker = new org.jdesktop.swingx.JXDatePicker();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtStatus = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        btnCategories = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        staffMenu2 = new javax.swing.JMenu();
        mnOpenStaff2 = new javax.swing.JMenuItem();
        stockMenu2 = new javax.swing.JMenu();
        orderStockMenu3 = new javax.swing.JMenu();
        mnOpenOrderForm1 = new javax.swing.JMenuItem();
        orderStockMenu4 = new javax.swing.JMenu();
        mnOpenCategoryForm = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        mnOpenDepartmentForm = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        mnOpenCampusForm = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        mnOpenMyProfileForm = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(0, 115, 56));

        jPanel1.setBackground(new java.awt.Color(19, 54, 57));
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

        btnAdd.setBackground(new java.awt.Color(0, 115, 56));
        btnAdd.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnAdd.setText("Insert Record");
        btnAdd.setName("btnInsertRecord"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(0, 115, 56));
        btnUpdate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnUpdate.setText("Update Record");
        btnUpdate.setName("btnUpdateRecord"); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnReport.setBackground(new java.awt.Color(0, 115, 56));
        btnReport.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnReport.setText("Create Report");
        btnReport.setToolTipText("");
        btnReport.setName("btnDeleteRecord"); // NOI18N
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateReport(evt);
            }
        });

        cmbSorting.setEditable(true);
        cmbSorting.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmbSorting.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sorting...", "Category", "Name", "Stock Quantity" }));

        txtSearch.setBackground(new java.awt.Color(19, 54, 57));
        txtSearch.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtSearch.setForeground(new java.awt.Color(255, 255, 255));
        txtSearch.setBorder(null);
        txtSearch.setName("txtUserName"); // NOI18N
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(0, 115, 56));
        btnSearch.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnSearch.setText("Search");
        btnSearch.setActionCommand("Log Off");
        btnSearch.setName("btnInsertRecord"); // NOI18N
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnLogOff.setBackground(new java.awt.Color(0, 115, 56));
        btnLogOff.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnLogOff.setText("Log Off");
        btnLogOff.setName("btnInsertRecord"); // NOI18N
        btnLogOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOffActionPerformed(evt);
            }
        });

        lbLoginedInUser.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbLoginedInUser.setForeground(new java.awt.Color(255, 255, 255));
        lbLoginedInUser.setText("Logged In As: ");

        btnDelete.setBackground(new java.awt.Color(0, 115, 56));
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
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Stock Form:");

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("stock ID");

        txtStockID.setBackground(new java.awt.Color(19, 54, 57));
        txtStockID.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtStockID.setForeground(new java.awt.Color(255, 255, 255));
        txtStockID.setBorder(null);
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

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Category:");

        cmbCategory.setBackground(new java.awt.Color(19, 54, 57));
        cmbCategory.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cmbCategory.setName("cmbCategory"); // NOI18N

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Item name:");

        txtItemName.setBackground(new java.awt.Color(19, 54, 57));
        txtItemName.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtItemName.setForeground(new java.awt.Color(255, 255, 255));
        txtItemName.setBorder(null);
        txtItemName.setName("txtUserName"); // NOI18N
        txtItemName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtItemNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtItemNameFocusLost(evt);
            }
        });

        txtPrice.setBackground(new java.awt.Color(19, 54, 57));
        txtPrice.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtPrice.setForeground(new java.awt.Color(255, 255, 255));
        txtPrice.setBorder(null);
        txtPrice.setName("txtUserName"); // NOI18N
        txtPrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPriceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPriceFocusLost(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Model:");

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Price:");

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Stock Count:");

        txtModel.setBackground(new java.awt.Color(19, 54, 57));
        txtModel.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtModel.setForeground(new java.awt.Color(255, 255, 255));
        txtModel.setBorder(null);
        txtModel.setName("txtUserName"); // NOI18N
        txtModel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtModelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtModelFocusLost(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Status:");

        jLabel17.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Date Added:");

        txtStatus.setBackground(new java.awt.Color(19, 54, 57));
        txtStatus.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txtStatus.setForeground(new java.awt.Color(255, 255, 255));
        txtStatus.setBorder(null);
        txtStatus.setName("txtUserName"); // NOI18N
        txtStatus.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStatusFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStatusFocusLost(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Search");

        btnCategories.setBackground(new java.awt.Color(0, 115, 56));
        btnCategories.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnCategories.setForeground(new java.awt.Color(255, 255, 255));
        btnCategories.setText("Categories");
        btnCategories.setActionCommand("Log Off");
        btnCategories.setName("btnInsertRecord"); // NOI18N
        btnCategories.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStockID, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCategories, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(136, 136, 136)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator6)
                                    .addComponent(spStockCount, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator7)
                                    .addComponent(txtStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator8)
                                    .addComponent(dobPicker, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))))
                        .addGap(33, 33, 33))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel18)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(738, 738, 738))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(cmbSorting, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbLoginedInUser)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSeparator9, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(40, 40, 40))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogOff, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 85, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbSorting, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(btnLogOff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbLoginedInUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtStockID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(spStockCount, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCategories, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtModel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dobPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReport, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 44)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(249, 236, 238));
        jLabel2.setText("Stationery ...");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 162, 7));
        jLabel1.setText("Managing systems PTY ltd.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(406, 406, 406))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jMenuBar1.setBackground(new java.awt.Color(0, 115, 56));
        jMenuBar1.setForeground(new java.awt.Color(255, 255, 255));
        jMenuBar1.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N

        staffMenu2.setText("Staff");
        staffMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        staffMenu2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        staffMenu2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        staffMenu2.setIconTextGap(20);

        mnOpenStaff2.setFont(mnOpenStaff2.getFont());
        mnOpenStaff2.setText("Open Staff Form");
        mnOpenStaff2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenStaff2ActionPerformed(evt);
            }
        });
        staffMenu2.add(mnOpenStaff2);

        jMenuBar1.add(staffMenu2);

        stockMenu2.setText("Stock");
        stockMenu2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        stockMenu2.setIconTextGap(10);
        jMenuBar1.add(stockMenu2);

        orderStockMenu3.setText("Order Stock");
        orderStockMenu3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        orderStockMenu3.setIconTextGap(10);

        mnOpenOrderForm1.setText("Open Order Form");
        mnOpenOrderForm1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenOrderForm1ActionPerformed(evt);
            }
        });
        orderStockMenu3.add(mnOpenOrderForm1);

        jMenuBar1.add(orderStockMenu3);

        orderStockMenu4.setText("Category");
        orderStockMenu4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        orderStockMenu4.setIconTextGap(10);

        mnOpenCategoryForm.setText("Open Category Form");
        mnOpenCategoryForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenCategoryFormActionPerformed(evt);
            }
        });
        orderStockMenu4.add(mnOpenCategoryForm);

        jMenuBar1.add(orderStockMenu4);

        jMenu4.setText("Department");
        jMenu4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        mnOpenDepartmentForm.setText("Open Department Form");
        mnOpenDepartmentForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenDepartmentFormActionPerformed(evt);
            }
        });
        jMenu4.add(mnOpenDepartmentForm);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Campus");
        jMenu5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        mnOpenCampusForm.setText("Open Campus Form");
        mnOpenCampusForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenCampusFormActionPerformed(evt);
            }
        });
        jMenu5.add(mnOpenCampusForm);

        jMenuBar1.add(jMenu5);

        jMenu6.setText("My Profile");
        jMenu6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        mnOpenMyProfileForm.setText("Open My Profile Form");
        mnOpenMyProfileForm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOpenMyProfileFormActionPerformed(evt);
            }
        });
        jMenu6.add(mnOpenMyProfileForm);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        int i = tblData.getSelectedRow();

        Stock selectedStock;
        try {
            selectedStock = new Stock(Integer.valueOf(tblData.getValueAt(i, 0).toString()),
                    Category.read(tblData.getValueAt(i, 1).toString()),
                    tblData.getValueAt(i, 2).toString(),
                    Double.valueOf(tblData.getValueAt(i, 3).toString()),
                    tblData.getValueAt(i, 4).toString(),
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").parse(tblData.getValueAt(i, 5).toString()),
                    Integer.valueOf(tblData.getValueAt(i, 6).toString()),
                    tblData.getValueAt(i, 7).toString());

            txtStockID.setText(String.valueOf(selectedStock.getStockID()));
            txtItemName.setText(selectedStock.getItemName());
            cmbCategory.setSelectedItem(selectedStock.getCategory().getName());
            spStockCount.setValue(selectedStock.getStockCount());
            txtStatus.setText(selectedStock.getStatus());
            dobPicker.setDate(selectedStock.getDateAdded());
            txtPrice.setText(Double.toString(selectedStock.getPrice()));
            txtModel.setText(selectedStock.getModel());
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
            double price = 0.0;
            String model = null;

            category = (cmbCategory.getItemCount() > 0) ? cmbCategory.getSelectedItem().toString() : "";
            itemName = txtItemName.getText();
            stockCount = (int) spStockCount.getValue();
            status = txtStatus.getText();
            price = Double.valueOf(txtPrice.getText());
            model = txtModel.getText();
            
            

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
            if (Common.checkInput(String.valueOf(price)) != 2 || price == 0) {
                check = false;
                txtPrice.setBackground(Color.red);
                txtPrice.setToolTipText("Only numerical value");
            }
            if (Common.checkInput(model) != 1 || model.length() > 20) {
                check = false;
                txtModel.setBackground(Color.red);
                txtModel.setToolTipText("Only alphabetical characters. Max 20 characters");
            }
            
            if (check == true) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    Stock.create(new Stock(0, Category.read(category),model,price,itemName,new Date(), stockCount, status));
                    stocks = Stock.read();
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
            double price = 0.0;
            String model = null;
            
            stockID = txtStockID.getText();
            category = (cmbCategory.getItemCount() > 0) ? cmbCategory.getSelectedItem().toString() : "";
            itemName = txtItemName.getText();
            stockCount = (int) spStockCount.getValue();
            status = txtStatus.getText();
            price = Double.valueOf(txtPrice.getText());
            model = txtModel.getText();
            

            boolean check = true;
            if (Common.checkInput(stockID) != 2 || Stock.read(Integer.parseInt(stockID)) == null) {
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
            if (Common.checkInput(String.valueOf(price)) != 2 || price == 0) {
                check = false;
                txtPrice.setBackground(Color.red);
                txtPrice.setToolTipText("Only numerical value");
            }
            if (Common.checkInput(model) != 1 || model.length() > 20) {
                check = false;
                txtModel.setBackground(Color.red);
                txtModel.setToolTipText("Only alphabetical characters. Max 20 characters");
            }

            if (check == true) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    Stock.update(new Stock(Integer.valueOf(stockID), Category.read(category),model, price ,itemName,  new Date(), stockCount, status));
                    stocks = Stock.read();
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
        Common.focusGain("Stock ID", txtStockID);
    }//GEN-LAST:event_txtStockIDFocusGained

    private void txtStockIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStockIDFocusLost
        // TODO add your handling code here:
        Common.focusLost("Stock ID", txtStockID);
    }//GEN-LAST:event_txtStockIDFocusLost

    private void txtItemNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtItemNameFocusGained
        // TODO add your handling code here:
        Common.focusGain("Item Name", txtItemName);
    }//GEN-LAST:event_txtItemNameFocusGained

    private void txtItemNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtItemNameFocusLost
        // TODO add your handling code here:
        Common.focusLost("Item Name", txtItemName);
    }//GEN-LAST:event_txtItemNameFocusLost

    private void txtStatusFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStatusFocusGained
        // TODO add your handling code here:
        Common.focusGain("Item Status", txtStatus);
    }//GEN-LAST:event_txtStatusFocusGained

    private void txtStatusFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStatusFocusLost
        // TODO add your handling code here:
        Common.focusLost("Item Status", txtStatus);
    }//GEN-LAST:event_txtStatusFocusLost

    private void jPanel1formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jPanel1formHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1formHierarchyChanged

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained
        // TODO add your handling code here:
        Common.focusGain("Serach data", txtSearch);
    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        // TODO add your handling code here:
        Common.focusLost("Serach data", txtSearch);
    }//GEN-LAST:event_txtSearchFocusLost

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        stocks = Stock.search(txtSearch.getText());
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

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // Delete Record
        if (Common.checkInput(txtStockID.getText()) == 2 && Stock.read(Integer.parseInt(txtStockID.getText())) != null) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to Delete this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                String category = (cmbCategory.getItemCount() > 0) ? cmbCategory.getSelectedItem().toString() : "";
                //enum tableColum {StockID, CategoryName, Model, Price, ItemName, DateAdded, StockCount, Status};

                Stock.delete(new Stock(Integer.valueOf(txtStockID.getText()), Category.read(category),txtModel.getText(),Double.parseDouble(txtPrice.getText()),txtItemName.getText(), 
                        (Date) dobPicker.getDate(),(int) spStockCount.getValue(), txtStatus.getText()));
                clearAllFields();
                stocks = Stock.read();
                setModel();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No valid stock item selected");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtPriceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPriceFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceFocusGained

    private void txtPriceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPriceFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPriceFocusLost

    private void txtModelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtModelFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModelFocusGained

    private void txtModelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtModelFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtModelFocusLost

    private void btnCategoriesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriesActionPerformed
        new CategoryForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCategoriesActionPerformed

    private void mnOpenStaff2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenStaff2ActionPerformed

        StaffForm staff = new StaffForm(currentUser);
        staff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenStaff2ActionPerformed

    private void mnOpenOrderForm1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenOrderForm1ActionPerformed

        OrderForm orderForm = new OrderForm(currentUser);
        orderForm.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenOrderForm1ActionPerformed

    private void mnOpenDepartmentFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenDepartmentFormActionPerformed
        new DepartmentForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenDepartmentFormActionPerformed

    private void mnOpenCampusFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenCampusFormActionPerformed

        new CampusForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenCampusFormActionPerformed

    private void mnOpenMyProfileFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenMyProfileFormActionPerformed

        new MyProfileForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenMyProfileFormActionPerformed

    private void mnOpenCategoryFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenCategoryFormActionPerformed

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
    private javax.swing.JButton btnCategories;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLogOff;
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbSorting;
    private org.jdesktop.swingx.JXDatePicker dobPicker;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lbLoginedInUser;
    private javax.swing.JMenuItem mnOpenCampusForm;
    private javax.swing.JMenuItem mnOpenCategoryForm;
    private javax.swing.JMenuItem mnOpenDepartmentForm;
    private javax.swing.JMenuItem mnOpenMyProfileForm;
    private javax.swing.JMenuItem mnOpenOrderForm1;
    private javax.swing.JMenuItem mnOpenStaff2;
    private javax.swing.JMenu orderStockMenu3;
    private javax.swing.JMenu orderStockMenu4;
    private javax.swing.JSpinner spStockCount;
    private javax.swing.JMenu staffMenu2;
    private javax.swing.JMenu stockMenu2;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtStockID;
    // End of variables declaration//GEN-END:variables
}
