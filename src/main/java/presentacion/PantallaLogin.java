package main.java.presentacion;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;
import javax.swing.event.HyperlinkEvent;

import main.java.negocio.controllers.GestorConsultas;
import main.java.negocio.controllers.GestorLogin;
import main.java.persistencia.Excepciones.TypeUserException;

public class PantallaLogin extends JFrame {

	protected static JTextField usuarioText = new JTextField();
	protected static JTextField user = new JTextField();
	protected static String tipo = "";
	protected static String nom = "";
	protected static String dni = "";
	protected static String tipoLetra = "Tahoma";
	protected JPanel panel;
	protected JButton loginButton;
	protected JButton btnRecuperar;
	protected JButton btnSiguiente;
	protected JButton btnNoAcceder;
	protected JButton btnNewButton;
	protected JPasswordField contrasenaText;
	protected JLabel userLabel;
	protected JLabel passwordLabel;
	protected JLabel lblNewLabel;
	private static final Logger logger = Logger.getLogger(PantallaLogin.class.getName());

	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);

	}

	public PantallaLogin() {
		final JFrame frmUclm = new JFrame("Demo application");
		frmUclm.setIconImage(
				Toolkit.getDefaultToolkit().getImage(PantallaLogin.class.getResource("/IMAGES/descarga.png")));
		frmUclm.setTitle("UCLM");
		frmUclm.setSize(625, 428);
		frmUclm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frmUclm.getContentPane().add(panel);
		placeComponents(panel);

		loginButton = new JButton("Iniciar sesion");
		loginButton.setFocusPainted(false);
		loginButton.addActionListener(event -> frmUclm.dispose());

		loginButton.setVisible(false);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBackground(SystemColor.textHighlight);
		loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginButton.setFont(new Font(tipoLetra, Font.BOLD, 13));
		loginButton.setBounds(406, 316, 168, 48);
		panel.add(loginButton);

		contrasenaText = new JPasswordField(20);
		contrasenaText.setToolTipText("Introduzca su contraseña");
		contrasenaText.setFont(new Font(tipoLetra, Font.PLAIN, 15));
		contrasenaText.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));

		contrasenaText.setActionCommand("");
		contrasenaText.setVisible(false);
		contrasenaText.setBounds(79, 191, 434, 42);
		panel.add(contrasenaText);

		usuarioText.setFont(new Font(tipoLetra, Font.PLAIN, 15));
		usuarioText.setBorder(new MatteBorder(0, 0, 2, 0, new Color(180, 180, 180)));
		usuarioText.setName("");
		usuarioText.setToolTipText("Introduzca su usuario");
		usuarioText.setBounds(79, 192, 434, 42);
		panel.add(usuarioText);
		usuarioText.setColumns(10);

		btnRecuperar = new JButton("He olvidado mi contraseña");
		btnRecuperar.addActionListener(event -> enlaceMan());
		btnRecuperar.setVisible(false);
		btnRecuperar.setHorizontalAlignment(SwingConstants.LEFT);
		btnRecuperar.setForeground(SystemColor.textHighlight);
		btnRecuperar.setFont(new Font(tipoLetra, Font.BOLD, 12));
		btnRecuperar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRecuperar.setBackground(Color.WHITE);
		btnRecuperar.setBorder(null);
		btnRecuperar.setBounds(80, 257, 168, 21);
		panel.add(btnRecuperar);

		frmUclm.setVisible(true);
		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setFocusPainted(false);
		btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSiguiente.setForeground(Color.WHITE);
		btnSiguiente.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnSiguiente.setBackground(SystemColor.textHighlight);
		btnSiguiente.setBounds(406, 316, 168, 48);
		panel.add(btnSiguiente);
		frmUclm.getRootPane().setDefaultButton(btnSiguiente);

		userLabel = new JLabel("Iniciar sesion");
		userLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		userLabel.setFont(new Font(tipoLetra, Font.BOLD, 20));
		userLabel.setBounds(80, 138, 148, 25);
		panel.add(userLabel);

		passwordLabel = new JLabel("Escribir Contraseña");
		passwordLabel.setVisible(false);
		passwordLabel.setFont(new Font(tipoLetra, Font.BOLD, 20));
		passwordLabel.setBounds(80, 129, 215, 42);
		panel.add(passwordLabel);

		user.setFont(new Font(tipoLetra, Font.BOLD, 10));
		user.setBackground(Color.WHITE);
		user.setBorder(null);
		user.setEditable(false);
		user.setVisible(false);
		user.setBounds(79, 109, 252, 19);
		panel.add(user);
		user.setColumns(10);

		btnNoAcceder = new JButton("¿No puede acceder a su cuenta?");
		btnNoAcceder.addActionListener(arg0 -> enlaceMan());
		btnNoAcceder.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNoAcceder.setHorizontalAlignment(SwingConstants.LEFT);
		btnNoAcceder.setForeground(SystemColor.textHighlight);
		btnNoAcceder.setFont(new Font(tipoLetra, Font.BOLD, 12));
		btnNoAcceder.setBorder(null);
		btnNoAcceder.setBackground(Color.WHITE);
		btnNoAcceder.setBounds(79, 258, 216, 21);
		panel.add(btnNoAcceder);

		btnNewButton = new JButton("Volver");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(44, 316, 114, 49);
		panel.add(btnNewButton);
		btnNewButton.setVisible(false);
		btnNewButton.addActionListener(event -> {

			frmUclm.setVisible(false);
			new main.java.presentacion.PantallaLogin();

		});

		btnSiguiente.addActionListener(event -> {

			String usu = usuarioText.getText();

			if (usu.length() == 0) {
				mostrarErrorUsuarioVacio();
			} else {
				mostrarComponentesLogin();
				configurarBotonLogin(frmUclm);
				user.setText(" Usuario: " + usuarioText.getText());
				contrasenaText.requestFocus();

				loginButton.addActionListener(ev ->

				validarCredenciales());
			}

		});

		lblNewLabel = new JLabel("");
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
		JOptionPane.showMessageDialog(null, "Bienvenido " + GestorConsultas.getTipoUsuLog(usu) + ".", "UCLM",
				JOptionPane.INFORMATION_MESSAGE);
		switch (GestorConsultas.getTipoUsuLog(usu)) {
		case "PROFESOR":

			tipo = GestorConsultas.getTipoUsuLog(usu);
			nom = GestorConsultas.getNombreLog(usu) + " " + GestorConsultas.getApellidoLog(usu);
			dni = GestorConsultas.getDNILog(usu);
			main.java.presentacion.PantallaDireccionCursos p = new main.java.presentacion.PantallaDireccionCursos();
			setVisible(false);
			p.setVisible(true);

			break;
		case "ESTUDIANTE":

			tipo = GestorConsultas.getTipoUsuLog(usu);
			nom = GestorConsultas.getNombreLog(usu) + " " + GestorConsultas.getApellidoLog(usu);
			dni = GestorConsultas.getDNILog(usu);
			main.java.presentacion.PantallaEstudiante e = new main.java.presentacion.PantallaEstudiante();
			setVisible(false);
			e.setVisible(true);

			break;
		case "JEFE_GABINETE":

			tipo = GestorConsultas.getTipoUsuLog(usu);
			nom = GestorConsultas.getNombreLog(usu) + " " + GestorConsultas.getApellidoLog(usu);

			main.java.presentacion.PantallaJefeGabineteVicerrectorado j = new main.java.presentacion.PantallaJefeGabineteVicerrectorado();
			setVisible(false);
			j.setVisible(true);

			break;
		case "VICERRECTOR":

			tipo = GestorConsultas.getTipoUsuLog(usu);
			nom = GestorConsultas.getNombreLog(usu) + " " + GestorConsultas.getApellidoLog(usu);
			main.java.presentacion.PantallaEmpleadosVicerrectorado v = new main.java.presentacion.PantallaEmpleadosVicerrectorado();
			setVisible(false);
			v.setVisible(true);
			break;
		default:
			throw new TypeUserException("Error tipo de usuario incorrecto.");
		}

	}

	private void mostrarErrorDatosIncorrectos() {
		JOptionPane.showMessageDialog(null,
				"El usuario o la contraseña son incorrectos. Por favor, introduzca correctamente los datos.", "ERROR",
				JOptionPane.ERROR_MESSAGE);
	}

	protected void reiniciarPantalla() {
		setVisible(false);
		new PantallaLogin();
	}

	private void mostrarErrorUsuarioVacio() {
		JOptionPane.showMessageDialog(null, "Debe introducir su usuario.", "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	private void mostrarComponentesLogin() {
		btnRecuperar.setVisible(true);
		btnSiguiente.setVisible(false);
		usuarioText.setVisible(false);
		contrasenaText.setVisible(true);
		loginButton.setVisible(true);
		userLabel.setVisible(false);
		passwordLabel.setVisible(true);
		btnNoAcceder.setVisible(false);
		btnNewButton.setVisible(true);
		user.setVisible(true);
	}

	private void validarCredenciales() {
		char[] password = contrasenaText.getPassword();
		String contrasena = new String(password);
		Vector<Object> rPass;
		Vector<Object> rUser;

		try {
			rPass = GestorLogin.loginContra(contrasena);
			rUser = GestorLogin.loginUsuario(usuarioText.getText());

			if (!rPass.isEmpty() && !rUser.isEmpty()) {
				cambioPantalla(usuarioText.getText());
			} else {
				mostrarErrorDatosIncorrectos();
				reiniciarPantalla();
			}
		} catch (Exception e1) {
			logger.info("Se ha producido un error al comprobar la validar credenciales: " + e1.getMessage());
		}
	}

	private void configurarBotonLogin(JFrame frame) {
		frame.getRootPane().setDefaultButton(loginButton);
	}
}
