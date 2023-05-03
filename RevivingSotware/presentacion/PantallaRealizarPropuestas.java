package presentacion;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.Color;
import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Cursor;

import negocio.entities.Facultad;
import negocio.entities.TipoCurso;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import java.awt.event.KeyAdapter;

public class PantallaRealizarPropuestas extends JFrame implements FocusListener {

	protected JPanel contentPane;
	protected JTextField NombreCurso;
	protected JTextField dniSec;
	protected JTextField dniProf;
	protected JTextField Edicion;
	protected JTextField NumCreditos;
	protected JTextField textPrecio;
	protected JButton btnNewButton;
	protected JButton btnFinalizar;
	protected JButton btnSiguiente;
	protected JButton btnNext;

	protected JLabel label;
	protected JLabel lblNewLabel;
	protected JLabel lblPrecio;
	protected JLabel lblNombreDelProfesor;
	protected JLabel lblNombreDelCurso;
	protected JLabel lblNmeroDeCrditos;
	protected JLabel lblFacultadDondeSe;
	protected JLabel lblTitulacinDelProfesor;
	protected JLabel lblDuracinDelCurso;
	protected JComboBox tipoCurso;
	protected JComboBox Fac;
	private String tipoLetra = "Tahoma";
	private String ERROR = "ERROR";

	/**
	 * Create the frame.
	 */
	TipoCurso c;
	Facultad f;
	String Num;
	private JLabel tc;

