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
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.negocio.controllers.GestorConsultas;
import main.java.negocio.controllers.GestorPropuestasCursos;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PantallaComenzarCurso extends JFrame  {
	public JList<CursoPropio> listaCursos;
	DefaultListModel modelo;
	public CursoPropio cursoSeleccionado;
	protected JLabel lblCursosMatriculados;
	protected final JButton btnAceptar;
	protected JButton btnNewButton;

	public PantallaComenzarCurso() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(PantallaComenzarCurso.class.getResource("/IMAGES/descarga.png")));
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
				new ImageIcon(PantallaComenzarCurso.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		lblCursosMatriculados = new JLabel("Cursos en matriculacion");
		lblCursosMatriculados.setVisible(true);
		lblCursosMatriculados.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCursosMatriculados.setBounds(54, 98, 379, 42);
		contentPane.add(lblCursosMatriculados);

		btnAceptar = new JButton("Comenzar imparticion");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea poner en imparticion el curso?", "ATENCION",
						JOptionPane.OK_CANCEL_OPTION);
				if (respuesta == JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "El curso se va a impartir.", "INFORMACION",
							JOptionPane.INFORMATION_MESSAGE);
					try {
						GestorPropuestasCursos.editarEstadoCurso(cursoSeleccionado, EstadoCurso.EN_IMPARTIZICION);
						modelo.removeElement(cursoSeleccionado);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

					

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

		JLabel lblMatriculado = new JLabel("AUN NO EXISTEN MATRICULAS PARA ESTE CURSO.");
		lblMatriculado.setVisible(false);
		lblMatriculado.setForeground(new Color(204, 51, 51));
		lblMatriculado.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatriculado.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMatriculado.setBounds(64, 402, 619, 65);
		contentPane.add(lblMatriculado);

		JLabel lblMatriculas = new JLabel("Matriculas:");
		lblMatriculas.setVisible(false);
		lblMatriculas.setForeground(SystemColor.textHighlight);
		lblMatriculas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblMatriculas.setBounds(630, 385, 186, 39);
		contentPane.add(lblMatriculas);

		JTextField matriculas = new JTextField();
		matriculas.setHorizontalAlignment(SwingConstants.CENTER);
		matriculas.setVisible(false);
		matriculas.setFont(new Font("Tahoma", Font.BOLD, 13));
		matriculas.setEditable(false);
		matriculas.setColumns(10);
		matriculas.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		matriculas.setBounds(630, 434, 114, 39);
		contentPane.add(matriculas);

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
				main.java.presentacion.PantallaJefeGabineteVicerrectorado p = new main.java.presentacion.PantallaJefeGabineteVicerrectorado();
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
					cursoSeleccionado = listaCursos.getSelectedValue();
					if (cursoSeleccionado != null) {
						try {
							boolean existeMat;
							existeMat = GestorPropuestasCursos.existeCursoConMatricula(cursoSeleccionado);
							if (existeMat) {
								lblMatriculado.setVisible(false);
								btnAceptar.setVisible(true);
								matriculas.setVisible(true);
								lblMatriculas.setVisible(true);
								matriculas
										.setText(Integer.toString(GestorConsultas.getNumMatricula(cursoSeleccionado)));
							} else {
								btnAceptar.setVisible(false);
								lblMatriculado.setVisible(true);
								matriculas.setVisible(false);
								lblMatriculas.setVisible(false);
							}

						} catch (Exception e) {
						}

					}
				}
			}
		});
	}
}
