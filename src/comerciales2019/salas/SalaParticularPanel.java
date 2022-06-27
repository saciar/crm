package comerciales2019.salas;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Date;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JToggleButton;

import comerciales2019.evento.EventoPanel;

import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class SalaParticularPanel extends JPanel {
	private JPanel panel_fechas;
	private DiaSalaPanel pnlFechaHorarios;

	/**
	 * Create the panel.
	 */
	public SalaParticularPanel() {
		setBackground(Color.WHITE);
		initComponent();
	}
	
	private void initComponent(){
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5, 0, 5, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel_fechas = new JPanel();
		GridBagConstraints gbc_panel_fechas = new GridBagConstraints();
		gbc_panel_fechas.insets = new Insets(0, 0, 5, 5);
		gbc_panel_fechas.fill = GridBagConstraints.BOTH;
		gbc_panel_fechas.gridx = 1;
		gbc_panel_fechas.gridy = 1;
		add(panel_fechas, gbc_panel_fechas);
		GridBagLayout gbl_panel_fechas = new GridBagLayout();
		gbl_panel_fechas.columnWidths = new int[]{0, 0, 0};
		gbl_panel_fechas.rowHeights = new int[]{0, 0};
		gbl_panel_fechas.columnWeights = new double[]{1.0};
		gbl_panel_fechas.rowWeights = new double[]{0.0};
		panel_fechas.setLayout(gbl_panel_fechas);
		gbl_panel_fechas.columnWidths = new int[]{0};
		gbl_panel_fechas.rowHeights = new int[]{0};
		gbl_panel_fechas.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel_fechas.rowWeights = new double[]{Double.MIN_VALUE};
		
		pnlFechaHorarios = new DiaSalaPanel(EventoPanel.getFechaDesdeSeleccionada(),EventoPanel.getFechaHastaSeleccionada());
		pnlFechaHorarios.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_pnlFechaHorarios = new GridBagConstraints();
		gbc_pnlFechaHorarios.fill = GridBagConstraints.BOTH;
		gbc_pnlFechaHorarios.gridx = 0;
		gbc_pnlFechaHorarios.gridy = 0;
		panel_fechas.add(pnlFechaHorarios, gbc_pnlFechaHorarios);
		
		JPanel panel_datos = new JPanel();
		GridBagConstraints gbc_panel_datos = new GridBagConstraints();
		gbc_panel_datos.insets = new Insets(0, 0, 5, 5);
		gbc_panel_datos.fill = GridBagConstraints.BOTH;
		gbc_panel_datos.gridx = 1;
		gbc_panel_datos.gridy = 2;
		add(panel_datos, gbc_panel_datos);
		GridBagLayout gbl_panel_datos = new GridBagLayout();
		gbl_panel_datos.columnWidths = new int[]{261, 0};
		gbl_panel_datos.rowHeights = new int[]{20, 0};
		gbl_panel_datos.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_datos.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_datos.setLayout(gbl_panel_datos);
		
		JPanel pnlDatos = new DatosSalaPanel();
		pnlDatos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_pnlDatos = new GridBagConstraints();
		gbc_pnlDatos.fill = GridBagConstraints.BOTH;
		gbc_pnlDatos.gridx = 0;
		gbc_pnlDatos.gridy = 0;
		panel_datos.add(pnlDatos, gbc_pnlDatos);
		
		JPanel panel_servicios = new JPanel();
		panel_servicios.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GridBagConstraints gbc_panel_servicios = new GridBagConstraints();
		gbc_panel_servicios.insets = new Insets(0, 0, 5, 5);
		gbc_panel_servicios.fill = GridBagConstraints.BOTH;
		gbc_panel_servicios.gridx = 1;
		gbc_panel_servicios.gridy = 3;
		add(panel_servicios, gbc_panel_servicios);
		panel_servicios.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlServicios = new ServiciosPanel();
		panel_servicios.add(pnlServicios, BorderLayout.CENTER);

	}

	public void setRangoFechas(Date desde, Date hasta){
		pnlFechaHorarios.setRangoFechas(desde, hasta);
	}
	
}
