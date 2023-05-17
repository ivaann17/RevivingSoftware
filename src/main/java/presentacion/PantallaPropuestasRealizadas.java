
package main.java.presentacion;

import java.awt.Color;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

public class PantallaPropuestasRealizadas extends JFrame {

	public JList<CursoPropio> listaCursos;
	DefaultListModel modelo;
	public CursoPropio cursoSeleccionado;

	public PantallaPropuestasRealizadas() {

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaPropuestasRealizadas.class.getResource("/IMAGES/descarga.png")));
		setTitle("UCLM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(PantallaPropuestasRealizadas.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JLabel lblCursosMatriculados = new JLabel("Historial");
		lblCursosMatriculados.setVisible(true);
		lblCursosMatriculados.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCursosMatriculados.setBounds(21, 101, 379, 42);
		contentPane.add(lblCursosMatriculados);

		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(630, 38, 114, 49);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				main.java.presentacion.PantallaDireccionCursos p = new main.java.presentacion.PantallaDireccionCursos();
				p.setVisible(true);
			}
		});

		listaCursos = new JList<CursoPropio>();
		listaCursos.setBounds(21, 153, 723, 207);
		contentPane.add(listaCursos);
		modelo = new DefaultListModel();
		listaCursos.setModel(modelo);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea eliminar el curso?", "ATENCION",
						JOptionPane.OK_CANCEL_OPTION);
				if (respuesta == JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "El curso ha sido eliminado de manera correcta.", "INFORMACION",
							JOptionPane.INFORMATION_MESSAGE);

					GestorPropuestasCursos.eliminarCurso(cursoSeleccionado);
					modelo.removeElement(cursoSeleccionado);

				}

			}
		});
		btnEliminar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setVisible(false);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEliminar.setFocusPainted(false);
		btnEliminar.setBackground(SystemColor.textHighlight);
		btnEliminar.setBounds(431, 404, 114, 49);
		contentPane.add(btnEliminar);

		JButton btnInfo = new JButton("Info");
		btnInfo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.java.presentacion.PantallaVisualizarCurso a = new main.java.presentacion.PantallaVisualizarCurso();
				setVisible(false);
				a.setVisible(true);
				infoCurso(a, cursoSeleccionado);
				if (cursoSeleccionado.getEstado().equals(EstadoCurso.PROPUESTA_RECHAZADA)) {
					a.btnMen.setVisible(true);
				}

			}
		});
		btnInfo.setForeground(Color.WHITE);
		btnInfo.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnInfo.setFocusPainted(false);
		btnInfo.setVisible(false);
		btnInfo.setBackground(SystemColor.textHighlight);
		btnInfo.setBounds(240, 404, 114, 49);
		contentPane.add(btnInfo);

		listaCursos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					cursoSeleccionado = listaCursos.getSelectedValue();
					if (cursoSeleccionado != null) {
						btnInfo.setVisible(true);

						if (cursoSeleccionado.getEstado().equals(EstadoCurso.PROPUESTO)) {
							btnEliminar.setVisible(true);
						} else {
							btnEliminar.setVisible(false);
						}
					}

				}
			}

		});
	}

	public static void infoCurso(main.java.presentacion.PantallaVisualizarCurso a, CursoPropio cursoSeleccionado) {
		a.id.setText(Integer.toString(cursoSeleccionado.getId()));
		a.dniProf.setText(cursoSeleccionado.getDniDirector().toString());
		a.dniSec.setText(cursoSeleccionado.getDniSecretario().toString());
		a.edicion.setText(Integer.toString(cursoSeleccionado.getEdicion()));
		a.nombreCurso.setText(cursoSeleccionado.getNombre().toString());
		a.numCreditos.setText(Integer.toString(cursoSeleccionado.getECTS()));
		a.facultad.setText(cursoSeleccionado.getCentro().toString());
		a.precio.setText(Double.toString(cursoSeleccionado.getTasaMatricula()));
		a.fechaIni.setText(cursoSeleccionado.getFechaInicio().toString());
		a.fechaFin.setText(cursoSeleccionado.getFechaFin().toString());
		a.mensaje = cursoSeleccionado.getMensaje().toString();
	}

}
