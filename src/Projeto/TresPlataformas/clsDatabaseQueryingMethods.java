/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.TresPlataformas;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Sanele
 */
public class clsDatabaseQueryingMethods {
    clsConnectToDatabase clsConnect = new clsConnectToDatabase();
    
    public boolean mCheckIfRecordExists(String strQuery)
    {
        boolean boolAccountExists = false;
        Statement stStatement;
        ResultSet rs;
        try
        {
            stStatement = clsConnect.mConnectToDatabaseDeveloperKonner().prepareStatement(strQuery);
            stStatement.execute(strQuery);
            rs = stStatement.getResultSet();
            boolAccountExists = rs.next();
            stStatement.close();
            rs.close();
        }
        catch(SQLException eX)
        {
            JOptionPane.showMessageDialog(null, "The application has encountered a technical error\n"+eX.getMessage());
        }
        return boolAccountExists;
    }
    
    public void mCreateRecord(String strQuery)
    {
        try
        {
            try (Statement stStatement = clsConnect.mConnectToDatabaseDeveloperKonner().prepareStatement(strQuery)) {
                stStatement.executeUpdate(strQuery);
                stStatement.close();
            }
	}
        catch(SQLException eX)
        {
            JOptionPane.showMessageDialog(null, " The record could not be created\n"+eX.getMessage());
        }
    }
    
    public int mGetNumberOfRecords(String strQuery)
    {
        int count = 0;
        try
        {
            try (Statement stStatement = clsConnect.mConnectToDatabaseDeveloperKonner().prepareStatement(strQuery)) {
                stStatement.execute(strQuery);
                try (ResultSet rs = stStatement.getResultSet()) {
                    while(rs.next()){
                        count = rs.getInt(1);
                    }
                    stStatement.close();
                    rs.close();
                }
            }
	}
        catch(SQLException | NullPointerException eX){
            JOptionPane.showMessageDialog(null, "Technical error has been encounterd\n"+eX.getMessage());
        }
       return count;
    }
    static String[] arrRecordDetails = null;
    public String[] mFetchAccountRecordDetails(String strQuery)
    {
        try
        {
            try (Statement stStatement = clsConnect.mConnectToDatabaseDeveloperKonner().prepareStatement(strQuery)) {
                stStatement.execute(strQuery);
                try (ResultSet rs = stStatement.getResultSet()) {
                    ResultSetMetaData rsmt = rs.getMetaData();
                    arrRecordDetails = new String[rsmt.getColumnCount()+1];
                    while(rs.next())
                    {
                        for(int i = 1; i < arrRecordDetails.length; i++){
                            arrRecordDetails[i] = rs.getString(i);                    
                        }
                    }
                    stStatement.close();
                    rs.close();
                }
                return arrRecordDetails;
            }
	}
        catch(SQLException eX)
        {
            JOptionPane.showMessageDialog(null, eX.getMessage());
        }
        return null;
    }
    
    public void mDisplayInTable(String strQuery, JInternalFrame frm)
    {
        try(Statement stStatement = clsConnect.mConnectToDatabaseDeveloperKonner().prepareStatement(strQuery)){
            JPanel pnlTable = new JPanel();
            JTable tblAccounts = new JTable();
            tblAccounts.setModel(DbUtils.resultSetToTableModel(stStatement.executeQuery(strQuery)));
            tblAccounts.setFillsViewportHeight(true);
            JScrollPane jspAccountsPane = new JScrollPane(tblAccounts);
            tblAccounts.setVisible(true);
            tblAccounts.validate();
            pnlTable.setLayout(new GridLayout(1,0));
            pnlTable.add(jspAccountsPane);
            frm.setResizable(true);
            frm.setContentPane(pnlTable);
        }catch(SQLException eX){
            JOptionPane.showMessageDialog(null, eX.getMessage());
        }
    }
    
    public void mUpdateAccountDetails(String strQuery)
    {
        try
        {
            try (Statement stStatement = clsConnect.mConnectToDatabaseDeveloperKonner().prepareStatement(strQuery)) {
                stStatement.executeUpdate(strQuery);
                stStatement.close();
            }
        }
        catch(HeadlessException | SQLException eX)
        {
            JOptionPane.showMessageDialog(null, "Technical error, updated transaction could not be finished\n."+eX);
        }
    }
    
    public void mDeleteAccountDetails(String strQuery)
    {
        try
        {
            try (Statement stStatement = clsConnect.mConnectToDatabaseDeveloperKonner().prepareStatement(strQuery)) {
                stStatement.execute(strQuery);
                stStatement.close();
            }
        }
        catch(HeadlessException | SQLException eX)
        {
            JOptionPane.showMessageDialog(null, "Technical error, deleted transaction could not be finished.");
        }
    }
}