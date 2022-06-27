/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.tables;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Evento
 */
public class RentabilidadTableModel implements TableModel,Comparable, Printable {
	public static final int COLUMNA_NUMERO= 0;
	public static final int COLUMNA_VENDEDOR = 1;
	public static final int COLUMNA_CLIENTE = 2;
	public static final int COLUMNA_FACTURADO = 3;
        public static final int COLUMNA_COSTO_OP = 4;
	public static final int COLUMNA_SUBCONTRATADO = 5;	
	public static final int COLUMNA_COM_LUGAR = 6;
        public static final int COLUMNA_COM_TERCERO = 7;
        public static final int COLUMNA_COM_COMERCIAL = 8;
        public static final int COLUMNA_RENTABILIDAD = 9;
        public static final int COLUMNA_PORCENTAJE = 10;
	
	private static final String[] colnames = new String[]{ 
		"Nro","Vendedor","Cliente","Facturado","Costo Op.", "Subcontratado", "Comision Lugar", "Comision 3ro","Comision Com.","RENTABILIDAD", "% Rent."};
	
	protected transient Vector listeners;
	private List<RentabilidadTableItem> rows;
	
	public RentabilidadTableModel(){
		rows = new ArrayList();
		listeners = new Vector<TableModelListener>();
	}
	
	public RentabilidadTableModel(List<RentabilidadTableItem> prows){
		this();
		
		for (RentabilidadTableItem item : prows) {
			rows.add(item);
		}
	}
	
	public void addRow(){
		rows.add(new RentabilidadTableItem());
	}
	
	public void addRow(RentabilidadTableItem item){
		rows.add(item);
	}
	
	public void addRow(RentabilidadTableItem item, int pos){
		rows.add(pos, item);
	}
	
	public void clear(){
		this.rows.clear();
	}

	public RentabilidadTableItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;
		
		return (RentabilidadTableItem)rows.get(idx);
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
		
		RentabilidadTableItem item = (RentabilidadTableItem)rows.get(rowIndex);
		
		switch (columnIndex){	
		case COLUMNA_NUMERO:
			return item.getNroppto();		
		case COLUMNA_VENDEDOR:
			return item.getVendedor();
		case COLUMNA_CLIENTE:
			return item.getCliente();
		case COLUMNA_SUBCONTRATADO:
			return item.getSubcontrataciones();
		case COLUMNA_COSTO_OP:
			return item.getCO();
		case COLUMNA_FACTURADO:
			return item.getFacturado();
                case COLUMNA_COM_LUGAR:
                        return item.getComisionLugar();
                case COLUMNA_COM_TERCERO:
                        return item.getComisionTercero();
                case COLUMNA_COM_COMERCIAL:
                        return item.getComisionComercial();
                case COLUMNA_RENTABILIDAD:
                        return item.getRentabilidad();
                case COLUMNA_PORCENTAJE:
                        return item.getPorcentajeRent();
		default:
			return null;
		}
	}
	
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;
		
		RentabilidadTableItem item = (RentabilidadTableItem)rows.get(rowIndex);
		String sValue = "";
		
		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){		
		case COLUMNA_NUMERO:
			item.setNroppto((Long)aValue);
			break;
		case COLUMNA_VENDEDOR:
			item.setVendedor(sValue);
			break;
		case COLUMNA_CLIENTE:
			item.setCliente(sValue);
			break;
		case COLUMNA_SUBCONTRATADO:
			item.setSubcontrataciones((Double)aValue);
			break;
		case COLUMNA_COSTO_OP:
			item.setCO((Double)aValue);
			break;
		case COLUMNA_FACTURADO:
			item.setFacturado((Double)aValue);
			break;
                case COLUMNA_COM_LUGAR:
			item.setComisionLugar((Double)aValue);
			break;
		case COLUMNA_COM_COMERCIAL:
			item.setComisionComercial((Double)aValue);
			break;
                case COLUMNA_COM_TERCERO:
			item.setComisionTercero((Double)aValue);
			break;
                case COLUMNA_RENTABILIDAD:
			item.setRentabilidad((Double)aValue);
			break;
                case COLUMNA_PORCENTAJE:
			item.setPorcentajeRent((Double)aValue);
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

	public List<RentabilidadTableItem> getRows() {
		return rows;
	}

	public void setRows(List<RentabilidadTableItem> rows) {
		this.rows = rows;
	}
	
	public int print (Graphics g, PageFormat f, int pageIndex) 
	   {
	      if (pageIndex == 0) 
	      {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
	        // g.drawString("Hola mundo", 100,100);
	         for (int i=0; i<rows.size();i++) {
	        	 RentabilidadTableItem item = rows.get(i);
	        	 g.drawString(item.getNroppto()+" Rentabilidad ", 50,(i+1)*20+100);
	 		}
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	   }
    
}
