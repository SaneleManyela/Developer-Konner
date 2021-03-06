/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.TresPlataformas;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Sanele
 */
public class jifrmHelp extends javax.swing.JInternalFrame {

    /**
     * Creates new form jifrmHelp
     */
    public jifrmHelp() {
        initComponents();
        mJIFrmHelpDefaultControls();
        mContacts();
    }
    
    private void mJIFrmHelpDefaultControls()
    {
        txtpReportIssue.setEnabled(false);
        txtpContacts.setEditable(false);
    }
    
    private String mDefaultDate()
    {
        Date dt = new Date();
        SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
        return sm.format(dt);
    }
  
    private void mContacts()
    {
        String Contacts = "Contact me on Facebook @: Sanele Manyela      "
                + "Email me @        "
                + "smanyela44@gmail.com";
        txtpContacts.setText(Contacts);
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
        jTextPane1 = new javax.swing.JTextPane();
        dsktopHelp = new javax.swing.JDesktopPane();
        jspContactDetails = new javax.swing.JScrollPane();
        txtpContacts = new javax.swing.JTextPane();
        btnUpdates = new javax.swing.JButton();
        btnReport = new javax.swing.JButton();
        jspReportIssue = new javax.swing.JScrollPane();
        txtpReportIssue = new javax.swing.JTextPane();
        lblBackground = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jTextPane1);

        setClosable(true);
        setTitle("Report Problems");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/TresPlataformas/e-Library.jpg"))); // NOI18N

        dsktopHelp.setBackground(new java.awt.Color(255, 255, 255));

        jspContactDetails.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jspContactDetails.setName(""); // NOI18N

        txtpContacts.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jspContactDetails.setViewportView(txtpContacts);

        dsktopHelp.add(jspContactDetails);
        jspContactDetails.setBounds(10, 11, 173, 252);

        btnUpdates.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        btnUpdates.setText("Updates");
        dsktopHelp.add(btnUpdates);
        btnUpdates.setBounds(214, 240, 90, 23);

        btnReport.setFont(new java.awt.Font("Consolas", 1, 12)); // NOI18N
        btnReport.setText("Report");
        btnReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportActionPerformed(evt);
            }
        });
        dsktopHelp.add(btnReport);
        btnReport.setBounds(214, 116, 90, 23);

        txtpReportIssue.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        txtpReportIssue.setText("Report Problems");
        txtpReportIssue.setName(""); // NOI18N
        jspReportIssue.setViewportView(txtpReportIssue);

        dsktopHelp.add(jspReportIssue);
        jspReportIssue.setBounds(214, 11, 158, 99);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/TresPlataformas/Light-dots-with-blue-tech-background-vector-09.jpg"))); // NOI18N
        dsktopHelp.add(lblBackground);
        lblBackground.setBounds(0, 0, 380, 270);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dsktopHelp, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dsktopHelp, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportActionPerformed
        frmLogin frmlogin = new frmLogin();
        
        switch(btnReport.getText())
        {
            case "Report": txtpReportIssue.setEnabled(true); txtpReportIssue.setText(""); btnReport.setText("Submit"); break;
            case "Submit":
                clsDatabaseQueryingMethods clsQueryingMethods = new clsDatabaseQueryingMethods();
                if(!(txtpReportIssue.getText().equals(""))){
                    clsQueryingMethods.mCreateRecord("INSERT INTO tblApplicationIssues " +
                        "(IssueDescription,reportingUserID, reportDate)" + 
                        "VALUES ('" +frmlogin.mGetUserID()+ "','" + txtpReportIssue.getText()+"','"+mDefaultDate()+"')");
                    JOptionPane.showMessageDialog(null, "The Application Issue was reported");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Issue description is required.");
                    txtpReportIssue.requestFocusInWindow();
                }
                btnReport.setText("Report"); break;
        }
    }//GEN-LAST:event_btnReportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReport;
    private javax.swing.JButton btnUpdates;
    private javax.swing.JDesktopPane dsktopHelp;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JScrollPane jspContactDetails;
    private javax.swing.JScrollPane jspReportIssue;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JTextPane txtpContacts;
    private javax.swing.JTextPane txtpReportIssue;
    // End of variables declaration//GEN-END:variables
}
