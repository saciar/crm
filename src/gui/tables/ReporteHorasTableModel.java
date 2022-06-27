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
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author sergio
 */
public class ReporteHorasTableModel implements TableModel,Comparable, Printable {
        public static final int COLUMNA_NOMBRE = 0;
	public static final int COLUMNA_DESDE = 2;
	public static final int COLUMNA_HASTA = 3;
	public static final int COLUMNA_OS = 1;
        public static final int COLUMNA_HS_NORMALES =4;
        public static final int COLUMNA_HS_EXTRAS = 5;
        public static final int COLUMNA_HS_EXTRAS_100 = 6;

	private static final String[] colnames = new String[]{
		"Operador","OS","Desde","Hasta","Hs Normales","Hs Extras", "Hs Extras al 100%"};

	protected transient Vector listeners;
	private List<ReporteHorasTableItem> rows;

	public ReporteHorasTableModel(){
		rows = new ArrayList<ReporteHorasTableItem>();
		listeners = new Vector<TableModelListener>();
	}

	public ReporteHorasTableModel(List<ReporteHorasTableItem> prows){
		this();

		for (ReporteHorasTableItem item : prows) {
			rows.add(item);
		}
	}

	public void addRow(){
		rows.add(new ReporteHorasTableItem());
	}

	public void addRow(ReporteHorasTableItem item){
		rows.add(item);
	}

	public void addRow(ReporteHorasTableItem item, int pos){
		rows.add(pos, item);
	}

	public void clear(){
		this.rows.clear();
	}

        public void removeRow(int pos){
            rows.remove(pos);
        }

	public ReporteHorasTableItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;

		return (ReporteHorasTableItem)rows.get(idx);
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
            ReporteHorasTableItem item = (ReporteHorasTableItem) rows.get(rowIndex);
            switch (columnIndex) {
                case COLUMNA_NOMBRE:
                    return item.getNombre();
                case COLUMNA_DESDE:
                    return item.getDesde();
                case COLUMNA_HASTA:
                    return item.getHasta();
                case COLUMNA_OS:
                    return item.getOs();
                case COLUMNA_HS_NORMALES:
                    return item.getHsNormales();
                case COLUMNA_HS_EXTRAS:
                    return item.getHsExtras();
                case COLUMNA_HS_EXTRAS_100:
                    return item.getHsExtras100();
                default:
                    return null;
            }

	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	       if (rowIndex < 0 || rowIndex >= rows.size()) {
            return;
        }

        ReporteHorasTableItem item = (ReporteHorasTableItem) rows.get(rowIndex);
        String sValue = "";

        if (aValue != null) {
            sValue = aValue.toString();
        }

        switch (columnIndex) {
            case COLUMNA_NOMBRE:
                item.setNombre(sValue);
                break;
            case COLUMNA_DESDE:
                item.setDesde(sValue);
                break;
            case COLUMNA_HASTA:
                item.setHasta(sValue);
                break;
            case COLUMNA_OS:
                item.setOs(sValue);
                break;
            case COLUMNA_HS_NORMALES:
                item.setHsNormales(sValue);
                break;
            case COLUMNA_HS_EXTRAS:
                item.setHsExtras(sValue);
                break;
            case COLUMNA_HS_EXTRAS_100:
                item.setHsExtras100(sValue);
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

    public List<ReporteHorasTableItem> getRows() {
        return rows;
    }

    public void setRows(List<ReporteHorasTableItem> rows) {
        this.rows = rows;
    }

    public int print(Graphics g, PageFormat f, int pageIndex) {
        if (pageIndex == 0) {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
            // g.drawString("Hola mundo", 100,100);
            for (int i = 0; i < rows.size(); i++) {
                ReporteHorasTableItem item = rows.get(i);
                g.drawString(item.getOs(), 50, (i + 1) * 20 + 100);
            }
            return PAGE_EXISTS;
        } else {
            return NO_SUCH_PAGE;
        }
    }

    
}
