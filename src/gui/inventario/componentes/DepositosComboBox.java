package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.DepositosManager;
import crm.libraries.abm.entities.Depositos;

public class DepositosComboBox extends ABMComboBox {
	public DepositosComboBox() {
		super(300, 20);
		m_codigoForeign = new Vector();
	}

	public void loadItems() {

		try {

			Depositos[] depositos = DepositosManager.instance().getAll();

			resetFields();

			for (int i = 0; i < depositos.length; i++) {
				Depositos depo = (Depositos)depositos[i];
				this.addItem(depo.getDepDescripcion());
				m_codigoForeign.add(depo.getCodigo());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void resetFields() {
		this.m_codigoForeign.clear();
		this.removeAllItems();
		if (this.getItemCount() >= 0) {
			this.addItem(new String("Seleccione un deposito"));
		}
	}

}
