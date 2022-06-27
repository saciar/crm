package comerciales2019.salas;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TabSalaPanel extends JPanel {

	private JLabel lblNombreSala;
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

	private final class BorrarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}

}
