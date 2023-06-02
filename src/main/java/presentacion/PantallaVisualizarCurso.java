
package main.java.presentacion;

import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.util.logging.Logger;
import java.awt.Color;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.Cursor;

import main.java.negocio.controllers.GestorConsultas;

import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;
import main.java.negocio.entities.Facultad;
import main.java.negocio.entities.TipoCurso;

import java.awt.Font;
import javax.swing.JOptionPane;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

public class PantallaVisualizarCurso extends JFrame {

	protected JPanel contentPane;
	protected static final JTextField nombreCurso = new JTextField();
	protected static final JTextField dniSec = new JTextField();
	protected static final JTextField dniProf = new JTextField();
	protected static final JTextField edicion = new JTextField();
	protected static final JTextField numCreditos = new JTextField();
	protected static final JTextField precio = new JTextField();
	protected JButton btnVolver2;
	protected JButton btnVolver;
	protected JButton btnMen;
	protected JLabel lblNewLabel;
	protected JLabel lblPrecio;
	protected JLabel lblDNIPro;
	protected JLabel lblNombreDelCurso;
	protected JLabel lblNmeroDeCrditos;
	protected JLabel lblFac;
	protected JLabel lblDNISec;
	protected JLabel lblEdi;
	private String tipoLetra = "Tahoma";
	private String volverText = "Volver";
	private String error = "Se ha producido un error: ";
	private static final Logger logger = Logger.getLogger(PantallaVisualizarCurso.class.getName());

	protected transient CursoPropio curso;

	TipoCurso c;
	Facultad f;
	String num;
	private JLabel lblId;
	public static final JTextField id = new JTextField();
	public static final JTextField facultad = new JTextField();
	public static final JTextField fechaFin = new JTextField();
	public static final JTextField fechaIni = new JTextField();
	private JLabel lblFechaFin;
	public static String mensaje = "";

	protected JButton btnVolver3;

