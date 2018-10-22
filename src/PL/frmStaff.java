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
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Stephan
 */
public class frmStaff extends javax.swing.JPanel {

    /**
     * Creates new form frmStaff
     */
    public frmStaff() {
        initComponents();
        disableAllFields();
        setModel();
        clearAllFields();
    }
    int insertClick = 0;
    int updateClick = 0;

    public void setModel() {
        DefaultTableModel model = (DefaultTableModel) tblData.getModel();
        model.setNumRows(0);
        List<User> myUsers = User.getUsers();
        Object rowData[] = new Object[20];
        Object columnData[] = new Object[20];
        columnData[0] = "IDNumber";
        columnData[1] = "FirstName";
        columnData[2] = "LastName";
        columnData[3] = "Title";
        columnData[4] = "DateOfBirth";
        columnData[5] = "Gender";
        columnData[6] = "Country";
        columnData[7] = "Province";
        columnData[8] = "City";
        columnData[9] = "Street";
        columnData[10] = "PostalCode";
        columnData[11] = "AddressLine";
        columnData[12] = "Email";
        columnData[13] = "CellNumber";
        columnData[14] = "TelNumber";
        columnData[15] = "DateAdded";
        columnData[16] = "UserID";
        columnData[17] = "Username";
        columnData[18] = "Password";
        columnData[19] = "AccountType";
        model.setColumnCount(20);
        model.setColumnIdentifiers(columnData);
        for (int i = 0; i < myUsers.size(); i++) {
            rowData[0] = myUsers.get(i).getIdNumber();
            rowData[1] = myUsers.get(i).getFirstName();
            rowData[2] = myUsers.get(i).getLastName();
            rowData[3] = myUsers.get(i).getTitle();
            rowData[4] = new SimpleDateFormat("yyyy-MM-dd").format(myUsers.get(i).getDateOfBirth());
            rowData[5] = myUsers.get(i).getGender();
            rowData[6] = myUsers.get(i).getCountry();
            rowData[7] = myUsers.get(i).getProvince();
            rowData[8] = myUsers.get(i).getCity();
            rowData[9] = myUsers.get(i).getStreet();
            rowData[10] = myUsers.get(i).getPostalCode();
            rowData[11] = myUsers.get(i).getAddressLine();
            rowData[12] = myUsers.get(i).getEmail();
            rowData[13] = myUsers.get(i).getCellNumber();
            rowData[14] = myUsers.get(i).getTelNumber();
            rowData[15] = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").format(myUsers.get(i).getDateAdded());
            rowData[16] = myUsers.get(i).getUserID();
            rowData[17] = myUsers.get(i).getUsername();
            rowData[18] = myUsers.get(i).getPassword();
            rowData[19] = myUsers.get(i).getAccountType();
            model.addRow(rowData);
        }
    }

    public void disableAllFields() {
        txtIDNumber.setEnabled(false);
        txtFirstName.setEnabled(false);
        txtLastName.setEnabled(false);
        cmbTitle.setEnabled(false);
        dobPicker.setEnabled(false);
        cmbGender.setEnabled(false);

        txtCountry.setEnabled(false);
        txtProvince.setEnabled(false);
        txtCity.setEnabled(false);
        txtStreet.setEnabled(false);
        txtPostalCode.setEnabled(false);
        txtAddressLine.setEnabled(false);

        txtEmail.setEnabled(false);
        txtCell.setEnabled(false);
        txtTel.setEnabled(false);

        txtUsername.setEnabled(false);
        txtPassword.setEnabled(false);
        cmbAccountType.setEnabled(false);
    }

    public void prepareInsert() {
        txtIDNumber.setEnabled(true);
        txtFirstName.setEnabled(true);
        txtLastName.setEnabled(true);
        cmbTitle.setEnabled(true);
        dobPicker.setEnabled(true);
        cmbGender.setEnabled(true);

        txtCountry.setEnabled(true);
        txtProvince.setEnabled(true);
        txtCity.setEnabled(true);
        txtStreet.setEnabled(true);
        txtPostalCode.setEnabled(true);
        txtAddressLine.setEnabled(true);

        txtEmail.setEnabled(true);
        txtCell.setEnabled(true);
        txtTel.setEnabled(true);

        txtUsername.setEnabled(true);
        txtPassword.setEnabled(true);
        cmbAccountType.setEnabled(true);
    }

