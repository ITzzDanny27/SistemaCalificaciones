package Vista;

import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import Conexion.cls_conexion;
import javax.security.auth.DestroyFailedException;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JInternalFrame;

public class frminterno_materia extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_nombremate;
	private JTextField txt_ID;
	private JButton btn_guardar;
	public static DefaultTableModel modelo;
	Modelo.cls_materia p = new Modelo.cls_materia();
	private JTable tabla;
	private JTextField txt_busmate;
	private JScrollPane scrollPane;
	private JButton btn_nuevo;
	private JButton btn_Actualizar;
	private JButton btn_Cancelar;
	private JButton btn_Eliminar;
	private JButton btnNewButton;
	int bandera = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frminterno_materia frame = new frminterno_materia();
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
	public frminterno_materia() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				int bandera=0;
				llenartabla("");
				bcajas();
				bbotones();
				acajas();
				btn_nuevo.setEnabled(true);
				btn_Actualizar.setEnabled(true);
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1062, 611);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre de materia");
		lblNewLabel.setBounds(10, 45, 158, 14);
		contentPane.add(lblNewLabel);
		
		txt_nombremate = new JTextField();
		txt_nombremate.setBounds(136, 42, 166, 20);
		contentPane.add(txt_nombremate);
		txt_nombremate.setColumns(10);
		
		txt_ID = new JTextField();
		txt_ID.setColumns(10);
		txt_ID.setBounds(10, 11, 86, 20);
		txt_ID.setVisible(true);
		contentPane.add(txt_ID);
		
		btn_guardar = new JButton("Guardar");
		btn_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setMateria(txt_nombremate.getText());
				if(p.insertar()) {
					JOptionPane.showMessageDialog(null, "Registro Guardado Correctamente");
					lcajas();
					bcajas();
					bbotones();
					btn_nuevo.setEnabled(true);
				}
				else{
					JOptionPane.showMessageDialog(null, "Error Al Guardar");
				}
				}
			
		});
		btn_guardar.setBounds(144, 278, 89, 23);
		contentPane.add(btn_guardar);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar materia");
		lblNewLabel_1.setBounds(353, 45, 141, 14);
		contentPane.add(lblNewLabel_1);
		
		txt_busmate = new JTextField();
		txt_busmate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				llenartabla(txt_busmate.getText());
			}
		});
		
		txt_busmate.setBounds(445, 42, 179, 20);
		contentPane.add(txt_busmate);
		txt_busmate.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setBounds(10, 80, 614, 153);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txt_ID.setText(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
				String dato[] = new String[6];
				dato = p.constultarDato(txt_ID.getText());
				txt_nombremate.setText(dato[1]);
				bbotones();
				btn_Actualizar.setEnabled(true);;
				btn_Eliminar.setEnabled(true);
				btn_Cancelar.setEnabled(true);
			}
		});
		scrollPane.setViewportView(tabla);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane.setRowHeaderView(scrollPane_1);
		
		btn_nuevo = new JButton("Nuevo");
		btn_nuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acajas();
				bbotones();
				btn_Cancelar.setEnabled(true);
				btn_guardar.setEnabled(true);
				bandera=1;
			}
		});
		btn_nuevo.setBounds(90, 244, 89, 23);
		contentPane.add(btn_nuevo);
		
		btn_Actualizar = new JButton("Actualizar");
		btn_Actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acajas();
				p.setId_materia(txt_ID.getText());
				p.setMateria(txt_nombremate.getText());
				p.actualizar(p);
				llenartabla("");
				JOptionPane.showMessageDialog(null, "Registro Actualizado Correctamente");
			}
		});
		btn_Actualizar.setBounds(199, 244, 89, 23);
		contentPane.add(btn_Actualizar);
		
		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lcajas();
				bcajas();
				bbotones();
				btn_nuevo.setEnabled(true);
				
			}
		});
		btn_Cancelar.setBounds(298, 244, 89, 23);
		contentPane.add(btn_Cancelar);
		
		btn_Eliminar = new JButton("Eliminar");
		btn_Eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int botones = JOptionPane.YES_NO_OPTION;
				int confirmacion = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea eliminar el registro.........???????","Warning",botones);
				if (confirmacion == JOptionPane.YES_OPTION) {
					p.setId_materia(txt_ID.getText());
					if(p.eliminar()>0) {
						JOptionPane.showMessageDialog(null, "Registro eliminado");
						lcajas();
						bcajas();
						bbotones();
						btn_nuevo.setEnabled(true);
					}else {
						JOptionPane.showMessageDialog(null, "Error al Eliminar el registro");
						bbotones();
						btn_nuevo.setEnabled(true);
						}
					}	
			}
		});
		btn_Eliminar.setBounds(261, 278, 89, 23);
		contentPane.add(btn_Eliminar);
		
		btnNewButton = new JButton("Cerrar sesion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*dispose();
				menu menu = new menu();
				menu.mostrarMenu();	
				*/
			}
		});
		btnNewButton.setBounds(483, 278, 141, 23);
		contentPane.add(btnNewButton);
	}
	public void lcajas() {
		txt_nombremate.setText("");

	}
	public void bcajas() {
		txt_nombremate.setEnabled(false);

		
	}
	public void acajas() {
		txt_nombremate.setEnabled(true);
	
		
	}
	public void bbotones() {
		btn_nuevo.setEnabled(false);
		btn_Cancelar.setEnabled(false);
		btn_Eliminar.setEnabled(false);
		btn_Actualizar.setEnabled(false);
		btn_guardar.setEnabled(false);
	}
	public void abotones() {
		btn_nuevo.setEnabled(true);
		btn_Cancelar.setEnabled(true);
		btn_Eliminar.setEnabled(true);
		btn_Actualizar.setEnabled(true);
		btn_guardar.setEnabled(true);
	}
	
	public void llenartabla(String apellidos) {
		String [] cabeceras= {"ID Materia", "Materia"};
		modelo = new DefaultTableModel(p.consultar(apellidos, apellidos),cabeceras);
		tabla.setModel(modelo);
	}

}
