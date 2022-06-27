package gui.inventario.componentes.listas;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

public class SalasListModel extends AbstractListModel<SalaListItem> {

	private ArrayList<SalaListItem> lista = new ArrayList<>();
	
	@Override
	public SalaListItem getElementAt(int index) {
		SalaListItem item = lista.get(index);
		return item;
	}	

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return lista.size();
	}
	
	public void addSalaItem(SalaListItem item){
		lista.add(item);
		this.fireIntervalAdded(this, getSize(), getSize()+1);
	}
	
	public void removeSalaItem(int index){
		lista.remove(index);
		this.fireIntervalRemoved(index, getSize(), getSize()+1);
	}
	
	public SalaListItem getSalaItem(int index){
		return lista.get(index);
	}
	
	public void removeAllSalaItems(){
		lista.removeAll(lista);
		this.fireIntervalRemoved(this, 0, getSize()+1);
	}

}
