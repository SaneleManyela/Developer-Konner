/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.TresPlataformas;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

/**
 *
 * @author Sanele
 */
public class frmLogin extends javax.swing.JFrame {

    /**
     * Creates new form frmLogin
     */
    
    public frmLogin() {
        initComponents();
        txtAccountName.requestFocusInWindow();
    }
    private static String strUserID;
    String strAccountName;
    String strAccountPassword;
   
    clsDatabaseQueryingMethods clsQueryingMethods = new clsDatabaseQueryingMethods();
    
    public String mGetUserID()
    {
        return frmLogin.strUserID;
    }
    
    public void mSetUserID(String strValue)
    {
        frmLogin.strUserID = strValue;
    }
    
    private String mAuthenticateGeneralUser()
    {
        return "SELECT AccountID, AccountName, AccountPassword FROM tblGeneralUsers WHERE AccountName ='"
                +txtAccountName.getText()+"' AND AccountPassword='"
                +clsPasswordAESCryptography.mEncryptPassword(txtAccountPassword.getText().toLowerCase())+"'";
    }
    
    private String mAuthenticateAdmin()
    {
        return "SELECT AdminID, Name, Password FROM tblAdmins WHERE Name ='"+txtAccountName.getText()
                +"' AND Password='"
                +clsPasswordAESCryptography.mEncryptPassword(txtAccountPassword.getText().toLowerCase())+"'";
    }
    
