package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JLabel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Cursor;

public class PantallaConectar extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaConectar frame = new PantallaConectar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PantallaConectar() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaConectar.class.getResource("/IMAGES/descarga.png")));
		setTitle("UCLM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(625, 428);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PantallaConectar.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

		JButton conectBtn = new JButton("ENTRAR");
		conectBtn.setFocusPainted(false);
		conectBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		conectBtn.setForeground(Color.WHITE);
		conectBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		conectBtn.setBackground(SystemColor.textHighlight);
		conectBtn.setBounds(141, 172, 274, 113);
		contentPane.getRootPane().setDefaultButton(conectBtn);
		contentPane.add(conectBtn);

		conectBtn.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new presentacion.PantallaLogin();

			}
		});
	}
}
