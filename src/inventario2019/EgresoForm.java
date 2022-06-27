package inventario2019;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JPanel;

import gui.ControlAplicacion;
import gui.inventario.ABMInventario;
import gui.inventario.Egresos;
import gui.inventario.componentes.ChoferesComboBox;
import gui.inventario.componentes.JPanelGradient;
import gui.inventario.componentes.SubfamiliasComboBox;
import gui.inventario.componentes.TransportesComboBox;
import gui.inventario.componentes.tablas.ListadoEquiposItem;
import gui.inventario.componentes.tablas.ListadoEquiposTableModel;
import gui.inventario.componentes.tablas.SalidasTableItem;
import gui.inventario.componentes.tablas.SalidasTableModel;
import gui.tables.ControlEquiposItem;
import gui.tables.ControlEquiposTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;

import crm.client.managers.EgresoEquipoManager;
import crm.client.managers.EgresoManager;
import crm.client.managers.EquipamientosManager;
import crm.client.managers.EquiposFamiliasManager;
import crm.client.managers.EquiposSubFamiliasManager;
import crm.client.managers.MarcasEquiposManager;
import crm.client.managers.PresupuestosManager;
import crm.client.util.DateConverter;
import crm.libraries.abm.entities.Egreso;
import crm.libraries.abm.entities.EgresoEquipo;
import crm.libraries.abm.entities.Equipamientos;
import crm.libraries.abm.entities.EquiposFamilias;
import crm.libraries.abm.entities.EquiposSubFamilias;

import javax.swing.BoxLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;

public class EgresoForm extends JDialog {

