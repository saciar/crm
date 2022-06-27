package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.LocalidadManager;
import crm.libraries.abm.entities.Localidad;

public class ABMLocalidadesComboBox extends ABMComboBox{
	
	private LocalidadManager manager = LocalidadManager.instance();
	
	public ABMLocalidadesComboBox(){
		super(200,20);
		this.m_codigoForeign = new Vector();
		this.addItem(new String("Seleccione una Localidad"));
	}	
	
	public void loadItems(String parentId){		
		try{			
			//Localidad[] localidades = LocalidadManager.instance().findByPartidoId(parentId);
			Object[] localidades = manager.findNamesByPartidoId(parentId);

			this.m_codigoForeign.clear();
			this.removeAllItems();			
			this.addItem(new String("Seleccione una Localidad"));
			for (int i = 0; i < localidades.length; i++) {			
				Object[] p = (Object[])localidades[i];
				
				this.addItem(p[1].toString());
				m_codigoForeign.add(p[0].toString());
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void resetFields(){
		this.m_codigoForeign.clear();
		this.removeAllItems();			
		if(this.getItemCount() >= 0){
			this.addItem(new String("Seleccione una Localidad"));
		}
	}
		
}
