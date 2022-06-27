/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crm.client.util;

import gui.gastos.SubcontratacionTodo;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author sergio
 */
public class Verificador extends InputVerifier {
 
    public boolean verify(JComponent editor) {
        if (editor instanceof JTextField) {
            String clave = ((JTextField) editor).getText();
            try {
                Double.parseDouble(clave);
                return true;

            } catch (Exception e) {
                ((JTextField) editor).setText("0.00");
                JOptionPane.showMessageDialog(null, "Valor invalido en el campo. Ingreselo correctamente", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
}
