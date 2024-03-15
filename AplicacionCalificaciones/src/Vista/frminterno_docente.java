package Vista;

import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.table.DefaultTableModel;
import Modelo.cls_profesores;
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

public class frminterno_docente extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_nombre;
	private JTextField txt_apellido;
	private JTextField txt_correo;
	private JTextField txt_identificacion;
	private JTextField txt_tele;
	private JTextField txt_idpro;
	private JButton btn_guardar;
	public static DefaultTableModel modelo;
	cls_profesores p = new cls_profesores();
	private JTable tabla;
	private JTextField txt_bapellido;
	private JScrollPane scrollPane;
	private JButton btn_nuevo;
	private JButton btn_Actualizar;
	private JButton btn_Cancelar;
	private JButton btn_Eliminar;
	private JButton btnNewButton;
	int bandera = 0;
	private JTextField txt_usuario;
	private JTextField txt_clave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frminterno_docente frame = new frminterno_docente();
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
	public frminterno_docente() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameActivated(InternalFrameEvent e) {
				int bandera = 0;
				llenartabla("");
				bcajas();
				bbotones();
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
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(36, 87, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("apellido");
		lblApellido.setBounds(36, 112, 46, 14);
		contentPane.add(lblApellido);
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(114, 84, 179, 20);
		contentPane.add(txt_nombre);
		txt_nombre.setColumns(10);
		
		txt_apellido = new JTextField();
		txt_apellido.setColumns(10);
		txt_apellido.setBounds(114, 109, 179, 20);
		contentPane.add(txt_apellido);
		
		JLabel lblNewLabel_1_1 = new JLabel("Identificacion");
		lblNewLabel_1_1.setBounds(36, 59, 69, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblCorreo = new JLabel("correo");
		lblCorreo.setBounds(36, 140, 46, 14);
		contentPane.add(lblCorreo);
		
		txt_correo = new JTextField();
		txt_correo.setColumns(10);
		txt_correo.setBounds(114, 137, 179, 20);
		contentPane.add(txt_correo);
		
		txt_identificacion = new JTextField();
		txt_identificacion.setColumns(10);
		txt_identificacion.setBounds(114, 56, 179, 20);
		contentPane.add(txt_identificacion);
		
		JLabel lblClave = new JLabel("Telefono");
		lblClave.setBounds(36, 168, 46, 14);
		contentPane.add(lblClave);
		
		txt_tele = new JTextField();
		txt_tele.setColumns(10);
		txt_tele.setBounds(114, 165, 179, 20);
		contentPane.add(txt_tele);
		
		txt_idpro = new JTextField();
		txt_idpro.setColumns(10);
		txt_idpro.setBounds(10, 11, 86, 20);
		txt_idpro.setVisible(true);
		contentPane.add(txt_idpro);
		
		btn_guardar = new JButton("Guardar");
		btn_guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setIdentificacion(txt_identificacion.getText());
				p.setNombre(txt_nombre.getText());
				p.setApellido(txt_apellido.getText());
				p.setTelefono(txt_tele.getText());
				p.setCorreo(txt_correo.getText());
				p.setUsuario(txt_usuario.getText());
				p.setClave(txt_clave.getText());
				
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
		btn_guardar.setBounds(64, 301, 89, 23);
		contentPane.add(btn_guardar);
		
		JLabel lblNewLabel_1 = new JLabel("Apellidos");
		lblNewLabel_1.setBounds(304, 42, 69, 14);
		contentPane.add(lblNewLabel_1);
		
		txt_bapellido = new JTextField();
		txt_bapellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				llenartabla(txt_bapellido.getText());
			}
		});
		
		txt_bapellido.setBounds(383, 42, 179, 20);
		contentPane.add(txt_bapellido);
		txt_bapellido.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		scrollPane.setBounds(314, 69, 460, 214);
		contentPane.add(scrollPane);
		
		tabla = new JTable();
		tabla.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txt_idpro.setText(tabla.getValueAt(tabla.getSelectedRow(), 0).toString());
				String dato[] = new String[6];
				dato = p.constultarDato(txt_idpro.getText());
				txt_identificacion.setText(dato[1]);
				txt_nombre.setText(dato[2]);
				txt_apellido.setText(dato[3]);
				txt_correo.setText(dato[4]);
				txt_tele.setText(dato[5]);
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
		btn_nuevo.setBounds(10, 267, 89, 23);
		contentPane.add(btn_nuevo);
		
		btn_Actualizar = new JButton("Actualizar");
		btn_Actualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 acajas();
				 	p.setId_profesores(Integer.parseInt(txt_idpro.getText()));
			        p.setIdentificacion(txt_identificacion.getText());
			        p.setNombre(txt_nombre.getText());
			        p.setApellido(txt_apellido.getText());
			        p.setTelefono(txt_tele.getText());
			        p.setCorreo(txt_correo.getText());
			        p.setUsuario(txt_usuario.getText());
			        p.setClave(txt_clave.getText());
			        
			        btn_nuevo.setEnabled(true);
			        btn_guardar.setEnabled(true);
			        btn_Cancelar.setEnabled(true);
			        p.actualizar(p);
			        llenartabla("");
			}
		});
		btn_Actualizar.setBounds(119, 267, 89, 23);
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
		btn_Cancelar.setBounds(218, 267, 89, 23);
		contentPane.add(btn_Cancelar);
		
		btn_Eliminar = new JButton("Eliminar");
		btn_Eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int botones = JOptionPane.YES_NO_OPTION;
				int confirmacion = JOptionPane.showConfirmDialog(null, "Esta seguro de que desea eliminar el registro.........?","Warning",botones);
				if (confirmacion == JOptionPane.YES_OPTION) {
					p.setId_profesores((Integer.parseInt(txt_idpro.getText())));
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
		btn_Eliminar.setBounds(181, 301, 89, 23);
		contentPane.add(btn_Eliminar);
		
		btnNewButton = new JButton("Cerrar sesion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			/*	
				dispose();
				menu menu = new menu();
				menu.mostrarMenu();	*/
			}
		});
		btnNewButton.setBounds(10, 356, 141, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(36, 195, 46, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblClave_1_1 = new JLabel("Clave");
		lblClave_1_1.setBounds(36, 220, 46, 14);
		contentPane.add(lblClave_1_1);
		
		txt_usuario = new JTextField();
		txt_usuario.setBounds(114, 192, 179, 20);
		contentPane.add(txt_usuario);
		txt_usuario.setColumns(10);
		
		txt_clave = new JTextField();
		txt_clave.setBounds(114, 217, 179, 20);
		contentPane.add(txt_clave);
		txt_clave.setColumns(10);
	}
	public void lcajas() {
		txt_nombre.setText("");
		txt_apellido.setText("");
		txt_correo.setText("");
		txt_identificacion.setText("");
		txt_tele.setText("");
	}
	public void bcajas() {
		txt_nombre.setEnabled(false);
		txt_apellido.setEnabled(false);
		txt_correo.setEnabled(false);
		txt_identificacion.setEnabled(false);
		txt_tele.setEnabled(false);
		
	}
	public void acajas() {
		txt_nombre.setEnabled(true);
		txt_apellido.setEnabled(true);
		txt_correo.setEnabled(true);
		txt_identificacion.setEnabled(true);
		txt_tele.setEnabled(true);
		
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
		String [] cabeceras= {"ID_Profesor","Identificacion", "Nombre", "Apellido", "Telefono", "Correo", "Usuario", "Clave"};
		modelo = new DefaultTableModel(p.consultar(apellidos, apellidos),cabeceras);
		tabla.setModel(modelo);
	}

}