    private void mAssignVariables(String strQuery)
    {
        String[] arrLoginDetails = clsQueryingMethods.mFetchAccountRecordDetails(strQuery);      
        frmLogin.strUserID = arrLoginDetails[1];
        this.strAccountName = arrLoginDetails[2];
        this.strAccountPassword = arrLoginDetails[3];
    }
    private void mLogin()
    {
        try
        {
            if(txtAccountName.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Account name is required");
                txtAccountName.requestFocusInWindow();
            }
            else if(txtAccountPassword.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Account password is required");
                txtAccountPassword.requestFocusInWindow();
            }
            else
            {
                String strIdentifier;
                if(txtAccountPassword.getText().toLowerCase().startsWith("Admin".toLowerCase())){
                    strIdentifier = "Yes";
                }else{
                    strIdentifier = "No";
                }
                switch(strIdentifier){
                    case "Yes":
                        mAssignVariables(mAuthenticateAdmin());
                        if(strAccountName.toLowerCase().equals(txtAccountName.getText().toLowerCase()) && 
                            clsPasswordAESCryptography.mDecryptPassword(strAccountPassword).toLowerCase().
                                equals(txtAccountPassword.getText().toLowerCase()))
                        {
                            frmDesktop frmDsktop = new frmDesktop();
                            frmDsktop.mSetUserIdentifier("Admin");
                            frmDsktop.mUAC();
                            frmDsktop.setVisible(true);
                            this.setVisible(false);
                            frmDsktop.setExtendedState(MAXIMIZED_BOTH);
                        }else{
                            JOptionPane.showMessageDialog(null, "Access Denied!! Check account name or password");
                        }
                        break;
                    case "No":
                        mAssignVariables(mAuthenticateGeneralUser());
                        if(strAccountName.toLowerCase().equals(txtAccountName.getText().toLowerCase()) && 
                            clsPasswordAESCryptography.mDecryptPassword(strAccountPassword).toLowerCase().
                                equals(txtAccountPassword.getText().toLowerCase()))
                        {
                            frmDesktop frmDsktop = new frmDesktop();
                            frmDsktop.mSetUserIdentifier("GeneralUser");
                            frmDsktop.mUAC();
                            frmDsktop.setVisible(true);
                            this.setVisible(false);
                            frmDsktop.setExtendedState(MAXIMIZED_BOTH);
                        }
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Account does not exist");
                }
            }
        }
        catch(HeadlessException | NullPointerException eX)
        {
            JOptionPane.showMessageDialog(null, "Access Denied!! Check account name or password");
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

        dskpLogin = new javax.swing.JDesktopPane();
        lblPassword = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnCreateAccount = new javax.swing.JButton();
        lblAccountName = new javax.swing.JLabel();
        txtAccountName = new javax.swing.JTextField();
        lblApplicationName = new javax.swing.JLabel();
        txtAccountPassword = new javax.swing.JPasswordField();
        lblAppSlogan = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Developer's Konner :\\>");
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        dskpLogin.setBackground(new java.awt.Color(255, 255, 255));
        dskpLogin.setForeground(new java.awt.Color(255, 255, 255));
        dskpLogin.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N

        lblPassword.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Password");
        dskpLogin.add(lblPassword);
        lblPassword.setBounds(350, 350, 150, 29);

        btnLogin.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        btnLogin.setText("login");
        btnLogin.setMaximumSize(new java.awt.Dimension(215, 37));
        btnLogin.setMinimumSize(new java.awt.Dimension(215, 37));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        dskpLogin.add(btnLogin);
        btnLogin.setBounds(850, 490, 230, 37);

        btnCreateAccount.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        btnCreateAccount.setText("Create Account");
        btnCreateAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAccountActionPerformed(evt);
            }
        });
        dskpLogin.add(btnCreateAccount);
        btnCreateAccount.setBounds(350, 500, 230, 37);

        lblAccountName.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblAccountName.setForeground(new java.awt.Color(255, 255, 255));
        lblAccountName.setText("Account name");
        dskpLogin.add(lblAccountName);
        lblAccountName.setBounds(350, 240, 187, 56);

        txtAccountName.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        txtAccountName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAccountNameActionPerformed(evt);
            }
        });
        dskpLogin.add(txtAccountName);
        txtAccountName.setBounds(830, 250, 250, 32);

        lblApplicationName.setFont(new java.awt.Font("Consolas", 1, 48)); // NOI18N
        lblApplicationName.setForeground(new java.awt.Color(255, 255, 255));
        lblApplicationName.setText("   Developer's Den :\\>");
        dskpLogin.add(lblApplicationName);
        lblApplicationName.setBounds(400, 60, 710, 100);
        dskpLogin.add(txtAccountPassword);
        txtAccountPassword.setBounds(830, 350, 250, 32);

        lblAppSlogan.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblAppSlogan.setForeground(new java.awt.Color(240, 240, 240));
        lblAppSlogan.setText("   Home of The Competent Developer");
        dskpLogin.add(lblAppSlogan);
        lblAppSlogan.setBounds(460, 130, 500, 30);

        lblBackground.setIcon(new javax.swing.ImageIcon("C:\\Users\\Sanele\\Documents\\Information Technology\\Project_iii_Systems\\A\\Developer's Konner.png")); // NOI18N
        dskpLogin.add(lblBackground);
        lblBackground.setBounds(0, 0, 1370, 700);

        getContentPane().add(dskpLogin);
        dskpLogin.setBounds(0, 0, 1400, 700);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        mLogin();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void txtAccountNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccountNameActionPerformed
        //
    }//GEN-LAST:event_txtAccountNameActionPerformed

    private void btnCreateAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAccountActionPerformed
        jifrmCreateAccount jifrmC = new jifrmCreateAccount();
        jifrmC.setLocation(500, 160);
        dskpLogin.add(jifrmC);
        jifrmC.setVisible(true);
    }//GEN-LAST:event_btnCreateAccountActionPerformed

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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateAccount;
    private javax.swing.JButton btnLogin;
    private javax.swing.JDesktopPane dskpLogin;
    private javax.swing.JLabel lblAccountName;
    private javax.swing.JLabel lblAppSlogan;
    private javax.swing.JLabel lblApplicationName;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JTextField txtAccountName;
    private javax.swing.JPasswordField txtAccountPassword;
    // End of variables declaration//GEN-END:variables
}
