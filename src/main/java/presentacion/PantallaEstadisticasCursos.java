
package main.java.presentacion;

import java.awt.Color;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.java.negocio.controllers.GestorConsultas;
import main.java.negocio.entities.CursoPropio;
import main.java.negocio.entities.EstadoCurso;

import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class PantallaEstadisticasCursos extends JFrame {
	protected JTextField ingresos;
	private static final JList<CursoPropio> listaCursos = new JList<>();
	private static final String tipoLetra = "Tahoma";
	DefaultListModel<CursoPropio> modelo;
	private transient CursoPropio cursoSeleccionado;

	public PantallaEstadisticasCursos() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(PantallaEstadisticasCursos.class.getResource("/IMAGES/descarga.png")));
		setTitle("UCLM");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
		btnNewButton.setFont(new Font(tipoLetra, Font.BOLD, 13));
		btnNewButton.setBackground(SystemColor.textHighlight);
		btnNewButton.setBounds(630, 38, 114, 49);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				main.java.presentacion.PantallaJefeGabineteVicerrectorado p = new main.java.presentacion.PantallaJefeGabineteVicerrectorado();
				p.setVisible(true);
			}
		});

		listaCursos.setBounds(54, 108, 684, 267);
		contentPane.add(listaCursos);
		modelo = new DefaultListModel<CursoPropio>();
		listaCursos.setModel(modelo);

		ingresos = new JTextField();
		ingresos.setVisible(false);
		ingresos.setFont(new Font(tipoLetra, Font.BOLD, 13));
		ingresos.setEditable(false);
		ingresos.setColumns(10);
		ingresos.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 120, 215)));
		ingresos.setBounds(44, 434, 186, 39);
		contentPane.add(ingresos);

		JLabel lblIngresosCurso = new JLabel("Ingresos de curso:\r\n\r\n");
		lblIngresosCurso.setVisible(false);
		lblIngresosCurso.setForeground(SystemColor.textHighlight);
		lblIngresosCurso.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		lblIngresosCurso.setBounds(44, 385, 186, 39);
		contentPane.add(lblIngresosCurso);

		JLabel filtros = new JLabel("Filtrar por:");
		filtros.setHorizontalAlignment(SwingConstants.CENTER);
		filtros.setVisible(true);
		filtros.setForeground(SystemColor.textHighlight);
		filtros.setFont(new Font(tipoLetra, Font.BOLD | Font.ITALIC, 13));
		filtros.setBounds(424, 385, 186, 39);
		contentPane.add(filtros);

		JRadioButton rdbtnTodos = new JRadioButton("Todos");
		rdbtnTodos.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnTodos.setVisible(false);
		rdbtnTodos.setForeground(Color.WHITE);
		rdbtnTodos.setFont(new Font(tipoLetra, Font.BOLD, 13));
		rdbtnTodos.setBorderPainted(true);
		rdbtnTodos.setBorder(new EmptyBorder(0, 0, 0, 0));
		rdbtnTodos.setBackground(SystemColor.textHighlight);
		rdbtnTodos.setBounds(280, 434, 109, 39);
		contentPane.add(rdbtnTodos);
		rdbtnTodos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modelo.removeAllElements();
					GestorConsultas.listarCursos(modelo);

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		JRadioButton rdbtnFiltrarEdi = new JRadioButton("Ediciones");
		rdbtnFiltrarEdi.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnFiltrarEdi.setForeground(Color.WHITE);
		rdbtnFiltrarEdi.setBorderPainted(true);
		rdbtnFiltrarEdi.setBorder(new EmptyBorder(0, 0, 0, 0));
		rdbtnFiltrarEdi.setBackground(SystemColor.textHighlight);
		rdbtnFiltrarEdi.setFont(new Font(tipoLetra, Font.BOLD, 13));
		rdbtnFiltrarEdi.setBounds(637, 434, 109, 39);
		contentPane.add(rdbtnFiltrarEdi);
		rdbtnFiltrarEdi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					listaCursos.clearSelection();
					modelo.removeAllElements();
					GestorConsultas.listarCursosPorEdiciones(modelo);
					rdbtnTodos.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		JRadioButton rdbtnFiltrarAcep = new JRadioButton("Aceptados");
		rdbtnFiltrarAcep.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnFiltrarAcep.setForeground(Color.WHITE);
		rdbtnFiltrarAcep.setFont(new Font(tipoLetra, Font.BOLD, 13));
		rdbtnFiltrarAcep.setBorderPainted(true);
		rdbtnFiltrarAcep.setBorder(new EmptyBorder(0, 0, 0, 0));
		rdbtnFiltrarAcep.setBackground(SystemColor.textHighlight);
		rdbtnFiltrarAcep.setBounds(518, 434, 109, 39);
		contentPane.add(rdbtnFiltrarAcep);
		rdbtnFiltrarAcep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modelo.removeAllElements();
					GestorConsultas.listarCursosPorEstado(modelo, EstadoCurso.EN_IMPARTIZICION);
					GestorConsultas.listarCursosPorEstado(modelo, EstadoCurso.EN_MATRICULACION);
					GestorConsultas.listarCursosPorEstado(modelo, EstadoCurso.VALIDADO);
					rdbtnTodos.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		JRadioButton rdbtnFiltrarRecha = new JRadioButton("Rechazados");
		rdbtnFiltrarRecha.setHorizontalAlignment(SwingConstants.CENTER);
		rdbtnFiltrarRecha.setForeground(Color.WHITE);
		rdbtnFiltrarRecha.setFont(new Font(tipoLetra, Font.BOLD, 13));
		rdbtnFiltrarRecha.setBorderPainted(true);
		rdbtnFiltrarRecha.setBorder(new EmptyBorder(0, 0, 0, 0));
		rdbtnFiltrarRecha.setBackground(SystemColor.textHighlight);
		rdbtnFiltrarRecha.setBounds(399, 434, 109, 39);
		contentPane.add(rdbtnFiltrarRecha);
		rdbtnFiltrarRecha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					modelo.removeAllElements();
					GestorConsultas.listarCursosPorEstado(modelo, EstadoCurso.PROPUESTA_RECHAZADA);
					rdbtnTodos.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnFiltrarRecha);
		buttonGroup.add(rdbtnFiltrarAcep);
		buttonGroup.add(rdbtnFiltrarEdi);
		buttonGroup.add(rdbtnTodos);

		listaCursos.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {

					cursoSeleccionado = listaCursos.getSelectedValue();
					if (cursoSeleccionado != null) {
						try {

							lblIngresosCurso.setVisible(true);
							ingresos.setVisible(true);
							ingresos.setText((Double.toString(GestorConsultas.consultarIngresos(cursoSeleccionado))));
						} catch (Exception e) {
							e.printStackTrace();

						}
					}

				}
			}
		});
	}
}
