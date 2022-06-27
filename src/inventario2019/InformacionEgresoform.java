package inventario2019;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crm.client.managers.OperadorManager;
import crm.client.managers.TransporteManager;
import crm.client.managers.UsuarioManager;
import crm.libraries.abm.entities.Operador;
import crm.libraries.abm.entities.Transporte;
import crm.libraries.abm.entities.Usuario;
import gui.inventario.componentes.tablas.SalidasTableItem;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class InformacionEgresoform extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblResponsableEgreso;
	private JLabel lblTRansporteEgreso;
	private JLabel lblChoferEgreso;
	private JLabel lblFechaEgreso;
	private JLabel lblCodigoEgreso;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InformacionEgresoform dialog = new InformacionEgresoform(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void CompletarPantalla(SalidasTableItem item){
		if(item!=null){
			
			try {
				lblCodigoEgreso.setText(item.getCodigoEgresoEquipo());
				Operador o = OperadorManager.instance().getOperadorById(item.getCodigoChoferEgreso());
				lblChoferEgreso.setText(o.getApellidoYNombre());
				Transporte t = TransporteManager.instance().getById(item.getCodigoTransporteEgreso());
				lblTRansporteEgreso.setText(t.getDescripcion());
				lblFechaEgreso.setText(item.getFechaEgreso());
				Usuario u = UsuarioManager.instance().getUsuarioById(item.getCodigoUsuarioEgreso());
				lblResponsableEgreso.setText(u.getApellidoYNombre());
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * Create the dialog.
	 */
	public InformacionEgresoform(JDialog owner, boolean modal) {
		super(owner, modal);
		setTitle("Informacion del Egreso");
		setIconImage(Toolkit.getDefaultToolkit().getImage(InformacionEgresoform.class.getResource("/gui/inventario/imagenes/logo50px.png")));
		setBounds(100, 100, 591, 404);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{10, 0, 5, 0, 10, 0};
		gbl_contentPanel.rowHeights = new int[]{10, 0, 0, 0, 15, 0, 15, 0, 15, 0, 15, 0, 10, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblInformacionDelEgreso = new JLabel("Informacion del egreso");
			lblInformacionDelEgreso.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 19));
			GridBagConstraints gbc_lblInformacionDelEgreso = new GridBagConstraints();
			gbc_lblInformacionDelEgreso.gridwidth = 3;
			gbc_lblInformacionDelEgreso.insets = new Insets(0, 0, 5, 5);
			gbc_lblInformacionDelEgreso.gridx = 1;
			gbc_lblInformacionDelEgreso.gridy = 1;
			contentPanel.add(lblInformacionDelEgreso, gbc_lblInformacionDelEgreso);
		}
		{
			JSeparator separator = new JSeparator();
			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.fill = GridBagConstraints.BOTH;
			gbc_separator.gridwidth = 3;
			gbc_separator.insets = new Insets(0, 0, 5, 5);
			gbc_separator.gridx = 1;
			gbc_separator.gridy = 2;
			contentPanel.add(separator, gbc_separator);
		}
		{
			JLabel lblNewLabel = new JLabel("Codigo Egreso");
			lblNewLabel.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
			GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
			gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
			gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel.gridx = 1;
			gbc_lblNewLabel.gridy = 3;
			contentPanel.add(lblNewLabel, gbc_lblNewLabel);
		}
		{
			lblCodigoEgreso = new JLabel("");
			lblCodigoEgreso.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			GridBagConstraints gbc_lblCodigoEgreso = new GridBagConstraints();
			gbc_lblCodigoEgreso.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblCodigoEgreso.insets = new Insets(0, 0, 5, 5);
			gbc_lblCodigoEgreso.gridx = 3;
			gbc_lblCodigoEgreso.gridy = 3;
			contentPanel.add(lblCodigoEgreso, gbc_lblCodigoEgreso);
		}
		{
			JLabel lblFecha = new JLabel("Fecha");
			lblFecha.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
			GridBagConstraints gbc_lblFecha = new GridBagConstraints();
			gbc_lblFecha.anchor = GridBagConstraints.WEST;
			gbc_lblFecha.insets = new Insets(0, 0, 5, 5);
			gbc_lblFecha.gridx = 1;
			gbc_lblFecha.gridy = 5;
			contentPanel.add(lblFecha, gbc_lblFecha);
		}
		{
			lblFechaEgreso = new JLabel("");
			lblFechaEgreso.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			GridBagConstraints gbc_lblFechaEgreso = new GridBagConstraints();
			gbc_lblFechaEgreso.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblFechaEgreso.insets = new Insets(0, 0, 5, 5);
			gbc_lblFechaEgreso.gridx = 3;
			gbc_lblFechaEgreso.gridy = 5;
			contentPanel.add(lblFechaEgreso, gbc_lblFechaEgreso);
		}
		{
			JLabel lblChofer = new JLabel("Chofer");
			lblChofer.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
			GridBagConstraints gbc_lblChofer = new GridBagConstraints();
			gbc_lblChofer.anchor = GridBagConstraints.WEST;
			gbc_lblChofer.insets = new Insets(0, 0, 5, 5);
			gbc_lblChofer.gridx = 1;
			gbc_lblChofer.gridy = 7;
			contentPanel.add(lblChofer, gbc_lblChofer);
		}
		{
			lblChoferEgreso = new JLabel("");
			lblChoferEgreso.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			GridBagConstraints gbc_lblChoferEgreso = new GridBagConstraints();
			gbc_lblChoferEgreso.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblChoferEgreso.insets = new Insets(0, 0, 5, 5);
			gbc_lblChoferEgreso.gridx = 3;
			gbc_lblChoferEgreso.gridy = 7;
			contentPanel.add(lblChoferEgreso, gbc_lblChoferEgreso);
		}
		{
			JLabel lblVehiculo = new JLabel("Vehiculo");
			lblVehiculo.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
			GridBagConstraints gbc_lblVehiculo = new GridBagConstraints();
			gbc_lblVehiculo.anchor = GridBagConstraints.WEST;
			gbc_lblVehiculo.insets = new Insets(0, 0, 5, 5);
			gbc_lblVehiculo.gridx = 1;
			gbc_lblVehiculo.gridy = 9;
			contentPanel.add(lblVehiculo, gbc_lblVehiculo);
		}
		{
			lblTRansporteEgreso = new JLabel("");
			lblTRansporteEgreso.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			GridBagConstraints gbc_lblTRansporteEgreso = new GridBagConstraints();
			gbc_lblTRansporteEgreso.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblTRansporteEgreso.insets = new Insets(0, 0, 5, 5);
			gbc_lblTRansporteEgreso.gridx = 3;
			gbc_lblTRansporteEgreso.gridy = 9;
			contentPanel.add(lblTRansporteEgreso, gbc_lblTRansporteEgreso);
		}
		{
			JLabel lblResponsable = new JLabel("Responsable");
			lblResponsable.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 14));
			GridBagConstraints gbc_lblResponsable = new GridBagConstraints();
			gbc_lblResponsable.anchor = GridBagConstraints.WEST;
			gbc_lblResponsable.insets = new Insets(0, 0, 5, 5);
			gbc_lblResponsable.gridx = 1;
			gbc_lblResponsable.gridy = 11;
			contentPanel.add(lblResponsable, gbc_lblResponsable);
		}
		{
			lblResponsableEgreso = new JLabel("");
			lblResponsableEgreso.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
			GridBagConstraints gbc_lblResponsableEgreso = new GridBagConstraints();
			gbc_lblResponsableEgreso.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblResponsableEgreso.insets = new Insets(0, 0, 5, 5);
			gbc_lblResponsableEgreso.gridx = 3;
			gbc_lblResponsableEgreso.gridy = 11;
			contentPanel.add(lblResponsableEgreso, gbc_lblResponsableEgreso);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setIcon(new ImageIcon(InformacionEgresoform.class.getResource("/gui/inventario/imagenes/tick.png")));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
