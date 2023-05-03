
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
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JScrollBar;

public class PantallaEstadisticasCursos extends JFrame {
	private JTextField textField;

	public PantallaEstadisticasCursos() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaEstadisticasCursos.class.getResource("/IMAGES/descarga.png")));
		setTitle("UCLM");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 520);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon(PantallaEstadisticasCursos.class.getResource("/IMAGES/Captura de pantalla (188).png")));
		lblNewLabel.setBounds(44, 10, 310, 99);
		contentPane.add(lblNewLabel);

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
				presentacion.PantallaJefeGabineteVicerrectorado p = new presentacion.PantallaJefeGabineteVicerrectorado();
				p.setVisible(true);
			}
		});

		JList listaCursos = new JList();
		listaCursos.setBounds(54, 108, 684, 267);
		contentPane.add(listaCursos);
		DefaultListModel modelo = new DefaultListModel();
		modelo.addElement("Elemento1");
		modelo.addElement("Elemento2");
		modelo.addElement("Elemento3");
		listaCursos.setModel(modelo);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 13));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		textField.setBounds(259, 434, 259, 39);
		contentPane.add(textField);

		JLabel lblIngresosTotales = new JLabel("Ingresos totales:\r\n\r\n");
		lblIngresosTotales.setForeground(SystemColor.textHighlight);
		lblIngresosTotales.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblIngresosTotales.setBounds(259, 385, 259, 39);
		contentPane.add(lblIngresosTotales);

		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(712, 108, 26, 267);
		contentPane.add(scrollBar);

		listaCursos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
//curso selecionado

				}
			}
		});
	}
}
