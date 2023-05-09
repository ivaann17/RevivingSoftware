package main.java.presentacion;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Cursor;

import main.java.negocio.entities.Facultad;
import main.java.negocio.entities.ModoPago;
import main.java.negocio.entities.TipoCurso;
import java.awt.Font;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

public class PantallaDatosAlumno extends JFrame {

	protected JPanel contentPane;
	protected JTextField dniAlu;
	protected JTextField metoPago;
	protected JTextField nomAlu;
	protected JTextField cualiAlu;
	protected JTextField tituAlu;
	protected JTextField textPrecio;
	protected JButton btnNewButton;
	protected JButton btnPagar;

	protected JLabel lblDatosDeMatriculacin;
	protected JLabel lblNewLabel;
	protected JLabel lblPrecio;
	protected JLabel lblNomAlu;
	protected JLabel lblTitu;
	protected JLabel lblCuali;
	protected JLabel lblMetoPago;
	private String tipoLetra = "Tahoma";

	TipoCurso c;
	Facultad f;
	String Num;
	private JLabel lblDNIAlu;

	public PantallaDatosAlumno() {
		setIconImage(
				Toolkit.getDefaultToolkit().getImage(PantallaDatosAlumno.class.getResource("/IMAGES/descarga.png")));
		setTitle("UCLM\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 618);
		contentPane = new JPanel();
		contentPane.setFont(new Font(tipoLetra, Font.BOLD, 15));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel
				.setIcon(new ImageIcon(PantallaDatosAlumno.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		dniAlu = new JTextField();
		dniAlu.setEditable(false);
		dniAlu.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		dniAlu.setFont(new Font(tipoLetra, Font.BOLD, 13));
		dniAlu.setBounds(71, 181, 259, 39);
		contentPane.add(dniAlu);
		dniAlu.setColumns(10);

		btnNewButton = new JButton("Volver");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font(tipoLetra, Font.BOLD, 15));
		btnNewButton.setBounds(20, 496, 114, 49);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.java.presentacion.PantallaMatriculacion p = new main.java.presentacion.PantallaMatriculacion();
				setVisible(false);
				p.setVisible(true);

			}
		});

		tituAlu = new JTextField();
		tituAlu.setEditable(false);
		tituAlu.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		tituAlu.setFont(new Font(tipoLetra, Font.BOLD, 13));
		tituAlu.setColumns(10);
		tituAlu.setBounds(454, 220, 259, 39);
		contentPane.add(tituAlu);

		cualiAlu = new JTextField();
		cualiAlu.setEditable(false);
		cualiAlu.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		cualiAlu.setFont(new Font(tipoLetra, Font.BOLD, 13));
		cualiAlu.setColumns(10);
		cualiAlu.setBounds(454, 308, 259, 39);
		contentPane.add(cualiAlu);

		nomAlu = new JTextField();
		nomAlu.setEditable(false);
		nomAlu.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		nomAlu.setFont(new Font(tipoLetra, Font.BOLD, 13));
		nomAlu.setColumns(10);
		nomAlu.setBounds(71, 259, 259, 39);
		contentPane.add(nomAlu);

		metoPago = new JTextField();
		metoPago.setEditable(false);
		metoPago.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
		metoPago.setFont(new Font(tipoLetra, Font.BOLD, 13));
		metoPago.setColumns(10);
		metoPago.setBounds(71, 337, 259, 39);
		contentPane.add(metoPago);

		lblDatosDeMatriculacin = new JLabel("Datos de matriculaci\u00F3n:");
		lblDatosDeMatriculacin.setVisible(false);
		lblDatosDeMatriculacin.setFont(new Font(tipoLetra, Font.BOLD, 20));
		lblDatosDeMatriculacin.setBounds(248, 101, 379, 42);
		contentPane.add(lblDatosDeMatriculacin);

		btnPagar = new JButton("Pagar");
		btnPagar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPagar.setForeground(Color.WHITE);
		btnPagar.setFont(new Font(tipoLetra, Font.BOLD, 15));
		btnPagar.setBackground(SystemColor.textHighlight);
		btnPagar.setBounds(594, 490, 114, 49);
		btnPagar.setVisible(true);
		contentPane.add(btnPagar);

		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (metoPago.getText().equals(ModoPago.TARJETA_CREDITO.toString())) {

					int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea pagar con tarjeta?", "ATENCIÓN",
							JOptionPane.OK_CANCEL_OPTION);
					if (respuesta == JOptionPane.OK_OPTION) {
						JOptionPane.showMessageDialog(null, "Se ha inscrito de forma correcta.", "INFORMACIÓN",
								JOptionPane.INFORMATION_MESSAGE);

						setVisible(false);
						main.java.presentacion.PantallaEstudiante p = new main.java.presentacion.PantallaEstudiante();
						p.setVisible(true);
					}
				} else if (metoPago.getText().equals(ModoPago.TRANSFERENCIA.toString())) {
					int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea pagar mediante transferencia bancaria?",
							"ATENCIÓN", JOptionPane.OK_CANCEL_OPTION);
					if (respuesta == JOptionPane.OK_OPTION) {
						JOptionPane.showMessageDialog(null, "Se ha inscrito de forma correcta.", "INFORMACIÓN",
								JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						main.java.presentacion.PantallaEstudiante p = new main.java.presentacion.PantallaEstudiante();
						p.setVisible(true);
					}
				}
			}
		});

		lblDNIAlu = new JLabel("DNI del alumno:\r\n");
		lblDNIAlu.setForeground(SystemColor.textHighlight);
		lblDNIAlu.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblDNIAlu.setBounds(71, 142, 259, 39);
		contentPane.add(lblDNIAlu);

		lblNomAlu = new JLabel("Nombre del alumno:");
		lblNomAlu.setForeground(SystemColor.textHighlight);
		lblNomAlu.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblNomAlu.setBounds(71, 230, 259, 39);
		contentPane.add(lblNomAlu);

		lblTitu = new JLabel("Titulacion:\r\n");
		lblTitu.setForeground(SystemColor.textHighlight);
		lblTitu.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblTitu.setBounds(454, 191, 259, 39);
		contentPane.add(lblTitu);

		lblCuali = new JLabel("Cualificacion:\r\n");
		lblCuali.setForeground(SystemColor.textHighlight);
		lblCuali.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblCuali.setBounds(454, 269, 259, 39);
		contentPane.add(lblCuali);

		lblMetoPago = new JLabel("Metodo de pago:\r\n");
		lblMetoPago.setForeground(SystemColor.textHighlight);
		lblMetoPago.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblMetoPago.setBounds(71, 308, 259, 39);
		contentPane.add(lblMetoPago);

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

	}

}