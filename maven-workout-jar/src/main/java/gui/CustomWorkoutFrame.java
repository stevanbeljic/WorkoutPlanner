package gui;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class CustomWorkoutFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField exTextField;

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
	
	private final ArrayList<Exercise> exerciseArray = new ArrayList();
	
	/**
	 * Create the frame.
	 */
	public void createCustomWorkoutFrame() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JScrollPane exScrollPane = new JScrollPane();
		exScrollPane.setBounds(0, 163, 830, 400);
		contentPane.add(exScrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 830, 150);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblExName = new JLabel("Exercise Name:");
		lblExName.setBounds(26, 11, 83, 14);
		panel.add(lblExName);
		
		exTextField = new JTextField();
		exTextField.setBounds(119, 8, 96, 20);
		panel.add(exTextField);
		exTextField.setColumns(10);
		
		JButton btnAddEx = new JButton("Add Exercise");
		btnAddEx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Exercise e = new Exercise(exTextField.getText(), 3, 12);
				exerciseArray.add(e);
				updateExerciseScrollPane(exScrollPane);
			}
		});
		btnAddEx.setBounds(627, 59, 114, 23);
		panel.add(btnAddEx);
	}
	
	private void updateExerciseScrollPane(JScrollPane exScrollPane) {
		JPanel exercisePanel = new JPanel();
		exercisePanel.setLayout(null);

		// Add ExerciseCards for all exercises in the list
		int yOffset = 0;
		for (Exercise e : exerciseArray) {
			ExerciseCard exerciseCard = new ExerciseCard(e);
			exerciseCard.setBounds(0, yOffset, 830, exerciseCard.getPreferredSize().height);
			exercisePanel.add(exerciseCard);
			yOffset += exerciseCard.getPreferredSize().height;
		}

		exScrollPane.setViewportView(exercisePanel);
		exScrollPane.revalidate();
	}
}
