/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;
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
 * @author sergio
 */
public class BuscadorPptosTableModel implements TableModel,Comparable, Printable {
	public static final int COLUMNA_NUMERO= 0;
	public static final int COLUMNA_ESTADO = 1;
	public static final int COLUMNA_VENDEDOR = 2;
	public static final int COLUMNA_CLIENTE = 3;
	public static final int COLUMNA_NOMBRE_EVENTO = 4;
	public static final int COLUMNA_FECHA_INICIO = 5;
	public static final int COLUMNA_UNIDAD = 6;
	
	private static final String[] colnames = new String[]{ 
		"Nro","Estado","Vendedor","Cliente","Nombre del evento","Fecha evento", "Facturado por"};
	
	protected transient Vector listeners;
	private List<BuscadorPptosItem> rows;
	
	public BuscadorPptosTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public BuscadorPptosTableModel(List<BuscadorPptosItem> prows){
		this();
		
		for (BuscadorPptosItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BuscadorPptosItem());
	}
	
	public void addRow(BuscadorPptosItem item){
		rows.add(item);
	}
	
	public void addRow(BuscadorPptosItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public BuscadorPptosItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BuscadorPptosItem)rows.get(idx);
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
		switch (columnIndex){	
		case COLUMNA_NUMERO:
			return Long.class;
		default:
			return String.class;
		}
	}
	
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}
	
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return null;
		
		BuscadorPptosItem item = (BuscadorPptosItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_NUMERO:
			return item.getNumeroPpto();		
		case COLUMNA_ESTADO:
			return item.getEstado();
		case COLUMNA_VENDEDOR:
			return item.getVendedor();
		case COLUMNA_CLIENTE:
			return item.getCliente();
		case COLUMNA_NOMBRE_EVENTO:
			return item.getNombreEvento();
		case COLUMNA_FECHA_INICIO:
			return item.getFechaInicio();
		case COLUMNA_UNIDAD:
			return item.getUnidadAdm();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BuscadorPptosItem item = (BuscadorPptosItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_NUMERO:
			item.setNumeroPpto((Long)aValue);
			break;
		case COLUMNA_ESTADO:
			item.setEstado(sValue);
			break;
		case COLUMNA_VENDEDOR:
			item.setVendedor(sValue);
			break;
		case COLUMNA_CLIENTE:
			item.setCliente(sValue);
			break;
		case COLUMNA_NOMBRE_EVENTO:
			item.setNombreEvento(sValue);
			break;
		case COLUMNA_FECHA_INICIO:
			item.setFechaInicio(sValue);
			break;
		case COLUMNA_UNIDAD:
			item.setUnidadAdm(sValue);
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

	public List<BuscadorPptosItem> getRows() {
		return rows;
	}

	public void setRows(List<BuscadorPptosItem> rows) {
		this.rows = rows;
	}
	
	public int print (Graphics g, PageFormat f, int pageIndex) 
	   {
	      if (pageIndex == 0) 
	      {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
	        // g.drawString("Hola mundo", 100,100);
	         for (int i=0; i<rows.size();i++) {
	        	 BuscadorPptosItem item = rows.get(i);
	        	 g.drawString(item.getNumeroPpto()+"  ||  "+item.getEstado()+"   ||  "+item.getVendedor()+"   ||  "+item.getNombreEvento()+"   ||  "+item.getCliente()+"   ||  "+item.getFechaInicio(), 50,(i+1)*20+100);
	 		}
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	   }
    
}
