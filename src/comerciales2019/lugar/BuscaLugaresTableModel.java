package comerciales2019.lugar;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BuscaLugaresTableModel implements TableModel,Comparable {
	public static final int COLUMNA_CODIGO= 0;
	public static final int COLUMNA_NOMBRE = 1;
	
	private static final String[] colnames = new String[]{ 
		"Código","Nombre del lugar"};
	
	protected transient Vector listeners;
	private List<BuscaLugaresItem> rows;
	
	public BuscaLugaresTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BuscaLugaresTableModel(List<BuscaLugaresItem> prows){
		this();
		
		for(BuscaLugaresItem item: prows){
			rows.add(item);
		}
		
	}
	
	public void addRow(){
		rows.add(new BuscaLugaresItem());
	}
	
	public void addRow(BuscaLugaresItem item){
		rows.add(item);
	}
	
	public void addRow(BuscaLugaresItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public BuscaLugaresItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BuscaLugaresItem)rows.get(idx);
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
		
		BuscaLugaresItem item = (BuscaLugaresItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_CODIGO:
			return item.getCodigo();		
		case COLUMNA_NOMBRE:
			return item.getNombreLugar();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BuscaLugaresItem item = (BuscaLugaresItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_CODIGO:
			item.setCodigo(sValue);
			break;
		case COLUMNA_NOMBRE:
			item.setNombreLugar(sValue);
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

	public List<BuscaLugaresItem> getRows() {
		return rows;
	}

	public void setRows(List<BuscaLugaresItem> rows) {
		this.rows = rows;
	}
}
