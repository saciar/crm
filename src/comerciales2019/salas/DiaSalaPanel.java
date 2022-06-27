package comerciales2019.salas;

import javax.swing.JPanel;
import com.toedter.calendar.JCalendar;

import comerciales2019.salas.horarios.Hora;
import comerciales2019.salas.horarios.HorariosCellEditor;
import comerciales2019.salas.horarios.HorariosCellRenderer;
import comerciales2019.salas.horarios.HorariosTableItem;
import comerciales2019.salas.horarios.HorariosTableModel;
import comerciales2019.salas.servicios.SalaServiciosHeaderRender;
import crm.client.util.DateConverter;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;

public class DiaSalaPanel extends JPanel {
	private JTable table;

	private Date fechaDesdeEvento;
	private Date fechaFinalEvento;
	private JCalendar calendar;
	
	/**
	 * Create the panel.
	 */
	public DiaSalaPanel(Date fechaDesde, Date fechaHasta) {
		
		fechaDesdeEvento = fechaDesde;
		fechaFinalEvento = fechaHasta;
		initComponents();
	}
	
	public void resetFechas(Date fechaDesde, Date fechaHasta){
		calendar.setMinSelectableDate(fechaDesde);
		 
		// Fecha maxima seleccionable
		calendar.setMaxSelectableDate(fechaHasta);
	}
	
	private void initComponents(){
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5, 0, 0, 0, 0, 0, 5, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		calendar = new JCalendar();
		GridLayout gridLayout = (GridLayout) calendar.getDayChooser().getDayPanel().getLayout();
		calendar.getYearChooser().getSpinner().setFont(new Font("Tahoma", Font.PLAIN, 11));
		calendar.getMonthChooser().getComboBox().setFont(new Font("Tahoma", Font.PLAIN, 11));
		calendar.getDayChooser().setFont(new Font("Tahoma", Font.PLAIN, 11));
		calendar.setSundayForeground(Color.RED);
		calendar.setWeekdayForeground(Color.WHITE);
		calendar.setDecorationBackgroundColor(new Color(65,65,65));
		calendar.setWeekOfYearVisible(false);
		GridBagConstraints gbc_calendar = new GridBagConstraints();
		gbc_calendar.insets = new Insets(0, 0, 5, 5);
		gbc_calendar.fill = GridBagConstraints.BOTH;
		gbc_calendar.gridx = 1;
		gbc_calendar.gridy = 1;
		add(calendar, gbc_calendar);
		// Fecha minima seleccionable
		calendar.setMinSelectableDate(fechaDesdeEvento);
		 
		// Fecha maxima seleccionable
		calendar.setMaxSelectableDate(fechaFinalEvento);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JButton btnAgregar = new JButton("");
		btnAgregar.setIcon(new ImageIcon(DiaSalaPanel.class.getResource("/gui/inventario/imagenes/arrow_right.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fecha = DateConverter.convertDateToString(calendar.getDate(), "dd/MM/yyyy");
				HorariosTableModel model = (HorariosTableModel)table.getModel();
				model.addItem(new HorariosTableItem(fecha, new Hora("00:00"),new Hora("00:00")));
				table.setModel(model);
				table.updateUI();
			}
		});
		panel.add(btnAgregar);
		
		JButton btnQuitar = new JButton("");
		btnQuitar.setIcon(new ImageIcon(DiaSalaPanel.class.getResource("/gui/inventario/imagenes/arrow_left.png")));
		panel.add(btnQuitar);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 5;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		table = new JTable();		
		table.setModel(new HorariosTableModel());
		table.setDefaultRenderer(Hora.class, new HorariosCellRenderer());
		table.setDefaultEditor(Hora.class, new HorariosCellEditor(getHorasAMostrar()));
		
		JTableHeader header = table.getTableHeader();
		header.setDefaultRenderer(new SalaServiciosHeaderRender());
		table.setTableHeader(header);
		
		scrollPane.setViewportView(table);

	}
	
	private List<Hora> getHorasAMostrar(){
		List<Hora> horarios = new ArrayList<Hora>();
		horarios.add(new Hora("00:00"));
		horarios.add(new Hora("00:30"));
		horarios.add(new Hora("01:00"));
		horarios.add(new Hora("01:30"));
		
		horarios.add(new Hora("02:00"));
		horarios.add(new Hora("02:30"));
		horarios.add(new Hora("03:00"));
		horarios.add(new Hora("03:30"));
		
		horarios.add(new Hora("04:00"));
		horarios.add(new Hora("04:30"));
		horarios.add(new Hora("05:00"));
		horarios.add(new Hora("05:30"));
		
		horarios.add(new Hora("06:00"));
		horarios.add(new Hora("06:30"));
		horarios.add(new Hora("07:00"));
		horarios.add(new Hora("07:30"));
		
		horarios.add(new Hora("08:00"));
		horarios.add(new Hora("08:30"));
		horarios.add(new Hora("09:00"));
		horarios.add(new Hora("09:30"));
		
		horarios.add(new Hora("10:00"));
		horarios.add(new Hora("10:30"));
		horarios.add(new Hora("11:00"));
		horarios.add(new Hora("11:30"));
		
		horarios.add(new Hora("12:00"));
		horarios.add(new Hora("12:30"));
		horarios.add(new Hora("13:00"));
		horarios.add(new Hora("13:30"));
		
		horarios.add(new Hora("14:00"));
		horarios.add(new Hora("14:30"));
		horarios.add(new Hora("15:00"));
		horarios.add(new Hora("15:30"));
		
		horarios.add(new Hora("16:00"));
		horarios.add(new Hora("16:30"));
		horarios.add(new Hora("17:00"));
		horarios.add(new Hora("17:30"));
		
		horarios.add(new Hora("18:00"));
		horarios.add(new Hora("18:30"));
		horarios.add(new Hora("19:00"));
		horarios.add(new Hora("19:30"));
		
		horarios.add(new Hora("20:00"));
		horarios.add(new Hora("20:30"));
		horarios.add(new Hora("21:00"));
		horarios.add(new Hora("21:30"));
		
		horarios.add(new Hora("22:00"));
		horarios.add(new Hora("22:30"));
		horarios.add(new Hora("23:00"));
		horarios.add(new Hora("23:30"));

		return horarios;
	}

	
	public void setRangoFechas(Date desde, Date hasta){
		calendar.setMinSelectableDate(desde);
		calendar.setMaxSelectableDate(hasta);
	}
}
