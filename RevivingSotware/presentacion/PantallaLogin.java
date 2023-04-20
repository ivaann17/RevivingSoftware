package presentacion;

import java.awt.Color;
import java.awt.Cursor;

import java.awt.Font;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.border.MatteBorder;

public class PantallaLogin extends JFrame {

	protected static JTextField UsuarioText;
	protected static JTextField Contrase�aText;
	protected static JTextField user;

	private static void placeComponents(JPanel panel) {

		panel.setLayout(null);

	}

	public PantallaLogin() {
		JFrame frmUclm = new JFrame("Demo application");
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

		JPasswordField Contrase�aText = new JPasswordField(20);
		Contrase�aText.setToolTipText("Introduzca su contrase\u00F1a");
		Contrase�aText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Contrase�aText.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));

		Contrase�aText.setActionCommand("");
		Contrase�aText.setVisible(false);
		Contrase�aText.setBounds(79, 191, 434, 42);
		panel.add(Contrase�aText);

		UsuarioText = new JTextField();
		UsuarioText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		UsuarioText.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(180, 180, 180)));
		UsuarioText.setName("");
		UsuarioText.setToolTipText("Introduzca su correo electr\u00F3nico");
		UsuarioText.setBounds(79, 192, 434, 42);
		panel.add(UsuarioText);
		UsuarioText.setColumns(10);

		JButton btnRecuperar = new JButton("He olvidado mi contrase\u00F1a");
		btnRecuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
						"Para conocer la contrase�a de prueba, debe acceder al manual de usuario: \n https://github.com/ivaann17/RevivingSoftware/blob/Master/ManualUsuario.md ",
						"RevivingSoftware", JOptionPane.INFORMATION_MESSAGE);

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

		JLabel userLabel = new JLabel("Iniciar sesi\u00F3n");
		userLabel.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		userLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		userLabel.setBounds(80, 138, 148, 25);
		panel.add(userLabel);

		JLabel passwordLabel = new JLabel("Escribir Contrase\u00F1a");
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

		JButton btnNoAcceder = new JButton("\u00BFNo puede acceder a su cuenta?");
		btnNoAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
						"Para conocer los diferentes tipos de usuarios de prueba, debe acceder al manual de usuario: \n https://github.com/ivaann17/RevivingSoftware/blob/Master/ManualUsuario.md ",
						"RevivingSoftware", JOptionPane.INFORMATION_MESSAGE);
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
				presentacion.PantallaLogin p = new presentacion.PantallaLogin();
				p.setVisible(true);
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
					Contrase�aText.setVisible(true);
					loginButton.setVisible(true);
					userLabel.setVisible(false);
					passwordLabel.setVisible(true);
					btnNoAcceder.setVisible(false);
					btnNewButton.setVisible(true);
					user.setVisible(true);
					user.setText(" Usuario: " + UsuarioText.getText());
					frmUclm.getRootPane().setDefaultButton(loginButton);
					Contrase�aText.requestFocus();

					loginButton.addActionListener((ActionListener) new ActionListener() {
						public void actionPerformed(ActionEvent l) {
							char[] passwordChars = Contrase�aText.getPassword();
							String pass = new String(passwordChars);

							if (pass.length() == 0) {
								JOptionPane.showMessageDialog(null, "Debe introducir su contrase�a.", "ERROR",
										JOptionPane.ERROR_MESSAGE);
							} else if (UsuarioText.getText().equals("admin") && pass.equals("admin")) {
								JOptionPane.showMessageDialog(null, "Bienvenido.", "UCLM",
										JOptionPane.INFORMATION_MESSAGE);

								presentacion.PantallaDireccionCursos p = new presentacion.PantallaDireccionCursos();
								p.setVisible(true);
								setVisible(false);

							}

							else {
								JOptionPane.showMessageDialog(null,
										"El usuario o la contrase�a son incorrectos. Por favor, introduzca correctamente los datos.",
										"ERROR", JOptionPane.ERROR_MESSAGE);
								frmUclm.setVisible(false);
								PantallaLogin p = new PantallaLogin();
								p.setVisible(true);

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

}
