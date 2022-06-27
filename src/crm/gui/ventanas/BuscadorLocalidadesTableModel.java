package crm.gui.ventanas;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BuscadorLocalidadesTableModel implements TableModel,Comparable {
	public static final int COLUMNA_PAIS= 3;
	public static final int COLUMNA_PROVINCIA = 2;
	public static final int COLUMNA_PARTIDO = 1;
	public static final int COLUMNA_LOCALIDAD = 0;

	private static final String[] colnames = new String[]{ 
		"Localidad","Partido","Provincia","País"};
	
	protected transient Vector listeners;
	private List<BuscadorLocalidadesItem> rows;
	
	public BuscadorLocalidadesTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BuscadorLocalidadesTableModel(List<BuscadorLocalidadesItem> prows){
		this();
		
		for (BuscadorLocalidadesItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BuscadorLocalidadesItem());
	}
	
	public void addRow(BuscadorLocalidadesItem item){
		rows.add(item);
	}
	
	public void addRow(BuscadorLocalidadesItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param item : item a remover.
	 */
	public void removeRow(BuscadorLocalidadesItem item){
		rows.remove(item);
	}

	public BuscadorLocalidadesItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BuscadorLocalidadesItem)rows.get(idx);
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

	public Class getColumnClass(int columnIndex) {
		return String.class;
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;
		
		BuscadorLocalidadesItem item = (BuscadorLocalidadesItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_PAIS:
			return item.getPais();		
		case COLUMNA_PROVINCIA:
			return item.getProvincia();
		case COLUMNA_PARTIDO:
			return item.getPartido();
		case COLUMNA_LOCALIDAD:
			return item.getLocalidad();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BuscadorLocalidadesItem item = (BuscadorLocalidadesItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_PAIS:
			item.setPais(sValue);
			break;
		case COLUMNA_PROVINCIA:
			item.setProvincia(sValue);
			break;
		case COLUMNA_PARTIDO:
			item.setPartido(sValue);
			break;
		case COLUMNA_LOCALIDAD:
			item.setLocalidad(sValue);
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

	public List<BuscadorLocalidadesItem> getRows() {
		return rows;
	}

	public void setRows(List<BuscadorLocalidadesItem> rows) {
		this.rows = rows;
	}
}
