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
public class jifrmUpdateDetails extends javax.swing.JInternalFrame {

    /**
     * Creates new form jifrmUpdateDetails
     */
    public jifrmUpdateDetails() {
        initComponents();
        mSetValuesInGUI();
        mDefaultGUIControls();
    }
    Boolean boolEdit = false;
    clsDatabaseQueryingMethods clsQueryingMethods = new clsDatabaseQueryingMethods();
    frmLogin frmlogin = new frmLogin();
    
    
    private void mSetValuesInGUI()
    {
        String[] arrAccountDetails = clsQueryingMethods.mFetchAccountRecordDetails(
                "SELECT AccountName, AccountPassword FROM tblGeneralUsers WHERE AccountID ='"+ frmlogin.mGetUserID()+"'");
        txtUserName.setText(arrAccountDetails[1]);
        txtPassword.setText(clsPasswordAESCryptography.mDecryptPassword(arrAccountDetails[2]));
    } 
    private void mDefaultGUIControls()
    {
        txtUserName.setEditable(false);
        txtPassword.setEditable(false);
        if(btnEdit.getText().equals("Edit"))
        {
            btnEdit.setEnabled(true);
        }
        else if(btnEdit.getText().equals("Delete"))
        {
            btnEdit.setEnabled(true);
            btnSave.setEnabled(true);
        }
        if(btnSave.getText().equals("Save"))
        {
            btnSave.setEnabled(true);
        }
        else if(btnSave.getText().equals("Cancel"))
        {
            btnSave.setEnabled(true);
        }
    }
    private void mEditGUIControls()
    {
        txtUserName.requestFocusInWindow();
        txtUserName.setEditable(true);
        txtPassword.setEditable(true);
        btnEdit.setText("Cancel");
        btnSave.setEnabled(true);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEditAccountDetails = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Edit Account");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/TresPlataformas/e-Library.jpg"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(463, 371));
        setMinimumSize(new java.awt.Dimension(463, 371));
        setPreferredSize(new java.awt.Dimension(463, 371));
        getContentPane().setLayout(null);

        lblEditAccountDetails.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblEditAccountDetails.setForeground(new java.awt.Color(255, 255, 255));
        lblEditAccountDetails.setText("Edit Account Details");
        getContentPane().add(lblEditAccountDetails);
        lblEditAccountDetails.setBounds(110, 10, 200, 22);

        lblUserName.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblUserName.setForeground(new java.awt.Color(255, 255, 255));
        lblUserName.setText("user name");
        getContentPane().add(lblUserName);
        lblUserName.setBounds(70, 70, 72, 17);

        txtUserName.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });
        getContentPane().add(txtUserName);
        txtUserName.setBounds(260, 70, 113, 20);

        lblPassword.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("password");
        getContentPane().add(lblPassword);
        lblPassword.setBounds(70, 140, 64, 17);

        txtPassword.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        getContentPane().add(txtPassword);
        txtPassword.setBounds(260, 140, 113, 20);

        btnEdit.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        getContentPane().add(btnEdit);
        btnEdit.setBounds(70, 200, 90, 25);

        btnSave.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave);
        btnSave.setBounds(280, 200, 90, 25);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/TresPlataformas/Light-dots-with-blue-tech-background-vector-09.jpg"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 450, 340);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        String switchCase = btnEdit.getText();
        switch(switchCase)
        {
            case "Edit":
                mEditGUIControls();
                break;
            
            case "Cancel":
                this.hide();  
                break;
                
            case "Delete":
                clsQueryingMethods.mDeleteAccountDetails("DELETE FROM tblGeneralUsers WHERE AccountID ='"+frmlogin.mGetUserID()+"'");
                JOptionPane.showMessageDialog(null, "Account Deleted!");
                this.hide();
                this.getTopLevelAncestor().hide();
                frmlogin.setVisible(true);
                frmlogin.setExtendedState(MAXIMIZED_BOTH);
                break;
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if(btnSave.getText().equals("Save"))
        {
            clsQueryingMethods.mUpdateAccountDetails("UPDATE tblGeneralUsers SET AccountName ='"+txtUserName.getText()
                    +"', AccountPassword = '"+ clsPasswordAESCryptography.mEncryptPassword(txtPassword.getText().toLowerCase())+
                    "'WHERE AccountID ='"+ frmlogin.mGetUserID()+"'");
            JOptionPane.showMessageDialog(null, "Account Details Edited.");
            this.hide();
        }
        else if(btnSave.getText().equals("Cancel"))
        {
            this.hide();
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnEdit;
    public javax.swing.JButton btnSave;
    private javax.swing.JLabel lblBackground;
    public javax.swing.JLabel lblEditAccountDetails;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUserName;
    public javax.swing.JTextField txtPassword;
    public javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}