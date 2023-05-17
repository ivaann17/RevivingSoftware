
package main.java.presentacion;

import java.awt.Color;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
	private String tipoLetra = "Tahoma";

	private transient CursoPropio cursoSeleccionado;

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
		btnNewButton.setFocusPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(630, 38, 114, 49);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				main.java.presentacion.PantallaDireccionCursos p = new main.java.presentacion.PantallaDireccionCursos();
				p.setVisible(true);
			}
		});

		listaCursos.setBounds(21, 153, 723, 220);
		contentPane.add(listaCursos);
		modelo = new DefaultListModel<>();
		listaCursos.setModel(modelo);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int respuesta = JOptionPane.showConfirmDialog(null, "�Desea eliminar el curso?", "ATENCION",
						JOptionPane.OK_CANCEL_OPTION);
				if (respuesta == JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "El curso ha sido eliminado de manera correcta.", "INFORMACION",
							JOptionPane.INFORMATION_MESSAGE);

					try {
						GestorPropuestasCursos.eliminarCurso(cursoSeleccionado);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					modelo.removeElement(cursoSeleccionado);

				}

			}
		});
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setVisible(false);
		btnEliminar.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnEliminar.setFocusPainted(false);
		btnEliminar.setBackground(SystemColor.textHighlight);
		btnEliminar.setBounds(439, 397, 114, 49);
		contentPane.add(btnEliminar);

		JButton btnInfo = new JButton("Info");
		btnInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.java.presentacion.PantallaVisualizarCurso a = new main.java.presentacion.PantallaVisualizarCurso();
				setVisible(false);
				a.setVisible(true);
				a.btnVolver.setVisible(false);
				a.btnVolver2.setVisible(true);
				infoCurso(a, cursoSeleccionado);
				if (cursoSeleccionado.getEstado().equals(EstadoCurso.PROPUESTA_RECHAZADA)) {
					a.btnMen.setVisible(true);
				}

			}
		});
		btnInfo.setForeground(Color.WHITE);
		btnInfo.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnInfo.setFocusPainted(false);
		btnInfo.setVisible(false);
		btnInfo.setBackground(SystemColor.textHighlight);
		btnInfo.setBounds(220, 397, 114, 49);
		contentPane.add(btnInfo);

		listaCursos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					cursoSeleccionado = listaCursos.getSelectedValue();
					if (cursoSeleccionado != null) {
						btnInfo.setVisible(true);

						if (cursoSeleccionado.getEstado().equals(EstadoCurso.EN_IMPARTIZICION)
								|| cursoSeleccionado.getEstado().equals(EstadoCurso.EN_MATRICULACION)
								|| cursoSeleccionado.getEstado().equals(EstadoCurso.VALIDADO)) {
							btnEliminar.setVisible(false);
						} else {
							btnEliminar.setVisible(true);
						}
					}

				}
			}

		});
	}

	public void infoCurso(main.java.presentacion.PantallaVisualizarCurso a, CursoPropio cursoSeleccionado) {
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

}
