package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.OperadorManager;
import crm.libraries.abm.entities.Operador;

public class ChoferesComboBox extends ABMComboBox {
	public ChoferesComboBox() {
		super(300, 20);
		m_codigoForeign = new Vector();
	}

	public void loadItems() {

		try {

			Operador[] choferes = OperadorManager.instance().getOperadorByModalidad("3");

			resetFields();

			for (int i = 0; i < choferes.length; i++) {
				Operador marca = (Operador)choferes[i];
				this.addItem(marca.getApellidoYNombre());
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
			this.addItem(new String("Seleccione un chofer"));
		}
	}

}
