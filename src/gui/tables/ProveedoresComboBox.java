/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;

import crm.client.managers.ProveedorManager;
import crm.libraries.abm.entities.Proveedor;
import java.awt.Component;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author saciar
 */
public class ProveedoresComboBox extends JComboBox {

    public ProveedoresComboBox() {
        try {
            Proveedor[] list = ProveedorManager.instance().getAllProveedores();
            for (Proveedor p : list) {
                this.addItem(p);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(ProveedoresComboBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
