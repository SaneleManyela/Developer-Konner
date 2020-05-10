/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.TresPlataformas;

import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.JOptionPane;

/**
 *
 * @author Sanele
 */
public class jifrmAdminAccountsManagement extends javax.swing.JInternalFrame {

    /**
     * Creates new form jifrmAdminAccountsManagement
     */
    public jifrmAdminAccountsManagement() {
        initComponents();
        mDefaultGUIControls();
        mSetValuesInGUI();
    }
    frmLogin frmlogin = new frmLogin();
    clsIDGenerator id = new clsIDGenerator();
    
    private void mDefaultGUIControls()
    {
        txtAdminName.setEditable(false);
        txtAdminPassword.setEditable(false);
        btnAddAccount.setEnabled(true);
        btnEditAccount.setEnabled(true);
        btnDeleteAccount.setEnabled(true);
        btnSaveAccountDetails.setEnabled(false);
    }
    
    private void mAddAccountGUIControls()
    {
        txtAdminName.setEditable(true);
        txtAdminPassword.setEditable(true);
        btnAddAccount.setEnabled(true);
        btnEditAccount.setEnabled(false);
        btnDeleteAccount.setEnabled(false);
        btnSaveAccountDetails.setEnabled(true);
        txtAdminName.requestFocusInWindow();
    }
    
    private void mEditAccountGUIControls()
    {
        txtAdminName.setEditable(true);
        txtAdminPassword.setEditable(true);
        btnAddAccount.setEnabled(false);
        btnEditAccount.setEnabled(true);
        btnDeleteAccount.setEnabled(false);
        btnSaveAccountDetails.setEnabled(true);
        txtAdminName.requestFocusInWindow();
    }
    
    private void mDeleteAccountGUIControls()
    {
        mDefaultGUIControls();
    }
    
    private void mSaveAccountDetailsGUIControls()
    {
        mDeleteAccountGUIControls();
    }
    
    clsDatabaseQueryingMethods clsQueryingMethods = new clsDatabaseQueryingMethods();
    boolean boolAccountExist = false;
        
    private void mSetValuesInGUI()
    {
        String[] arrAccountDetails = clsQueryingMethods.mFetchAccountRecordDetails("SELECT Name, Password FROM tblAdmins WHERE AdminID ='"+ 
                frmlogin.mGetUserID()+"'");
        txtAdminName.setText(arrAccountDetails[1]);
        txtAdminPassword.setText(clsPasswordAESCryptography.mDecryptPassword(arrAccountDetails[2]));
    }
    
    private void mClearGUITextFields()
    {
        txtAdminName.setText("");
        txtAdminPassword.setText("");
    }
    
    private void mAfterSaveProcedure()
    {
        mSaveAccountDetailsGUIControls();
        mClearGUITextFields();
        if(btnAddAccount.getText().equals("Cancel")){
            btnAddAccount.setText("Add Account");
        }else if(btnEditAccount.getText().equals("Cancel")){
            btnEditAccount.setText("Edit Account");
        }
        mSetValuesInGUI();
    }
    
    private void mSaveAccountDetails()
    {
        if(txtAdminName.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Admin Name field cannot be left empty");
            txtAdminName.requestFocusInWindow();
        }else if(txtAdminPassword.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Admin Password field cannot be left empty");
            txtAdminPassword.requestFocusInWindow();
        }else if(btnAddAccount.isEnabled()){
            try{
                boolAccountExist = clsQueryingMethods.mCheckIfRecordExists("SELECT * FROM tblAdmins WHERE Name='" + txtAdminName.getText().toLowerCase()
                                    +"' AND Password ='" + clsPasswordAESCryptography.mEncryptPassword(txtAdminPassword.getText())+"'");
            }catch(NullPointerException eX){
                JOptionPane.showMessageDialog(null, "Technical error encountered, try again.");
            }
            if(boolAccountExist == true)
            {
                boolAccountExist = false;
                JOptionPane.showMessageDialog(null, "You have an account already.");
            }
            else if(boolAccountExist == false)
            {
                if(txtAdminPassword.getText().toLowerCase().startsWith("admin")){
                    clsQueryingMethods.mCreateRecord("INSERT INTO tblAdmins " + "(AdminID, Name, Password)" + 
                        "VALUES ('"+id.mGetID().toString()+"','"+txtAdminName.getText() + "','" + clsPasswordAESCryptography.mEncryptPassword(txtAdminPassword.getText().toLowerCase())+"')");
                    JOptionPane.showMessageDialog(null, "Account has been created.");
                    mAfterSaveProcedure();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Admin passwords must be prefixed with the term 'admin'");
                    txtAdminPassword.requestFocusInWindow();
                }
            }
        }else{
            if(txtAdminPassword.getText().toLowerCase().startsWith("admin")){
                clsQueryingMethods.mUpdateAccountDetails("UPDATE tblAdmins SET Name='"+txtAdminName.getText()
                    +"' ,Password='"+clsPasswordAESCryptography.mEncryptPassword(txtAdminPassword.getText().toLowerCase())
                    +"' WHERE AdminID='"+frmlogin.mGetUserID()+"'");
                JOptionPane.showMessageDialog(null, "Account details have been updated.");
                mAfterSaveProcedure();
            }else{
                JOptionPane.showMessageDialog(null, "Admin passwords must be prefixed with the term 'admin'");
                txtAdminPassword.requestFocusInWindow();
            }
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

        lblHeading = new javax.swing.JLabel();
        lblAdminName = new javax.swing.JLabel();
        txtAdminName = new javax.swing.JTextField();
        lblAdminPassword = new javax.swing.JLabel();
        txtAdminPassword = new javax.swing.JTextField();
        btnAddAccount = new javax.swing.JButton();
        btnEditAccount = new javax.swing.JButton();
        btnDeleteAccount = new javax.swing.JButton();
        btnSaveAccountDetails = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Admin Accounts Management");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/TresPlataformas/e-Library.jpg"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(414, 462));
        setMinimumSize(new java.awt.Dimension(414, 462));
        setPreferredSize(new java.awt.Dimension(414, 462));
        getContentPane().setLayout(null);

        lblHeading.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblHeading.setForeground(new java.awt.Color(240, 240, 240));
        lblHeading.setText("Manage Account");
        getContentPane().add(lblHeading);
        lblHeading.setBounds(100, 20, 200, 30);

        lblAdminName.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblAdminName.setForeground(new java.awt.Color(240, 240, 240));
        lblAdminName.setText("Admin Name");
        getContentPane().add(lblAdminName);
        lblAdminName.setBounds(30, 100, 100, 30);
        getContentPane().add(txtAdminName);
        txtAdminName.setBounds(220, 100, 160, 30);

        lblAdminPassword.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblAdminPassword.setForeground(new java.awt.Color(240, 240, 240));
        lblAdminPassword.setText("Admin Password");
        getContentPane().add(lblAdminPassword);
        lblAdminPassword.setBounds(30, 150, 160, 30);
        getContentPane().add(txtAdminPassword);
        txtAdminPassword.setBounds(220, 150, 160, 30);

        btnAddAccount.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        btnAddAccount.setText("Add Account");
        btnAddAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAccountActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddAccount);
        btnAddAccount.setBounds(90, 240, 220, 23);

