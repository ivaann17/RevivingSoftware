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
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.negocio.controllers.GestorMatriculacion;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.ModoPago;
import main.java.persistencia.GestorBD;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class PantallaMatriculacion extends JFrame {

	public JList<CursoPropio> listaCursos;
	DefaultListModel<CursoPropio> modelo;
	public CursoPropio cursoSeleccionado;
	private static String tipoLetra = "Tahoma";
	private static final Logger logger = Logger.getLogger(PantallaMatriculacion.class.getName());

	public PantallaMatriculacion() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(PantallaMatriculacion.class.getResource("/IMAGES/descarga.png")));
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
				new ImageIcon(PantallaMatriculacion.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JButton btnTar = new JButton("Pago con Tarjeta");
		btnTar.addActionListener(event -> {

			main.java.presentacion.PantallaDatosAlumno p;
			setVisible(false);
			try {
				p = new main.java.presentacion.PantallaDatosAlumno();
				p.metoPago.setText(ModoPago.TARJETA_CREDITO.toString());
				p.textPrecio.setText(Double.toString(cursoSeleccionado.getTasaMatricula()));
				p.id = cursoSeleccionado.getId();
				p.setVisible(true);
			} catch (SQLException e1) {
				logger.info("Se ha producido un error al obtener los datos de la base: " + e1.getMessage());
			}

		});
		btnTar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTar.setForeground(Color.WHITE);
		btnTar.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnTar.setBackground(SystemColor.textHighlight);
		btnTar.setBounds(56, 380, 226, 75);
		btnTar.setVisible(false);
		contentPane.add(btnTar);

		JButton btnTrans = new JButton("Pago por transferencia");
		btnTrans.addActionListener(event -> {

			setVisible(false);
			main.java.presentacion.PantallaDatosAlumno p;
			try {
				p = new main.java.presentacion.PantallaDatosAlumno();
				p.setVisible(true);
				p.metoPago.setText(ModoPago.TRANSFERENCIA.toString());
				p.textPrecio.setText(Double.toString(cursoSeleccionado.getTasaMatricula()));
				p.id = cursoSeleccionado.getId();
			} catch (SQLException e1) {
				logger.info("Se ha producido un error al obtener los datos de la base: " + e1.getMessage());
			}

		});
		btnTrans.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTrans.setForeground(Color.WHITE);
		btnTrans.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnTrans.setBackground(SystemColor.textHighlight);
		btnTrans.setBounds(449, 380, 226, 75);
		btnTrans.setVisible(false);
		contentPane.add(btnTrans);

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
			main.java.presentacion.PantallaEstudiante p = new main.java.presentacion.PantallaEstudiante();
			p.setVisible(true);

		});

		listaCursos = new JList<>();
		listaCursos.setBounds(54, 108, 690, 251);
		contentPane.add(listaCursos);
		modelo = new DefaultListModel<>();

		listaCursos.setModel(modelo);

		JLabel lblMatriculado = new JLabel("YA SE HA MATRICULADO EN ESTE CURSO");
		lblMatriculado.setVisible(false);
		lblMatriculado.setForeground(new Color(204, 51, 51));
		lblMatriculado.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatriculado.setFont(new Font(tipoLetra, Font.BOLD, 16));
		lblMatriculado.setBounds(56, 390, 619, 65);
		contentPane.add(lblMatriculado);

		listaCursos.addListSelectionListener(event -> {
			cursoSeleccionado = listaCursos.getSelectedValue();
			if (cursoSeleccionado != null) {
				boolean existeMatricula;
				try {
					existeMatricula = GestorMatriculacion.existe(cursoSeleccionado.getId(),
							main.java.presentacion.PantallaLogin.dni.toString());

					if (!existeMatricula) {
						lblMatriculado.setVisible(false);
						btnTar.setVisible(true);
						btnTrans.setVisible(true);
					} else {
						btnTar.setVisible(false);
						btnTrans.setVisible(false);
						lblMatriculado.setVisible(true);
						listaCursos.clearSelection();
					}
				} catch (Exception e) {
					logger.info(
							"Se ha producido un error al comprobar la existencia de la matricula: " + e.getMessage());
				}
			}

		});
	}
}
