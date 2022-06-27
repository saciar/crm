package inventario2019;

import javax.swing.JPanel;

import crm.libraries.abm.entities.Usuario;
import gui.ControlAplicacion;

import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class PopupUsuarioPanel extends JPanel {

	private Usuario usuario;
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * Create the panel.
	 */
	public PopupUsuarioPanel() {
		
		setBackground(Color.WHITE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{115, 45, 115, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 20, 0, 15, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("");
		
		Image img= new ImageIcon(PopupUsuarioPanel.class.getResource("/gui/imagenes/08_Negro.png")).getImage();
		ImageIcon img2=new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_SMOOTH));

		
		lblNewLabel.setIcon(img2);
		lblNewLabel.setPreferredSize(new Dimension(50, 50));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(ControlAplicacion.getInstance().getUsuario().getApellidoYNombre());
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Cambiar contrase\u00F1a");
		btnNewButton.setIcon(new ImageIcon(PopupUsuarioPanel.class.getResource("/gui/imagenes/key_go.png")));
		btnNewButton.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 4;
		add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cerrar sesion");
		btnNewButton_1.setIcon(new ImageIcon(PopupUsuarioPanel.class.getResource("/gui/imagenes/door_out.png")));
		btnNewButton_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 6;
		add(btnNewButton_1, gbc_btnNewButton_1);

	}

}
