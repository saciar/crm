/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.gui;

import gui.Login;
import gui.LoginForm;

/**
 *
 * @author saciar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               // new Login().setVisible(true);
            	new LoginForm().setVisible(true);
            }
        });
    }

}
