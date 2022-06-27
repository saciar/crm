package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.ClienteContactoManager;
import crm.client.util.Util;
import crm.gui.Main;

public class ABMClienteContactoComboBox extends ABMComboBox{
	public ABMClienteContactoComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItemsForCliente (String codCliente){
		try{	
			resetFields();
			
			Object[] cliente = ClienteContactoManager.instance().getClienteContactoByClienteCodeReport(codCliente);
			for (int i = 0; i < cliente.length; i++) {
				Object[] row = (Object[])cliente[i];
				
				String cod = row[0].toString();
				addItem(row[1].toString());
				
				m_codigoForeign.add(cod);
			}
			
		} 
		catch (Exception e) {
			Util.errMsg(null,"Error al cargar la lista de Contactos",e);
		}
	}
	
	public void loadEmpty(){	
		
		this.addItem(new String("Seleccione un Contacto"));
	}
	
	public void resetFields(){
		this.m_codigoForeign.clear();
		this.removeAllItems();	
		if(this.getItemCount() >= 0){
			this.addItem(new String("Seleccione un Contacto"));
		}
	}
}
