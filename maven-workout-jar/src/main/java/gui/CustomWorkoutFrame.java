package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class CustomWorkoutFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public CustomWorkoutFrame() {
		try {
			createCustomWorkoutFrame();
			setVisible(true);
			setTitle("Create Custom Workout - Workout Generator");
			setIconImage(new ImageIcon(getClass().getResource("/gui/Assets/AppIcon.png")).getImage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the frame.
	 */
	public void createCustomWorkoutFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("My Name is:");
		lblNewLabel.setBounds(193, 10, 211, 130);
		contentPane.add(lblNewLabel);
	}

}
