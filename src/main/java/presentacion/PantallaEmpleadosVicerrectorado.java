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

public class PantallaEmpleadosVicerrectorado extends JFrame {

	private JPanel contentPane;
	private static String tipoLetra = "Tahoma";
	private static final Logger logger = Logger.getLogger(PantallaEmpezarMatriculacion.class.getName());

	public PantallaEmpleadosVicerrectorado() {
		setTitle("UCLM");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaEmpleadosVicerrectorado.class.getResource("/IMAGES/descarga.png")));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				PantallaEmpleadosVicerrectorado.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(10, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JButton btnVerPropuestas = new JButton("Cursos propuestos\r\n");
		btnVerPropuestas.addActionListener(event -> {

			main.java.presentacion.PantallaEvaluarCurso p = new main.java.presentacion.PantallaEvaluarCurso();
			try {
				GestorConsultas.listarCursosPorEstado(p.modelo, EstadoCurso.PROPUESTO);
				setVisible(false);
				p.setVisible(true);
			} catch (Exception e1) {
				logger.info("Se ha producido un error al mostrar los cursos propuestos: " + e1.getMessage());
			}

		});
		btnVerPropuestas.setFocusPainted(false);
		btnVerPropuestas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVerPropuestas.setForeground(Color.WHITE);
		btnVerPropuestas.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnVerPropuestas.setBackground(SystemColor.textHighlight);
		btnVerPropuestas.setBounds(45, 122, 228, 76);
		contentPane.add(btnVerPropuestas);

		contentPane.add(main.java.presentacion.PantallaEstudiante.createNombreUsuTextField());

		contentPane.add(main.java.presentacion.PantallaEstudiante.createTipoUsuarioTextField());

		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2.setIcon(new ImageIcon(PantallaEmpleadosVicerrectorado.class.getResource("/IMAGES/images2.jpg")));
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
		cs.setIcon(new ImageIcon(PantallaEmpleadosVicerrectorado.class.getResource("/IMAGES/cerrar-sesion .png")));
		cs.setBounds(552, 303, 176, 39);
		contentPane.add(cs);

		JButton btnMostrarResueltos = new JButton("Propuestas resueltas");
		btnMostrarResueltos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrarResueltos.setForeground(Color.WHITE);
		btnMostrarResueltos.setFont(new Font(tipoLetra, Font.BOLD, 15));
		btnMostrarResueltos.setBackground(SystemColor.textHighlight);
		btnMostrarResueltos.setBounds(103, 146, 228, 99);
	}
}
