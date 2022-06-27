package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel;

import comerciales2019.BienvenidaComerciales;
import crm.client.managers.UsuarioManager;
import crm.libraries.abm.entities.Usuario;
import crm.services.sei.PerfilManagerSEI;
import gui.gastos.GastosPrincipal;
import gui.inventario.componentes.JPanelGradient;
import gui.inventario.componentes.VentanaEspera;
import inventario2019.BienvenidaInventario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {	

	private static final Color COLOR_ROJO=new Color(0xe40520);
	private static final Color COLOR_BORDO=new Color(0xc41928);
	
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtPass;
	
	private UsuarioManager usuarioManager;
	
	private int x,y;
	
	private ControlAplicacion control;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setUndecorated(true);	
		control = ControlAplicacion.getInstance();
		usuarioManager =UsuarioManager.instance();
		try {
			UIManager.setLookAndFeel(new SubstanceDustLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initComponents();
		setLocationRelativeTo(null);
	}
	
	private void initComponents(){
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 394);
		contentPane = new JPanel();
		contentPane.putClientProperty(SubstanceDustLookAndFeel.COLORIZATION_FACTOR, 1.0);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelSuperior = new JPanel();
		
		panelSuperior.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent evt) {
				LoginForm.this.setLocation(LoginForm.this.getLocation().x + evt.getX() - x, LoginForm.this.getLocation().y + evt.getY() - y);
			}
		});
		panelSuperior.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent evt) {
				x = evt.getX();
				y = evt.getY();
			}
		});
		panelSuperior.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(153, 0, 0)));
		panelSuperior.putClientProperty(SubstanceDustLookAndFeel.COLORIZATION_FACTOR, 1.0);
		panelSuperior.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) panelSuperior.getLayout();
		flowLayout.setVgap(30);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginForm.class.getResource("/gui/inventario/imagenes/logo_thinking.png")));
		panelSuperior.add(lblNewLabel);
		
		JPanelGradient panelCentral = new JPanelGradient();
		panelCentral.setBorder(null);
		panelCentral.setColor1(COLOR_ROJO);
		panelCentral.setColor2(COLOR_BORDO);
		contentPane.add(panelCentral, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentral = new GridBagLayout();
		gbl_panelCentral.columnWidths = new int[]{20, 0, 0, 220, 47, 0};
		gbl_panelCentral.rowHeights = new int[]{20, 0, 19, 0, 20, 0, 0};
		gbl_panelCentral.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelCentral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelCentral.setLayout(gbl_panelCentral);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginForm.class.getResource("/gui/imagenes/user.png")));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panelCentral.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Usuario");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 1;
		panelCentral.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
		gbc_txtUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsuario.gridx = 3;
		gbc_txtUsuario.gridy = 1;
		panelCentral.add(txtUsuario, gbc_txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(LoginForm.class.getResource("/gui/imagenes/key.png")));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 3;
		panelCentral.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password");
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 3;
		panelCentral.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		txtPass = new JPasswordField();
		txtPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
					ingresar2();
				}
			}
		});
		txtPass.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		GridBagConstraints gbc_txtPass = new GridBagConstraints();
		gbc_txtPass.insets = new Insets(0, 0, 5, 5);
		gbc_txtPass.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPass.gridx = 3;
		gbc_txtPass.gridy = 3;
		panelCentral.add(txtPass, gbc_txtPass);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new IngresarActionListener());
		btnIngresar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnIngresar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnIngresar.setIcon(new ImageIcon(LoginForm.class.getResource("/gui/imagenes/door_in.png")));
		btnIngresar.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		GridBagConstraints gbc_btnIngresar = new GridBagConstraints();
		gbc_btnIngresar.gridwidth = 5;
		gbc_btnIngresar.gridx = 0;
		gbc_btnIngresar.gridy = 5;
		panelCentral.add(btnIngresar, gbc_btnIngresar);
		
		JPanelGradient panelInferior = new JPanelGradient();
		panelInferior.setColor1(COLOR_BORDO);
		panelInferior.setColor2(new Color(153, 0, 0));
		panelInferior.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) panelInferior.getLayout();
		flowLayout_1.setHgap(15);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		
		JButton btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
		btnNewButton_1.setIcon(new ImageIcon(LoginForm.class.getResource("/gui/imagenes/cross.png")));
		panelInferior.add(btnNewButton_1);
	}
	
	private void ingresar() {
        try {     

            Usuario usuario = UsuarioManager.instance().getUsuarioById2(txtUsuario.getText(), txtPass.getText());

            if (usuario != null) {
                control.setUsuario(usuario);
                if (usuario.getPerfil().equals(PerfilManagerSEI.PERFIL_DEPOSITOS)) {
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                        	BienvenidaInventario dialog = new BienvenidaInventario(LoginForm.this,true);
                			dialog.setLocationRelativeTo(LoginForm.this);
                			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                			limpiarPantalla();
                			dialog.setVisible(true);                			
                        }
                    });
                } else if (usuario.getPerfil().equals(PerfilManagerSEI.PERFIL_GASTOS)) {
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            GastosPrincipal dialog = new GastosPrincipal(new javax.swing.JFrame(), true);
                            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                                public void windowClosing(java.awt.event.WindowEvent e) {
                                    System.exit(0);
                                }
                            });
                            dialog.setVisible(true);
                        }
                    });
                } else if (usuario.getPerfil().equals(PerfilManagerSEI.PERFIL_VENDEDOR)) {
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                        	BienvenidaComerciales dialog = new BienvenidaComerciales(LoginForm.this,true);
                			dialog.setLocationRelativeTo(LoginForm.this);
                			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                			limpiarPantalla();
                			dialog.setVisible(true);                			
                        }
                    });
                }
                
                
            } else {
                JOptionPane.showMessageDialog(this, "No existe usuario con esas credenciales. Intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RemoteException e) {
        	e.printStackTrace();
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	private void ingresar2() {	
		VentanaEspera waitDialog = new VentanaEspera(LoginForm.this, false);
		waitDialog.setLocationRelativeTo(LoginForm.this);
		waitDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		waitDialog.setVisible(true);
		SwingWorker<Boolean,Void> worker = new SwingWorker<Boolean,Void>(){
			@Override
		
			protected Boolean doInBackground() throws Exception {
				waitDialog.startCount();
				Usuario u = tarea();
				if(u != null)
					return true;
				else return false;
			}

			@Override
			protected void done() {
				try {
					waitDialog.dispose();
					if(get()){
						if (control.getUsuario().getPerfil().equals(PerfilManagerSEI.PERFIL_DEPOSITOS)) {

							BienvenidaInventario dialog = new BienvenidaInventario(LoginForm.this,true);
							dialog.setLocationRelativeTo(LoginForm.this);
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							limpiarPantalla();
							dialog.setVisible(true);                			
						}
						else if (control.getUsuario().getPerfil().equals(PerfilManagerSEI.PERFIL_GASTOS)) {

							GastosPrincipal dialog = new GastosPrincipal(new javax.swing.JFrame(), true);
							dialog.addWindowListener(new java.awt.event.WindowAdapter() {
								public void windowClosing(java.awt.event.WindowEvent e) {
									System.exit(0);
								}
							});
							dialog.setVisible(true);

						}
						else if (control.getUsuario().getPerfil().equals(PerfilManagerSEI.PERFIL_VENDEDOR)) {
		                    java.awt.EventQueue.invokeLater(new Runnable() {
		                        public void run() {
		                        	
		                        	BienvenidaComerciales dialog = new BienvenidaComerciales(LoginForm.this,true);
		                			dialog.setLocationRelativeTo(LoginForm.this);
		                			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		                			limpiarPantalla();
		                			dialog.setVisible(true);
		                        }
		                    });
		                }

					}else {
						JOptionPane.showMessageDialog(LoginForm.this, "No existe usuario con esas credenciales. Intente nuevamente", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (HeadlessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		};
		worker.execute();
    }
	private Usuario tarea(){
		try {				
			Usuario usuario = usuarioManager.getUsuarioById2(txtUsuario.getText(), txtPass.getText());
			if (usuario != null) {
				control.setUsuario(usuario);
				return usuario;
			}
			else return null;	
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(LoginForm.this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	

	private final class IngresarActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ingresar();
		}
	}
	
	public void limpiarPantalla(){
		txtUsuario.setText(null);
		txtPass.setText(null);
	}

}
