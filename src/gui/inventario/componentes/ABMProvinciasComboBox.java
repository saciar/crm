package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.ProvinciaManager;
import crm.libraries.abm.entities.Provincia;


public class ABMProvinciasComboBox extends ABMComboBox{
	
	private static ProvinciaManager manager = ProvinciaManager.instance();
	
	public ABMProvinciasComboBox(){
		super(200,20);		
		this.m_codigoForeign = new Vector();
		this.addItem(new String("Seleccione una Provincia"));
	}
		
	
	public void loadItems(String parentId){		
		try{

			Object[] provincias = manager.findCodAndDescriptionByPaisId(parentId);

			//Provincia[] provincias = ProvinciaManager.instance().findByPaisId(parentId);
			this.m_codigoForeign.clear();
			this.removeAllItems();			
			this.addItem(new String("Seleccione una Provincia"));
			
			for (int i = 0; i < provincias.length; i++) {
				//this.addItem(provincias[i].getDescripcion());
				//m_codigoForeign.add(provincias[i].getCodigo());				
				this.addItem(((Object[])provincias[i])[1].toString());
				m_codigoForeign.add(((Object[])provincias[i])[0].toString());
			}
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}

