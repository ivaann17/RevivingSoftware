
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

public class PantallaPropuestasRealizadas extends JFrame {

	public static final JList<CursoPropio> listaCursos = new JList<>();
	DefaultListModel<CursoPropio> modelo;
	protected transient CursoPropio cursoSeleccionado;
	private static final Logger logger = Logger.getLogger(PantallaPropuestasRealizadas.class.getName());
	private static String tipoLetra = "Tahoma";

	public PantallaPropuestasRealizadas() {

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaPropuestasRealizadas.class.getResource("/resources/IMAGES/descarga.png")));
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
				new ImageIcon(PantallaPropuestasRealizadas.class.getResource("/resources/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JLabel lblCursosMatriculados = new JLabel("Historial");
		lblCursosMatriculados.setVisible(true);
		lblCursosMatriculados.setFont(new Font(tipoLetra, Font.BOLD, 20));
		lblCursosMatriculados.setBounds(21, 101, 379, 42);
		contentPane.add(lblCursosMatriculados);

		JButton btnNewButton = new JButton("Volver");
		contentPane.add(crearBotonVolver(btnNewButton));
		btnNewButton.addActionListener(event -> {

			setVisible(false);
			main.java.presentacion.PantallaDireccionCursos p = new main.java.presentacion.PantallaDireccionCursos();
			p.setVisible(true);

		});

		listaCursos.setBounds(21, 153, 723, 207);
		contentPane.add(listaCursos);
		modelo = new DefaultListModel<>();
		listaCursos.setModel(modelo);

		JButton btnEliminar = new JButton("Eliminar");
		contentPane.add(datosBtnEliminar(btnEliminar));
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
		contentPane.add(PantallaPropuestasRechazadas.datosBtnInfo(btnInfo));

		btnInfo.addActionListener(event -> {

			main.java.presentacion.PantallaVisualizarCurso a = new main.java.presentacion.PantallaVisualizarCurso();
			setVisible(false);
			a.setVisible(true);
			infoCurso(cursoSeleccionado);
			if (cursoSeleccionado.getEstado().equals(EstadoCurso.PROPUESTA_RECHAZADA)) {
				a.btnMen.setVisible(true);

			}
		});

		listaCursos.addListSelectionListener(arg0 -> {
			if (!arg0.getValueIsAdjusting()) {
				cursoSeleccionado = listaCursos.getSelectedValue();
				if (cursoSeleccionado != null) {
					btnInfo.setVisible(true);

					btnEliminar.setVisible(cursoSeleccionado.getEstado().equals(EstadoCurso.PROPUESTO));
				}
			}
		});

	}

	public static void infoCurso(CursoPropio cursoSeleccionado) {
		PantallaVisualizarCurso.id.setText(Integer.toString(cursoSeleccionado.getId()));
		PantallaVisualizarCurso.dniProf.setText(cursoSeleccionado.getDniDirector());
		PantallaVisualizarCurso.dniSec.setText(cursoSeleccionado.getDniSecretario());
		PantallaVisualizarCurso.edicion.setText(Integer.toString(cursoSeleccionado.getEdicion()));
		PantallaVisualizarCurso.nombreCurso.setText(cursoSeleccionado.getNombre());
		PantallaVisualizarCurso.numCreditos.setText(Integer.toString(cursoSeleccionado.getECTS()));
		PantallaVisualizarCurso.facultad.setText(cursoSeleccionado.getCentro());
		PantallaVisualizarCurso.precio.setText(Double.toString(cursoSeleccionado.getTasaMatricula()));
		PantallaVisualizarCurso.fechaIni.setText(cursoSeleccionado.getFechaInicio().toString());
		PantallaVisualizarCurso.fechaFin.setText(cursoSeleccionado.getFechaFin().toString());
		PantallaVisualizarCurso.mensaje = cursoSeleccionado.getMensaje();
	}

	protected static JButton crearBotonVolver(JButton a) {
		a.setFocusPainted(false);
		a.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		a.setForeground(Color.WHITE);
		a.setFont(new Font(tipoLetra, Font.BOLD, 13));
		a.setBackground(SystemColor.textHighlight);
		a.setBounds(630, 38, 114, 49);
		return a;
	}

	protected static JButton datosBtnEliminar(JButton a) {
		a.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		a.setForeground(Color.WHITE);
		a.setVisible(false);
		a.setFont(new Font(tipoLetra, Font.BOLD, 13));
		a.setFocusPainted(false);
		a.setBackground(SystemColor.textHighlight);
		a.setBounds(431, 404, 114, 49);
		return a;
	}
}
