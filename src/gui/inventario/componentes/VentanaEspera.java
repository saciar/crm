package gui.inventario.componentes;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;

import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;

import com.sun.awt.AWTUtilities;

import javax.swing.border.LineBorder;
import javax.swing.UIManager;

public class VentanaEspera extends JDialog {
	
	private static final Color COLOR_ROJO=new Color(0xe40520);
	private static final Color COLOR_BORDO=new Color(0xc41928);
	
	private PanelProgress panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEspera dialog = new VentanaEspera(null,true);
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
	public VentanaEspera(JFrame owner, boolean modal) {
		super(owner,modal);		
		setBackground(Color.WHITE);
		getContentPane().setBackground(Color.WHITE);
		setUndecorated(true);
		setBounds(100, 100, 224, 221);
		
		panel = new PanelProgress();
		panel.putClientProperty(SubstanceBusinessLookAndFeel.COLORIZATION_FACTOR, 1.0);
		panel.setBorder(new MatteBorder(2, 2, 2, 2, COLOR_BORDO));
		panel.setBackground(Color.WHITE);
		getContentPane().add(panel, BorderLayout.CENTER);
		AWTUtilities.setWindowOpaque(this, false);
		
	}
	
	public void startCount(){
		SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>(){
			@Override
		
			protected Boolean doInBackground() throws Exception {
				for(int i=1;i<=100;i++){
					try {
						panel.updateProgress(i);
						panel.repaint();
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return true;
			}

			@Override
			protected void done() {
				VentanaEspera.this.dispose();
			}
			
		};
		worker.execute();
	}

}
