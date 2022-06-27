package comerciales2019.lugar;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class NuevoLugarTableModel implements TableModel,Comparable {
	public static final int COLUMNA_NUMERO_SALA= 0;
	public static final int COLUMNA_NOMBRE_SALA = 1;
	
	private static final String[] colnames = new String[]{ 
		"Sala nro","Nombre de la sala"};
	
	protected transient Vector listeners;
	private List<NuevoLugarItem> rows;
	
	public NuevoLugarTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public NuevoLugarTableModel(List<NuevoLugarItem> prows){
		this();
		
		for (NuevoLugarItem item : prows) {
			rows.add(item);
		}
	}
	
	public void removeRow(NuevoLugarItem item){		
		rows.remove(item);		
	}
	
	public void recalcularNroSala(int nro){
		for(int i = nro; i<rows.size(); i++){
			((NuevoLugarItem)rows.get(i)).setNumeroSala(((NuevoLugarItem)rows.get(i)).getNumeroSala()-1);
		}
	}
	
	public void addRow(){
		rows.add(new NuevoLugarItem());
	}
	
	public void addRow(NuevoLugarItem item){
		rows.add(item);
	}
	
	public void addRow(NuevoLugarItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public NuevoLugarItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (NuevoLugarItem)rows.get(idx);
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
		switch (columnIndex){	
		case COLUMNA_NUMERO_SALA:	
			return false;
		case COLUMNA_NOMBRE_SALA:
			return true;
		default:
			return false;
		}
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;
		
		NuevoLugarItem item = (NuevoLugarItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_NUMERO_SALA:
			return item.getNumeroSala();		
		case COLUMNA_NOMBRE_SALA:
			return item.getNombreSala();		
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		NuevoLugarItem item = (NuevoLugarItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_NUMERO_SALA:
			item.setNumeroSala(Integer.parseInt(sValue));
			break;
		case COLUMNA_NOMBRE_SALA:
			item.setNombreSala(sValue);
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

	public List<NuevoLugarItem> getRows() {
		return rows;
	}

	public void setRows(List<NuevoLugarItem> rows) {
		this.rows = rows;
	}
}
