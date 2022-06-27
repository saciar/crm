package crm.gui.paneles;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import crm.gui.Main;
import crm.gui.ventanas.BuscadorLocalidades;
import gui.inventario.componentes.ABMPaisesComboBox;
import gui.inventario.componentes.ABMProvinciasComboBox;
import gui.inventario.componentes.ABMPartidosComboBox;
import gui.inventario.componentes.ABMLocalidadesComboBox;
import gui.inventario.componentes.ABMCodigosPostalesComboBox;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelDireccion extends JPanel {

	private JTextField txtDomicilio;
	private JTextField txtNro;
	private JTextField txtPiso;
	private JTextField txtDepto;
	private ABMPaisesComboBox cmbPaises;
	private ABMProvinciasComboBox cmbProvincias;
	private ABMPartidosComboBox cmbPartidos;
	private ABMLocalidadesComboBox cmbLocalidades;
	private ABMCodigosPostalesComboBox cmbCP;
	private JButton btnRefresh;
	
	private JDialog owner;

	public String getCodPais() {
		return cmbPaises.searchForeign();
	}

	public String getCodProvincia() {
		return cmbProvincias.searchForeign();
	}

	public String getCodPartido() {
		return cmbPartidos.searchForeign();
	}

	public String getCodLocalidad() {
		return cmbLocalidades.searchForeign();
	}

	public String getCodCP() {
		return cmbCP.searchForeign();
	}
	
	public String getDomicilio(){
		return txtDomicilio.getText();
	}
	
	public String getDepto(){
		return txtDepto.getText();
	}
	
	public String getPiso(){
		return txtPiso.getText();
	}
	
	public String getNro(){
		return txtNro.getText();
	}
	
	public void setCodPais(String cod) {
		cmbPaises.setForeign(cod);
	}

	public void setCodProvincia(String cod) {
		cmbProvincias.setForeign(cod);
	}

	public void setCodPartido(String cod) {
		cmbPartidos.setForeign(cod);
	}

	public void setCodLocalidad(String cod) {
		cmbLocalidades.setForeign(cod);
	}

	public void setCodCP(String cod) {
		cmbCP.setForeign(cod);
	}
	
	public void setDomicilio(String value){
		txtDomicilio.setText(value);
	}
	
	public void setDepto(String value){
		txtDepto.setText(value);
	}
	
	public void setPiso(String value){
		txtPiso.setText(value);
	}
	
	public void setNro(String value){
		txtNro.setText(value);
	}

	/**
	 * Create the panel.
	 */
	public PanelDireccion(JDialog owner) {
		this.owner = owner;
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 210, 5, 50, 210, 5, 0, 210, 5, 0, 210, 5, 0};
		gbl_panel.rowHeights = new int[]{0, 10, 0, 10, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel label = new JLabel("Domicilio");
		label.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.VERTICAL;
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		txtDomicilio = new JTextField();
		txtDomicilio.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		txtDomicilio.setColumns(10);
		GridBagConstraints gbc_txtDomicilio = new GridBagConstraints();
		gbc_txtDomicilio.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDomicilio.insets = new Insets(0, 0, 5, 5);
		gbc_txtDomicilio.gridx = 1;
		gbc_txtDomicilio.gridy = 0;
		panel.add(txtDomicilio, gbc_txtDomicilio);
		
		JLabel label_1 = new JLabel("Nro");
		label_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 3;
		gbc_label_1.gridy = 0;
		panel.add(label_1, gbc_label_1);
		
		txtNro = new JTextField();
		txtNro.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		txtNro.setColumns(10);
		GridBagConstraints gbc_txtNro = new GridBagConstraints();
		gbc_txtNro.anchor = GridBagConstraints.WEST;
		gbc_txtNro.insets = new Insets(0, 0, 5, 5);
		gbc_txtNro.gridx = 4;
		gbc_txtNro.gridy = 0;
		panel.add(txtNro, gbc_txtNro);
		
		JLabel label_2 = new JLabel("Piso");
		label_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 6;
		gbc_label_2.gridy = 0;
		panel.add(label_2, gbc_label_2);
		
		txtPiso = new JTextField();
		txtPiso.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		txtPiso.setColumns(10);
		GridBagConstraints gbc_txtPiso = new GridBagConstraints();
		gbc_txtPiso.anchor = GridBagConstraints.WEST;
		gbc_txtPiso.insets = new Insets(0, 0, 5, 5);
		gbc_txtPiso.gridx = 7;
		gbc_txtPiso.gridy = 0;
		panel.add(txtPiso, gbc_txtPiso);
		
		JLabel label_3 = new JLabel("Depto");
		label_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.VERTICAL;
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 9;
		gbc_label_3.gridy = 0;
		panel.add(label_3, gbc_label_3);
		
		txtDepto = new JTextField();
		txtDepto.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		txtDepto.setColumns(10);
		GridBagConstraints gbc_txtDepto = new GridBagConstraints();
		gbc_txtDepto.anchor = GridBagConstraints.WEST;
		gbc_txtDepto.insets = new Insets(0, 0, 5, 5);
		gbc_txtDepto.gridx = 10;
		gbc_txtDepto.gridy = 0;
		panel.add(txtDepto, gbc_txtDepto);
		
		JLabel label_4 = new JLabel("Pais");
		label_4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.anchor = GridBagConstraints.WEST;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 0;
		gbc_label_4.gridy = 2;
		panel.add(label_4, gbc_label_4);
		
		cmbPaises = new ABMPaisesComboBox();
		cmbPaises.loadItems();
		cmbPaises.addActionListener(new PaisesActionListener());
		cmbPaises.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_cmbPaises = new GridBagConstraints();
		gbc_cmbPaises.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbPaises.insets = new Insets(0, 0, 5, 5);
		gbc_cmbPaises.gridx = 1;
		gbc_cmbPaises.gridy = 2;
		panel.add(cmbPaises, gbc_cmbPaises);
		
		JLabel label_5 = new JLabel("Provincia");
		label_5.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_label_5 = new GridBagConstraints();
		gbc_label_5.anchor = GridBagConstraints.WEST;
		gbc_label_5.insets = new Insets(0, 0, 5, 5);
		gbc_label_5.gridx = 3;
		gbc_label_5.gridy = 2;
		panel.add(label_5, gbc_label_5);
		
		cmbProvincias = new ABMProvinciasComboBox();
		cmbProvincias.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		cmbProvincias.setEnabled(false);
		cmbProvincias.loadItems();
		cmbProvincias.addActionListener(new ProvinciasActionListener());
		GridBagConstraints gbc_cmbProvincias = new GridBagConstraints();
		gbc_cmbProvincias.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbProvincias.insets = new Insets(0, 0, 5, 5);
		gbc_cmbProvincias.gridx = 4;
		gbc_cmbProvincias.gridy = 2;
		panel.add(cmbProvincias, gbc_cmbProvincias);
		
		JLabel label_6 = new JLabel("Partido");
		label_6.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_label_6 = new GridBagConstraints();
		gbc_label_6.anchor = GridBagConstraints.WEST;
		gbc_label_6.insets = new Insets(0, 0, 5, 5);
		gbc_label_6.gridx = 6;
		gbc_label_6.gridy = 2;
		panel.add(label_6, gbc_label_6);
		
		cmbPartidos = new ABMPartidosComboBox();
		cmbPartidos.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		cmbPartidos.setEnabled(false);
		cmbPartidos.loadItems();
		cmbPartidos.addActionListener(new PartidosActionListener());
		GridBagConstraints gbc_cmbPartidos = new GridBagConstraints();
		gbc_cmbPartidos.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbPartidos.insets = new Insets(0, 0, 5, 5);
		gbc_cmbPartidos.gridx = 7;
		gbc_cmbPartidos.gridy = 2;
		panel.add(cmbPartidos, gbc_cmbPartidos);
		
		btnRefresh = new JButton("Refrescar");
		btnRefresh.addActionListener(new RefreshAction());
		btnRefresh.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		btnRefresh.setIcon(new ImageIcon(PanelDireccion.class.getResource("/gui/inventario/imagenes/arrow_refresh.png")));
		GridBagConstraints gbc_btnRefresh = new GridBagConstraints();
		gbc_btnRefresh.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnRefresh.insets = new Insets(0, 0, 5, 5);
		gbc_btnRefresh.gridx = 10;
		gbc_btnRefresh.gridy = 3;
		panel.add(btnRefresh, gbc_btnRefresh);
		
		JLabel label_7 = new JLabel("Localidad");
		label_7.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_label_7 = new GridBagConstraints();
		gbc_label_7.anchor = GridBagConstraints.WEST;
		gbc_label_7.insets = new Insets(0, 0, 0, 5);
		gbc_label_7.gridx = 0;
		gbc_label_7.gridy = 4;
		panel.add(label_7, gbc_label_7);
		
		cmbLocalidades = new ABMLocalidadesComboBox();
		cmbLocalidades.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		cmbLocalidades.setEnabled(false);
		cmbLocalidades.loadItems();
		cmbLocalidades.addActionListener(new LocalidadesActionListener());
		GridBagConstraints gbc_cmbLocalidades = new GridBagConstraints();
		gbc_cmbLocalidades.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbLocalidades.insets = new Insets(0, 0, 0, 5);
		gbc_cmbLocalidades.gridx = 1;
		gbc_cmbLocalidades.gridy = 4;
		panel.add(cmbLocalidades, gbc_cmbLocalidades);
		
		JLabel label_8 = new JLabel("CP");
		label_8.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_label_8 = new GridBagConstraints();
		gbc_label_8.anchor = GridBagConstraints.WEST;
		gbc_label_8.insets = new Insets(0, 0, 0, 5);
		gbc_label_8.gridx = 3;
		gbc_label_8.gridy = 4;
		panel.add(label_8, gbc_label_8);
		
		cmbCP = new ABMCodigosPostalesComboBox();
		cmbCP.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		cmbCP.setEnabled(false);
		cmbCP.loadItems();
		GridBagConstraints gbc_cmbCP = new GridBagConstraints();
		gbc_cmbCP.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbCP.insets = new Insets(0, 0, 0, 5);
		gbc_cmbCP.gridx = 4;
		gbc_cmbCP.gridy = 4;
		panel.add(cmbCP, gbc_cmbCP);
		
		JButton btnBuscarLocalidad = new JButton("Buscar localidad");
		btnBuscarLocalidad.addActionListener(new BuscarLocalidadesActionListener());
		btnBuscarLocalidad.setIcon(new ImageIcon(PanelDireccion.class.getResource("/gui/inventario/imagenes/magnifier.png")));
		btnBuscarLocalidad.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		GridBagConstraints gbc_btnBuscarLocalidad = new GridBagConstraints();
		gbc_btnBuscarLocalidad.anchor = GridBagConstraints.BELOW_BASELINE_LEADING;
		gbc_btnBuscarLocalidad.insets = new Insets(0, 0, 0, 5);
		gbc_btnBuscarLocalidad.gridx = 7;
		gbc_btnBuscarLocalidad.gridy = 4;
		panel.add(btnBuscarLocalidad, gbc_btnBuscarLocalidad);

	}
	
	private final class BuscarLocalidadesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			BuscadorLocalidades a = new BuscadorLocalidades(owner,true);
			a.setVisible(true);
			if(a.getItemSeleccionado() != null){
				cmbPaises.setEnabled(true);
				cmbPaises.setForeign(a.getItemSeleccionado().getIdPais());
				cmbProvincias.setEnabled(true);
				cmbProvincias.setForeign(a.getItemSeleccionado().getIdProvincia());
				cmbPartidos.setEnabled(true);
				cmbPartidos.setForeign(a.getItemSeleccionado().getIdPartido());
				cmbLocalidades.setEnabled(true);
				cmbLocalidades.setForeign(a.getItemSeleccionado().getIdLocalidad());
			}
		}
	}
	
	private class PaisesActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(cmbPaises.getSelectedIndex()>0){
				cmbProvincias.setEnabled(true);
				cmbProvincias.loadItems(cmbPaises.searchForeign());
			}
			else{
				cmbProvincias.setEnabled(false);
				cmbProvincias.loadItems();
				cmbPartidos.setEnabled(false);				
				cmbPartidos.loadItems();
				cmbLocalidades.setEnabled(false);				
				cmbLocalidades.loadItems();
				cmbCP.setEnabled(false);				
				cmbCP.loadItems();
			}
		}
		
	}
	
	private class ProvinciasActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(cmbProvincias.getSelectedIndex()>0){
				cmbPartidos.setEnabled(true);
				cmbPartidos.loadItems(cmbProvincias.searchForeign());
			}
			else{
				cmbPartidos.setEnabled(false);				
				cmbPartidos.loadItems();
				cmbLocalidades.setEnabled(false);				
				cmbLocalidades.loadItems();
				cmbCP.setEnabled(false);				
				cmbCP.loadItems();
			}
		}
		
	}
	
	private class PartidosActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(cmbPartidos.getSelectedIndex()>0){
				cmbLocalidades.setEnabled(true);
				cmbLocalidades.loadItems(cmbPartidos.searchForeign());
			}
			else{
				cmbLocalidades.setEnabled(false);				
				cmbLocalidades.loadItems();
				cmbCP.setEnabled(false);				
				cmbCP.loadItems();
			}
		}
		
	}
	
	private class LocalidadesActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(cmbLocalidades.getSelectedIndex()>0){
				cmbCP.setEnabled(true);
				cmbCP.loadItems(cmbLocalidades.searchForeign());
			}
			else{
				cmbCP.setEnabled(false);				
				cmbCP.loadItems();
			}
		}
		
	}
	
	private class RefreshAction implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {			
			if(cmbPaises.isEnabled()){
				cmbPaises.loadItems();
			}
			else if(cmbProvincias.isEnabled() && StringUtils.isBlank(cmbPaises.searchForeign())){
				cmbProvincias.loadItems(cmbPaises.searchForeign());
			}
			else if(cmbPartidos.isEnabled() && StringUtils.isBlank(cmbProvincias.searchForeign())){
				cmbPartidos.loadItems(cmbProvincias.searchForeign());
			}
			else if(cmbLocalidades.isEnabled() && StringUtils.isBlank(cmbPartidos.searchForeign())){
				cmbLocalidades.loadItems(cmbPartidos.searchForeign());
			}
			else if(cmbCP.isEnabled() && StringUtils.isBlank(cmbLocalidades.searchForeign())){
				cmbCP.loadItems(cmbLocalidades.searchForeign());
			}
		}
    	
    }

}
