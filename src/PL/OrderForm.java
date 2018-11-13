/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PL;

import BLL.Builders.OrderBuilder;
import BLL.Category;
import BLL.Common;
import BLL.User;
import BLL.Exceptions.UserDoesNotExistException;
import BLL.Interfaces.FormSetUp;
import BLL.Order;
import BLL.Reports;
import BLL.Stock;
import BLL.StockOrder;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Stephan
 */
public class OrderForm extends javax.swing.JFrame implements FormSetUp {

    private User currentUser;
    private List<Order> orderList = new ArrayList<>();
    private List<Stock> stocks = new ArrayList<>();
    private int insertClick = 0;
    private int updateClick = 0;

    /**
     * Creates new form OrderForm
     */
    public OrderForm() {
        initComponents();        
        
        currentUser = null;
        lbLoginedInUser.setText(lbLoginedInUser.getText() + "No User Selected");
        this.setLocationRelativeTo(null);
        if (LoginForm.enableEasterEggs) {
            Common.playMusic(2);
        }
        initModel();
    }

    public OrderForm(User u) {
        initComponents();
        initModel();
        
        try {
            if (u == null) {
                currentUser = null;
                throw new UserDoesNotExistException(this);
            }

            currentUser = u;
            lbLoginedInUser.setText("Logged in as: " + u.getFullName() + ((u.getAccountType().equals(User.accountTypeState.ADMIN)) ? " with Admin privileges" : ""));
        } catch (UserDoesNotExistException ex) {
            ex.showMessage();

        }
        this.setLocationRelativeTo(null);
        if (LoginForm.enableEasterEggs) {
            Common.playMusic(2);
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
    @Override
    public void setNavigation(boolean flag) {
        mnOpenStaff.setEnabled(flag);
        mnOpenStockForm.setEnabled(flag);

        mnOpenStaff.setVisible(flag);
        mnOpenStockForm.setVisible(flag);

    }

    private void initModel() {
        disableAllFields();
        setModel();
        clearAllFields();

    }

    public void prepareInsert() {
        txtItemName.setEnabled(true);
        txtOrderID.setEnabled(true);
        dpOrderDate.setEnabled(true);
        dpReceiveDate.setEnabled(true);
        btnAddItem.setEnabled(true);
        btnRemoveItem.setEnabled(true);

    }

    public void prepareUpdate() {
        txtItemName.setEnabled(true);
        txtOrderID.setEnabled(true);
        dpOrderDate.setEnabled(true);
        dpReceiveDate.setEnabled(true);;
    }

    public void disableAllFields() {
        txtItemName.setEnabled(false);
        txtOrderID.setEnabled(false);
        dpOrderDate.setEnabled(false);
        dpReceiveDate.setEnabled(false);
        btnAddItem.setEnabled(false);
        btnRemoveItem.setEnabled(false);

    }

    public void resetColor() {
        txtItemName.setBackground(Color.white);
        txtOrderID.setBackground(Color.white);
        dpOrderDate.setBackground(Color.white);
        dpReceiveDate.setBackground(Color.white);
    }

    public void setModel() {
        if (currentUser != null) {

            cmbSorting.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Order ID", "Order Date", "Received Date", "Status", "By Employee", "Approved By"}));
            //orderID, Date orderDate, Date receiveDate, String status, User placedByEmployee, User approvedByEmployee
            DefaultTableModel model = (DefaultTableModel) tblOrders.getModel();
            model.setNumRows(0);
            Object rowData[] = new Object[8];
            Object columnData[] = new Object[8];
            columnData[0] = "Order ID";
            columnData[1] = "Order Date";
            columnData[2] = "Received Date";
            columnData[3] = "Status";
            columnData[4] = "By Employee";
            columnData[5] = "Approved By";
            orderList = Order.read();
            model.setColumnCount(23);
            model.setColumnIdentifiers(columnData);
            for (int i = 0; i < orderList.size(); i++) {
                rowData[0] = orderList.get(i).getOrderID();
                rowData[1] = orderList.get(i).getOrderDateString();
                rowData[2] = orderList.get(i).getReceiveDateString();
                rowData[3] = orderList.get(i).getStatus();
                rowData[4] = orderList.get(i).getPlacedByEmployee();
                rowData[5] = orderList.get(i).getApprovedByEmployee();

                model.addRow(rowData);
            }
            DefaultTableModel model1 = (DefaultTableModel) tblSellItems.getModel();
            model.setNumRows(0);
            Object rowData1[] = new Object[8];
            Object columnData1[] = new Object[8];
            columnData1[0] = "StockID";
            columnData1[1] = "CategoryName";
            columnData1[2] = "Model";
            columnData1[3] = "Price";
            columnData1[4] = "ItemName";
            columnData1[5] = "DateAdded";
            columnData1[6] = "StockCount";
            columnData1[7] = "Status";

            model1.setColumnCount(8);
            model1.setColumnIdentifiers(columnData1);
            for (int i = 0; i < stocks.size(); i++) {
                rowData1[0] = stocks.get(i).getStockID();
                rowData1[1] = stocks.get(i).getCategory().getName();
                rowData1[2] = stocks.get(i).getModel();
                rowData1[3] = stocks.get(i).getPrice();
                rowData1[4] = stocks.get(i).getItemName();
                rowData1[5] = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").format(stocks.get(i).getDateAdded());
                rowData1[6] = stocks.get(i).getStockCount();
                rowData1[7] = stocks.get(i).getStatus();
                model.addRow(rowData);
            }

        }
    }

    private void clearAllFields() {
        txtItemName.setText("");
        txtOrderID.setText("");
        txtSearchOrders.setText("");
        txtSearchStock.setText("");
        btnAddItem.setEnabled(false);
        btnRemoveItem.setEnabled(false);

        txtItemName.setToolTipText(null);
        txtOrderID.setToolTipText(null);
        txtSearchOrders.setToolTipText(null);
        txtSearchStock.setToolTipText(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator6 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSellItems = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        cmbSorting = new javax.swing.JComboBox<>();
        txtSearchOrders = new javax.swing.JTextField();
        btnSearchOrder = new javax.swing.JButton();
        btnLogOff = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblOrders = new javax.swing.JTable();
        txtSearchStock = new javax.swing.JTextField();
        btnSearchStock = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblNewOrderItems = new javax.swing.JTable();
        btnApprove = new javax.swing.JButton();
        btnDecline = new javax.swing.JButton();
        lbLoginedInUser = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtOrderID = new javax.swing.JTextField();
        dpOrderDate = new org.jdesktop.swingx.JXDatePicker();
        dpReceiveDate = new org.jdesktop.swingx.JXDatePicker();
        cmbOrderStatus = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        spOrderQuantity = new javax.swing.JSpinner();
        btnAdjustOrder = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        btnAddItem = new javax.swing.JButton();
        btnRemoveItem = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        staffMenu = new javax.swing.JMenu();
        mnOpenStaff = new javax.swing.JMenuItem();
        stockMenu = new javax.swing.JMenu();
        mnOpenStockForm = new javax.swing.JMenuItem();
        orderStockMenu = new javax.swing.JMenu();
        orderStockMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(0, 115, 56));

        jPanel1.setBackground(new java.awt.Color(19, 54, 57));
        jPanel1.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jPanel1formHierarchyChanged(evt);
            }
        });

