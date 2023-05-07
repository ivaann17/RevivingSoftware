package presentacion;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.border.MatteBorder;
import javax.swing.event.HyperlinkEvent;

import negocio.entities.TipoCurso;
import persistencia.GestorBD;

public class PantallaLogin extends JFrame {

	protected static JTextField UsuarioText;
	protected static JTextField ContrasenaText;
	protected static JTextField user;
	protected static String tipo = "";
	protected static String nom = "";
	protected static String dni = "";

	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);

	}

	public PantallaLogin() {
		final JFrame frmUclm = new JFrame("Demo application");
		frmUclm.setIconImage(
				Toolkit.getDefaultToolkit().getImage(PantallaLogin.class.getResource("/IMAGES/descarga.png")));
		frmUclm.setTitle("UCLM");
		frmUclm.setSize(625, 428);
		frmUclm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frmUclm.getContentPane().add(panel);
		placeComponents(panel);

		JButton loginButton = new JButton("Iniciar sesi\u00F3n");
		loginButton.setFocusPainted(false);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUclm.dispose();
			}

		});

		loginButton.setVisible(false);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(SystemColor.textHighlight);
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		loginButton.setBounds(406, 316, 168, 48);
		panel.add(loginButton);

		JPasswordField ContrasenaText = new JPasswordField(20);
		ContrasenaText.setToolTipText("Introduzca su contrase\u00F1a");
		ContrasenaText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ContrasenaText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));

		ContrasenaText.setActionCommand("");
		ContrasenaText.setVisible(false);
		ContrasenaText.setBounds(79, 191, 434, 42);
		panel.add(ContrasenaText);

		UsuarioText = new JTextField();
		UsuarioText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		UsuarioText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(180, 180, 180)));
		UsuarioText.setName("");
		UsuarioText.setToolTipText("Introduzca su usuario");
		UsuarioText.setBounds(79, 192, 434, 42);
		panel.add(UsuarioText);
		UsuarioText.setColumns(10);

		JButton btnRecuperar = new JButton("He olvidado mi contraseña");
		btnRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enlaceMan();

			}
		});
		btnRecuperar.setVisible(false);
		btnRecuperar.setHorizontalAlignment(SwingConstants.LEFT);
		btnRecuperar.setForeground(SystemColor.textHighlight);
		btnRecuperar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRecuperar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRecuperar.setBackground(Color.WHITE);
		btnRecuperar.setBorder(null);
		btnRecuperar.setBounds(80, 257, 168, 21);
		panel.add(btnRecuperar);

		frmUclm.setVisible(true);
		JButton btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFocusPainted(false);
		btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSiguiente.setForeground(Color.WHITE);
		btnSiguiente.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSiguiente.setBackground(SystemColor.textHighlight);
		btnSiguiente.setBounds(406, 316, 168, 48);
		panel.add(btnSiguiente);
		frmUclm.getRootPane().setDefaultButton(btnSiguiente);

		JLabel userLabel = new JLabel("Iniciar sesion");
		userLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		userLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		userLabel.setBounds(80, 138, 148, 25);
		panel.add(userLabel);

		JLabel passwordLabel = new JLabel("Escribir Contraseï¿½a");
		passwordLabel.setVisible(false);
		passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		passwordLabel.setBounds(80, 129, 215, 42);
		panel.add(passwordLabel);

		user = new JTextField();
		user.setFont(new Font("Tahoma", Font.BOLD, 10));
		user.setBackground(Color.WHITE);
		user.setBorder(null);
		user.setEditable(false);
		user.setVisible(false);
		user.setBounds(79, 109, 252, 19);
		panel.add(user);
		user.setColumns(10);

		JButton btnNoAcceder = new JButton("ï¿½No puede acceder a su cuenta?");
		btnNoAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enlaceMan();

			}
		});
		btnNoAcceder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNoAcceder.setHorizontalAlignment(SwingConstants.LEFT);
		btnNoAcceder.setForeground(SystemColor.textHighlight);
		btnNoAcceder.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNoAcceder.setBorder(null);
		btnNoAcceder.setBackground(Color.WHITE);
		btnNoAcceder.setBounds(79, 258, 216, 21);
		panel.add(btnNoAcceder);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(44, 316, 114, 49);
		panel.add(btnNewButton);
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmUclm.setVisible(false);
				new presentacion.PantallaLogin();

			}
		});

		btnSiguiente.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usu = UsuarioText.getText();
				if (usu.length() == 0) {
					JOptionPane.showMessageDialog(null, "Debe introducir su usuario.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {
					btnRecuperar.setVisible(true);
					btnSiguiente.setVisible(false);
					UsuarioText.setVisible(false);
					ContrasenaText.setVisible(true);
					loginButton.setVisible(true);
					userLabel.setVisible(false);
					passwordLabel.setVisible(true);
					btnNoAcceder.setVisible(false);
					btnNewButton.setVisible(true);
					user.setVisible(true);
					user.setText(" Usuario: " + UsuarioText.getText());
					frmUclm.getRootPane().setDefaultButton(loginButton);
					ContrasenaText.requestFocus();

					loginButton.addActionListener((ActionListener) new ActionListener() {
						public void actionPerformed(ActionEvent l) {
							String sqlUser = "SELECT * FROM usuarios WHERE usuario = ?";
							String sqlPass = "SELECT * FROM usuarios WHERE usuario = ?";
							
							  
							 

						       
						       
						        
							Vector<Object> rUser = null;
							Vector<Object> rPass = null;
							try {
								PreparedStatement psU = GestorBD.mBD.prepareStatement(sqlUser, Statement.RETURN_GENERATED_KEYS);
								 PreparedStatement psP = GestorBD.mBD.prepareStatement(sqlPass, Statement.RETURN_GENERATED_KEYS);
								 
								 	psU.setString(1, UsuarioText.getText());
							        psP.setString(1, ContrasenaText.getText());
								rUser = GestorBD.select(psU);
								rPass = GestorBD.select(psP);
							} catch (Exception e) {
								e.printStackTrace();
								throw new RuntimeException("Error al ejecutar la consulta: " + e.getMessage());
							}

							if (rPass.size() > 0 && rUser.size() > 0) {
								try {
									cambioPantalla(UsuarioText.getText());
								} catch (HeadlessException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"El usuario o la contraseï¿½a son incorrectos. Por favor, introduzca correctamente los datos.",
										"ERROR", JOptionPane.ERROR_MESSAGE);
								frmUclm.setVisible(false);
								PantallaLogin p = new PantallaLogin();
							}
						}
					});

				}
			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PantallaLogin.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		panel.add(lblNewLabel);

	}

	public void enlaceMan() {
		JEditorPane editorPane = new JEditorPane();
		editorPane.setEditable(false);
		editorPane.setContentType("text/html");
		editorPane.setText(
				"<html><body><a href=\"https://github.com/ivaann17/RevivingSoftware/blob/Master/ManualUsuario.md\">Manual de usuario</a></body></html>");
		editorPane.addHyperlinkListener(e -> {
			if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
				try {
					Desktop.getDesktop().browse(e.getURL().toURI());
				} catch (IOException | URISyntaxException ex) {
					ex.printStackTrace();
				}
			}
		});

		JOptionPane.showMessageDialog(null, editorPane, "RevivingSoftware", JOptionPane.INFORMATION_MESSAGE);

	}

	public void cambioPantalla(String usu) throws HeadlessException, Exception {
		JOptionPane.showMessageDialog(null, "Bienvenido " + (tipoUsu(usu)) + ".", "UCLM",
				JOptionPane.INFORMATION_MESSAGE);
		switch (tipoUsu(usu).toUpperCase()) {
		case "PROFESOR":

			tipo = tipoUsu(usu).toUpperCase().toString();
			nom = nombreUsu(usu).toUpperCase().toString();
			dni = dniUsu(usu).toString();
			presentacion.PantallaDireccionCursos p = new presentacion.PantallaDireccionCursos();
			setVisible(false);
			p.setVisible(true);

			break;
		case "ESTUDIANTE":

			tipo = tipoUsu(usu).toUpperCase().toString();
			nom = nombreUsu(usu).toUpperCase().toString();
			presentacion.PantallaEstudiante e = new presentacion.PantallaEstudiante();
			setVisible(false);
			e.setVisible(true);

			break;
		case "JEFE_GABINETE":

			tipo = tipoUsu(usu).toUpperCase().toString();
			nom = nombreUsu(usu).toUpperCase().toString();
			

			presentacion.PantallaJefeGabineteVicerrectorado j = new presentacion.PantallaJefeGabineteVicerrectorado();
			setVisible(false);
			j.setVisible(true);

			break;
		case "VICERRECTOR":

			tipo = tipoUsu(usu).toUpperCase().toString();
			nom = nombreUsu(usu).toUpperCase().toString();
			presentacion.PantallaEmpleadosVicerrectorado v = new presentacion.PantallaEmpleadosVicerrectorado();
			setVisible(false);
			v.setVisible(true);
			break;
		}
	}

	public String tipoUsu(String usu) throws Exception {
		String sql ="SELECT tipo FROM usuarios WHERE usuario = ?";
		PreparedStatement psT = GestorBD.mBD.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		 
		 	psT.setString(1, usu);
	        
		Vector<Object> tipo = GestorBD.select(psT);
		if (!tipo.isEmpty()) {
			String tipoUsuario = tipo.get(0).toString().replaceAll("[\\[\\]]", "").trim().toUpperCase();
			switch (tipoUsuario) {
			case "PROFESOR":
				return "Profesor";
			case "ESTUDIANTE":
				return "Estudiante";
			case "JEFE_GABINETE":
				return "Jefe_Gabinete";
			case "VICERRECTOR":
				return "Vicerrector";
			}
		}
		return "";

	}

	public String nombreUsu(String usu) throws Exception {
		
		String sqlNom= "SELECT nombre,apellido FROM usuarios WHERE usuario = ?";
		
		PreparedStatement psN = GestorBD.mBD.prepareStatement(sqlNom, Statement.RETURN_GENERATED_KEYS);
		 
	 	psN.setString(1, usu);
		Vector<Object> nombre = GestorBD.select(psN);
		String nom = null;
		if (!nombre.isEmpty()) {
			
			nom = nombre.get(0).toString().replaceAll("[\\[\\],]","").trim().toUpperCase();
		}
		return nom;

	}
	public String dniUsu(String usu) throws Exception {
		
		String sqlDNI= "SELECT DNI FROM usuarios WHERE usuario = ?";
		
		PreparedStatement psD = GestorBD.mBD.prepareStatement(sqlDNI, Statement.RETURN_GENERATED_KEYS);
		 
	 	psD.setString(1, usu);
		Vector<Object> dni = GestorBD.select(psD);
		
		String dniUsu = null;
		if (!dni.isEmpty()) {
			dniUsu = dni.get(0).toString().replaceAll("[\\[\\]]", "").trim().toUpperCase();
		
		}
		
		return dniUsu;
		

	}

}
