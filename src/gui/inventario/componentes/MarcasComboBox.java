package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.MarcasEquiposManager;
import crm.libraries.abm.entities.MarcaEquipo;

public class MarcasComboBox extends ABMComboBox {
	public MarcasComboBox() {
		super(300, 20);
		m_codigoForeign = new Vector();
	}

	public void loadItems() {

		try {

			MarcaEquipo[] depositos = MarcasEquiposManager.instance().getAll();

			resetFields();

			for (int i = 0; i < depositos.length; i++) {
				MarcaEquipo marca = (MarcaEquipo)depositos[i];
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
			this.addItem(new String("Seleccione una marca"));
		}
	}

}
