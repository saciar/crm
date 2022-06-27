package inventario2019;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;

import crm.client.managers.EquipamientosManager;
import crm.client.managers.EquiposSubFamiliasManager;
import crm.libraries.abm.entities.Equipamientos;
import crm.libraries.abm.entities.EquiposSubFamilias;
import gui.inventario.ABMInventario;
import gui.inventario.componentes.DepositosComboBox;
import gui.inventario.componentes.FamiliasComboBox;
import gui.inventario.componentes.JPanelGradient;
import gui.inventario.componentes.MarcasComboBox;
import gui.inventario.componentes.SubfamiliasComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EquipamientoModifForm extends JDialog {
	
	private JTextField txtModelo;
	private JTextField txtNroSerie;
	private JTextField txtAlto;
	private JTextField txtAncho;
	private JTextField txtLargo;
	private JTextField txtPeso;
	private FamiliasComboBox cmbFamilia;
	private SubfamiliasComboBox cmbSubfamilia;
	private MarcasComboBox cmbMarca;
	private DepositosComboBox cmbDeposito;
	private JTextArea txtAreaObservaciones;
	private JTextField txtCodigo;

	private Equipamientos itemSeleccionado;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EquipamientoModifForm dialog = new EquipamientoModifForm(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EquipamientoModifForm(JDialog owner, boolean modal) {
		super(owner, modal);
		setTitle("Modificacion de Equipo");
		getContentPane().setBackground(Color.WHITE);
		setBounds(0, 0, 1280, 720);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(20);
		borderLayout.setHgap(20);
		getContentPane().setLayout(borderLayout);
		
		JPanelGradient panelGradient = new JPanelGradient();
		FlowLayout flowLayout = (FlowLayout) panelGradient.getLayout();
		flowLayout.setVgap(10);
		flowLayout.setHgap(35);
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelGradient.setColor2(new Color(228, 5, 32));
		panelGradient.setColor1(new Color(196, 25, 40));
		panelGradient.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		getContentPane().add(panelGradient, BorderLayout.NORTH);
		
		JLabel lblModificarEquipo = new JLabel("Modificar equipo");
		lblModificarEquipo.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		lblModificarEquipo.setForeground(Color.WHITE);
		lblModificarEquipo.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 35));
		panelGradient.add(lblModificarEquipo);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{20, 0, 0, 0, 0, 20, 0};
		gbl_panel.rowHeights = new int[]{0, 18, 0, 18, 0, 18, 0, 20, 0, 18, 0, 20, 0, 20, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblCodigoDeBarras = new JLabel("Codigo de barras");
		lblCodigoDeBarras.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_lblCodigoDeBarras = new GridBagConstraints();
		gbc_lblCodigoDeBarras.anchor = GridBagConstraints.EAST;
		gbc_lblCodigoDeBarras.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigoDeBarras.gridx = 1;
		gbc_lblCodigoDeBarras.gridy = 0;
		panel.add(lblCodigoDeBarras, gbc_lblCodigoDeBarras);
		
		txtCodigo = new JTextField();
		txtCodigo.setEnabled(false);
		txtCodigo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		txtCodigo.setColumns(10);
		GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
		gbc_txtCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_txtCodigo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtCodigo.gridx = 2;
		gbc_txtCodigo.gridy = 0;
		panel.add(txtCodigo, gbc_txtCodigo);
		
		JLabel label = new JLabel("Familia");
		label.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.VERTICAL;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 2;
		panel.add(label, gbc_label);
		
		cmbFamilia = new FamiliasComboBox();
		cmbFamilia.loadItems();
		cmbFamilia.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_cmbFamilia = new GridBagConstraints();
		gbc_cmbFamilia.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbFamilia.insets = new Insets(5, 0, 5, 5);
		gbc_cmbFamilia.gridx = 2;
		gbc_cmbFamilia.gridy = 2;
		panel.add(cmbFamilia, gbc_cmbFamilia);
		
		JLabel label_1 = new JLabel("          ");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 3;
		gbc_label_1.gridy = 2;
		panel.add(label_1, gbc_label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridheight = 13;
		gbc_panel_1.insets = new Insets(5, 0, 5, 5);
		gbc_panel_1.gridx = 4;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel label_13 = new JLabel("Observaciones");
		label_13.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_label_13 = new GridBagConstraints();
		gbc_label_13.insets = new Insets(0, 0, 5, 0);
		gbc_label_13.gridx = 0;
		gbc_label_13.gridy = 0;
		panel_1.add(label_13, gbc_label_13);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel_1.add(scrollPane, gbc_scrollPane);
		
		txtAreaObservaciones = new JTextArea();
		txtAreaObservaciones.setWrapStyleWord(true);
		txtAreaObservaciones.setLineWrap(true);
		txtAreaObservaciones.setColumns(25);
		txtAreaObservaciones.setFont(new Font("Monospaced", Font.PLAIN, 18));
		scrollPane.setViewportView(txtAreaObservaciones);
		
		JLabel label_3 = new JLabel("Subfamilia");
		label_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.VERTICAL;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 4;
		panel.add(label_3, gbc_label_3);
		
		cmbSubfamilia = new SubfamiliasComboBox();
		cmbSubfamilia.loadItemsPorFamilia(null);
		cmbSubfamilia.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_cmbSubfamilia = new GridBagConstraints();
		gbc_cmbSubfamilia.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbSubfamilia.insets = new Insets(0, 0, 5, 5);
		gbc_cmbSubfamilia.gridx = 2;
		gbc_cmbSubfamilia.gridy = 4;
		panel.add(cmbSubfamilia, gbc_cmbSubfamilia);
		
		JLabel label_4 = new JLabel("Marca");
		label_4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.fill = GridBagConstraints.VERTICAL;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 1;
		gbc_label_4.gridy = 6;
		panel.add(label_4, gbc_label_4);
		
		cmbMarca = new MarcasComboBox();
		cmbMarca.loadItems();
		cmbMarca.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_cmbMarca = new GridBagConstraints();
		gbc_cmbMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbMarca.insets = new Insets(0, 0, 5, 5);
		gbc_cmbMarca.gridx = 2;
		gbc_cmbMarca.gridy = 6;
		panel.add(cmbMarca, gbc_cmbMarca);
		
		JLabel label_5 = new JLabel("Modelo");
		label_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.fill = GridBagConstraints.VERTICAL;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 1;
		gbc_label_5.gridy = 8;
		panel.add(label_5, gbc_label_5);
		
		txtModelo = new JTextField();
		txtModelo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(Character.isLetter(e.getKeyChar()))
					txtModelo.setText(txtModelo.getText().toUpperCase());
			}
		});
		txtModelo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		txtModelo.setColumns(10);
		GridBagConstraints gbc_txtModelo = new GridBagConstraints();
		gbc_txtModelo.fill = GridBagConstraints.BOTH;
		gbc_txtModelo.insets = new Insets(0, 0, 5, 5);
		gbc_txtModelo.gridx = 2;
		gbc_txtModelo.gridy = 8;
		panel.add(txtModelo, gbc_txtModelo);
		
		JLabel label_6 = new JLabel("Nro de Serie");
		label_6.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.fill = GridBagConstraints.VERTICAL;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 1;
		gbc_label_6.gridy = 10;
		panel.add(label_6, gbc_label_6);
		
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
		gbc_txtNroSerie.fill = GridBagConstraints.BOTH;
		gbc_txtNroSerie.insets = new Insets(0, 0, 5, 5);
		gbc_txtNroSerie.gridx = 2;
		gbc_txtNroSerie.gridy = 10;
		panel.add(txtNroSerie, gbc_txtNroSerie);
		
		JLabel label_7 = new JLabel("Deposito");
		label_7.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.fill = GridBagConstraints.VERTICAL;
		gbc_label_7.insets = new Insets(0, 0, 5, 5);
		gbc_label_7.gridx = 1;
		gbc_label_7.gridy = 12;
		panel.add(label_7, gbc_label_7);
		
		cmbDeposito = new DepositosComboBox();
		cmbDeposito.loadItems();
		cmbDeposito.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_cmbDeposito = new GridBagConstraints();
		gbc_cmbDeposito.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbDeposito.insets = new Insets(0, 0, 5, 5);
		gbc_cmbDeposito.gridx = 2;
		gbc_cmbDeposito.gridy = 12;
		panel.add(cmbDeposito, gbc_cmbDeposito);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridwidth = 4;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 14;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel label_8 = new JLabel("Dimensiones");
		label_8.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.insets = new Insets(0, 0, 5, 5);
		gbc_label_8.gridx = 0;
		gbc_label_8.gridy = 0;
		panel_2.add(label_8, gbc_label_8);
		
		JLabel label_9 = new JLabel("Alto (Cm)");
		label_9.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		GridBagConstraints gbc_label_9 = new GridBagConstraints();
		gbc_label_9.anchor = GridBagConstraints.NORTHEAST;
		gbc_label_9.insets = new Insets(0, 0, 0, 5);
		gbc_label_9.gridx = 1;
		gbc_label_9.gridy = 1;
		panel_2.add(label_9, gbc_label_9);
		
		txtAlto = new JTextField();
		txtAlto.setColumns(10);
		GridBagConstraints gbc_txtAlto = new GridBagConstraints();
		gbc_txtAlto.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAlto.insets = new Insets(0, 0, 0, 5);
		gbc_txtAlto.gridx = 2;
		gbc_txtAlto.gridy = 1;
		panel_2.add(txtAlto, gbc_txtAlto);
		
		JLabel label_10 = new JLabel("Ancho (Cm)");
		label_10.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		GridBagConstraints gbc_label_10 = new GridBagConstraints();
		gbc_label_10.anchor = GridBagConstraints.EAST;
		gbc_label_10.insets = new Insets(0, 0, 0, 5);
		gbc_label_10.gridx = 3;
		gbc_label_10.gridy = 1;
		panel_2.add(label_10, gbc_label_10);
		
		txtAncho = new JTextField();
		txtAncho.setColumns(10);
		GridBagConstraints gbc_txtAncho = new GridBagConstraints();
		gbc_txtAncho.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAncho.insets = new Insets(0, 0, 0, 5);
		gbc_txtAncho.gridx = 4;
		gbc_txtAncho.gridy = 1;
		panel_2.add(txtAncho, gbc_txtAncho);
		
		JLabel label_11 = new JLabel("Largo (Cm)");
		label_11.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		GridBagConstraints gbc_label_11 = new GridBagConstraints();
		gbc_label_11.anchor = GridBagConstraints.EAST;
		gbc_label_11.insets = new Insets(0, 0, 0, 5);
		gbc_label_11.gridx = 5;
		gbc_label_11.gridy = 1;
		panel_2.add(label_11, gbc_label_11);
		
		txtLargo = new JTextField();
		txtLargo.setColumns(10);
		GridBagConstraints gbc_txtLargo = new GridBagConstraints();
		gbc_txtLargo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtLargo.insets = new Insets(0, 0, 0, 5);
		gbc_txtLargo.gridx = 6;
		gbc_txtLargo.gridy = 1;
		panel_2.add(txtLargo, gbc_txtLargo);
		
		JLabel label_12 = new JLabel("Peso (Kg)");
		label_12.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		GridBagConstraints gbc_label_12 = new GridBagConstraints();
		gbc_label_12.anchor = GridBagConstraints.EAST;
		gbc_label_12.insets = new Insets(0, 0, 0, 5);
		gbc_label_12.gridx = 7;
		gbc_label_12.gridy = 1;
		panel_2.add(label_12, gbc_label_12);
		
		txtPeso = new JTextField();
		txtPeso.setColumns(10);
		GridBagConstraints gbc_txtPeso = new GridBagConstraints();
		gbc_txtPeso.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPeso.insets = new Insets(0, 0, 0, 5);
		gbc_txtPeso.gridx = 8;
		gbc_txtPeso.gridy = 1;
		panel_2.add(txtPeso, gbc_txtPeso);
		
		JPanelGradient panelGradient_1 = new JPanelGradient();
		panelGradient_1.setColor2(new Color(228, 5, 32));
		panelGradient_1.setColor1(new Color(196, 25, 40));
		getContentPane().add(panelGradient_1, BorderLayout.SOUTH);
		panelGradient_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton button = new JButton("Guardar");
		button.addActionListener(new GuardarModifActionListener());
		button.setIcon(new ImageIcon(EquipamientoModifForm.class.getResource("/gui/inventario/imagenes/disk.png")));
		button.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		button.setActionCommand("OK");
		panelGradient_1.add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int res = JOptionPane.showConfirmDialog(EquipamientoModifForm.this, "Desea cancelar la modificacion de equipamiento?\nSe perderan los cambios no guardados.", "Cancelar", JOptionPane.YES_NO_OPTION );
                if(res == JOptionPane.YES_OPTION){
                	dispose();
                }	
			}
		});
		button_1.setIcon(new ImageIcon(EquipamientoModifForm.class.getResource("/gui/inventario/imagenes/cross.png")));
		button_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		button_1.setActionCommand("Cancel");
		panelGradient_1.add(button_1);
	}
	
	private final class GuardarModifActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	            try {
	            	Equipamientos eq = new Equipamientos();
	                eq.setCodigo(itemSeleccionado.getCodigo());
	                eq.setMarca(cmbMarca.searchForeign());
	                if (!txtNroSerie.getText().isEmpty()) {
	                    eq.setNroSerie(txtNroSerie.getText());
	                }
	                if (!txtAreaObservaciones.getText().isEmpty()) {
	                    eq.setObservaciones(txtAreaObservaciones.getText());
	                }
	                eq.setDeposito(cmbDeposito.searchForeign());
	                eq.setSubfamilia(cmbSubfamilia.searchForeign());
	                eq.setActivo("S");
	                eq.setEstado(itemSeleccionado.getEstado());
	                eq.setCodigoBarras(txtCodigo.getText());
	                eq.setModelo(txtModelo.getText());
	                if(!txtAlto.getText().isEmpty())
	                    eq.setAlto(Integer.parseInt(txtAlto.getText()));
	                if(!txtAncho.getText().isEmpty())
	                    eq.setAncho(Integer.parseInt(txtAncho.getText()));
	                if(!txtLargo.getText().isEmpty())
	                    eq.setLargo(Integer.parseInt(txtLargo.getText()));
	                if(!txtPeso.getText().isEmpty())
	                    eq.setPeso(Integer.parseInt(txtPeso.getText()));
	                
	                EquipamientosManager.instance().update(eq);
	                JOptionPane.showMessageDialog(EquipamientoModifForm.this, "Equipo guardado con exito!", "Guardado", JOptionPane.INFORMATION_MESSAGE);
	                dispose();
	            } catch (RemoteException ex) {
	                Logger.getLogger(ABMInventario.class.getName()).log(Level.SEVERE, null, ex);
	                JOptionPane.showMessageDialog(EquipamientoModifForm.this, "Error al grabar el equipo", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        
		}
	}
	
	public void completarPantalla(Equipamientos equipo){
		try {
		if (equipo.getCodigo() != null) {
			itemSeleccionado=equipo;
            txtCodigo.setText(equipo.getCodigoBarras());
            if (equipo.getDeposito() != null) {
                cmbDeposito.setForeign(equipo.getDeposito());
            }
            if (equipo.getSubfamilia() != null) {
            	 
				EquiposSubFamilias subfamilia = (EquiposSubFamiliasManager.instance().getById(equipo.getSubfamilia()));
				
				cmbFamilia.setForeign(subfamilia.getEqSubfamFamilia());
				if(subfamilia.getEqSubfamFamilia() != null){
					cmbSubfamilia.loadItemsPorFamilia(subfamilia.getEqSubfamFamilia());
					cmbSubfamilia.setForeign(equipo.getSubfamilia());
				}
            }
            if (equipo.getMarca() !=null){
            	cmbMarca.setForeign(equipo.getMarca());
            }
            txtAreaObservaciones.setText(equipo.getObservaciones());
            txtNroSerie.setText(equipo.getNroSerie());
            txtAlto.setText(String.valueOf(equipo.getAlto()));
            txtAncho.setText(String.valueOf(equipo.getAncho()));
            txtLargo.setText(String.valueOf(equipo.getLargo()));
            txtPeso.setText(String.valueOf(equipo.getPeso()));
            txtModelo.setText(equipo.getModelo());
        }
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
