package comerciales2019.evento;

import javax.swing.JPanel;

import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;

import gui.inventario.componentes.ABMCategEventosComboBox;
import gui.inventario.componentes.ABMTipoVentaComboBox;
import gui.inventario.componentes.ABMTiposEventosComboBox;
import gui.inventario.componentes.HorariosComboBox;
import gui.inventario.componentes.JPanelGradient;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import comerciales2019.ContenedorPpto;
import crm.client.util.DateConverter;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.DefaultComboBoxModel;

public class EventoPanel extends JPanel {
	
	private JTextField txtNombreEvento;
	private JTextField txtCantPersonas;
	private JDialog owner;
	
	private static Date fechaDesdeSeleccionada = new Date();
	private static Date fechaHastaSeleccionada= new Date();
	
	public static Date getFechaDesdeSeleccionada() {
		return fechaDesdeSeleccionada;
	}

	public static Date getFechaHastaSeleccionada() {
		return fechaHastaSeleccionada;
	}
	
	public void setOwner(JDialog owner) {
		this.owner = owner;
	}
	
	/**
	 * Create the panel.
	 */
	public EventoPanel() {
		setBackground(Color.WHITE);
		initComponents();
	}
	
	private void initComponents(){
		setLayout(new BorderLayout(0, 0));
		
		putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		
		JPanelGradient panel = new JPanelGradient();
		panel.setColor1(new Color(236, 31, 47));
		panel.setColor2(new Color(196, 25, 40));
		add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Evento");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
		panel.add(lblNewLabel);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{20, 0, 20, 0};
		gbl_panelCentral.rowHeights = new int[]{30, 0, 20, 0, 20, 0, 10, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 1;
		panelCentral.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{10, 0, 5, 0, 10, 0, 5, 0, 10, 0};
		gbl_panel_1.rowHeights = new int[]{10, 0, 10, 0, 10, 0, 10, 0, 10, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNombreDeEvento = new JLabel("Nombre de evento");
		lblNombreDeEvento.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNombreDeEvento = new GridBagConstraints();
		gbc_lblNombreDeEvento.anchor = GridBagConstraints.WEST;
		gbc_lblNombreDeEvento.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreDeEvento.gridx = 1;
		gbc_lblNombreDeEvento.gridy = 1;
		panel_1.add(lblNombreDeEvento, gbc_lblNombreDeEvento);
		
		txtNombreEvento = new JTextField();
		txtNombreEvento.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_txtNombreEvento = new GridBagConstraints();
		gbc_txtNombreEvento.gridwidth = 5;
		gbc_txtNombreEvento.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombreEvento.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombreEvento.gridx = 3;
		gbc_txtNombreEvento.gridy = 1;
		panel_1.add(txtNombreEvento, gbc_txtNombreEvento);
		txtNombreEvento.setColumns(10);
		
		JLabel lblFechaDesde = new JLabel("Fecha desde");
		lblFechaDesde.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblFechaDesde = new GridBagConstraints();
		gbc_lblFechaDesde.anchor = GridBagConstraints.WEST;
		gbc_lblFechaDesde.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDesde.gridx = 1;
		gbc_lblFechaDesde.gridy = 3;
		panel_1.add(lblFechaDesde, gbc_lblFechaDesde);
		
		JDateChooser dateDesde = new JDateChooser();
		dateDesde.setDate(new Date());
		dateDesde.getDateEditor().addPropertyChangeListener(new FechaDesdeCambioListener());
		GridBagConstraints gbc_dateDesde = new GridBagConstraints();
		gbc_dateDesde.insets = new Insets(0, 0, 5, 5);
		gbc_dateDesde.fill = GridBagConstraints.BOTH;
		gbc_dateDesde.gridx = 3;
		gbc_dateDesde.gridy = 3;
		panel_1.add(dateDesde, gbc_dateDesde);
		
		JLabel lblFechaHasta = new JLabel("Fecha hasta");
		lblFechaHasta.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblFechaHasta = new GridBagConstraints();
		gbc_lblFechaHasta.anchor = GridBagConstraints.WEST;
		gbc_lblFechaHasta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaHasta.gridx = 5;
		gbc_lblFechaHasta.gridy = 3;
		panel_1.add(lblFechaHasta, gbc_lblFechaHasta);
		
		JDateChooser dateHasta = new JDateChooser();		
		dateHasta.setDate(new Date());
		dateHasta.getDateEditor().addPropertyChangeListener(new FechaHastaCambioListener());
		GridBagConstraints gbc_dateHasta = new GridBagConstraints();
		gbc_dateHasta.anchor = GridBagConstraints.NORTH;
		gbc_dateHasta.insets = new Insets(0, 0, 5, 5);
		gbc_dateHasta.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateHasta.gridx = 7;
		gbc_dateHasta.gridy = 3;
		panel_1.add(dateHasta, gbc_dateHasta);
		
		JLabel lblFechaInstalacion = new JLabel("Fecha instalacion");
		lblFechaInstalacion.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblFechaInstalacion = new GridBagConstraints();
		gbc_lblFechaInstalacion.anchor = GridBagConstraints.WEST;
		gbc_lblFechaInstalacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaInstalacion.gridx = 1;
		gbc_lblFechaInstalacion.gridy = 5;
		panel_1.add(lblFechaInstalacion, gbc_lblFechaInstalacion);
		
		JDateChooser dateInstalacion = new JDateChooser();
		dateInstalacion.setDate(new Date());
		GridBagConstraints gbc_dateInstalacion = new GridBagConstraints();
		gbc_dateInstalacion.insets = new Insets(0, 0, 5, 5);
		gbc_dateInstalacion.fill = GridBagConstraints.BOTH;
		gbc_dateInstalacion.gridx = 3;
		gbc_dateInstalacion.gridy = 5;
		panel_1.add(dateInstalacion, gbc_dateInstalacion);
		
		JLabel lblALas = new JLabel("a las");
		lblALas.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblALas = new GridBagConstraints();
		gbc_lblALas.anchor = GridBagConstraints.WEST;
		gbc_lblALas.insets = new Insets(0, 0, 5, 5);
		gbc_lblALas.gridx = 5;
		gbc_lblALas.gridy = 5;
		panel_1.add(lblALas, gbc_lblALas);
		
		HorariosComboBox cmbHoraInstalacion = new HorariosComboBox();
		cmbHoraInstalacion.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_cmbHoraInstalacion = new GridBagConstraints();
		gbc_cmbHoraInstalacion.insets = new Insets(0, 0, 5, 5);
		gbc_cmbHoraInstalacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbHoraInstalacion.gridx = 7;
		gbc_cmbHoraInstalacion.gridy = 5;
		panel_1.add(cmbHoraInstalacion, gbc_cmbHoraInstalacion);
		
		JLabel lblTotalDePersonas = new JLabel("Total de personas");
		lblTotalDePersonas.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblTotalDePersonas = new GridBagConstraints();
		gbc_lblTotalDePersonas.anchor = GridBagConstraints.WEST;
		gbc_lblTotalDePersonas.insets = new Insets(0, 0, 5, 5);
		gbc_lblTotalDePersonas.gridx = 1;
		gbc_lblTotalDePersonas.gridy = 7;
		panel_1.add(lblTotalDePersonas, gbc_lblTotalDePersonas);
		
		txtCantPersonas = new JTextField();
		txtCantPersonas.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_txtCantPersonas = new GridBagConstraints();
		gbc_txtCantPersonas.insets = new Insets(0, 0, 5, 5);
		gbc_txtCantPersonas.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCantPersonas.gridx = 3;
		gbc_txtCantPersonas.gridy = 7;
		panel_1.add(txtCantPersonas, gbc_txtCantPersonas);
		txtCantPersonas.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 3;
		panelCentral.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 10, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de evento");
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		ABMTiposEventosComboBox cmbTipoEvento = new ABMTiposEventosComboBox();
		cmbTipoEvento.loadItems();
		cmbTipoEvento.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_cmbTipoEvento = new GridBagConstraints();
		gbc_cmbTipoEvento.insets = new Insets(0, 0, 5, 5);
		gbc_cmbTipoEvento.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbTipoEvento.gridx = 3;
		gbc_cmbTipoEvento.gridy = 1;
		panel_2.add(cmbTipoEvento, gbc_cmbTipoEvento);
		
		JLabel lblNewLabel_2 = new JLabel("Categoria de evento");
		lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 5;
		gbc_lblNewLabel_2.gridy = 1;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		ABMCategEventosComboBox cmbCategoriaEvento = new ABMCategEventosComboBox();
		cmbCategoriaEvento.loadItems();
		cmbCategoriaEvento.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_cmbCategoriaEvento = new GridBagConstraints();
		gbc_cmbCategoriaEvento.insets = new Insets(0, 0, 5, 0);
		gbc_cmbCategoriaEvento.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCategoriaEvento.gridx = 7;
		gbc_cmbCategoriaEvento.gridy = 1;
		panel_2.add(cmbCategoriaEvento, gbc_cmbCategoriaEvento);
		
		JLabel lblCategoriaDeEvento = new JLabel("Tipo de venta");
		lblCategoriaDeEvento.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblCategoriaDeEvento = new GridBagConstraints();
		gbc_lblCategoriaDeEvento.insets = new Insets(0, 0, 0, 5);
		gbc_lblCategoriaDeEvento.gridx = 1;
		gbc_lblCategoriaDeEvento.gridy = 3;
		panel_2.add(lblCategoriaDeEvento, gbc_lblCategoriaDeEvento);
		
		ABMTipoVentaComboBox cmbTipoVenta = new ABMTipoVentaComboBox();
		cmbTipoVenta.loadItems();
		cmbTipoVenta.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_cmbTipoVenta = new GridBagConstraints();
		gbc_cmbTipoVenta.insets = new Insets(0, 0, 0, 5);
		gbc_cmbTipoVenta.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbTipoVenta.gridx = 3;
		gbc_cmbTipoVenta.gridy = 3;
		panel_2.add(cmbTipoVenta, gbc_cmbTipoVenta);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 5;
		panelCentral.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0, 200, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JLabel lblNewLabel_3 = new JLabel("Observaciones");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 1;
		panel_3.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setRows(5);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.gridwidth = 2;
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 3;
		panel_3.add(textArea, gbc_textArea);

	}
	
	private final class FechaDesdeCambioListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent e) {
		    if ("date".equals(e.getPropertyName())) {
		    	//cambia la fecha del label en el panel general del ppto
		        ((ContenedorPpto)owner).setFechaDesde(DateConverter.convertDateToString((Date) e.getNewValue(), "dd/MM/yyyy"));
		        fechaDesdeSeleccionada = (Date) e.getNewValue();
		        
		    }
		}
	}

	private final class FechaHastaCambioListener implements PropertyChangeListener {
		@Override
		public void propertyChange(PropertyChangeEvent e) {
		    if ("date".equals(e.getPropertyName())) {
		    	//cambia la fecha del label en el panel general del ppto
		        ((ContenedorPpto)owner).setFechaHasta(DateConverter.convertDateToString((Date) e.getNewValue(), "dd/MM/yyyy"));
		        fechaHastaSeleccionada = (Date) e.getNewValue();
		    }
		}
	}

}
