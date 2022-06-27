package gui.inventario.componentes;

import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import crm.client.managers.TipoEventoManager;

public class ABMTiposEventosComboBox extends ABMComboBox{
	public ABMTiposEventosComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}	
	
	public void loadItems(){	
		this.addItem(new String("Seleccione un tipo de evento"));		
		try {
			Object[] eventos = TipoEventoManager.instance().getTipoEventosReport();
			
			for (int i = 0; i < eventos.length; i++) {
				Object[] evento = (Object[])eventos[i];
				this.addItem(evento[1].toString());
				m_codigoForeign.add(evento[0].toString());
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
