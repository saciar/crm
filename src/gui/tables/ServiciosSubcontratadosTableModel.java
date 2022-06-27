/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;

import crm.libraries.abm.entities.Proveedor;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author saciar
 */
public class ServiciosSubcontratadosTableModel implements TableModel,Comparable, Printable {

        public static final int COLUMNA_ES_SUBCONTRATADO = 0;
        public static final int COLUMNA_CANTIDAD = 1;
	public static final int COLUMNA_NOMBRE = 2;
        public static final int COLUMNA_SALA = 3;
	public static final int COLUMNA_DIAS = 4;
	public static final int COLUMNA_DESCUENTO = 5;
        public static final int COLUMNA_PRECIO_VTA = 6;
        public static final int COLUMNA_COSTO = 7;
        public static final int COLUMNA_PROVEEDOR = 8;
        public static final int COLUMNA_ESTADO =9;
        public static final int COLUMNA_OPCIONAL =10;
        public static final int COLUMNA_PROPIO=11;

	private static final String[] colnames = new String[]{
		"Subc.","Cant","Nombre","Sala","Dias","Variacion","Precio Venta","Costo Total","Proveedor","Estado","opc","ext"};

	protected transient Vector listeners;
	private List<ServiciosSubcontratadosItem> rows;

	public ServiciosSubcontratadosTableModel(){
		rows = new ArrayList<ServiciosSubcontratadosItem>();
		listeners = new Vector<TableModelListener>();
	}

	public ServiciosSubcontratadosTableModel(List<ServiciosSubcontratadosItem> prows){
		this();

		for (ServiciosSubcontratadosItem item : prows) {
			rows.add(item);
		}
	}

	public void addRow(){
		rows.add(new ServiciosSubcontratadosItem());
	}

	public void addRow(ServiciosSubcontratadosItem item){
		rows.add(item);
	}

	public void addRow(ServiciosSubcontratadosItem item, int pos){
		rows.add(pos, item);
	}

	public void clear(){
		this.rows.clear();
	}

	public ServiciosSubcontratadosItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;

		return (ServiciosSubcontratadosItem)rows.get(idx);
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
            if(columnIndex ==COLUMNA_COSTO)
                return Double.class;
            if(columnIndex ==COLUMNA_PRECIO_VTA)
                return Double.class;
            if(columnIndex == COLUMNA_PROVEEDOR)
                return Proveedor.class;
            if(columnIndex == COLUMNA_ES_SUBCONTRATADO)
                return Boolean.class;
            return String.class;

	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
            if(columnIndex == COLUMNA_ES_SUBCONTRATADO)
                return true;
            else return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

            if (rowIndex < 0 || rowIndex >= rows.size()) {
                return null;
            }
            ServiciosSubcontratadosItem item = (ServiciosSubcontratadosItem) rows.get(rowIndex);
            switch (columnIndex) {
                case COLUMNA_ES_SUBCONTRATADO:
                    return item.isIsSubcontratado();
                case COLUMNA_CANTIDAD:
                    return item.getCantidad();
                case COLUMNA_NOMBRE:
                    return item.getNombre();
                case COLUMNA_SALA:
                    return item.getNombreSala();
                case COLUMNA_DESCUENTO:
                    return item.getDescuento();
                case COLUMNA_DIAS:
                    return item.getDias();
                case COLUMNA_PRECIO_VTA:
                    return item.getPrecio_vta();
                case COLUMNA_COSTO:
                    return item.getCosto();
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

		ServiciosSubcontratadosItem item = (ServiciosSubcontratadosItem)rows.get(rowIndex);
		String sValue = "";

		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){

                    case COLUMNA_ES_SUBCONTRATADO:
                        item.setIsSubcontratado((Boolean)aValue);
                        break;
		case COLUMNA_CANTIDAD:
			item.setCantidad(sValue);
			break;
		case COLUMNA_DIAS:
			item.setDias(sValue);
			break;
		case COLUMNA_NOMBRE:
			item.setNombre(sValue);
			break;
                case COLUMNA_SALA:
                        item.setNombreSala(sValue);
                        break;
		case COLUMNA_DESCUENTO:
			item.setDescuento(sValue);
			break;
                case COLUMNA_PRECIO_VTA:
			item.setPrecio_vta((Double)aValue);
			break;
                case COLUMNA_COSTO:
			item.setCosto((Double)aValue);
			break;
                case COLUMNA_PROVEEDOR:
			item.setProveedor((Proveedor)aValue);
			break;
                 case COLUMNA_ESTADO:
                    item.setEstado(sValue);
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

	public List<ServiciosSubcontratadosItem> getRows() {
		return rows;
	}

	public void setRows(List<ServiciosSubcontratadosItem> rows) {
		this.rows = rows;
	}

	public int print (Graphics g, PageFormat f, int pageIndex)
	   {
	      if (pageIndex == 0)
	      {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
	        // g.drawString("Hola mundo", 100,100);
	         for (int i=0; i<rows.size();i++) {
	        	 ServiciosSubcontratadosItem item = rows.get(i);
	        	 g.drawString(item.getNombre(), 50,(i+1)*20+100);
	 		}
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	   }

}
