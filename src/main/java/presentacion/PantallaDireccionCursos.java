package main.java.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.negocio.controllers.GestorConsultas;
import main.java.negocio.entities.EstadoCurso;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

import main.java.persistencia.Excepciones.SelectException;
import main.java.persistencia.GestorBD;

import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Logger;
import java.awt.Cursor;
import javax.swing.WindowConstants;

public class PantallaDireccionCursos extends JFrame {

	private JPanel contentPane;
	private static String tipoLetra = "Tahoma";
	private static String error = "Se ha producido un error: ";
	private static final Logger logger = Logger.getLogger(PantallaDireccionCursos.class.getName());

	public PantallaDireccionCursos() {

		setTitle("UCLM");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaDireccionCursos.class.getResource("/IMAGES/descarga.png")));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnMostrarHistorial = new JButton("Historial \r\nde \r\npropuestas");
		btnMostrarHistorial.addActionListener(event -> {

			try {
				main.java.presentacion.PantallaPropuestasRealizadas p = new main.java.presentacion.PantallaPropuestasRealizadas();
				String nombre = main.java.presentacion.PantallaEstudiante.createNombreUsuTextField().getText()
						.replaceAll("\\s.*", "");

				GestorConsultas.listarHistorial(p.modelo, dni(nombre));
				setVisible(false);
				p.setVisible(true);
			} catch (Exception e1) {
				logger.info(error + e1.getMessage());
			}

		});
		btnMostrarHistorial.setFocusPainted(false);
		btnMostrarHistorial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrarHistorial.setForeground(Color.WHITE);
		btnMostrarHistorial.setBackground(SystemColor.textHighlight);
		btnMostrarHistorial.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnMostrarHistorial.setBounds(29, 266, 206, 110);
		contentPane.add(btnMostrarHistorial);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(PantallaDireccionCursos.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(10, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JButton btnRealizarPropuesta = new JButton("Realizar \r\npropuesta");
		btnRealizarPropuesta.addActionListener(event -> {

			PantallaRealizarPropuestas p = new PantallaRealizarPropuestas();
			setVisible(false);
			p.setVisible(true);

		});
		btnRealizarPropuesta.setFocusPainted(false);
		btnRealizarPropuesta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRealizarPropuesta.setForeground(Color.WHITE);
		btnRealizarPropuesta.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnRealizarPropuesta.setBackground(SystemColor.textHighlight);
		btnRealizarPropuesta.setBounds(29, 119, 206, 110);
		contentPane.add(btnRealizarPropuesta);

		contentPane.add(main.java.presentacion.PantallaEstudiante.createNombreUsuTextField());
		contentPane.add(main.java.presentacion.PantallaEstudiante.createTipoUsuarioTextField());

		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2.setIcon(new ImageIcon(PantallaDireccionCursos.class.getResource("/IMAGES/images2.jpg")));
		lblNewLabel2.setBounds(549, 55, 142, 143);
		contentPane.add(lblNewLabel2);
		JButton cs = new JButton("Cerrar sesion");
		cs.setIcon(new ImageIcon(PantallaDireccionCursos.class.getResource("/IMAGES/cerrar-sesion .png")));
		contentPane.add(PantallaEmpleadosVicerrectorado.crearBotonCerrarSesion(cs));
		cs.addActionListener(event -> {

			setVisible(false);
			new PantallaLogin();

		});
		
		
		JButton btnRechazados = new JButton("Propuestas \r\nrechazadas");
		btnRechazados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRechazados.addActionListener(arg0 -> {

			try {
				main.java.presentacion.PantallaPropuestasRechazadas p = new main.java.presentacion.PantallaPropuestasRechazadas();

				GestorConsultas.listarCursosPorEstado(p.modelo, EstadoCurso.PROPUESTA_RECHAZADA);
				setVisible(false);
				p.setVisible(true);
			} catch (Exception e1) {
				logger.info(error + e1.getMessage());
			}

		});
		btnRechazados.setForeground(Color.WHITE);
		btnRechazados.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnRechazados.setFocusPainted(false);
		btnRechazados.setBackground(SystemColor.textHighlight);
		btnRechazados.setBounds(287, 266, 206, 110);
		contentPane.add(btnRechazados);

		JButton btnValidados = new JButton("Empezar matriculacion");
		btnValidados.setForeground(Color.WHITE);
		btnValidados.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnValidados.setFocusPainted(false);
		btnValidados.setBackground(SystemColor.textHighlight);
		btnValidados.setBounds(287, 119, 206, 110);
		contentPane.add(btnValidados);
		btnValidados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnValidados.addActionListener(arg0 -> {

			try {
				main.java.presentacion.PantallaEmpezarMatriculacion p = new main.java.presentacion.PantallaEmpezarMatriculacion();

				GestorConsultas.listarCursosPorEstado(p.modelo, EstadoCurso.VALIDADO);
				setVisible(false);
				p.setVisible(true);
			} catch (Exception e1) {
				logger.info(error + e1.getMessage());
			}

		});

		JButton btnMostrarResueltos = new JButton("Propuestas resueltas");
		btnMostrarResueltos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrarResueltos.setForeground(Color.WHITE);
		btnMostrarResueltos.setFont(new Font(tipoLetra, Font.BOLD, 15));
		btnMostrarResueltos.setBackground(SystemColor.textHighlight);
		btnMostrarResueltos.setBounds(103, 146, 228, 99);
	}

	public static String dni(String usu) throws SQLException, SelectException  {
		String sqlDNI = "SELECT DNI FROM usuarios WHERE UPPER(nombre) = UPPER(?)";
		try (PreparedStatement psD = GestorBD.mBD.prepareStatement(sqlDNI, Statement.RETURN_GENERATED_KEYS)) {
			psD.setString(1, usu);
			Vector<Object> dni = GestorBD.select(psD);
			String dniUsu = null;
			if (!dni.isEmpty()) {
				dniUsu = dni.get(0).toString().replaceAll("[\\[\\]]", "").trim().toUpperCase();
			}
			return dniUsu;
		}
	}

}
