
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

import main.java.negocio.controllers.GestorMatriculacion;
import main.java.negocio.controllers.GestorPropuestasCursos;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;
import main.java.negocio.entities.Matricula;
import main.java.negocio.entities.ModoPago;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Cursor;

public class PantallaEmpezarMatriculacion extends JFrame {

	public JList<CursoPropio> listaCursos;
	DefaultListModel modelo;
	public CursoPropio cursoSeleccionado;

	public PantallaEmpezarMatriculacion() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaEmpezarMatriculacion.class.getResource("/IMAGES/descarga.png")));
		setTitle("UCLM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblCursosMatriculados = new JLabel("Cursos validados:");
		lblCursosMatriculados.setVisible(true);
		lblCursosMatriculados.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCursosMatriculados.setBounds(54, 103, 379, 42);
		contentPane.add(lblCursosMatriculados);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(PantallaEmpezarMatriculacion.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JButton btnValidar = new JButton("Empezar matriculacion");
		btnValidar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea comenzar la matriculacion de este curso?",
						"ATENCIÃ“N", JOptionPane.OK_CANCEL_OPTION);
				if (respuesta == JOptionPane.OK_OPTION) {
					GestorPropuestasCursos.editarEstadoCurso(cursoSeleccionado, EstadoCurso.EN_MATRICULACION);
					modelo.removeElement(cursoSeleccionado);
					JOptionPane.showMessageDialog(null, "El curso ha sido dado de alta.", "INFORMACION",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnValidar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnValidar.setForeground(Color.WHITE);
		btnValidar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnValidar.setBackground(SystemColor.textHighlight);
		btnValidar.setBounds(54, 398, 226, 75);
		btnValidar.setVisible(false);
		contentPane.add(btnValidar);

		JButton btnEli = new JButton("Eliminar curso");
		btnEli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

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
		btnEli.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEli.setForeground(Color.WHITE);
		btnEli.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEli.setBackground(SystemColor.textHighlight);
		btnEli.setBounds(448, 398, 226, 75);
		btnEli.setVisible(false);
		contentPane.add(btnEli);

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

		listaCursos = new JList();
		listaCursos.setBounds(54, 144, 659, 226);
		contentPane.add(listaCursos);
		modelo = new DefaultListModel();

		listaCursos.setModel(modelo);

		listaCursos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				cursoSeleccionado = listaCursos.getSelectedValue();
				if (cursoSeleccionado != null) {
					btnEli.setVisible(true);
					btnValidar.setVisible(true);

				}
			}

		});
	}
}
