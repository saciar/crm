package comerciales2019.salas.horarios;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class HorariosCellRenderer  extends DefaultTableCellRenderer{
	public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        if (value instanceof Hora) {
        	Hora country = (Hora) value;
            setText(country.getHora());
        }
         
        if (isSelected) {
            setBackground(table.getSelectionBackground());            
        } else {
            setBackground(null);
        }
         
        return this;
    }
}
