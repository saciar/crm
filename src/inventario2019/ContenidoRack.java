package inventario2019;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crm.libraries.abm.entities.Equipamientos;
import inventario2019.paneles.BaulesPanel;

public class ContenidoRack extends JDialog {
	
	
	/**
	 * Create the dialog.
	 */
	public ContenidoRack(JDialog owner, boolean modal, int codRackSeleccionado) {
		super(owner);
		setTitle("Contenido del Rack");
		this.setModal(modal);		
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		{
			BaulesPanel panel = new BaulesPanel(ContenidoRack.this);
			//BaulesPanel panel = new BaulesPanel(null);
			panel.cargarEquipamientos(codRackSeleccionado);
			getContentPane().add(panel, BorderLayout.CENTER);
		}
	}

}
