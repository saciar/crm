package comerciales2019;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Toolkit;
import gui.inventario.componentes.JPanelGradient;
import javax.swing.border.EtchedBorder;

import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;

import comerciales2019.clientes.ClientePanel;
import comerciales2019.evento.EventoPanel;
import comerciales2019.lugar.LugarPanel;
import comerciales2019.salas.SalasPanel;
import crm.client.util.DateConverter;

import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JCheckBox;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import principal.MyJButton;

public class ContenedorPpto extends JDialog {
	private JPanel panelCentral;
	private ArrayList<JPanel> paneles = new ArrayList<JPanel>();

	private int punteroPaneles = 0;
	private JCheckBox chkConfirmado;
	private JCheckBox chkRechazado;
	private JCheckBox chkOS;
	private JCheckBox chkCancelado;
	private JCheckBox chkOF;
	private JCheckBox chkFacturado;
	private JCheckBox chkCobrado;
	private JCheckBox chkOFA;
	private JCheckBox chkAdelFacturado;
	private JCheckBox chkAdelCobrado;
	private JCheckBox chkOFS;
	private JCheckBox chkSaldoFacturado;
	private JCheckBox chkSaldoCobrado;
	
	private EstadosEvento ultimoEstado = new EstadosEvento(); 
	private JLabel lblFechaDesde;
	private JLabel lblFechaHasta;
	private JPanel panelFijo;
	private SalasPanel panelSalas;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ContenedorPpto dialog = new ContenedorPpto(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ContenedorPpto(JDialog owner, boolean modal) {
		super(owner, modal);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ContenedorPpto.class.getResource("/gui/inventario/imagenes/logo50px.png")));
		setTitle("CRM Comercial");
		if(owner==null){
			//setSize(new Dimension(1280, 720));
			//setPreferredSize(new Dimension(1280, 720));
			setBounds(100, 100, 1280, 720);
		}
		else{
			//setSize(owner.getSize());
			//setPreferredSize(owner.getPreferredSize());
			setBounds(owner.getBounds().x, owner.getBounds().y, owner.getBounds().width, owner.getBounds().height);
		}
		getContentPane().setLayout(new BorderLayout());
		{
			JPanelGradient panelSuperior = new JPanelGradient();
			panelSuperior.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
			panelSuperior.setForeground(Color.WHITE);
			panelSuperior.setColor2(new Color(236, 31, 47));
			panelSuperior.setColor1(new Color(196, 25, 40));
			panelSuperior.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			getContentPane().add(panelSuperior, BorderLayout.NORTH);
			GridBagLayout gbl_panelSuperior = new GridBagLayout();
			gbl_panelSuperior.columnWidths = new int[]{20, 0, 0, 20, 0};
			gbl_panelSuperior.rowHeights = new int[]{5, 0, 5, 0};
			gbl_panelSuperior.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panelSuperior.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelSuperior.setLayout(gbl_panelSuperior);
			{
				MyJButton mjbtnJjj = new MyJButton();
				mjbtnJjj.setFocusable(false);
				mjbtnJjj.setBorderPainted(false);
				mjbtnJjj.setContentAreaFilled(false);
				mjbtnJjj.setIcon(new ImageIcon(ContenedorPpto.class.getResource("/gui/inventario/imagenes/resultset_previous.png")));
				mjbtnJjj.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(panelFijo.isVisible()){
							panelFijo.setVisible(false);
							mjbtnJjj.setIcon(new ImageIcon(ContenedorPpto.class.getResource("/gui/inventario/imagenes/resultset_next.png")));
						}
						else{
							panelFijo.setVisible(true);
							mjbtnJjj.setIcon(new ImageIcon(ContenedorPpto.class.getResource("/gui/inventario/imagenes/resultset_previous.png")));
						}
					}
				});
				GridBagConstraints gbc_mjbtnJjj = new GridBagConstraints();
				gbc_mjbtnJjj.fill = GridBagConstraints.VERTICAL;
				gbc_mjbtnJjj.gridheight = 3;
				gbc_mjbtnJjj.insets = new Insets(0, 0, 5, 5);
				gbc_mjbtnJjj.gridx = 0;
				gbc_mjbtnJjj.gridy = 0;
				panelSuperior.add(mjbtnJjj, gbc_mjbtnJjj);
			}
			{
				JLabel lblCliente = new JLabel("PRESUPUESTO");
				lblCliente.setForeground(Color.WHITE);
				lblCliente.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 35));
				GridBagConstraints gbc_lblCliente = new GridBagConstraints();
				gbc_lblCliente.insets = new Insets(0, 0, 5, 5);
				gbc_lblCliente.gridx = 1;
				gbc_lblCliente.gridy = 1;
				panelSuperior.add(lblCliente, gbc_lblCliente);
			}
			{
				JButton btnBoton = new JButton("Anterior");
				btnBoton.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						btnBoton.setForeground(Color.LIGHT_GRAY);
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						btnBoton.setForeground(Color.WHITE);
					}
				});
				btnBoton.addActionListener(new AnteriorActionListener());
				btnBoton.setIcon(new ImageIcon(ContenedorPpto.class.getResource("/gui/inventario/imagenes/arrow_left.png")));
				btnBoton.setForeground(Color.WHITE);
				btnBoton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
				btnBoton.setFocusable(false);
				btnBoton.setContentAreaFilled(false);
				btnBoton.setBorderPainted(false);
				GridBagConstraints gbc_btnBoton = new GridBagConstraints();
				gbc_btnBoton.anchor = GridBagConstraints.EAST;
				gbc_btnBoton.insets = new Insets(0, 0, 5, 5);
				gbc_btnBoton.gridx = 2;
				gbc_btnBoton.gridy = 1;
				panelSuperior.add(btnBoton, gbc_btnBoton);
			}
			{
				JButton btnNewButton = new JButton("Siguiente");
				btnNewButton.addActionListener(new SiguienteActionListener());
				btnNewButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent arg0) {
						btnNewButton.setForeground(Color.WHITE);
					}
					@Override
					public void mousePressed(MouseEvent e) {
						btnNewButton.setForeground(Color.LIGHT_GRAY);
					}
				});
				btnNewButton.setFocusable(false);
				btnNewButton.setHorizontalTextPosition(SwingConstants.LEFT);
				btnNewButton.setIcon(new ImageIcon(ContenedorPpto.class.getResource("/gui/inventario/imagenes/arrow_right.png")));
				btnNewButton.setForeground(Color.WHITE);
				btnNewButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
				btnNewButton.setContentAreaFilled(false);
				btnNewButton.setBorderPainted(false);
				GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
				gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
				gbc_btnNewButton.gridx = 3;
				gbc_btnNewButton.gridy = 1;
				panelSuperior.add(btnNewButton, gbc_btnNewButton);
			}
		}
		{
			panelCentral = new JPanel();
			panelCentral.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
			panelCentral.setBackground(Color.WHITE);
			getContentPane().add(panelCentral, BorderLayout.CENTER);
			panelCentral.setLayout(new CardLayout(0, 0));
			{
				JPanel panelEventoContenedor = new JPanel();
				panelEventoContenedor.setBackground(Color.WHITE);
				panelCentral.add(panelEventoContenedor, "EVENTO");				
				panelEventoContenedor.setLayout(new BorderLayout(0, 0));
				{
					EventoPanel panelEvento = new EventoPanel();
					paneles.add(panelEvento);
					panelEvento.setOwner(ContenedorPpto.this);
					panelEventoContenedor.add(panelEvento, BorderLayout.NORTH);
				}
			}
			{
				JPanel panelClienteContenedor = new JPanel();
				panelClienteContenedor.setBackground(Color.WHITE);				
				panelCentral.add(panelClienteContenedor, "CLIENTE");				
				panelClienteContenedor.setLayout(new BorderLayout(0, 0));
				{
					ClientePanel panelCliente = new ClientePanel();
					paneles.add(panelCliente);
					panelCliente.setOwner(ContenedorPpto.this);
					panelClienteContenedor.add(panelCliente, BorderLayout.NORTH);
				}
				
			}
			{
				JPanel panelLugarContenedor = new JPanel();
				panelLugarContenedor.setBackground(Color.WHITE);
				panelCentral.add(panelLugarContenedor, "LUGAR");
				//paneles.add(panelLugar);
				panelLugarContenedor.setLayout(new BorderLayout(0, 0));
				{
					LugarPanel panelLugar = new LugarPanel();
					paneles.add(panelLugar);
					panelLugarContenedor.add(panelLugar, BorderLayout.CENTER);
				}
			}
			{
				JPanel panelContenedorSalas = new JPanel();
				panelCentral.add(panelContenedorSalas, "SALAS");
				//paneles.add(panelSalas);
				panelContenedorSalas.setLayout(new BorderLayout(0, 0));
				{
					panelSalas = new SalasPanel();					
					paneles.add(panelSalas);
					panelContenedorSalas.add(panelSalas, BorderLayout.CENTER);
				}
			}
		}
		
		
		{
			panelFijo = new JPanel();
			panelFijo.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
			panelFijo.setBackground(Color.WHITE);
			panelFijo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			getContentPane().add(panelFijo, BorderLayout.WEST);
			GridBagLayout gbl_panelFijo = new GridBagLayout();
			gbl_panelFijo.columnWidths = new int[]{10, 0, 0, 0, 0};
			gbl_panelFijo.rowHeights = new int[]{10, 0, 10, 0, 10, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panelFijo.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panelFijo.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panelFijo.setLayout(gbl_panelFijo);
			{
				JLabel lblNewLabel = new JLabel("Fecha Desde");
				lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
				GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
				gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel.gridx = 1;
				gbc_lblNewLabel.gridy = 1;
				panelFijo.add(lblNewLabel, gbc_lblNewLabel);
			}
			{
				lblFechaDesde = new JLabel("yyyy-mm-dd");
				lblFechaDesde.setText(DateConverter.convertDateToString(new Date(), "dd/MM/yyyy"));
				lblFechaDesde.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblFechaDesde = new GridBagConstraints();
				gbc_lblFechaDesde.anchor = GridBagConstraints.WEST;
				gbc_lblFechaDesde.insets = new Insets(0, 0, 5, 5);
				gbc_lblFechaDesde.gridx = 2;
				gbc_lblFechaDesde.gridy = 1;
				panelFijo.add(lblFechaDesde, gbc_lblFechaDesde);
			}
			{
				JLabel lblNewLabel2 = new JLabel("Fecha hasta");
				lblNewLabel2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
				GridBagConstraints gbc_lblNewLabel2 = new GridBagConstraints();
				gbc_lblNewLabel2.anchor = GridBagConstraints.WEST;
				gbc_lblNewLabel2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel2.gridx = 1;
				gbc_lblNewLabel2.gridy = 3;
				panelFijo.add(lblNewLabel2, gbc_lblNewLabel2);
			}
			{
				lblFechaHasta = new JLabel("yyyy-mm-dd");
				lblFechaHasta.setText(DateConverter.convertDateToString(new Date(), "dd/MM/yyyy"));
				lblFechaHasta.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_lblFechaHasta = new GridBagConstraints();
				gbc_lblFechaHasta.anchor = GridBagConstraints.WEST;
				gbc_lblFechaHasta.insets = new Insets(0, 0, 5, 5);
				gbc_lblFechaHasta.gridx = 2;
				gbc_lblFechaHasta.gridy = 3;
				panelFijo.add(lblFechaHasta, gbc_lblFechaHasta);
			}
			{
				JLabel lblEstado = new JLabel("Estado");
				lblEstado.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
				GridBagConstraints gbc_lblEstado = new GridBagConstraints();
				gbc_lblEstado.gridwidth = 2;
				gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
				gbc_lblEstado.gridx = 1;
				gbc_lblEstado.gridy = 5;
				panelFijo.add(lblEstado, gbc_lblEstado);
			}
			{
				chkConfirmado = new JCheckBox("Confirmado");
				chkConfirmado.addActionListener(new ConfirmadoActionListener());
				chkConfirmado.setOpaque(false);
				chkConfirmado.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_chkConfirmado = new GridBagConstraints();
				gbc_chkConfirmado.anchor = GridBagConstraints.WEST;
				gbc_chkConfirmado.insets = new Insets(0, 0, 5, 5);
				gbc_chkConfirmado.gridx = 1;
				gbc_chkConfirmado.gridy = 7;
				panelFijo.add(chkConfirmado, gbc_chkConfirmado);
			}
			{
				chkRechazado = new JCheckBox("Rechazado");
				chkRechazado.addActionListener(new RechazadoActionListener());
				chkRechazado.setOpaque(false);
				chkRechazado.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_chkRechazado = new GridBagConstraints();
				gbc_chkRechazado.anchor = GridBagConstraints.WEST;
				gbc_chkRechazado.insets = new Insets(0, 0, 5, 5);
				gbc_chkRechazado.gridx = 2;
				gbc_chkRechazado.gridy = 7;
				panelFijo.add(chkRechazado, gbc_chkRechazado);
			}
			{
				chkOS = new JCheckBox("OS");
				chkOS.addActionListener(new OSActionListener());
				chkOS.setEnabled(false);
				chkOS.setOpaque(false);
				chkOS.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_chkOS = new GridBagConstraints();
				gbc_chkOS.anchor = GridBagConstraints.WEST;
				gbc_chkOS.insets = new Insets(0, 0, 5, 5);
				gbc_chkOS.gridx = 1;
				gbc_chkOS.gridy = 8;
				panelFijo.add(chkOS, gbc_chkOS);
			}
			{
				chkCancelado = new JCheckBox("Cancelado");
				chkCancelado.addActionListener(new CanceladoActionListener());
				chkCancelado.setEnabled(false);
				chkCancelado.setOpaque(false);
				chkCancelado.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_chkCancelado = new GridBagConstraints();
				gbc_chkCancelado.anchor = GridBagConstraints.WEST;
				gbc_chkCancelado.insets = new Insets(0, 0, 5, 5);
				gbc_chkCancelado.gridx = 2;
				gbc_chkCancelado.gridy = 8;
				panelFijo.add(chkCancelado, gbc_chkCancelado);
			}
			{
				chkOF = new JCheckBox("OF");
				chkOF.addItemListener(new OFItemChangeListener());
				chkOF.setEnabled(false);
				chkOF.setOpaque(false);
				chkOF.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_chkOF = new GridBagConstraints();
				gbc_chkOF.anchor = GridBagConstraints.WEST;
				gbc_chkOF.insets = new Insets(0, 0, 5, 5);
				gbc_chkOF.gridx = 1;
				gbc_chkOF.gridy = 9;
				panelFijo.add(chkOF, gbc_chkOF);
			}
			{
				chkOFA = new JCheckBox("OFA");
				chkOFA.addItemListener(new OFAItemChangeListener());
				chkOFA.setEnabled(false);
				chkOFA.setOpaque(false);
				chkOFA.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_chkOFA = new GridBagConstraints();
				gbc_chkOFA.anchor = GridBagConstraints.WEST;
				gbc_chkOFA.insets = new Insets(0, 0, 5, 5);
				gbc_chkOFA.gridx = 1;
				gbc_chkOFA.gridy = 10;
				panelFijo.add(chkOFA, gbc_chkOFA);
			}
			{
				chkOFS = new JCheckBox("OFS");
				chkOFS.addItemListener(new OFSItemChangeListener());
				chkOFS.setEnabled(false);
				chkOFS.setOpaque(false);
				chkOFS.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_chkOFS = new GridBagConstraints();
				gbc_chkOFS.anchor = GridBagConstraints.WEST;
				gbc_chkOFS.insets = new Insets(0, 0, 5, 5);
				gbc_chkOFS.gridx = 1;
				gbc_chkOFS.gridy = 11;
				panelFijo.add(chkOFS, gbc_chkOFS);
			}
			{
				{
					chkFacturado = new JCheckBox("Facturado");
					chkFacturado.setEnabled(false);
					chkFacturado.setOpaque(false);
					chkFacturado.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_chkFacturado = new GridBagConstraints();
					gbc_chkFacturado.anchor = GridBagConstraints.WEST;
					gbc_chkFacturado.insets = new Insets(0, 0, 5, 5);
					gbc_chkFacturado.gridx = 1;
					gbc_chkFacturado.gridy = 12;
					panelFijo.add(chkFacturado, gbc_chkFacturado);
				}
				{
					chkCobrado = new JCheckBox("Cobrado");
					chkCobrado.setEnabled(false);
					chkCobrado.setOpaque(false);
					chkCobrado.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_chkCobrado = new GridBagConstraints();
					gbc_chkCobrado.anchor = GridBagConstraints.WEST;
					gbc_chkCobrado.insets = new Insets(0, 0, 5, 5);
					gbc_chkCobrado.gridx = 2;
					gbc_chkCobrado.gridy = 12;
					panelFijo.add(chkCobrado, gbc_chkCobrado);
				}
				{
					chkAdelFacturado = new JCheckBox("Adel. Facturado");
					chkAdelFacturado.setEnabled(false);
					chkAdelFacturado.setOpaque(false);
					chkAdelFacturado.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_chkAdelFacturado = new GridBagConstraints();
					gbc_chkAdelFacturado.anchor = GridBagConstraints.WEST;
					gbc_chkAdelFacturado.insets = new Insets(0, 0, 5, 5);
					gbc_chkAdelFacturado.gridx = 1;
					gbc_chkAdelFacturado.gridy = 13;
					panelFijo.add(chkAdelFacturado, gbc_chkAdelFacturado);
				}
				{
					chkAdelCobrado = new JCheckBox("Adel. cobrado");
					chkAdelCobrado.setEnabled(false);
					chkAdelCobrado.setOpaque(false);
					chkAdelCobrado.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_chkAdelCobrado = new GridBagConstraints();
					gbc_chkAdelCobrado.anchor = GridBagConstraints.WEST;
					gbc_chkAdelCobrado.insets = new Insets(0, 0, 5, 5);
					gbc_chkAdelCobrado.gridx = 2;
					gbc_chkAdelCobrado.gridy = 13;
					panelFijo.add(chkAdelCobrado, gbc_chkAdelCobrado);
				}
				{
					chkSaldoFacturado = new JCheckBox("Saldo Facturado");
					chkSaldoFacturado.setEnabled(false);
					chkSaldoFacturado.setOpaque(false);
					chkSaldoFacturado.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
					GridBagConstraints gbc_chkSaldoFacturado = new GridBagConstraints();
					gbc_chkSaldoFacturado.anchor = GridBagConstraints.WEST;
					gbc_chkSaldoFacturado.insets = new Insets(0, 0, 5, 5);
					gbc_chkSaldoFacturado.gridx = 1;
					gbc_chkSaldoFacturado.gridy = 14;
					panelFijo.add(chkSaldoFacturado, gbc_chkSaldoFacturado);
				}
				chkSaldoCobrado = new JCheckBox("Saldo cobrado");
				chkSaldoCobrado.setEnabled(false);
				chkSaldoCobrado.setOpaque(false);
				chkSaldoCobrado.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
				GridBagConstraints gbc_chkSaldoCobrado = new GridBagConstraints();
				gbc_chkSaldoCobrado.anchor = GridBagConstraints.WEST;
				gbc_chkSaldoCobrado.insets = new Insets(0, 0, 5, 5);
				gbc_chkSaldoCobrado.gridx = 2;
				gbc_chkSaldoCobrado.gridy = 14;
				panelFijo.add(chkSaldoCobrado, gbc_chkSaldoCobrado);
				//setPptoEnOF();
				//setPptoConfirmado();
				setPptoFacturado();
			}
		}
	}
	
	public void setFechaDesde(String date){
		lblFechaDesde.setText(date);
		
		try {
			panelSalas.setRangoFechas(DateConverter.convertStringToDate(date, "dd/MM/yyyy"), DateConverter.convertStringToDate(lblFechaHasta.getText(), "dd/MM/yyyy"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setFechaHasta(String date){
		lblFechaHasta.setText(date);
		try {
			panelSalas.setRangoFechas(DateConverter.convertStringToDate(lblFechaDesde.getText(), "dd/MM/yyyy"), DateConverter.convertStringToDate(date, "dd/MM/yyyy"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//simulacro de presupuesto confirmado
	private void setPptoConfirmado(){
		chkConfirmado.setSelected(true);
		chkConfirmado.setEnabled(false);
		
		chkRechazado.setEnabled(false);
		
		chkOS.setEnabled(true);
		
		chkCancelado.setEnabled(true);
	}
	
	//simulacro de presupuesto que llego hasta OS	
	private void setPptoEnOS(){
		setPptoConfirmado();
		
		chkOS.setSelected(true);
		chkOS.setEnabled(false);
		
		chkOF.setEnabled(true);
		chkOFA.setEnabled(true);
		chkOFS.setEnabled(true);

	}
	
	//simulacro de presupuesto que llego hasta OF
	private void setPptoEnOF(){
		setPptoEnOS();
		
		chkOF.setSelected(true);
		chkOF.setEnabled(false);
		
		chkOFS.setEnabled(true);		
		chkOFA.setEnabled(true);
	}
	
	//simulacro de presupuesto que llego hasta facturado
	private void setPptoFacturado(){
		setPptoEnOF();
		
		chkFacturado.setSelected(true);
		chkFacturado.setEnabled(false);
		
	}
	
	//simulacro de presupuesto que llego hasta adelanto facturado
	private void setPptoOFA(){
		setPptoEnOS();
		
		chkOFA.setSelected(true);
		chkOFA.setEnabled(false);
		
		chkOFS.setEnabled(true);		
		chkOFA.setEnabled(true);
	}

	private final class OFSItemChangeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
		}
	}

	private final class OFAItemChangeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
		}
	}

	private final class OFItemChangeListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
		}
	}	

	private void setUltimoEstado(){		
		
		ultimoEstado.setConfirmadoEnabled(chkConfirmado.isEnabled());
		ultimoEstado.setConfirmadoSelected(chkConfirmado.isSelected());
		ultimoEstado.setRechazadoEnabled(chkRechazado.isEnabled());
		ultimoEstado.setRechazadoSelected(chkRechazado.isSelected());
		
		ultimoEstado.setOsEnabled(chkOS.isEnabled());
		ultimoEstado.setOsSelected(chkOS.isSelected());
		ultimoEstado.setCanceladoEnabled(chkCancelado.isEnabled());
		ultimoEstado.setCanceladoSelected(chkCancelado.isSelected());
		
		ultimoEstado.setOfEnabled(chkOF.isEnabled());
		ultimoEstado.setOfSelected(chkOF.isSelected());
		ultimoEstado.setOfaEnabled(chkOFA.isEnabled());
		ultimoEstado.setOfaSelected(chkOFA.isSelected());
		ultimoEstado.setOfsEnabled(chkOFS.isEnabled());
		ultimoEstado.setOfsSelected(chkOFS.isSelected());
		
		ultimoEstado.setFacturadoEnabled(chkFacturado.isEnabled());
		ultimoEstado.setFacturadoSelected(chkFacturado.isSelected());
		ultimoEstado.setAdelantoFacturadoEnabled(chkAdelFacturado.isEnabled());
		ultimoEstado.setAdelantoFacturadoSelected(chkAdelFacturado.isSelected());
		ultimoEstado.setSaldoFacturadoEnabled(chkSaldoFacturado.isEnabled());
		ultimoEstado.setSaldoFacturadoSelected(chkSaldoFacturado.isSelected());
		
		ultimoEstado.setCobradoEnabled(chkCobrado.isEnabled());
		ultimoEstado.setCobradoSelected(chkCobrado.isSelected());
		ultimoEstado.setAdelantoCobradoEnabled(chkAdelCobrado.isEnabled());
		ultimoEstado.setAdelantoCobradoSelected(chkAdelCobrado.isSelected());
		ultimoEstado.setSaldoCobradoEnabled(chkSaldoCobrado.isEnabled());
		ultimoEstado.setSaldoCobradoSelected(chkSaldoCobrado.isSelected());

	}
	
	private final class CanceladoActionListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if(chkCancelado.isSelected()){		
				setUltimoEstado();
				chkConfirmado.setEnabled(false);
				chkOS.setEnabled(false);
				chkOF.setEnabled(false);
				chkOFA.setEnabled(false);
				chkOFS.setEnabled(false);	
			}
			else{
				/*if(chkOS.isEnabled())
					chkOS.setEnabled(!chkOS.isEnabled());					
				if(chkOS.isSelected()){
					chkOF.setEnabled(!chkOF.isEnabled());
					chkOFA.setEnabled(!chkOFA.isEnabled());
					chkOFS.setEnabled(!chkOFS.isEnabled());
				}
				if(chkOF.isEnabled())
					chkOF.setEnabled(!chkOF.isEnabled());
				*/
				chkConfirmado.setEnabled(ultimoEstado.isConfirmadoEnabled());
				chkConfirmado.setSelected(ultimoEstado.isConfirmadoSelected());
				chkRechazado.setEnabled(ultimoEstado.isRechazadoEnabled());
				chkRechazado.setSelected(ultimoEstado.isRechazadoSelected());
				chkOS.setEnabled(ultimoEstado.isOsEnabled());
				chkOS.setSelected(ultimoEstado.isOsSelected());
				chkOF.setEnabled(ultimoEstado.isOfEnabled());
				chkOF.setSelected(ultimoEstado.isOfSelected());
				chkOFA.setEnabled(ultimoEstado.isOfaEnabled());
				chkOFA.setSelected(ultimoEstado.isOfaSelected());
				chkOFS.setEnabled(ultimoEstado.isOfsEnabled());
				chkOFS.setSelected(ultimoEstado.isOfsSelected());
				chkFacturado.setEnabled(ultimoEstado.isFacturadoEnabled());
				chkFacturado.setSelected(ultimoEstado.isFacturadoSelected());
				chkAdelFacturado.setEnabled(ultimoEstado.isAdelantoFacturadoEnabled());
				chkAdelFacturado.setSelected(ultimoEstado.isAdelantoFacturadoSelected());
				chkSaldoFacturado.setEnabled(ultimoEstado.isSaldoFacturadoEnabled());
				chkSaldoFacturado.setSelected(ultimoEstado.isSaldoFacturadoSelected());
				chkCobrado.setEnabled(ultimoEstado.isCobradoEnabled());
				chkCobrado.setSelected(ultimoEstado.isCobradoSelected());
				chkAdelCobrado.setEnabled(ultimoEstado.isAdelantoCobradoEnabled());
				chkAdelCobrado.setSelected(ultimoEstado.isAdelantoCobradoSelected());
				chkSaldoCobrado.setEnabled(ultimoEstado.isSaldoCobradoEnabled());
				chkSaldoCobrado.setSelected(ultimoEstado.isSaldoCobradoSelected());
				
			}
		}
		
	}

	private final class OSActionListener implements ActionListener {
	
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(chkOS.isSelected()){
				chkOF.setEnabled(true);
				chkOFA.setEnabled(true);
				chkOFS.setEnabled(true);
			}
			else{
				chkOF.setEnabled(false);
				chkOFA.setEnabled(false);
				chkOFS.setEnabled(false);
				chkOF.setSelected(false);
				chkOFA.setSelected(false);
				chkOFS.setSelected(false);
			}
		}
	}

	private final class RechazadoActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(chkRechazado.isSelected()){
				chkConfirmado.setEnabled(false);
			}
			else{
				chkConfirmado.setEnabled(true);
			}
		}
	}

	private final class ConfirmadoActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if(chkConfirmado.isSelected()){
				chkOS.setEnabled(true);
				chkCancelado.setEnabled(true);
				chkRechazado.setEnabled(false);
			}
			else{
				chkOS.setEnabled(false);
				chkCancelado.setEnabled(false);
				chkRechazado.setEnabled(true);
				chkOS.setSelected(false);
			}
		}
	}

	private final class SiguienteActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			punteroPaneles++;
			if(punteroPaneles>=paneles.size())
				punteroPaneles=paneles.size()-1;
			if(paneles.get(punteroPaneles).getClass() == ClientePanel.class){
				((CardLayout)panelCentral.getLayout()).show(panelCentral, "CLIENTE");
			}
			else if(paneles.get(punteroPaneles).getClass() == EventoPanel.class){
				((CardLayout)panelCentral.getLayout()).show(panelCentral, "EVENTO");
			}
			else if(paneles.get(punteroPaneles).getClass() == LugarPanel.class){
				((CardLayout)panelCentral.getLayout()).show(panelCentral, "LUGAR");
			}
			else if(paneles.get(punteroPaneles).getClass() == SalasPanel.class){
				((CardLayout)panelCentral.getLayout()).show(panelCentral, "SALAS");
			}
		}
	}

	private final class AnteriorActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			punteroPaneles--;
			if(punteroPaneles<=0)
				punteroPaneles=0;
			if(paneles.get(punteroPaneles).getClass() == ClientePanel.class){
				((CardLayout)panelCentral.getLayout()).show(panelCentral, "CLIENTE");
			}
			else if(paneles.get(punteroPaneles).getClass() == EventoPanel.class){
				((CardLayout)panelCentral.getLayout()).show(panelCentral, "EVENTO");
			}
			else if(paneles.get(punteroPaneles).getClass() == LugarPanel.class){
				((CardLayout)panelCentral.getLayout()).show(panelCentral, "LUGAR");
			}
			else if(paneles.get(punteroPaneles).getClass() == SalasPanel.class){
				((CardLayout)panelCentral.getLayout()).show(panelCentral, "EVENTO");
			}
		}
	}

}