    public void prepareUpdate() {
        txtIDNumber.setEnabled(false);
        txtFirstName.setEnabled(true);
        txtLastName.setEnabled(true);
        cmbTitle.setEnabled(true);
        dobPicker.setEnabled(false);
        cmbGender.setEnabled(true);

        txtCountry.setEnabled(true);
        txtProvince.setEnabled(true);
        txtCity.setEnabled(true);
        txtStreet.setEnabled(true);
        txtPostalCode.setEnabled(true);
        txtAddressLine.setEnabled(true);

        txtEmail.setEnabled(true);
        txtCell.setEnabled(true);
        txtTel.setEnabled(true);

        txtUsername.setEnabled(false);
        txtPassword.setEnabled(true);
        cmbAccountType.setEnabled(true);
    }

    private void clearAllFields() {
        txtIDNumber.setText("");
        txtIDNumber.setToolTipText(null);
        txtFirstName.setText("");
        txtFirstName.setToolTipText(null);
        txtLastName.setText("");
        txtLastName.setToolTipText(null);
        cmbTitle.setSelectedIndex(0);
        cmbTitle.setToolTipText(null);
        cmbGender.setSelectedIndex(0);
        cmbGender.setToolTipText(null);
        txtCountry.setText("");
        txtCountry.setToolTipText(null);
        txtProvince.setText("");
        txtProvince.setToolTipText(null);
        txtCity.setText("");
        txtCity.setToolTipText(null);
        txtStreet.setText("");
        txtStreet.setToolTipText(null);
        txtPostalCode.setText("");
        txtPostalCode.setToolTipText(null);
        txtAddressLine.setText("");
        txtAddressLine.setToolTipText(null);
        txtEmail.setText("");
        txtEmail.setToolTipText(null);
        txtCell.setText("");
        txtCell.setToolTipText(null);
        txtTel.setText("");
        txtTel.setToolTipText(null);
        dobPicker.setDate(new Date());
        dobPicker.setToolTipText(null);
        txtUsername.setText("");
        txtUsername.setToolTipText(null);
        txtPassword.setText("");
        txtPassword.setToolTipText(null);
        cmbAccountType.setSelectedIndex(0);
        cmbAccountType.setToolTipText(null);
    }

