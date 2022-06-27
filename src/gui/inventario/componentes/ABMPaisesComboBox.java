package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.PaisManager;
import crm.libraries.abm.entities.Pais;

public class ABMPaisesComboBox extends ABMComboBox{
	
	public ABMPaisesComboBox(){
		super(200,20);
		this.m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		try{
			Pais[] Paises = PaisManager.instance().getAllPaises();
	
			this.addItem(new String("Seleccione un Pais"));
			for (int i = 0; i < Paises.length; i++) {				
				this.addItem(Paises[i].getDescripcion());
				m_codigoForeign.add(Paises[i].getCodigo());
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
		
}

