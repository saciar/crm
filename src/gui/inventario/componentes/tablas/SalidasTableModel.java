package gui.inventario.componentes.tablas;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SalidasTableModel implements TableModel,Comparable, Printable {
	public static final int COLUMNA_CODIGO = 0;
	public static final int COLUMNA_FAMILIA= 1;
	public static final int COLUMNA_SUBFAMILIA = 2;
	public static final int COLUMNA_MARCA = 3;
	public static final int COLUMNA_MODELO = 4;

	private static final String[] colnames = new String[]{
			"Codigo barras", "Familia", "Subfamilia", "Marca", "Modelo"};

	protected transient Vector listeners;
	private List<SalidasTableItem> rows;

	public SalidasTableModel(){
		rows = new ArrayList<SalidasTableItem>();
		listeners = new Vector<TableModelListener>();
	}

	public SalidasTableModel(List<SalidasTableItem> prows){
		this();

		for (SalidasTableItem item : prows) {
			rows.add(item);
		}
	}

	public void addRow(){
		rows.add(new SalidasTableItem());
	}

	public void addRow(SalidasTableItem item){
		rows.add(item);
	}
        
	public void removeRow(){
		
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param item : item a remover.
	 */
	public void removeRow(SalidasTableItem item){
		rows.remove(item);
	}
	
	/**
	 * Remueve un item de la grilla
	 * @param idx : numero de fila a remover. 
	 */
	public void removeRow(int idx){
		rows.remove(idx);
	}

	public void addRow(SalidasTableItem item, int pos){
		rows.add(pos, item);
	}

	public void clear(){
		this.rows.clear();
	}

	public SalidasTableItem getRow(int idx){
		if (idx < 0 || idx >= rows.size())
			return null;

		return (SalidasTableItem)rows.get(idx);
	}
	
	public boolean isInTable(String codBarra){
		boolean esta = false;
		for(SalidasTableItem item: rows){
			if(item.getCodigoBarras().equals(codBarra)){
				esta = true;
				break;
			}
		}
		return esta;
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
            SalidasTableItem item = (SalidasTableItem) rows.get(rowIndex);
            switch (columnIndex) {
                case COLUMNA_CODIGO:
                    return item.getCodigoBarras();
                case COLUMNA_FAMILIA:                	
                    return item.getNombreFamilia();
                case COLUMNA_SUBFAMILIA:                    
                    return item.getNombreSubfamilia();
                case COLUMNA_MARCA:
                    return item.getNombreMarca();
                case COLUMNA_MODELO:
                	return item.getModelo();

                default:
                    return null;
            }

	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex >= rows.size())
			return;

		SalidasTableItem item = (SalidasTableItem)rows.get(rowIndex);
		String sValue = "";

		if (aValue != null)
			sValue = aValue.toString();

		switch (columnIndex){
		case COLUMNA_CODIGO:
			item.setCodigoBarras(sValue);
			break;
		case COLUMNA_SUBFAMILIA:
			item.setNombreSubfamilia(sValue);
			break;
		case COLUMNA_MARCA:
			item.setNombreMarca(sValue);
			break;
		case COLUMNA_MODELO:
			item.setModelo(sValue);
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

	public List<SalidasTableItem> getRows() {
		return rows;
	}

	public void setRows(List<SalidasTableItem> rows) {
		this.rows = rows;
	}

	public int print (Graphics g, PageFormat f, int pageIndex)
	   {
	      if (pageIndex == 0)
	      {
	         // Imprime "Hola mundo" en la primera pagina, en la posicion 100,100
	        // g.drawString("Hola mundo", 100,100);
	         for (int i=0; i<rows.size();i++) {
	        	 SalidasTableItem item = rows.get(i);
	        	 g.drawString(String.valueOf(item.getCodigoBarras()), 50,(i+1)*20+100);
	 		}
	         return PAGE_EXISTS;
	      }
	      else
	         return NO_SUCH_PAGE;
	   }

}
