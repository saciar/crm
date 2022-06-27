package comerciales2019.lugar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crm.client.excepciones.ExcepcionCRM;
import crm.client.managers.LugarEventoManager;
import crm.client.managers.SalaLugarManager;
import crm.client.util.CRMConstantes;
import crm.client.util.Util;
import crm.gui.Main;
import crm.gui.paneles.PanelDireccion;
import crm.libraries.abm.entities.Cliente;
import crm.libraries.abm.entities.LugarEvento;
import crm.libraries.abm.entities.SalaLugar;
import crm.libraries.util.MessageUtil;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import gui.inventario.componentes.ABMPaisesComboBox;
import gui.inventario.componentes.ABMProvinciasComboBox;
import gui.inventario.componentes.ABMPartidosComboBox;
import gui.inventario.componentes.ABMLocalidadesComboBox;
import gui.inventario.componentes.ABMCodigosPostalesComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import comerciales2019.clientes.NuevoCliente;

import javax.swing.ImageIcon;

public class NuevoLugar extends JDialog {
	
	public static final int MODO_NUEVO = 0;
	public static final int MODO_EDICION = 1;

	private final JPanel panelCentral = new JPanel();
	private JTextField txtNombreLugar;
	private JTextField txtResponsableLugar;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private JTextField txtNombreSala;
	private JTable table;
	private JButton btnAgregar;
	
	private LugarEvento lugarAEditar;
	private PanelDireccion panel_direccion;
	
