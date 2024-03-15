package Vista;

import java.awt.EventQueue;
import Modelo.Estudiante;
import Modelo.Curso;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JInternalFrame;

public class frminterno_estudiante extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_identificacion;
	private JTextField txt_nombre;
	private JTextField txt_apellido;
	private JTextField txt_correo;
	private JTextField txt_curso;
	private JTextField txt_paralelo;
	private JTable tableEstudiantes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frminterno_estudiante frame = new frminterno_estudiante();
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
	public frminterno_estudiante() {
		setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1062, 611);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRO DE ALUMNOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(218, 11, 320, 23);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Alumnos", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "Alumnos", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(21, 60, 219, 270);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Identificacion:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setBounds(23, 32, 86, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setBounds(23, 68, 57, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(23, 103, 57, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Correo:");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_4.setBounds(23, 139, 57, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Curso:");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setBounds(23, 179, 46, 14);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Paralelo:");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6.setBounds(23, 211, 46, 14);
		panel.add(lblNewLabel_6);
		
		txt_identificacion = new JTextField();
		txt_identificacion.setBounds(99, 29, 98, 20);
		panel.add(txt_identificacion);
		txt_identificacion.setColumns(10);
		
		txt_nombre = new JTextField();
		txt_nombre.setBounds(79, 65, 86, 20);
		panel.add(txt_nombre);
		txt_nombre.setColumns(10);
		
		txt_apellido = new JTextField();
		txt_apellido.setBounds(79, 100, 86, 20);
		panel.add(txt_apellido);
		txt_apellido.setColumns(10);
		
		txt_correo = new JTextField();
		txt_correo.setBounds(79, 136, 86, 20);
		panel.add(txt_correo);
		txt_correo.setColumns(10);
		
		txt_curso = new JTextField();
		txt_curso.setBounds(79, 176, 86, 20);
		panel.add(txt_curso);
		txt_curso.setColumns(10);
		
		txt_paralelo = new JTextField();
		txt_paralelo.setBounds(79, 208, 86, 20);
		panel.add(txt_paralelo);
		txt_paralelo.setColumns(10);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estudiante estudiante=new Estudiante();
				Curso c=new Curso();
				
				int id= c.ObtenerIDCurso(txt_curso.getText(), txt_paralelo.getText());
				estudiante.setID_CURSO(id);
				
				estudiante.setIdentificacion(txt_identificacion.getText());
				estudiante.setNombre(txt_nombre.getText());
				estudiante.setApellido(txt_apellido.getText());
				estudiante.setCorreo(txt_correo.getText());		
				estudiante.registrarEstudiante(estudiante);
				listarEstudiantesTable();
				limpiarCajas();
			}
		});
		btnNewButton.setBounds(21, 354, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modificar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Estudiante estudiante=new Estudiante();
				
				Curso curso = new Curso();
				
				int fila=tableEstudiantes.getSelectedRow();
				
				estudiante.setID_ESTUDIANTE(Integer.parseInt(tableEstudiantes.getValueAt(fila, 0).toString()));
				
				int id_curso= curso.ObtenerIDCurso(txt_curso.getText(), txt_paralelo.getText());
				estudiante.setID_CURSO(id_curso);
				estudiante.setIdentificacion(txt_identificacion.getText());
				estudiante.setNombre(txt_nombre.getText());
				estudiante.setApellido(txt_apellido.getText());
				estudiante.setCorreo(txt_correo.getText());
				estudiante.ActualizarEstudiante(estudiante);
				
				listarEstudiantesTable();
			}
		});
		btnNewButton_1.setBounds(140, 354, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Estudiante estudiante=new Estudiante();
				
				int fila=tableEstudiantes.getSelectedRow();
				
				estudiante.setID_ESTUDIANTE(Integer.parseInt(tableEstudiantes.getValueAt(fila, 0).toString()));
				
				estudiante.EliminarEstudiante(estudiante);
				
				listarEstudiantesTable();
			}
		});
		btnNewButton_2.setBounds(89, 388, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(268, 60, 462, 264);
		contentPane.add(scrollPane);
		
		tableEstudiantes = new JTable();
		tableEstudiantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila=tableEstudiantes.rowAtPoint(e.getPoint());
				
				txt_identificacion.setText(tableEstudiantes.getValueAt(fila, 1).toString());
				txt_nombre.setText(tableEstudiantes.getValueAt(fila, 2).toString());
				txt_apellido.setText(tableEstudiantes.getValueAt(fila, 3).toString());
				txt_correo.setText(tableEstudiantes.getValueAt(fila, 4).toString());
				txt_curso.setText(tableEstudiantes.getValueAt(fila, 5).toString());
				txt_paralelo.setText(tableEstudiantes.getValueAt(fila, 6).toString());
				
			}
		});
		tableEstudiantes.setModel(new DefaultTableModel(
			new Object[][] {
				{"", null, null, null, "", null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"ID", "IDENTIFICACION", "NOMBRE", "APELLIDO", "CORREO", "CURSO", "PARALELO"
			}
		));
		tableEstudiantes.getColumnModel().getColumn(0).setPreferredWidth(30);
		tableEstudiantes.getColumnModel().getColumn(1).setPreferredWidth(134);
		scrollPane.setViewportView(tableEstudiantes);
		listarEstudiantesTable();
	}
	
	public void listarEstudiantesTable() {
		Estudiante estudiante=new Estudiante();
		List<Estudiante> listaEstudiantes= estudiante.listarEstudiantes();
		DefaultTableModel model= (DefaultTableModel) tableEstudiantes.getModel();
		
		model.setRowCount(0);
		
		for (Estudiante estudiante2 : listaEstudiantes) {
			model.addRow(new Object[] {					
				estudiante2.getID_ESTUDIANTE(),
				estudiante2.getIdentificacion(),
				estudiante2.getNombre(),
				estudiante2.getApellido(),
				estudiante2.getCorreo(),
				estudiante2.getCurso(),
				estudiante2.getParalelo()
			});
		}
		
				
	}
	
	public void limpiarCajas(){
		txt_identificacion.setText("");
		txt_nombre.setText("");
		txt_apellido.setText("");
		txt_correo.setText("");
		txt_curso.setText("");
		txt_paralelo.setText("");
	}
}
