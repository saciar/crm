package comerciales2019;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.border.MatteBorder;

import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceCeruleanLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeSilver2007LookAndFeel;

import gui.ControlAplicacion;
import gui.LoginForm;
import gui.inventario.componentes.JPanelGradient;
import inventario2019.UsuarioSettings;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BienvenidaComerciales extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private LoginForm owner;
	private UsuarioSettings userDialog;

	public LoginForm getOwner() {
		return owner;
	}

	public void setOwner(LoginForm owner) {
		this.owner = owner;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BienvenidaComerciales dialog = new BienvenidaComerciales(null,true);
			dialog.setLocationRelativeTo(null);			
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BienvenidaComerciales(JFrame owner, boolean modal) {
		super(owner,modal);
		setTitle("CRM Deposito");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/logo50px.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int resp=JOptionPane.showConfirmDialog(BienvenidaComerciales.this,"Seguro desea salir de la aplicacion?", "Salir",JOptionPane.YES_NO_OPTION);
				if (JOptionPane.OK_OPTION == resp){
					System.exit(0);
				}
			}
		});
		userDialog = new UsuarioSettings(BienvenidaComerciales.this,false);	
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int clicx = e.getX();
				int clicy = e.getY();
				int ventanax = userDialog.getX();
				int ventanay = userDialog.getY();
													
				if(clicx<ventanax || clicx>ventanax+userDialog.getWidth() || clicy > ventanay+userDialog.getHeight()){
					userDialog.dispose();
				}
			}
		});
		this.owner = (LoginForm)owner;
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();

		setSize(new Dimension(1280, 720));
		setPreferredSize(new Dimension(1280, 720));
		setBounds(new Rectangle(0, 0, 1280, 720));
		
		JPanelGradient panelSuperior = new JPanelGradient();
		panelSuperior.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		panelSuperior.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panelSuperior.setColor1(new Color(0xc41928));
		panelSuperior.setColor2(new Color(0xEC1F2F));
		panelSuperior.setForeground(new Color(255, 255, 255));
		getContentPane().add(panelSuperior, BorderLayout.NORTH);
		GridBagLayout gbl_panelSuperior = new GridBagLayout();
		gbl_panelSuperior.columnWidths = new int[]{20, 0, 0, 20, 0};
		gbl_panelSuperior.rowHeights = new int[]{5, 0, 5, 0};
		gbl_panelSuperior.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelSuperior.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelSuperior.setLayout(gbl_panelSuperior);
		
		JLabel lblBienvenido = new JLabel("BIENVENIDO");
		lblBienvenido.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount()==2){
					owner.setExtendedState(owner.MAXIMIZED_BOTH);
					setBounds(owner.getBounds().x,owner.getBounds().y,owner.getBounds().width,owner.getBounds().height);
				}
			}
		});
		lblBienvenido.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		lblBienvenido.setForeground(Color.WHITE);
		lblBienvenido.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 35));
		GridBagConstraints gbc_lblBienvenido = new GridBagConstraints();
		gbc_lblBienvenido.insets = new Insets(0, 0, 5, 5);
		gbc_lblBienvenido.gridx = 1;
		gbc_lblBienvenido.gridy = 1;
		panelSuperior.add(lblBienvenido, gbc_lblBienvenido);
		
		JButton btnUsuario = new JButton(ControlAplicacion.getInstance().getUsuario().getLoginName());
		//JButton btnUsuario = new JButton("nada");
		btnUsuario.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		btnUsuario.setBorderPainted(false);
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {									
					userDialog.setLocation(BienvenidaComerciales.this.getX()+btnUsuario.getX()-userDialog.getWidth(), BienvenidaComerciales.this.getY()+btnUsuario.getY()+btnUsuario.getHeight());
					userDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					userDialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnUsuario.setFocusable(false);
		btnUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuario.setContentAreaFilled(false);
		btnUsuario.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/imagenes/user.png")));
		btnUsuario.setForeground(Color.WHITE);
		btnUsuario.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
		GridBagConstraints gbc_btnUsuario = new GridBagConstraints();
		gbc_btnUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_btnUsuario.anchor = GridBagConstraints.EAST;
		gbc_btnUsuario.gridx = 2;
		gbc_btnUsuario.gridy = 1;
		panelSuperior.add(btnUsuario, gbc_btnUsuario);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		panelCentral.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{690, 455, 0};
		gbl_panelCentral.rowHeights = new int[]{491, 0};
		gbl_panelCentral.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/imagenes/logoThinking2.png")));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelCentral.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panelBotonera = new JPanel();
		panelBotonera.setBackground(Color.WHITE);
		panelBotonera.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		GridBagConstraints gbc_panelBotonera = new GridBagConstraints();
		gbc_panelBotonera.fill = GridBagConstraints.BOTH;
		gbc_panelBotonera.gridx = 1;
		gbc_panelBotonera.gridy = 0;
		panelCentral.add(panelBotonera, gbc_panelBotonera);
		GridBagLayout gbl_panelBotonera = new GridBagLayout();
		gbl_panelBotonera.columnWidths = new int[]{15, 416, 0, 0};
		gbl_panelBotonera.rowHeights = new int[]{23, 23, 23, 0, 0};
		gbl_panelBotonera.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelBotonera.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelBotonera.setLayout(gbl_panelBotonera);
		
		JLabel lblEquipos = new JLabel("");
		lblEquipos.setOpaque(true);
		lblEquipos.setBackground(new Color(72, 201, 176));
		GridBagConstraints gbc_lblEquipos = new GridBagConstraints();
		gbc_lblEquipos.fill = GridBagConstraints.BOTH;
		gbc_lblEquipos.insets = new Insets(10, 0, 10, 0);
		gbc_lblEquipos.gridx = 0;
		gbc_lblEquipos.gridy = 0;
		panelBotonera.add(lblEquipos, gbc_lblEquipos);
		
		JButton btnNuevoPpto = new JButton("Nuevo");
		btnNuevoPpto.setIconTextGap(20);
		btnNuevoPpto.setBorderPainted(false);
		btnNuevoPpto.setContentAreaFilled(false);
		btnNuevoPpto.addActionListener(new NuevoActionListener());
		btnNuevoPpto.setForeground(Color.BLACK);
		btnNuevoPpto.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnNuevoPpto.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/ic_mode_edit_grey600_48dp.png")));
		btnNuevoPpto.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(72, 201, 176)));
		btnNuevoPpto.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblEquipos.setBackground(new Color(209, 242, 235));
				btnNuevoPpto.setForeground(new Color(219, 219, 219));
				btnNuevoPpto.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(209, 242, 235)));
				btnNuevoPpto.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/ic_mode_edit_inactivo_48px.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblEquipos.setBackground(new Color(72, 201, 176));
				btnNuevoPpto.setForeground(new Color(0, 0, 0));
				btnNuevoPpto.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(72, 201, 176)));
				btnNuevoPpto.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/ic_mode_edit_grey600_48dp.png")));
			}
		});
		btnNuevoPpto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNuevoPpto.setFocusable(false);
		btnNuevoPpto.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
		GridBagConstraints gbc_btnNuevoPpto = new GridBagConstraints();
		gbc_btnNuevoPpto.fill = GridBagConstraints.BOTH;
		gbc_btnNuevoPpto.insets = new Insets(10, 0, 10, 5);
		gbc_btnNuevoPpto.gridx = 1;
		gbc_btnNuevoPpto.gridy = 0;
		panelBotonera.add(btnNuevoPpto, gbc_btnNuevoPpto);
		
		JLabel lblSalidas = new JLabel("");
		lblSalidas.setOpaque(true);
		lblSalidas.setBackground(new Color(93, 173, 226));
		GridBagConstraints gbc_lblSalidas = new GridBagConstraints();
		gbc_lblSalidas.fill = GridBagConstraints.BOTH;
		gbc_lblSalidas.insets = new Insets(0, 0, 10, 0);
		gbc_lblSalidas.gridx = 0;
		gbc_lblSalidas.gridy = 1;
		panelBotonera.add(lblSalidas, gbc_lblSalidas);
		
		JButton btnBuscarPpto = new JButton("Buscar");
		btnBuscarPpto.addActionListener(new EgresoActionListener());
		btnBuscarPpto.setIconTextGap(20);
		btnBuscarPpto.setBorderPainted(false);
		btnBuscarPpto.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnBuscarPpto.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/ic_find_in_page_grey600_48dp.png")));
		btnBuscarPpto.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(93, 173, 226)));
		btnBuscarPpto.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblSalidas.setBackground(new Color(214, 234, 248));
				btnBuscarPpto.setForeground(new Color(219, 219, 219));
				btnBuscarPpto.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(214, 234, 248)));
				btnBuscarPpto.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/ic_find_in_page_inactivo.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblSalidas.setBackground(new Color(93, 173, 226));
				btnBuscarPpto.setForeground(new Color(0, 0, 0));
				btnBuscarPpto.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(93, 173, 226)));
				btnBuscarPpto.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/ic_find_in_page_grey600_48dp.png")));
			}
		});
		btnBuscarPpto.setFocusable(false);
		btnBuscarPpto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarPpto.setContentAreaFilled(false);
		btnBuscarPpto.setForeground(Color.BLACK);
		btnBuscarPpto.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
		GridBagConstraints gbc_btnBuscarPpto = new GridBagConstraints();
		gbc_btnBuscarPpto.fill = GridBagConstraints.BOTH;
		gbc_btnBuscarPpto.insets = new Insets(0, 0, 10, 5);
		gbc_btnBuscarPpto.gridx = 1;
		gbc_btnBuscarPpto.gridy = 1;
		panelBotonera.add(btnBuscarPpto, gbc_btnBuscarPpto);
		
		JLabel lblEntradas = new JLabel("");
		lblEntradas.setOpaque(true);
		lblEntradas.setBackground(new Color(236, 112, 99));
		GridBagConstraints gbc_lblEntradas = new GridBagConstraints();
		gbc_lblEntradas.fill = GridBagConstraints.BOTH;
		gbc_lblEntradas.insets = new Insets(0, 0, 10, 0);
		gbc_lblEntradas.gridx = 0;
		gbc_lblEntradas.gridy = 2;
		panelBotonera.add(lblEntradas, gbc_lblEntradas);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.setIconTextGap(20);
		btnReportes.setBorderPainted(false);
		btnReportes.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnReportes.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/ic_content_paste_black_24dp.png")));
		btnReportes.setForeground(Color.BLACK);
		btnReportes.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(236, 112, 99)));
		btnReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblEntradas.setBackground(new Color(250, 219, 216));
				btnReportes.setForeground(new Color(219, 219, 219));
				btnReportes.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(250, 219, 216)));
				btnReportes.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/ic_content_paste_inactivo_24dp.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblEntradas.setBackground(new Color(236, 112, 99));
				btnReportes.setForeground(new Color(0, 0, 0));
				btnReportes.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(236, 112, 99)));
				btnReportes.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/ic_content_paste_black_24dp.png")));
			}
		});
		btnReportes.setFocusable(false);
		btnReportes.setContentAreaFilled(false);
		btnReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReportes.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
		GridBagConstraints gbc_btnReportes = new GridBagConstraints();
		gbc_btnReportes.fill = GridBagConstraints.BOTH;
		gbc_btnReportes.insets = new Insets(0, 0, 10, 5);
		gbc_btnReportes.gridx = 1;
		gbc_btnReportes.gridy = 2;
		panelBotonera.add(btnReportes, gbc_btnReportes);
		
		JLabel lblReportes = new JLabel("");
		lblReportes.setBackground(new Color(244, 208, 63));
		lblReportes.setOpaque(true);
		GridBagConstraints gbc_lblReportes = new GridBagConstraints();
		gbc_lblReportes.fill = GridBagConstraints.BOTH;
		gbc_lblReportes.gridx = 0;
		gbc_lblReportes.gridy = 3;
		panelBotonera.add(lblReportes, gbc_lblReportes);
		
		JButton btnEstadisticas = new JButton("Estadisticas");
		btnEstadisticas.setIconTextGap(20);
		btnEstadisticas.setBorderPainted(false);
		btnEstadisticas.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/ic_insert_chart_grey600_48dp.png")));
		btnEstadisticas.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnEstadisticas.setForeground(Color.BLACK);
		btnEstadisticas.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(244, 208, 63)));
		btnEstadisticas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblReportes.setBackground(new Color(252, 243, 207));
				btnEstadisticas.setForeground(new Color(219, 219, 219));
				btnEstadisticas.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(252, 243, 207)));
				btnEstadisticas.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/ic_insert_chart_inactivo_48px.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblReportes.setBackground(new Color(244, 208, 63));
				btnEstadisticas.setForeground(new Color(0, 0, 0));
				btnEstadisticas.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(244, 208, 63)));
				btnEstadisticas.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/gui/inventario/imagenes/ic_insert_chart_grey600_48dp.png")));
			}
		});
		btnEstadisticas.setFocusable(false);
		btnEstadisticas.setContentAreaFilled(false);
		btnEstadisticas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEstadisticas.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
		GridBagConstraints gbc_btnEstadisticas = new GridBagConstraints();
		gbc_btnEstadisticas.insets = new Insets(0, 0, 0, 5);
		gbc_btnEstadisticas.fill = GridBagConstraints.BOTH;
		gbc_btnEstadisticas.gridx = 1;
		gbc_btnEstadisticas.gridy = 3;
		panelBotonera.add(btnEstadisticas, gbc_btnEstadisticas);
		
		JPanelGradient panelInferior = new JPanelGradient();
		panelInferior.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		panelInferior.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		panelInferior.setColor2(new Color(0xc41928));
		panelInferior.setColor1(new Color(0xEC1F2F));
		panelInferior.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panelInferior.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(35);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		getContentPane().add(panelInferior, BorderLayout.SOUTH);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new SalirActionListener());
		btnSalir.setIcon(new ImageIcon(BienvenidaComerciales.class.getResource("/crm/gui/imagenes/cross.png")));
		btnSalir.setFont(new Font("Verdana", Font.PLAIN, 14));
		panelInferior.add(btnSalir);
		
	}
	
	private final class EgresoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
/*			EgresoForm dialog = new EgresoForm(BienvenidaComerciales.this,true);
			dialog.setLocationRelativeTo(BienvenidaComerciales.this);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);*/
		}
	}

	private final class SalirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int resp=JOptionPane.showConfirmDialog(BienvenidaComerciales.this,"Seguro desea salir de la aplicacion?", "Salir",JOptionPane.YES_NO_OPTION);
			if (JOptionPane.OK_OPTION == resp){
				System.exit(0);
			}
		}
	}

	private final class NuevoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

			ContenedorPpto dialog = new ContenedorPpto(BienvenidaComerciales.this,true);
			dialog.setLocationRelativeTo(BienvenidaComerciales.this);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}

}
