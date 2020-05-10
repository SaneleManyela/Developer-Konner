/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template PDF, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.TresPlataformas;

/**
 *
 * @author Sanele
 */
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.icepdf.ri.common.ComponentKeyBinding;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

public class frmeLibrary extends javax.swing.JFrame {

    /**
     * Creates new form frmeLibrary
     */
    public frmeLibrary() {
        initComponents();
        frmDesktop frmDsktp = new frmDesktop();
        if(frmDsktp.mGetUserIdentifier().equals("Admin")){
            mnuDiscussionForums.setVisible(false);
        }
        jBooksScrollPane.setVisible(false);
    }
    
    clsConnectToDatabase clsConnect = new clsConnectToDatabase();
    clsDatabaseQueryingMethods clsQueryingMethods = new clsDatabaseQueryingMethods();
    clsRemoveEmptyIndexes<String> clsRemoveEmptyStrIndexes = new clsRemoveEmptyIndexes<>();
    clsRemoveEmptyIndexes<Blob> clsRemoveEmptyBlobIndexes = new clsRemoveEmptyIndexes<>();
    
    private File Path()
    {
        File filePath = new java.io.File("C:\\Users\\Sanele\\Documents\\"
                + "Information Technology\\Project_iii_Systems\\"
                + "Projeto TresPlataformas\\src\\Projeto\\TresPlataformas\\"
                + "Books");
        return filePath;
    }
        
    private String mGeteBooks(String strCategory)
    {
        return "SELECT eBook FROM tblBooks WHERE Category='"+strCategory+"'";
    }
    private String mGetTitles(String strCategory)
    {
        return "SELECT BookTitle FROM tblBooks WHERE Category='"+strCategory+"'";
    }
    private String mGetTitlesCount(String strCategory)
    {
        return "SELECT COUNT(BookTitle) FROM tblBooks WHERE Category='"+strCategory+"'";
    }
    /**
    private String mGetCovers(String strCategory)
    {
        return "SELECT BookCover FROM tblBooks WHERE Category='"+strCategory+"'";
    }
    private String mGetCoverCount(String strCategory)
    {
        return "SELECT COUNT(BookCover) FROM tblBooks WHERE Category='"+strCategory+"'";
    }
    */
    
    private Blob[] mFetchBinaryData(String strQuery, String strCountQuery)
    {
        Blob[] arrRecordDetails = null;
        try
        {
            try (Statement stStatement = clsConnect.mConnectToDatabaseDeveloperKonner().prepareStatement(strQuery)) {
                stStatement.execute(strQuery);
                try (ResultSet rs = stStatement.getResultSet()) {
                    arrRecordDetails = new Blob[clsQueryingMethods.mGetNumberOfRecords(strCountQuery)+1];
                    while(rs.next())
                    {
                        for(int i = 1; i < arrRecordDetails.length; i++){
                            arrRecordDetails[i] = rs.getBlob(i);
                        }
                    }
                    rs.close();
                    stStatement.close();
                }
            }
	}
        catch(SQLException eX)
        {
            JOptionPane.showMessageDialog(null, eX);
        }
        return arrRecordDetails;
    }
       
    private String[] mGetCategoryBookTitles(String strQuery, int intBookTitleCount)
    {
        String[] bookTitles = null;
        try
        {
            try (Statement stStatement = clsConnect.mConnectToDatabaseDeveloperKonner().prepareStatement(strQuery); ResultSet rs = stStatement.executeQuery(strQuery)) {
                bookTitles = new String[intBookTitleCount + 1];
                while(rs.next()){
                    for(int i = 1; i < bookTitles.length; i++ ){
                        bookTitles[i] = rs.getString(i);
                    }
                }
                stStatement.close();
                rs.close();
            }
        }
        catch(SQLException eX)
        {
            JOptionPane.showMessageDialog(null, eX);
        }
        return bookTitles;
    }
    
