/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.TresPlataformas;

import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Sanele
 */
public class jifrmLibraryManagement extends javax.swing.JInternalFrame {

    /**
     * Creates new form jifrmLibraryManagement
     */
    public jifrmLibraryManagement() {
        initComponents();
        mDefaultGUIControls();
    }
    
    private void mDefaultGUIControls()
    {
        txtISBNCode.setEnabled(false);
        txtBookTitle.setEnabled(false);
        txtBookAuthor.setEnabled(false);
        cboCategory.setEnabled(false);
        cboBookTitles.setEnabled(true);
        btnAddBook.setEnabled(true);
        btnUpdate.setEnabled(true);
        btnSaveBook.setEnabled(false);
    }
    
    private void mAddBookGUIControls()
    {
        txtISBNCode.setEnabled(true);
        txtBookTitle.setEnabled(true);
        txtBookAuthor.setEnabled(true);
        cboCategory.setEnabled(true);
        cboBookTitles.setEnabled(false);
        btnAddBook.setEnabled(true);
        btnUpdate.setEnabled(false);
        btnSaveBook.setEnabled(true);
    }
    
    private void mUpdateGUIControls()
    {
        txtISBNCode.setEnabled(true);
        txtBookTitle.setEnabled(true);
        txtBookAuthor.setEnabled(true);
        cboCategory.setEnabled(true);
        cboBookTitles.setEnabled(false);
        btnAddBook.setEnabled(false);
        btnUpdate.setEnabled(true);
        btnSaveBook.setEnabled(true);
    }
    
    private void mSaveGUIControls()
    {
        mDefaultGUIControls();
    }
    
    private void mSetValuesInGUI(String[] arrBookDetails)
    {
        txtISBNCode.setText(arrBookDetails[1]);
        txtBookTitle.setText(arrBookDetails[2]);
        txtBookAuthor.setText(arrBookDetails[3]);
        cboCategory.setSelectedItem(arrBookDetails[4]);
    }
    
    private void mClearComboBox()
    {
        String[] arrArray = new String[0];
        javax.swing.DefaultComboBoxModel model = new javax.swing.DefaultComboBoxModel(arrArray);
        cboBookTitles.setModel(model);
    }
    
    private void mClearGUITextBoxes()
    {
        txtISBNCode.setText("");
        txtBookTitle.setText("");
        txtBookAuthor.setText("");
    }
    
    boolean boolBookExist = false;
    clsDatabaseQueryingMethods clsQueryingMethods = new clsDatabaseQueryingMethods();
    clsConnectToDatabase clsConnect = new clsConnectToDatabase();
    frmLogin frmlogin = new frmLogin();
    
    private File Path()
    {
        File filePath = new java.io.File("C:\\Users\\Sanele\\Documents\\"
                + "Information Technology");
        return filePath;
    }
    
