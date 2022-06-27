package gui.inventario.componentes;

import crm.client.managers.CodigoPostalManager;
import crm.libraries.abm.entities.CodigoPostal;

public class ABMCodigosPostalesComboBox extends ABMComboBox{
	
	private CodigoPostalManager manager =CodigoPostalManager.instance();	
	
	public ABMCodigosPostalesComboBox(){
		super(200,20);
		resetFields();
	}
	
	public void loadItems(String parentId){		
		try{
			//CodigoPostal[] codigosPostales = CodigoPostalManager.instance().findByLocalidadId(parentId);
			Object[] codigosPostales = manager.findNamesByLocalidadId(parentId);
			
			this.m_codigoForeign.clear();
			this.removeAllItems();			
			this.addItem(new String("Seleccione un Codigo Postal"));
			for (int i = 0; i < codigosPostales.length; i++) {
				Object[] p = (Object[])codigosPostales[i];
				
				this.addItem(p[1].toString());
				m_codigoForeign.add(p[1].toString());
			}
			/*CodigoPostal[] codigosPostales = CodigoPostalManager.instance().findByLocalidadId(parentId);

			this.m_codigoForeign.clear();
			this.removeAllItems();			
			this.addItem(new String("Seleccione un Codigo Postal"));
			for (int i = 0; i < codigosPostales.length; i++) {				
				this.addItem(codigosPostales[i].getCodigoPostal());
				m_codigoForeign.add(codigosPostales[i].getCodigoPostal());
			}*/
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void resetFields(){
		this.m_codigoForeign.clear();
		this.removeAllItems();			
		if(this.getItemCount() >= 0){
			this.addItem(new String("Seleccione un Codigo Postal"));
		}
	}
		
}
