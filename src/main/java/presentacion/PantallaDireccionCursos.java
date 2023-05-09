package main.java.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.java.negocio.controllers.GestorConsultas;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import main.java.persistencia.CursoPropioDAO;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PantallaDireccionCursos extends JFrame {

	private JPanel contentPane;
	protected final JTextField NombreUsu;
	protected final JTextField TipoUsuario;
	CursoPropioDAO cDAO = new CursoPropioDAO();
	
	


	public PantallaDireccionCursos() {
		
		setTitle("UCLM");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaDireccionCursos.class.getResource("/IMAGES/descarga.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnMostrarHistorial = new JButton("Propuestas realizadas");
		btnMostrarHistorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					main.java.presentacion.PantallaPropuestasRealizadas p = new main.java.presentacion.PantallaPropuestasRealizadas();
					GestorConsultas.listarCursos(p.modelo);
					setVisible(false);
					p.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnMostrarHistorial.setFocusPainted(false);
		btnMostrarHistorial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrarHistorial.setForeground(Color.WHITE);
		btnMostrarHistorial.setBackground(SystemColor.textHighlight);
		btnMostrarHistorial.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMostrarHistorial.setBounds(45, 224, 228, 76);
		contentPane.add(btnMostrarHistorial);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(PantallaDireccionCursos.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(10, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JButton btnRealizarPropuesta = new JButton("Realizar propuesta");
		btnRealizarPropuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaRealizarPropuestas p = new PantallaRealizarPropuestas();
				setVisible(false);
				p.setVisible(true);

			}
		});
		btnRealizarPropuesta.setFocusPainted(false);
		btnRealizarPropuesta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRealizarPropuesta.setForeground(Color.WHITE);
		btnRealizarPropuesta.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRealizarPropuesta.setBackground(SystemColor.textHighlight);
		btnRealizarPropuesta.setBounds(45, 122, 228, 76);
		contentPane.add(btnRealizarPropuesta);

		NombreUsu = new JTextField();
		NombreUsu.setText(main.java.presentacion.PantallaLogin.nom.toString());
		NombreUsu.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		NombreUsu.setEditable(false);
		NombreUsu.setHorizontalAlignment(SwingConstants.LEFT);
		NombreUsu.setFont(new Font("Tahoma", Font.BOLD, 15));
		NombreUsu.setColumns(10);
		NombreUsu.setBorder(null);
		NombreUsu.setBackground(Color.WHITE);
		NombreUsu.setBounds(552, 214, 252, 19);
		contentPane.add(NombreUsu);

		TipoUsuario = new JTextField();
		TipoUsuario.setEditable(false);
		TipoUsuario.setText(main.java.presentacion.PantallaLogin.tipo.toString());
		TipoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		TipoUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		TipoUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		TipoUsuario.setColumns(10);
		TipoUsuario.setBorder(null);
		TipoUsuario.setBackground(Color.WHITE);
		TipoUsuario.setBounds(552, 253, 252, 19);
		contentPane.add(TipoUsuario);

		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2.setIcon(new ImageIcon(PantallaDireccionCursos.class.getResource("/IMAGES/images2.jpg")));
		lblNewLabel2.setBounds(549, 55, 142, 143);
		contentPane.add(lblNewLabel2);

		JButton cs = new JButton("Cerrar sesi\u00F3n\r\n");
		cs.setBorderPainted(false);
		cs.setFocusPainted(false);
		cs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				PantallaLogin p = new PantallaLogin();
			}
		});
		cs.setHorizontalTextPosition(SwingConstants.LEFT);
		cs.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cs.setBackground(new Color(255, 0, 0));
		cs.setForeground(new Color(255, 255, 255));
		cs.setFont(new Font("Tahoma", Font.BOLD, 13));
		cs.setIconTextGap(15);
		cs.setIcon(new ImageIcon(PantallaDireccionCursos.class.getResource("/IMAGES/cerrar-sesion .png")));
		cs.setBounds(552, 303, 176, 39);
		contentPane.add(cs);
		
		JButton btnRechazados = new JButton("Propuestas rechazadas");
		btnRechazados.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRechazados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					main.java.presentacion.PantallaPropuestasRechazadas p = new main.java.presentacion.PantallaPropuestasRechazadas();
					
					GestorConsultas.listarCursosRechazados(p.modelo);
					setVisible(false);
					p.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		btnRechazados.setForeground(Color.WHITE);
		btnRechazados.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRechazados.setFocusPainted(false);
		btnRechazados.setBackground(SystemColor.textHighlight);
		btnRechazados.setBounds(45, 326, 228, 76);
		contentPane.add(btnRechazados);

		JButton btnMostrarResueltos = new JButton("Propuestas resueltas");
		btnMostrarResueltos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrarResueltos.setForeground(Color.WHITE);
		btnMostrarResueltos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMostrarResueltos.setBackground(SystemColor.textHighlight);
		btnMostrarResueltos.setBounds(103, 146, 228, 99);
	}
}