    private byte[] mGetPdf()
    {
        final JFileChooser fc = new JFileChooser(".");
        fc.setCurrentDirectory(Path());
        int status = fc.showOpenDialog(null);
        File PdfFile = new File("");
                
        if(status == JFileChooser.APPROVE_OPTION)
        {
            PdfFile = fc.getSelectedFile();
        }
        else if(status == JFileChooser.CANCEL_OPTION)
        {
            fc.cancelSelection();
        }
        Path pdfPath = Paths.get(PdfFile.getPath());
        byte[] pdfData = new byte[(int)PdfFile.length()];
        try{
            pdfData =  Files.readAllBytes(pdfPath);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        try(DataInputStream dis = new DataInputStream(new FileInputStream(PdfFile)))
        {
            dis.readFully(pdfData);
            dis.close();
        }
        catch(IOException eX)
        {
            JOptionPane.showMessageDialog(null, eX);
        }     
        return pdfData;
     }
    
    private void mUploadBook()
    {
        try
        {
            String sqlInsert = "INSERT INTO tblBooks " + "(BookISBN, BookTitle, Author, Category, eBook, AdminID)" + 
                    "VALUES (?,?,?,?,?,?)";
            try (PreparedStatement pstmt = (PreparedStatement)clsConnect.mConnectToDatabaseDeveloperKonner().prepareStatement(sqlInsert)) {
                pstmt.setString(1, txtISBNCode.getText());
                pstmt.setString(2, txtBookTitle.getText());
                pstmt.setString(3, txtBookAuthor.getText());
                pstmt.setString(4, cboCategory.getSelectedItem().toString());
                pstmt.setBytes(5,mGetPdf());
                pstmt.setString(6, String.valueOf(frmlogin.mGetUserID()));
                pstmt.executeUpdate();
                pstmt.close();
            }
            JOptionPane.showMessageDialog(null, "New book Added");
	}
        catch(HeadlessException | SQLException eX)
        {
            JOptionPane.showMessageDialog(null, "The book could not be added" +""+eX);
        }
    }
    
    private void mSaveBookDetails()
    {
        if(txtISBNCode.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Book ISBN Code field cannot be left empty");
            txtISBNCode.requestFocusInWindow();
        }
        else if(txtBookTitle.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Book title field cannot be left empty");
            txtBookTitle.requestFocusInWindow();
        }
        else if(txtBookAuthor.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Book author field cannot be left empty");
            txtBookAuthor.requestFocusInWindow();
        }
        
        boolBookExist = clsQueryingMethods.mCheckIfRecordExists("SELECT * FROM tblBooks WHERE BookISBN='"+
                txtISBNCode.getText()+"' AND BookTitle='"+txtBookTitle.getText()+"' AND Author='"+
                txtBookAuthor.getText()+"'");
        
        if(boolBookExist == true){
            boolBookExist = false;
            JOptionPane.showMessageDialog(null, "Book already added.");
      	}
        else if(boolBookExist == false){
            mUploadBook();
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
        cboBookTitles = new javax.swing.JComboBox<>();
        lblISBNCode = new javax.swing.JLabel();
        txtISBNCode = new javax.swing.JTextField();
        lblBookTitle = new javax.swing.JLabel();
        txtBookTitle = new javax.swing.JTextField();
        lblBookAuthor1 = new javax.swing.JLabel();
        txtBookAuthor = new javax.swing.JTextField();
        lblBookCategory = new javax.swing.JLabel();
        cboCategory = new javax.swing.JComboBox<>();
        btnAddBook = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnSaveBook = new javax.swing.JButton();
        lblBackground = new javax.swing.JLabel();

        setClosable(true);
        setTitle("e-Library Management");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/TresPlataformas/e-Library.jpg"))); // NOI18N
        setMinimumSize(new java.awt.Dimension(484, 475));
        setPreferredSize(new java.awt.Dimension(484, 475));
        getContentPane().setLayout(null);

        lblHeading.setFont(new java.awt.Font("Consolas", 1, 24)); // NOI18N
        lblHeading.setForeground(new java.awt.Color(240, 240, 240));
        lblHeading.setText("e-Library Management");
        getContentPane().add(lblHeading);
        lblHeading.setBounds(110, 20, 270, 30);

        cboBookTitles.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        getContentPane().add(cboBookTitles);
        cboBookTitles.setBounds(40, 70, 400, 20);

        lblISBNCode.setBackground(new java.awt.Color(0, 0, 0));
        lblISBNCode.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblISBNCode.setForeground(new java.awt.Color(240, 240, 240));
        lblISBNCode.setText("ISBN Code");
        getContentPane().add(lblISBNCode);
        lblISBNCode.setBounds(40, 120, 120, 22);
        getContentPane().add(txtISBNCode);
        txtISBNCode.setBounds(280, 110, 160, 30);

        lblBookTitle.setBackground(new java.awt.Color(0, 0, 0));
        lblBookTitle.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblBookTitle.setForeground(new java.awt.Color(240, 240, 240));
        lblBookTitle.setText("Book Title");
        getContentPane().add(lblBookTitle);
        lblBookTitle.setBounds(40, 180, 120, 22);
        getContentPane().add(txtBookTitle);
        txtBookTitle.setBounds(280, 170, 160, 30);

        lblBookAuthor1.setBackground(new java.awt.Color(0, 0, 0));
        lblBookAuthor1.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblBookAuthor1.setForeground(new java.awt.Color(240, 240, 240));
        lblBookAuthor1.setText("Book Author");
        getContentPane().add(lblBookAuthor1);
        lblBookAuthor1.setBounds(40, 230, 120, 22);
        getContentPane().add(txtBookAuthor);
        txtBookAuthor.setBounds(280, 220, 160, 30);

        lblBookCategory.setBackground(new java.awt.Color(0, 0, 0));
        lblBookCategory.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        lblBookCategory.setForeground(new java.awt.Color(240, 240, 240));
        lblBookCategory.setText("Book Category");
        getContentPane().add(lblBookCategory);
        lblBookCategory.setBounds(40, 280, 150, 22);

        cboCategory.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        cboCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Algorithms", "Artificial Intelligence", "Cloud Computing", "Cyber Security", "Data Science", "Database Programming", "Internet of Things", "Linux", "Object Oriented Programming", "Web Development" }));
        cboCategory.setPreferredSize(new java.awt.Dimension(6, 20));
        getContentPane().add(cboCategory);
        cboCategory.setBounds(280, 270, 160, 30);

        btnAddBook.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnAddBook.setText("Add Book");
        btnAddBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBookActionPerformed(evt);
            }
        });
        getContentPane().add(btnAddBook);
        btnAddBook.setBounds(40, 370, 100, 25);

