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
       /* disableAllFields();
        setModel();
        clearAllFields();*/
    }
    /*int insertClick = 0;
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
*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu9 = new javax.swing.JMenu();
        jMenu10 = new javax.swing.JMenu();
        jMenuBar6 = new javax.swing.JMenuBar();
        jMenu11 = new javax.swing.JMenu();
        jMenu12 = new javax.swing.JMenu();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jMenu9.setText("File");
        jMenuBar5.add(jMenu9);

        jMenu10.setText("Edit");
        jMenuBar5.add(jMenu10);

        jMenu11.setText("File");
        jMenuBar6.add(jMenu11);

        jMenu12.setText("Edit");
        jMenuBar6.add(jMenu12);

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1345, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 928, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu12;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private javax.swing.JMenuBar jMenuBar6;
    // End of variables declaration//GEN-END:variables
}
