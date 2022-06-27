/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author sergio
 */
public class BuscadorReportesServiciosTableModel implements TableModel,Comparable, Printable {
	public static final int COLUMNA_NUMERO= 0;
	public static final int COLUMNA_SERVICIO = 1;
	public static final int COLUMNA_CANTIDAD = 2;
	public static final int COLUMNA_DIAS = 3;
	public static final int COLUMNA_DTO = 4;
	public static final int COLUMNA_NOMBRE_EVENTO = 5;
	public static final int COLUMNA_FECHA_INICIO = 6;
	public static final int COLUMNA_TOTAL_SERV= 7;
        public static final int COLUMNA_PROVEEDOR= 8;
        public static final int COLUMNA_COSTO= 9;
        public static final int COLUMNA_ESTADO=10;
	
	private static final String[] colnames = new String[]{ 
		"Nro","Servicio","Cant.","Días","Dto%","Nombre del evento","Fecha evento","TotalServ", "Proveedor","Costo","Estado"};
	
	protected transient Vector listeners;
	private List<BuscadorReportesServiciosItem> rows;
	
	public BuscadorReportesServiciosTableModel(){
		rows = new ArrayList<BuscadorReportesServiciosItem>();
		listeners = new Vector<TableModelListener>();
	}
	
	public BuscadorReportesServiciosTableModel(List<BuscadorReportesServiciosItem> prows){
		this();
		
		for (BuscadorReportesServiciosItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new BuscadorReportesServiciosItem());
	}
	
	public void addRow(BuscadorReportesServiciosItem item){
		rows.add(item);
	}
	
	public void addRow(BuscadorReportesServiciosItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public BuscadorReportesServiciosItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (BuscadorReportesServiciosItem)rows.get(idx);
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
		case COLUMNA_CANTIDAD:
			return Integer.class;
		case COLUMNA_DTO:
			return Integer.class;
		case COLUMNA_DIAS:
			return Integer.class;
                case COLUMNA_ESTADO:
                    return Integer.class;
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
		
		BuscadorReportesServiciosItem item = (BuscadorReportesServiciosItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_NUMERO:
			return item.getNumeroPpto();		
		case COLUMNA_SERVICIO:
			return item.getServicio();
		case COLUMNA_CANTIDAD:
			return item.getCantidad();
		case COLUMNA_DIAS:
			return item.getDias();
		case COLUMNA_DTO:
			return item.getDescuento();
		case COLUMNA_NOMBRE_EVENTO:
			return item.getNombreEvento();
		case COLUMNA_FECHA_INICIO:
			return item.getFechaInicio();
		case COLUMNA_TOTAL_SERV:
			return getCurrencyFormat().format(item.getTotalServicio());
                case COLUMNA_COSTO:
                    return getCurrencyFormat().format(item.getCosto());
                case COLUMNA_PROVEEDOR:
                    return item.getProveedor();
                case COLUMNA_ESTADO:
                    return item.getEstado();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		BuscadorReportesServiciosItem item = (BuscadorReportesServiciosItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_NUMERO:
			item.setNumeroPpto((Long)aValue);
			break;
		case COLUMNA_SERVICIO:
			item.setServicio(sValue);
			break;
		case COLUMNA_CANTIDAD:
			item.setCantidad((Integer)aValue);
			break;
		case COLUMNA_DIAS:
			item.setCantidad((Integer)aValue);
			break;
		case COLUMNA_DTO:
			item.setDescuento((Integer)aValue);
			break;
		case COLUMNA_NOMBRE_EVENTO:
			item.setNombreEvento(sValue);
			break;
		case COLUMNA_FECHA_INICIO:
			item.setFechaInicio(sValue);
			break;
		case COLUMNA_TOTAL_SERV:
			item.setTotalServicio((Double)aValue);
			break;
                case COLUMNA_PROVEEDOR:
			item.setProveedor(sValue);
			break;
                case COLUMNA_COSTO:
			item.setCosto((Double)aValue);
			break;
                case COLUMNA_ESTADO:
                        item.setEstado((Integer)aValue);
                        break;
		}
	}
	
	private static NumberFormat currencyFormat;
	private NumberFormat getCurrencyFormat() {
		if (currencyFormat == null){
			                 Locale l = new Locale("es","AR");
			currencyFormat = NumberFormat.getCurrencyInstance(l);			
		}
		
		return currencyFormat;
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

	public List<BuscadorReportesServiciosItem> getRows() {
		return rows;
	}

	public void setRows(List<BuscadorReportesServiciosItem> rows) {
		this.rows = rows;
	}
	
	public int print (Graphics g, PageFormat f, int pageIndex) 
	   {
	      if (pageIndex == 0) 
	      {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
	        // g.drawString("Hola mundo", 100,100);
	         for (int i=0; i<rows.size();i++) {
	        	 BuscadorReportesServiciosItem item = rows.get(i);
	        	 g.drawString(item.getNumeroPpto()+"  ||  "+item.getServicio()+"   ||  "+item.getCantidad()+"   ||  "+item.getDias()+"   ||  "+item.getNombreEvento()+"   ||  "+item.getFechaInicio()+"   ||  "+item.getTotalServicio(), 50,(i+1)*20+100);
	 		}
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	   }
    
}
