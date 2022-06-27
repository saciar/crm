package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.TransporteManager;
import crm.libraries.abm.entities.Transporte;

public class TransportesComboBox extends ABMComboBox {
	public TransportesComboBox() {
		super(300, 20);
		m_codigoForeign = new Vector();
	}

	public void loadItems() {

		try {

			Transporte[] transportes = TransporteManager.instance().getAll();

			resetFields();

			for (int i = 0; i < transportes.length; i++) {
				Transporte marca = (Transporte)transportes[i];
				this.addItem(marca.getDescripcion());
				m_codigoForeign.add(marca.getCodigo());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void resetFields() {
		this.m_codigoForeign.clear();
		this.removeAllItems();
		if (this.getItemCount() >= 0) {
			this.addItem(new String("Seleccione un transporte"));
		}
	}

}
