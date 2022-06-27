package gui.inventario.componentes;

import java.util.Vector;

import crm.client.managers.EquiposSubFamiliasManager;
import crm.libraries.abm.entities.EquiposSubFamilias;

public class SubfamiliasComboBox extends ABMComboBox {
	public SubfamiliasComboBox() {
		super(300, 20);
		m_codigoForeign = new Vector();
	}

	public void loadItems() {

		try {

			EquiposSubFamilias[] familias = EquiposSubFamiliasManager.instance().getAll();

			resetFields();

			for (int i = 0; i < familias.length; i++) {
				EquiposSubFamilias familia = (EquiposSubFamilias)familias[i];
				this.addItem(familia.getEqSubfamDescripcion());
				m_codigoForeign.add(familia.getCodigo());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadItemsPorFamilia(String codFamilia){
		try {

			EquiposSubFamilias[] familias = EquiposSubFamiliasManager.instance().findByField("eqSubfamFamilia", codFamilia);

			resetFields();

			for (int i = 0; i < familias.length; i++) {
				EquiposSubFamilias familia = (EquiposSubFamilias)familias[i];
				this.addItem(familia.getEqSubfamDescripcion());
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
			this.addItem(new String("Seleccione una Subfamilia"));
		}
	}

}
