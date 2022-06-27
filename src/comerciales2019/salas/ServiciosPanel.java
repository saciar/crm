package comerciales2019.salas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import comerciales2019.salas.servicios.SalaServicioItem;
import comerciales2019.salas.servicios.SalaServiciosHeaderRender;
import comerciales2019.salas.servicios.SalaServiciosTableModel;
import comerciales2019.salas.servicios.TableRenderSalas;
import principal.MyJButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;

public class ServiciosPanel extends JPanel {
	
	private JTable tblServicios;

	/**
	 * Create the panel.
	 */
	public ServiciosPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{5, 799, 5, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 288, 35, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblServicios = new JLabel("Servicios");
		lblServicios.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
		GridBagConstraints gbc_lblServicios = new GridBagConstraints();
		gbc_lblServicios.anchor = GridBagConstraints.WEST;
		gbc_lblServicios.insets = new Insets(0, 0, 5, 5);
		gbc_lblServicios.gridx = 1;
		gbc_lblServicios.gridy = 1;
		add(lblServicios, gbc_lblServicios);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 2;
		add(scrollPane, gbc_scrollPane);
		
		tblServicios = new JTable();
		tblServicios.setModel(new SalaServiciosTableModel());
		TableRenderSalas render = new TableRenderSalas(tblServicios);
		
		JTableHeader header = tblServicios.getTableHeader();
		header.setDefaultRenderer(new SalaServiciosHeaderRender());
		tblServicios.setTableHeader(header);
		
		scrollPane.setViewportView(tblServicios);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 3;
		add(panel, gbc_panel);
		
		MyJButton btnAgregar = new MyJButton();
		btnAgregar.setIcon(new ImageIcon(ServiciosPanel.class.getResource("/gui/inventario/imagenes/add.png")));
		btnAgregar.addActionListener(new AgregarActionListener());
		btnAgregar.setText("Agregar");
		panel.add(btnAgregar);

	}

	private final class AgregarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			SalaServiciosTableModel model = (SalaServiciosTableModel)tblServicios.getModel();
			SalaServicioItem item = new SalaServicioItem();
			item.setCantidad(1);
			item.setDescuento(10);
			item.setDias(1);
			item.setFamilia("familia de serv");
			item.setFamiliaCodigo("2");
			item.setFechaAlta("2020-05-23 00:00:00");
			item.setOpcional(true);
			item.setPrecioLista(3500);
			item.setServicio("este servicio");
			item.setServicioCodigo("152");
			item.setSubContratado(false);
			item.setTotal(3500);
			item.setUnicoDia(true);
			item.setEditar("EDITAR");
			item.setEliminar("ELIMINAR");
			
			model.addRow(item);
			tblServicios.setModel(model);
			tblServicios.updateUI();
		}
	}
}
