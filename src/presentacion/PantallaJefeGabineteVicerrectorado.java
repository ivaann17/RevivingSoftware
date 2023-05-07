package presentacion;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import persistencia.CursoPropioDAO;
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
	protected final JTextField NombreUsu;
	protected final JTextField TipoUsuario;
	CursoPropioDAO cDAO = new CursoPropioDAO();

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

		JButton btnVerCursos = new JButton("Mostrar Cursos");
		btnVerCursos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				presentacion.PantallaEstadisticasCursos p = new presentacion.PantallaEstadisticasCursos();
				setVisible(false);
				p.setVisible(true);

			}
		});
		btnVerCursos.setFocusPainted(false);
		btnVerCursos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVerCursos.setForeground(Color.WHITE);
		btnVerCursos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnVerCursos.setBackground(SystemColor.textHighlight);
		btnVerCursos.setBounds(45, 122, 228, 76);
		contentPane.add(btnVerCursos);

		NombreUsu = new JTextField();
		NombreUsu.setText(presentacion.PantallaLogin.nom.toString());
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
		TipoUsuario.setText(presentacion.PantallaLogin.tipo.toString());
		TipoUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		TipoUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		TipoUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		TipoUsuario.setColumns(10);
		TipoUsuario.setBorder(null);
		TipoUsuario.setBackground(Color.WHITE);
		TipoUsuario.setBounds(552, 253, 252, 19);
		contentPane.add(TipoUsuario);

		JLabel lblNewLabel2 = new JLabel("");
		lblNewLabel2
				.setIcon(new ImageIcon(PantallaJefeGabineteVicerrectorado.class.getResource("/IMAGES/images2.jpg")));
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
		cs.setIcon(new ImageIcon(PantallaJefeGabineteVicerrectorado.class.getResource("/IMAGES/cerrar-sesion .png")));
		cs.setBounds(552, 303, 176, 39);
		contentPane.add(cs);

		JButton btnMostrarResueltos = new JButton("Propuestas resueltas");
		btnMostrarResueltos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMostrarResueltos.setForeground(Color.WHITE);
		btnMostrarResueltos.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnMostrarResueltos.setBackground(SystemColor.textHighlight);
		btnMostrarResueltos.setBounds(103, 146, 228, 99);
	}
}
