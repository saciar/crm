package gui.tables;
import crm.libraries.abm.entities.Proveedor;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
/**
 * @web http://www.jc-mouse.net
 * @author Mouse
 */
public class Celda_CheckBox extends DefaultCellEditor implements TableCellRenderer  {
    
    private JComponent component = new ProveedoresComboBox();
    private Proveedor value; // valor de la celda
    
    /** Constructor de clase */
    public Celda_CheckBox() {
        super( new ProveedoresComboBox() );
    }

    /** retorna valor de celda */
    @Override
    public Object getCellEditorValue() {
        return ((ProveedoresComboBox)component).getSelectedItem();
    }

    /** Segun el valor de la celda selecciona/deseleciona el JCheckBox */
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        //Color de fondo en modo edicion
        //( (JCheckBox) component).setBackground( new Color(200,200,0) );
        //obtiene valor de celda y coloca en el JCheckBox
        
        ( (ProveedoresComboBox) component).setSelectedItem(value);
        return ( (ProveedoresComboBox) component);
    }

    /** cuando termina la manipulacion de la celda */
    @Override
    public boolean stopCellEditing() {        
        value = ((Proveedor)getCellEditorValue()) ;
        ((ProveedoresComboBox)component).setSelectedItem( value );
        return super.stopCellEditing();
    }

    /** retorna componente */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
         if (value == null)
            return null;         
         return ( (ProveedoresComboBox) component );
    }

}
