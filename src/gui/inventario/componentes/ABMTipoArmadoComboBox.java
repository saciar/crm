package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.TipoArmadoManager;
import crm.client.managers.TipoEventoManager;
import crm.libraries.abm.entities.TipoArmado;

public class ABMTipoArmadoComboBox extends ABMComboBox{
	public ABMTipoArmadoComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}
	
	public void loadItems(){
		this.addItem(new String("Seleccione un tipo de armado"));
		try{
			Object[] tiposEvento = TipoArmadoManager.instance().getTipoArmadoReport();
			
			for (int i = 0; i < tiposEvento.length; i++) {
				Object[] evento = (Object[])tiposEvento[i];
				this.addItem(evento[1].toString());
				m_codigoForeign.add(evento[0].toString());
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
