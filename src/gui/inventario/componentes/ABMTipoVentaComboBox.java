package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.TipoVentaManager;

public class ABMTipoVentaComboBox extends ABMComboBox{
	public ABMTipoVentaComboBox(){
		super(200,20);
		m_codigoForeign = new Vector();
	}	
	
	public void loadItems(){	
		this.addItem(new String("Seleccione un tipo de venta"));		
		try {
			Object[] eventos = TipoVentaManager.instance().getTipoVentasReport();
			
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
