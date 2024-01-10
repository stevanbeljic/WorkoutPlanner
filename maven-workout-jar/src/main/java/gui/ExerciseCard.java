package gui;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ExerciseCard extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ExerciseCard(Exercise e) {
		setForeground(new Color(0, 0, 0));
		setBounds(0, 0, 830, 100);
		setLayout(null);
		
		JLabel exNameLabel = new JLabel(e.getName());
		exNameLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		exNameLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 12));
		exNameLabel.setBounds(24, 24, 174, 46);
		add(exNameLabel);
		
		JLabel lblSets = new JLabel("SET INFO GOES HERE");
		lblSets.setHorizontalAlignment(SwingConstants.CENTER);
		lblSets.setBounds(488, 24, 174, 46);
		add(lblSets);

	}

}
