
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
	import java.time.LocalDateTime;
	import java.util.Calendar;
	import java.awt.Color;
	import javax.swing.JLabel;

	import javax.swing.ImageIcon;
	import javax.swing.JTextField;
	import javax.swing.JButton;
	import java.awt.Cursor;

	import main.java.negocio.entities.Centro;
	import main.java.negocio.entities.CursoPropio;
	import main.java.negocio.entities.EstadoCurso;
	import main.java.negocio.entities.Facultad;
	import main.java.negocio.entities.TipoCurso;
	import main.java.persistencia.CursoPropioDAO;

	import java.awt.Font;
	import javax.swing.JOptionPane;
	import javax.swing.JFormattedTextField.AbstractFormatter;
	import javax.swing.JComboBox;
	import java.awt.SystemColor;
	import javax.swing.border.MatteBorder;

	import java.awt.event.KeyAdapter;

	public class PantallaVisualizarCurso extends JFrame {

		protected JPanel contentPane;
		public JTextField NombreCurso;
		public JTextField dniSec;
		public JTextField dniProf;
		public JTextField Edicion;
		public JTextField NumCreditos;
		public JTextField precio;
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
		private String fechaIni = "";
		private String fechaFin = "";
		private String ERROR = "ERROR";
		protected CursoPropio curso;
		protected CursoPropioDAO cursoDAO;

		/**
		 * Create the frame.
		 */
		TipoCurso c;
		Facultad f;
		String Num;
		private JLabel lblId;
		public JTextField id;
		public JTextField facultad;
		public JTextField FechaFin;
		public JTextField FechaIni;
		private JLabel lblFechaFin;
		public String mensaje;

		public PantallaVisualizarCurso() {
			setIconImage(Toolkit.getDefaultToolkit()
					.getImage(PantallaVisualizarCurso.class.getResource("/IMAGES/descarga.png")));
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
			lblNewLabel.setIcon(
					new ImageIcon(PantallaVisualizarCurso.class.getResource("/IMAGES/Captura de pantalla (188).png")));
			lblNewLabel.setBounds(44, 0, 310, 99);
			contentPane.add(lblNewLabel);

			NombreCurso = new JTextField();
			NombreCurso.setEditable(false);
			NombreCurso.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
			NombreCurso.setFont(new Font(tipoLetra, Font.BOLD, 13));
			NombreCurso.setBounds(454, 39, 259, 39);
			contentPane.add(NombreCurso);
			NombreCurso.setColumns(10);

			btnVolver = new JButton("Volver");
			btnVolver.setForeground(Color.WHITE);
			btnVolver.setBackground(SystemColor.textHighlight);
			btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnVolver.setFont(new Font(tipoLetra, Font.BOLD, 15));
			btnVolver.setBounds(10, 496, 114, 49);
			contentPane.add(btnVolver);
			btnVolver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					main.java.presentacion.PantallaPropuestasRealizadas p = new main.java.presentacion.PantallaPropuestasRealizadas();
					main.java.presentacion.PantallaDireccionCursos d = new main.java.presentacion.PantallaDireccionCursos();
					try {
						d.agregarCursosAlModelo(p.modelo);
						setVisible(false);
						p.setVisible(true);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				;

				}
			});

			NumCreditos = new JTextField();
			NumCreditos.setEditable(false);
			NumCreditos.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
			NumCreditos.setFont(new Font(tipoLetra, Font.BOLD, 13));
			NumCreditos.setColumns(10);
			NumCreditos.setBounds(454, 128, 259, 39);
			contentPane.add(NumCreditos);

			Edicion = new JTextField();
			Edicion.setEditable(false);
			Edicion.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
			Edicion.setFont(new Font(tipoLetra, Font.BOLD, 13));
			Edicion.setColumns(10);
			Edicion.setBounds(71, 395, 259, 39);
			contentPane.add(Edicion);

			dniProf = new JTextField();
			dniProf.setEditable(false);
			dniProf.setText(main.java.presentacion.PantallaLogin.dni.toString());
			dniProf.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
			dniProf.setFont(new Font(tipoLetra, Font.BOLD, 13));
			dniProf.setColumns(10);
			dniProf.setBounds(71, 217, 259, 39);
			contentPane.add(dniProf);

			dniSec = new JTextField();
			dniSec.setEditable(false);
			dniSec.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
			dniSec.setFont(new Font(tipoLetra, Font.BOLD, 13));
			dniSec.setColumns(10);
			dniSec.setBounds(71, 306, 259, 39);
			contentPane.add(dniSec);

			btnMen = new JButton("Mensaje");
			btnMen.setVisible(false);
			btnMen.setForeground(Color.WHITE);
			btnMen.setBackground(SystemColor.textHighlight);
			btnMen.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnMen.setFont(new Font(tipoLetra, Font.BOLD, 15));
			btnMen.setBounds(294, 496, 114, 49);
			contentPane.add(btnMen);
			contentPane.getRootPane().setDefaultButton(btnMen);
			btnMen.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					main.java.presentacion.PantallaPropuestasRealizadas p = new main.java.presentacion.PantallaPropuestasRealizadas();
					JOptionPane.showMessageDialog(null, mensaje ,
							"INFORMACION", JOptionPane.INFORMATION_MESSAGE);
				}
			});

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

			lblNmeroDeCrditos = new JLabel("N\u00FAmero de cr\u00E9ditos:");
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

			lblEdi = new JLabel("Edici\u00F3n del curso:");
			lblEdi.setForeground(SystemColor.textHighlight);
			lblEdi.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
			lblEdi.setBounds(71, 366, 259, 39);
			contentPane.add(lblEdi);

			precio = new JTextField();
			precio.setEditable(false);
			precio.setFont(new Font(tipoLetra, Font.BOLD, 13));
			precio.setColumns(10);
			precio.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
			precio.setBounds(454, 306, 259, 39);
			contentPane.add(precio);

			lblPrecio = new JLabel("Precio de la matr\u00EDcula:");
			lblPrecio.setForeground(SystemColor.textHighlight);
			lblPrecio.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
			lblPrecio.setBounds(454, 277, 259, 39);
			contentPane.add(lblPrecio);
			
			id = new JTextField();
			id.setEditable(false);
			id.setToolTipText("No se permiten caracteres num\u00E9ricos\r\n");
			id.setFont(new Font("Tahoma", Font.BOLD, 13));
			id.setColumns(10);
			id.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
			id.setBounds(71, 128, 259, 39);
			contentPane.add(id);
			
			facultad = new JTextField();
			facultad.setEditable(false);
			facultad.setFont(new Font("Tahoma", Font.BOLD, 13));
			facultad.setColumns(10);
			facultad.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
			facultad.setBounds(454, 217, 259, 39);
			contentPane.add(facultad);
			
			FechaFin = new JTextField();
			FechaFin.setEditable(false);
			FechaFin.setFont(new Font("Tahoma", Font.BOLD, 13));
			FechaFin.setColumns(10);
			FechaFin.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
			FechaFin.setBounds(454, 484, 259, 39);
			contentPane.add(FechaFin);
			
			JLabel lblFechaIni = new JLabel("Fecha de Inicio");
			lblFechaIni.setForeground(SystemColor.textHighlight);
			lblFechaIni.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
			lblFechaIni.setBounds(454, 366, 259, 39);
			contentPane.add(lblFechaIni);
			
			FechaIni = new JTextField();
			FechaIni.setEditable(false);
			FechaIni.setFont(new Font("Tahoma", Font.BOLD, 13));
			FechaIni.setColumns(10);
			FechaIni.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
			FechaIni.setBounds(454, 395, 259, 39);
			contentPane.add(FechaIni);
			
			lblFechaFin = new JLabel("Fecha de finalizaci\u00F3n");
			lblFechaFin.setForeground(SystemColor.textHighlight);
			lblFechaFin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
			lblFechaFin.setBounds(454, 455, 259, 39);
			contentPane.add(lblFechaFin);
		}

		

	

	
	}