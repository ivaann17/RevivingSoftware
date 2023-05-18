package main.java.presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;

import main.java.negocio.controllers.GestorConsultas;
import main.java.negocio.entities.EstadoCurso;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PantallaJefeGabineteVicerrectorado extends JFrame {

	private JPanel contentPane;

	public PantallaJefeGabineteVicerrectorado() {
		setTitle("UCLM");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaJefeGabineteVicerrectorado.class.getResource("/IMAGES/descarga.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(
				PantallaJefeGabineteVicerrectorado.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(10, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JButton btnVerCursos = new JButton("Estadisticas cursos");
		btnVerCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					main.java.presentacion.PantallaEstadisticasCursos p = new main.java.presentacion.PantallaEstadisticasCursos();
					GestorConsultas.listarCursos(p.modelo);
					setVisible(false);
					p.setVisible(true);
				} catch (Exception e1) {
				
				}

			}
		});
		btnVerCursos.setFocusPainted(false);
		btnVerCursos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVerCursos.setForeground(Color.WHITE);
		btnVerCursos.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnVerCursos.setBackground(SystemColor.textHighlight);
		btnVerCursos.setBounds(45, 122, 228, 76);
		contentPane.add(btnVerCursos);

		contentPane.add(main.java.presentacion.PantallaEstudiante.createNombreUsuTextField());

		contentPane.add(main.java.presentacion.PantallaEstudiante.createTipoUsuarioTextField());

		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2
				.setIcon(new ImageIcon(PantallaJefeGabineteVicerrectorado.class.getResource("/IMAGES/images2.jpg")));
		lblNewLabel2.setBounds(549, 55, 142, 143);
		contentPane.add(lblNewLabel2);

		JButton cs = new JButton("Cerrar sesion");
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
		cs.setIcon(new ImageIcon(PantallaJefeGabineteVicerrectorado.class.getResource("/IMAGES/cerrar-sesion .png")));
		cs.setBounds(552, 303, 176, 39);
		contentPane.add(cs);

		JButton btnComenzarCursos = new JButton("Comenzar cursos");
		btnComenzarCursos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnComenzarCursos.setVisible(true);
		btnComenzarCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				main.java.presentacion.PantallaComenzarCurso p = new main.java.presentacion.PantallaComenzarCurso();
				try {
					GestorConsultas.listarCursosPorEstado(p.modelo, EstadoCurso.EN_MATRICULACION);
					setVisible(false);
					p.setVisible(true);

				} catch (Exception e1) {
	
				}

			}
		});
		btnComenzarCursos.setForeground(Color.WHITE);
		btnComenzarCursos.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnComenzarCursos.setFocusPainted(false);
		btnComenzarCursos.setBackground(SystemColor.textHighlight);
		btnComenzarCursos.setBounds(45, 267, 228, 76);
		contentPane.add(btnComenzarCursos);

		JButton btnMostrarResueltos = new JButton("Propuestas resueltas");
		btnMostrarResueltos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrarResueltos.setForeground(Color.WHITE);
		btnMostrarResueltos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMostrarResueltos.setBackground(SystemColor.textHighlight);
		btnMostrarResueltos.setBounds(103, 146, 228, 99);
	}

}
