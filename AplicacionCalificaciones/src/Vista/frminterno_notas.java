package Vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

public class frminterno_notas extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frminterno_notas frame = new frminterno_notas();
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
	public frminterno_notas() {
		setBounds(0, 0, 1062, 611);

	}

}
