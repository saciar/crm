package comerciales2019.clientes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.data.general.CombinationDataset;
import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;

import com.lowagie.text.pdf.CMapAwareDocumentFont;

import crm.client.excepciones.ExcepcionCRM;
import crm.client.managers.ClienteManager;
import crm.client.util.CRMConstantes;
import crm.client.util.Util;
import crm.libraries.abm.entities.Cliente;
import gui.inventario.componentes.ABMCodigosPostalesComboBox;
import gui.inventario.componentes.ABMCondIVAComboBox;
import gui.inventario.componentes.ABMLocalidadesComboBox;
import gui.inventario.componentes.ABMPaisesComboBox;
import gui.inventario.componentes.ABMPartidosComboBox;
import gui.inventario.componentes.ABMProvinciasComboBox;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Date;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class NuevoCliente extends JDialog {

	public static final int MODO_NUEVO = 0;
	public static final int MODO_EDICION = 1;
	
	private final JPanel panelCentral = new JPanel();
	private JTextField txtNombreFantasia;
	private JTextField txtContactoInicial;
	private JTextField txtTelefonoContacto;
	private JTextField txtEmailContacto;
	private JTextField txtRazonSocial;
	private JTextField txtCuit;
	private JTextField txtCalle;
	private JTextField txtNumero;
	private JTextField txtPiso;
	private JTextField txtDepto;
	private ABMPaisesComboBox cmbPaises;
	private ABMProvinciasComboBox cmbProvincias;
	private ABMPartidosComboBox cmbPartidos;
	private ABMLocalidadesComboBox cmbLocalidades;
	private ABMCodigosPostalesComboBox cmbCP;
	private ABMCondIVAComboBox cmbCondicionIVA;
	
	private Cliente clienteAEditar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			NuevoCliente dialog = new NuevoCliente(null,true,MODO_NUEVO, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public NuevoCliente(JDialog owner, boolean modal, int modo, Cliente aEditar) {
		super(owner,modal);
		if(modo == MODO_NUEVO){
			setTitle("Nuevo Cliente");
		}
		else{
			setTitle("Edicion de Cliente");	
			clienteAEditar = aEditar;
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage(NuevoCliente.class.getResource("/gui/inventario/imagenes/logo50px.png")));
		if(owner!=null)
			setBounds(owner.getBounds().x, owner.getBounds().y, owner.getBounds().width, owner.getBounds().height);
		else
			setBounds(100,100,1280,768);
		getContentPane().setLayout(new BorderLayout());
		panelCentral.setBackground(Color.WHITE);
		panelCentral.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		panelCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{20, 0, 20, 0};
		gbl_panelCentral.rowHeights = new int[]{20, 0, 10, 0, 20, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		{
			JPanel panelDatosGral = new JPanel();
			panelDatosGral.setBackground(Color.WHITE);
			GridBagConstraints gbc_panelDatosGral = new GridBagConstraints();
			gbc_panelDatosGral.insets = new Insets(0, 0, 5, 5);
			gbc_panelDatosGral.fill = GridBagConstraints.BOTH;
			gbc_panelDatosGral.gridx = 1;
			gbc_panelDatosGral.gridy = 1;
			panelCentral.add(panelDatosGral, gbc_panelDatosGral);
			GridBagLayout gbl_panelDatosGral = new GridBagLayout();
			gbl_panelDatosGral.columnWidths = new int[]{0, 0, 5, 0, 10, 0, 5, 0, 0, 0};
			gbl_panelDatosGral.rowHeights = new int[]{0, 0, 10, 0, 10, 0, 0, 0};
			gbl_panelDatosGral.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panelDatosGral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelDatosGral.setLayout(gbl_panelDatosGral);
			{
				JLabel lblDatosDelCliente = new JLabel("Datos del cliente General ");
				lblDatosDelCliente.setForeground(new Color(CRMConstantes.COLOR_ROJO));
				lblDatosDelCliente.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
				GridBagConstraints gbc_lblDatosDelCliente = new GridBagConstraints();
				gbc_lblDatosDelCliente.gridwidth = 7;
				gbc_lblDatosDelCliente.anchor = GridBagConstraints.WEST;
				gbc_lblDatosDelCliente.insets = new Insets(0, 0, 5, 5);
				gbc_lblDatosDelCliente.gridx = 1;
				gbc_lblDatosDelCliente.gridy = 1;
				panelDatosGral.add(lblDatosDelCliente, gbc_lblDatosDelCliente);
			}
			{
				JLabel lblNombreDeFantasia = new JLabel("Nombre de fantasia");
				lblNombreDeFantasia.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblNombreDeFantasia = new GridBagConstraints();
				gbc_lblNombreDeFantasia.anchor = GridBagConstraints.WEST;
				gbc_lblNombreDeFantasia.insets = new Insets(0, 0, 5, 5);
				gbc_lblNombreDeFantasia.gridx = 1;
				gbc_lblNombreDeFantasia.gridy = 3;
				panelDatosGral.add(lblNombreDeFantasia, gbc_lblNombreDeFantasia);
			}
			{
				txtNombreFantasia = new JTextField();
				txtNombreFantasia.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_txtNombreFantasia = new GridBagConstraints();
				gbc_txtNombreFantasia.insets = new Insets(0, 0, 5, 5);
				gbc_txtNombreFantasia.anchor = GridBagConstraints.NORTH;
				gbc_txtNombreFantasia.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtNombreFantasia.gridx = 3;
				gbc_txtNombreFantasia.gridy = 3;
				panelDatosGral.add(txtNombreFantasia, gbc_txtNombreFantasia);
				txtNombreFantasia.setColumns(10);
			}
			{
				JLabel lblContactoInicial = new JLabel("Contacto inicial");
				lblContactoInicial.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblContactoInicial = new GridBagConstraints();
				gbc_lblContactoInicial.anchor = GridBagConstraints.WEST;
				gbc_lblContactoInicial.insets = new Insets(0, 0, 5, 5);
				gbc_lblContactoInicial.gridx = 5;
				gbc_lblContactoInicial.gridy = 3;
				panelDatosGral.add(lblContactoInicial, gbc_lblContactoInicial);
			}
			{
				txtContactoInicial = new JTextField();
				txtContactoInicial.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_txtContactoInicial = new GridBagConstraints();
				gbc_txtContactoInicial.insets = new Insets(0, 0, 5, 5);
				gbc_txtContactoInicial.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtContactoInicial.gridx = 7;
				gbc_txtContactoInicial.gridy = 3;
				panelDatosGral.add(txtContactoInicial, gbc_txtContactoInicial);
				txtContactoInicial.setColumns(10);
			}
			{
				JLabel lblTelefono = new JLabel("Telefono");
				lblTelefono.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblTelefono = new GridBagConstraints();
				gbc_lblTelefono.anchor = GridBagConstraints.WEST;
				gbc_lblTelefono.insets = new Insets(0, 0, 5, 5);
				gbc_lblTelefono.gridx = 1;
				gbc_lblTelefono.gridy = 5;
				panelDatosGral.add(lblTelefono, gbc_lblTelefono);
			}
			{
				txtTelefonoContacto = new JTextField();
				txtTelefonoContacto.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_txtTelefonoContacto = new GridBagConstraints();
				gbc_txtTelefonoContacto.insets = new Insets(0, 0, 5, 5);
				gbc_txtTelefonoContacto.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtTelefonoContacto.gridx = 3;
				gbc_txtTelefonoContacto.gridy = 5;
				panelDatosGral.add(txtTelefonoContacto, gbc_txtTelefonoContacto);
				txtTelefonoContacto.setColumns(10);
			}
			{
				JLabel lblEmail = new JLabel("Email");
				lblEmail.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblEmail = new GridBagConstraints();
				gbc_lblEmail.anchor = GridBagConstraints.WEST;
				gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
				gbc_lblEmail.gridx = 5;
				gbc_lblEmail.gridy = 5;
				panelDatosGral.add(lblEmail, gbc_lblEmail);
			}
			{
				txtEmailContacto = new JTextField();
				txtEmailContacto.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_txtEmailContacto = new GridBagConstraints();
				gbc_txtEmailContacto.insets = new Insets(0, 0, 5, 5);
				gbc_txtEmailContacto.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtEmailContacto.gridx = 7;
				gbc_txtEmailContacto.gridy = 5;
				panelDatosGral.add(txtEmailContacto, gbc_txtEmailContacto);
				txtEmailContacto.setColumns(10);
			}
		}
		{
			JPanel panelDatosLegales = new JPanel();
			panelDatosLegales.setBackground(Color.WHITE);
			panelDatosLegales.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
			GridBagConstraints gbc_panelDatosLegales = new GridBagConstraints();
			gbc_panelDatosLegales.insets = new Insets(0, 0, 5, 5);
			gbc_panelDatosLegales.fill = GridBagConstraints.BOTH;
			gbc_panelDatosLegales.gridx = 1;
			gbc_panelDatosLegales.gridy = 3;
			panelCentral.add(panelDatosLegales, gbc_panelDatosLegales);
			GridBagLayout gbl_panelDatosLegales = new GridBagLayout();
			gbl_panelDatosLegales.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0};
			gbl_panelDatosLegales.rowHeights = new int[]{0, 0, 10, 0, 10, 0, 10, 0, 10, 0, 0, 0};
			gbl_panelDatosLegales.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panelDatosLegales.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			panelDatosLegales.setLayout(gbl_panelDatosLegales);
			{
				JLabel lblNewLabel = new JLabel("Datos Legales");
				lblNewLabel.setForeground(new Color(CRMConstantes.COLOR_ROJO));
				lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.gridwidth = 15;
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 1;
				panelDatosLegales.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("Razon Social");
				lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 3;
				panelDatosLegales.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				txtRazonSocial = new JTextField();
				txtRazonSocial.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_txtRazonSocial = new GridBagConstraints();
				gbc_txtRazonSocial.gridwidth = 13;
				gbc_txtRazonSocial.insets = new Insets(0, 0, 5, 5);
				gbc_txtRazonSocial.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtRazonSocial.gridx = 3;
				gbc_txtRazonSocial.gridy = 3;
				panelDatosLegales.add(txtRazonSocial, gbc_txtRazonSocial);
				txtRazonSocial.setColumns(10);
			}
			{
				JLabel lblCondicionDelIva = new JLabel("Condicion del IVA");
				lblCondicionDelIva.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblCondicionDelIva = new GridBagConstraints();
				gbc_lblCondicionDelIva.anchor = GridBagConstraints.WEST;
				gbc_lblCondicionDelIva.insets = new Insets(0, 0, 5, 5);
				gbc_lblCondicionDelIva.gridx = 1;
				gbc_lblCondicionDelIva.gridy = 5;
				panelDatosLegales.add(lblCondicionDelIva, gbc_lblCondicionDelIva);
			}
			{
				cmbCondicionIVA = new ABMCondIVAComboBox();
				cmbCondicionIVA.loadItems();
				cmbCondicionIVA.addActionListener(new CondicionIVAActionListener());				
				cmbCondicionIVA.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_cmbCondicionIVA = new GridBagConstraints();
				gbc_cmbCondicionIVA.gridwidth = 2;
				gbc_cmbCondicionIVA.insets = new Insets(0, 0, 5, 5);
				gbc_cmbCondicionIVA.fill = GridBagConstraints.HORIZONTAL;
				gbc_cmbCondicionIVA.gridx = 3;
				gbc_cmbCondicionIVA.gridy = 5;
				panelDatosLegales.add(cmbCondicionIVA, gbc_cmbCondicionIVA);
			}
			{
				JLabel lblCuit = new JLabel("CUIT");
				lblCuit.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblCuit = new GridBagConstraints();
				gbc_lblCuit.anchor = GridBagConstraints.WEST;
				gbc_lblCuit.insets = new Insets(0, 0, 5, 5);
				gbc_lblCuit.gridx = 1;
				gbc_lblCuit.gridy = 7;
				panelDatosLegales.add(lblCuit, gbc_lblCuit);
			}
			{
				txtCuit = new JTextField();
				txtCuit.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_txtCuit = new GridBagConstraints();
				gbc_txtCuit.gridwidth = 2;
				gbc_txtCuit.insets = new Insets(0, 0, 5, 5);
				gbc_txtCuit.fill = GridBagConstraints.HORIZONTAL;
				gbc_txtCuit.gridx = 3;
				gbc_txtCuit.gridy = 7;
				panelDatosLegales.add(txtCuit, gbc_txtCuit);
				txtCuit.setColumns(10);
			}
			{
				JPanel panelGeo = new JPanel();
				panelGeo.setBackground(Color.WHITE);
				GridBagConstraints gbc_panelGeo = new GridBagConstraints();
				gbc_panelGeo.gridwidth = 15;
				gbc_panelGeo.insets = new Insets(0, 0, 5, 5);
				gbc_panelGeo.fill = GridBagConstraints.BOTH;
				gbc_panelGeo.gridx = 1;
				gbc_panelGeo.gridy = 9;
				panelDatosLegales.add(panelGeo, gbc_panelGeo);
				GridBagLayout gbl_panelGeo = new GridBagLayout();
				gbl_panelGeo.columnWidths = new int[]{0, 210, 5, 50, 210, 5, 0, 210, 5, 0, 210, 5, 0};
				gbl_panelGeo.rowHeights = new int[]{0, 10, 0, 10, 0, 0};
				gbl_panelGeo.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				gbl_panelGeo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
				panelGeo.setLayout(gbl_panelGeo);
				{
					JLabel lblNewLabel_2 = new JLabel("Domicilio");
					lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
					gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
					gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
					gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_2.gridx = 0;
					gbc_lblNewLabel_2.gridy = 0;
					panelGeo.add(lblNewLabel_2, gbc_lblNewLabel_2);
				}
				{
					txtCalle = new JTextField();
					txtCalle.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_txtCalle = new GridBagConstraints();
					gbc_txtCalle.fill = GridBagConstraints.HORIZONTAL;
					gbc_txtCalle.insets = new Insets(0, 0, 5, 5);
					gbc_txtCalle.gridx = 1;
					gbc_txtCalle.gridy = 0;
					panelGeo.add(txtCalle, gbc_txtCalle);
					txtCalle.setColumns(10);
				}
				{
					JLabel lblNro = new JLabel("Nro");
					lblNro.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_lblNro = new GridBagConstraints();
					gbc_lblNro.anchor = GridBagConstraints.EAST;
					gbc_lblNro.insets = new Insets(0, 0, 5, 5);
					gbc_lblNro.gridx = 3;
					gbc_lblNro.gridy = 0;
					panelGeo.add(lblNro, gbc_lblNro);
				}
				{
					txtNumero = new JTextField();
					txtNumero.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_txtNumero = new GridBagConstraints();
					gbc_txtNumero.anchor = GridBagConstraints.WEST;
					gbc_txtNumero.insets = new Insets(0, 0, 5, 5);
					gbc_txtNumero.gridx = 4;
					gbc_txtNumero.gridy = 0;
					panelGeo.add(txtNumero, gbc_txtNumero);
					txtNumero.setColumns(10);
				}
				{
					JLabel lblNewLabel_3 = new JLabel("Piso");
					lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
					gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
					gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_3.gridx = 6;
					gbc_lblNewLabel_3.gridy = 0;
					panelGeo.add(lblNewLabel_3, gbc_lblNewLabel_3);
				}
				{
					txtPiso = new JTextField();
					txtPiso.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_txtPiso = new GridBagConstraints();
					gbc_txtPiso.anchor = GridBagConstraints.WEST;
					gbc_txtPiso.insets = new Insets(0, 0, 5, 5);
					gbc_txtPiso.gridx = 7;
					gbc_txtPiso.gridy = 0;
					panelGeo.add(txtPiso, gbc_txtPiso);
					txtPiso.setColumns(10);
				}
				{
					JLabel lblNewLabel_6 = new JLabel("Depto");
					lblNewLabel_6.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
					gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
					gbc_lblNewLabel_6.fill = GridBagConstraints.VERTICAL;
					gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_6.gridx = 9;
					gbc_lblNewLabel_6.gridy = 0;
					panelGeo.add(lblNewLabel_6, gbc_lblNewLabel_6);
				}
				{
					txtDepto = new JTextField();
					txtDepto.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_txtDepto = new GridBagConstraints();
					gbc_txtDepto.anchor = GridBagConstraints.WEST;
					gbc_txtDepto.insets = new Insets(0, 0, 5, 5);
					gbc_txtDepto.gridx = 10;
					gbc_txtDepto.gridy = 0;
					panelGeo.add(txtDepto, gbc_txtDepto);
					txtDepto.setColumns(10);
				}
				{
					JLabel lblNewLabel_4 = new JLabel("Pais");
					lblNewLabel_4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
					gbc_lblNewLabel_4.anchor = GridBagConstraints.WEST;
					gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_4.gridx = 0;
					gbc_lblNewLabel_4.gridy = 2;
					panelGeo.add(lblNewLabel_4, gbc_lblNewLabel_4);
				}
				{
					cmbPaises = new ABMPaisesComboBox();
					cmbPaises.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					cmbPaises.loadItems();
					cmbPaises.addActionListener(new PaisesActionListener());
					GridBagConstraints gbc_cmbPaises = new GridBagConstraints();
					gbc_cmbPaises.fill = GridBagConstraints.HORIZONTAL;
					gbc_cmbPaises.insets = new Insets(0, 0, 5, 5);
					gbc_cmbPaises.gridx = 1;
					gbc_cmbPaises.gridy = 2;
					panelGeo.add(cmbPaises, gbc_cmbPaises);
				}
				{
					JLabel lblNewLabel_5 = new JLabel("Provincia");
					lblNewLabel_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
					gbc_lblNewLabel_5.anchor = GridBagConstraints.WEST;
					gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
					gbc_lblNewLabel_5.gridx = 3;
					gbc_lblNewLabel_5.gridy = 2;
					panelGeo.add(lblNewLabel_5, gbc_lblNewLabel_5);
				}
				{
					cmbProvincias = new ABMProvinciasComboBox();
					cmbProvincias.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					cmbProvincias.setEnabled(false);
					cmbProvincias.loadItems();
					cmbProvincias.addActionListener(new ProvinciasActionListener());
					GridBagConstraints gbc_cmbProvincias = new GridBagConstraints();
					gbc_cmbProvincias.fill = GridBagConstraints.HORIZONTAL;
					gbc_cmbProvincias.insets = new Insets(0, 0, 5, 5);
					gbc_cmbProvincias.gridx = 4;
					gbc_cmbProvincias.gridy = 2;
					panelGeo.add(cmbProvincias, gbc_cmbProvincias);
				}
				{
					JLabel lblPartido = new JLabel("Partido");
					lblPartido.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_lblPartido = new GridBagConstraints();
					gbc_lblPartido.anchor = GridBagConstraints.WEST;
					gbc_lblPartido.insets = new Insets(0, 0, 5, 5);
					gbc_lblPartido.gridx = 6;
					gbc_lblPartido.gridy = 2;
					panelGeo.add(lblPartido, gbc_lblPartido);
				}
				{
					cmbPartidos = new ABMPartidosComboBox();
					cmbPartidos.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					cmbPartidos.setEnabled(false);
					cmbPartidos.loadItems();
					cmbPartidos.addActionListener(new PartidosActionListener());
					GridBagConstraints gbc_cmbPartidos = new GridBagConstraints();
					gbc_cmbPartidos.fill = GridBagConstraints.HORIZONTAL;
					gbc_cmbPartidos.insets = new Insets(0, 0, 5, 5);
					gbc_cmbPartidos.gridx = 7;
					gbc_cmbPartidos.gridy = 2;
					panelGeo.add(cmbPartidos, gbc_cmbPartidos);
				}
				{
					JLabel lblLocalidad = new JLabel("Localidad");
					lblLocalidad.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_lblLocalidad = new GridBagConstraints();
					gbc_lblLocalidad.insets = new Insets(0, 0, 0, 5);
					gbc_lblLocalidad.anchor = GridBagConstraints.WEST;
					gbc_lblLocalidad.gridx = 0;
					gbc_lblLocalidad.gridy = 4;
					panelGeo.add(lblLocalidad, gbc_lblLocalidad);
				}
				{
					cmbLocalidades = new ABMLocalidadesComboBox();
					cmbLocalidades.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					cmbLocalidades.setEnabled(false);
					cmbLocalidades.loadItems();
					cmbLocalidades.addActionListener(new LocalidadesActionListener());
					GridBagConstraints gbc_cmbLocalidades = new GridBagConstraints();
					gbc_cmbLocalidades.fill = GridBagConstraints.HORIZONTAL;
					gbc_cmbLocalidades.insets = new Insets(0, 0, 0, 5);
					gbc_cmbLocalidades.gridx = 1;
					gbc_cmbLocalidades.gridy = 4;
					panelGeo.add(cmbLocalidades, gbc_cmbLocalidades);
				}
				{
					JLabel lblCp = new JLabel("CP");
					lblCp.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_lblCp = new GridBagConstraints();
					gbc_lblCp.anchor = GridBagConstraints.WEST;
					gbc_lblCp.insets = new Insets(0, 0, 0, 5);
					gbc_lblCp.gridx = 3;
					gbc_lblCp.gridy = 4;
					panelGeo.add(lblCp, gbc_lblCp);
				}
				{
					cmbCP = new ABMCodigosPostalesComboBox();
					cmbCP.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					cmbCP.setEnabled(false);
					cmbCP.loadItems();
					GridBagConstraints gbc_cmbCP = new GridBagConstraints();
					gbc_cmbCP.fill = GridBagConstraints.HORIZONTAL;
					gbc_cmbCP.insets = new Insets(0, 0, 0, 5);
					gbc_cmbCP.gridx = 4;
					gbc_cmbCP.gridy = 4;
					panelGeo.add(cmbCP, gbc_cmbCP);
				}
				{
					JButton btnNewButton = new JButton("Buscar localidad");
					btnNewButton.setIcon(new ImageIcon(NuevoCliente.class.getResource("/gui/inventario/imagenes/magnifier.png")));
					btnNewButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
					gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
					gbc_btnNewButton.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
					gbc_btnNewButton.gridx = 7;
					gbc_btnNewButton.gridy = 4;
					panelGeo.add(btnNewButton, gbc_btnNewButton);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new GuardarClienteActionListener());
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
				buttonPane.add(cancelButton);
			}
		}
		completarPantalla();
	}
	
	private void completarPantalla(){
		if(clienteAEditar!=null){
			txtCalle.setText(clienteAEditar.getCalle());
			txtContactoInicial.setText(clienteAEditar.getPagoContacto());
			txtCuit.setText(clienteAEditar.getCuit());
			txtDepto.setText(clienteAEditar.getDepartamento());
			//txtEmailContacto.setText(clienteAEditar.get);
			txtNombreFantasia.setText(clienteAEditar.getNombreFantasia());
			txtNumero.setText(clienteAEditar.getNumero());
			txtPiso.setText(clienteAEditar.getPiso());
			txtRazonSocial.setText(clienteAEditar.getEmpresa());
			txtTelefonoContacto.setText(clienteAEditar.getPagoTelefono());
			cmbCondicionIVA.setForeign(clienteAEditar.getIva());	
			cmbPaises.setForeign(clienteAEditar.getPais());
			cmbProvincias.setForeign(clienteAEditar.getProvincia());
			cmbPartidos.setForeign(clienteAEditar.getPartido());
			cmbLocalidades.setForeign(clienteAEditar.getLocalidad());
			cmbCP.setForeign(clienteAEditar.getCodigoPostal());
		}
	}
	
	private final class GuardarClienteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			try {
				if(clienteValido()){
					Cliente cliente;
					if(clienteAEditar== null){
						cliente = new Cliente();						
					}
					else{
						cliente = clienteAEditar;
					}
					cliente.setCalle(txtCalle.getText());
					cliente.setCodigoPostal(cmbCP.searchForeign());
					if(cmbCondicionIVA.searchForeign().equals("5")){
						cliente.setCuit(null);
					}
					else{
						cliente.setCuit(txtCuit.getText());
					}
					//cliente.setEmailContacto(txtEmailContacto.getText());
					cliente.setDepartamento(txtDepto.getText());
					cliente.setEmpresa(txtRazonSocial.getText());		
					//cliente.setFechaModificacion(new Date());
					cliente.setIva(cmbCondicionIVA.searchForeign());
					cliente.setLocalidad(cmbLocalidades.searchForeign());
					cliente.setNombreFantasia(txtNombreFantasia.getText());
					cliente.setNumero(txtNumero.getText());
					cliente.setPagoContacto(txtContactoInicial.getText());
					cliente.setPagoTelefono(txtTelefonoContacto.getText());
					cliente.setPais(cmbPaises.searchForeign());
					cliente.setPartido(cmbPartidos.searchForeign());
					cliente.setPiso(txtPiso.getText());
					cliente.setProvincia(cmbProvincias.searchForeign());				
					ClienteManager.instance().update(cliente);
					Util.alertMsg(NuevoCliente.this, "El cliente se grabo con exito!");
					dispose();
				}
				else
					Util.errMsg(NuevoCliente.this, "Debe completar todos los datos", null);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				Util.errMsg(NuevoCliente.this, "Ocurrio un error al grabar el cliente nuevo", null);
			}
			catch (ExcepcionCRM e2) {
				Util.errMsg(NuevoCliente.this, "Debe completar todos los datos. "+e2.getMessage(), null);
			} 

		}
	}
	
	private boolean clienteValido() throws ExcepcionCRM{
		boolean result = true;
		if(txtNombreFantasia.getText().isEmpty()){			
			result=false;	
			throw new ExcepcionCRM("Falta el nombre de fantasia");
		}
		else if(txtContactoInicial.getText().isEmpty()){
			result=false;
			throw new ExcepcionCRM("Falta el nombre de contacto inicial");
		}
		else if(txtRazonSocial.getText().isEmpty()){
			result=false;
			throw new ExcepcionCRM("Falta la razon social");
		}
		else if(txtCalle.getText().isEmpty()){
			result=false;
			throw new ExcepcionCRM("Falta la calle del domicilio");
		}
		else if(txtNumero.getText().isEmpty()){
			result=false;
			throw new ExcepcionCRM("Falta nel numero de la calle del domicilio");
		}
		else if(cmbCondicionIVA.searchForeign()==null){
			result=false;
			throw new ExcepcionCRM("Falta la condicion de IVA");
		}
		else if(txtCuit.getText().isEmpty() && !cmbCondicionIVA.searchForeign().equals("5")){
			result = false;
			throw new ExcepcionCRM("Si la condicion de IVa no es Consumidor Final debe indicar su CUIT");
		}
		return result;
	}

	private class PaisesActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(cmbPaises.getSelectedIndex()>0){
				cmbProvincias.setEnabled(true);
				cmbProvincias.loadItems(cmbPaises.searchForeign());
			}
			else{
				cmbProvincias.setEnabled(false);
				cmbProvincias.loadItems();
				cmbPartidos.setEnabled(false);				
				cmbPartidos.loadItems();
				cmbLocalidades.setEnabled(false);				
				cmbLocalidades.loadItems();
				cmbCP.setEnabled(false);				
				cmbCP.loadItems();
			}
		}
		
	}
	
	private class ProvinciasActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(cmbProvincias.getSelectedIndex()>0){
				cmbPartidos.setEnabled(true);
				cmbPartidos.loadItems(cmbProvincias.searchForeign());
			}
			else{
				cmbPartidos.setEnabled(false);				
				cmbPartidos.loadItems();
				cmbLocalidades.setEnabled(false);				
				cmbLocalidades.loadItems();
				cmbCP.setEnabled(false);				
				cmbCP.loadItems();
			}
		}
		
	}
	
	private class PartidosActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(cmbPartidos.getSelectedIndex()>0){
				cmbLocalidades.setEnabled(true);
				cmbLocalidades.loadItems(cmbPartidos.searchForeign());
			}
			else{
				cmbLocalidades.setEnabled(false);				
				cmbLocalidades.loadItems();
				cmbCP.setEnabled(false);				
				cmbCP.loadItems();
			}
		}
		
	}
	
	private class LocalidadesActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(cmbLocalidades.getSelectedIndex()>0){
				cmbCP.setEnabled(true);
				cmbCP.loadItems(cmbLocalidades.searchForeign());
			}
			else{
				cmbCP.setEnabled(false);				
				cmbCP.loadItems();
			}
		}
		
	}
	
	private class CondicionIVAActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(cmbCondicionIVA.searchForeign().equals("5")){
				txtCuit.setEnabled(false);
			}
			else
				txtCuit.setEnabled(true);
		}
		
	}

}
