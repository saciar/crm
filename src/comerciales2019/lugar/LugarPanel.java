package comerciales2019.lugar;

import javax.swing.JPanel;

import comerciales2019.salas.SalasPanel;
import crm.libraries.abm.entities.LugarEvento;
import gui.inventario.componentes.JPanelGradient;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LugarPanel extends JPanel {

	
	private static LugarEvento lugarSeleccionado;
	private static JLabel lblLugarElegido;
	private static JLabel lblDireccion;
	private JComboBox comboBox;
	
	public static LugarEvento getLugarSeleccionado() {
		return lugarSeleccionado;
	}

	public static void setLugarSeleccionado(LugarEvento lugarSeleccionado) {
		LugarPanel.lugarSeleccionado = lugarSeleccionado;
		lblLugarElegido.setText(lugarSeleccionado.getNombreLugar());
		lblDireccion.setText(lugarSeleccionado.getCalle()+ " "+ lugarSeleccionado.getNumero());
		
		SalasPanel.completarListaSalas(lugarSeleccionado.getCodigo());
		
	}

	/**
	 * Create the panel.
	 */
	public LugarPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanelGradient panelCabecera = new JPanelGradient();
		panelCabecera.setColor1(new Color(236, 31, 47));
		panelCabecera.setColor2(new Color(196, 25, 40));
		add(panelCabecera, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Lugar de evento");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
		panelCabecera.add(lblNewLabel);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{20, 0, 20, 0};
		gbl_panelCentral.rowHeights = new int[]{30, 0, 20, 0, 20, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelDatos = new GridBagConstraints();
		gbc_panelDatos.insets = new Insets(0, 0, 5, 5);
		gbc_panelDatos.fill = GridBagConstraints.BOTH;
		gbc_panelDatos.gridx = 1;
		gbc_panelDatos.gridy = 1;
		panelCentral.add(panelDatos, gbc_panelDatos);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panelDatos.rowHeights = new int[]{0, 0, 10, 0, 10, 0, 0, 0};
		gbl_panelDatos.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelDatos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDatos.setLayout(gbl_panelDatos);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione un lugar de evento");
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panelDatos.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new BuscarActionListener());
		btnBuscar.setIcon(new ImageIcon(LugarPanel.class.getResource("/gui/inventario/imagenes/magnifier.png")));
		btnBuscar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 1;
		panelDatos.add(btnBuscar, gbc_btnBuscar);
		
		JLabel lblNewLabel_2 = new JLabel("Lugar elegido");
		lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		panelDatos.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		lblLugarElegido = new JLabel("");
		lblLugarElegido.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		GridBagConstraints gbc_lblLugarElegido = new GridBagConstraints();
		gbc_lblLugarElegido.anchor = GridBagConstraints.WEST;
		gbc_lblLugarElegido.insets = new Insets(0, 0, 5, 5);
		gbc_lblLugarElegido.gridx = 3;
		gbc_lblLugarElegido.gridy = 3;
		panelDatos.add(lblLugarElegido, gbc_lblLugarElegido);
		
		JLabel lblNewLabel3 = new JLabel("Direccion");
		lblNewLabel3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel3 = new GridBagConstraints();
		gbc_lblNewLabel3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel3.gridx = 1;
		gbc_lblNewLabel3.gridy = 5;
		panelDatos.add(lblNewLabel3, gbc_lblNewLabel3);
		
		lblDireccion = new JLabel("");
		lblDireccion.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblDireccion = new GridBagConstraints();
		gbc_lblDireccion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDireccion.gridx = 3;
		gbc_lblDireccion.gridy = 5;
		panelDatos.add(lblDireccion, gbc_lblDireccion);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		panelCentral.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 10, 0, 10, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_4 = new JLabel("Contacto del lugar");
		lblNewLabel_4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 1;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 3;
		gbc_comboBox.gridy = 1;
		panel.add(comboBox, gbc_comboBox);
		
		JButton btnNewButton = new JButton("Recargar");
		btnNewButton.setIcon(new ImageIcon(LugarPanel.class.getResource("/gui/inventario/imagenes/arrow_refresh.png")));
		btnNewButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 1;
		panel.add(btnNewButton, gbc_btnNewButton);
		
		JLabel lblNewLabel_5 = new JLabel("Telefono");
		lblNewLabel_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 3;
		panel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 3;
		gbc_lblNewLabel_6.gridy = 3;
		panel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Email");
		lblNewLabel_7.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 1;
		gbc_lblNewLabel_7.gridy = 5;
		panel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 3;
		gbc_lblNewLabel_8.gridy = 5;
		panel.add(lblNewLabel_8, gbc_lblNewLabel_8);

	}
	
	private final class BuscarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			BusquedaLugares dialog = new BusquedaLugares(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}

}
