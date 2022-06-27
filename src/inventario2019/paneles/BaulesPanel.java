package inventario2019.paneles;

import javax.swing.JPanel;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import gui.inventario.componentes.tablas.ListadoEquiposRackItem;
import gui.inventario.componentes.tablas.ListadoEquiposRackTableModel;
import inventario2019.ContenidoRack;
import inventario2019.EquipamientoForm;

import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import crm.client.managers.EquipamientosManager;
import crm.client.managers.EquiposFamiliasManager;
import crm.client.managers.EquiposSubFamiliasManager;
import crm.client.managers.MarcasEquiposManager;
import crm.client.managers.RacksManager;
import crm.libraries.abm.entities.Equipamientos;
import crm.libraries.abm.entities.EquiposSubFamilias;
import crm.libraries.abm.entities.Rack;

public class BaulesPanel extends JPanel {
	private JTable table;
	private ContenidoRack owner;
	private ArrayList<Integer> codBorrados = new ArrayList<>();
	private int codRackSeleccionado;
	/**
	 * Create the panel.
	 */
	public BaulesPanel(ContenidoRack owner) {
		this.owner=owner;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{15, 0, 0, 15, 0};
		gridBagLayout.rowHeights = new int[]{15, 0, 15, 15, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panelGrilla = new JPanel();
		GridBagConstraints gbc_panelGrilla = new GridBagConstraints();
		gbc_panelGrilla.gridwidth = 2;
		gbc_panelGrilla.insets = new Insets(0, 0, 5, 5);
		gbc_panelGrilla.fill = GridBagConstraints.BOTH;
		gbc_panelGrilla.gridx = 1;
		gbc_panelGrilla.gridy = 1;
		add(panelGrilla, gbc_panelGrilla);
		GridBagLayout gbl_panelGrilla = new GridBagLayout();
		gbl_panelGrilla.columnWidths = new int[]{0, 10, 0, 0};
		gbl_panelGrilla.rowHeights = new int[]{0, 0, 0};
		gbl_panelGrilla.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelGrilla.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panelGrilla.setLayout(gbl_panelGrilla);
		
		JLabel lblNewLabel = new JLabel("Contenido del Rack");
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelGrilla.add(lblNewLabel, gbc_lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panelGrilla.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(new ListadoEquiposRackTableModel());
		table.getColumnModel().getColumn(0).setResizable(false);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 2;
		gbc_panel.gridy = 1;
		panelGrilla.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{107, 0};
		gbl_panel.rowHeights = new int[]{27, 10, 27, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new AgregarActionListener());
		btnAgregar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnAgregar.setIcon(new ImageIcon(BaulesPanel.class.getResource("/gui/inventario/imagenes/add.png")));
		GridBagConstraints gbc_btnAgregar = new GridBagConstraints();
		gbc_btnAgregar.anchor = GridBagConstraints.WEST;
		gbc_btnAgregar.insets = new Insets(0, 0, 5, 0);
		gbc_btnAgregar.gridx = 0;
		gbc_btnAgregar.gridy = 0;
		panel.add(btnAgregar, gbc_btnAgregar);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new QuitarActionListener());
		btnQuitar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnQuitar.setIcon(new ImageIcon(BaulesPanel.class.getResource("/gui/inventario/imagenes/delete.png")));
		GridBagConstraints gbc_btnQuitar = new GridBagConstraints();
		gbc_btnQuitar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnQuitar.gridx = 0;
		gbc_btnQuitar.gridy = 2;
		panel.add(btnQuitar, gbc_btnQuitar);
		
		JPanel panelBotoneraInferior = new JPanel();
		GridBagConstraints gbc_panelBotoneraInferior = new GridBagConstraints();
		gbc_panelBotoneraInferior.insets = new Insets(0, 0, 5, 5);
		gbc_panelBotoneraInferior.fill = GridBagConstraints.BOTH;
		gbc_panelBotoneraInferior.gridx = 2;
		gbc_panelBotoneraInferior.gridy = 2;
		add(panelBotoneraInferior, gbc_panelBotoneraInferior);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new GuardarActionListener());
		btnNewButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnNewButton.setIcon(new ImageIcon(BaulesPanel.class.getResource("/gui/inventario/imagenes/disk.png")));
		panelBotoneraInferior.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new CancelarActionListener());
		btnNewButton_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		btnNewButton_1.setIcon(new ImageIcon(BaulesPanel.class.getResource("/gui/inventario/imagenes/cross.png")));
		panelBotoneraInferior.add(btnNewButton_1);

	}
	
	public void cargarEquipamientos(int codRackSeleccionado){
		this.codRackSeleccionado = codRackSeleccionado;
		RacksManager manager = RacksManager.instance();
		try {
			Rack[] racks = manager.findByField("codRack", String.valueOf(codRackSeleccionado));
			ListadoEquiposRackTableModel model = (ListadoEquiposRackTableModel) table.getModel();

			for(Rack rack : racks){	
				Equipamientos result = EquipamientosManager.instance().getById(rack.getCodEquipamiento());
				ListadoEquiposRackItem eq = crearRowEquipamiento(result, rack.getCodigo());

				model.addRow(eq);
		
			}
			table.setModel(model);
		    table.updateUI();
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private final class GuardarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int resp=JOptionPane.showConfirmDialog(owner,"Seguro desea grabar la carga de equipos?", "Guardar",JOptionPane.YES_NO_OPTION);
			if (JOptionPane.OK_OPTION == resp){
				try {
					RacksManager manager = RacksManager.instance();
					if(codBorrados.size()>0){
						for(Integer i: codBorrados){
							manager.removeRack(i.intValue());
							System.out.println("codigo: "+i);
						}
					}
					ListadoEquiposRackTableModel model = (ListadoEquiposRackTableModel) table.getModel();
					ArrayList<ListadoEquiposRackItem> equiposItems = (ArrayList<ListadoEquiposRackItem>) model.getRows();
					for(ListadoEquiposRackItem item: equiposItems){
						Rack rack = new Rack();
						rack.setCodigo(item.getCodRackEquipo());
						rack.setCodRack(String.valueOf(codRackSeleccionado));
						rack.setCodEquipamiento(String.valueOf(item.getCodigo()));
						rack.setActivo("S");
						manager.update(rack);
					}
					JOptionPane.showMessageDialog(owner, "Equipo guardado con exito!", "Guardado", JOptionPane.INFORMATION_MESSAGE);
					owner.dispose();
				} catch (RemoteException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(owner, "Se ha producido un error!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private final class CancelarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {				
			int resp=JOptionPane.showConfirmDialog(owner,"Seguro desea cancelar la carga de equipos?", "Cancelar",JOptionPane.YES_NO_OPTION);
			if (JOptionPane.OK_OPTION == resp){
				owner.dispose();
			}
		}
	}
	
	private final class QuitarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
			ListadoEquiposRackTableModel model = (ListadoEquiposRackTableModel) table.getModel();
			if(table.getSelectedRow()>0){
				codBorrados.add(Integer.parseInt((model.getRow(table.getSelectedRow())).getCodRackEquipo()));
				model.removeRow(table.getSelectedRow()); 
			}
			else{
				
			}
		}
	}

	private final class AgregarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				String codBarra=JOptionPane.showInputDialog("Ingrese el codigo de barra del equipo a agregar");
				ListadoEquiposRackTableModel model = (ListadoEquiposRackTableModel) table.getModel();
				ArrayList<ListadoEquiposRackItem> equiposItems = new ArrayList<ListadoEquiposRackItem>();

				Object[] result = EquipamientosManager.instance().buscarEquipamientoxCodigoBarras(1,codBarra);
				equiposItems = crearEquipamiento(result);
				
				if (equiposItems.size() > 0) {
				    for (ListadoEquiposRackItem eq : equiposItems) {
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
		
	}
	
	private ArrayList<ListadoEquiposRackItem> crearEquipamiento(Object[] result) {
		// TODO Auto-generated method stub			
		try {
			ArrayList<ListadoEquiposRackItem> equiposItems = new ArrayList<ListadoEquiposRackItem>();
			
			for (int i = 0; i < result.length; i++){
				ListadoEquiposRackItem r = new ListadoEquiposRackItem();
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
	
	private ListadoEquiposRackItem crearRowEquipamiento(Equipamientos result, String codRackEquipo) {
		// TODO Auto-generated method stub			
		try {
			ListadoEquiposRackItem r = new ListadoEquiposRackItem();

				r.setCodigo(Integer.parseInt(result.getCodigo()));
				r.setMarca(Integer.parseInt(result.getMarca()));
				r.setSubfamilia(Integer.parseInt(result.getSubfamilia()));
				r.setNroSerie(result.getNroSerie());
				r.setDeposito(Integer.parseInt(result.getDeposito()));
				
				r.setActivo(result.getActivo());
				r.setObservaciones(result.getObservaciones());
				
				r.setEstado(Integer.parseInt(result.getEstado()));
				r.setCodigoBarras(Integer.parseInt(result.getCodigoBarras()));
				r.setModelo(result.getModelo());
				r.setAlto(result.getAlto());
				r.setAncho(result.getAncho());
				r.setLargo(result.getLargo());
				r.setPeso(result.getPeso());
				r.setCodRackEquipo(codRackEquipo);
				
				EquiposSubFamilias subfamilia= EquiposSubFamiliasManager.instance().getById(result.getSubfamilia());
				
				r.setNombreFamilia((EquiposFamiliasManager.instance().getById(subfamilia.getEqSubfamFamilia()).getEqfamDescripcion()));
				r.setNombreSubfamilia(subfamilia.getEqSubfamDescripcion());
				r.setNombreMarca((MarcasEquiposManager.instance().getById(result.getMarca())).getDescripcion());
			
			return r;				
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
