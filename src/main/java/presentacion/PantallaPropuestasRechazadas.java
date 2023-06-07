
package main.java.presentacion;

import java.awt.Color;

import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import main.java.negocio.controllers.GestorPropuestasCursos;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Cursor;

public class PantallaPropuestasRechazadas extends JFrame {

	private static final JList<CursoPropio> listaCursos = new JList<>();
	DefaultListModel<CursoPropio> modelo;
	private static String tipoLetra = "Tahoma";
	private static final Logger logger = Logger.getLogger(PantallaPropuestasRechazadas.class.getName());

	protected transient CursoPropio cursoSeleccionado;

	public PantallaPropuestasRechazadas() {

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaPropuestasRechazadas.class.getResource("/IMAGES/descarga.png")));
		setTitle("UCLM");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(PantallaPropuestasRechazadas.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JLabel lblCursosMatriculados = new JLabel("Propuestas rechazadas");
		lblCursosMatriculados.setVisible(true);
		lblCursosMatriculados.setFont(new Font(tipoLetra, Font.BOLD, 20));
		lblCursosMatriculados.setBounds(21, 101, 379, 42);
		contentPane.add(lblCursosMatriculados);

		JButton btnNewButton = new JButton("Volver");
		contentPane.add(PantallaPropuestasRealizadas.crearBotonVolver(btnNewButton));
		btnNewButton.addActionListener(event -> {

			setVisible(false);
			main.java.presentacion.PantallaDireccionCursos p = new main.java.presentacion.PantallaDireccionCursos();
			p.setVisible(true);

		});

		listaCursos.setBounds(21, 153, 723, 220);
		contentPane.add(listaCursos);
		modelo = new DefaultListModel<>();
		listaCursos.setModel(modelo);

		JButton btnEliminar = new JButton("Eliminar");
		contentPane.add(PantallaPropuestasRealizadas.datosBtnEliminar(btnEliminar));
		btnEliminar.addActionListener(event -> {

			int respuesta = JOptionPane.showConfirmDialog(null, "�Desea eliminar el curso?", "ATENCION",
					JOptionPane.OK_CANCEL_OPTION);
			if (respuesta == JOptionPane.OK_OPTION) {
				JOptionPane.showMessageDialog(null, "El curso ha sido eliminado de manera correcta.", "INFORMACION",
						JOptionPane.INFORMATION_MESSAGE);

				try {
					GestorPropuestasCursos.eliminarCurso(cursoSeleccionado);
				} catch (SQLException e) {
					logger.info("Se ha producido un error al eliminar el curso: " + e.getMessage());
				}
				modelo.removeElement(cursoSeleccionado);

			}
		});

		JButton btnInfo = new JButton("Info");
		contentPane.add(datosBtnInfo(btnInfo));
		btnInfo.addActionListener(event -> {

			main.java.presentacion.PantallaVisualizarCurso a = new main.java.presentacion.PantallaVisualizarCurso();
			setVisible(false);
			a.setVisible(true);
			a.btnVolver.setVisible(false);
			a.btnVolver2.setVisible(true);
			PantallaPropuestasRealizadas.infoCurso(cursoSeleccionado);
			if (cursoSeleccionado.getEstado().equals(EstadoCurso.PROPUESTA_RECHAZADA)) {
				a.btnMen.setVisible(true);
			}

		});

		listaCursos.addListSelectionListener(arg0 -> {
			if (!arg0.getValueIsAdjusting()) {
				cursoSeleccionado = listaCursos.getSelectedValue();
				if (cursoSeleccionado != null) {
					btnInfo.setVisible(true);

					btnEliminar.setVisible(!isCursoVisible(cursoSeleccionado.getEstado()));
				}

			}

		});
	}

	private boolean isCursoVisible(EstadoCurso estado) {
		return estado.equals(EstadoCurso.EN_IMPARTICION) || estado.equals(EstadoCurso.EN_MATRICULACION)
				|| estado.equals(EstadoCurso.VALIDADO);
	}

	protected static JButton datosBtnInfo(JButton a) {
		a.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		a.setForeground(Color.WHITE);
		a.setFont(new Font(tipoLetra, Font.BOLD, 13));
		a.setFocusPainted(false);
		a.setVisible(false);
		a.setBackground(SystemColor.textHighlight);
		a.setBounds(220, 397, 114, 49);
		return a;
	}

}