        tblSellItems.setBackground(new java.awt.Color(204, 204, 204));
        tblSellItems.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 102, 102), new java.awt.Color(204, 204, 204), null, null)));
        tblSellItems.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSellItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSellItemsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSellItems);

        btnAdd.setBackground(new java.awt.Color(0, 115, 56));
        btnAdd.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setText("Insert Record");
        btnAdd.setName("btnInsertRecord"); // NOI18N
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setBackground(new java.awt.Color(0, 115, 56));
        btnUpdate.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdate.setText("Update Record");
        btnUpdate.setName("btnUpdateRecord"); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(0, 115, 56));
        btnDelete.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setText("Delete Record");
        btnDelete.setToolTipText("");
        btnDelete.setName("btnDeleteRecord"); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        cmbSorting.setEditable(true);
        cmbSorting.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cmbSorting.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sorting...", "Category", "Date", "Name", "Stock Quantity" }));

        txtSearchOrders.setEditable(false);
        txtSearchOrders.setBackground(new java.awt.Color(19, 54, 57));
        txtSearchOrders.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtSearchOrders.setForeground(new java.awt.Color(255, 255, 255));
        txtSearchOrders.setBorder(null);
        txtSearchOrders.setName("txtUserName"); // NOI18N
        txtSearchOrders.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchOrdersFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchOrdersFocusLost(evt);
            }
        });

        btnSearchOrder.setBackground(new java.awt.Color(0, 115, 56));
        btnSearchOrder.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnSearchOrder.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchOrder.setText("Search");
        btnSearchOrder.setActionCommand("Log Off");
        btnSearchOrder.setName("btnInsertRecord"); // NOI18N
        btnSearchOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchOrderActionPerformed(evt);
            }
        });

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

        tblOrders.setBackground(new java.awt.Color(204, 204, 204));
        tblOrders.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 102, 102), new java.awt.Color(204, 204, 204), null, null)));
        tblOrders.setModel(new javax.swing.table.DefaultTableModel(
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
        tblOrders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrdersMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblOrders);

        txtSearchStock.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtSearchStock.setName("txtUserName"); // NOI18N
        txtSearchStock.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchStockFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchStockFocusLost(evt);
            }
        });

        btnSearchStock.setBackground(new java.awt.Color(0, 115, 56));
        btnSearchStock.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnSearchStock.setForeground(new java.awt.Color(255, 255, 255));
        btnSearchStock.setText("Search");
        btnSearchStock.setActionCommand("Log Off");
        btnSearchStock.setName("btnInsertRecord"); // NOI18N
        btnSearchStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchStockActionPerformed(evt);
            }
        });

        tblNewOrderItems.setBackground(new java.awt.Color(204, 204, 204));
        tblNewOrderItems.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.black, null), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED, new java.awt.Color(102, 102, 102), new java.awt.Color(204, 204, 204), null, null)));
        tblNewOrderItems.setModel(new javax.swing.table.DefaultTableModel(
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
        tblNewOrderItems.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNewOrderItemsMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tblNewOrderItems);

        btnApprove.setBackground(new java.awt.Color(0, 115, 56));
        btnApprove.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnApprove.setForeground(new java.awt.Color(255, 255, 255));
        btnApprove.setText("Approve");
        btnApprove.setName("btnInsertRecord"); // NOI18N
        btnApprove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApproveActionPerformed(evt);
            }
        });

        btnDecline.setBackground(new java.awt.Color(0, 115, 56));
        btnDecline.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnDecline.setForeground(new java.awt.Color(255, 255, 255));
        btnDecline.setText("Decline");
        btnDecline.setName("btnUpdateRecord"); // NOI18N
        btnDecline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeclineActionPerformed(evt);
            }
        });

        lbLoginedInUser.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        lbLoginedInUser.setForeground(new java.awt.Color(255, 255, 255));
        lbLoginedInUser.setText("Logged In As: ");

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Order Form:");

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Search:");

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Search:");

        txtOrderID.setBackground(new java.awt.Color(19, 54, 57));
        txtOrderID.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtOrderID.setForeground(new java.awt.Color(255, 255, 255));
        txtOrderID.setBorder(null);
        txtOrderID.setEnabled(false);
        txtOrderID.setName("txtUserName"); // NOI18N
        txtOrderID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtOrderIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtOrderIDFocusLost(evt);
            }
        });

        dpOrderDate.setBackground(new java.awt.Color(19, 54, 57));
        dpOrderDate.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        dpReceiveDate.setBackground(new java.awt.Color(19, 54, 57));
        dpReceiveDate.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N

        cmbOrderStatus.setBackground(new java.awt.Color(19, 54, 57));
        cmbOrderStatus.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cmbOrderStatus.setBorder(null);
        cmbOrderStatus.setName("cmbCategory"); // NOI18N

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Order ID:");

        jLabel18.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Order Date:");

        jLabel19.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Receive Date:");

        jLabel16.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Order Status:");

        txtItemName.setBackground(new java.awt.Color(19, 54, 57));
        txtItemName.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        txtItemName.setForeground(new java.awt.Color(255, 255, 255));
        txtItemName.setBorder(null);
        txtItemName.setEnabled(false);
        txtItemName.setName("txtUserName"); // NOI18N
        txtItemName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtItemNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtItemNameFocusLost(evt);
            }
        });

        btnAdjustOrder.setBackground(new java.awt.Color(0, 115, 56));
        btnAdjustOrder.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        btnAdjustOrder.setForeground(new java.awt.Color(255, 255, 255));
        btnAdjustOrder.setText("Adjust");
        btnAdjustOrder.setName("btnInsertRecord"); // NOI18N
        btnAdjustOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdjustOrderActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Item Name");
        jLabel12.setToolTipText("");

        jLabel20.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Quantity");
        jLabel20.setToolTipText("");

        btnAddItem.setLabel("Add Item");
        btnAddItem.setName(""); // NOI18N
        btnAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddItemActionPerformed(evt);
            }
        });

        btnRemoveItem.setLabel("Remove Item");
        btnRemoveItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbLoginedInUser)
                                    .addComponent(cmbSorting, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSearchOrders, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnSearchOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLogOff)
                                .addGap(17, 17, 17))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel11))
                                .addGap(44, 44, 44)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(dpOrderDate, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                    .addComponent(txtOrderID)
                                    .addComponent(cmbOrderStatus, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator1)
                                    .addComponent(jSeparator2)
                                    .addComponent(jSeparator3)
                                    .addComponent(dpReceiveDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator7))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel20))
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtItemName)
                                    .addComponent(jSeparator8)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(spOrderQuantity, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnAdjustOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jSeparator9))
                                        .addGap(4, 4, 4)))))
                        .addGap(106, 106, 106)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnDecline, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSearchStock, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearchStock, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnRemoveItem, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(79, 79, 79)))
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(btnLogOff, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbLoginedInUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbSorting, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSearchOrders, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(btnSearchOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtSearchStock, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(btnSearchStock, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRemoveItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addComponent(btnAddItem, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtOrderID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(dpOrderDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(dpReceiveDate, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16)
                                    .addComponent(cmbOrderStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(spOrderQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdjustOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnApprove, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDecline, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
        );

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(249, 236, 238));
        jLabel2.setText("Stationery ...");

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 162, 7));
        jLabel1.setText("Managing systems PTY ltd.");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(409, 409, 409))
        );

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

        orderStockMenu2.setText("Category");
        orderStockMenu2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        orderStockMenu2.setIconTextGap(10);
        jMenuBar1.add(orderStockMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void tblSellItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSellItemsMouseClicked
        /*   int i = tblData.getSelectedRow();

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
            Logger.getLogger(frmStock.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_tblSellItemsMouseClicked

    private void txtSearchOrdersFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchOrdersFocusGained
        // TODO add your handling code here:
        Common.focusGain("Serach data", txtSearchOrders);
    }//GEN-LAST:event_txtSearchOrdersFocusGained

    private void txtSearchOrdersFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchOrdersFocusLost
        // TODO add your handling code here:
        Common.focusLost("Serach data", txtSearchOrders);
    }//GEN-LAST:event_txtSearchOrdersFocusLost

    private void btnSearchOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchOrderActionPerformed
        String parameter = cmbSorting.getSelectedItem().toString();
        String searchKeyword = txtSearchOrders.getText();
        switch (parameter) {
            case "Order ID":
                //Implement search in Order
                break;
            case "Order Date":
                //Implement search in Order
                break;
            case "Received Date":
               //Implement search in Order
                break;
            case "Status":
                //Implement search in Order
                break;
            case "By Employee":
                //Implement search in Order
                break;
            case "Approved By":
                //Implement search in Order
                break;
            
        }
        setModel();
    }//GEN-LAST:event_btnSearchOrderActionPerformed

    private void btnLogOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOffActionPerformed
        // TODO add your handling code here:
        Common.logOff(this);
    }//GEN-LAST:event_btnLogOffActionPerformed

    private void jPanel1formHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jPanel1formHierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1formHierarchyChanged

    private void tblOrdersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrdersMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblOrdersMouseClicked

    private void txtSearchStockFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchStockFocusGained
        // TODO add your handling code here:
        Common.focusGain("Serach orders", txtSearchStock);
    }//GEN-LAST:event_txtSearchStockFocusGained

    private void txtSearchStockFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchStockFocusLost
        // TODO add your handling code here:
        Common.focusGain("Serach orders", txtSearchOrders);
    }//GEN-LAST:event_txtSearchStockFocusLost

    private void btnSearchStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchStockActionPerformed

    private void tblNewOrderItemsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNewOrderItemsMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblNewOrderItemsMouseClicked

    private void txtItemNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtItemNameFocusGained
        // TODO add your handling code here:
        Common.focusGain("Item Name", txtItemName);
    }//GEN-LAST:event_txtItemNameFocusGained

    private void txtItemNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtItemNameFocusLost
        // TODO add your handling code here:
        Common.focusLost("Item Name", txtItemName);
    }//GEN-LAST:event_txtItemNameFocusLost

    private void btnAdjustOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdjustOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAdjustOrderActionPerformed

    private void btnApproveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApproveActionPerformed

        DefaultTableModel model = (DefaultTableModel) tblOrders.getModel();
        int row = tblOrders.getSelectedRow();
        if (model.getValueAt(row, 7).toString().equals("Approved")) {
            //TODO add message
        } else {
            model.setValueAt("Approved", row, 7);
            Order order = new Order((int) tblOrders.getValueAt(row, 0),
                    (Date) tblOrders.getValueAt(row, 1),
                    (Date) tblOrders.getValueAt(row, 2),
                    tblOrders.getValueAt(row, 3).toString(),
                    sortUserList(tblOrders.getValueAt(row, 4).toString()),
                    currentUser);
            OrderBuilder orderBuilder = new OrderBuilder(currentUser);
            orderBuilder.approveOrder(currentUser, order.getReceiveDate());
        }

    }//GEN-LAST:event_btnApproveActionPerformed

    private void btnDeclineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeclineActionPerformed

        DefaultTableModel model = (DefaultTableModel) tblOrders.getModel();
        int row = tblOrders.getSelectedRow();
        if (model.getValueAt(row, 7).toString().equals("Pending")) {
            Order order = new Order((int) tblOrders.getValueAt(row, 0),
                    (Date) tblOrders.getValueAt(row, 1),
                    (Date) tblOrders.getValueAt(row, 2),
                    tblOrders.getValueAt(row, 3).toString(),
                    sortUserList(tblOrders.getValueAt(row, 4).toString()),
                    currentUser);
            OrderBuilder orderBuilder = new OrderBuilder(currentUser);
            orderBuilder.rejectOrder(currentUser);

        } else if (model.getValueAt(row, 7).toString().equals("Approved")) {
            //TODO add message
        }
    }//GEN-LAST:event_btnDeclineActionPerformed

    private void mnOpenStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenStaffActionPerformed
        // TODO add your handling code here:
        StaffForm staff = new StaffForm(currentUser);
        staff.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenStaffActionPerformed

    private void txtOrderIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOrderIDFocusLost
        // TODO add your handling code here:
        Common.focusGain("Order ID", txtOrderID);
    }//GEN-LAST:event_txtOrderIDFocusLost

    private void txtOrderIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtOrderIDFocusGained
        // TODO add your handling code here:
        Common.focusLost("Order ID", txtOrderID);
    }//GEN-LAST:event_txtOrderIDFocusGained

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // Delete Record

        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to Delete this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
            int row = tblOrders.getSelectedRow();
            new Order((int) tblOrders.getValueAt(row, 0),
                    (Date) tblOrders.getValueAt(row, 1),
                    (Date) tblOrders.getValueAt(row, 2),
                    tblOrders.getValueAt(row, 3).toString(),
                    sortUserList(tblOrders.getValueAt(row, 4).toString()),
                    currentUser);
            OrderBuilder orderbuilder = new OrderBuilder(currentUser);
            orderbuilder.removeOrder();
            clearAllFields();
            setModel();
        }

        JOptionPane.showMessageDialog(null, "No valid stock item selected");

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        //Update record INCOMPLETE
        /* if (updateClick == 0) {
            updateClick++;
            btnAdd.setEnabled(false);
            btnDelete.setEnabled(false);
            prepareUpdate();
        } else {
            resetColor();
            String category = null;
            String itemName = null;
            int stockCount = 0;
            String status = null;
            stockCount = (int) spOrderQuantity.getValue();
            

            boolean check = true;
            //Add checks?

            if (check == true) {
                 int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    OrderBuilder builder = new OrderBuilder(currentUser);
                    for (int i = 0; i < tblNewOrderItems.getRowCount(); i++) {
                        try {
                            Stock stock = new Stock((int) tblSellItems.getValueAt(i, 0),
                                    (Category) tblSellItems.getValueAt(i, 1),
                                    tblSellItems.getValueAt(i, 2).toString(),
                                    (double) tblSellItems.getValueAt(i, 3),
                                    tblSellItems.getValueAt(i, 4).toString(),
                                    new SimpleDateFormat("yyyy-MM-dd").parse(tblNewOrderItems.getValueAt(i, 5).toString()),
                                    (int) tblSellItems.getValueAt(i, 6),
                                    tblSellItems.getValueAt(i, 7).toString());
                            builder.removeStock(stock, (int) tblSellItems.getValueAt(i, 8));
                            
                        } catch (ParseException ex) {
                            Logger.getLogger(OrderForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //Order.update();
                    setModel();
                }
                clearAllFields();
                disableAllFields();
                resetColor();
                updateClick = 0;
                btnAdd.setEnabled(true);
                btnUpdate.setEnabled(true);
                btnDelete.setEnabled(true);
            } else {
                int option = JOptionPane.showConfirmDialog(this, "There were some errors, would you like to fix them?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 1) {
                    clearAllFields();
                    disableAllFields();
                    resetColor();
                    updateClick = 0;
                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(true);
                    btnDelete.setEnabled(true);
                }
            }
        }*/
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (insertClick == 0) {
            insertClick++;
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
            clearAllFields();
            prepareInsert();
        } else {
            resetColor();
            String category = null;
            String itemName = null;
            int stockCount = 0;
            String status = null;
            stockCount = (int) spOrderQuantity.getValue();

            boolean check = true;
            //Add checks?
            if (check == true) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    OrderBuilder builder = new OrderBuilder(currentUser);
                    for (int i = 0; i < tblNewOrderItems.getRowCount(); i++) {
                        try {
                            Stock stock = new Stock((int) tblSellItems.getValueAt(i, 0),
                                    (Category) tblSellItems.getValueAt(i, 1),
                                    tblSellItems.getValueAt(i, 2).toString(),
                                    (double) tblSellItems.getValueAt(i, 3),
                                    tblSellItems.getValueAt(i, 4).toString(),
                                    new SimpleDateFormat("yyyy-MM-dd").parse(tblNewOrderItems.getValueAt(i, 5).toString()),
                                    (int) tblSellItems.getValueAt(i, 6),
                                    tblSellItems.getValueAt(i, 7).toString());
                            builder.removeStock(stock, (int) tblSellItems.getValueAt(i, 8));

                        } catch (ParseException ex) {
                            Logger.getLogger(OrderForm.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    builder.publishOrder();
                    setModel();
                }
                //int stockID, Category category, String model, double price, String itemName, Date dateAdded, int stockCount, String status
                clearAllFields();
                disableAllFields();
                resetColor();
                insertClick = 0;
                btnAdd.setEnabled(true);
                btnUpdate.setEnabled(true);
                btnDelete.setEnabled(true);
            } else {
                int option = JOptionPane.showConfirmDialog(this, "There were some errors, would you like to fix them?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 1) {
                    spOrderQuantity.setBackground(Color.red);
                    spOrderQuantity.setToolTipText("You require an amount!");
                    clearAllFields();
                    disableAllFields();
                    resetColor();
                    insertClick = 0;
                    btnAdd.setEnabled(true);
                    btnUpdate.setEnabled(true);
                    btnDelete.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void mnOpenStockFormActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOpenStockFormActionPerformed
        // TODO add your handling code here:
        new StockForm(currentUser).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_mnOpenStockFormActionPerformed

    private void btnAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddItemActionPerformed
        // TODO add your handling code here:
        resetColor();
        if ((int) spOrderQuantity.getValue() > 0) {
            DefaultTableModel model = (DefaultTableModel) tblNewOrderItems.getModel();
            model.setNumRows(0);
            Object rowData[] = new Object[8];
            Object columnData[] = new Object[8];
            columnData[0] = "StockID";
            columnData[1] = "CategoryName";
            columnData[2] = "Model";
            columnData[3] = "Price";
            columnData[4] = "ItemName";
            columnData[5] = "DateAdded";
            columnData[6] = "Current Stock";
            columnData[7] = "Status";
            columnData[8] = "Order Amount";

            model.setColumnCount(8);
            model.setColumnIdentifiers(columnData);
            int i = tblSellItems.getSelectedRow();
            rowData[0] = tblSellItems.getValueAt(i, 0);
            rowData[1] = tblSellItems.getValueAt(i, 1);
            rowData[2] = tblSellItems.getValueAt(i, 2);
            rowData[3] = tblSellItems.getValueAt(i, 3);
            rowData[4] = tblSellItems.getValueAt(i, 4);
            rowData[5] = tblSellItems.getValueAt(i, 5);
            rowData[6] = tblSellItems.getValueAt(i, 6);
            rowData[7] = tblSellItems.getValueAt(i, 7);
            rowData[8] = (int) spOrderQuantity.getValue();

            model.addRow(rowData);
        } else {
            spOrderQuantity.setBackground(Color.red);
            spOrderQuantity.setToolTipText("You require an amount!");
        }


    }//GEN-LAST:event_btnAddItemActionPerformed

    private void btnRemoveItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveItemActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tblNewOrderItems.getModel();
        model.removeRow(tblNewOrderItems.getSelectedRow());

    }//GEN-LAST:event_btnRemoveItemActionPerformed

    public void createReport(String fileName,ArrayList<String> data)
    {/*
     Document doc = new Document( PageSize.A4,50,50,50,50);
        
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
        Date date = new Date();
            PdfWriter writer= PdfWriter.getInstance(doc, new FileOutputStream(dateFormat.format(date)+" "+ fileName+".pdf"));
            doc.open();
            String anchorString=String .format("Created on: "+dateFormat.format(date));
            Anchor anchor= new Anchor(anchorString);
            Paragraph title = new Paragraph("Report "+ dateFormat.format(date)+ " on Stocks",FontFactory.getFont(FontFactory.TIMES_ROMAN,26,Font.BOLD,new CMYKColor(100,100,0,0)));
            Chapter chapter = new Chapter(title,0);
            chapter.setNumberDepth(0);
            chapter.add(anchor);
            com.itextpdf.text.List reportList = new com.itextpdf.text.List(true);
            ListItem listItem = null;
            
            for(Object item : data)
            {
                if (item instanceof Stock) {
                    listItem = new ListItem(((Stock) item).getReportFormat());
                    reportList.add(listItem);
 
                }
              
            
            }
            chapter.add(reportList);
            doc.add(chapter);
            doc.close();
            writer.close();
            
        } catch (DocumentException | FileNotFoundException ex) {
            Logger.getLogger(Reports.class.getName()).log(Level.SEVERE, null, ex);
        }
    */
}
    
    public User sortUserList(String name) {
        User user = null;
        List<User> userList = User.getUsersByFullName(name);
        user = userList.get(0);
        return user;
    }

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
            java.util.logging.Logger.getLogger(OrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddItem;
    private javax.swing.JButton btnAdjustOrder;
    private javax.swing.JButton btnApprove;
    private javax.swing.JButton btnDecline;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnLogOff;
    private javax.swing.JButton btnRemoveItem;
    private javax.swing.JButton btnSearchOrder;
    private javax.swing.JButton btnSearchStock;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbOrderStatus;
    private javax.swing.JComboBox<String> cmbSorting;
    private org.jdesktop.swingx.JXDatePicker dpOrderDate;
    private org.jdesktop.swingx.JXDatePicker dpReceiveDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel lbLoginedInUser;
    private javax.swing.JMenuItem mnOpenStaff;
    private javax.swing.JMenuItem mnOpenStockForm;
    private javax.swing.JMenu orderStockMenu;
    private javax.swing.JMenu orderStockMenu2;
    private javax.swing.JSpinner spOrderQuantity;
    private javax.swing.JMenu staffMenu;
    private javax.swing.JMenu stockMenu;
    private javax.swing.JTable tblNewOrderItems;
    private javax.swing.JTable tblOrders;
    private javax.swing.JTable tblSellItems;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtOrderID;
    private javax.swing.JTextField txtSearchOrders;
    private javax.swing.JTextField txtSearchStock;
    // End of variables declaration//GEN-END:variables
}
