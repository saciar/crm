/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tables;

import crm.libraries.abm.entities.Servicio;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Evento
 */
public class ServiciosSubcontratadosTableRender2 implements TableCellRenderer{

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        
        JCheckBox check = new JCheckBox();
        if(column == 1)
            check.setSelected(((Boolean)value).booleanValue());
        if(isSelected){
            check.setBackground(new Color(153, 180, 209));
        }
        else
            check.setBackground(Color.WHITE);
        return check;
    }
    
}
