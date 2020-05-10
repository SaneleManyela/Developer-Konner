/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.TresPlataformas;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Blob;
import javax.swing.JButton;

/**
 *
 * @author Sanele
 */
public class jifrmeBookChooser extends javax.swing.JInternalFrame {

    /**
     * Creates new form jifrmeBookChooser
     */
    public jifrmeBookChooser() {
        initComponents();
    }
    frmeLibrary frmebrary = new frmeLibrary();
    private void mButtonEvent(JButton btn, String[] arrBookTitles, Blob[] arreBooks)
    {
       btn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               //File Writing and evoking of mOpenPDF
               int index = 0;
               for(int i = 0; i < arrBookTitles.length; i++){
                   index = arrBookTitles[i].indexOf(btn.getText());
               }
               frmebrary.mPDFFileOperations(arrBookTitles[index], arreBooks[index]);
           }
       });
       this.hide();
    }
    
    public void mSetUpCategoryeBookResults(String[] arrBookTitles, Blob[] arreBooks)
    {        
        GridLayout grid = new GridLayout(5, 3, 10, 10);
        this.setLayout(grid);
        
        JButton[] arrBtn = new JButton[arrBookTitles.length];
        for(int i = 0; i < arrBtn.length; i++){
            arrBtn[i] = new JButton(arrBookTitles[i]);
            mButtonEvent(arrBtn[i], arrBookTitles, arreBooks);
            /**try{
                Image img = ImageIO.read(getClass().getResource("TresPlataformas/Books.jpg"));
                arrBtn[i].setIcon(new ImageIcon(img));
            } catch (IOException eX) {
                JOptionPane.showMessageDialog(null, eX);
            }
            */
            this.add(arrBtn[i]);
        }
        this.setLocation(500, 160);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dsktpeBooks = new javax.swing.JDesktopPane();

        setClosable(true);
        setTitle("eBooks In the Category");
        setMaximumSize(new java.awt.Dimension(502, 467));
        setMinimumSize(new java.awt.Dimension(502, 467));

        dsktpeBooks.setBackground(new java.awt.Color(255, 255, 255));
        dsktpeBooks.setForeground(new java.awt.Color(255, 255, 255));
        dsktpeBooks.setMaximumSize(new java.awt.Dimension(502, 467));
        dsktpeBooks.setMinimumSize(new java.awt.Dimension(486, 433));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dsktpeBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dsktpeBooks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dsktpeBooks;
    // End of variables declaration//GEN-END:variables
}
