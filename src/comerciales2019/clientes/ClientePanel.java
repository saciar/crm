package comerciales2019.clientes;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

import org.apache.commons.lang.StringUtils;
import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;

import crm.client.managers.ClienteContactoManager;
import crm.client.util.CRMConstantes;
import crm.client.util.Util;
import crm.gui.Main;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.ClienteContacto;
import gui.inventario.componentes.ABMClienteContactoComboBox;
import gui.inventario.componentes.ABMFuenteEventoComboBox;
import gui.inventario.componentes.JPanelGradient;

import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ClientePanel extends JPanel {
	
	private JDialog owner;
	
	//entidades seleccionadas
	private static Cliente clienteSeleccionado;
	private static ClienteContacto contactoClienteSeleccionado;
	
	//componentes
	private static JLabel lblClienteSeleccionado;	
	private static ABMClienteContactoComboBox cmbContactoCliente;
	private JLabel lblTelefonoContacto;
	private JLabel lblEmailContacto;
	private ABMFuenteEventoComboBox cmbFuente;
	
	private final class RecargarFuentesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			cmbFuente.removeAllItems();
			cmbFuente.loadItems();
		}
	}

	private final class RecargarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			cmbContactoCliente.loadEmpty();
			if(ClientePanel.clienteSeleccionado != null){
				ClientePanel.cmbContactoCliente.loadItemsForCliente(clienteSeleccionado.getCodigo());
			}
		}
	}

	private final class BuscarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			BusquedaClientes dialog = new BusquedaClientes(owner,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}

	/**
	 * Create the panel.
	 */
	public ClientePanel() {
		setBackground(Color.WHITE);
		initComponents();
	}
	
	public void setOwner(JDialog owner) {
		this.owner = owner;
	}

	public void initComponents(){
		setLayout(new BorderLayout(0, 0));
		putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		
		JPanelGradient panel = new JPanelGradient();
		panel.setColor1(new Color(236, 31, 47));
		panel.setColor2(new Color(196, 25, 40));
		add(panel, BorderLayout.NORTH);
		JLabel lblNewLabel = new JLabel("Cliente");
		panel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{20, 0, 20, 0};
		gbl_panelCentral.rowHeights = new int[]{30, 0, 20, 0, 20, 0, 10, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JPanel panelDatos = new JPanel();
		panelDatos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelDatos.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelDatos = new GridBagConstraints();
		gbc_panelDatos.insets = new Insets(0, 0, 5, 5);
		gbc_panelDatos.fill = GridBagConstraints.BOTH;
		gbc_panelDatos.gridx = 1;
		gbc_panelDatos.gridy = 1;
		panelCentral.add(panelDatos, gbc_panelDatos);
		GridBagLayout gbl_panelDatos = new GridBagLayout();
		gbl_panelDatos.columnWidths = new int[]{10, 0, 5, 0, 10, 0};
		gbl_panelDatos.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
		gbl_panelDatos.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelDatos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelDatos.setLayout(gbl_panelDatos);
		
		JLabel lblNewLabel_1 = new JLabel("Seleccione un cliente");
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panelDatos.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(ClientePanel.class.getResource("/gui/inventario/imagenes/magnifier.png")));
		btnBuscar.addActionListener(new BuscarActionListener());
		btnBuscar.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.anchor = GridBagConstraints.WEST;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 3;
		gbc_btnBuscar.gridy = 1;
		panelDatos.add(btnBuscar, gbc_btnBuscar);
		
		JLabel lblNewLabel_6 = new JLabel("Cliente seleccionado");
		lblNewLabel_6.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 3;
		panelDatos.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		lblClienteSeleccionado = new JLabel("");
		lblClienteSeleccionado.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		GridBagConstraints gbc_lblClienteSeleccionado = new GridBagConstraints();
		gbc_lblClienteSeleccionado.anchor = GridBagConstraints.WEST;
		gbc_lblClienteSeleccionado.insets = new Insets(0, 0, 5, 5);
		gbc_lblClienteSeleccionado.gridx = 3;
		gbc_lblClienteSeleccionado.gridy = 3;
		panelDatos.add(lblClienteSeleccionado, gbc_lblClienteSeleccionado);
		
		JPanel panelContacto = new JPanel();
		panelContacto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelContacto.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelContacto = new GridBagConstraints();
		gbc_panelContacto.insets = new Insets(0, 0, 5, 5);
		gbc_panelContacto.fill = GridBagConstraints.BOTH;
		gbc_panelContacto.gridx = 1;
		gbc_panelContacto.gridy = 3;
		panelCentral.add(panelContacto, gbc_panelContacto);
		GridBagLayout gbl_panelContacto = new GridBagLayout();
		gbl_panelContacto.columnWidths = new int[]{10, 0, 5, 0, 0, 0, 10, 0};
		gbl_panelContacto.rowHeights = new int[]{10, 0, 10, 0, 10, 0, 0, 0, 10, 0};
		gbl_panelContacto.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelContacto.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelContacto.setLayout(gbl_panelContacto);
		
		JLabel lblNewLabel_2 = new JLabel("Contacto del cliente");
		lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 1;
		panelContacto.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		cmbContactoCliente = new ABMClienteContactoComboBox();
		cmbContactoCliente.loadEmpty();
		cmbContactoCliente.addActionListener(new ContactosActionListener());
		GridBagConstraints gbc_cmbContactoCliente = new GridBagConstraints();
		gbc_cmbContactoCliente.insets = new Insets(0, 0, 5, 5);
		gbc_cmbContactoCliente.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbContactoCliente.gridx = 3;
		gbc_cmbContactoCliente.gridy = 1;
		panelContacto.add(cmbContactoCliente, gbc_cmbContactoCliente);
		
		JButton btnRecargarContactos = new JButton("Recargar");
		btnRecargarContactos.addActionListener(new RecargarActionListener());
		btnRecargarContactos.setIcon(new ImageIcon(ClientePanel.class.getResource("/gui/inventario/imagenes/arrow_refresh.png")));
		btnRecargarContactos.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_btnRecargarContactos = new GridBagConstraints();
		gbc_btnRecargarContactos.anchor = GridBagConstraints.EAST;
		gbc_btnRecargarContactos.insets = new Insets(0, 0, 5, 5);
		gbc_btnRecargarContactos.gridx = 5;
		gbc_btnRecargarContactos.gridy = 1;
		panelContacto.add(btnRecargarContactos, gbc_btnRecargarContactos);
		
		JLabel lblNewLabel_3 = new JLabel("Telefonos");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 3;
		panelContacto.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		lblTelefonoContacto = new JLabel("");
		GridBagConstraints gbc_lblTelefonoContacto = new GridBagConstraints();
		gbc_lblTelefonoContacto.anchor = GridBagConstraints.WEST;
		gbc_lblTelefonoContacto.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefonoContacto.gridx = 3;
		gbc_lblTelefonoContacto.gridy = 3;
		panelContacto.add(lblTelefonoContacto, gbc_lblTelefonoContacto);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.WEST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 5;
		panelContacto.add(lblEmail, gbc_lblEmail);
		
		lblEmailContacto = new JLabel("");
		lblEmailContacto.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblEmailContacto = new GridBagConstraints();
		gbc_lblEmailContacto.anchor = GridBagConstraints.WEST;
		gbc_lblEmailContacto.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailContacto.gridx = 3;
		gbc_lblEmailContacto.gridy = 5;
		panelContacto.add(lblEmailContacto, gbc_lblEmailContacto);
		
		JLabel lblNewLabel_5 = new JLabel("Fuente de contacto");
		lblNewLabel_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 1;
		gbc_lblNewLabel_5.gridy = 7;
		panelContacto.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		cmbFuente = new ABMFuenteEventoComboBox();
		cmbFuente.loadItems();
		cmbFuente.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_cmbFuente = new GridBagConstraints();
		gbc_cmbFuente.insets = new Insets(0, 0, 5, 5);
		gbc_cmbFuente.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbFuente.gridx = 3;
		gbc_cmbFuente.gridy = 7;
		panelContacto.add(cmbFuente, gbc_cmbFuente);
		
		JButton btnRecargarFuentes = new JButton("Recargar");
		btnRecargarFuentes.addActionListener(new RecargarFuentesActionListener());
		btnRecargarFuentes.setIcon(new ImageIcon(ClientePanel.class.getResource("/gui/inventario/imagenes/arrow_refresh.png")));
		btnRecargarFuentes.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_btnRecargarFuentes = new GridBagConstraints();
		gbc_btnRecargarFuentes.insets = new Insets(0, 0, 5, 5);
		gbc_btnRecargarFuentes.gridx = 5;
		gbc_btnRecargarFuentes.gridy = 7;
		panelContacto.add(btnRecargarFuentes, gbc_btnRecargarFuentes);
		
		JPanel panelObservacion = new JPanel();
		panelObservacion.setBackground(Color.WHITE);
		GridBagConstraints gbc_panelObservacion = new GridBagConstraints();
		gbc_panelObservacion.insets = new Insets(0, 0, 5, 5);
		gbc_panelObservacion.fill = GridBagConstraints.BOTH;
		gbc_panelObservacion.gridx = 1;
		gbc_panelObservacion.gridy = 5;
		panelCentral.add(panelObservacion, gbc_panelObservacion);
		GridBagLayout gbl_panelObservacion = new GridBagLayout();
		gbl_panelObservacion.columnWidths = new int[]{0, 0, 0};
		gbl_panelObservacion.rowHeights = new int[]{0, 0, 10, 200, 0, 0};
		gbl_panelObservacion.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panelObservacion.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelObservacion.setLayout(gbl_panelObservacion);
		
		JLabel lblNewLabel_4 = new JLabel("Observaciones");
		lblNewLabel_4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 1;
		panelObservacion.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		textArea.setRows(5);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 3;
		panelObservacion.add(textArea, gbc_textArea);

	}

	public static Cliente getClienteSeleccionado() {
		return clienteSeleccionado;
	}

	public static void setClienteSeleccionado(Cliente clienteSeleccionado) {
		ClientePanel.clienteSeleccionado = clienteSeleccionado;
		lblClienteSeleccionado.setText(clienteSeleccionado.getEmpresa());
		cmbContactoCliente.loadItemsForCliente(clienteSeleccionado.getCodigo());
	}
	
	private class ContactosActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			String codigo = cmbContactoCliente.searchForeign();
			
			if (StringUtils.isNotBlank(codigo) && !codigo.equals("0")){
				try {
					contactoClienteSeleccionado = ClienteContactoManager.instance().getClienteContactoById(codigo);					
					lblTelefonoContacto.setText(contactoClienteSeleccionado.getTelefono1()+" / "+ contactoClienteSeleccionado.getTelefono2());
					lblEmailContacto.setText(contactoClienteSeleccionado.getEmail());				
				} catch (RemoteException ex) {
					Util.errMsg(null,"Error al cargar datos externos ",ex);
					return;
				}
			}
			else {
				lblTelefonoContacto.setText("");
				lblEmailContacto.setText("");
			}
		}
		
	}

}