	private int nroSala;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevoLugar dialog = new NuevoLugar(null,true,MODO_NUEVO,null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevoLugar(JDialog owner, boolean modal, int modo, LugarEvento lEditar) {
		super(owner,modal);
		if(modo == MODO_NUEVO){
			setTitle("Nuevo lugar de evento");
		}
		else{
			setTitle("Edicion de lugar de evento");	
			lugarAEditar = lEditar;
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoLugar.class.getResource("/gui/inventario/imagenes/logo50px.png")));
		if(owner!=null)
			setBounds(owner.getBounds().x, owner.getBounds().y, owner.getBounds().width, owner.getBounds().height);
		else
			setBounds(100,100,1280,768);
		getContentPane().setLayout(new BorderLayout());
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{20, 0, 20, 0};
		gbl_panelCentral.rowHeights = new int[]{0, 0, 0, 0, 0, 20, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		{
			JPanel panelDatos = new JPanel();
			panelDatos.setBackground(Color.WHITE);
			GridBagConstraints gbc_panelDatos = new GridBagConstraints();
			gbc_panelDatos.insets = new Insets(0, 0, 5, 5);
			gbc_panelDatos.fill = GridBagConstraints.BOTH;
			gbc_panelDatos.gridx = 1;
			gbc_panelDatos.gridy = 0;
			panelCentral.add(panelDatos, gbc_panelDatos);
			GridBagLayout gbl_panelDatos = new GridBagLayout();
			gbl_panelDatos.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panelDatos.rowHeights = new int[]{0, 0, 10, 0, 10, 0, 10, 0, 0};
			gbl_panelDatos.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panelDatos.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panelDatos.setLayout(gbl_panelDatos);
			{
				JLabel lblNewLabel = new JLabel("Datos del lugar");
				lblNewLabel.setForeground(new Color(CRMConstantes.COLOR_ROJO));
				lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 1;
				panelDatos.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Nombre del lugar");
				lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 3;
				panelDatos.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				txtNombreLugar = new JTextField();
				txtNombreLugar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_txtNombreLugar = new GridBagConstraints();
				gbc_txtNombreLugar.gridwidth = 7;
				gbc_txtNombreLugar.insets = new Insets(0, 0, 5, 5);
				gbc_txtNombreLugar.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtNombreLugar.gridx = 3;
				gbc_txtNombreLugar.gridy = 3;
				panelDatos.add(txtNombreLugar, gbc_txtNombreLugar);
				txtNombreLugar.setColumns(10);
			}
			{
				JLabel lblResponsableDelLugar = new JLabel("Responsable del lugar");
				lblResponsableDelLugar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblResponsableDelLugar = new GridBagConstraints();
				gbc_lblResponsableDelLugar.anchor = GridBagConstraints.WEST;
				gbc_lblResponsableDelLugar.insets = new Insets(0, 0, 5, 5);
				gbc_lblResponsableDelLugar.gridx = 11;
				gbc_lblResponsableDelLugar.gridy = 3;
				panelDatos.add(lblResponsableDelLugar, gbc_lblResponsableDelLugar);
			}
			{
				txtResponsableLugar = new JTextField();
				txtResponsableLugar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_txtResponsableLugar = new GridBagConstraints();
				gbc_txtResponsableLugar.gridwidth = 3;
				gbc_txtResponsableLugar.insets = new Insets(0, 0, 5, 5);
				gbc_txtResponsableLugar.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtResponsableLugar.gridx = 13;
				gbc_txtResponsableLugar.gridy = 3;
				panelDatos.add(txtResponsableLugar, gbc_txtResponsableLugar);
				txtResponsableLugar.setColumns(10);
			}
			{
				JLabel lblTeelfonos = new JLabel("Telefonos");
				lblTeelfonos.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblTeelfonos = new GridBagConstraints();
				gbc_lblTeelfonos.anchor = GridBagConstraints.WEST;
				gbc_lblTeelfonos.insets = new Insets(0, 0, 5, 5);
				gbc_lblTeelfonos.gridx = 1;
				gbc_lblTeelfonos.gridy = 5;
				panelDatos.add(lblTeelfonos, gbc_lblTeelfonos);
			}
			{
				txtTelefono = new JTextField();
				txtTelefono.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_txtTelefono = new GridBagConstraints();
				gbc_txtTelefono.insets = new Insets(0, 0, 5, 5);
				gbc_txtTelefono.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtTelefono.gridx = 3;
				gbc_txtTelefono.gridy = 5;
				panelDatos.add(txtTelefono, gbc_txtTelefono);
				txtTelefono.setColumns(10);
			}
			{
				JLabel lblEmail = new JLabel("Email");
				lblEmail.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblEmail = new GridBagConstraints();
				gbc_lblEmail.anchor = GridBagConstraints.WEST;
				gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
				gbc_lblEmail.gridx = 5;
				gbc_lblEmail.gridy = 5;
				panelDatos.add(lblEmail, gbc_lblEmail);
			}
			{
				txtEmail = new JTextField();
				txtEmail.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_txtEmail = new GridBagConstraints();
				gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
				gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEmail.gridx = 7;
				gbc_txtEmail.gridy = 5;
				panelDatos.add(txtEmail, gbc_txtEmail);
				txtEmail.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 2;
			panelCentral.add(panel, gbc_panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				panel_direccion = new PanelDireccion(this);
				panel.add(panel_direccion, BorderLayout.CENTER);
			}
		}
		{
			JPanel panelSalas = new JPanel();
			panelSalas.setBackground(Color.WHITE);
			GridBagConstraints gbc_panelSalas = new GridBagConstraints();
			gbc_panelSalas.insets = new Insets(0, 0, 5, 5);
			gbc_panelSalas.fill = GridBagConstraints.BOTH;
			gbc_panelSalas.gridx = 1;
			gbc_panelSalas.gridy = 4;
			panelCentral.add(panelSalas, gbc_panelSalas);
			GridBagLayout gbl_panelSalas = new GridBagLayout();
			gbl_panelSalas.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panelSalas.rowHeights = new int[]{0, 0, 5, 0, 5, 0, 0, 0};
			gbl_panelSalas.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
			gbl_panelSalas.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			panelSalas.setLayout(gbl_panelSalas);
			{
				JLabel lblNewLabel_2 = new JLabel("Salas");
				lblNewLabel_2.setForeground(new Color(CRMConstantes.COLOR_ROJO));
				lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_2.gridx = 1;
				gbc_lblNewLabel_2.gridy = 1;
				panelSalas.add(lblNewLabel_2, gbc_lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Nombre de la sala");
				lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 2;
				gbc_lblNewLabel_3.gridy = 3;
				panelSalas.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				txtNombreSala = new JTextField();
				GridBagConstraints gbc_txtNombreSala = new GridBagConstraints();
				gbc_txtNombreSala.insets = new Insets(0, 0, 5, 5);
				gbc_txtNombreSala.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtNombreSala.gridx = 4;
				gbc_txtNombreSala.gridy = 3;
				panelSalas.add(txtNombreSala, gbc_txtNombreSala);
				txtNombreSala.setColumns(10);
			}
			{
				btnAgregar = new JButton("Agregar");
				btnAgregar.addActionListener(new AgregarSalaActionListener());
				btnAgregar.setIcon(new ImageIcon(NuevoLugar.class.getResource("/gui/inventario/imagenes/add.png")));
				btnAgregar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
				gbc_btnAgregar.insets = new Insets(0, 0, 5, 0);
				gbc_btnAgregar.gridx = 6;
				gbc_btnAgregar.gridy = 3;
				panelSalas.add(btnAgregar, gbc_btnAgregar);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
				gbc_scrollPane.gridwidth = 5;
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 2;
				gbc_scrollPane.gridy = 5;
				panelSalas.add(scrollPane, gbc_scrollPane);
				{
					table = new JTable();
					table.setModel(new NuevoLugarTableModel());
					table.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new GuardarLugarActionListener());
				okButton.setIcon(new ImageIcon(NuevoCliente.class.getResource("/gui/inventario/imagenes/tick.png")));
				okButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setIcon(new ImageIcon(NuevoCliente.class.getResource("/gui/inventario/imagenes/cross.png")));
				cancelButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				cancelButton.setActionCommand("Cancel");

			}
		}
		completarPantalla();
	}
	
	private void completarPantalla(){
		if(lugarAEditar!=null){
			panel_direccion.setDomicilio(lugarAEditar.getCalle());
			txtResponsableLugar.setText(lugarAEditar.getContacto());
			panel_direccion.setDepto(lugarAEditar.getDepartamento());
			txtEmail.setText(lugarAEditar.getEmail());
			txtNombreLugar.setText(lugarAEditar.getNombreLugar());
			panel_direccion.setNro(lugarAEditar.getNumero());
			panel_direccion.setPiso(lugarAEditar.getPiso());

			txtTelefono.setText(lugarAEditar.getTelefono1());
			panel_direccion.setCodPais(lugarAEditar.getCodigoPais());
			panel_direccion.setCodProvincia(lugarAEditar.getCodigoProvincia());
			panel_direccion.setCodPartido(lugarAEditar.getCodigoPartido());
			panel_direccion.setCodLocalidad(lugarAEditar.getLocalidad());
			panel_direccion.setCodCP(lugarAEditar.getCodigoPostal());
			
			completarSalas(lugarAEditar.getCodigo());
		}
	}
	
	private void completarSalas(String entityId){
		Object[] salas;
		try {
			salas = SalaLugarManager.instance().getSalaLugarReportByLugar(entityId);

			if(salas != null){
				nroSala = salas.length+1;
				for(int i=0; i<salas.length; i++){
					NuevoLugarItem it = new NuevoLugarItem();
					Object[] sala = (Object[])salas[i];
					it.setCodSala((String)sala[0]);
					it.setNombreSala((String)sala[1]);				
					it.setNumeroSala(i+1);
					((NuevoLugarTableModel)table.getModel()).addRow(it);
				}
				table.updateUI();
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private final class AgregarSalaActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			NuevoLugarItem it = new NuevoLugarItem();
			it.setNombreSala(txtNombreSala.getText());
			it.setNumeroSala(nroSala);
			it.setCodSala(null);
			((NuevoLugarTableModel)table.getModel()).addRow(it);
			table.updateUI();
			nroSala++;
		}
	}

	private class GuardarLugarActionListener implements ActionListener{

		private boolean lugarValido() throws ExcepcionCRM{
			boolean result = true;
			if(txtNombreLugar.getText().isEmpty()){			
				result=false;	
				throw new ExcepcionCRM("Falta el nombre de fantasia");
			}
			else if (((NuevoLugarTableModel)table.getModel()).getRowCount()<=0){
				result=false;	
				throw new ExcepcionCRM("Debe agregar al menos una sala");
			}
			return result;
		}
		
		private SalaLugar createSala(String lugarId, NuevoLugarItem item){
			SalaLugar salaLugar = new SalaLugar();
			try{			 
				if(item.getCodSala() != null){
					salaLugar = SalaLugarManager.instance().getSalaLugarById(item.getCodSala());
					salaLugar.setDescripcion(item.getNombreSala());
					
				}
				else{
					salaLugar.setCodigoLugar(lugarId);				
					salaLugar.setDescripcion(item.getNombreSala());					
					salaLugar.setLargo(null);
					salaLugar.setAncho(null);
					salaLugar.setAltura(null);
					salaLugar.setCapacidad(null);    
				}
			}
			catch (RemoteException e){
				Util.errMsg(NuevoLugar.this,"Error al cargar datos externos ",e);
			}
    		return salaLugar;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				if(lugarValido()){
					LugarEvento lugar;
					if(lugarAEditar== null){
						lugar = new LugarEvento();						
					}
					else{
						lugar = lugarAEditar;
					}
					lugar.setCalle(panel_direccion.getDomicilio());
					lugar.setCodigoPais(panel_direccion.getCodPais());
					lugar.setCodigoPartido(panel_direccion.getCodPartido());
					lugar.setCodigoPostal(panel_direccion.getCodCP());
					lugar.setCodigoProvincia(panel_direccion.getCodProvincia());
					lugar.setContacto(txtResponsableLugar.getText());
					if(!panel_direccion.getDepto().isEmpty())
						lugar.setDepartamento(panel_direccion.getDepto());
					else
						lugar.setDepartamento(null);
					lugar.setEmail(txtEmail.getText());
					lugar.setLocalidad(panel_direccion.getCodLocalidad());
					lugar.setNombreLugar(txtNombreLugar.getText());
					lugar.setNumero(panel_direccion.getNro());
					if(!panel_direccion.getPiso().isEmpty())
						lugar.setPiso(panel_direccion.getPiso());
					else
						lugar.setPiso(null);
					lugar.setTelefono1(txtTelefono.getText());
					
					lugar.setFlotaNextel(null);
					lugar.setIdNextel(null);
					lugar.setCodigoComision(null);
					lugar.setNumero(null);
					
					String codLugarElegido = LugarEventoManager.instance().update(lugar);
					
					int cantSalas = ((NuevoLugarTableModel)table.getModel()).getRowCount();
					for(int i=0; i<cantSalas; i++){  
						SalaLugar sala = createSala(codLugarElegido, ((NuevoLugarTableModel)table.getModel()).getRow(i));
						SalaLugarManager.instance().update(sala);
					}
					MessageUtil.showMessage(NuevoLugar.this, "El lugar de eventos se cargó correctamente");
					dispose();
				}
			} catch (ExcepcionCRM | RemoteException e1) {
				// TODO Auto-generated catch block
				MessageUtil.showErrorMessage(NuevoLugar.this, e1.getMessage());
			}
		}
		
	}

}
