package crm.gui.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crm.client.managers.LocalidadManager;
import crm.client.managers.PaisManager;
import crm.client.managers.PartidoManager;
import crm.client.managers.ProvinciaManager;
import crm.client.util.CRMConstantes;
import crm.client.util.Util;
import crm.libraries.abm.entities.Localidad;
import crm.libraries.abm.entities.Partido;
import crm.libraries.abm.entities.Provincia;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class BuscadorLocalidades extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField txtNombre;
	private JDialog owner;
	private BuscadorLocalidadesItem itemSeleccionado;

	public BuscadorLocalidadesItem getItemSeleccionado() {
		return itemSeleccionado;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BuscadorLocalidades dialog = new BuscadorLocalidades(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BuscadorLocalidades(JDialog owner, boolean modal) {
		super(owner,modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BuscadorLocalidades.class.getResource("/gui/inventario/imagenes/logo50px.png")));
		setTitle("Buscador de Localidades");
		setBounds(100, 100, 1035, 539);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{20, 0, 20, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 20, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				JLabel lblNewLabel = new JLabel("Busqueda de Localidades");
				lblNewLabel.setForeground(new Color(CRMConstantes.COLOR_ROJO));
				lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 1;
				panel.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Ingrese el nombre de la localidad a buscar");
				lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
				gbc_lblNewLabel_1.gridwidth = 2;
				gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 3;
				panel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				txtNombre = new JTextField();
				txtNombre.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_txtNombre = new GridBagConstraints();
				gbc_txtNombre.insets = new Insets(0, 0, 0, 5);
				gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtNombre.gridx = 4;
				gbc_txtNombre.gridy = 3;
				panel.add(txtNombre, gbc_txtNombre);
				txtNombre.setColumns(10);
			}
			{
				JButton btnNewButton = new JButton("Buscar");
				btnNewButton.addActionListener(new BuscarActionListener());
				btnNewButton.setIcon(new ImageIcon(BuscadorLocalidades.class.getResource("/gui/inventario/imagenes/magnifier.png")));
				btnNewButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.gridx = 6;
				gbc_btnNewButton.gridy = 3;
				panel.add(btnNewButton, gbc_btnNewButton);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 3;
			contentPanel.add(scrollPane, gbc_scrollPane);
			{
				table = new JTable();
				table.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				table.setModel(new BuscadorLocalidadesTableModel());
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new SeleccionarActionListener());
				okButton.setIcon(new ImageIcon(BuscadorLocalidades.class.getResource("/gui/inventario/imagenes/tick.png")));
				okButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			}
		}
	}
	

	private final class SeleccionarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			BuscadorLocalidadesTableModel model = (BuscadorLocalidadesTableModel)table.getModel();
			itemSeleccionado = model.getRow(table.getSelectedRow());
			setVisible(false);
		}
	}


	private final class BuscarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(txtNombre.getText().length() >=3){
				//ProgressDialogUtil.setType(ProgressDialogUtil.LOAD_DATA);
				//ProgressDialogUtil.launchProcessDialog(Main.getVentana());
				//new Thread(new Runnable() {	
					//public void run() {	
						try {						
							BuscadorLocalidadesTableModel model = new BuscadorLocalidadesTableModel(); 
							Localidad[] l = LocalidadManager.instance().findByField("descripcion",txtNombre.getText());
							for (int i=0; i<l.length; i++){
								BuscadorLocalidadesItem item = new BuscadorLocalidadesItem(); 
								item.setLocalidad(l[i].getDescripcion());
								item.setIdLocalidad(l[i].getCodigoLocalidad());
								Partido part = PartidoManager.instance().getPartidoByCodPartido(l[i].getCodigoPartido());
								item.setPartido(part.getDescripcion());
								item.setIdPartido(part.getCodigoPartido());
								Provincia prov = ProvinciaManager.instance().getProvinciaByCodProvincia(part.getCodigoProvincia());
								item.setProvincia(prov.getDescripcion());
								item.setIdProvincia(prov.getCodigoProvincia());
								item.setPais(PaisManager.instance().getNombrePaisById(prov.getCodigoPais()));
								item.setIdPais(prov.getCodigoPais());
								model.addRow(item);
							}
							if(model.getRowCount() > 0){
								table.setModel(model);
								table.updateUI();
							}
							///ProgressDialogUtil.closeProcessDialog();
							
						} catch (RemoteException ex) {
							Util.errMsg(BuscadorLocalidades.this,"Error al cargar datos externos.",ex);
							//ProgressDialogUtil.closeProcessDialog();
						}
					//}
				//}).start(); 
				
			}
			else
				Util.errMsg(BuscadorLocalidades.this,"Como mínimo debe ingresar 3 carácteres.",null);
		}
	}

}
