package comerciales2019.clientes;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class BuscaClientesTableModel implements TableModel,Comparable {
	public static final int COLUMNA_CODIGO= 0;
	public static final int COLUMNA_RAZON = 1;
	public static final int COLUMNA_FANTASIA = 2;
	
	private static final String[] colnames = new String[]{ 
		"Código","Nombre de fantasía","Razón social"};
	
	protected transient Vector listeners;
	private List<BuscaClientesItem> rows;
	
	public BuscaClientesTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BuscaClientesTableModel(List<BuscaClientesItem> prows){
		this();
		
		for(BuscaClientesItem item: prows){
			rows.add(item);
		}
		
	}
	
	public void addRow(){
		rows.add(new BuscaClientesItem());
	}
	
	public void addRow(BuscaClientesItem item){
		rows.add(item);
	}
	
	public void addRow(BuscaClientesItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public BuscaClientesItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BuscaClientesItem)rows.get(idx);
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
		
		BuscaClientesItem item = (BuscaClientesItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_CODIGO:
			return item.getCodigo();		
		case COLUMNA_RAZON:
			return item.getRazonSocial();
		case COLUMNA_FANTASIA:
			return item.getNombreFantasia();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BuscaClientesItem item = (BuscaClientesItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_CODIGO:
			item.setCodigo(sValue);
			break;
		case COLUMNA_RAZON:
			item.setRazonSocial(sValue);
			break;
		case COLUMNA_FANTASIA:
			item.setNombreFantasia(sValue);
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

	public List<BuscaClientesItem> getRows() {
		return rows;
	}

	public void setRows(List<BuscaClientesItem> rows) {
		this.rows = rows;
	}
}
