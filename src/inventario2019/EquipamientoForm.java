package inventario2019;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;

import crm.client.managers.EquipamientosManager;
import crm.client.managers.EquiposSubFamiliasManager;
import crm.client.managers.MarcasEquiposManager;
import crm.libraries.abm.entities.Equipamientos;
import crm.libraries.abm.entities.EquiposSubFamilias;
import crm.libraries.abm.entities.MarcaEquipo;
import gui.inventario.ABMInventario;
import gui.inventario.componentes.DepositosComboBox;
import gui.inventario.componentes.FamiliasComboBox;
import gui.inventario.componentes.JPanelGradient;
import gui.inventario.componentes.MarcasComboBox;
import gui.inventario.componentes.SubfamiliasComboBox;
import gui.inventario.componentes.tablas.ListadoEquiposItem;
import gui.inventario.componentes.tablas.ListadoEquiposTableModel;
import inventario2019.paneles.BaulesPanel;

import javax.swing.JMenuBar;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;

public class EquipamientoForm extends JDialog {

	private final JPanel panelCentral = new JPanel();
	private JButton btnNuevo;
	private JButton btnListar;
	private JLabel lblNuevo;
	private JLabel lblListar;
	
	private static final Color COLOR_ROJO=new Color(0xe40520);
	private static final Color COLOR_BORDO=new Color(0xc41928);
	private static final Color COLOR_GRIS=new Color(0x575756);
	private static final Color COLOR_GRIS_CLARO=new Color(0xcccccc);
	private JPanel panelNuevoEq;
	private JLabel lblNewLabel_1;
	private FamiliasComboBox cmbFamilia;
	private JLabel lblSubfamilia;
	private JLabel lblMarca;
	private JLabel lblModelo;
	private JLabel lblNroDeSerie;
	private JLabel lblDeposito;
	private SubfamiliasComboBox cmbSubfamilia;
	private MarcasComboBox cmbMarca;
	private JTextField txtModelo;
	private DepositosComboBox cmbDeposito;
	private JTextField txtNroSerie;
	private JPanel panelListado;
	private JPanel panelDinamico;
	private JPanel panelObservaciones;
	private JLabel lblNewLabel_2;
	private JScrollPane scrollPane;
	private JTextArea txtAreaObservaciones;
	private JLabel lblNewLabel_3;
	private JPanel panelMedidas;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JTextField txtAlto;
	private JLabel lblNewLabel_6;
	private JTextField txtAncho;
	private JLabel lblNewLabel_7;
	private JTextField txtLargo;
	private JLabel lblNewLabel_8;
	private JTextField txtPeso;
	private JPanel panelCriterios;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JLabel lblCriteriosDeBusqueda;
	private JTextField txtCodigoLista;
	private JTextField txtNroSerieLista;
	private FamiliasComboBox cmbFamiliaLista;
	private SubfamiliasComboBox cmbSubfamiliaLista;
	private JRadioButton rdCodigo;
	private JRadioButton rdNroSerie;
	private JRadioButton rdFamilia;
	private JRadioButton rdSubfamilia;
	private JButton btnBuscar;
	private JPopupMenu popupMenu;
	private JMenuItem mntmModificar;
	private JMenuItem mntmEliminar;
	private JMenuItem mntmReimprimir;
	private JMenuItem mntmContenido;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			try {
				UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EquipamientoForm dialog = new EquipamientoForm(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public EquipamientoForm(JDialog owner, boolean modal) {
		super(owner,modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(EquipamientoForm.class.getResource("/gui/inventario/imagenes/logo50px.png")));
		
		setTitle("CRM Deposito");

		setBounds(100, 100, 1280, 720);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanelGradient panelSuperior = new JPanelGradient();
			//JPanel panelSuperior = new JPanel();
			panelSuperior.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			panelSuperior.setColor1(COLOR_BORDO);
			panelSuperior.setColor2(COLOR_ROJO);
			FlowLayout fl_panelSuperior = (FlowLayout) panelSuperior.getLayout();
			fl_panelSuperior.setVgap(10);
			fl_panelSuperior.setHgap(35);
			fl_panelSuperior.setAlignment(FlowLayout.LEFT);
			getContentPane().add(panelSuperior, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("Equipamiento");
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
				lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 35));
				panelSuperior.add(lblNewLabel);
			}
		}
		panelCentral.setBackground(Color.WHITE);
		panelCentral.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{0, 0};
		gbl_panelCentral.rowHeights = new int[]{56, 549, 0};
		gbl_panelCentral.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		{
			JPanel panelBotonera = new JPanel();
			panelBotonera.setBackground(Color.WHITE);
			GridBagConstraints gbc_panelBotonera = new GridBagConstraints();
			gbc_panelBotonera.insets = new Insets(0, 0, 5, 0);
			gbc_panelBotonera.fill = GridBagConstraints.BOTH;
			gbc_panelBotonera.gridx = 0;
			gbc_panelBotonera.gridy = 0;
			panelCentral.add(panelBotonera, gbc_panelBotonera);
			GridBagLayout gbl_panelBotonera = new GridBagLayout();
			gbl_panelBotonera.columnWidths = new int[]{0, 0, 0};
			gbl_panelBotonera.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panelBotonera.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_panelBotonera.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelBotonera.setLayout(gbl_panelBotonera);
			
				btnNuevo = new JButton("Nuevo Equipo");
				btnNuevo.setFocusable(false);
				btnNuevo.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent arg0) {
						if(arg0.getKeyCode()==KeyEvent.VK_SPACE){
							btnNuevo.setForeground(Color.BLACK);
							lblNuevo.setBackground(COLOR_ROJO);
							btnListar.setForeground(Color.WHITE);
							lblListar.setBackground(COLOR_GRIS_CLARO);
						}
					}
				});
				btnNuevo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						((CardLayout)panelDinamico.getLayout()).show(panelDinamico, "panelNuevoEquipo");
					}
				});
				btnNuevo.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						btnNuevo.setForeground(Color.BLACK);
						lblNuevo.setBackground(COLOR_ROJO);
						btnListar.setForeground(Color.WHITE);
						lblListar.setBackground(COLOR_GRIS_CLARO);
					}
				});
				btnNuevo.setBorderPainted(false);
				btnNuevo.setContentAreaFilled(false);
				btnNuevo.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 25));
				GridBagConstraints gbc_btnNuevo = new GridBagConstraints();
				gbc_btnNuevo.fill = GridBagConstraints.BOTH;
				gbc_btnNuevo.insets = new Insets(0, 0, 5, 5);
				gbc_btnNuevo.gridx = 0;
				gbc_btnNuevo.gridy = 0;
				panelBotonera.add(btnNuevo, gbc_btnNuevo);
			
				btnListar = new JButton("Listado");
				btnListar.setFocusable(false);
				btnListar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						((CardLayout)panelDinamico.getLayout()).show(panelDinamico, "panelListado");
					}
				});
				btnListar.addKeyListener(new KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if(e.getKeyCode()==KeyEvent.VK_SPACE){
							btnListar.setForeground(Color.BLACK);
							lblListar.setBackground(COLOR_ROJO);
							btnNuevo.setForeground(Color.WHITE);
							lblNuevo.setBackground(COLOR_GRIS_CLARO);
						}
					}
				});
				btnListar.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						btnListar.setForeground(Color.BLACK);
						lblListar.setBackground(COLOR_ROJO);
						btnNuevo.setForeground(Color.WHITE);
						lblNuevo.setBackground(COLOR_GRIS_CLARO);
					}
				});
				btnListar.setForeground(Color.LIGHT_GRAY);
				btnListar.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 25));
				btnListar.setBorderPainted(false);
				btnListar.setContentAreaFilled(false);
				GridBagConstraints gbc_btnListar = new GridBagConstraints();
				gbc_btnListar.insets = new Insets(0, 0, 5, 0);
				gbc_btnListar.fill = GridBagConstraints.BOTH;
				gbc_btnListar.gridx = 1;
				gbc_btnListar.gridy = 0;
				panelBotonera.add(btnListar, gbc_btnListar);
			
				lblNuevo = new JLabel(" ");
				lblNuevo.setOpaque(true);
				lblNuevo.setBackground(COLOR_ROJO);
				lblNuevo.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
				GridBagConstraints gbc_lblNuevo = new GridBagConstraints();
				gbc_lblNuevo.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblNuevo.insets = new Insets(0, 0, 5, 5);
				gbc_lblNuevo.gridx = 0;
				gbc_lblNuevo.gridy = 1;
				panelBotonera.add(lblNuevo, gbc_lblNuevo);
			
				lblListar = new JLabel(" ");
				lblListar.setBackground(COLOR_GRIS_CLARO);
				lblListar.setOpaque(true);
				lblListar.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
				GridBagConstraints gbc_lblListar = new GridBagConstraints();
				gbc_lblListar.insets = new Insets(0, 0, 5, 0);
				gbc_lblListar.fill = GridBagConstraints.HORIZONTAL;
				gbc_lblListar.gridx = 1;
				gbc_lblListar.gridy = 1;
				panelBotonera.add(lblListar, gbc_lblListar);
			
		}
		{
			panelDinamico = new JPanel();
			panelDinamico.setBackground(Color.WHITE);
			GridBagConstraints gbc_panelDinamico = new GridBagConstraints();
			gbc_panelDinamico.fill = GridBagConstraints.BOTH;
			gbc_panelDinamico.gridx = 0;
			gbc_panelDinamico.gridy = 1;
			panelCentral.add(panelDinamico, gbc_panelDinamico);
			panelDinamico.setLayout(new CardLayout(20, 20));
			
			panelNuevoEq = new JPanel();
			panelNuevoEq.setBackground(Color.WHITE);
			panelDinamico.add(panelNuevoEq, "panelNuevoEquipo");
			GridBagLayout gbl_panelNuevoEq = new GridBagLayout();
			gbl_panelNuevoEq.columnWidths = new int[] {0, 0, 0, 0, 0};
			gbl_panelNuevoEq.rowHeights = new int[]{0, 18, 0, 18, 0, 20, 0, 18, 0, 20, 0, 20, 0, 0};
			gbl_panelNuevoEq.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panelNuevoEq.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			panelNuevoEq.setLayout(gbl_panelNuevoEq);
			
			lblNewLabel_1 = new JLabel("Familia");
			lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
			gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
			gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_1.gridx = 0;
			gbc_lblNewLabel_1.gridy = 0;
			panelNuevoEq.add(lblNewLabel_1, gbc_lblNewLabel_1);
			
			cmbFamilia = new FamiliasComboBox();	
			cmbFamilia.loadItems();
			cmbFamilia.addActionListener(new FamiliaActionListener());
			cmbFamilia.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_cmbFamilia = new GridBagConstraints();
			gbc_cmbFamilia.insets = new Insets(5, 0, 5, 5);
			gbc_cmbFamilia.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbFamilia.gridx = 1;
			gbc_cmbFamilia.gridy = 0;
			panelNuevoEq.add(cmbFamilia, gbc_cmbFamilia);
			
			lblNewLabel_3 = new JLabel("          ");
			GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
			gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_3.gridx = 2;
			gbc_lblNewLabel_3.gridy = 0;
			panelNuevoEq.add(lblNewLabel_3, gbc_lblNewLabel_3);
			
			panelObservaciones = new JPanel();
			panelObservaciones.setBackground(new Color(255, 255, 255));
			GridBagConstraints gbc_panelObservaciones = new GridBagConstraints();
			gbc_panelObservaciones.gridheight = 11;
			gbc_panelObservaciones.insets = new Insets(5, 0, 5, 0);
			gbc_panelObservaciones.fill = GridBagConstraints.BOTH;
			gbc_panelObservaciones.gridx = 3;
			gbc_panelObservaciones.gridy = 0;
			panelNuevoEq.add(panelObservaciones, gbc_panelObservaciones);
			GridBagLayout gbl_panelObservaciones = new GridBagLayout();
			gbl_panelObservaciones.columnWidths = new int[]{0, 0};
			gbl_panelObservaciones.rowHeights = new int[]{0, 0, 0};
			gbl_panelObservaciones.columnWeights = new double[]{1.0, Double.MIN_VALUE};
			gbl_panelObservaciones.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
			panelObservaciones.setLayout(gbl_panelObservaciones);
			
			lblNewLabel_2 = new JLabel("Observaciones");
			lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
			gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
			gbc_lblNewLabel_2.gridx = 0;
			gbc_lblNewLabel_2.gridy = 0;
			panelObservaciones.add(lblNewLabel_2, gbc_lblNewLabel_2);
			
			scrollPane = new JScrollPane();
			GridBagConstraints gbc_scrollPane = new GridBagConstraints();
			gbc_scrollPane.fill = GridBagConstraints.BOTH;
			gbc_scrollPane.gridx = 0;
			gbc_scrollPane.gridy = 1;
			panelObservaciones.add(scrollPane, gbc_scrollPane);
			
			txtAreaObservaciones = new JTextArea();
			txtAreaObservaciones.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_TAB) {
	                    if (e.getModifiers() > 0) {
	                    	txtAreaObservaciones.transferFocusBackward();
	                    } else {
	                    	txtAreaObservaciones.transferFocus();
	                    }
	                    e.consume();
	                }
				}

			});
			txtAreaObservaciones.setMargin(new Insets(2, 10, 2, 10));
			txtAreaObservaciones.setLineWrap(true);
			txtAreaObservaciones.setColumns(10);
			txtAreaObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 18));
			txtAreaObservaciones.setRows(10);
			scrollPane.setViewportView(txtAreaObservaciones);
			
			lblSubfamilia = new JLabel("Subfamilia");
			lblSubfamilia.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_lblSubfamilia = new GridBagConstraints();
			gbc_lblSubfamilia.fill = GridBagConstraints.VERTICAL;
			gbc_lblSubfamilia.insets = new Insets(0, 0, 5, 5);
			gbc_lblSubfamilia.gridx = 0;
			gbc_lblSubfamilia.gridy = 2;
			panelNuevoEq.add(lblSubfamilia, gbc_lblSubfamilia);
			
			cmbSubfamilia = new SubfamiliasComboBox();
			cmbSubfamilia.loadItemsPorFamilia(null);
			cmbSubfamilia.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_cmbSubfamilia = new GridBagConstraints();
			gbc_cmbSubfamilia.insets = new Insets(0, 0, 5, 5);
			gbc_cmbSubfamilia.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbSubfamilia.gridx = 1;
			gbc_cmbSubfamilia.gridy = 2;
			panelNuevoEq.add(cmbSubfamilia, gbc_cmbSubfamilia);
			
			lblMarca = new JLabel("Marca");
			lblMarca.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_lblMarca = new GridBagConstraints();
			gbc_lblMarca.fill = GridBagConstraints.VERTICAL;
			gbc_lblMarca.insets = new Insets(0, 0, 5, 5);
			gbc_lblMarca.gridx = 0;
			gbc_lblMarca.gridy = 4;
			panelNuevoEq.add(lblMarca, gbc_lblMarca);
			
			cmbMarca = new MarcasComboBox();
			cmbMarca.loadItems();
			cmbMarca.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_cmbMarca = new GridBagConstraints();
			gbc_cmbMarca.insets = new Insets(0, 0, 5, 5);
			gbc_cmbMarca.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbMarca.gridx = 1;
			gbc_cmbMarca.gridy = 4;
			panelNuevoEq.add(cmbMarca, gbc_cmbMarca);
			
			lblModelo = new JLabel("Modelo");
			lblModelo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_lblModelo = new GridBagConstraints();
			gbc_lblModelo.fill = GridBagConstraints.VERTICAL;
			gbc_lblModelo.insets = new Insets(0, 0, 5, 5);
			gbc_lblModelo.gridx = 0;
			gbc_lblModelo.gridy = 6;
			panelNuevoEq.add(lblModelo, gbc_lblModelo);
			
			txtModelo = new JTextField();
			txtModelo.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent arg0) {
					if(Character.isLetter(arg0.getKeyChar()))
						txtModelo.setText(txtModelo.getText().toUpperCase());
				}
			});
			txtModelo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_txtModelo = new GridBagConstraints();
			gbc_txtModelo.insets = new Insets(0, 0, 5, 5);
			gbc_txtModelo.fill = GridBagConstraints.BOTH;
			gbc_txtModelo.gridx = 1;
			gbc_txtModelo.gridy = 6;
			panelNuevoEq.add(txtModelo, gbc_txtModelo);
			txtModelo.setColumns(10);
			
			lblNroDeSerie = new JLabel("Nro de Serie");
			lblNroDeSerie.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_lblNroDeSerie = new GridBagConstraints();
			gbc_lblNroDeSerie.fill = GridBagConstraints.VERTICAL;
			gbc_lblNroDeSerie.insets = new Insets(0, 0, 5, 5);
			gbc_lblNroDeSerie.gridx = 0;
			gbc_lblNroDeSerie.gridy = 8;
			panelNuevoEq.add(lblNroDeSerie, gbc_lblNroDeSerie);
			
			txtNroSerie = new JTextField();
			txtNroSerie.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if(Character.isLetter(e.getKeyChar()))
						txtNroSerie.setText(txtNroSerie.getText().toUpperCase());
				}
			});
			txtNroSerie.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			txtNroSerie.setColumns(10);
			GridBagConstraints gbc_txtNroSerie = new GridBagConstraints();
			gbc_txtNroSerie.insets = new Insets(0, 0, 5, 5);
			gbc_txtNroSerie.fill = GridBagConstraints.BOTH;
			gbc_txtNroSerie.gridx = 1;
			gbc_txtNroSerie.gridy = 8;
			panelNuevoEq.add(txtNroSerie, gbc_txtNroSerie);
			
			lblDeposito = new JLabel("Deposito");
			lblDeposito.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_lblDeposito = new GridBagConstraints();
			gbc_lblDeposito.fill = GridBagConstraints.VERTICAL;
			gbc_lblDeposito.insets = new Insets(0, 0, 5, 5);
			gbc_lblDeposito.gridx = 0;
			gbc_lblDeposito.gridy = 10;
			panelNuevoEq.add(lblDeposito, gbc_lblDeposito);
			
			cmbDeposito = new DepositosComboBox();
			cmbDeposito.loadItems();
			cmbDeposito.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_cmbDeposito = new GridBagConstraints();
			gbc_cmbDeposito.insets = new Insets(0, 0, 5, 5);
			gbc_cmbDeposito.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbDeposito.gridx = 1;
			gbc_cmbDeposito.gridy = 10;
			panelNuevoEq.add(cmbDeposito, gbc_cmbDeposito);
			
			panelMedidas = new JPanel();
			panelMedidas.setBackground(new Color(255, 255, 255));
			GridBagConstraints gbc_panelMedidas = new GridBagConstraints();
			gbc_panelMedidas.gridwidth = 4;
			gbc_panelMedidas.fill = GridBagConstraints.BOTH;
			gbc_panelMedidas.gridx = 0;
			gbc_panelMedidas.gridy = 12;
			panelNuevoEq.add(panelMedidas, gbc_panelMedidas);
			GridBagLayout gbl_panelMedidas = new GridBagLayout();
			gbl_panelMedidas.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panelMedidas.rowHeights = new int[]{0, 0, 0, 0};
			gbl_panelMedidas.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panelMedidas.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelMedidas.setLayout(gbl_panelMedidas);
			
			lblNewLabel_4 = new JLabel("Dimensiones");
			lblNewLabel_4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
			GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
			gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_4.gridx = 0;
			gbc_lblNewLabel_4.gridy = 0;
			panelMedidas.add(lblNewLabel_4, gbc_lblNewLabel_4);
			
			lblNewLabel_5 = new JLabel("Alto (Cm)");
			lblNewLabel_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
			GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
			gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_5.anchor = GridBagConstraints.NORTHEAST;
			gbc_lblNewLabel_5.gridx = 1;
			gbc_lblNewLabel_5.gridy = 1;
			panelMedidas.add(lblNewLabel_5, gbc_lblNewLabel_5);
			
			txtAlto = new JTextField();
			GridBagConstraints gbc_txtAlto = new GridBagConstraints();
			gbc_txtAlto.insets = new Insets(0, 0, 5, 5);
			gbc_txtAlto.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtAlto.gridx = 2;
			gbc_txtAlto.gridy = 1;
			panelMedidas.add(txtAlto, gbc_txtAlto);
			txtAlto.setColumns(10);
			
			lblNewLabel_6 = new JLabel("Ancho (Cm)");
			lblNewLabel_6.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
			GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
			gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_6.gridx = 3;
			gbc_lblNewLabel_6.gridy = 1;
			panelMedidas.add(lblNewLabel_6, gbc_lblNewLabel_6);
			
			txtAncho = new JTextField();
			GridBagConstraints gbc_txtAncho = new GridBagConstraints();
			gbc_txtAncho.insets = new Insets(0, 0, 5, 5);
			gbc_txtAncho.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtAncho.gridx = 4;
			gbc_txtAncho.gridy = 1;
			panelMedidas.add(txtAncho, gbc_txtAncho);
			txtAncho.setColumns(10);
			
			lblNewLabel_7 = new JLabel("Largo (Cm)");
			lblNewLabel_7.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
			GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
			gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_7.gridx = 5;
			gbc_lblNewLabel_7.gridy = 1;
			panelMedidas.add(lblNewLabel_7, gbc_lblNewLabel_7);
			
			txtLargo = new JTextField();
			GridBagConstraints gbc_txtLargo = new GridBagConstraints();
			gbc_txtLargo.insets = new Insets(0, 0, 5, 5);
			gbc_txtLargo.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtLargo.gridx = 6;
			gbc_txtLargo.gridy = 1;
			panelMedidas.add(txtLargo, gbc_txtLargo);
			txtLargo.setColumns(10);
			
			lblNewLabel_8 = new JLabel("Peso (Kg)");
			lblNewLabel_8.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
			GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
			gbc_lblNewLabel_8.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_8.gridx = 7;
			gbc_lblNewLabel_8.gridy = 1;
			panelMedidas.add(lblNewLabel_8, gbc_lblNewLabel_8);
			
			txtPeso = new JTextField();
			GridBagConstraints gbc_txtPeso = new GridBagConstraints();
			gbc_txtPeso.insets = new Insets(0, 0, 5, 5);
			gbc_txtPeso.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtPeso.gridx = 8;
			gbc_txtPeso.gridy = 1;
			panelMedidas.add(txtPeso, gbc_txtPeso);
			txtPeso.setColumns(10);
			{
				JButton okButton = new JButton("Guardar");
				GridBagConstraints gbc_okButton = new GridBagConstraints();
				gbc_okButton.insets = new Insets(0, 0, 5, 0);
				gbc_okButton.anchor = GridBagConstraints.EAST;
				gbc_okButton.gridx = 9;
				gbc_okButton.gridy = 1;
				panelMedidas.add(okButton, gbc_okButton);
				okButton.addActionListener(new GuardarActionListener());
				okButton.setIcon(new ImageIcon(EquipamientoForm.class.getResource("/gui/inventario/imagenes/disk.png")));
				okButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
				okButton.setActionCommand("OK");
				//getRootPane().setDefaultButton(okButton);
			}
			
			panelListado = new JPanel();
			panelDinamico.add(panelListado, "panelListado");
			GridBagLayout gbl_panelListado = new GridBagLayout();
			gbl_panelListado.columnWidths = new int[]{300, 1014, 0};
			gbl_panelListado.rowHeights = new int[]{15, 0, 0, 0};
			gbl_panelListado.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
			gbl_panelListado.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			panelListado.setLayout(gbl_panelListado);
			
			panelCriterios = new JPanel();
			GridBagConstraints gbc_panelCriterios = new GridBagConstraints();
			gbc_panelCriterios.insets = new Insets(0, 0, 5, 5);
			gbc_panelCriterios.fill = GridBagConstraints.BOTH;
			gbc_panelCriterios.gridx = 0;
			gbc_panelCriterios.gridy = 1;
			panelListado.add(panelCriterios, gbc_panelCriterios);
			GridBagLayout gbl_panelCriterios = new GridBagLayout();
			gbl_panelCriterios.columnWidths = new int[]{0, 75, 0, 0};
			gbl_panelCriterios.rowHeights = new int[]{0, 0, 0, 10, 0, 0, 10, 0, 0, 10, 0, 0, 20, 0, 0};
			gbl_panelCriterios.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panelCriterios.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelCriterios.setLayout(gbl_panelCriterios);
			
			lblCriteriosDeBusqueda = new JLabel("Criterios de busqueda");
			lblCriteriosDeBusqueda.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
			GridBagConstraints gbc_lblCriteriosDeBusqueda = new GridBagConstraints();
			gbc_lblCriteriosDeBusqueda.gridwidth = 2;
			gbc_lblCriteriosDeBusqueda.anchor = GridBagConstraints.WEST;
			gbc_lblCriteriosDeBusqueda.insets = new Insets(0, 0, 5, 0);
			gbc_lblCriteriosDeBusqueda.gridx = 1;
			gbc_lblCriteriosDeBusqueda.gridy = 0;
			panelCriterios.add(lblCriteriosDeBusqueda, gbc_lblCriteriosDeBusqueda);
			
			ButtonGroup bg = new ButtonGroup();
			
			txtCodigoLista = new JTextField();
			txtCodigoLista.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						buscarEquipos();
	                }
				}
			});
			GridBagConstraints gbc_txtCodigoLista = new GridBagConstraints();
			gbc_txtCodigoLista.insets = new Insets(0, 0, 5, 5);
			gbc_txtCodigoLista.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCodigoLista.gridx = 1;
			gbc_txtCodigoLista.gridy = 2;
			panelCriterios.add(txtCodigoLista, gbc_txtCodigoLista);
			txtCodigoLista.setColumns(10);
			
			txtNroSerieLista = new JTextField();
			txtNroSerieLista.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						buscarEquipos();
	                }
				}
			});
			GridBagConstraints gbc_txtNroSerieLista = new GridBagConstraints();
			gbc_txtNroSerieLista.insets = new Insets(0, 0, 5, 5);
			gbc_txtNroSerieLista.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtNroSerieLista.gridx = 1;
			gbc_txtNroSerieLista.gridy = 5;
			panelCriterios.add(txtNroSerieLista, gbc_txtNroSerieLista);
			txtNroSerieLista.setColumns(10);
			
			cmbFamiliaLista = new FamiliasComboBox();
			cmbFamiliaLista.loadItems();
			GridBagConstraints gbc_cmbFamiliaLista = new GridBagConstraints();
			gbc_cmbFamiliaLista.insets = new Insets(0, 0, 5, 5);
			gbc_cmbFamiliaLista.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbFamiliaLista.gridx = 1;
			gbc_cmbFamiliaLista.gridy = 8;
			panelCriterios.add(cmbFamiliaLista, gbc_cmbFamiliaLista);
			
			cmbSubfamiliaLista = new SubfamiliasComboBox();
			cmbSubfamiliaLista.loadItems();
			GridBagConstraints gbc_cmbSubfamiliaLista = new GridBagConstraints();
			gbc_cmbSubfamiliaLista.insets = new Insets(0, 0, 5, 5);
			gbc_cmbSubfamiliaLista.fill = GridBagConstraints.HORIZONTAL;
			gbc_cmbSubfamiliaLista.gridx = 1;
			gbc_cmbSubfamiliaLista.gridy = 11;
			panelCriterios.add(cmbSubfamiliaLista, gbc_cmbSubfamiliaLista);
			
			rdCodigo = new JRadioButton("Codigo");
			rdCodigo.addItemListener(new CodigoChangeItemListener());
			rdCodigo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			rdCodigo.setSelected(true);
			GridBagConstraints gbc_rdCodigo = new GridBagConstraints();
			gbc_rdCodigo.anchor = GridBagConstraints.WEST;
			gbc_rdCodigo.insets = new Insets(0, 0, 5, 5);
			gbc_rdCodigo.gridx = 1;
			gbc_rdCodigo.gridy = 1;
			bg.add(rdCodigo);
			panelCriterios.add(rdCodigo, gbc_rdCodigo);

			rdNroSerie = new JRadioButton("Nro de serie");
			rdNroSerie.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(rdNroSerie.isSelected()){
						txtCodigoLista.setEnabled(false);
						txtNroSerieLista.setEnabled(true);
						cmbFamiliaLista.setEnabled(false);
						cmbSubfamiliaLista.setEnabled(false);
					}
				}
			});
			rdNroSerie.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			GridBagConstraints gbc_rdNroSerie = new GridBagConstraints();
			gbc_rdNroSerie.anchor = GridBagConstraints.WEST;
			gbc_rdNroSerie.insets = new Insets(0, 0, 5, 5);
			gbc_rdNroSerie.gridx = 1;
			gbc_rdNroSerie.gridy = 4;
			bg.add(rdNroSerie);
			panelCriterios.add(rdNroSerie, gbc_rdNroSerie);
			
			rdFamilia = new JRadioButton("Familia");
			rdFamilia.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(rdFamilia.isSelected()){
						txtCodigoLista.setEnabled(false);
						txtNroSerieLista.setEnabled(false);
						cmbFamiliaLista.setEnabled(true);
						cmbSubfamiliaLista.setEnabled(false);
					}
				}
			});
			rdFamilia.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			GridBagConstraints gbc_rdFamilia = new GridBagConstraints();
			gbc_rdFamilia.anchor = GridBagConstraints.WEST;
			gbc_rdFamilia.insets = new Insets(0, 0, 5, 5);
			gbc_rdFamilia.gridx = 1;
			gbc_rdFamilia.gridy = 7;
			bg.add(rdFamilia);
			panelCriterios.add(rdFamilia, gbc_rdFamilia);

			rdSubfamilia = new JRadioButton("Subfamilia");
			rdSubfamilia.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(rdSubfamilia.isSelected()){
						txtCodigoLista.setEnabled(false);
						txtNroSerieLista.setEnabled(false);
						cmbFamiliaLista.setEnabled(false);
						cmbSubfamiliaLista.setEnabled(true);
					}
				}
			});
			rdSubfamilia.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			GridBagConstraints gbc_rdSubfamilia = new GridBagConstraints();
			gbc_rdSubfamilia.anchor = GridBagConstraints.WEST;
			gbc_rdSubfamilia.insets = new Insets(0, 0, 5, 5);
			gbc_rdSubfamilia.gridx = 1;
			gbc_rdSubfamilia.gridy = 10;
			bg.add(rdSubfamilia);
			panelCriterios.add(rdSubfamilia, gbc_rdSubfamilia);			
			
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new BuscarEquiposActionListener());
			btnBuscar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
			btnBuscar.setIcon(new ImageIcon(EquipamientoForm.class.getResource("/gui/imagenes/magnifier.png")));
			GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
			gbc_btnBuscar.insets = new Insets(0, 0, 0, 5);
			gbc_btnBuscar.gridx = 1;
			gbc_btnBuscar.gridy = 13;
			panelCriterios.add(btnBuscar, gbc_btnBuscar);
			
			scrollPane_1 = new JScrollPane();
			
			popupMenu = new JPopupMenu();
			popupMenu.addPopupMenuListener(new PopupMenuListener() {
				public void popupMenuCanceled(PopupMenuEvent arg0) {
				}
				public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
				}
				public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
					mntmContenido.setEnabled(false);
					ListadoEquiposTableModel model = (ListadoEquiposTableModel)table.getModel();
					ListadoEquiposItem item = model.getRow(table.getSelectedRow());
					if(item.getSubfamilia()==78 || item.getSubfamilia()==79){
						mntmContenido.setEnabled(true);
					}
				}
			});
			addPopup(scrollPane_1, popupMenu);
			GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
			gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
			gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
			gbc_scrollPane_1.gridx = 1;
			gbc_scrollPane_1.gridy = 1;
			panelListado.add(scrollPane_1, gbc_scrollPane_1);
			
			table = new JTable();
			table.setComponentPopupMenu(popupMenu);
			
			mntmModificar = new JMenuItem("Modificar");
			mntmModificar.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent event) {
					ListadoEquiposTableModel model = (ListadoEquiposTableModel)table.getModel();
					ListadoEquiposItem e = model.getRow(table.getSelectedRow());
			        EquipamientoModifForm dialog = new EquipamientoModifForm(EquipamientoForm.this,true);
			        dialog.setLocationRelativeTo(EquipamientoForm.this);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.completarPantalla(e.toEquipamientoEntity());
					dialog.setVisible(true);
				}
			});
			mntmModificar.setIcon(new ImageIcon(EquipamientoForm.class.getResource("/gui/inventario/imagenes/pencil.png")));
			popupMenu.add(mntmModificar);
			
			mntmEliminar = new JMenuItem("Eliminar");
			mntmEliminar.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					ListadoEquiposTableModel model = (ListadoEquiposTableModel) table.getModel();
					ListadoEquiposItem itemSeleccionado = model.getRow(table.getSelectedRow());
			        if(itemSeleccionado!=null){
			            try {
			            	EquipamientosManager.instance().remove(String.valueOf(itemSeleccionado.getCodigo()));
			                JOptionPane.showMessageDialog(EquipamientoForm.this, "Equipo borrado con exito!", "Borrado", JOptionPane.INFORMATION_MESSAGE);
			                
			                
			            } catch (RemoteException ex) {
			                Logger.getLogger(ABMInventario.class.getName()).log(Level.SEVERE, null, ex);
			                JOptionPane.showMessageDialog(EquipamientoForm.this, "Error al borrar el equipo", "Error", JOptionPane.ERROR_MESSAGE);
			            }
			        }
			        else{
			            JOptionPane.showMessageDialog(EquipamientoForm.this, "Debe seleccionar un item de la grilla", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			});
			mntmEliminar.setIcon(new ImageIcon(EquipamientoForm.class.getResource("/gui/inventario/imagenes/cross.png")));
			popupMenu.add(mntmEliminar);
			
			mntmReimprimir = new JMenuItem("Reimprimir");
			mntmReimprimir.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					ListadoEquiposTableModel model = (ListadoEquiposTableModel) table.getModel();
					ListadoEquiposItem itemSeleccionado = model.getRow(table.getSelectedRow());
			        if(itemSeleccionado!=null){            
			            imprimirEtiqueta(itemSeleccionado.toEquipamientoEntity());
			        }
				}
			});
			mntmReimprimir.setIcon(new ImageIcon(EquipamientoForm.class.getResource("/gui/inventario/imagenes/printer.png")));
			popupMenu.add(mntmReimprimir);
			
			mntmContenido = new JMenuItem("Contenido");
			mntmContenido.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseReleased (MouseEvent e) {
					ListadoEquiposTableModel model = (ListadoEquiposTableModel) table.getModel();
					ListadoEquiposItem itemSeleccionado = model.getRow(table.getSelectedRow());
					ContenidoRack dialog = new ContenidoRack(EquipamientoForm.this,true,itemSeleccionado.getCodigo());
					dialog.setLocationRelativeTo(EquipamientoForm.this);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);					
					dialog.setVisible(true);
				}
			});
			mntmContenido.setIcon(new ImageIcon(EquipamientoForm.class.getResource("/gui/inventario/imagenes/bricks.png")));
			mntmContenido.setEnabled(false);
			popupMenu.add(mntmContenido);
			table.setModel(new ListadoEquiposTableModel());
			table.getColumnModel().getColumn(0).setResizable(false);
			scrollPane_1.setViewportView(table);
		}
		{
			JPanelGradient panelInferior = new JPanelGradient();
			panelInferior.setFocusTraversalKeysEnabled(false);
			panelInferior.setColor1(COLOR_BORDO);
			panelInferior.setColor2(COLOR_ROJO);
			panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(panelInferior, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int res = JOptionPane.showConfirmDialog(EquipamientoForm.this, "Desea salir de la carga de equipamientos?\nSe perderan los cambios no guardados.", "Cancelar", JOptionPane.YES_NO_OPTION );
		                if(res == JOptionPane.YES_OPTION){
		                	dispose();
		                }						
					}
				});
				cancelButton.setIcon(new ImageIcon(EquipamientoForm.class.getResource("/gui/inventario/imagenes/cross.png")));
				cancelButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
				cancelButton.setActionCommand("Cancel");
				panelInferior.add(cancelButton);
			}
		}
	}
	
	private boolean validarNuevo(){
        boolean result = true;
        if(txtModelo.getText().isEmpty()){
        	JOptionPane.showMessageDialog(EquipamientoForm.this, "Complete el modelo del equipo", "Error", JOptionPane.ERROR_MESSAGE);
            result=false;
        }

        if(cmbFamilia.getSelectedIndex()==0){
        	JOptionPane.showMessageDialog(EquipamientoForm.this, "Seleccione una familia de equipos", "Error", JOptionPane.ERROR_MESSAGE);
            result=false;
        }
        if(cmbSubfamilia.getSelectedIndex()==0){
        	JOptionPane.showMessageDialog(EquipamientoForm.this, "Seleccione una subfamilia de equipos", "Error", JOptionPane.ERROR_MESSAGE);
            result=false;
        }
        if(cmbMarca.getSelectedIndex()==0){
        	JOptionPane.showMessageDialog(EquipamientoForm.this, "Seleccione una marca para el equipo", "Error", JOptionPane.ERROR_MESSAGE);
            result=false;
        }
        return result;
    }
	
	private void buscarEquipos(){
		try{
			ListadoEquiposTableModel model = (ListadoEquiposTableModel) table.getModel();
            model.clear();
            ArrayList<ListadoEquiposItem> equiposItems = new ArrayList<ListadoEquiposItem>();
            if (rdCodigo.isSelected()){
            	Object[] result = EquipamientosManager.instance().buscarEquipamientoxCodigoBarras(1,txtCodigoLista.getText());
            	equiposItems = crearEquipamiento(result);
            }
            else if(rdSubfamilia.isSelected()){
            	Object[] result = EquipamientosManager.instance().buscarEquipamientoxCodigoBarras(4,cmbSubfamiliaLista.searchForeign());
            	equiposItems = crearEquipamiento(result);
            }
            else if(rdFamilia.isSelected()){
            	Object[] result = EquipamientosManager.instance().buscarEquipamientoxCodigoBarras(3,cmbFamiliaLista.searchForeign());
            	equiposItems = crearEquipamiento(result);
            }
            else if(rdNroSerie.isSelected()){
            	Object[] result = EquipamientosManager.instance().buscarEquipamientoxCodigoBarras(2,txtNroSerieLista.getText());
            	equiposItems = crearEquipamiento(result);
            }

            if (equiposItems.size() > 0) {
                for (ListadoEquiposItem eq : equiposItems) {
                    model.addRow(eq);
                }
                table.setModel(model);
                table.updateUI();
            }
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	private ArrayList<ListadoEquiposItem> crearEquipamiento(Object[] result) {
		// TODO Auto-generated method stub			
		try {
			ArrayList<ListadoEquiposItem> equiposItems = new ArrayList<ListadoEquiposItem>();
			
			for (int i = 0; i < result.length; i++){
				ListadoEquiposItem r = new ListadoEquiposItem();
				Object[] item = (Object[])result[i];
				r.setCodigo((int)item[0]);
				r.setMarca((int)item[1]);
				r.setSubfamilia((int)item[2]);
				r.setNroSerie((String)item[3]);
				r.setDeposito((int)item[4]);
				r.setActivo((String)item[5]);
				r.setObservaciones((String)item[6]);
				r.setEstado((int)item[7]);
				r.setCodigoBarras((int)item[8]);
				r.setModelo((String)item[9]);
				r.setAlto((int)item[10]);
				r.setAncho((int)item[11]);
				r.setLargo((int)item[12]);
				r.setPeso((int)item[13]);
				r.setNombreFamilia((String)item[14]);
				r.setNombreSubfamilia((String)item[15]);
				r.setNombreMarca((String)item[16]);
				equiposItems.add(r);
			}
			return equiposItems;				
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	private final class BuscarEquiposActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			buscarEquipos();	
		}

	}

	private final class CodigoChangeItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent arg0) {
			if(rdCodigo.isSelected()){
				txtCodigoLista.setEnabled(true);
				txtNroSerieLista.setEnabled(false);
				cmbFamiliaLista.setEnabled(false);
				cmbSubfamiliaLista.setEnabled(false);
			}
		}
	}

	private final class GuardarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(validarNuevo()){
	            try {
	            	Equipamientos eq = new Equipamientos();
	                eq.setMarca(cmbMarca.searchForeign());
	                eq.setModelo(txtModelo.getText());
	                if(!txtNroSerie.getText().isEmpty())
	                    eq.setNroSerie(txtNroSerie.getText());
	                if(!txtAreaObservaciones.getText().isEmpty())
	                    eq.setObservaciones(txtAreaObservaciones.getText());
	                eq.setDeposito(cmbDeposito.searchForeign());
	                eq.setSubfamilia((cmbSubfamilia.searchForeign()));
	                
	                eq.setActivo("S");
	                eq.setCodigoBarras("");
	                eq.setEstado("1");

	                if(!txtAlto.getText().isEmpty())
	                    eq.setAlto(Integer.parseInt(txtAlto.getText()));
	                if(!txtAncho.getText().isEmpty())
	                    eq.setAncho(Integer.parseInt(txtAncho.getText()));
	                if(!txtLargo.getText().isEmpty())
	                    eq.setLargo(Integer.parseInt(txtLargo.getText()));
	                if(!txtPeso.getText().isEmpty())
	                    eq.setPeso(Integer.parseInt(txtPeso.getText()));
	                Equipamientos e = EquipamientosManager.instance().update(eq);
	                JOptionPane.showMessageDialog(EquipamientoForm.this, "Equipo guardado con exito!", "Guardado", JOptionPane.INFORMATION_MESSAGE);
	                int res = JOptionPane.showConfirmDialog(EquipamientoForm.this, "Desea imprimir la etiqueta con el codigo de barras?", "Imprimir", JOptionPane.YES_NO_OPTION );
	                if(res == JOptionPane.YES_OPTION){
	                    imprimirEtiqueta(e);
	                }
	                limpiarNuevo();
	            } catch (RemoteException ex) {
	                Logger.getLogger(ABMInventario.class.getName()).log(Level.SEVERE, null, ex);
	                JOptionPane.showMessageDialog(EquipamientoForm.this, "Error al grabar el equipo", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        }
		}

		private void limpiarNuevo(){
			cmbSubfamilia.setSelectedIndex(0);
			cmbFamilia.setSelectedIndex(0);
			cmbDeposito.setSelectedIndex(0);
			cmbMarca.setSelectedIndex(0);
			txtAlto.setText("");
			txtAncho.setText("");
			txtAreaObservaciones.setText("");
			txtLargo.setText("");
			txtModelo.setText("");
			txtNroSerie.setText("");
			txtPeso.setText("");
		}
	}
	
	private String agregarCeros(String string, int largo) {
        String ceros = "";
        int cantidad = largo - string.length();
        if (cantidad >= 1) {
            for (int i = 0; i < cantidad; i++) {
                ceros += "0";
            }
            return (ceros + string);
        } else {
            return string;
        }
    }
	
	private void imprimirEtiqueta(Equipamientos eq){
        if (eq != null) {
            try {
            	PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
            	String zplCommand2=null;
            	MarcaEquipo marca = MarcasEquiposManager.instance().getById(eq.getMarca());
            	if(!eq.getSubfamilia().equals("25")){
            		zplCommand2 = "^XA\n"
            				+ "^FO20, 50^ADN, 11, 2^FD" + marca.getDescripcion() + " - " + eq.getModelo() + "^FS\n"
            				+ "^FO65, 80^BY3\n"
            				+ "^B3N,N,100,Y,N\n"
            				+ "^FD" + agregarCeros(eq.getCodigoBarras(), 6) + "^FS\n"
            				+ "^XZ";
            	}
            	else{
            		zplCommand2 = "^XA\n"
            				+ "^FO20, 50^ADN, 11, 2^FD" + eq.getObservaciones() + "^FS\n"
            				+ "^FO65, 80^BY3\n"
            				+ "^B3N,N,100,Y,N\n"
            				+ "^FD" + agregarCeros(eq.getCodigoBarras(), 6) + "^FS\n"
            				+ "^XZ";
            	}
            	if(zplCommand2 != null){
            		// convertimos el comando a bytes
            		byte[] by = zplCommand2.getBytes();
            		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            		Doc doc = new SimpleDoc(by, flavor, null);

            		// creamos el printjob
            		DocPrintJob job = printService.createPrintJob();

            		// imprimimos  
            		job.print(doc, null);
            	}
            } catch (PrintException ex) {
                Logger.getLogger(ABMInventario.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

	private class FamiliaActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(cmbFamilia.getSelectedIndex()>0){
				String cod = cmbFamilia.searchForeign();
				cmbSubfamilia.loadItemsPorFamilia(cod);
			}
		}
		
	}

	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {					
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
