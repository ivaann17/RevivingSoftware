package main.java.presentacion;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.Cursor;

import main.java.negocio.controllers.GestorEdiciones;
import main.java.negocio.controllers.GestorPropuestasCursos;
import main.java.negocio.entities.Centro;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;
import main.java.negocio.entities.Facultad;
import main.java.negocio.entities.TipoCurso;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

import java.awt.event.KeyAdapter;

public class PantallaRealizarPropuestas extends JFrame implements FocusListener {

	protected JPanel contentPane;
	protected JTextField nombreCurso;
	protected JTextField dniSec;
	protected JTextField dniProf;
	protected JTextField edicion;
	protected JTextField numCreditos;
	protected JTextField textPrecio;
	protected JButton btnNewButton;
	protected JButton btnFinalizar;
	protected JButton btnSiguiente;
	protected JButton btnNext;
	protected JLabel img;

	protected JLabel label;
	protected JLabel lblNewLabel;
	protected JLabel lblPrecio;
	protected JLabel lblNombreDelProfesor;
	protected JLabel lblNombreDelCurso;
	protected JLabel lblNmeroDeCrditos;
	protected JLabel lblFacultadDondeSe;
	protected JLabel lblTitulacinDelProfesor;
	protected JLabel lblDuracinDelCurso;
	protected JComboBox<TipoCurso> tipoCurso;
	protected JComboBox<Facultad> fac;
	protected JComboBox<Integer> dia;
	protected JComboBox<String> mes;
	protected JComboBox<Integer> ano;
	protected JLabel lblDia;
	protected JLabel lblMes;
	protected JLabel lblAno;
	private String tipoLetra = "Tahoma";
	private String fechaIni = "";
	private String fechaFin = "";
	private String error = "ERROR";
	protected CursoPropio curso;

	/**
	 * Create the frame.
	 */
	TipoCurso c;
	Facultad f;
	String nume;
	private JLabel tc;

