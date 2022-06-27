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
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Evento
 */
public class ServiciosSubcontratadosTableRender extends DefaultTableCellRenderer{
//    public Component getTableCellRendererComponent(JTable table,
//            Object value,
//            boolean isSelected,
//            boolean hasFocus,
//            int row,
//            int column) {
//
//        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        this.setOpaque(true);
//        ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) table.getModel();
//        ServiciosSubcontratadosItem it = (ServiciosSubcontratadosItem) model.getRow(row);
//        if (column == 11 && it.getCodigoServicio().equals("1")) {
//            this.setBackground(Color.LIGHT_GRAY);
//        } else if ((it.getModalidad().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA_OPCIONAL)) || it.getModalidad().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_INTERNA_OPCIONAL))) && column == 10) {
//            this.setBackground(new Color(175, 31, 36));
//        } else {
//            this.setBackground(Color.WHITE);
//        }
//
//        if (isSelected && column < 10) {
//            this.setBackground(new Color(153, 180, 209));
//        }
//
//        return this;
//    }
    
    public Component getTableCellRendererComponent(JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
        
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        ServiciosSubcontratadosTableModel model = (ServiciosSubcontratadosTableModel) table.getModel();
        ServiciosSubcontratadosItem it = (ServiciosSubcontratadosItem) model.getRow(row);
        if (column == 11 && it.getCodigoServicio().equals("1")) {
            this.setBackground(Color.LIGHT_GRAY);
        } else if ((it.getModalidad().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_EXTERNA_OPCIONAL)) || it.getModalidad().equals(Integer.toString(Servicio.MODALIDAD_CONTRATACION_INTERNA_OPCIONAL))) && column == 10) {
            this.setBackground(new Color(175, 31, 36));
        } else {
            this.setBackground(Color.WHITE);
        }

        if (isSelected && column < 10) {
            this.setBackground(new Color(153, 180, 209));
        }

        return this;
    }
}