    public void resetColor() {
        txtIDNumber.setBackground(Color.white);
        txtFirstName.setBackground(Color.white);
        txtLastName.setBackground(Color.white);
        cmbTitle.setBackground(Color.white);
        cmbGender.setBackground(Color.white);
        txtCountry.setBackground(Color.white);
        txtProvince.setBackground(Color.white);
        txtCity.setBackground(Color.white);
        txtStreet.setBackground(Color.white);
        txtPostalCode.setBackground(Color.white);
        txtAddressLine.setBackground(Color.white);
        txtEmail.setBackground(Color.white);
        txtCell.setBackground(Color.white);
        txtTel.setBackground(Color.white);
        dobPicker.setBackground(Color.white);
        txtUsername.setBackground(Color.white);
        txtPassword.setBackground(Color.white);
        cmbAccountType.setBackground(Color.white);
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtIDNumber = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cmbTitle = new javax.swing.JComboBox<>();
        dobPicker = new org.jdesktop.swingx.JXDatePicker();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cmbGender = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCountry = new javax.swing.JTextField();
        txtProvince = new javax.swing.JTextField();
        txtCity = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtPostalCode = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtStreet = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtAddressLine = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtCell = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtTel = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        cmbAccountType = new javax.swing.JComboBox<>();

        jPanel1.setBackground(new java.awt.Color(102, 153, 255));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("ID number: ");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("First name:");

        txtFirstName.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtFirstName.setName("txtUserName"); // NOI18N
        txtFirstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFirstNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFirstNameFocusLost(evt);
            }
        });

        txtIDNumber.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtIDNumber.setName("txtUserName"); // NOI18N
        txtIDNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtIDNumberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtIDNumberFocusLost(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Last name:");

        txtLastName.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtLastName.setName("txtUserName"); // NOI18N
        txtLastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLastNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLastNameFocusLost(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Title:");

        cmbTitle.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cmbTitle.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mr.", "Mrs.", "Miss", "Dr.", "Prof." }));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setText("Date of Birth:");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel10.setText("Gender:");

        cmbGender.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cmbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female" }));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel11.setText("Country");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Province:");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel13.setText("City:");

        txtCountry.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtCountry.setName("txtUserName"); // NOI18N
        txtCountry.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCountryFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCountryFocusLost(evt);
            }
        });

        txtProvince.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtProvince.setName("txtUserName"); // NOI18N
        txtProvince.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProvinceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtProvinceFocusLost(evt);
            }
        });

        txtCity.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtCity.setName("txtUserName"); // NOI18N
        txtCity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCityFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCityFocusLost(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel15.setText("Postal code:");

        txtPostalCode.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtPostalCode.setName("txtUserName"); // NOI18N
        txtPostalCode.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPostalCodeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPostalCodeFocusLost(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Street:");

        txtStreet.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtStreet.setName("txtUserName"); // NOI18N
        txtStreet.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStreetFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStreetFocusLost(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel17.setText("Address Line:");

        txtAddressLine.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtAddressLine.setName("txtUserName"); // NOI18N
        txtAddressLine.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAddressLineFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAddressLineFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(txtAddressLine, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtProvince, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtCity, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtStreet, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txtPostalCode, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtAddressLine, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel18.setText("Email:");

        txtEmail.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtEmail.setName("txtUserName"); // NOI18N
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel24.setText("Cell number:");

        txtCell.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtCell.setName("txtUserName"); // NOI18N
        txtCell.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCellFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCellFocusLost(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel25.setText("Tel number:");

        txtTel.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtTel.setName("txtUserName"); // NOI18N
        txtTel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtTelFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(txtCell, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtCell, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(txtTel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

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

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel14.setText("Employee Form");

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel19.setText("Username:");

        txtUsername.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtUsername.setName("txtUserName"); // NOI18N
        txtUsername.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsernameFocusLost(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setText("Password:");

        txtPassword.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        txtPassword.setName("txtUserName"); // NOI18N
        txtPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPasswordFocusLost(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("Account Type:");

        cmbAccountType.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        cmbAccountType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Admin" }));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(25, 25, 25)
                        .addComponent(cmbAccountType, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(cmbAccountType, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel10))
                                            .addGap(59, 59, 59)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtLastName)
                                                .addComponent(cmbTitle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(dobPicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(188, 188, 188)))
                        .addGap(28, 28, 28))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtIDNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(cmbTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dobPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbGender, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(22, Short.MAX_VALUE))))))
        );

        jLabel9.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtFirstNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFirstNameFocusGained
        // TODO add your handling code here:
        if (txtFirstName.getText().trim().equals("")) {
            txtFirstName.setText("Firstname");

        }
        txtFirstName.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtFirstNameFocusGained

    private void txtFirstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFirstNameFocusLost
        // TODO add your handling code here:
        if (txtFirstName.getText().trim().equals("Firstname")) {
            txtFirstName.setText("");

        }
        txtFirstName.setForeground(Color.BLACK);

    }//GEN-LAST:event_txtFirstNameFocusLost

    private void txtIDNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDNumberFocusGained
        // TODO add your handling code here:
        if (txtIDNumber.getText().trim().equals("")) {
            txtIDNumber.setText("ID Number");

        }
        txtIDNumber.setForeground(Color.LIGHT_GRAY);

    }//GEN-LAST:event_txtIDNumberFocusGained

    private void txtIDNumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtIDNumberFocusLost
        // TODO add your handling code here:
        if (txtIDNumber.getText().trim().equals("ID Number")) {
            txtIDNumber.setText("");

        }
        txtIDNumber.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtIDNumberFocusLost

    private void txtLastNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLastNameFocusGained
        // TODO add your handling code here:
        if (txtLastName.getText().trim().equals("")) {
            txtLastName.setText("Lastname");

        }
        txtLastName.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtLastNameFocusGained

    private void txtLastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLastNameFocusLost
        // TODO add your handling code here:
        if (txtLastName.getText().trim().equals("Lastname")) {
            txtLastName.setText("");

        }
        txtLastName.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtLastNameFocusLost

    private void txtCountryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCountryFocusGained
        // TODO add your handling code here:
        if (txtCountry.getText().trim().equals("")) {
            txtCountry.setText("Country");

        }
        txtCountry.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtCountryFocusGained

    private void txtCountryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCountryFocusLost
        // TODO add your handling code here:
        if (txtCountry.getText().trim().equals("Country")) {
            txtCountry.setText("");

        }
        txtCountry.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtCountryFocusLost

    private void txtProvinceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProvinceFocusGained
        // TODO add your handling code here:
        if (txtProvince.getText().trim().equals("")) {
            txtProvince.setText("Province");

        }
        txtProvince.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtProvinceFocusGained

    private void txtProvinceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProvinceFocusLost
        // TODO add your handling code here:
        if (txtProvince.getText().trim().equals("Province")) {
            txtProvince.setText("");

        }
        txtProvince.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtProvinceFocusLost

    private void txtCityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCityFocusGained
        // TODO add your handling code here:
        if (txtCity.getText().trim().equals("")) {
            txtCity.setText("City");

        }
        txtCity.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtCityFocusGained

    private void txtCityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCityFocusLost
        // TODO add your handling code here:
        if (txtCity.getText().trim().equals("City")) {
            txtCity.setText("");

        }
        txtCity.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtCityFocusLost

    private void txtPostalCodeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPostalCodeFocusGained
        // TODO add your handling code here:
        if (txtPostalCode.getText().trim().equals("")) {
            txtPostalCode.setText("Postal code");

        }
        txtPostalCode.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtPostalCodeFocusGained

    private void txtPostalCodeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPostalCodeFocusLost
        // TODO add your handling code here:
        if (txtPostalCode.getText().trim().equals("Postal code")) {
            txtPostalCode.setText("");

        }
        txtPostalCode.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtPostalCodeFocusLost

    private void txtStreetFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStreetFocusGained
        // TODO add your handling code here:
        if (txtStreet.getText().trim().equals("")) {
            txtStreet.setText("Street Address");

        }
        txtStreet.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtStreetFocusGained

    private void txtStreetFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStreetFocusLost
        // TODO add your handling code here:
        if (txtStreet.getText().trim().equals("Street Address")) {
            txtStreet.setText("");

        }
        txtStreet.setForeground(Color.LIGHT_GRAY);

    }//GEN-LAST:event_txtStreetFocusLost

    private void txtAddressLineFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAddressLineFocusGained
        // TODO add your handling code here:
        if (txtAddressLine.getText().trim().equals("")) {
            txtAddressLine.setText("Address Line");

        }
        txtAddressLine.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtAddressLineFocusGained

    private void txtAddressLineFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAddressLineFocusLost
        // TODO add your handling code here:
        if (txtAddressLine.getText().trim().equals("Address Line")) {
            txtAddressLine.setText("");

        }
        txtAddressLine.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtAddressLineFocusLost

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        // TODO add your handling code here:
        if (txtEmail.getText().trim().equals("")) {
            txtEmail.setText("Email Address");

        }
        txtEmail.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtEmailFocusGained

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost
        // TODO add your handling code here:
        if (txtEmail.getText().trim().equals("Email Address")) {
            txtEmail.setText("");

        }
        txtEmail.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtEmailFocusLost

    private void txtCellFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCellFocusGained
        // TODO add your handling code here:
        if (txtCell.getText().trim().equals("")) {
            txtCell.setText("Cellphone number");

        }
        txtCell.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtCellFocusGained

    private void txtCellFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCellFocusLost
        // TODO add your handling code here:
        if (txtCell.getText().trim().equals("Cellphone number")) {
            txtCell.setText("");

        }
        txtCell.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtCellFocusLost

    private void txtTelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelFocusGained
        // TODO add your handling code here:
        if (txtTel.getText().trim().equals("")) {
            txtTel.setText("Telephone number");

        }
        txtTel.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtTelFocusGained

    private void txtTelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTelFocusLost
        // TODO add your handling code here:
        if (txtTel.getText().trim().equals("Telephone number")) {
            txtTel.setText("");

        }
        txtTel.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtTelFocusLost

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        if (insertClick == 0) {
            insertClick++;
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
            clearAllFields();
            prepareInsert();
        } else {
            resetColor();
            String idNumber;
            String firstName;
            String lastName;
            String title;
            Date dateOfBirth;
            String gender;
            String country;
            String province;
            String city;
            String street;
            String postalCode;
            String addressLine ;
            String email;
            String cellNumber;
            String telNumber;
            String username;
            String password;
            String accountType;

            idNumber = txtIDNumber.getText();
            firstName = txtFirstName.getText();
            lastName = txtLastName.getText();
            title = cmbTitle.getSelectedItem().toString();
            dateOfBirth = dobPicker.getDate();
            gender = cmbGender.getSelectedItem().toString();
            country = txtCountry.getText();
            province = (txtProvince.getText() != null) ? txtProvince.getText() : "";
            city = (txtCity.getText() != null) ? txtCity.getText() : "";
            street = (txtStreet.getText() != null) ? txtStreet.getText() : "";
            postalCode = (txtPostalCode.getText() != null) ? txtPostalCode.getText() : "";
            addressLine = (txtAddressLine.getText() != null) ? txtAddressLine.getText() : "";
            email = (txtEmail.getText() != null) ? txtEmail.getText() : "";
            cellNumber = (txtCell.getText() != null) ? txtCell.getText() : "";
            telNumber = (txtTel.getText() != null) ? txtTel.getText() : "";
            username = txtUsername.getText();
            password = txtPassword.getText();
            accountType = cmbAccountType.getSelectedItem().toString();

            boolean check = true;
            if (Common.checkInput(idNumber) != 10 || idNumber.length() != 13) {
                check = false;
                txtIDNumber.setBackground(Color.red);
                txtIDNumber.setToolTipText("Only numerical values. Must be 13 characters. Must be valid RSA ID Number");
            }
            if (Common.checkInput(firstName) != 1 || firstName.length() > 20) {
                check = false;
                txtFirstName.setBackground(Color.red);
                txtFirstName.setToolTipText("Only alphabetical characters. Max 20 characters");
            }
            if (Common.checkInput(lastName) != 1 || lastName.length() > 20) {
                check = false;
                txtLastName.setBackground(Color.red);
                txtLastName.setToolTipText("Only alphabetical characters. Max 20 characters");
            }
            if (!(Common.checkInput(title) == 1 || Common.checkInput(title) == 5) || title.length() > 4) {
                check = false;
                cmbTitle.setBackground(Color.red);
                cmbTitle.setToolTipText("Only alphabetical characters. Max 4 characters");
            }
            //DATE OF BIRTH CHECK NOT INCLUDED
            if (Common.checkInput(gender) != 1 || gender.length() > 6) {
                check = false;
                cmbGender.setBackground(Color.red);
                cmbGender.setToolTipText("Only alphabetical characters. Max 6 characters");
            }
            if (Common.checkInput(country) != 1 || country.length() > 20) {
                check = false;
                txtCountry.setBackground(Color.red);
                txtCountry.setToolTipText("Only alphabetical characters. Max 20 characters");
            }
            if (!(Common.checkInput(province) == 0 || Common.checkInput(province) == 1 || Common.checkInput(province) == 5) || province.length() > 20) {
                check = false;
                txtProvince.setBackground(Color.orange);
                txtProvince.setToolTipText("(Optional Field) Only alphabetical characters and optional special characters. Max 20 characters");
            }
            if (!(Common.checkInput(city) == 0 || Common.checkInput(city) == 1) || city.length() > 30) {
                check = false;
                txtCity.setBackground(Color.orange);
                txtCity.setToolTipText("(Optional Field) Only alphabetical characters. Max 30 characters");
            }
            if (!(Common.checkInput(street) == 0 || Common.checkInput(street) == 1 || Common.checkInput(street) == 4) || street.length() > 30) {
                check = false;
                txtStreet.setBackground(Color.orange);
                txtStreet.setToolTipText("(Optional Field) Only alphabetical characters and optional numerical values. Max 30 characters");
            }
            if (!(Common.checkInput(postalCode) == 0 || Common.checkInput(postalCode) == 2) || postalCode.length() > 10) {
                check = false;
                txtPostalCode.setBackground(Color.orange);
                txtPostalCode.setToolTipText("(Optional Field) Only numerical values. Max 10 characters");
            }
            if (addressLine.length() > 50) {
                check = false;
                txtAddressLine.setBackground(Color.orange);
                txtAddressLine.setToolTipText("(Optional Field) Any characters. Max 50 characters");
            }
            if (!(Common.checkInput(email) == 0 || Common.checkInput(email) == 9) || email.length() > 30) {
                check = false;
                txtEmail.setBackground(Color.orange);
                txtEmail.setToolTipText("(Optional Field) Only valid email address. Max 30 characters");
            }
            if (!(Common.checkInput(cellNumber) == 0 || (Common.checkInput(cellNumber) == 2 && cellNumber.length() == 10))) {
                check = false;
                txtCell.setBackground(Color.orange);
                txtCell.setToolTipText("(Optional Field) Only numerical values. Must be 10 characters");
            }
            if (!(Common.checkInput(telNumber) == 0 || (Common.checkInput(telNumber) == 2 && telNumber.length() == 10))) {
                check = false;
                txtTel.setBackground(Color.orange);
                txtTel.setToolTipText("(Optional Field) Only numerical values. Must be 10 characters");
            }
            if (Common.checkInput(username) != 1 || username.length() > 20) {
                check = false;
                txtUsername.setBackground(Color.red);
                txtUsername.setToolTipText("Only alphabetical characters. Max 20 characters");
            }
            if (Common.checkInput(password) != 7 || password.length() < 4 || password.length() > 20) {
                check = false;
                txtPassword.setBackground(Color.red);
                txtPassword.setToolTipText("Must contain alphabetical characters and at least one numerical value and at least one special character. Min 4 characters. Max 20 characters");
            }
            if (Common.checkInput(accountType) != 1 || accountType.length() > 50) {
                check = false;
                cmbAccountType.setBackground(Color.red);
                cmbAccountType.setToolTipText("Only alphabetical characters. Max 50 characters");
            }

            if (check == true) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to add this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    new User(firstName, lastName, title, dateOfBirth, gender, country, province, city, street, postalCode, addressLine, email, cellNumber, telNumber, new Date(), 0, username, password, accountType, idNumber).registerUser();
                    setModel();
                }
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

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        //Update record
        if (updateClick == 0) {
            updateClick++;
            btnAdd.setEnabled(false);
            btnDelete.setEnabled(false);
            prepareUpdate();
        } else {
            resetColor();
            String idNumber;
            String firstName;
            String lastName;
            String title;
            Date dateOfBirth;
            String gender;
            String country;
            String province;
            String city;
            String street;
            String postalCode;
            String addressLine ;
            String email;
            String cellNumber;
            String telNumber;
            String username;
            String password;
            String accountType;

            idNumber = txtIDNumber.getText();
            firstName = txtFirstName.getText();
            lastName = txtLastName.getText();
            title = cmbTitle.getSelectedItem().toString();
            dateOfBirth = dobPicker.getDate();
            gender = cmbGender.getSelectedItem().toString();
            country = txtCountry.getText();
            province = (txtProvince.getText() != null) ? txtProvince.getText() : "";
            city = (txtCity.getText() != null) ? txtCity.getText() : "";
            street = (txtStreet.getText() != null) ? txtStreet.getText() : "";
            postalCode = (txtPostalCode.getText() != null) ? txtPostalCode.getText() : "";
            addressLine = (txtAddressLine.getText() != null) ? txtAddressLine.getText() : "";
            email = (txtEmail.getText() != null) ? txtEmail.getText() : "";
            cellNumber = (txtCell.getText() != null) ? txtCell.getText() : "";
            telNumber = (txtTel.getText() != null) ? txtTel.getText() : "";
            username = txtUsername.getText();
            password = txtPassword.getText();
            accountType = cmbAccountType.getSelectedItem().toString();

            boolean check = true;
            if (Common.checkInput(idNumber) != 10 || idNumber.length() != 13) {
                check = false;
                txtIDNumber.setBackground(Color.red);
                txtIDNumber.setToolTipText("Only numerical values. Must be 13 characters. Must be valid RSA ID Number");
            }
            if (Common.checkInput(firstName) != 1 || firstName.length() > 20) {
                check = false;
                txtFirstName.setBackground(Color.red);
                txtFirstName.setToolTipText("Only alphabetical characters. Max 20 characters");
            }
            if (Common.checkInput(lastName) != 1 || lastName.length() > 20) {
                check = false;
                txtLastName.setBackground(Color.red);
                txtLastName.setToolTipText("Only alphabetical characters. Max 20 characters");
            }
            if (!(Common.checkInput(title) == 1 || Common.checkInput(title) == 5) || title.length() > 4) {
                check = false;
                cmbTitle.setBackground(Color.red);
                cmbTitle.setToolTipText("Only alphabetical characters. Max 4 characters");
            }
            //DATE OF BIRTH CHECK NOT INCLUDED
            if (Common.checkInput(gender) != 1 || gender.length() > 6) {
                check = false;
                cmbGender.setBackground(Color.red);
                cmbGender.setToolTipText("Only alphabetical characters. Max 6 characters");
            }
            if (Common.checkInput(country) != 1 || country.length() > 20) {
                check = false;
                txtCountry.setBackground(Color.red);
                txtCountry.setToolTipText("Only alphabetical characters. Max 20 characters");
            }
            if (!(Common.checkInput(province) == 0 || Common.checkInput(province) == 1 || Common.checkInput(province) == 5) || province.length() > 20) {
                check = false;
                txtProvince.setBackground(Color.orange);
                txtProvince.setToolTipText("(Optional Field) Only alphabetical characters and optional special characters. Max 20 characters");
            }
            if (!(Common.checkInput(city) == 0 || Common.checkInput(city) == 1) || city.length() > 30) {
                check = false;
                txtCity.setBackground(Color.orange);
                txtCity.setToolTipText("(Optional Field) Only alphabetical characters. Max 30 characters");
            }
            if (!(Common.checkInput(street) == 0 || Common.checkInput(street) == 1 || Common.checkInput(street) == 4) || street.length() > 30) {
                check = false;
                txtStreet.setBackground(Color.orange);
                txtStreet.setToolTipText("(Optional Field) Only alphabetical characters and optional numerical values. Max 30 characters");
            }
            if (!(Common.checkInput(postalCode) == 0 || Common.checkInput(postalCode) == 2) || postalCode.length() > 10) {
                check = false;
                txtPostalCode.setBackground(Color.orange);
                txtPostalCode.setToolTipText("(Optional Field) Only numerical values. Max 10 characters");
            }
            if (addressLine.length() > 50) {
                check = false;
                txtAddressLine.setBackground(Color.orange);
                txtAddressLine.setToolTipText("(Optional Field) Any characters. Max 50 characters");
            }
            if (!(Common.checkInput(email) == 0 || Common.checkInput(email) == 9) || email.length() > 30) {
                check = false;
                txtEmail.setBackground(Color.orange);
                txtEmail.setToolTipText("(Optional Field) Only valid email address. Max 30 characters");
            }
            if (!(Common.checkInput(cellNumber) == 0 || (Common.checkInput(cellNumber) == 2 && cellNumber.length() == 10))) {
                check = false;
                txtCell.setBackground(Color.orange);
                txtCell.setToolTipText("(Optional Field) Only numerical values. Must be 10 characters");
            }
            if (!(Common.checkInput(telNumber) == 0 || (Common.checkInput(telNumber) == 2 && telNumber.length() == 10))) {
                check = false;
                txtTel.setBackground(Color.orange);
                txtTel.setToolTipText("(Optional Field) Only numerical values. Must be 10 characters");
            }
            if (Common.checkInput(username) != 1 || username.length() > 20) {
                check = false;
                txtUsername.setBackground(Color.red);
                txtUsername.setToolTipText("Only alphabetical characters. Max 20 characters");
            }
            if (Common.checkInput(password) != 7 || password.length() < 4 || password.length() > 20) {
                check = false;
                txtPassword.setBackground(Color.red);
                txtPassword.setToolTipText("Must contain alphabetical characters and at least one numerical value and at least one special character. Min 4 characters. Max 20 characters");
            }
            if (Common.checkInput(accountType) != 1 || accountType.length() > 50) {
                check = false;
                cmbAccountType.setBackground(Color.red);
                cmbAccountType.setToolTipText("Only alphabetical characters. Max 50 characters");
            }

            if (check == true) {
                int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to update this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    new User(firstName, lastName, title, dateOfBirth, gender, country, province, city, street, postalCode, addressLine, email, cellNumber, telNumber, new Date(), User.GetUserByIdNumber(idNumber).getUserID(), username, password, accountType, idNumber).updateUser();
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
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // Delete Record
        if (Common.checkInput(txtIDNumber.getText()) == 10 && User.GetUserByIdNumber(txtIDNumber.getText()) != null) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to Delete this data?", "Confirmation.", JOptionPane.YES_NO_OPTION);
            if (option == 0) {
                User.deleteUser(txtIDNumber.getText(), User.GetUserByIdNumber(txtIDNumber.getText()).getUserID());
                clearAllFields();
                setModel();
            }
        } else {
            JOptionPane.showMessageDialog(null, "No valid user selected");
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void txtUsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusGained
        // TODO add your handling code here:
        if (txtUsername.getText().trim().equals("")) {
            txtUsername.setText("Username");

        }
        txtUsername.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtUsernameFocusGained

    private void txtUsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsernameFocusLost
        // TODO add your handling code here:
        if (txtUsername.getText().trim().equals("Username")) {
            txtUsername.setText("");

        }
        txtUsername.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtUsernameFocusLost

    private void txtPasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusGained
        // TODO add your handling code here:
        if (txtPassword.getText().trim().equals("")) {
            txtPassword.setText("Password");

        }
        txtPassword.setForeground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_txtPasswordFocusGained

    private void txtPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPasswordFocusLost
        // TODO add your handling code here:
        if (txtPassword.getText().trim().equals("Password")) {
            txtPassword.setText("");

        }
        txtPassword.setForeground(Color.BLACK);
    }//GEN-LAST:event_txtPasswordFocusLost

    private void tblDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDataMouseClicked
        int i = tblData.getSelectedRow();

        User selectedUser;
        try {
            selectedUser = new User(tblData.getValueAt(i, 1).toString(),
                    tblData.getValueAt(i, 2).toString(),
                    tblData.getValueAt(i, 3).toString(),
                    new SimpleDateFormat("yyyy-MM-dd").parse(tblData.getValueAt(i, 4).toString()),
                    tblData.getValueAt(i, 5).toString(),
                    tblData.getValueAt(i, 6).toString(),
                    (tblData.getValueAt(i, 7) != null) ? tblData.getValueAt(i, 7).toString() : "",
                    (tblData.getValueAt(i, 8) != null) ? tblData.getValueAt(i, 8).toString() : "",
                    (tblData.getValueAt(i, 9) != null) ? tblData.getValueAt(i, 9).toString() : "",
                    (tblData.getValueAt(i, 10) != null) ? tblData.getValueAt(i, 10).toString() : "",
                    (tblData.getValueAt(i, 11) != null) ? tblData.getValueAt(i, 11).toString() : "",
                    (tblData.getValueAt(i, 12) != null) ? tblData.getValueAt(i, 12).toString() : "",
                    (tblData.getValueAt(i, 13) != null) ? tblData.getValueAt(i, 13).toString() : "",
                    (tblData.getValueAt(i, 14) != null) ? tblData.getValueAt(i, 14).toString() : "",
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S").parse(tblData.getValueAt(i, 15).toString()),
                    Integer.valueOf(tblData.getValueAt(i, 16).toString()),
                    tblData.getValueAt(i, 17).toString(),
                    tblData.getValueAt(i, 18).toString(),
                    tblData.getValueAt(i, 19).toString(),
                    tblData.getValueAt(i, 0).toString());

            txtIDNumber.setText(selectedUser.getIdNumber());
            txtFirstName.setText(selectedUser.getFirstName());
            txtLastName.setText(selectedUser.getLastName());
            cmbTitle.setSelectedItem(selectedUser.getTitle());
            dobPicker.setDate(selectedUser.getDateOfBirth());
            cmbGender.setSelectedItem(selectedUser.getGender());
            txtCountry.setText(selectedUser.getCountry());
            txtProvince.setText(selectedUser.getProvince());
            txtCity.setText(selectedUser.getCity());
            txtStreet.setText(selectedUser.getStreet());
            txtPostalCode.setText(selectedUser.getPostalCode());
            txtAddressLine.setText(selectedUser.getAddressLine());
            txtEmail.setText(selectedUser.getEmail());
            txtCell.setText(selectedUser.getCellNumber());
            txtTel.setText(selectedUser.getTelNumber());
            txtUsername.setText(selectedUser.getUsername());
            txtPassword.setText(selectedUser.getPassword());
            cmbAccountType.setSelectedItem(selectedUser.getAccountType());
        } catch (ParseException ex) {
            Logger.getLogger(frmStock.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblDataMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbAccountType;
    private javax.swing.JComboBox<String> cmbGender;
    private javax.swing.JComboBox<String> cmbTitle;
    private org.jdesktop.swingx.JXDatePicker dobPicker;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtAddressLine;
    private javax.swing.JTextField txtCell;
    private javax.swing.JTextField txtCity;
    private javax.swing.JTextField txtCountry;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtIDNumber;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPostalCode;
    private javax.swing.JTextField txtProvince;
    private javax.swing.JTextField txtStreet;
    private javax.swing.JTextField txtTel;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
