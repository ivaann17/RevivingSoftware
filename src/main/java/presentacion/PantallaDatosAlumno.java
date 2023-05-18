package main.java.presentacion;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;
import java.awt.Color;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.Cursor;

import main.java.negocio.controllers.GestorConsultas;
import main.java.negocio.controllers.GestorMatriculacion;

import main.java.negocio.entities.EstadoCurso;

import main.java.negocio.entities.Matricula;
import main.java.negocio.entities.ModoPago;
import main.java.persistencia.GestorBD;

import java.awt.Font;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

public class PantallaDatosAlumno extends JFrame {

	protected JPanel contentPane;
	protected JTextField dniAlu;
	protected JTextField apeAlu;
	protected JTextField nomAlu;
	protected JTextField textPrecio;
	protected JButton btnNewButton;
	protected JButton btnPagar;

	protected JLabel lblDatosDeMatriculacion;
	protected JLabel lblNewLabel;
	protected JLabel lblPrecio;
	protected JLabel lblNomAlu;
	protected JLabel lblApellido;
	private String tipoLetra = "Tahoma";

	int id;
	protected JLabel lblDNIAlu;
	main.java.presentacion.PantallaEstudiante p;
	protected JTextField metoPago;
	protected Matricula matricula;
	private static final SecureRandom random = new SecureRandom();
	private static final Logger logger = Logger.getLogger(GestorBD.class.getName());


