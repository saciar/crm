/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import gui.tables.GastosPptoItem;

/**
 *
 * @author saciar
 */
public class GastosPptoTableModel implements TableModel,Comparable {
	public static final int COLUMNA_NRO = 0;        
	public static final int COLUMNA_NOMBRE = 1;
        public static final int COLUMNA_CLIENTE = 2;
        public static final int COLUMNA_VENDEDOR = 3;
        public static final int COLUMNA_DESDE= 4;
        public static final int COLUMNA_HASTA = 5;
        public static final int COLUMNA_SUBC = 6;

	private static final String[] colnames = new String[]{
		"Nro.Ppto","Evento","Cliente","Vendedor","Desde","Hasta","Serv Externos"};

	protected transient Vector listeners;
	private List<GastosPptoItem> rows;

	public GastosPptoTableModel(){
		rows = new ArrayList<GastosPptoItem>();
		listeners = new Vector<TableModelListener>();
	}

	public GastosPptoTableModel(List<GastosPptoItem> prows){
		this();

		for (GastosPptoItem item : prows) {
			rows.add(item);
		}
	}

	public void addRow(){
		rows.add(new GastosPptoItem());
	}

	public void addRow(GastosPptoItem item){
		rows.add(item);
	}

	public void addRow(GastosPptoItem item, int pos){
		rows.add(pos, item);
	}

	public void clear(){
		this.rows.clear();
	}

        public void removeRow(int pos){
            rows.remove(pos);
        }

	public GastosPptoItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;

		return (GastosPptoItem)rows.get(idx);
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
            GastosPptoItem item = (GastosPptoItem) rows.get(rowIndex);
            switch (columnIndex) {
                case COLUMNA_NRO :
                    return item.getNroPpto();
                case COLUMNA_NOMBRE:
                    return item.getNombreEvento();
                case COLUMNA_DESDE:
                    return item.getFechaDesde();
                case COLUMNA_HASTA:
                    return item.getFechaHasta();
                case COLUMNA_SUBC:
                    return item.getSubcontratados();
                case COLUMNA_VENDEDOR:
                    return item.getVendedor();
                case COLUMNA_CLIENTE:
                    return item.getCliente();
                default:
                    return null;
            }

	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;

		          GastosPptoItem item = (GastosPptoItem) rows.get(rowIndex);
            String sValue = "";

            if (aValue != null) {
                sValue = aValue.toString();
            }

            switch (columnIndex) {
                case COLUMNA_NRO:
                    item.setNroPpto(sValue);
                    break;
                case COLUMNA_NOMBRE:
                    item.setNombreEvento(sValue);
                    break;
                case COLUMNA_DESDE:
                    item.setFechaDesde(sValue);
                    break;
                case COLUMNA_HASTA:
                    item.setFechaHasta(sValue);
                    break;
                case COLUMNA_SUBC:
                    item.setSubcontratados(sValue);
                    break;
                case COLUMNA_VENDEDOR:
                    item.setVendedor(sValue);
                    break;
                case COLUMNA_CLIENTE:
                    item.setCliente(sValue);
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

	public List<GastosPptoItem> getRows() {
		return rows;
	}

	public void setRows(List<GastosPptoItem> rows) {
		this.rows = rows;
	}


}