    File PDF;
    // Check if the contact exists
    public void mPDFFileOperations(String fileName, Blob eBook)
    {
        PDF = new java.io.File(fileName+".pdf");
        try{
            PDF.createNewFile();
        }
        catch(IOException eX){
            JOptionPane.showMessageDialog(null, "Technical error encountered\n"+eX);
        }
        if(PDF.exists()){
            mOpenPDF(PDF);
        }else{
            mWritePDFFile(PDF, eBook);
            mOpenPDF(PDF);
        }
    }
    //Write Binary data to the PDF
    private void mWritePDFFile(File file, Blob eBook)
    {
        try{
            try (InputStream is = eBook.getBinaryStream()) {
                try (FileOutputStream fout = new FileOutputStream(file)) {
                    int b;
                    while((b = is.read()) != -1){
                        fout.write(b);
                    }
                    fout.close();
                }
                is.close();
            }
        }
        catch(IOException  | SQLException eX)
        {
            JOptionPane.showMessageDialog(null, eX);
        }
    }
        
    private void mOpenPDF(File file)
    {
        try
        {
            SwingController control = new SwingController();
            SwingViewBuilder factry = new SwingViewBuilder(control);
            JPanel viewerCompntpnl = factry.buildViewerPanel();
            ComponentKeyBinding.install(control, viewerCompntpnl);
            control.getDocumentViewController().setAnnotationCallback(
            new org.icepdf.ri.common.MyAnnotationCallback(control.getDocumentViewController()));
            jBooksScrollPane.setVisible(true);
            jBooksScrollPane.setViewportView(viewerCompntpnl);
            control.openDocument(file.toString());
        }
        catch(Exception eX)
        {
            JOptionPane.showMessageDialog(null, "An error occured while trying to open the file");
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

        dskLibraryDesktop = new javax.swing.JDesktopPane();
        lblBackground = new javax.swing.JLabel();
        jBooksScrollPane = new javax.swing.JScrollPane();
        mnuLibrary = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mnuitemHome = new javax.swing.JMenuItem();
        mnuELibrary = new javax.swing.JMenu();
        mnuItemAlgorithms = new javax.swing.JMenuItem();
        mnuItemArtificialIntelligence = new javax.swing.JMenuItem();
        mnuItemCloudComputing = new javax.swing.JMenuItem();
        mnuItemCyberSecurity = new javax.swing.JMenuItem();
        mnuItemDataScience = new javax.swing.JMenuItem();
        mnuItemDatabaseProgramming = new javax.swing.JMenuItem();
        mnuItemIoT = new javax.swing.JMenuItem();
        mnuItemLinux = new javax.swing.JMenuItem();
        mnuOOP = new javax.swing.JMenu();
        mnuItemCSharp = new javax.swing.JMenuItem();
        mnuItemJava = new javax.swing.JMenuItem();
        mnuItemPython = new javax.swing.JMenuItem();
        mnuWebDevelopment = new javax.swing.JMenu();
        mnuItemHTMLandCSS = new javax.swing.JMenuItem();
        mnuItemJavaScript = new javax.swing.JMenuItem();
        mnuItemPHP = new javax.swing.JMenuItem();
        mnuDiscussionForums = new javax.swing.JMenu();
        mnuItemDiscussionForum = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Developer's Konner :\\>");
        getContentPane().setLayout(null);

        dskLibraryDesktop.setBackground(new java.awt.Color(255, 255, 255));

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/TresPlataformas/e-Library.jpg"))); // NOI18N
        lblBackground.setText("  ");
        dskLibraryDesktop.add(lblBackground);
        lblBackground.setBounds(0, -120, 1380, 1010);
        dskLibraryDesktop.add(jBooksScrollPane);
        jBooksScrollPane.setBounds(0, 0, 1390, 890);

        getContentPane().add(dskLibraryDesktop);
        dskLibraryDesktop.setBounds(0, 0, 1390, 890);

        mnuLibrary.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N

        mnuFile.setText("File");
        mnuFile.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N

        mnuitemHome.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuitemHome.setText("Home");
        mnuitemHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuitemHomeActionPerformed(evt);
            }
        });
        mnuFile.add(mnuitemHome);

        mnuLibrary.add(mnuFile);

        mnuELibrary.setText("e-Library");
        mnuELibrary.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N

        mnuItemAlgorithms.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemAlgorithms.setText("Algorithms");
        mnuItemAlgorithms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemAlgorithmsActionPerformed(evt);
            }
        });
        mnuELibrary.add(mnuItemAlgorithms);

        mnuItemArtificialIntelligence.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemArtificialIntelligence.setText("Artificial Intelligence");
        mnuELibrary.add(mnuItemArtificialIntelligence);

        mnuItemCloudComputing.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemCloudComputing.setText("Cloud Computing");
        mnuItemCloudComputing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemCloudComputingActionPerformed(evt);
            }
        });
        mnuELibrary.add(mnuItemCloudComputing);

        mnuItemCyberSecurity.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemCyberSecurity.setText("Cyber Security");
        mnuELibrary.add(mnuItemCyberSecurity);

        mnuItemDataScience.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemDataScience.setText("Data Science");
        mnuELibrary.add(mnuItemDataScience);

        mnuItemDatabaseProgramming.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemDatabaseProgramming.setText("Database Programming");
        mnuELibrary.add(mnuItemDatabaseProgramming);

        mnuItemIoT.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemIoT.setText("Internet of Things");
        mnuELibrary.add(mnuItemIoT);

        mnuItemLinux.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemLinux.setText("Linux");
        mnuELibrary.add(mnuItemLinux);

        mnuOOP.setText("Object Oriented Programming");
        mnuOOP.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N

        mnuItemCSharp.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemCSharp.setText("C#");
        mnuOOP.add(mnuItemCSharp);

        mnuItemJava.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemJava.setText("Java");
        mnuItemJava.setToolTipText("");
        mnuOOP.add(mnuItemJava);

        mnuItemPython.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemPython.setText("Python");
        mnuOOP.add(mnuItemPython);

        mnuELibrary.add(mnuOOP);

        mnuWebDevelopment.setText("Web Development");
        mnuWebDevelopment.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N

        mnuItemHTMLandCSS.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemHTMLandCSS.setText("HTML & CSS");
        mnuWebDevelopment.add(mnuItemHTMLandCSS);

        mnuItemJavaScript.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemJavaScript.setText("JavaScript");
        mnuWebDevelopment.add(mnuItemJavaScript);

        mnuItemPHP.setText("PHP");
        mnuWebDevelopment.add(mnuItemPHP);

        mnuELibrary.add(mnuWebDevelopment);

        mnuLibrary.add(mnuELibrary);

        mnuDiscussionForums.setText("Discussion Forums");
        mnuDiscussionForums.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N

        mnuItemDiscussionForum.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        mnuItemDiscussionForum.setText("Open Forum");
        mnuItemDiscussionForum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuItemDiscussionForumActionPerformed(evt);
            }
        });
        mnuDiscussionForums.add(mnuItemDiscussionForum);

        mnuLibrary.add(mnuDiscussionForums);

        setJMenuBar(mnuLibrary);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void mnuItemAlgorithmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemAlgorithmsActionPerformed
        Blob[] algorithmeBooks = clsRemoveEmptyBlobIndexes.mRemoveEmptyIndexes(mFetchBinaryData(mGeteBooks(mnuItemAlgorithms.getText()),
                mGetTitlesCount(mnuItemAlgorithms.getText()))).toArray(new Blob[
                clsRemoveEmptyBlobIndexes.mRemoveEmptyIndexes(mFetchBinaryData(mGeteBooks(mnuItemAlgorithms.getText()),
                mGetTitlesCount(mnuItemAlgorithms.getText()))).size()]);
        
        String[] algorithmeBooksTitles = clsRemoveEmptyStrIndexes.mRemoveEmptyIndexes(mGetCategoryBookTitles(mGetTitles(mnuItemAlgorithms.getText()),
                clsQueryingMethods.mGetNumberOfRecords(mGetTitlesCount(mnuItemAlgorithms.getText())))).toArray(new String[
                clsRemoveEmptyStrIndexes.mRemoveEmptyIndexes(mGetCategoryBookTitles(mGetTitles(mnuItemAlgorithms.getText()),
                clsQueryingMethods.mGetNumberOfRecords(mGetTitlesCount(mnuItemAlgorithms.getText())))).size()]);
        
        jifrmeBookChooser jifrmeChooser = new jifrmeBookChooser();
        jifrmeChooser.mSetUpCategoryeBookResults(algorithmeBooksTitles, algorithmeBooks);
        dskLibraryDesktop.add(jifrmeChooser);
        jifrmeChooser.show();
    }//GEN-LAST:event_mnuItemAlgorithmsActionPerformed

    private void mnuitemHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuitemHomeActionPerformed
        frmDesktop frmDsktop = new frmDesktop();
        frmDsktop.mUAC();
        frmDsktop.setVisible(true);
        this.hide();
        frmDsktop.setExtendedState(MAXIMIZED_BOTH);
    }//GEN-LAST:event_mnuitemHomeActionPerformed

    private void mnuItemDiscussionForumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemDiscussionForumActionPerformed
        String url = "http://localhost/Projeto%20TresPlataformas%20Forum/index.php";
        if(Desktop.isDesktopSupported() &&
                Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)){
            Desktop desktop = Desktop.getDesktop();
            try{
                desktop.browse(new URI(url));
            }catch(IOException | URISyntaxException eX){
                JOptionPane.showMessageDialog(null,"A technical error has been encountered\n"+ eX);
            }
        }
        else{
            Runtime runtime = Runtime.getRuntime();
            try{
                runtime.exec("xdg-open " + url);
            }catch(IOException eX){
                JOptionPane.showMessageDialog(null,"A technical error has been encountered\n"+ eX);
            }
        }
    }//GEN-LAST:event_mnuItemDiscussionForumActionPerformed

    private void mnuItemCloudComputingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuItemCloudComputingActionPerformed
        Blob[] cloudComputingeBooks = clsRemoveEmptyBlobIndexes.mRemoveEmptyIndexes(mFetchBinaryData(mGeteBooks(mnuItemCloudComputing.getText()),
                mGetTitlesCount(mnuItemCloudComputing.getText()))).toArray(new Blob[
                clsRemoveEmptyBlobIndexes.mRemoveEmptyIndexes(mFetchBinaryData(mGeteBooks(mnuItemCloudComputing.getText()),
                mGetTitlesCount(mnuItemCloudComputing.getText()))).size()]);
        
        String[] cloudComputingeBooksTitles = clsRemoveEmptyStrIndexes.mRemoveEmptyIndexes(mGetCategoryBookTitles(mGetTitles(mnuItemCloudComputing.getText()),
                clsQueryingMethods.mGetNumberOfRecords(mGetTitlesCount(mnuItemCloudComputing.getText())))).toArray(new String[
                clsRemoveEmptyStrIndexes.mRemoveEmptyIndexes(mGetCategoryBookTitles(mGetTitles(mnuItemCloudComputing.getText()),
                clsQueryingMethods.mGetNumberOfRecords(mGetTitlesCount(mnuItemCloudComputing.getText())))).size()]);
        
        jifrmeBookChooser jifrmeChooser = new jifrmeBookChooser();
        jifrmeChooser.mSetUpCategoryeBookResults(cloudComputingeBooksTitles, cloudComputingeBooks);
        dskLibraryDesktop.add(jifrmeChooser);
        jifrmeChooser.show();
    }//GEN-LAST:event_mnuItemCloudComputingActionPerformed

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
            java.util.logging.Logger.getLogger(frmeLibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmeLibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmeLibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmeLibrary.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmeLibrary().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dskLibraryDesktop;
    private javax.swing.JScrollPane jBooksScrollPane;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JMenu mnuDiscussionForums;
    private javax.swing.JMenu mnuELibrary;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenuItem mnuItemAlgorithms;
    private javax.swing.JMenuItem mnuItemArtificialIntelligence;
    private javax.swing.JMenuItem mnuItemCSharp;
    private javax.swing.JMenuItem mnuItemCloudComputing;
    private javax.swing.JMenuItem mnuItemCyberSecurity;
    private javax.swing.JMenuItem mnuItemDataScience;
    private javax.swing.JMenuItem mnuItemDatabaseProgramming;
    private javax.swing.JMenuItem mnuItemDiscussionForum;
    private javax.swing.JMenuItem mnuItemHTMLandCSS;
    private javax.swing.JMenuItem mnuItemIoT;
    private javax.swing.JMenuItem mnuItemJava;
    private javax.swing.JMenuItem mnuItemJavaScript;
    private javax.swing.JMenuItem mnuItemLinux;
    private javax.swing.JMenuItem mnuItemPHP;
    private javax.swing.JMenuItem mnuItemPython;
    private javax.swing.JMenuBar mnuLibrary;
    private javax.swing.JMenu mnuOOP;
    private javax.swing.JMenu mnuWebDevelopment;
    private javax.swing.JMenuItem mnuitemHome;
    // End of variables declaration//GEN-END:variables
}
