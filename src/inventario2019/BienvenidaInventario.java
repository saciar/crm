package inventario2019;

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

public class BienvenidaInventario extends JDialog {

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
			BienvenidaInventario dialog = new BienvenidaInventario(null,true);
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
	public BienvenidaInventario(JFrame owner, boolean modal) {
		super(owner,modal);
		setTitle("CRM Deposito");
		setIconImage(Toolkit.getDefaultToolkit().getImage(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/logo50px.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int resp=JOptionPane.showConfirmDialog(BienvenidaInventario.this,"Seguro desea salir de la aplicacion?", "Salir",JOptionPane.YES_NO_OPTION);
				if (JOptionPane.OK_OPTION == resp){
					System.exit(0);
				}
			}
		});
		userDialog = new UsuarioSettings(BienvenidaInventario.this,false);	
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
		
		JLabel label = new JLabel("CONTROL DE EQUIPOS");
		label.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 35));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		panelSuperior.add(label, gbc_label);
		
		JButton btnUsuario = new JButton(ControlAplicacion.getInstance().getUsuario().getLoginName());
		//JButton btnUsuario = new JButton("nada");
		btnUsuario.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		btnUsuario.setBorderPainted(false);
		btnUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				try {									
					userDialog.setLocation(BienvenidaInventario.this.getX()+btnUsuario.getX()-userDialog.getWidth(), BienvenidaInventario.this.getY()+btnUsuario.getY()+btnUsuario.getHeight());
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
		btnUsuario.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/imagenes/user.png")));
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
		lblNewLabel.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/imagenes/logoThinking2.png")));
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
		
		JButton btnEquipos = new JButton("Equipos");
		btnEquipos.setIconTextGap(20);
		btnEquipos.setBorderPainted(false);
		btnEquipos.setContentAreaFilled(false);
		btnEquipos.addActionListener(new EquiposActionListener());
		btnEquipos.setForeground(Color.BLACK);
		btnEquipos.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnEquipos.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/ic_devices_black_24dp.png")));
		btnEquipos.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(72, 201, 176)));
		btnEquipos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblEquipos.setBackground(new Color(209, 242, 235));
				btnEquipos.setForeground(new Color(219, 219, 219));
				btnEquipos.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(209, 242, 235)));
				btnEquipos.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/ic_devices_inactivo_24dp.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblEquipos.setBackground(new Color(72, 201, 176));
				btnEquipos.setForeground(new Color(0, 0, 0));
				btnEquipos.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(72, 201, 176)));
				btnEquipos.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/ic_devices_black_24dp.png")));
			}
		});
		btnEquipos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEquipos.setFocusable(false);
		btnEquipos.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
		GridBagConstraints gbc_btnEquipos = new GridBagConstraints();
		gbc_btnEquipos.fill = GridBagConstraints.BOTH;
		gbc_btnEquipos.insets = new Insets(10, 0, 10, 5);
		gbc_btnEquipos.gridx = 1;
		gbc_btnEquipos.gridy = 0;
		panelBotonera.add(btnEquipos, gbc_btnEquipos);
		
		JLabel lblSalidas = new JLabel("");
		lblSalidas.setOpaque(true);
		lblSalidas.setBackground(new Color(93, 173, 226));
		GridBagConstraints gbc_lblSalidas = new GridBagConstraints();
		gbc_lblSalidas.fill = GridBagConstraints.BOTH;
		gbc_lblSalidas.insets = new Insets(0, 0, 10, 0);
		gbc_lblSalidas.gridx = 0;
		gbc_lblSalidas.gridy = 1;
		panelBotonera.add(lblSalidas, gbc_lblSalidas);
		
		JButton btnSalidas = new JButton("Salidas");
		btnSalidas.addActionListener(new EgresoActionListener());
		btnSalidas.setIconTextGap(20);
		btnSalidas.setBorderPainted(false);
		btnSalidas.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnSalidas.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/ic_local_shipping_black_24dp.png")));
		btnSalidas.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(93, 173, 226)));
		btnSalidas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblSalidas.setBackground(new Color(214, 234, 248));
				btnSalidas.setForeground(new Color(219, 219, 219));
				btnSalidas.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(214, 234, 248)));
				btnSalidas.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/ic_local_shipping_inactivo_24dp.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblSalidas.setBackground(new Color(93, 173, 226));
				btnSalidas.setForeground(new Color(0, 0, 0));
				btnSalidas.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(93, 173, 226)));
				btnSalidas.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/ic_local_shipping_black_24dp.png")));
			}
		});
		btnSalidas.setFocusable(false);
		btnSalidas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSalidas.setContentAreaFilled(false);
		btnSalidas.setForeground(Color.BLACK);
		btnSalidas.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
		GridBagConstraints gbc_btnSalidas = new GridBagConstraints();
		gbc_btnSalidas.fill = GridBagConstraints.BOTH;
		gbc_btnSalidas.insets = new Insets(0, 0, 10, 5);
		gbc_btnSalidas.gridx = 1;
		gbc_btnSalidas.gridy = 1;
		panelBotonera.add(btnSalidas, gbc_btnSalidas);
		
		JLabel lblEntradas = new JLabel("");
		lblEntradas.setOpaque(true);
		lblEntradas.setBackground(new Color(236, 112, 99));
		GridBagConstraints gbc_lblEntradas = new GridBagConstraints();
		gbc_lblEntradas.fill = GridBagConstraints.BOTH;
		gbc_lblEntradas.insets = new Insets(0, 0, 10, 0);
		gbc_lblEntradas.gridx = 0;
		gbc_lblEntradas.gridy = 2;
		panelBotonera.add(lblEntradas, gbc_lblEntradas);
		
		JButton btnEntradas = new JButton("Entradas");
		btnEntradas.setIconTextGap(20);
		btnEntradas.setBorderPainted(false);
		btnEntradas.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnEntradas.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/ic_local_shipping2_black_24dp.png")));
		btnEntradas.setForeground(Color.BLACK);
		btnEntradas.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(236, 112, 99)));
		btnEntradas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblEntradas.setBackground(new Color(250, 219, 216));
				btnEntradas.setForeground(new Color(219, 219, 219));
				btnEntradas.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(250, 219, 216)));
				btnEntradas.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/ic_local_shipping2_inactiva_24dp.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblEntradas.setBackground(new Color(236, 112, 99));
				btnEntradas.setForeground(new Color(0, 0, 0));
				btnEntradas.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(236, 112, 99)));
				btnEntradas.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/ic_local_shipping2_black_24dp.png")));
			}
		});
		btnEntradas.setFocusable(false);
		btnEntradas.setContentAreaFilled(false);
		btnEntradas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEntradas.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
		GridBagConstraints gbc_btnEntradas = new GridBagConstraints();
		gbc_btnEntradas.fill = GridBagConstraints.BOTH;
		gbc_btnEntradas.insets = new Insets(0, 0, 10, 5);
		gbc_btnEntradas.gridx = 1;
		gbc_btnEntradas.gridy = 2;
		panelBotonera.add(btnEntradas, gbc_btnEntradas);
		
		JLabel lblReportes = new JLabel("");
		lblReportes.setBackground(new Color(244, 208, 63));
		lblReportes.setOpaque(true);
		GridBagConstraints gbc_lblReportes = new GridBagConstraints();
		gbc_lblReportes.fill = GridBagConstraints.BOTH;
		gbc_lblReportes.gridx = 0;
		gbc_lblReportes.gridy = 3;
		panelBotonera.add(lblReportes, gbc_lblReportes);
		
		JButton btnReportes = new JButton("Reportes");
		btnReportes.setIconTextGap(20);
		btnReportes.setBorderPainted(false);
		btnReportes.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/ic_content_paste_black_24dp.png")));
		btnReportes.setHorizontalTextPosition(SwingConstants.RIGHT);
		btnReportes.setForeground(Color.BLACK);
		btnReportes.setBorder(new MatteBorder(0, 0, 4, 0, (Color) new Color(244, 208, 63)));
		btnReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lblReportes.setBackground(new Color(252, 243, 207));
				btnReportes.setForeground(new Color(219, 219, 219));
				btnReportes.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(252, 243, 207)));
				btnReportes.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/ic_content_paste_inactivo_24dp.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblReportes.setBackground(new Color(244, 208, 63));
				btnReportes.setForeground(new Color(0, 0, 0));
				btnReportes.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(244, 208, 63)));
				btnReportes.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/gui/inventario/imagenes/ic_content_paste_black_24dp.png")));
			}
		});
		btnReportes.setFocusable(false);
		btnReportes.setContentAreaFilled(false);
		btnReportes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReportes.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 40));
		GridBagConstraints gbc_btnReportes = new GridBagConstraints();
		gbc_btnReportes.insets = new Insets(0, 0, 0, 5);
		gbc_btnReportes.fill = GridBagConstraints.BOTH;
		gbc_btnReportes.gridx = 1;
		gbc_btnReportes.gridy = 3;
		panelBotonera.add(btnReportes, gbc_btnReportes);
		
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
		btnSalir.setIcon(new ImageIcon(BienvenidaInventario.class.getResource("/crm/gui/imagenes/cross.png")));
		btnSalir.setFont(new Font("Verdana", Font.PLAIN, 14));
		panelInferior.add(btnSalir);
		
	}
	
	private final class EgresoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			EgresoForm dialog = new EgresoForm(BienvenidaInventario.this,true);
			dialog.setLocationRelativeTo(BienvenidaInventario.this);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}

	private final class SalirActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int resp=JOptionPane.showConfirmDialog(BienvenidaInventario.this,"Seguro desea salir de la aplicacion?", "Salir",JOptionPane.YES_NO_OPTION);
			if (JOptionPane.OK_OPTION == resp){
				System.exit(0);
			}
		}
	}

	private final class EquiposActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				EquipamientoForm dialog = new EquipamientoForm(BienvenidaInventario.this,true);
				dialog.setLocationRelativeTo(BienvenidaInventario.this);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
