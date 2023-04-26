package presentacion;


import java.awt.Color;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Cursor;

public class PantallaMatriculacion extends JFrame {

	public PantallaMatriculacion(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(PantallaMatriculacion.class.getResource("/IMAGES/descarga.png")));
		setTitle("UCLM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PantallaMatriculacion.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);
		
		JButton btnTar = new JButton("Pago con Tarjeta");
		btnTar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTar.setForeground(Color.WHITE);
		btnTar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTar.setBackground(SystemColor.textHighlight);
		btnTar.setBounds(56, 380, 226, 75);
		btnTar.setVisible(false);
		contentPane.add(btnTar);
		
		JButton btnTrans = new JButton("Pago por transferencia");
		btnTrans.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnTrans.setForeground(Color.WHITE);
		btnTrans.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnTrans.setBackground(SystemColor.textHighlight);
		btnTrans.setBounds(449, 380, 226, 75);
		btnTrans.setVisible(false);
		contentPane.add(btnTrans);
		
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(630, 38, 114, 49);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				presentacion.PantallaEstudiante p = new presentacion.PantallaEstudiante();
				p.setVisible(true);
			}
		});
		
		JList listaCursos = new JList();
		listaCursos.setBounds(54, 108, 659, 251);
		contentPane.add(listaCursos);
		DefaultListModel modelo = new DefaultListModel();
		modelo.addElement("Elemento1");
		modelo.addElement("Elemento2");
		modelo.addElement("Elemento3");
		listaCursos.setModel(modelo);
		
		listaCursos.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	btnTar.setVisible(true);
                	btnTrans.setVisible(true);
                	
	}            
	}});
		}
	public int numRand() {
		int numero = (int)(Math.random()*100+1);
		return numero;
	}
}
