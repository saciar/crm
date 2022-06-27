package gui.inventario.componentes;

import java.rmi.RemoteException;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import crm.client.managers.CategEventoManager;

public class ABMCategEventosComboBox extends ABMComboBox{
	private static final Log log = LogFactory.getLog(ABMCategEventosComboBox.class);
	
	public ABMCategEventosComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
		resetFields();
	}
	
	public void loadItems(){
		
		resetFields();	
		
		try {
			Object[] categorias = CategEventoManager.instance().getCategEventosReport();
			
			for (int i = 0; i < categorias.length; i++) {
				Object[] categ = (Object[])categorias[i];
				this.addItem(categ[1].toString());
				m_codigoForeign.add(categ[0].toString());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void resetFields(){
		this.m_codigoForeign.clear();
		this.removeAllItems();	
		if(this.getItemCount() >= 0){
			this.addItem(new String("Seleccione una Categoria de evento"));
		}
	}
}
