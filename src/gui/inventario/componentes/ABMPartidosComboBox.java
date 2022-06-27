package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.PartidoManager;
import crm.libraries.abm.entities.Partido;

public class ABMPartidosComboBox extends ABMComboBox{
	
	private PartidoManager manager = PartidoManager.instance();
	
	public ABMPartidosComboBox(){
		super(200,20);
		this.m_codigoForeign = new Vector();
		this.addItem(new String("Seleccione un partido"));
	}
		
	
	public void loadItems(String parentId){		
		try{
			
			//Partido[] partidos = PartidoManager.instance().findByProvinciaId(parentId);
			Object[] partidos =manager.findNamesByProvinciaId(parentId);

			this.m_codigoForeign.clear();
			this.removeAllItems();			
			this.addItem(new String("Seleccione un Partido"));
			for (int i = 0; i < partidos.length; i++) {	
				Object[] p = (Object[])partidos[i];
				
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
			this.addItem(new String("Seleccione un Partido"));
		}
	}
	
}

	