	public PantallaRealizarPropuestas() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaRealizarPropuestas.class.getResource("/IMAGES/descarga.png")));
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
		lblNewLabel.setIcon(new ImageIcon(PantallaConectar.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		NombreCurso = new JTextField();
		NombreCurso.setToolTipText("No se permiten caracteres num\u00E9ricos\r\n");
		NombreCurso.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				soloLetras(e);
			}
		});
		NombreCurso.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		NombreCurso.setFont(new Font(tipoLetra, Font.BOLD, 13));
		NombreCurso.setBounds(454, 142, 259, 39);
		contentPane.add(NombreCurso);
		NombreCurso.setColumns(10);

		btnNewButton = new JButton("Volver");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font(tipoLetra, Font.BOLD, 15));
		btnNewButton.setBounds(20, 496, 114, 49);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaDireccionCursos p = new PantallaDireccionCursos();
				setVisible(false);
				p.setVisible(true);

			}
		});

		NumCreditos = new JTextField();
		NumCreditos.setToolTipText("No se permiten caracteres alfabeticos\r\n");
		NumCreditos.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				soloNum(e);
			}
		});
		NumCreditos.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		NumCreditos.setFont(new Font(tipoLetra, Font.BOLD, 13));
		NumCreditos.setColumns(10);
		NumCreditos.setBounds(454, 220, 259, 39);
		NumCreditos.addFocusListener(this);
		contentPane.add(NumCreditos);

		Edicion = new JTextField();
		Edicion.setToolTipText("No se permiten caracteres alfabeticos\r\n\r\n");
		Edicion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				soloNum(e);

			}
		});
		Edicion.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		Edicion.setFont(new Font(tipoLetra, Font.BOLD, 13));
		Edicion.setColumns(10);
		Edicion.setBounds(454, 376, 259, 39);
		contentPane.add(Edicion);

		dniProf = new JTextField();
		dniProf.setToolTipText("Debe introducir la letra en may\u00FAscula");
		dniProf.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				comDNI(e);
			}
		});
		dniProf.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		dniProf.setFont(new Font(tipoLetra, Font.BOLD, 13));
		dniProf.setColumns(10);
		dniProf.setBounds(71, 259, 259, 39);
		contentPane.add(dniProf);

		dniSec = new JTextField();
		dniSec.setToolTipText("Debe introducir la letra en may\u00FAscula");
		dniSec.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				comDNI(e);
			}
		});
		dniSec.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
		dniSec.setFont(new Font(tipoLetra, Font.BOLD, 13));
		dniSec.setColumns(10);
		dniSec.setBounds(71, 337, 259, 39);
		contentPane.add(dniSec);

		label = new JLabel("Seleccione la fecha de inicio:");
		label.setVisible(false);
		label.setFont(new Font(tipoLetra, Font.BOLD, 20));
		label.setBounds(30, 115, 379, 42);
		contentPane.add(label);

		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFinalizar.setForeground(Color.WHITE);
		btnFinalizar.setFont(new Font(tipoLetra, Font.BOLD, 15));
		btnFinalizar.setBackground(SystemColor.textHighlight);
		btnFinalizar.setBounds(594, 490, 114, 49);
		btnFinalizar.setVisible(false);
		contentPane.add(btnFinalizar);

		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea enviar su propuesta de curso?",
						"ATENCIÓN", JOptionPane.OK_CANCEL_OPTION);
				if (respuesta == JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "Su propuesta ha sido enviada de manera correcta.",
							"INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);

				}
			}

		});

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setForeground(Color.WHITE);
		btnSiguiente.setBackground(SystemColor.textHighlight);
		btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSiguiente.setFont(new Font(tipoLetra, Font.BOLD, 15));
		btnSiguiente.setBounds(594, 490, 114, 49);
		contentPane.add(btnSiguiente);
		btnSiguiente.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Num = NumCreditos.getText();

				if (!textoVacio(Edicion) || !textoVacio(NombreCurso) || !textoVacio(dniProf) || !textoVacio(dniSec)) {
					JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos para realizar la propuesta.",
							ERROR, JOptionPane.ERROR_MESSAGE);
				} else {
					if (NumCreditos.getText().isEmpty() || !isNumeric(NumCreditos.getText()))
						JOptionPane.showMessageDialog(null, "Introduzca los cr�ditos de manera correcta.", ERROR,
								JOptionPane.ERROR_MESSAGE);
					else if (!dniDigi(dniProf)) {
						JOptionPane.showMessageDialog(null, "Introduzca el DNI del profesor con todos sus d�gitos.",
								ERROR, JOptionPane.ERROR_MESSAGE);
					} else if (!dniDigi(dniSec)) {
						JOptionPane.showMessageDialog(null, "Introduzca el DNI del secretario con todos sus d�gitos.",
								ERROR, JOptionPane.ERROR_MESSAGE);
					} else if (tipoCurso.getSelectedItem() == "") {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de curso.", ERROR,
								JOptionPane.ERROR_MESSAGE);
					} else if (Fac.getSelectedItem() == "") {
						JOptionPane.showMessageDialog(null, "Debe seleccionar una facultad.", ERROR,
								JOptionPane.ERROR_MESSAGE);
					} else {
						c = (TipoCurso) tipoCurso.getSelectedItem();
						compruebaCreditos(c);
					}
				}
			}
		});

		tipoCurso = new JComboBox();
		tipoCurso.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tipoCurso.setSelectedItem("");
		tipoCurso.addItem("");
		tipoCurso.addItem(TipoCurso.MASTER);
		tipoCurso.addItem(TipoCurso.ESPECIALISTA);
		tipoCurso.addItem(TipoCurso.EXPERTO);
		tipoCurso.addItem(TipoCurso.FORMACION_AVANZADA);
		tipoCurso.addItem(TipoCurso.FORMACION_CONTINUA);
		tipoCurso.addItem(TipoCurso.MICROCREDENCIALES);
		tipoCurso.addItem(TipoCurso.CORTA_DURACION);
		tipoCurso.setFont(new Font(tipoLetra, Font.BOLD, 13));
		tipoCurso.setBounds(71, 181, 259, 39);
		contentPane.add(tipoCurso);

		tc = new JLabel("Tipo de curso:");
		tc.setForeground(SystemColor.textHighlight);
		tc.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		tc.setBounds(71, 142, 259, 39);
		contentPane.add(tc);

		lblNombreDelProfesor = new JLabel("DNI del profesor");
		lblNombreDelProfesor.setForeground(SystemColor.textHighlight);
		lblNombreDelProfesor.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblNombreDelProfesor.setBounds(71, 230, 259, 39);
		contentPane.add(lblNombreDelProfesor);

		lblNombreDelCurso = new JLabel("Nombre del Curso:");
		lblNombreDelCurso.setForeground(SystemColor.textHighlight);
		lblNombreDelCurso.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblNombreDelCurso.setBounds(454, 113, 259, 39);
		contentPane.add(lblNombreDelCurso);

		lblNmeroDeCrditos = new JLabel("N\u00FAmero de cr\u00E9ditos:");
		lblNmeroDeCrditos.setForeground(SystemColor.textHighlight);
		lblNmeroDeCrditos.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblNmeroDeCrditos.setBounds(454, 191, 259, 39);
		contentPane.add(lblNmeroDeCrditos);

		lblFacultadDondeSe = new JLabel("Facultad donde se imparte:");
		lblFacultadDondeSe.setForeground(SystemColor.textHighlight);
		lblFacultadDondeSe.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblFacultadDondeSe.setBounds(454, 269, 259, 39);
		contentPane.add(lblFacultadDondeSe);

		lblTitulacinDelProfesor = new JLabel("Introduzca el DNI del secretario:");
		lblTitulacinDelProfesor.setForeground(SystemColor.textHighlight);
		lblTitulacinDelProfesor.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblTitulacinDelProfesor.setBounds(71, 308, 259, 39);
		contentPane.add(lblTitulacinDelProfesor);

		lblDuracinDelCurso = new JLabel("Edici\u00F3n del curso:");
		lblDuracinDelCurso.setForeground(SystemColor.textHighlight);
		lblDuracinDelCurso.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblDuracinDelCurso.setBounds(454, 347, 259, 39);
		contentPane.add(lblDuracinDelCurso);

		textPrecio = new JTextField();
		textPrecio.setEditable(false);
		textPrecio.setFont(new Font(tipoLetra, Font.BOLD, 13));
		textPrecio.setColumns(10);
		textPrecio.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		textPrecio.setBounds(232, 502, 259, 39);
		contentPane.add(textPrecio);

		lblPrecio = new JLabel("Precio de la matr\u00EDcula:");
		lblPrecio.setForeground(SystemColor.textHighlight);
		lblPrecio.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblPrecio.setBounds(232, 453, 259, 39);
		contentPane.add(lblPrecio);

		btnNext = new JButton("Siguiente");
		btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnFinalizar.setVisible(true);
				label.setText("Seleccione la fecha de fin:");

			}

		});
		btnNext.setForeground(Color.WHITE);
		btnNext.setFont(new Font(tipoLetra, Font.BOLD, 15));
		btnNext.setBackground(SystemColor.textHighlight);
		btnNext.setBounds(594, 490, 114, 49);
		contentPane.add(btnNext);

		Fac = new JComboBox();
		Fac.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Fac.setSelectedItem("");
		Fac.addItem("");
		Fac.addItem(Facultad.CAMPUS_ALBACETE);
		Fac.addItem(Facultad.CAMPUS_ALMADEN);
		Fac.addItem(Facultad.CAMPUS_CIUDAD_REAL);
		Fac.addItem(Facultad.CAMPUS_CUENCA);
		Fac.addItem(Facultad.CAMPUS_TALAVERA);
		Fac.addItem(Facultad.CAMPUS_TOLEDO);
		Fac.setFont(new Font(tipoLetra, Font.BOLD, 13));
		Fac.setBounds(454, 308, 259, 39);
		contentPane.add(Fac);

	}

	public void mostrarFechas() {
		NombreCurso.setVisible(false);
		dniSec.setVisible(false);
		dniProf.setVisible(false);
		Edicion.setVisible(false);
		NumCreditos.setVisible(false);
		textPrecio.setVisible(false);
		btnNewButton.setVisible(false);
		btnFinalizar.setVisible(false);
		btnSiguiente.setVisible(false);
		btnNext.setVisible(true);
		label.setVisible(true);
		tc.setVisible(false);
		lblPrecio.setVisible(false);
		lblNombreDelProfesor.setVisible(false);
		lblNombreDelCurso.setVisible(false);
		lblNmeroDeCrditos.setVisible(false);
		lblFacultadDondeSe.setVisible(false);
		lblTitulacinDelProfesor.setVisible(false);
		lblDuracinDelCurso.setVisible(false);
		tipoCurso.setVisible(false);
		Fac.setVisible(false);

	}

	public void compruebaCreditos(TipoCurso c) {
		int num = Integer.parseInt(Num);

		String err = "Introduzca los créditos adecuados para la modalidad elegida.";
		switch (c) {
		case MASTER:

			if (num == 60 || num == 90 || num == 120) {

				mostrarFechas();
			} else {
				JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

			}
			break;

		case ESPECIALISTA:
			if (num > 29 && num < 60) {
				mostrarFechas();
			} else {
				JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

			}
			break;
		case EXPERTO:
			if (num > 14 && num < 30) {
				mostrarFechas();

			} else {
				JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

			}
			break;
		case MICROCREDENCIALES:
			if (num > 1 && num < 15) {

				mostrarFechas();
			} else {
				JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

			}
			break;
		case FORMACION_AVANZADA:
			if (num > 14 && num < 31) {

				mostrarFechas();
			} else {
				JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

			}
			break;
		case FORMACION_CONTINUA:
			if (num > 2 && num < 15) {
				mostrarFechas();
			} else {
				JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

			}
			break;
		case CORTA_DURACION:
			if (num > 0 && num < 2) {
				mostrarFechas();
			} else {
				JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

			}
			break;
		}

	}

	private static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	public static boolean textoVacio(JTextField cuadro) {
		boolean vacio = true;
		if (cuadro.getText().equals(""))
			vacio = false;
		return vacio;
	}

	public static boolean dniDigi(JTextField cuadro) {
		boolean vacio = true;
		if (cuadro.getText().length() != 9)
			vacio = false;
		return vacio;
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == NumCreditos) {
			if (NumCreditos.getText().isBlank()) {
				NumCreditos.setText("0");
			}
			Num = NumCreditos.getText();
			String a = String.valueOf((Double.parseDouble(Num)) * 18.87);
			textPrecio.setText(a);
		}

		// TODO Auto-generated method stub

	}

	public int numRand() {
		int numero = (int) (Math.random() * 100 + 1);
		return numero;
	}

	public class DateLabelFormatter extends AbstractFormatter {

		private String datePattern = "yyyy-MM-dd";
		private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

		@Override
		public Object stringToValue(String text) throws java.text.ParseException {
			return dateFormatter.parseObject(text);
		}

		@Override
		public String valueToString(Object value) {
			if (value != null) {
				Calendar cal = (Calendar) value;
				return dateFormatter.format(cal.getTime());
			}

			return "";
		}

	}

	public static void soloLetras(KeyEvent evt) {
		if (!Character.isLetter(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_SPACE)
				&& !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
			evt.consume();
		}
	}

	public static void soloNum(KeyEvent evt) {

		if (!Character.isDigit(evt.getKeyChar()) && !(evt.getKeyChar() == KeyEvent.VK_SPACE)
				&& !(evt.getKeyChar() == KeyEvent.VK_BACK_SPACE)) {
			evt.consume();
		}

	}

	public static void comDNI(KeyEvent evt) {
		String textoActual = ((JTextField) evt.getComponent()).getText();
		int longitudActual = textoActual.length();

		if (longitudActual < 8) {
			if (!Character.isDigit(evt.getKeyChar())) {
				evt.consume();
			}
		} else if (longitudActual == 8) {
			if (!Character.isUpperCase(evt.getKeyChar())) {
				evt.consume();
			}
		} else {
			evt.consume();
		}
	}
}