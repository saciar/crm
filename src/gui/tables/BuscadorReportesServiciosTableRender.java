/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tables;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *Seleccione...
En OS
Solicitado al proveedor
Confirmado por proveedor
A pagar
 * @author Evento
 */
public class BuscadorReportesServiciosTableRender extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if(table.getValueAt(row, 10) !=null){
            
            if((Integer)(table.getValueAt(row, 10)) == 1){
                component.setForeground(Color.red);
            }
            else if ((Integer)(table.getValueAt(row, 10)) == 2)
                component.setForeground(Color.blue);
            else if ((Integer)(table.getValueAt(row, 10)) == 3)
                component.setForeground(Color.black);
            else if((Integer)(table.getValueAt(row, 10)) == 4)
                component.setForeground(Color.black);
            else component.setForeground(Color.gray);
        }
        else{
            component.setForeground(Color.gray);
        }
        return component;
    }
    
}
