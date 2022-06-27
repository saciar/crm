package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.EquiposFamiliasManager;
import crm.client.managers.FamiliaServManager;
import crm.libraries.abm.entities.EquiposFamilias;

public class FamiliasComboBox extends ABMComboBox {
	public FamiliasComboBox() {
		super(300, 20);
		m_codigoForeign = new Vector();
	}

	/*public void loadItems() {

		try {

			FamiliaServ[] familias = FamiliaServManager.instance()
					.getAllFamiliaServs();

			resetFields();

			for (int i = 0; i < familias.length; i++) {
				this.addItem(familias[i].getDescripcion());
				m_codigoForeign.add(familias[i].getCodigo());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	*/
	public void loadItems() {

		try {

			EquiposFamilias[] familias = EquiposFamiliasManager.instance().getAll();

			resetFields();

			for (int i = 0; i < familias.length; i++) {
				EquiposFamilias familia = (EquiposFamilias)familias[i];
				this.addItem(familia.getEqfamDescripcion());
				m_codigoForeign.add(familia.getCodigo());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void resetFields() {
		this.m_codigoForeign.clear();
		this.removeAllItems();
		if (this.getItemCount() >= 0) {
			this.addItem(new String("Seleccione una Familia"));
		}
	}

}
