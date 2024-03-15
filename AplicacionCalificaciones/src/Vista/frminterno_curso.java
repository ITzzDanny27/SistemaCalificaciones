package Vista;

import java.awt.EventQueue;
import Modelo.Curso;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JInternalFrame;

public class frminterno_curso extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_curso;
	private JTextField txt_paralelo;
	private JTable tableCursos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frminterno_curso frame = new frminterno_curso();
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
	public frminterno_curso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1062, 611);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.desktop);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Curso:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setForeground(SystemColor.menu);
		lblNewLabel.setBounds(21, 83, 46, 14);
		contentPane.add(lblNewLabel);
		
		txt_curso = new JTextField();
		txt_curso.setBounds(70, 82, 86, 20);
		contentPane.add(txt_curso);
		txt_curso.setColumns(10);
		
		JButton btn_Guardar = new JButton("Guardar");
		btn_Guardar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_Guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Curso c=new Curso();
				c.setCurso(txt_curso.getText());;
				c.setParalelo(txt_paralelo.getText());
				c.registrarCurso(c);
				ListarTableCursos();
				LimpiarCajas();
			}
		});
		btn_Guardar.setBounds(21, 136, 89, 23);
		contentPane.add(btn_Guardar);
		
		txt_paralelo = new JTextField();
		txt_paralelo.setBounds(248, 82, 86, 20);
		contentPane.add(txt_paralelo);
		txt_paralelo.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Paralelo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setForeground(SystemColor.menu);
		lblNewLabel_1.setBounds(181, 83, 57, 14);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Curso c=new Curso();
				int fila = tableCursos.getSelectedRow();
				
				if (fila == -1) {
			        JOptionPane.showMessageDialog(null, "Seleccione una fila para actualizar");
			        return;
			    }
				
				c.setId((int) tableCursos.getValueAt(fila, 0)); 
				c.setCurso(txt_curso.getText());
				c.setParalelo(txt_paralelo.getText());
				c.ActualizarCurso(c);
				ListarTableCursos();
				LimpiarCajas();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(135, 136, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Curso c=new Curso();
				int fila = tableCursos.getSelectedRow();
				
				if (fila == -1) {
			        JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar");
			        return;
			    }
				
				c.setId((int)tableCursos.getValueAt(fila, 0));
				c.EliminarCurso(c);
				ListarTableCursos();
				LimpiarCajas();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton_1.setBounds(245, 136, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 189, 330, 169);
		contentPane.add(scrollPane);
		
		tableCursos = new JTable();
		tableCursos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tableCursos.rowAtPoint(e.getPoint());
				
				String curso= tableCursos.getValueAt(fila, 1).toString();
				String paralelo= tableCursos.getValueAt(fila, 2).toString();
				
				txt_curso.setText(curso);
				txt_paralelo.setText(paralelo);
			}
		});
		tableCursos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"ID", "CURSO", "PARALELO"
			}
		));
		scrollPane.setViewportView(tableCursos);
		
		JLabel lblNewLabel_2 = new JLabel("GESTION DE CURSOS");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_2.setForeground(SystemColor.menu);
		lblNewLabel_2.setBounds(57, 26, 264, 20);
		contentPane.add(lblNewLabel_2);
		ListarTableCursos();
	}



    	public void ListarTableCursos() {

    		Curso curso = new Curso();
    		List<Curso> listaCursos = curso.listarCursos();

    		DefaultTableModel model = (DefaultTableModel) tableCursos.getModel();
    		model.setRowCount(0); // Clear existing rows

    		for (Curso cursos : listaCursos) {
    			model.addRow(new Object[] {
    				cursos.getId(),
    				cursos.getCurso(),
    				cursos.getParalelo()	       		
    			});
    			}
    	}
    
    
    	public void LimpiarCajas() {
    		txt_curso.setText("");
    		txt_paralelo.setText("");
    	}

}
