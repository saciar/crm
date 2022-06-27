package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.CondIVAManager;
import crm.libraries.abm.entities.CondIVA;

public class ABMCondIVAComboBox extends ABMComboBox{
	public ABMCondIVAComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	
	
	public void loadItems(){	
		
		try{			

			CondIVA[] condIVAs = CondIVAManager.instance().getAllCondIVAs();

			resetFields();
			
			for (int i = 0; i < condIVAs.length; i++) {				
				this.addItem(condIVAs[i].getDescripcion());
				m_codigoForeign.add(condIVAs[i].getCodigo());				
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
			this.addItem(new String("Seleccione una Condicio IVA"));
		}
	}	
}