        btnEditAccount.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        btnEditAccount.setText("Edit Account");
        btnEditAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditAccountActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditAccount);
        btnEditAccount.setBounds(90, 280, 220, 23);

        btnDeleteAccount.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        btnDeleteAccount.setText("Delete Account");
        btnDeleteAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteAccountActionPerformed(evt);
            }
        });
        getContentPane().add(btnDeleteAccount);
        btnDeleteAccount.setBounds(90, 320, 220, 23);

        btnSaveAccountDetails.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        btnSaveAccountDetails.setText("Save Account Details");
        btnSaveAccountDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAccountDetailsActionPerformed(evt);
            }
        });
        getContentPane().add(btnSaveAccountDetails);
        btnSaveAccountDetails.setBounds(90, 360, 220, 23);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/TresPlataformas/Light-dots-with-blue-tech-background-vector-09.jpg"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(-10, 0, 410, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAccountActionPerformed
        mAddAccountGUIControls();
        switch(btnAddAccount.getText())
        {
            case "Add Account":
                txtAdminName.setText("");
                txtAdminPassword.setText("admin");
                JOptionPane.showMessageDialog(null, "Admin passwords must be prefixed with the term 'admin'");
                btnAddAccount.setText("Cancel");
                break;
            case "Cancel":
                btnAddAccount.setText("Add Account");
                mClearGUITextFields();
                mSetValuesInGUI();
                mDefaultGUIControls();
        }
    }//GEN-LAST:event_btnAddAccountActionPerformed

    private void btnEditAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditAccountActionPerformed
        mEditAccountGUIControls();
        switch(btnEditAccount.getText())
        {
            case "Edit Account":
                btnEditAccount.setText("Cancel");
                break;
            case "Cancel":
                btnEditAccount.setText("Edit Account");
                mClearGUITextFields();
                mDefaultGUIControls();
                mSetValuesInGUI();
                break;
        }
    }//GEN-LAST:event_btnEditAccountActionPerformed

    private void btnDeleteAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteAccountActionPerformed
        mDeleteAccountGUIControls();
        clsQueryingMethods.mDeleteAccountDetails("DELETE FROM tblAdmins WHERE AdminID='"+frmlogin.mGetUserID()+"'");
        JOptionPane.showMessageDialog(null, "Account Deleted!");
        mClearGUITextFields();
        this.hide();
        this.getTopLevelAncestor().hide();
        frmlogin.setVisible(true);
        frmlogin.setExtendedState(MAXIMIZED_BOTH);
    }//GEN-LAST:event_btnDeleteAccountActionPerformed

    private void btnSaveAccountDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAccountDetailsActionPerformed
        mSaveAccountDetails();
    }//GEN-LAST:event_btnSaveAccountDetailsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAccount;
    private javax.swing.JButton btnDeleteAccount;
    private javax.swing.JButton btnEditAccount;
    private javax.swing.JButton btnSaveAccountDetails;
    private javax.swing.JLabel lblAdminName;
    private javax.swing.JLabel lblAdminPassword;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JTextField txtAdminName;
    private javax.swing.JTextField txtAdminPassword;
    // End of variables declaration//GEN-END:variables
}
