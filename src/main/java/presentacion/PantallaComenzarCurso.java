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
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

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
import javax.swing.WindowConstants;

public class PantallaComenzarCurso extends JFrame {
	protected JList<CursoPropio> listaCursos;
	protected DefaultListModel<CursoPropio> modelo;
	protected transient CursoPropio cursoSeleccionado;
	protected JLabel lblCursosMatriculados;
	protected final JButton btnAceptar;
	protected JButton btnNewButton;
	private String tipoLetra = "Tahoma";
	private static final Logger logger = Logger.getLogger(PantallaComenzarCurso.class.getName());

	public PantallaComenzarCurso() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaComenzarCurso.class.getResource("/resources/IMAGES/descarga.png")));
		setTitle("UCLM");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				PantallaComenzarCurso.class.getResource("/resources/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		lblCursosMatriculados = new JLabel("Cursos en matriculacion");
		lblCursosMatriculados.setVisible(true);
		lblCursosMatriculados.setFont(new Font(tipoLetra, Font.BOLD, 20));
		lblCursosMatriculados.setBounds(54, 98, 379, 42);
		contentPane.add(lblCursosMatriculados);

		btnAceptar = new JButton("Comenzar imparticion");
		btnAceptar.addActionListener(event -> {
			int respuesta = mostrarConfirmacion();
			if (respuesta == JOptionPane.OK_OPTION) {
				mostrarInformacion();
				editarEstadoCurso();
				eliminarCursoSeleccionado();

			}
		});
		btnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnAceptar.setBackground(SystemColor.textHighlight);
		btnAceptar.setBounds(54, 398, 226, 75);
		btnAceptar.setVisible(false);
		contentPane.add(btnAceptar);

		JLabel lblMatriculado = new JLabel("AUN NO EXISTEN MATRICULAS PARA ESTE CURSO.");
		lblMatriculado.setVisible(false);
		lblMatriculado.setForeground(new Color(204, 51, 51));
		lblMatriculado.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatriculado.setFont(new Font(tipoLetra, Font.BOLD, 16));
		lblMatriculado.setBounds(64, 402, 619, 65);
		contentPane.add(lblMatriculado);

		JLabel lblMatriculas = new JLabel("Matriculas:");
		lblMatriculas.setVisible(false);
		lblMatriculas.setForeground(SystemColor.textHighlight);
		lblMatriculas.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblMatriculas.setBounds(630, 385, 186, 39);
		contentPane.add(lblMatriculas);

		JTextField matriculas = new JTextField();
		matriculas.setHorizontalAlignment(SwingConstants.CENTER);
		matriculas.setVisible(false);
		matriculas.setFont(new Font(tipoLetra, Font.BOLD, 13));
		matriculas.setEditable(false);
		matriculas.setColumns(10);
		matriculas.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		matriculas.setBounds(630, 434, 114, 39);
		contentPane.add(matriculas);

		btnNewButton = new JButton("Volver");
		contentPane.add(PantallaPropuestasRealizadas.crearBotonVolver(btnNewButton));
		btnNewButton.addActionListener(event -> {

			setVisible(false);
			main.java.presentacion.PantallaJefeGabineteVicerrectorado p = new main.java.presentacion.PantallaJefeGabineteVicerrectorado();
			p.setVisible(true);

		});

		listaCursos = new JList<>();
		listaCursos.setBounds(54, 137, 690, 251);
		contentPane.add(listaCursos);
		modelo = new DefaultListModel<>();
		listaCursos.setModel(modelo);

		listaCursos.addListSelectionListener(arg0 -> {
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
							matriculas.setText(Integer.toString(GestorConsultas.getNumMatricula(cursoSeleccionado)));
						} else {
							btnAceptar.setVisible(false);
							lblMatriculado.setVisible(true);
							matriculas.setVisible(false);
							lblMatriculas.setVisible(false);
						}

					} catch (Exception e) {
						logger.info("Se ha producido un error al seleccionar el curso: " + e.getMessage());
					}

				}
			}

		});
	}

	private int mostrarConfirmacion() {
		return JOptionPane.showConfirmDialog(null, "Desea poner en imparticion el curso?", "ATENCION",
				JOptionPane.OK_CANCEL_OPTION);
	}

	private void mostrarInformacion() {
		JOptionPane.showMessageDialog(null, "El curso se va a impartir.", "INFORMACION",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void editarEstadoCurso() {
		try {
			GestorPropuestasCursos.editarEstadoCurso(cursoSeleccionado, EstadoCurso.EN_IMPARTICION);
		} catch (SQLException e1) {
			logger.info("Se ha producido un error al editar el estado del curso: " + e1.getMessage());
		}
	}

	private void eliminarCursoSeleccionado() {
		modelo.removeElement(cursoSeleccionado);
	}
}