	public PantallaVisualizarCurso() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaVisualizarCurso.class.getResource("/resources/IMAGES/descarga.png")));
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
		lblNewLabel.setIcon(
				new ImageIcon(PantallaVisualizarCurso.class.getResource("/resources/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 0, 310, 99);
		contentPane.add(lblNewLabel);

		nombreCurso.setEditable(false);
		nombreCurso.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		nombreCurso.setFont(new Font(tipoLetra, Font.BOLD, 13));
		nombreCurso.setBounds(454, 39, 259, 39);
		contentPane.add(nombreCurso);
		nombreCurso.setColumns(10);

		btnVolver = new JButton(volverText);
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setBackground(SystemColor.textHighlight);
		btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVolver.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnVolver.setBounds(10, 496, 114, 49);
		contentPane.add(btnVolver);
		btnVolver.addActionListener(event -> {

			main.java.presentacion.PantallaPropuestasRealizadas p = new main.java.presentacion.PantallaPropuestasRealizadas();
			try {
				GestorConsultas.listarHistorial(p.modelo, dniProf.getText());
				setVisible(false);
				p.setVisible(true);
			} catch (Exception e1) {
				logger.info(error + e1.getMessage());
			}

		});

		btnVolver2 = new JButton(volverText);
		btnVolver2.setForeground(Color.WHITE);
		btnVolver2.setVisible(false);
		btnVolver2.setBackground(SystemColor.textHighlight);
		btnVolver2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVolver2.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnVolver2.setBounds(10, 496, 114, 49);
		contentPane.add(btnVolver2);
		btnVolver2.addActionListener(event -> {

			main.java.presentacion.PantallaPropuestasRechazadas p = new main.java.presentacion.PantallaPropuestasRechazadas();
			try {
				GestorConsultas.listarCursosPorEstado(p.modelo, EstadoCurso.PROPUESTA_RECHAZADA);
				setVisible(false);
				p.setVisible(true);
			} catch (Exception e1) {
				logger.info(error + e1.getMessage());
			}

		});

		numCreditos.setEditable(false);
		numCreditos.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		numCreditos.setFont(new Font(tipoLetra, Font.BOLD, 13));
		numCreditos.setColumns(10);
		numCreditos.setBounds(454, 128, 259, 39);
		contentPane.add(numCreditos);

		edicion.setEditable(false);
		edicion.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		edicion.setFont(new Font(tipoLetra, Font.BOLD, 13));
		edicion.setColumns(10);
		edicion.setBounds(71, 395, 259, 39);
		contentPane.add(edicion);

		dniProf.setEditable(false);
		dniProf.setText(main.java.presentacion.PantallaLogin.dni);
		dniProf.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		dniProf.setFont(new Font(tipoLetra, Font.BOLD, 13));
		dniProf.setColumns(10);
		dniProf.setBounds(71, 217, 259, 39);
		contentPane.add(dniProf);

		dniSec.setEditable(false);
		dniSec.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		dniSec.setFont(new Font(tipoLetra, Font.BOLD, 13));
		dniSec.setColumns(10);
		dniSec.setBounds(71, 306, 259, 39);
		contentPane.add(dniSec);

		btnMen = new JButton("Mensaje");
		btnMen.setVisible(false);
		btnMen.setForeground(Color.WHITE);
		btnMen.setBackground(SystemColor.textHighlight);
		btnMen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMen.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnMen.setBounds(294, 496, 114, 49);
		contentPane.add(btnMen);
		contentPane.getRootPane().setDefaultButton(btnMen);
		btnMen.addActionListener(event ->

		JOptionPane.showMessageDialog(null, mensaje, "MENSAJE", JOptionPane.INFORMATION_MESSAGE)

		);

		lblId = new JLabel("ID del curso:");
		lblId.setForeground(SystemColor.textHighlight);
		lblId.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblId.setBounds(71, 99, 259, 39);
		contentPane.add(lblId);

		lblDNIPro = new JLabel("DNI del profesor");
		lblDNIPro.setForeground(SystemColor.textHighlight);
		lblDNIPro.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblDNIPro.setBounds(71, 188, 259, 39);
		contentPane.add(lblDNIPro);

		lblNombreDelCurso = new JLabel("Nombre del Curso:");
		lblNombreDelCurso.setForeground(SystemColor.textHighlight);
		lblNombreDelCurso.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblNombreDelCurso.setBounds(454, 10, 259, 39);
		contentPane.add(lblNombreDelCurso);

		lblNmeroDeCrditos = new JLabel("Numero de creditos:");
		lblNmeroDeCrditos.setForeground(SystemColor.textHighlight);
		lblNmeroDeCrditos.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblNmeroDeCrditos.setBounds(454, 99, 259, 39);
		contentPane.add(lblNmeroDeCrditos);

		lblFac = new JLabel("Facultad donde se imparte:");
		lblFac.setForeground(SystemColor.textHighlight);
		lblFac.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblFac.setBounds(454, 188, 259, 39);
		contentPane.add(lblFac);

		lblDNISec = new JLabel("DNI del secretario:");
		lblDNISec.setForeground(SystemColor.textHighlight);
		lblDNISec.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblDNISec.setBounds(71, 277, 259, 39);
		contentPane.add(lblDNISec);

		lblEdi = new JLabel("Edicion del curso:");
		lblEdi.setForeground(SystemColor.textHighlight);
		lblEdi.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblEdi.setBounds(71, 366, 259, 39);
		contentPane.add(lblEdi);

		precio.setEditable(false);
		precio.setFont(new Font(tipoLetra, Font.BOLD, 13));
		precio.setColumns(10);
		precio.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		precio.setBounds(454, 306, 259, 39);
		contentPane.add(precio);

		lblPrecio = new JLabel("Precio de la matricula:");
		lblPrecio.setForeground(SystemColor.textHighlight);
		lblPrecio.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblPrecio.setBounds(454, 277, 259, 39);
		contentPane.add(lblPrecio);

		id.setEditable(false);
		id.setToolTipText("No se permiten caracteres numericos\r\n");
		id.setFont(new Font(tipoLetra, Font.BOLD, 13));
		id.setColumns(10);
		id.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		id.setBounds(71, 128, 259, 39);
		contentPane.add(id);

		facultad.setEditable(false);
		facultad.setFont(new Font(tipoLetra, Font.BOLD, 13));
		facultad.setColumns(10);
		facultad.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		facultad.setBounds(454, 217, 259, 39);
		contentPane.add(facultad);

		fechaFin.setEditable(false);
		fechaFin.setFont(new Font(tipoLetra, Font.BOLD, 13));
		fechaFin.setColumns(10);
		fechaFin.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		fechaFin.setBounds(454, 484, 259, 39);
		contentPane.add(fechaFin);

		JLabel lblFechaIni = new JLabel("Fecha de Inicio");
		lblFechaIni.setForeground(SystemColor.textHighlight);
		lblFechaIni.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblFechaIni.setBounds(454, 366, 259, 39);
		contentPane.add(lblFechaIni);

		fechaIni.setEditable(false);
		fechaIni.setFont(new Font(tipoLetra, Font.BOLD, 13));
		fechaIni.setColumns(10);
		fechaIni.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		fechaIni.setBounds(454, 395, 259, 39);
		contentPane.add(fechaIni);

		lblFechaFin = new JLabel("Fecha de finalizacion");
		lblFechaFin.setForeground(SystemColor.textHighlight);
		lblFechaFin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblFechaFin.setBounds(454, 455, 259, 39);
		contentPane.add(lblFechaFin);

		btnVolver3 = new JButton(volverText);
		btnVolver3.setVisible(false);
		btnVolver3.setForeground(Color.WHITE);
		btnVolver3.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnVolver3.setBackground(SystemColor.textHighlight);
		btnVolver3.setBounds(10, 496, 114, 49);
		contentPane.add(btnVolver3);
		btnVolver3.addActionListener(event -> {

			main.java.presentacion.PantallaMisCursos p = new main.java.presentacion.PantallaMisCursos();
			try {
				GestorConsultas.listarCursosMatriculados(PantallaMisCursos.modelo, main.java.presentacion.PantallaLogin.dni);
				setVisible(false);
				p.setVisible(true);
			} catch (Exception e1) {
				logger.info(error + e1.getMessage());
			}

		});
	}

}