/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.tables;

import crm.client.managers.EstadoEquiposManager;
import crm.libraries.abm.entities.Equipos;
import crm.libraries.abm.entities.EstadoEquipos;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author saciar
 */
public class BusquedaEquiposTableModel implements TableModel,Comparable, Printable {
	public static final int COLUMNA_CODIGO = 0;
	public static final int COLUMNA_NOMBRE = 1;
	public static final int COLUMNA_ESTADO = 2;
	public static final int COLUMNA_SERIAL = 3;

	private static final String[] colnames = new String[]{
		"Cod","Nombre","Estado","Nro Serie"};

	protected transient Vector listeners;
	private List<Equipos> rows;

	public BusquedaEquiposTableModel(){
		rows = new ArrayList<Equipos>();
		listeners = new Vector<TableModelListener>();
	}

	public BusquedaEquiposTableModel(List<Equipos> prows){
		this();

		for (Equipos item : prows) {
			rows.add(item);
		}
	}

	public void addRow(){
		rows.add(new Equipos());
	}

	public void addRow(Equipos item){
		rows.add(item);
	}
        
        public void removeRow(){
            
        }

	public void addRow(Equipos item, int pos){
		rows.add(pos, item);
	}

	public void clear(){
		this.rows.clear();
	}

	public Equipos getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;

		return (Equipos)rows.get(idx);
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
        try {
            if (rowIndex < 0 || rowIndex >= rows.size()) {
                return null;
            }
            Equipos item = (Equipos) rows.get(rowIndex);
            switch (columnIndex) {
                case COLUMNA_CODIGO:
                    return item.getCodigoBarras();
                case COLUMNA_NOMBRE:
                    return item.getEqDescripcion();
                case COLUMNA_ESTADO:
                    EstadoEquiposManager estadoManager = EstadoEquiposManager.instance();
                    EstadoEquipos e = estadoManager.getById(item.getEqEstado());
                    return e.getDescripcion();
                case COLUMNA_SERIAL:
                    return item.getEqNroserie();
                default:
                    return null;
            }
        } catch (RemoteException ex) {
            Logger.getLogger(BusquedaEquiposTableModel.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;

		Equipos item = (Equipos)rows.get(rowIndex);
		String sValue = "";

		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){
		case COLUMNA_CODIGO:
			item.setCodigoBarras(sValue);
			break;
		case COLUMNA_ESTADO:
			item.setEqEstado(sValue);
			break;
		case COLUMNA_NOMBRE:
			item.setEqDescripcion(sValue);
			break;
		case COLUMNA_SERIAL:
			item.setEqNroserie(sValue);
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

	public List<Equipos> getRows() {
		return rows;
	}

	public void setRows(List<Equipos> rows) {
		this.rows = rows;
	}

	public int print (Graphics g, PageFormat f, int pageIndex)
	   {
	      if (pageIndex == 0)
	      {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
	        // g.drawString("Hola mundo", 100,100);
	         for (int i=0; i<rows.size();i++) {
	        	 Equipos item = rows.get(i);
	        	 g.drawString(item.getCodigoBarras(), 50,(i+1)*20+100);
	 		}
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	   }

}
