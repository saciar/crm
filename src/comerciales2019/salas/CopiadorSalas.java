package comerciales2019.salas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import principal.MyJButton;
import principal.SubtituloLabel;
import principal.TituloLabel;
import javax.swing.JList;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import crm.client.managers.SalaLugarManager;
import gui.inventario.componentes.listas.SalaListItem;
import gui.inventario.componentes.listas.SalasListModel;

import java.awt.Toolkit;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CopiadorSalas extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private SalasPanel owner;
	private JScrollPane scrollPane;
	private JList list;
	private String salaOrigen;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CopiadorSalas dialog = new CopiadorSalas(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CopiadorSalas(SalasPanel sala, boolean modal) {
		setModal(modal);
		owner = sala;
		setTitle("Copiar sala");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CopiadorSalas.class.getResource("/gui/inventario/imagenes/logo50px.png")));
		setBounds(100, 100, 413, 578);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.anchor = GridBagConstraints.WEST;
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.VERTICAL;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 1;
			contentPanel.add(panel, gbc_panel);
			{
				TituloLabel tlblCopiarSalas = new TituloLabel();
				tlblCopiarSalas.setText("Duplicar sala");
				panel.add(tlblCopiarSalas);
			}
		}
		{
			JPanel panel = new JPanel();
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.insets = new Insets(0, 0, 5, 5);
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 1;
			gbc_panel.gridy = 3;
			contentPanel.add(panel, gbc_panel);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			{
				SubtituloLabel sbtlblSubtitulo = new SubtituloLabel();
				sbtlblSubtitulo.setText("Sala destino");
				GridBagConstraints gbc_sbtlblSubtitulo = new GridBagConstraints();
				gbc_sbtlblSubtitulo.insets = new Insets(0, 0, 5, 5);
				gbc_sbtlblSubtitulo.gridx = 1;
				gbc_sbtlblSubtitulo.gridy = 1;
				panel.add(sbtlblSubtitulo, gbc_sbtlblSubtitulo);
			}
			{
				scrollPane = new JScrollPane();
				GridBagConstraints gbc_scrollPane = new GridBagConstraints();
				gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
				gbc_scrollPane.fill = GridBagConstraints.BOTH;
				gbc_scrollPane.gridx = 1;
				gbc_scrollPane.gridy = 3;
				panel.add(scrollPane, gbc_scrollPane);
				{
					list = new JList();
					//scrollPane.setViewportView(list);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Copiar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int selection = list.getSelectedIndex();
						SalasListModel model = (SalasListModel)list.getModel();
						if(selection != -1){
							SalaListItem item = model.getElementAt(selection);
							owner.createSalaTab(item.getNombreSala(), item.getCodigoSala());				
					        model.removeSalaItem(selection);
						}
						dispose();
					}
				});
				buttonPane.add(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		completarListaSalasDisponibles();
	}
	
	private void completarListaSalasDisponibles(){
		SalasListModel model = (SalasListModel)owner.getListaSalasDisponibles().getModel();
		list.setModel(model);
		scrollPane.setViewportView(list);
	}

}
