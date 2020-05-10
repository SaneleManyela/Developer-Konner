/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Projeto.TresPlataformas;

import static java.awt.Frame.MAXIMIZED_BOTH;

/**
 *
 * @author Sanele
 */
public class ProjetoTresPlataformas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        frmLogin frmlogin = new frmLogin();
        frmlogin.show();
        frmlogin.setTitle("Developer Konner :/>");
        frmlogin.setExtendedState(MAXIMIZED_BOTH);
    }
    
}
