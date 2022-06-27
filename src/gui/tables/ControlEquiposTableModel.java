/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;

import crm.libraries.abm.entities.Equipos;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author saciar
 */
public class ControlEquiposTableModel implements TableModel,Comparable, Printable {
	public static final int COLUMNA_CODIGO = 0;
	public static final int COLUMNA_NOMBRE = 1;
	public static final int COLUMNA_SERIAL = 3;


	private static final String[] colnames = new String[]{
		"Cod Barras","Nombre","Nro Serie"};

	protected transient Vector listeners;
	private List<ControlEquiposItem> rows;

	public ControlEquiposTableModel(){
		rows = new ArrayList<ControlEquiposItem>();
		listeners = new Vector<TableModelListener>();
	}

	public ControlEquiposTableModel(List<ControlEquiposItem> prows){
		this();

		for (ControlEquiposItem item : prows) {
			rows.add(item);
		}
	}

	public void addRow(){
		rows.add(new ControlEquiposItem());
	}

	public void addRow(ControlEquiposItem item){
		rows.add(item);
	}

	public void addRow(ControlEquiposItem item, int pos){
		rows.add(pos, item);
	}

	public void clear(){
		this.rows.clear();
	}

        public void removeRow(int pos){
            rows.remove(pos);
        }

	public ControlEquiposItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;

		return (ControlEquiposItem)rows.get(idx);
	}

	public int getRowCount() {
		return rows.size();
	}

	public int getColumnCount() {
		return colnames.length;
	}

	public String getColumnName(int columnIndex) {
		return colnames[columnIndex];
	}

	@SuppressWarnings("unchecked")
	public Class getColumnClass(int columnIndex) {

            return String.class;

	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex < 0 || rowIndex >= rows.size()) {
                return null;
            }
            ControlEquiposItem item = (ControlEquiposItem) rows.get(rowIndex);
            switch (columnIndex) {
                case COLUMNA_CODIGO:
                    return item.getCodigoBarras();
                case COLUMNA_NOMBRE:
                    return item.getNombre();
                case COLUMNA_SERIAL:
                    return item.getNoSerie();
                default:
                    return null;
            }

	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;

		ControlEquiposItem item = (ControlEquiposItem)rows.get(rowIndex);
		String sValue = "";

		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){
		case COLUMNA_CODIGO:
			item.setCodigoBarras(sValue);
			break;
		case COLUMNA_NOMBRE:
			item.setNombre(sValue);
			break;
		case COLUMNA_SERIAL:
			item.setNoSerie(sValue);
			break;
		}
	}

	public void addTableModelListener(TableModelListener l) {
		listeners.addElement(l);
	}

	public void removeTableModelListener(TableModelListener l) {
		listeners.remove(l);
	}

	public int compareTo(Object arg0) {
		return 0;
	}

	public List<ControlEquiposItem> getRows() {
		return rows;
	}

	public void setRows(List<ControlEquiposItem> rows) {
		this.rows = rows;
	}

	public int print (Graphics g, PageFormat f, int pageIndex)
	   {
	      if (pageIndex == 0)
	      {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
	        // g.drawString("Hola mundo", 100,100);
	         for (int i=0; i<rows.size();i++) {
	        	 ControlEquiposItem item = rows.get(i);
	        	 g.drawString(item.getCodigoEquipo(), 50,(i+1)*20+100);
	 		}
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	   }

}
