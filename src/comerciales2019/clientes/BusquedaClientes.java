package comerciales2019.clientes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;

import comerciales2019.ContenedorPpto;

import java.awt.Toolkit;
import java.awt.GridBagLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import crm.client.managers.ClienteManager;
import crm.client.util.CRMConstantes;
import crm.client.util.Util;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.util.MessageUtil;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.rmi.RemoteException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;

public class BusquedaClientes extends JDialog {

	private final JPanel panelGeneral = new JPanel();
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private JTable table;
	private JRadioButton rdNombre;
	private JRadioButton rdCodigo;
	
	private JDialog owner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BusquedaClientes dialog = new BusquedaClientes(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BusquedaClientes(JDialog owner, boolean modal) {
		super(owner, modal);
		this.owner = owner;
		initComponents();
	}
	
	public void initComponents(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(BusquedaClientes.class.getResource("/gui/inventario/imagenes/logo50px.png")));
		setTitle("Busqueda de clientes");
		if(owner!=null)
			setBounds(owner.getBounds().x, owner.getBounds().y, owner.getBounds().width, owner.getBounds().height);
		else
			setBounds(100,100,900,600);
		getContentPane().setLayout(new BorderLayout());
		panelGeneral.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		panelGeneral.setBackground(Color.WHITE);
		panelGeneral.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelGeneral, BorderLayout.CENTER);
		GridBagLayout gbl_panelGeneral = new GridBagLayout();
		gbl_panelGeneral.columnWidths = new int[]{15, 0, 5, 0, 15, 0};
		gbl_panelGeneral.rowHeights = new int[]{0, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 10, 0};
		gbl_panelGeneral.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelGeneral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelGeneral.setLayout(gbl_panelGeneral);
		ButtonGroup buttonGroup = new ButtonGroup();
		{
			JLabel lblNewLabel = new JLabel("Busqueda de clientes");
			lblNewLabel.setForeground(new Color(CRMConstantes.COLOR_ROJO));
			lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 24));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.gridwidth = 3;
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 1;
			panelGeneral.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			rdNombre = new JRadioButton("Por nombre");
			rdNombre.addActionListener(new NombreClienteActionListener());
			rdNombre.setSelected(true);
			buttonGroup.add(rdNombre);
			rdNombre.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			rdNombre.setOpaque(false);
			GridBagConstraints gbc_rdNombre = new GridBagConstraints();
			gbc_rdNombre.anchor = GridBagConstraints.WEST;
			gbc_rdNombre.insets = new Insets(0, 0, 5, 5);
			gbc_rdNombre.gridx = 1;
			gbc_rdNombre.gridy = 3;
			panelGeneral.add(rdNombre, gbc_rdNombre);
		}
		{
			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					teclaPresionada(arg0);
				}
			});
			txtNombre.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			GridBagConstraints gbc_txtNombre = new GridBagConstraints();
			gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
			gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNombre.gridx = 3;
			gbc_txtNombre.gridy = 3;
			panelGeneral.add(txtNombre, gbc_txtNombre);
			txtNombre.setColumns(10);
		}
		{
			rdCodigo = new JRadioButton("Por codigo");
			rdCodigo.addActionListener(new CodigoActionListener());
			buttonGroup.add(rdCodigo);
			rdCodigo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			rdCodigo.setOpaque(false);
			GridBagConstraints gbc_rdCodigo = new GridBagConstraints();
			gbc_rdCodigo.anchor = GridBagConstraints.WEST;
			gbc_rdCodigo.insets = new Insets(0, 0, 5, 5);
			gbc_rdCodigo.gridx = 1;
			gbc_rdCodigo.gridy = 5;
			panelGeneral.add(rdCodigo, gbc_rdCodigo);
		}
		{
			txtCodigo = new JTextField();
			txtCodigo.setEnabled(false);
			txtCodigo.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					teclaPresionada(arg0);
				}
			});
			txtCodigo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
			gbc_txtCodigo.insets = new Insets(0, 0, 5, 5);
			gbc_txtCodigo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCodigo.gridx = 3;
			gbc_txtCodigo.gridy = 5;
			panelGeneral.add(txtCodigo, gbc_txtCodigo);
			txtCodigo.setColumns(10);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.gridwidth = 3;
			gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 1;
			gbc_scrollPane.gridy = 7;
			panelGeneral.add(scrollPane, gbc_scrollPane);
			{
				table = new JTable();
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				table.setModel(new BuscaClientesTableModel());
				table.getColumnModel().getColumn(0).setPreferredWidth(98);
				scrollPane.setViewportView(table);
			}
		}
		{
			JButton btnNewButton = new JButton("Crear cliente");
			btnNewButton.addActionListener(new AgregarActionListener());
			btnNewButton.setIcon(new ImageIcon(BusquedaClientes.class.getResource("/gui/inventario/imagenes/add.png")));
			btnNewButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton.gridx = 1;
			gbc_btnNewButton.gridy = 9;
			panelGeneral.add(btnNewButton, gbc_btnNewButton);
		}
		{
			JButton btnNewButton_1 = new JButton("Editar cliente");
			btnNewButton_1.addActionListener(new EditarActionListener());
			btnNewButton_1.setIcon(new ImageIcon(BusquedaClientes.class.getResource("/gui/inventario/imagenes/application_edit.png")));
			btnNewButton_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
			gbc_btnNewButton_1.gridx = 1;
			gbc_btnNewButton_1.gridy = 10;
			panelGeneral.add(btnNewButton_1, gbc_btnNewButton_1);
		}
		{
			JPanel panelBotones = new JPanel();
			panelBotones.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
			panelBotones.setBackground(Color.WHITE);
			panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(panelBotones, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new OkActionListener());
				okButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				okButton.setIcon(new ImageIcon(BusquedaClientes.class.getResource("/gui/inventario/imagenes/tick.png")));
				okButton.setActionCommand("OK");
				panelBotones.add(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new CancelarActionListener());
				cancelButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				cancelButton.setIcon(new ImageIcon(BusquedaClientes.class.getResource("/gui/inventario/imagenes/cross.png")));
				cancelButton.setActionCommand("Cancel");
				panelBotones.add(cancelButton);
			}
		}
	}
	
	public void teclaPresionada(KeyEvent arg0) {
		if (arg0.getSource() == this.txtCodigo){
			if(this.txtCodigo.getText().length()>0){
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					buscarCliente();
			}
		}else if(arg0.getSource() == this.txtNombre){
			if(this.txtNombre.getText().length()>=3){
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
					buscarCliente();
			}
		}
	}
	
	private void buscarCliente(){
		try{		
			BuscaClientesTableModel model = new BuscaClientesTableModel();
			if (rdNombre.isSelected()){
				Object[] codesAndFantasyNames = ClienteManager.instance().buscarPorNombreFantasiaOEmpresa(txtNombre.getText());
				
				if (codesAndFantasyNames != null && codesAndFantasyNames.length > 0){
					for (int i = 0; i < codesAndFantasyNames.length; i++){
						BuscaClientesItem item = new BuscaClientesItem();
						Object[] p = (Object[]) codesAndFantasyNames;
						Object[] clientes = (Object[])p[i];
						
						item.setCodigo((String.valueOf(clientes[0])));
						item.setNombreFantasia((String.valueOf(clientes[1])));
						item.setRazonSocial((String.valueOf(clientes[2])));
						
						model.addRow(item);
					}
				}
				else{
					MessageUtil.showMessage(BusquedaClientes.this, "No se encontró ningún cliente con los datos especificados");
				}
				
			}
			else if(rdCodigo.isSelected()){
				Cliente c = ClienteManager.instance().getClienteById(txtCodigo.getText());
				if(c!=null){
					BuscaClientesItem item = new BuscaClientesItem();
					item.setCodigo(c.getCodigo());
					item.setNombreFantasia(c.getNombreFantasia());
					item.setRazonSocial(c.getEmpresa());
					
					model.addRow(item);
				}
				else{
					MessageUtil.showMessage(BusquedaClientes.this, "No se encontró ningún cliente con los datos especificados");
				}
			}
			if(model.getRowCount() > 0){
				table.setModel(model);
				table.updateUI();					
			}
			
		}catch (RemoteException e) {
			Util.errMsg(BusquedaClientes.this, "Error al cargar datos externos", e);
		}
	}

	private final class EditarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRowCount()>0){
				BuscaClientesItem clienteItem = getSelectedItem();		
				try {
					Cliente selectedClient = ClienteManager.instance().getClienteById(clienteItem.getCodigo());				
					if(selectedClient!= null){
						NuevoCliente dialog = new NuevoCliente(owner,true, NuevoCliente.MODO_EDICION, selectedClient);
						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						dialog.setVisible(true);
					}						
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}
	
			}
			else MessageUtil.showMessage(BusquedaClientes.this, "Debe selecionarse un cliente primeramente.");
		}
	}

	private final class AgregarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			NuevoCliente dialog = new NuevoCliente(owner,true, NuevoCliente.MODO_NUEVO, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}

	private final class CancelarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	}

	private final class OkActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(table.getSelectedRowCount()>0){
				BuscaClientesItem clienteItem = getSelectedItem();		
				try {
					Cliente selectedClient = ClienteManager.instance().getClienteById(clienteItem.getCodigo());				
					if(selectedClient!= null)
						ClientePanel.setClienteSeleccionado(selectedClient);
				} catch (RemoteException ex) {
					ex.printStackTrace();
				}

				setVisible(false);		
			}
			else MessageUtil.showMessage(BusquedaClientes.this, "Debe selecionarse un cliente primeramente.");
		}
	}
	
	public BuscaClientesItem getSelectedItem(){
		
		// obtengo el modelo de la tabla
		BuscaClientesTableModel model = (BuscaClientesTableModel)table.getModel();
		
		// obtengo el numero de linea seleccionada
		//int selrow = getTable().getSelectedRow();
		
//		 obtengo el numero de linea seleccionada de la vista
		int selrow = table.getSelectedRow();
		
		//obtengo el numero de linea seleccionada del modelo y no de la vista
		if((System.getProperties().getProperty("os.name").toLowerCase().substring(0,3)).equals("win"))
			selrow = table.convertRowIndexToModel(table.getSelectedRow());
		
		// si no hay nada seleccionado, asumo la primera linea.
		if (selrow < 0)
			selrow = 0;
		
		// obtengo el item de la tabla
		BuscaClientesItem item = model.getRow(selrow);
		
		// asigno los datos
		if (item == null){
			System.out.println("TableRenderSeguimientp::getSelectedItem() - item para row "+selrow+" es null");
		}
		
		return item;
	}

	private final class CodigoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			txtNombre.setEnabled(false);
			txtCodigo.setEnabled(true);
		}
	}

	private final class NombreClienteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			txtNombre.setEnabled(true);
			txtCodigo.setEnabled(false);
	
		}
	}
	
}
