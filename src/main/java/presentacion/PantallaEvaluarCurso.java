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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.negocio.controllers.GestorPropuestasCursos;
import main.java.negocio.entities.CursoPropio;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Cursor;

public class PantallaEvaluarCurso extends JFrame {
	public JList<CursoPropio> listaCursos;
	DefaultListModel modelo;
	public CursoPropio cursoSeleccionado;
	protected JLabel propuestas;
	protected final JButton btnRechazar;
	protected final JButton btnAceptar;
	protected JButton btnNewButton;

	public PantallaEvaluarCurso() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(PantallaEvaluarCurso.class.getResource("/IMAGES/descarga.png")));
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
				new ImageIcon(PantallaEvaluarCurso.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		propuestas = new JLabel("Propuestas de cursos:");
		propuestas.setVisible(true);
		propuestas.setFont(new Font("Tahoma", Font.BOLD, 20));
		propuestas.setBounds(54, 98, 379, 42);
		contentPane.add(propuestas);

		btnAceptar = new JButton("Aceptar propuesta");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea aceptar la propuesta?", "ATENCION",
						JOptionPane.OK_CANCEL_OPTION);
				if (respuesta == JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "El curso se ha dado de alta.", "INFORMACION",
							JOptionPane.INFORMATION_MESSAGE);
					try {
						GestorPropuestasCursos.aceptarPropuesta(cursoSeleccionado);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					modelo.removeElement(cursoSeleccionado);

				}
			}
		});
		btnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAceptar.setBackground(SystemColor.textHighlight);
		btnAceptar.setBounds(54, 398, 226, 75);
		btnAceptar.setVisible(false);
		contentPane.add(btnAceptar);

		btnRechazar = new JButton("Rechazar propuesta");
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea rechazar la propuesta?", "Confirmacion",
						JOptionPane.OK_CANCEL_OPTION);
				if (respuesta == JOptionPane.OK_OPTION) {
					String mensaje = "";
					while (mensaje.equals("")) {
						mensaje = JOptionPane.showInputDialog(null, "Escriba los motivos y recomendaciones necesarias.",
								"Atencion!", JOptionPane.PLAIN_MESSAGE);
						if (mensaje == null) {
							return;
						}
						if (mensaje.equals("")) {
							JOptionPane.showMessageDialog(null, "Debe rellenar los motivos del rechazo.");
						}

						else {
							try {
								GestorPropuestasCursos.rechazarPropuesta(cursoSeleccionado, mensaje);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							modelo.removeElement(cursoSeleccionado);
						}
					}

				}

			}
		});
		btnRechazar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRechazar.setForeground(Color.WHITE);
		btnRechazar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRechazar.setBackground(SystemColor.textHighlight);
		btnRechazar.setBounds(449, 398, 226, 75);
		btnRechazar.setVisible(false);
		contentPane.add(btnRechazar);

		btnNewButton = new JButton("Volver");
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
				main.java.presentacion.PantallaEmpleadosVicerrectorado p = new main.java.presentacion.PantallaEmpleadosVicerrectorado();
				p.setVisible(true);
			}
		});

		listaCursos = new JList();
		listaCursos.setBounds(54, 137, 690, 251);
		contentPane.add(listaCursos);
		modelo = new DefaultListModel();
		listaCursos.setModel(modelo);

		listaCursos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					btnAceptar.setVisible(true);
					btnRechazar.setVisible(true);
					cursoSeleccionado = listaCursos.getSelectedValue();

				}
			}
		});
	}
}
