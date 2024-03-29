
package main.java.presentacion;

import java.awt.Color;

import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import main.java.negocio.entities.CursoPropio;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Cursor;

public class PantallaMisCursos extends JFrame {

	protected JList<CursoPropio> listaCursos;
	protected static DefaultListModel<CursoPropio> modelo;
	protected transient CursoPropio cursoSeleccionado;
	private static String tipoLetra = "Tahoma";

	public PantallaMisCursos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaMisCursos.class.getResource("/IMAGES/descarga.png")));
		setTitle("UCLM");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel
				.setIcon(new ImageIcon(PantallaMisCursos.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JLabel lblCursosMatriculados = new JLabel("Cursos matriculados:");
		lblCursosMatriculados.setVisible(true);
		lblCursosMatriculados.setFont(new Font(tipoLetra, Font.BOLD, 20));
		lblCursosMatriculados.setBounds(24, 90, 379, 42);
		contentPane.add(lblCursosMatriculados);

		JButton btnInfo = new JButton("Info");
		btnInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInfo.setFocusPainted(false);
		btnInfo.setVisible(false);
		btnInfo.setForeground(Color.WHITE);
		btnInfo.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnInfo.setBackground(SystemColor.textHighlight);
		btnInfo.setBounds(272, 398, 226, 75);
		contentPane.add(btnInfo);
		btnInfo.addActionListener(event -> {

			main.java.presentacion.PantallaVisualizarCurso a = new main.java.presentacion.PantallaVisualizarCurso();
			setVisible(false);
			a.setVisible(true);
			a.btnVolver.setVisible(false);
			a.btnVolver2.setVisible(false);
			a.btnVolver3.setVisible(true);
			main.java.presentacion.PantallaPropuestasRealizadas.infoCurso(cursoSeleccionado);

		});

		JButton btnNewButton = new JButton("Volver");
		contentPane.add(PantallaPropuestasRealizadas.crearBotonVolver(btnNewButton));
		btnNewButton.addActionListener(event -> {

			setVisible(false);
			main.java.presentacion.PantallaEstudiante p = new main.java.presentacion.PantallaEstudiante();
			p.setVisible(true);

		});

		listaCursos = new JList<>();
		listaCursos.setBounds(23, 132, 721, 258);
		contentPane.add(listaCursos);
		modelo = new DefaultListModel<>();
		listaCursos.setModel(modelo);

		listaCursos.addListSelectionListener(arg0 -> {

			if (!arg0.getValueIsAdjusting()) {
				cursoSeleccionado = listaCursos.getSelectedValue();
				if (cursoSeleccionado != null) {
					btnInfo.setVisible(true);
				}

			}

		});
	}
}
