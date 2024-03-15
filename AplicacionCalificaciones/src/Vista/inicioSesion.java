package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

//import Borrador.cls_conexion;
import Conexion.cls_conexion;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JPasswordField;

public class inicioSesion {

	private JFrame frame;
	private JDesktopPane escritorio;
	private JMenuItem menu_docentes;
	private JMenuItem menu_alumnos;
	private JMenuItem menu_calificaciones;
	private JMenuItem menu_reportes;
	private JPanel panel_login;
	private JTextField txt_usuario;
	private JButton btn_ingresar;
	private JButton btn_registrar;
	private JButton btn_recuperar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPasswordField txt_clave;
	private JMenuItem menu_materia;
	private JMenuItem menu_curso;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inicioSesion window = new inicioSesion();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public inicioSesion() {
		initialize();
	}

	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1076, 678);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		escritorio = new JDesktopPane();
		escritorio.setBounds(0, 0, 1060, 611);
		frame.getContentPane().add(escritorio);

		panel_login = new JPanel();
		panel_login.setBackground(new Color(18, 13, 29));
		panel_login.setBounds(0, 0, 1062, 611);
		escritorio.add(panel_login);
		panel_login.setLayout(null);
		
		btn_registrar = new JButton("Registrarse");
		btn_registrar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_registrar.setBackground(new Color(255, 255, 255));
		btn_registrar.setBorder(null);
		btn_registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btn_recuperar = new JButton("Recuperar");
		btn_recuperar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_recuperar.setBackground(new Color(28, 28, 57));
		btn_recuperar.setBorder(null);
		btn_recuperar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.setVisible(false);
		
		btn_ingresar = new JButton("Ingresar");
		btn_ingresar.setBackground(new Color(78, 79, 235));
		btn_ingresar.setBorder(null);
		btn_ingresar.setBounds(76, 376, 280, 39);
		btn_ingresar.setForeground(new Color(255, 255, 255));
		btn_ingresar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String usuario = txt_usuario.getText();
				String clave = String.valueOf(txt_clave.getPassword());
				
				cls_conexion conexionlg = new cls_conexion();
				if (conexionlg.validarCredenciales(usuario, clave)) {
					menuBar.setVisible(true);
		            txt_usuario.setText("");
		            txt_clave.setText("");
		            panel_login.setVisible(false);
		            System.out.println("Inicio de sesión exitoso");
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		txt_clave = new JPasswordField();
		txt_clave.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_clave.setForeground(new Color(255, 255, 255));
		txt_clave.setBackground(new Color(28, 28, 57));
		txt_clave.setBorder(null);
		txt_clave.setBounds(60, 300, 307, 33);
		
		panel_login.add(txt_clave);
		panel_login.add(btn_ingresar);
		btn_recuperar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_recuperar.setForeground(new Color(255, 255, 255));
		btn_recuperar.setBounds(302, 515, 71, 23);
		panel_login.add(btn_recuperar);
		btn_registrar.setForeground(new Color(0, 0, 0));
		btn_registrar.setBounds(76, 438, 280, 39);
		panel_login.add(btn_registrar);
		
		txt_usuario = new JTextField();
		txt_usuario.setFont(new Font("Tahoma", Font.BOLD, 12));
		txt_usuario.setForeground(new Color(255, 255, 255));
		txt_usuario.setBackground(new Color(28, 28, 57));
		txt_usuario.setBounds(60, 222, 307, 33);
		txt_usuario.setBorder(null);
		panel_login.add(txt_usuario);
		txt_usuario.setColumns(10);
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBackground(new Color(10, 5, 95));
		lblNewLabel_1.setIcon(new ImageIcon(inicioSesion.class.getResource("/Imagenes/Logincambio.jpg")));
		lblNewLabel_1.setBounds(0, 0, 456, 611);
		panel_login.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(inicioSesion.class.getResource("/Imagenes/Docente.png")));
		lblNewLabel.setBounds(496, 23, 538, 565);
		panel_login.add(lblNewLabel);

		JMenu mnNewMenu = new JMenu("Menú Principal");
		menuBar.add(mnNewMenu);
		
		
		menu_docentes = new JMenuItem("Docentes");
		menu_docentes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frminterno_docente docente = new frminterno_docente();
				escritorio.add(docente);
				docente.show();
				
			}
		});
		mnNewMenu.add(menu_docentes);
		
		menu_calificaciones = new JMenuItem("Calificaciones");
		menu_calificaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frminterno_notas notas = new frminterno_notas();
				escritorio.add(notas);
				notas.show();
				
			}
		});
		
		menu_alumnos = new JMenuItem("Alumnos");
		menu_alumnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frminterno_estudiante estudiante = new frminterno_estudiante();
				escritorio.add(estudiante);
				estudiante.show();
				
			}
		});
		mnNewMenu.add(menu_alumnos);
		
		menu_materia = new JMenuItem("Materia");
		menu_materia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frminterno_materia materia = new frminterno_materia();
				escritorio.add(materia);
				materia.show();
				
			}
		});
		mnNewMenu.add(menu_materia);
		
		menu_curso = new JMenuItem("Curso");
		menu_curso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frminterno_curso curso = new frminterno_curso();
				escritorio.add(curso);
				curso.show();
				
			}
		});
		mnNewMenu.add(menu_curso);
		mnNewMenu.add(menu_calificaciones);
		
		menu_reportes = new JMenuItem("Reportes");
		mnNewMenu.add(menu_reportes);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Cerrar Sesión");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				menuBar.setVisible(false);
				panel_login.setVisible(true);

			}
		});
		menuBar.add(mntmNewMenuItem);
	}
}