package comerciales2019.salas.horarios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class HorariosTableModel extends AbstractTableModel {
    private String[] columnNames = {"Dia","Fecha", "Desde", "Hasta"};
    private List<HorariosTableItem> listItems = new ArrayList<>();
     
    public HorariosTableModel(List<HorariosTableItem> listPerson) {	
        this.listItems.addAll(listPerson);
    }
    
    public HorariosTableModel() {

    }
     
    public void addItem(HorariosTableItem it){
    	this.listItems.add(it);
    }
    
    public void removeItem(int index){
    	this.listItems.remove(index);
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
     
    public String getColumnName(int column) {
        return columnNames[column];
    }
     
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }
 
    @Override
    public int getRowCount() {
        return listItems.size();
    }
 
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
    	HorariosTableItem person = listItems.get(rowIndex);
         
        switch (columnIndex) {
        case 1:
                person.setFecha((String) value);
            break;
        case 2:
                person.setHoraDesde((Hora) value);
            break;
        case 3:
                person.setHoraHasta((Hora) value);
            break;
        }      
    }
     
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object returnValue = null;
        HorariosTableItem person = listItems.get(rowIndex);
         
        switch (columnIndex) {
        case 0:
                returnValue = rowIndex + 1;
            break;
        case 1:
                returnValue = person.getFecha();
            break;
        case 2:
                returnValue = person.getHoraDesde();
            break;
        case 3:
                returnValue = person.getHoraHasta();
            break;
        }
         
        return returnValue;
    }
 
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 0;
    }  

}