	public PantallaRealizarPropuestas() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaRealizarPropuestas.class.getResource("/IMAGES/descarga.png")));
		setTitle("UCLM\r\n");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 618);
		contentPane = new JPanel();
		contentPane.setFont(new Font(tipoLetra, Font.BOLD, 15));
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		img = new JLabel("");
		img.setIcon(
				new ImageIcon(PantallaRealizarPropuestas.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		img.setBounds(44, 10, 310, 99);
		contentPane.add(img);

		nombreCurso = new JTextField();
		nombreCurso.setToolTipText("No se permiten caracteres numericos");
		nombreCurso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloLetras(e);
			}
		});
		nombreCurso.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		nombreCurso.setFont(new Font(tipoLetra, Font.BOLD, 13));
		nombreCurso.setBounds(454, 142, 259, 39);
		contentPane.add(nombreCurso);
		nombreCurso.setColumns(10);

		btnNewButton = new JButton("Volver");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnNewButton.setBounds(20, 496, 114, 49);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaDireccionCursos p = new PantallaDireccionCursos();
				setVisible(false);
				p.setVisible(true);

			}
		});

		numCreditos = new JTextField();
		numCreditos.setToolTipText("No se permiten caracteres alfabeticos\r\n");
		numCreditos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNum(e);
			}
		});
		numCreditos.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		numCreditos.setFont(new Font(tipoLetra, Font.BOLD, 13));
		numCreditos.setColumns(10);
		numCreditos.setBounds(454, 220, 259, 39);
		numCreditos.addFocusListener(this);
		contentPane.add(numCreditos);

		edicion = new JTextField();
		edicion.setToolTipText("No se permiten caracteres alfabeticos\r\n\r\n");
		edicion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				soloNum(e);

			}
		});
		edicion.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		edicion.setFont(new Font(tipoLetra, Font.BOLD, 13));
		edicion.setColumns(10);
		edicion.setBounds(454, 376, 259, 39);
		contentPane.add(edicion);

		dniProf = new JTextField();
		dniProf.setEditable(false);
		dniProf.setText(main.java.presentacion.PantallaLogin.dni.toString());
		dniProf.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		dniProf.setFont(new Font(tipoLetra, Font.BOLD, 13));
		dniProf.setColumns(10);
		dniProf.setBounds(71, 259, 259, 39);
		contentPane.add(dniProf);

		dniSec = new JTextField();
		dniSec.setToolTipText("Debe introducir la letra en mayuscula");
		dniSec.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				comDNI(e);
			}
		});
		dniSec.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
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
		btnFinalizar.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnFinalizar.setBackground(SystemColor.textHighlight);
		btnFinalizar.setBounds(594, 490, 114, 49);
		btnFinalizar.setVisible(false);
		contentPane.add(btnFinalizar);

		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mes.getSelectedItem().toString().equals("") || dia.getSelectedItem().toString().equals("")
						|| ano.getSelectedItem().toString().equals(""))
					JOptionPane.showMessageDialog(null, "Rellene correctamente las fechas del curso.", error,
							JOptionPane.ERROR_MESSAGE);
				else {
					fechaFin = ano.getSelectedItem().toString() + "-" + mesNumero(mes.getSelectedItem().toString())
							+ "-" + dia.getSelectedItem().toString();
					try {

						if (!compararFechas(formatoFecha(fechaIni), formatoFecha(fechaFin),
								new Date(System.currentTimeMillis()))) {
							JOptionPane.showMessageDialog(null,
									"Debe introducir las fechas de forma correcta (posterior a la fecha actual y posterior a la de inicio).",
									error, JOptionPane.ERROR_MESSAGE);
							mostrarForm();

						} else {
							Centro cent = new Centro(fac.getSelectedItem().toString());

							curso = new CursoPropio(cent.getNombre().toString(), EstadoCurso.PROPUESTO,
									TipoCurso.valueOf(tipoCurso.getSelectedItem().toString()), dniProf.getText(),
									dniSec.getText(), numRand(), nombreCurso.getText(),
									Integer.parseInt(numCreditos.getText()), formatoFecha(fechaIni),
									formatoFecha(fechaFin), Double.parseDouble(textPrecio.getText()),
									Integer.parseInt(edicion.getText()), "");
							boolean existeEdicion;
							existeEdicion = GestorEdiciones.existeEdicion(curso.getEdicion(), curso.getNombre());
							if (!existeEdicion) {
								int respuesta = JOptionPane.showConfirmDialog(null,
										"¿Desea enviar su propuesta de curso?", "ATENCIO“N",
										JOptionPane.OK_CANCEL_OPTION);
								if (respuesta == JOptionPane.OK_OPTION) {
									JOptionPane.showMessageDialog(null,
											"Su propuesta ha sido enviada de manera correcta.", "INFORMACION",
											JOptionPane.INFORMATION_MESSAGE);
									GestorPropuestasCursos.realizarPropuestaCurso(curso);
									GestorEdiciones.crearEdicion(numRand(), curso.getNombre(), curso.getEdicion(),
											curso.getId());
									main.java.presentacion.PantallaDireccionCursos p = new main.java.presentacion.PantallaDireccionCursos();
									setVisible(false);
									p.setVisible(true);
								}
							} else {
								JOptionPane.showMessageDialog(null, "La edición ya existe en la base de datos.", error,
										JOptionPane.ERROR_MESSAGE);
								main.java.presentacion.PantallaDireccionCursos p = new main.java.presentacion.PantallaDireccionCursos();
								setVisible(false);
								p.setVisible(true);
							}
						}

					} catch (Exception e1) {
	
					}

				}
			}

		});

		btnSiguiente = new JButton("Siguiente");
		btnSiguiente.setForeground(Color.WHITE);
		btnSiguiente.setBackground(SystemColor.textHighlight);
		btnSiguiente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSiguiente.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnSiguiente.setBounds(594, 490, 114, 49);
		contentPane.add(btnSiguiente);
		contentPane.getRootPane().setDefaultButton(btnSiguiente);
		btnSiguiente.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				nume = numCreditos.getText();

				if (!textoVacio(edicion) || !textoVacio(nombreCurso) || !textoVacio(dniProf) || !textoVacio(dniSec)) {
					JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos para realizar la propuesta.",
							error, JOptionPane.ERROR_MESSAGE);
				} else {
					if (numCreditos.getText().isEmpty() || !isNumeric(numCreditos.getText()))
						JOptionPane.showMessageDialog(null, "Introduzca los creditos de manera correcta.", error,
								JOptionPane.ERROR_MESSAGE);

					else if (!dniDigi(dniSec)) {
						JOptionPane.showMessageDialog(null, "Introduzca el DNI del secretario con todos sus digitos.",
								error, JOptionPane.ERROR_MESSAGE);
					} else if (tipoCurso.getSelectedItem() == "") {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un tipo de curso.", error,
								JOptionPane.ERROR_MESSAGE);
					} else if (fac.getSelectedItem() == "") {
						JOptionPane.showMessageDialog(null, "Debe seleccionar una facultad.", error,
								JOptionPane.ERROR_MESSAGE);
					} else {
						c = (TipoCurso) tipoCurso.getSelectedItem();
						compruebaCreditos(c);

					}
				}
			}
		});

		tipoCurso = new JComboBox<>();
		tipoCurso.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tipoCurso.setSelectedItem("");
		DefaultComboBoxModel<TipoCurso> comboBoxModel = new DefaultComboBoxModel<>();

		comboBoxModel.addElement(null);

		comboBoxModel.addElement(TipoCurso.MASTER);
		comboBoxModel.addElement(TipoCurso.ESPECIALISTA);
		comboBoxModel.addElement(TipoCurso.EXPERTO);
		comboBoxModel.addElement(TipoCurso.FORMACION_AVANZADA);
		comboBoxModel.addElement(TipoCurso.FORMACION_CONTINUA);
		comboBoxModel.addElement(TipoCurso.MICROCREDENCIALES);
		comboBoxModel.addElement(TipoCurso.CORTA_DURACION);
		tipoCurso.setModel(comboBoxModel);
		tipoCurso.setFont(new Font(tipoLetra, Font.BOLD, 13));
		tipoCurso.setBounds(71, 181, 259, 39);
		contentPane.add(tipoCurso);
		tipoCurso.setSelectedItem(null);

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

		lblNmeroDeCrditos = new JLabel("Numero de creditos:");
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

		lblDuracinDelCurso = new JLabel("Edicion del curso:");
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

		lblPrecio = new JLabel("Precio de la matricula:");
		lblPrecio.setForeground(SystemColor.textHighlight);
		lblPrecio.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblPrecio.setBounds(232, 453, 259, 39);
		contentPane.add(lblPrecio);

		btnNext = new JButton("Siguiente");
		btnNext.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mes.getSelectedItem().toString().equals("") || dia.getSelectedItem().toString().equals("")
						|| ano.getSelectedItem().toString().equals(""))
					JOptionPane.showMessageDialog(null, "Rellene correctamente las fechas del curso.", error,
							JOptionPane.ERROR_MESSAGE);
				else {
					btnFinalizar.setVisible(true);
					btnNext.setVisible(false);
					fechaIni = ano.getSelectedItem().toString() + "-" + mesNumero(mes.getSelectedItem().toString())
							+ "-" + dia.getSelectedItem().toString();
					label.setText("Seleccione la fecha de fin:");

					mes.setSelectedItem("");
					dia.setSelectedItem("");
					ano.setSelectedItem("");
					contentPane.getRootPane().setDefaultButton(btnFinalizar);
				}
			}

		});
		btnNext.setForeground(Color.WHITE);
		btnNext.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnNext.setBackground(SystemColor.textHighlight);
		btnNext.setBounds(594, 490, 114, 49);
		contentPane.add(btnNext);

		fac = new JComboBox<>();
		fac.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		DefaultComboBoxModel<Facultad> comboBoxModelFac = new DefaultComboBoxModel<>();

		comboBoxModelFac.addElement(null);
		comboBoxModelFac.addElement(Facultad.CAMPUS_ALBACETE);
		comboBoxModelFac.addElement(Facultad.CAMPUS_ALMADEN);
		comboBoxModelFac.addElement(Facultad.CAMPUS_CIUDAD_REAL);
		comboBoxModelFac.addElement(Facultad.CAMPUS_CUENCA);
		comboBoxModelFac.addElement(Facultad.CAMPUS_TALAVERA);
		comboBoxModelFac.addElement(Facultad.CAMPUS_TOLEDO);
		fac.setFont(new Font(tipoLetra, Font.BOLD, 13));
		fac.setBounds(454, 308, 259, 39);
		fac.setModel(comboBoxModelFac);
		contentPane.add(fac);
		fac.setSelectedItem(null);

		dia = new JComboBox<>();
		dia.setFont(new Font(tipoLetra, Font.BOLD, 13));
		dia.setBounds(71, 230, 101, 39);
		dia.setVisible(false);
		dia.addItem(null);
		for (int a = 1; a <= 31; a++) {
			dia.addItem(a);
		}
		contentPane.add(dia);

		mes = new JComboBox<>();
		mes.setFont(new Font(tipoLetra, Font.BOLD, 13));
		mes.setBounds(263, 230, 181, 39);
		mes.setVisible(false);
		mes.setSelectedItem("");
		mes.addItem("");
		mes.addItem("Enero");
		mes.addItem("Febrero");
		mes.addItem("Marzo");
		mes.addItem("Abril");
		mes.addItem("Mayo");
		mes.addItem("Junio");
		mes.addItem("Julio");
		mes.addItem("Agosto");
		mes.addItem("Septiembre");
		mes.addItem("Octubre");
		mes.addItem("Noviembre");
		mes.addItem("Diciembre");
		contentPane.add(mes);

		ano = new JComboBox<>();
		ano.setFont(new Font(tipoLetra, Font.BOLD, 13));
		ano.setBounds(550, 230, 114, 39);
		ano.setVisible(false);
		Calendar cal = Calendar.getInstance();
		int actual = cal.get(Calendar.YEAR);

		DefaultComboBoxModel<Integer> comboBoxModelAno = new DefaultComboBoxModel<>();

		comboBoxModelAno.addElement(null);
		for (int b = 1; b <= 10; b++) {
			comboBoxModelAno.addElement(actual);
			actual++;
		}

		ano.setModel(comboBoxModelAno);

		contentPane.add(ano);

		lblDia = new JLabel("Dia:");
		lblDia.setForeground(SystemColor.textHighlight);
		lblDia.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblDia.setBounds(71, 181, 259, 39);
		lblDia.setVisible(false);
		contentPane.add(lblDia);

		lblMes = new JLabel("Mes:");
		lblMes.setForeground(SystemColor.textHighlight);
		lblMes.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblMes.setBounds(263, 181, 259, 39);
		lblMes.setVisible(false);
		contentPane.add(lblMes);

		lblAno = new JLabel("Año:");
		lblAno.setForeground(SystemColor.textHighlight);
		lblAno.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblAno.setBounds(548, 195, 259, 39);
		lblAno.setVisible(false);
		contentPane.add(lblAno);

	}

	public void mostrarFechas() {
		nombreCurso.setVisible(false);
		dniSec.setVisible(false);
		dniProf.setVisible(false);
		edicion.setVisible(false);
		numCreditos.setVisible(false);
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
		fac.setVisible(false);
		dia.setVisible(true);
		mes.setVisible(true);
		ano.setVisible(true);
		lblDia.setVisible(true);
		lblMes.setVisible(true);
		lblAno.setVisible(true);
		contentPane.getRootPane().setDefaultButton(btnNext);

	}

	public void mostrarForm() {
		nombreCurso.setVisible(true);
		dniSec.setVisible(true);
		dniProf.setVisible(true);
		edicion.setVisible(true);
		numCreditos.setVisible(true);
		textPrecio.setVisible(true);
		btnNewButton.setVisible(true);
		btnFinalizar.setVisible(false);
		btnSiguiente.setVisible(true);
		btnNext.setVisible(false);
		label.setVisible(false);
		tc.setVisible(true);
		lblPrecio.setVisible(true);
		lblNombreDelProfesor.setVisible(true);
		lblNombreDelCurso.setVisible(true);
		lblNmeroDeCrditos.setVisible(true);
		lblFacultadDondeSe.setVisible(true);
		lblTitulacinDelProfesor.setVisible(true);
		lblDuracinDelCurso.setVisible(true);
		tipoCurso.setVisible(true);
		fac.setVisible(true);
		dia.setVisible(false);
		mes.setVisible(false);
		ano.setVisible(false);
		lblDia.setVisible(false);
		lblMes.setVisible(false);
		lblAno.setVisible(false);
		contentPane.getRootPane().setDefaultButton(btnSiguiente);
		mes.setSelectedItem("");
		dia.setSelectedItem("");
		ano.setSelectedItem("");

	}

	public void compruebaCreditos(TipoCurso c) {
		int num = Integer.parseInt(nume);

		String err = "Introduzca los creditos adecuados para la modalidad elegida.";
		switch (c) {
		case MASTER:

			if (num == 60 || num == 90 || num == 120) {

				mostrarFechas();
			} else {
				JOptionPane.showMessageDialog(null, err, error, JOptionPane.ERROR_MESSAGE);

			}
			break;

		case ESPECIALISTA:
			if (num > 29 && num < 60) {
				mostrarFechas();
			} else {
				JOptionPane.showMessageDialog(null, err, error, JOptionPane.ERROR_MESSAGE);

			}
			break;
		case EXPERTO:
			if (num > 14 && num < 30) {
				mostrarFechas();

			} else {
				JOptionPane.showMessageDialog(null, err, error, JOptionPane.ERROR_MESSAGE);

			}
			break;
		case MICROCREDENCIALES:
			if (num > 1 && num < 15) {

				mostrarFechas();
			} else {
				JOptionPane.showMessageDialog(null, err, error, JOptionPane.ERROR_MESSAGE);

			}
			break;
		case FORMACION_AVANZADA:
			if (num > 14 && num < 31) {

				mostrarFechas();
			} else {
				JOptionPane.showMessageDialog(null, err, error, JOptionPane.ERROR_MESSAGE);

			}
			break;
		case FORMACION_CONTINUA:
			if (num > 2 && num < 15) {
				mostrarFechas();
			} else {
				JOptionPane.showMessageDialog(null, err, error, JOptionPane.ERROR_MESSAGE);

			}
			break;
		case CORTA_DURACION:
			if (num > 0 && num < 2) {
				mostrarFechas();
			} else {
				JOptionPane.showMessageDialog(null, err, error, JOptionPane.ERROR_MESSAGE);

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

	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == numCreditos) {
			if (numCreditos.getText().isBlank()) {
				numCreditos.setText("0");
			}
			nume = numCreditos.getText();
			String a = String.valueOf((Double.parseDouble(nume)) * 18.87);
			textPrecio.setText(a);
		}

	}

	public int numRand() {
		int numero = (int) (Math.random() * 100 + 1);
		return numero;
	}

	public static void soloLetras(KeyEvent evt) {
		if (!Character.isLetter(evt.getKeyChar()) && evt.getKeyChar() != KeyEvent.VK_SPACE
		        && evt.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
		    evt.consume();
		}
	}

	public static void soloNum(KeyEvent evt) {
		if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != KeyEvent.VK_SPACE
				&& evt.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
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

	public String mesNumero(String c) {
		switch (c) {
		case "Enero":
			return "01";
		case "Febrero":
			return "02";
		case "Marzo":
			return "03";
		case "Abril":
			return "04";
		case "Mayo":
			return "05";
		case "Junio":
			return "06";
		case "Julio":
			return "07";
		case "Agosto":
			return "08";
		case "Septiembre":
			return "09";
		case "Octubre":
			return "10";
		case "Noviembre":
			return "11";
		case "Diciembre":
			return "12";
		default:
			return "";
		}
	}

	public boolean compararFechas(java.util.Date fecha1, java.util.Date fecha2, java.util.Date fecha3)
			throws Exception {
		if (fecha2.before(fecha1)) {
			return false;
		} else if (fecha1.equals(fecha2)) {
			return false;
		} else if (fecha1.before(fecha3) || fecha2.before(fecha3)) {
			return false;
		} else {
			return true;
		}

	}

	public java.util.Date formatoFecha(String fech) throws ParseException {
		try {

			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date fecha = formatoFecha.parse(fech);
			return fecha;
		} catch (Exception e) {
			System.out.println("Error al convertir la fecha: " + e.getMessage());
		}
		return null;
	}

}