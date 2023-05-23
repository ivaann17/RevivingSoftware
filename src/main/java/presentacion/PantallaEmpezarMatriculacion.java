
package main.java.presentacion;

import java.awt.Color;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.negocio.controllers.GestorPropuestasCursos;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;
import main.java.persistencia.GestorBD;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Cursor;

public class PantallaEmpezarMatriculacion extends JFrame {

	protected JList<CursoPropio> listaCursos;
	DefaultListModel<CursoPropio> modelo;
	protected CursoPropio cursoSeleccionado;
	private static final Logger logger = Logger.getLogger(PantallaEmpezarMatriculacion.class.getName());
	private static String tipoLetra = "Tahoma";

	public PantallaEmpezarMatriculacion() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaEmpezarMatriculacion.class.getResource("/IMAGES/descarga.png")));
		setTitle("UCLM");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCursosMatriculados = new JLabel("Cursos validados:");
		lblCursosMatriculados.setVisible(true);
		lblCursosMatriculados.setFont(new Font(tipoLetra, Font.BOLD, 20));
		lblCursosMatriculados.setBounds(54, 103, 379, 42);
		contentPane.add(lblCursosMatriculados);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(PantallaEmpezarMatriculacion.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JButton btnValidar = new JButton("Empezar matriculacion");
		btnValidar.addActionListener(event -> {

			int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea comenzar la matriculacion de este curso?",
					"ATENCIÃ“N", JOptionPane.OK_CANCEL_OPTION);
			if (respuesta == JOptionPane.OK_OPTION) {
				try {
					GestorPropuestasCursos.editarEstadoCurso(cursoSeleccionado, EstadoCurso.EN_MATRICULACION);
				} catch (SQLException e1) {
					logger.info("Se ha producido un error al editar el estado del curso: " + e1.getMessage());
				}
				modelo.removeElement(cursoSeleccionado);
				JOptionPane.showMessageDialog(null, "El curso ha sido dado de alta.", "INFORMACION",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});
		btnValidar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnValidar.setForeground(Color.WHITE);
		btnValidar.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnValidar.setBackground(SystemColor.textHighlight);
		btnValidar.setBounds(54, 398, 226, 75);
		btnValidar.setVisible(false);
		contentPane.add(btnValidar);

		JButton btnEli = new JButton("Eliminar curso");
		btnEli.addActionListener(event -> {

			int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el curso?", "ATENCION",
					JOptionPane.OK_CANCEL_OPTION);
			if (respuesta == JOptionPane.OK_OPTION) {
				JOptionPane.showMessageDialog(null, "El curso ha sido eliminado de manera correcta.", "INFORMACION",
						JOptionPane.INFORMATION_MESSAGE);

				try {
					GestorPropuestasCursos.eliminarCurso(cursoSeleccionado);
				} catch (SQLException e1) {
					logger.info("Se ha producido un error al eliminar el curso: " + e1.getMessage());
				}
				modelo.removeElement(cursoSeleccionado);
			}

		});
		btnEli.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEli.setForeground(Color.WHITE);
		btnEli.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnEli.setBackground(SystemColor.textHighlight);
		btnEli.setBounds(448, 398, 226, 75);
		btnEli.setVisible(false);
		contentPane.add(btnEli);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(630, 38, 114, 49);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(event -> {

			setVisible(false);
			main.java.presentacion.PantallaDireccionCursos p = new main.java.presentacion.PantallaDireccionCursos();
			p.setVisible(true);

		});

		listaCursos = new JList<>();
		listaCursos.setBounds(54, 144, 690, 226);
		contentPane.add(listaCursos);
		modelo = new DefaultListModel<>();

		listaCursos.setModel(modelo);

		listaCursos.addListSelectionListener(event -> {

			cursoSeleccionado = listaCursos.getSelectedValue();
			if (cursoSeleccionado != null) {
				btnEli.setVisible(true);
				btnValidar.setVisible(true);

			}

		});
	}
}
