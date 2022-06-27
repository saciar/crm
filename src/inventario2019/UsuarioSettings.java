package inventario2019;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel;

import gui.ControlAplicacion;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.border.MatteBorder;

public class UsuarioSettings extends JDialog {

	private JDialog owner;
	
	private static final Color COLOR_ROJO=new Color(0xe40520);
	private static final Color COLOR_BORDO=new Color(0xc41928);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UsuarioSettings dialog = new UsuarioSettings(null,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Image getImageToByteArray(byte [] data){	      
		try {
			if(data != null){
			ByteArrayInputStream bis = new ByteArrayInputStream(data);
		    BufferedImage bImage2;
			bImage2 = ImageIO.read(bis);
			return bImage2;
			}
			else
				return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Create the dialog.
	 */
	public UsuarioSettings(JDialog owner, boolean modal) {
		super(owner,modal);
		setBackground(Color.WHITE);
		
		this.owner=owner;
		
		setUndecorated(true);
		setBounds(new Rectangle(0, 0, 300, 320));

		//Image img= new ImageIcon(PopupUsuarioPanel.class.getResource("/gui/imagenes/08_Negro.png")).getImage();
		Image img = getImageToByteArray(ControlAplicacion.getInstance().getUsuario().getFoto());
		ImageIcon img2=new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(3, 3, 3, 3, (Color) COLOR_ROJO));
		panel.setBounds(0, 0, 299, 331);
		panel.putClientProperty(SubstanceDustLookAndFeel.COLORIZATION_FACTOR, 1.0);
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{92, 100, 92, 0};
		gbl_panel.rowHeights = new int[]{10, 100, 27, 20, 27, 20, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblFoto = new JLabel("");
		GridBagConstraints gbc_lblFoto = new GridBagConstraints();
		gbc_lblFoto.gridwidth = 3;
		gbc_lblFoto.anchor = GridBagConstraints.NORTH;
		gbc_lblFoto.insets = new Insets(0, 0, 5, 0);
		gbc_lblFoto.gridx = 0;
		gbc_lblFoto.gridy = 1;
		panel.add(lblFoto, gbc_lblFoto);
		lblFoto.setIcon(img2);
		
		JLabel lblNombre = new JLabel(ControlAplicacion.getInstance().getUsuario().getApellidoYNombre());
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.gridwidth = 3;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 0);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 2;
		panel.add(lblNombre, gbc_lblNombre);
		lblNombre.setForeground(Color.BLACK);
		lblNombre.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		
		JButton btnCambiarPass = new JButton("Cambiar contrase\u00F1a");
		btnCambiarPass.setEnabled(false);
		GridBagConstraints gbc_btnCambiarPass = new GridBagConstraints();
		gbc_btnCambiarPass.insets = new Insets(0, 0, 5, 0);
		gbc_btnCambiarPass.gridwidth = 3;
		gbc_btnCambiarPass.gridx = 0;
		gbc_btnCambiarPass.gridy = 4;
		panel.add(btnCambiarPass, gbc_btnCambiarPass);
		btnCambiarPass.setIcon(new ImageIcon(UsuarioSettings.class.getResource("/gui/imagenes/key_go.png")));
		btnCambiarPass.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		
		JButton btnCerrarSesion = new JButton("Cerrar sesion");
		GridBagConstraints gbc_btnCerrarSesion = new GridBagConstraints();
		gbc_btnCerrarSesion.anchor = GridBagConstraints.NORTH;
		gbc_btnCerrarSesion.gridwidth = 3;
		gbc_btnCerrarSesion.gridx = 0;
		gbc_btnCerrarSesion.gridy = 6;
		panel.add(btnCerrarSesion, gbc_btnCerrarSesion);
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//((BienvenidaInventario)owner).getOwner().limpiarPantalla();
				owner.dispose();
				dispose();
			}
		});
		btnCerrarSesion.setIcon(new ImageIcon(UsuarioSettings.class.getResource("/gui/imagenes/door_out.png")));
		btnCerrarSesion.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		
	}

}
