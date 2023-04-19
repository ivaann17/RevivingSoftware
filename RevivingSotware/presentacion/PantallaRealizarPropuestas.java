package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.Vector;
import java.awt.Color;
import javax.swing.JLabel;
//import com.jgoodies.forms.factories.DefaultComponentFactory;


import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.border.SoftBevelBorder;


import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import java.awt.Panel;

public class PantallaRealizarPropuestas extends JFrame implements FocusListener{

	protected JPanel contentPane;
	protected JTextField NombreCurso;
	protected JTextField CategoriaProf;
	protected JTextField NombreProf;
	protected JTextField Edicion;
	protected JTextField Facultad; 
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
	protected JComboBox comboBox;
	private String tipoLetra= "Tahoma";
	private String ERROR= "ERROR";



	/**
	 * Create the frame.
	 */
	 String c="";
	String Num;
	private JLabel tc;
	
	public PantallaRealizarPropuestas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaRealizarPropuestas.class.getResource("/IMAGES/descarga.png")));
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
				

				}
		});
		
		NumCreditos  = new JTextField ();
		NumCreditos.setBorder(new MatteBorder(0, 0, 1, 0, SystemColor.textHighlight));
		NumCreditos.setFont(new Font(tipoLetra, Font.BOLD, 13));
		NumCreditos.setColumns(10);
		NumCreditos.setBounds(454, 220, 259, 39);
		NumCreditos.addFocusListener(this);
		contentPane.add(NumCreditos);
		
		
		

		
		
		Facultad = new JTextField();
		Facultad.setBorder(new MatteBorder(0, 0, 1, 0,  SystemColor.textHighlight));
		Facultad.setFont(new Font(tipoLetra, Font.BOLD, 13));
		Facultad.setColumns(10);
		Facultad.setBounds(454, 298, 259, 39);
		contentPane.add(Facultad);
		
		Edicion = new JTextField();
		Edicion.setBorder(new MatteBorder(0, 0, 1, 0,  SystemColor.textHighlight));
		Edicion.setFont(new Font(tipoLetra, Font.BOLD, 13));
		Edicion.setColumns(10);
		Edicion.setBounds(454, 376, 259, 39);
		contentPane.add(Edicion);
		
		NombreProf = new JTextField();
		NombreProf.setBorder(new MatteBorder(0, 0, 1, 0,  new Color(0, 120, 215)));
		NombreProf.setFont(new Font(tipoLetra, Font.BOLD, 13));
		NombreProf.setColumns(10);
		NombreProf.setBounds(71, 259, 259, 39);
		contentPane.add(NombreProf);

		
		CategoriaProf = new JTextField();
		CategoriaProf.setBorder(new MatteBorder(0, 0, 1, 0, (Color) SystemColor.textHighlight));
		CategoriaProf.setFont(new Font(tipoLetra, Font.BOLD, 13));
		CategoriaProf.setColumns(10);
		CategoriaProf.setBounds(71, 337, 259, 39);
		contentPane.add(CategoriaProf);
		

		

		
		label = new JLabel("Seleccione la fecha de inicio:");
		label.hide();
		label.setFont(new Font(tipoLetra, Font.BOLD, 20));
		label.setBounds(30, 115, 379, 42);
		contentPane.add(label);
		
		
		
		btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnFinalizar.setForeground(Color.WHITE);
		btnFinalizar.setFont(new Font(tipoLetra, Font.BOLD, 15));
		btnFinalizar.setBackground(SystemColor.textHighlight);
		btnFinalizar.setBounds(594, 490, 114, 49);
		btnFinalizar.hide();
		contentPane.add(btnFinalizar);
		
		btnFinalizar.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					
				
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea enviar su propuesta de curso?", "ATENCIÓN", JOptionPane.OK_CANCEL_OPTION);
				if (respuesta == JOptionPane.OK_OPTION) {
					JOptionPane.showMessageDialog(null, "Su propuesta ha sido enviada de manera correcta.", "INFORMACIÓN", JOptionPane.INFORMATION_MESSAGE);


				
				
				}}
			
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
				
				Num=NumCreditos.getText();
				
					if (!textoVacio(Facultad)||!textoVacio(Edicion) || !textoVacio(NombreCurso) || !textoVacio(NombreProf)||!textoVacio(CategoriaProf) ) {
						JOptionPane.showMessageDialog(null, "Debe rellenar todos los campos para realizar la propuesta.", ERROR, JOptionPane.ERROR_MESSAGE);}
					else {
				if (NumCreditos.getText().isEmpty()|| !isNumeric(NumCreditos.getText()))
					JOptionPane.showMessageDialog(null, "Introduzca los créditos de manera correcta.", ERROR, JOptionPane.ERROR_MESSAGE);
				else {
				compruebaCreditos(c);
			
				
				}}}});
		
		comboBox = new JComboBox();
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 comboBox.addItem("Master de Formación Permanente");
		 comboBox.addItem("Especialización");
		 comboBox.addItem("Diploma de Experto");
		 comboBox.addItem("Curso de Formacion Avanzada");
		 comboBox.addItem("Curso de Formacion Continua");
		 comboBox.addItem("Microcredenciales");
		 comboBox.addItem("Actividades de Corta Duración");
		 comboBox.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
				c= comboBox.getSelectedItem().toString();

					}
					
			});
		comboBox.setFont(new Font(tipoLetra, Font.BOLD, 13));
		comboBox.setBounds(71, 181, 259, 39);
		contentPane.add(comboBox);
		
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
				

					btnFinalizar.show();
				label.setText("Seleccione la fecha de fin:");

				}
			
		});
		btnNext.setForeground(Color.WHITE);
		btnNext.setFont(new Font(tipoLetra, Font.BOLD, 15));
		btnNext.setBackground(SystemColor.textHighlight);
		btnNext.setBounds(594, 490, 114, 49);
		contentPane.add(btnNext);
	}
    public void mostrarFechas() {
    	NombreCurso.setVisible(false);
    	CategoriaProf.setVisible(false);
    	NombreProf.setVisible(false);
    	Edicion.setVisible(false);
    	Facultad.setVisible(false);
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
    	comboBox.setVisible(false);
    	
    	
    }
	public void compruebaCreditos (String c) {
		int num = Integer.parseInt(Num);
		String err="Introduzca los créditos adecuados para la modalidad elegida.";
		switch (c) { 
	    case "Master de Formaci�n Permanente":
	    
	    	if (num ==60 || num ==90 || num ==120) {

mostrarFechas();
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

	    	} 
	    	break;
	    	
	    case "Especializaci�n":
	    	if (num >29 && num<60) {
	    		mostrarFechas();
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, err , ERROR, JOptionPane.ERROR_MESSAGE);

	    	} 
	    	break;
	    case "Diploma de Experto":
	    	if (num >14 && num<30) {
	    		mostrarFechas();
	   
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

	    	} 
	    	break;
	    case "Microcredenciales":
	    	if (num >1 && num<15) {
	    		
	    		mostrarFechas();
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

	    	} 
	    	break;
	    case "Curso de Formacion Avanzada":
	    	if (num >14 && num<31) {
	    	
	    		mostrarFechas();
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

	    	} 
	    	break;
	    case "Curso de Formacion Continua":
	    	if (num >2 && num<15) {
	    		mostrarFechas();
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

	    	} 
	    	break;
	    case "Actividades de Corta Duraci�n":
	    	if (num >0 && num<2) {
	    		mostrarFechas();
	    	}
	    	else {
	    		JOptionPane.showMessageDialog(null, err, ERROR, JOptionPane.ERROR_MESSAGE);

	    	} 
	    	break;
	  }
		
	}
	private static boolean isNumeric(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	public static boolean textoVacio (JTextField cuadro){
		boolean vacio=true;
		if(cuadro.getText().equals(""))
			vacio=false;
		return vacio;
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource()== NumCreditos) {
			Num=NumCreditos.getText();
			String a= String.valueOf((Double.parseDouble(Num))*18.87);
			textPrecio.setText(a);
		}
	
		// TODO Auto-generated method stub
		
	}
	public int numRand() {
		int numero = (int)(Math.random()*100+1);
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
}