	private static final Color COLOR_ROJO=new Color(0xe40520);
	private static final Color COLOR_BORDO=new Color(0xc41928);
	private JTextField txtCodBarraEquipo;
	private JTable table;
	private TransportesComboBox cmbTransportes;
	private ChoferesComboBox cmbChoferes;
	private JTextField txtNroOrden;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EgresoForm dialog = new EgresoForm(null,true);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public EgresoForm(JDialog owner, boolean modal) {
		super(owner,modal);
		setBounds(0, 0, 1280, 720);
		
		JPanelGradient panelSuperior = new JPanelGradient();
		FlowLayout flowLayout = (FlowLayout) panelSuperior.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(35);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelSuperior.setColor2(COLOR_ROJO);
		panelSuperior.setColor1(COLOR_BORDO);
		getContentPane().add(panelSuperior, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Egreso de Equipo");
		lblNewLabel.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 35));
		panelSuperior.add(lblNewLabel);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(Color.WHITE);
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{15, 0, 15, 0, 15, 0};
		gbl_panelCentral.rowHeights = new int[]{15, 0, 15, 0, 15, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JPanel panelOS = new JPanel();
		GridBagConstraints gbc_panelOS = new GridBagConstraints();
		gbc_panelOS.gridwidth = 3;
		gbc_panelOS.insets = new Insets(0, 0, 5, 5);
		gbc_panelOS.fill = GridBagConstraints.BOTH;
		gbc_panelOS.gridx = 1;
		gbc_panelOS.gridy = 1;
		panelCentral.add(panelOS, gbc_panelOS);
		GridBagLayout gbl_panelOS = new GridBagLayout();
		gbl_panelOS.columnWidths = new int[]{0, 1035, 0};
		gbl_panelOS.rowHeights = new int[]{29, 0};
		gbl_panelOS.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panelOS.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panelOS.setLayout(gbl_panelOS);
		
		JLabel label = new JLabel("Ingrese el nro de OS");
		label.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panelOS.add(label, gbc_label);
		
		txtNroOrden = new JTextField();
		txtNroOrden.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER){
					
					buscarPresupuesto();
				}
						
			}
		});
		txtNroOrden.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		txtNroOrden.setColumns(10);
		GridBagConstraints gbc_txtNroOrden = new GridBagConstraints();
		gbc_txtNroOrden.anchor = GridBagConstraints.WEST;
		gbc_txtNroOrden.gridx = 1;
		gbc_txtNroOrden.gridy = 0;
		panelOS.add(txtNroOrden, gbc_txtNroOrden);
		
		JPanel panelTransporte = new JPanel();
		GridBagConstraints gbc_panelTransporte = new GridBagConstraints();
		gbc_panelTransporte.insets = new Insets(0, 0, 5, 5);
		gbc_panelTransporte.fill = GridBagConstraints.BOTH;
		gbc_panelTransporte.gridx = 1;
		gbc_panelTransporte.gridy = 3;
		panelCentral.add(panelTransporte, gbc_panelTransporte);
		GridBagLayout gbl_panelTransporte = new GridBagLayout();
		gbl_panelTransporte.columnWidths = new int[]{15, 0, 0, 0};
		gbl_panelTransporte.rowHeights = new int[]{15, 0, 15, 15, 0, 0, 0};
		gbl_panelTransporte.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panelTransporte.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelTransporte.setLayout(gbl_panelTransporte);
		
		JLabel lblIngrese = new JLabel("Seleccione el transporte");
		lblIngrese.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_lblIngrese = new GridBagConstraints();
		gbc_lblIngrese.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngrese.anchor = GridBagConstraints.WEST;
		gbc_lblIngrese.gridx = 1;
		gbc_lblIngrese.gridy = 1;
		panelTransporte.add(lblIngrese, gbc_lblIngrese);
		
		cmbTransportes = new TransportesComboBox();
		cmbTransportes.setEnabled(false);
		cmbTransportes.loadItems();
		cmbTransportes.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_cmbTransportes = new GridBagConstraints();
		gbc_cmbTransportes.insets = new Insets(0, 0, 5, 5);
		gbc_cmbTransportes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbTransportes.gridx = 1;
		gbc_cmbTransportes.gridy = 2;
		panelTransporte.add(cmbTransportes, gbc_cmbTransportes);
		
		JLabel lblNewLabel_2 = new JLabel("Seleccione el chofer");
		lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 4;
		panelTransporte.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		cmbChoferes = new ChoferesComboBox();
		cmbChoferes.setEnabled(false);
		cmbChoferes.loadItems();
		cmbChoferes.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_cmbChoferes = new GridBagConstraints();
		gbc_cmbChoferes.insets = new Insets(0, 0, 0, 5);
		gbc_cmbChoferes.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbChoferes.gridx = 1;
		gbc_cmbChoferes.gridy = 5;
		panelTransporte.add(cmbChoferes, gbc_cmbChoferes);
		
		JPanel panelEquipos = new JPanel();
		GridBagConstraints gbc_panelEquipos = new GridBagConstraints();
		gbc_panelEquipos.insets = new Insets(0, 0, 5, 5);
		gbc_panelEquipos.fill = GridBagConstraints.BOTH;
		gbc_panelEquipos.gridx = 3;
		gbc_panelEquipos.gridy = 3;
		panelCentral.add(panelEquipos, gbc_panelEquipos);
		GridBagLayout gbl_panelEquipos = new GridBagLayout();
		gbl_panelEquipos.columnWidths = new int[]{15, 241, 0, 127, 447, 15, 0};
		gbl_panelEquipos.rowHeights = new int[]{15, 0, 15, 0, 0};
		gbl_panelEquipos.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelEquipos.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panelEquipos.setLayout(gbl_panelEquipos);
		
		JLabel lblNewLabel_3 = new JLabel("Ingrese el codigo de equipo");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 1;
		panelEquipos.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		txtCodBarraEquipo = new JTextField();
		txtCodBarraEquipo.setEnabled(false);
		txtCodBarraEquipo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				buscarEquipo(arg0);
			}
		});
		txtCodBarraEquipo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_txtCodBarraEquipo = new GridBagConstraints();
		gbc_txtCodBarraEquipo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodBarraEquipo.insets = new Insets(0, 0, 5, 5);
		gbc_txtCodBarraEquipo.gridwidth = 2;
		gbc_txtCodBarraEquipo.gridx = 2;
		gbc_txtCodBarraEquipo.gridy = 1;
		panelEquipos.add(txtCodBarraEquipo, gbc_txtCodBarraEquipo);
		txtCodBarraEquipo.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 4;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		panelEquipos.add(scrollPane, gbc_scrollPane);
		
		table = new JTable();
		table.setModel(new SalidasTableModel());
		scrollPane.setViewportView(table);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table, popupMenu);
		
		JMenuItem mntmInformacion = new JMenuItem("Informacion");
		mntmInformacion.setIcon(new ImageIcon(EgresoForm.class.getResource("/gui/inventario/imagenes/information.png")));
		mntmInformacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//abro jdialog con la info
				InformacionEgresoform dialog = new InformacionEgresoform(EgresoForm.this, true);
				dialog.CompletarPantalla(((SalidasTableModel)table.getModel()).getRow(table.getSelectedRow()));
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				
			}
		});
		popupMenu.add(mntmInformacion);
		
		JPanelGradient panelInferior = new JPanelGradient();
		FlowLayout flowLayout_1 = (FlowLayout) panelInferior.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelInferior.setColor2(COLOR_BORDO);
		panelInferior.setColor1(COLOR_ROJO);
		getContentPane().add(panelInferior, BorderLayout.SOUTH);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(EgresoForm.class.getResource("/gui/inventario/imagenes/disk.png")));
		btnGuardar.addActionListener(new GuardarActionListener());
		btnGuardar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		panelInferior.add(btnGuardar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(EgresoForm.class.getResource("/gui/inventario/imagenes/cross.png")));
		btnSalir.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		panelInferior.add(btnSalir);

	}
	
	private void buscarEquipo(KeyEvent evt){
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                Equipamientos[] equiposEncontrados;
                equiposEncontrados = EquipamientosManager.instance().findByFieldExactly("codigoBarras", txtCodBarraEquipo.getText());

                if (equiposEncontrados.length > 0) {
                	SalidasTableModel model = (SalidasTableModel) table.getModel();

                    for (Equipamientos eq : equiposEncontrados) {
                    	if(!model.isInTable(eq.getCodigoBarras())){
	                        SalidasTableItem item = new SalidasTableItem();
	                        item.setCodigo(equiposEncontrados[0].getCodigo());
	                        item.setCodigoBarras(equiposEncontrados[0].getCodigoBarras());
	                        EquiposSubFamilias subfamilia = EquiposSubFamiliasManager.instance().getById(eq.getSubfamilia());
	                        EquiposFamilias familia = EquiposFamiliasManager.instance().getById(subfamilia.getEqSubfamFamilia());
	                        item.setNombreFamilia(familia.getEqfamDescripcion());
	                        item.setNombreMarca(MarcasEquiposManager.instance().getById(eq.getMarca()).getDescripcion());
	                        item.setNombreSubfamilia(subfamilia.getEqSubfamDescripcion());
	                        item.setModelo(equiposEncontrados[0].getModelo());
	                        model.addRow(item);
	                        table.setModel(model);
	                        table.updateUI();  
                    	}
                    	else{
                    		JOptionPane.showMessageDialog(EgresoForm.this, "Ya fue agregado este Item", "Atencion", JOptionPane.INFORMATION_MESSAGE);
                    	}
                    }
                    table.setModel(model);
                    table.updateUI();
                }
            } catch (RemoteException ex) {
                Logger.getLogger(ABMInventario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
	}
	
	private void buscarPresupuesto() {
		// TODO Auto-generated method stub
		try {
						
			SalidasTableModel model = (SalidasTableModel) table.getModel();
            EgresoEquipoManager manager = EgresoEquipoManager.instance();
            EgresoManager egresoManager = EgresoManager.instance();
            EquipamientosManager equipoManager = EquipamientosManager.instance();            
            
            if (tieneOS(txtNroOrden.getText())) {
            	model.clear();
				txtCodBarraEquipo.setEnabled(true);
				cmbChoferes.setEnabled(true);
				cmbTransportes.setEnabled(true);
				cmbChoferes.setSelectedIndex(0);
        		cmbTransportes.setSelectedIndex(0);
                Egreso[] egresos = egresoManager.findByFieldExactly("nroppto", txtNroOrden.getText());                 
                if(egresos.length>0){
                	for (Egreso egreso : egresos) {
                		EgresoEquipo[] egresosEquipos = manager.findByField("codEgreso", egreso.getCodigo());
                		for(EgresoEquipo egresoEquipo: egresosEquipos){
                			Equipamientos[] equiposEncontrados = equipoManager.findByFieldExactly("codigo", egresoEquipo.getCodEquipo());
                			for (Equipamientos eq : equiposEncontrados) {

                                SalidasTableItem item = new SalidasTableItem();
                                //le cargo al item de la grilla los datos del egreso
                                item.setCodigoEgresoEquipo(egresoEquipo.getCodEgreso());
                                item.setCodigoChoferEgreso(String.valueOf(egreso.getCodChofer()));
                                item.setCodigoTransporteEgreso(String.valueOf(egreso.getCodTransporte()));
                                item.setCodigoUsuarioEgreso(egreso.getCodUsuario());
                                item.setFechaEgreso(egreso.getFecha_egreso());
                                
                                //le cargo al item los datos de los equipos
                                item.setCodigo(equiposEncontrados[0].getCodigo());
                                item.setCodigoBarras(equiposEncontrados[0].getCodigoBarras());
                                EquiposSubFamilias subfamilia = EquiposSubFamiliasManager.instance().getById(eq.getSubfamilia());
                                EquiposFamilias familia = EquiposFamiliasManager.instance().getById(subfamilia.getEqSubfamFamilia());
                                item.setNombreFamilia(familia.getEqfamDescripcion());
                                item.setNombreMarca(MarcasEquiposManager.instance().getById(eq.getMarca()).getDescripcion());
                                item.setNombreSubfamilia(subfamilia.getEqSubfamDescripcion());
                                item.setModelo(equiposEncontrados[0].getModelo());
                                model.addRow(item);
                                table.setModel(model);
                                table.updateUI();  
                            }
                		}
                	}
                }
                table.setModel(model);
                table.updateUI(); 
            } else {            	
                JOptionPane.showMessageDialog(EgresoForm.this, "No existe orden de servicio con ese nro.", "Error", JOptionPane.INFORMATION_MESSAGE);
                txtCodBarraEquipo.setEnabled(false);
				cmbChoferes.setEnabled(false);
				cmbTransportes.setEnabled(false);
				model.clear();
            }
            table.setModel(model);
            table.updateUI(); 
        } catch (RemoteException ex) {
            Logger.getLogger(Egresos.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	private boolean tieneOS(String os){
        try {
            Object[] estado = PresupuestosManager.instance().buscarEstadoActual(Long.parseLong(os));
            for (int j = 0; j < estado.length; j++) {
                Object[] estados = (Object[]) estado[j];
                if ((Integer) estados[5] == 1) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        } catch (RemoteException ex) {
            Logger.getLogger(Egresos.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
	
	private final class GuardarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (JOptionPane.showOptionDialog(EgresoForm.this, "Esta seguro que quiere grabar los cambios?", "Guardar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, rootPane) == 0) {
	            try {
	            	SalidasTableModel model = (SalidasTableModel) table.getModel();
	                EgresoEquipoManager egresoEquiposmanager = EgresoEquipoManager.instance();
	                EgresoManager egresoManager = EgresoManager.instance();

	                Egreso egreso = new Egreso();
	                egreso.setCodChofer(Integer.parseInt(cmbChoferes.searchForeign()));
	                egreso.setCodTransporte(Integer.parseInt(cmbTransportes.searchForeign()));
	                egreso.setCodUsuario(ControlAplicacion.getInstance().getUsuario().getCodigo());
	                egreso.setFecha_egreso(DateConverter.convertDateToString(new Date(), "yyyy-MM-dd hh:mm:ss"));
	                egreso.setNroppto(txtNroOrden.getText());
	                egreso.setTipoEgreso("S");
	                String cod = egresoManager.update(egreso);
	                
	                for (SalidasTableItem tableItem : model.getRows()) {
	                    EgresoEquipo egresoEquipo = new EgresoEquipo();
	                    if(tableItem.getCodigoEgresoEquipo()==null){
	                    	egresoEquipo.setCodEgreso(cod);
	                    	egresoEquipo.setCodEquipo(tableItem.getCodigo());
	                    }
	                    egresoEquiposmanager.update(egresoEquipo);
	                }
	            } catch (RemoteException ex) {
	                Logger.getLogger(Egresos.class.getName()).log(Level.SEVERE, null, ex);
	            }
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
