package comerciales2019.salas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import gui.inventario.componentes.JPanelGradient;
import gui.inventario.componentes.listas.SalaListItem;
import gui.inventario.componentes.listas.SalasListModel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;

import comerciales2019.lugar.LugarPanel;
import comerciales2019.lugar.NuevoLugar;
import crm.client.managers.SalaLugarManager;
import crm.client.util.Util;
import crm.libraries.abm.entities.VariacionFecha;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Date;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.ScrollPaneConstants;
import java.awt.Dimension;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class SalasPanel extends JPanel {

	private String codLugarSeleccionado;
	private static JList list;
	private static JScrollPane scrollPane;
	private JTabbedPane tabbedPane;
	private JLabel lblNombreSalaSeleccionada;

	/**
	 * Create the panel.
	 */
	public SalasPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanelGradient panelGradient = new JPanelGradient();
		panelGradient.setColor2(new Color(196, 25, 40));
		panelGradient.setColor1(new Color(236, 31, 47));
		add(panelGradient, BorderLayout.NORTH);
		
		JLabel lblSalas = new JLabel("Salas");
		lblSalas.setForeground(Color.WHITE);
		lblSalas.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 30));
		panelGradient.add(lblSalas);
		
		JPanel panelCentral = new JPanel();
		add(panelCentral, BorderLayout.SOUTH);
		panelCentral.setLayout(new BorderLayout(10, 0));
		
		JPanel panel = new JPanel();
		
		panel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Presione + para agregar una sala");
		lblNewLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 39));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		JPanel panel_todas_salas = new JPanel();
		panel_todas_salas.setBackground(Color.WHITE);
		add(panel_todas_salas, BorderLayout.WEST);
		GridBagLayout gbl_panel_todas_salas = new GridBagLayout();
		gbl_panel_todas_salas.columnWidths = new int[]{10, 177, 0, 0};
		gbl_panel_todas_salas.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
		gbl_panel_todas_salas.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_todas_salas.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_todas_salas.setLayout(gbl_panel_todas_salas);
		
		JLabel lblNewLabel_1 = new JLabel("Salas disponibles");
		lblNewLabel_1.setBackground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel_todas_salas.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.anchor = GridBagConstraints.WEST;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.VERTICAL;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 3;
		panel_todas_salas.add(scrollPane, gbc_scrollPane);
		
		list = new JList(new SalasListModel());
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setValueIsAdjusting(true);
		list.addMouseListener(new ListaMouseListener());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		add(panel_1, BorderLayout.CENTER);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{10, 0, 10, 0};
		gbl_panel_1.rowHeights = new int[]{10, 0, 10, 0, 10, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		panel_1.add(panel_2, gbc_panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		lblNombreSalaSeleccionada = new JLabel("Seleccione una sala");
		lblNombreSalaSeleccionada.setBackground(Color.WHITE);
		lblNombreSalaSeleccionada.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		panel_2.add(lblNombreSalaSeleccionada, BorderLayout.NORTH);
		
		tabbedPane = new JTabbedPane();
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.setBorder(null);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 5);
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 3;
		panel_1.add(tabbedPane, gbc_tabbedPane);
	}

	//completa la lista con el lugar seleccionado en la LugarPanel
	public static void completarListaSalas(String codLugarSeleccionado){
		try {
			SalasListModel model = (SalasListModel)list.getModel();
			Object[] salas = SalaLugarManager.instance().getSalaLugarReportByLugar(codLugarSeleccionado);
			vaciarListaSalas();
			for (int i = 0; i < salas.length; i++) {
				Object[] row = (Object[]) salas[i];
				SalaListItem item = new SalaListItem();
				item.setCodigoSala(row[0].toString());
				item.setNombreSala(row[1].toString());
				model.addSalaItem(item);				
			}
			scrollPane.setViewportView(list);
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
	}
	
	private static void vaciarListaSalas(){
		((SalasListModel)list.getModel()).removeAllSalaItems();
	}
	
	//crea un nuevo Tab para una sala con el nombre dado por nombreSala
	public void createSalaTab(String nombreSala, String codSala){
		int tab = tabbedPane.getTabCount();// cant de tabs, o sea de salas elegidas hasta el momento

		lblNombreSalaSeleccionada.setText("Salas seleccionadas");
		
		SalaParticularPanel pane = new SalaParticularPanel();		

		tabbedPane.insertTab(nombreSala, null, pane, nombreSala, tab); // Inserta una nueva pestaña con la sala seleccionada en la lista
		tabbedPane.setSelectedIndex(tab); // Selecciona la nueva pestaña creada
		TabSalaPanel panelTab = new TabSalaPanel();
		panelTab.setNombreSala(nombreSala);
		panelTab.setCodSala(codSala);
		tabbedPane.setTabComponentAt(tabbedPane.indexOfComponent(pane), panelTab);

	}
	
	//funcion para setear el rango de fechas seleccionables en todas las salas seleccionadas
	public void setRangoFechas(Date desde, Date hasta){
		for(int i =0; i<tabbedPane.getTabCount();i++){
			SalaParticularPanel pane = (SalaParticularPanel)tabbedPane.getComponentAt(i);
			pane.setRangoFechas(desde, hasta);
		}
	}
	
	public JList getListaSalasDisponibles(){
		return list;
	}
	//Clse q maneja el clic en la lista de salas disponibles
	private final class ListaMouseListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent arg0) {
			int selection = list.getSelectedIndex();
			SalasListModel model = (SalasListModel)list.getModel();
			if(selection != -1){
				SalaListItem item = model.getElementAt(selection);
				createSalaTab(item.getNombreSala(), item.getCodigoSala());				
		        model.removeSalaItem(selection);
			}
		}
	}
	
	//clase que dibuja el panel con botones en los tabs
	private class TabSalaPanel extends JPanel {

		private JLabel lblNombreSala;
		private String codSala;
		/**
		 * Create the panel.
		 */
		public TabSalaPanel() {
			setOpaque(false);
			setLayout(new BorderLayout(5, 0));
			
			lblNombreSala = new JLabel("Sala");
			lblNombreSala.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			add(lblNombreSala, BorderLayout.CENTER);
			
			JPanel panel = new JPanel();
			panel.setOpaque(false);
			add(panel, BorderLayout.EAST);
			panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
			
			JButton btnDuplicar = new JButton("");
			btnDuplicar.setToolTipText("Copiar Sala");
			btnDuplicar.addActionListener(new DuplicarActionListener());
			btnDuplicar.setBorderPainted(false);
			btnDuplicar.setOpaque(false);
			btnDuplicar.setIcon(new ImageIcon(TabSalaPanel.class.getResource("/gui/inventario/imagenes/page_copy.png")));
			btnDuplicar.setContentAreaFilled(false);
			panel.add(btnDuplicar);
			
			JButton btnBorrar = new JButton("");
			btnBorrar.addActionListener(new BorrarActionListener());
			btnBorrar.setOpaque(false);
			btnBorrar.setBorderPainted(false);
			btnBorrar.setContentAreaFilled(false);
			btnBorrar.setIcon(new ImageIcon(TabSalaPanel.class.getResource("/gui/inventario/imagenes/cross.png")));
			panel.add(btnBorrar);
			
		}
		
		public void setNombreSala(String nombre){
			lblNombreSala.setText(nombre);
		}
		
		public void setCodSala(String codigo){
			codSala = codigo;
		}

		private final class BorrarActionListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {				
		        if (lblNombreSala.getText() != null) {
		        	int index = tabbedPane.indexOfTab(lblNombreSala.getText());
		        	if(Util.confirm(null, "desea borrar la sala ?"+lblNombreSala.getText())){
		        		tabbedPane.remove(index);		        		
		        		SalasListModel model = (SalasListModel)list.getModel();
		        		SalaListItem item = new SalaListItem();
		        		item.setCodigoSala(codSala);
		        		item.setNombreSala(lblNombreSala.getText());
		        		model.addSalaItem(item);		        		  		
		        		if(tabbedPane.getTabCount()==0){
		        			lblNombreSalaSeleccionada.setText("Seleccione una sala");
		        		}
		        	}
		        }
			}
		}
		
		private final class DuplicarActionListener implements ActionListener {
			public void actionPerformed(ActionEvent arg0) {				
		        if (lblNombreSala.getText() != null) {
		        	int index = tabbedPane.indexOfTab(lblNombreSala.getText());
		        	if(Util.confirm(null, "desea duplicar la sala ?"+lblNombreSala.getText())){
		        		/*tabbedPane.remove(index);		        		
		        		SalasListModel model = (SalasListModel)list.getModel();
		        		SalaListItem item = new SalaListItem();
		        		item.setCodigoSala(codSala);
		        		item.setNombreSala(lblNombreSala.getText());
		        		model.addSalaItem(item);		        		  		
		        		if(tabbedPane.getTabCount()==0){
		        			lblNombreSalaSeleccionada.setText("Seleccione una sala");
		        		}*/
		        		CopiadorSalas dialog = new CopiadorSalas(SalasPanel.this,true);
		        		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    			dialog.setVisible(true);
		        	}
		        	
		        }
			}
		}

	}
}
