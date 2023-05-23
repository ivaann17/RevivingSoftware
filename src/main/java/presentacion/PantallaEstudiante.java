package main.java.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

import main.java.negocio.controllers.GestorConsultas;
import main.java.negocio.entities.EstadoCurso;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class PantallaEstudiante extends JFrame {

	private JPanel contentPane;
	private static String tipoLetra = "Tahoma";
	private static final Logger logger = Logger.getLogger(PantallaEstudiante.class.getName());

	public PantallaEstudiante() {
		setTitle("UCLM");
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(PantallaEstudiante.class.getResource("/IMAGES/descarga.png")));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnMisCursos = new JButton("Mis cursos\r\n");
		btnMisCursos.addActionListener(event -> {

			try {
				main.java.presentacion.PantallaMisCursos p = new main.java.presentacion.PantallaMisCursos();
				GestorConsultas.listarCursosMatriculados(p.modelo, main.java.presentacion.PantallaLogin.dni);
				setVisible(false);
				p.setVisible(true);
			} catch (Exception e1) {
				logger.info("Error" + e1.getMessage());
			}

		});
		btnMisCursos.setFocusPainted(false);
		btnMisCursos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMisCursos.setForeground(Color.WHITE);
		btnMisCursos.setBackground(SystemColor.textHighlight);
		btnMisCursos.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnMisCursos.setBounds(45, 253, 228, 76);
		contentPane.add(btnMisCursos);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel
				.setIcon(new ImageIcon(PantallaEstudiante.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(10, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JButton btnRealizarMatriculacion = new JButton("Realizar matriculacion");
		btnRealizarMatriculacion.addActionListener(event -> {

			try {
				main.java.presentacion.PantallaMatriculacion p = new main.java.presentacion.PantallaMatriculacion();
				GestorConsultas.listarCursosPorEstado(p.modelo, EstadoCurso.EN_MATRICULACION);
				setVisible(false);
				p.setVisible(true);
			} catch (Exception e1) {
				logger.info("Error" + e1.getMessage());
			}

		});
		btnRealizarMatriculacion.setFocusPainted(false);
		btnRealizarMatriculacion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRealizarMatriculacion.setForeground(Color.WHITE);
		btnRealizarMatriculacion.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnRealizarMatriculacion.setBackground(SystemColor.textHighlight);
		btnRealizarMatriculacion.setBounds(45, 122, 228, 76);
		contentPane.add(btnRealizarMatriculacion);

		contentPane.add(createNombreUsuTextField());

		contentPane.add(createTipoUsuarioTextField());

		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2.setIcon(new ImageIcon(PantallaEstudiante.class.getResource("/IMAGES/images2.jpg")));
		lblNewLabel2.setBounds(549, 55, 142, 143);
		contentPane.add(lblNewLabel2);

		JButton cs = new JButton("Cerrar sesion");
		cs.setBorderPainted(false);
		cs.setFocusPainted(false);
		cs.addActionListener(event -> {

			setVisible(false);
			new PantallaLogin();

		});
		cs.setHorizontalTextPosition(SwingConstants.LEFT);
		cs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cs.setBackground(new Color(255, 0, 0));
		cs.setForeground(new Color(255, 255, 255));
		cs.setFont(new Font(tipoLetra, Font.BOLD, 13));
		cs.setIconTextGap(15);
		cs.setIcon(new ImageIcon(PantallaEstudiante.class.getResource("/IMAGES/cerrar-sesion .png")));
		cs.setBounds(552, 303, 176, 39);
		contentPane.add(cs);

		JButton btnMostrarResueltos = new JButton("Propuestas resueltas");
		btnMostrarResueltos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrarResueltos.setForeground(Color.WHITE);
		btnMostrarResueltos.setFont(new Font(tipoLetra, Font.BOLD, 15));
		btnMostrarResueltos.setBackground(SystemColor.textHighlight);
		btnMostrarResueltos.setBounds(103, 146, 228, 99);
	}

	protected static JTextField createTipoUsuarioTextField() {
		JTextField tipoUsuario = new JTextField();
		tipoUsuario.setEditable(false);
		tipoUsuario.setText(main.java.presentacion.PantallaLogin.tipo);
		tipoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		tipoUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		tipoUsuario.setFont(new Font(tipoLetra, Font.BOLD, 15));
		tipoUsuario.setColumns(10);
		tipoUsuario.setBorder(null);
		tipoUsuario.setBackground(Color.WHITE);
		tipoUsuario.setBounds(552, 253, 252, 19);
		return tipoUsuario;
	}

	protected static JTextField createNombreUsuTextField() {
		JTextField nombreUsu = new JTextField();
		nombreUsu.setText(main.java.presentacion.PantallaLogin.nom);
		nombreUsu.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		nombreUsu.setEditable(false);
		nombreUsu.setHorizontalAlignment(SwingConstants.LEFT);
		nombreUsu.setFont(new Font(tipoLetra, Font.BOLD, 15));
		nombreUsu.setColumns(10);
		nombreUsu.setBorder(null);
		nombreUsu.setBackground(Color.WHITE);
		nombreUsu.setBounds(552, 214, 252, 19);
		return nombreUsu;
	}

}