	public PantallaDatosAlumno() throws SQLException{
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(PantallaDatosAlumno.class.getResource("/IMAGES/descarga.png")));
		setTitle("UCLM\r\n");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 618);
		contentPane = new JPanel();
		contentPane.setFont(new Font(tipoLetra, Font.BOLD, 15));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNewLabel = new JLabel("");
		lblNewLabel
				.setIcon(new ImageIcon(PantallaDatosAlumno.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		dniAlu = new JTextField();
		dniAlu.setEditable(false);
		dniAlu.setText(main.java.presentacion.PantallaLogin.dni);
		dniAlu.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		dniAlu.setFont(new Font(tipoLetra, Font.BOLD, 13));
		dniAlu.setBounds(428, 235, 259, 39);
		contentPane.add(dniAlu);
		dniAlu.setColumns(10);

		btnNewButton = new JButton("Volver");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnNewButton.setBounds(20, 496, 114, 49);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.java.presentacion.PantallaMatriculacion m = new main.java.presentacion.PantallaMatriculacion();
				try {
					GestorConsultas.listarCursosPorEstado(m.modelo, EstadoCurso.EN_MATRICULACION);
					setVisible(false);
					m.setVisible(true);
				} catch (Exception e1) {
				}

			}
		});

		nomAlu = new JTextField();
		nomAlu.setText(devolverNombre(main.java.presentacion.PantallaLogin.nom));
		nomAlu.setEditable(false);
		nomAlu.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		nomAlu.setFont(new Font(tipoLetra, Font.BOLD, 13));
		nomAlu.setColumns(10);
		nomAlu.setBounds(62, 235, 259, 39);
		contentPane.add(nomAlu);

		apeAlu = new JTextField();
		apeAlu.setText(devolverApellido(main.java.presentacion.PantallaLogin.nom));
		apeAlu.setEditable(false);
		apeAlu.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		apeAlu.setFont(new Font(tipoLetra, Font.BOLD, 13));
		apeAlu.setColumns(10);
		apeAlu.setBounds(62, 313, 259, 39);
		contentPane.add(apeAlu);

		lblDatosDeMatriculacion = new JLabel("Datos de matriculacion:");
		lblDatosDeMatriculacion.setVisible(true);
		lblDatosDeMatriculacion.setFont(new Font(tipoLetra, Font.BOLD, 20));
		lblDatosDeMatriculacion.setBounds(232, 102, 379, 42);
		contentPane.add(lblDatosDeMatriculacion);

		btnPagar = new JButton("Pagar");
		btnPagar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPagar.setForeground(Color.WHITE);
		btnPagar.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnPagar.setBackground(SystemColor.textHighlight);
		btnPagar.setBounds(594, 490, 114, 49);
		btnPagar.setVisible(true);
		contentPane.add(btnPagar);
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date fechaActual = new Date();

				if (metoPago.getText().equals(ModoPago.TARJETA_CREDITO.toString())) {

					int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea pagar con tarjeta?", "ATENCION",
							JOptionPane.OK_CANCEL_OPTION);
					if (respuesta == JOptionPane.OK_OPTION) {
						matricula = new Matricula(numRand(), nomAlu.getText(), apeAlu.getText(),
								ModoPago.valueOf(ModoPago.TARJETA_CREDITO.toString()), fechaActual, dniAlu.getText(),
								Double.parseDouble(textPrecio.getText()), id);
						try {
							GestorMatriculacion.realizarMatriculacion(matricula);
						} catch (SQLException e1) {
							logger.info("Se ha producido un error al realizar matriculacion: " + e1.getMessage());
						}
						JOptionPane.showMessageDialog(null, "Se ha inscrito de forma correcta.", "INFORMACION",
								JOptionPane.INFORMATION_MESSAGE);

						setVisible(false);
						p = new main.java.presentacion.PantallaEstudiante();
						p.setVisible(true);
					}
				} else if (metoPago.getText().equals(ModoPago.TRANSFERENCIA.toString())) {
					int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea pagar mediante transferencia bancaria?",
							"ATENCION", JOptionPane.OK_CANCEL_OPTION);
					if (respuesta == JOptionPane.OK_OPTION) {
						matricula = new Matricula(numRand(), nomAlu.getText(), apeAlu.getText(),
								ModoPago.valueOf(ModoPago.TRANSFERENCIA.toString()), fechaActual, dniAlu.getText(),
								Double.parseDouble(textPrecio.getText()), id);
						try {
							GestorMatriculacion.realizarMatriculacion(matricula);
						} catch (SQLException e1) {
							logger.info("Se ha producido un error al realizar matriculacion: " + e1.getMessage());
						}

						JOptionPane.showMessageDialog(null, "Se ha inscrito de forma correcta.", "INFORMACION",
								JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						p = new main.java.presentacion.PantallaEstudiante();
						p.setVisible(true);
					}
				}
			}
		});

		lblDNIAlu = new JLabel("DNI del alumno:\r\n");
		lblDNIAlu.setForeground(SystemColor.textHighlight);
		lblDNIAlu.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblDNIAlu.setBounds(428, 206, 259, 39);
		contentPane.add(lblDNIAlu);

		lblNomAlu = new JLabel("Nombre del alumno:");
		lblNomAlu.setForeground(SystemColor.textHighlight);
		lblNomAlu.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblNomAlu.setBounds(62, 206, 259, 39);
		contentPane.add(lblNomAlu);

		lblApellido = new JLabel("Apellido del alumno:");

		lblApellido.setForeground(SystemColor.textHighlight);
		lblApellido.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblApellido.setBounds(62, 284, 259, 39);
		contentPane.add(lblApellido);

		textPrecio = new JTextField();
		textPrecio.setEditable(false);

		textPrecio.setFont(new Font(tipoLetra, Font.BOLD, 13));
		textPrecio.setColumns(10);
		textPrecio.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		textPrecio.setBounds(232, 502, 259, 39);
		contentPane.add(textPrecio);

		lblPrecio = new JLabel("Precio a pagar:");
		lblPrecio.setForeground(SystemColor.textHighlight);
		lblPrecio.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblPrecio.setBounds(232, 453, 259, 39);
		contentPane.add(lblPrecio);

		metoPago = new JTextField();
		metoPago.setFont(new Font(tipoLetra, Font.BOLD, 13));
		metoPago.setEditable(false);
		metoPago.setColumns(10);
		metoPago.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		metoPago.setBounds(428, 313, 259, 39);
		contentPane.add(metoPago);

		JLabel lblMetoPago = new JLabel("Metodo de pago:");
		lblMetoPago.setForeground(SystemColor.textHighlight);
		lblMetoPago.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblMetoPago.setBounds(428, 284, 259, 39);
		contentPane.add(lblMetoPago);

	}

	public static String devolverNombre(String nombreCompleto) {
		String[] nombreApe = nombreCompleto.split(" ");
		return nombreApe[0];
	}

	public static String devolverApellido(String nombreCompleto) {
		String[] nombreApe = nombreCompleto.split(" ");
		return nombreApe[1];
	}

	private int numRand() {
		return random.nextInt(100) + 1;
	}

}
