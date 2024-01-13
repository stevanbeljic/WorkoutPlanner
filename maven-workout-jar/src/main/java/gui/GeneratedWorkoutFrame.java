package gui;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.JButton;

public class GeneratedWorkoutFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JProgressBar progressBar;

	public GeneratedWorkoutFrame() {
		try {
			createGeneratedWorkoutFrame();
			setVisible(true);
			setTitle("Generate Workout - Workout Generator");
			setIconImage(new ImageIcon(getClass().getResource("/gui/Assets/AppIcon.png")).getImage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createGeneratedWorkoutFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 830, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		progressBar = new JProgressBar();
		progressBar.setMaximum(5);
		progressBar.setForeground(new Color(0, 120, 215));
		progressBar.setBounds(109, 463, 595, 24);
		progressBar.setValue(1);
		contentPane.add(progressBar);
		
		contentPane.add(new workoutSplitPanel());
	}
	
	private void updateProgressBar(int i) {
		progressBar.setValue(progressBar.getValue()+i);
	}
	
	public class workoutSplitPanel extends JPanel {
		
		private static final long serialVersionUID = 1L;

		public workoutSplitPanel() {
			setBounds(209, 109, 481, 210);
			setLayout(null);
			
			JLabel splitQuestionLabel = new JLabel("What workout split do you want to follow?", SwingConstants.CENTER);
			splitQuestionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			splitQuestionLabel.setBounds(35, 89, 318, 20);
			add(splitQuestionLabel);
			
			final JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.setBounds(80, 129, 220, 22);
			add(comboBox);
			
			final JButton nextButton = new JButton("Next");
			nextButton.setBounds(199, 176, 89, 23);
			nextButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.remove(workoutSplitPanel.this);
					contentPane.add(new freqPanel());
					updateProgressBar(1);
					contentPane.revalidate();
			        contentPane.repaint();
				}
			});
			add(nextButton);
			
			final JButton exitButton = new JButton("Exit");
			exitButton.setBounds(100, 176, 89, 23);
			exitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new Home();
					dispose();
					contentPane.revalidate();
			        contentPane.repaint();
				}
			});
			add(exitButton);
		}
	}
	
	public class freqPanel extends JPanel {
		
		private static final long serialVersionUID = 1L;

		public freqPanel() {
			setBounds(209, 109, 481, 210);
			setLayout(null);
			
			JLabel splitQuestionLabel = new JLabel("How often would you like to train?", SwingConstants.CENTER);
			splitQuestionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			splitQuestionLabel.setBounds(35, 89, 318, 20);
			add(splitQuestionLabel);
			
			final JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.setBounds(80, 129, 220, 22);
			add(comboBox);
			
			final JButton nextButton = new JButton("Next");
			nextButton.setBounds(199, 176, 89, 23);
			nextButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.remove(freqPanel.this);
					contentPane.add(new durationPanel());
					updateProgressBar(1);
					contentPane.revalidate();
			        contentPane.repaint();
				}
			});
			add(nextButton);
			
			final JButton backButton = new JButton("Back");
			backButton.setBounds(100, 176, 89, 23);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.remove(freqPanel.this);
					contentPane.add(new workoutSplitPanel());
					updateProgressBar(-1);
					contentPane.revalidate();
			        contentPane.repaint();
				}
			});
			add(backButton);
		}
	}
	
	public class durationPanel extends JPanel {
		
		private static final long serialVersionUID = 1L;

		public durationPanel() {
			setBounds(209, 109, 481, 210);
			setLayout(null);
			
			JLabel splitQuestionLabel = new JLabel("How long would you like each session to take?", SwingConstants.CENTER);
			splitQuestionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			splitQuestionLabel.setBounds(35, 89, 381, 20);
			add(splitQuestionLabel);
			
			final JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.setBounds(80, 129, 220, 22);
			add(comboBox);
			
			final JButton nextButton = new JButton("Next");
			nextButton.setBounds(199, 176, 89, 23);
			add(nextButton);
			
			final JButton backButton = new JButton("Back");
			backButton.setBounds(100, 176, 89, 23);
			backButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPane.remove(durationPanel.this);
					contentPane.add(new freqPanel());
					updateProgressBar(-1);
					contentPane.revalidate();
			        contentPane.repaint();
				}
			});
			add(backButton);
		}
	}
}