        btnUpdate.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate);
        btnUpdate.setBounds(200, 370, 100, 25);

        btnSaveBook.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        btnSaveBook.setText("Save");
        btnSaveBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveBookActionPerformed(evt);
            }
        });
        getContentPane().add(btnSaveBook);
        btnSaveBook.setBounds(340, 370, 100, 25);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Projeto/TresPlataformas/Light-dots-with-blue-tech-background-vector-09.jpg"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 470, 450);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBookActionPerformed
        mAddBookGUIControls();
        switch(btnAddBook.getText())
        {
            case "Add Book": txtISBNCode.requestFocusInWindow(); btnAddBook.setText("Cancel"); break;
            case "Cancel": mClearGUITextBoxes(); mDefaultGUIControls(); btnAddBook.setText("Add Book"); break;
        }
    }//GEN-LAST:event_btnAddBookActionPerformed

    private void btnSaveBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveBookActionPerformed
       if(btnAddBook.isEnabled()){
           mSaveBookDetails();
           mClearGUITextBoxes();
           btnAddBook.setText("Add Book");
       }else{
           clsQueryingMethods.mUpdateAccountDetails("UPDATE tblBooks SET BookISBN='"+txtISBNCode.getText()+
                   "', BookTitle='"+txtBookTitle.getText()+"', Author='"+txtBookAuthor.getText()+
                   "', Category='"+cboCategory.getSelectedItem().toString()+"'");
           btnUpdate.setText("Update");
           mClearGUITextBoxes();
           JOptionPane.showMessageDialog(null, "Book Details updated.");
       }
       mSaveGUIControls();
    }//GEN-LAST:event_btnSaveBookActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        mUpdateGUIControls();
        switch(btnUpdate.getText())
        {
            case "Update":
                String[] arrBookTitles = clsQueryingMethods.mFetchAccountRecordDetails("SELECT BookTitle FROM tblBooks");
                for (String arrBookTitle : arrBookTitles) {
                    cboBookTitles.addItem(arrBookTitle);
                }
                String[] arrBookDetails = clsQueryingMethods.mFetchAccountRecordDetails("SELECT BookISBN, BookTitle, Author, Category"
                    + " FROM tblBooks WHERE BookTitle='"+cboBookTitles.getSelectedItem().toString()+"'");
                mSetValuesInGUI(arrBookDetails);
                txtISBNCode.requestFocusInWindow();
                btnUpdate.setText("Cancel");
                break;
            case "Cancel": mClearGUITextBoxes(); mClearComboBox(); mDefaultGUIControls(); btnUpdate.setText("Update"); break;
        }
    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBook;
    private javax.swing.JButton btnSaveBook;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboBookTitles;
    private javax.swing.JComboBox<String> cboCategory;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBookAuthor1;
    private javax.swing.JLabel lblBookCategory;
    private javax.swing.JLabel lblBookTitle;
    private javax.swing.JLabel lblHeading;
    private javax.swing.JLabel lblISBNCode;
    private javax.swing.JTextField txtBookAuthor;
    private javax.swing.JTextField txtBookTitle;
    private javax.swing.JTextField txtISBNCode;
    // End of variables declaration//GEN-END:variables
}
