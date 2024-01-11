package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

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
		panel.setBounds(0, 0, 816, 150);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblExName = new JLabel("Exercise Name:");
		lblExName.setBounds(26, 63, 83, 14);
		panel.add(lblExName);
		
		final JPanel setsPanel = new JPanel();
		setsPanel.setBounds(237, 11, 350, 128);
		panel.add(setsPanel);
		
		exTextField = new JTextField();
		exTextField.setBounds(119, 60, 96, 20);
		panel.add(exTextField);
		exTextField.setColumns(10);
		
		JCheckBox chckbxEqualReps = new JCheckBox("Equal reps per set?");
		chckbxEqualReps.setSelected(true);
		chckbxEqualReps.setBounds(62, 127, 153, 23);
		panel.add(chckbxEqualReps);
		
		final JButton btnAddEx = new JButton("Add Exercise");
		btnAddEx.setEnabled(false);
		
		String setArray[] = {"0","1","2","3","4","5","6","7","8","9","10"};
		final JComboBox comboBoxSetAmount = new JComboBox(setArray);
		comboBoxSetAmount.setSelectedIndex(0);
		comboBoxSetAmount.setBounds(140, 91, 60, 22);
		panel.add(comboBoxSetAmount);
		comboBoxSetAmount.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent ae) {
	            int numOfSets = Integer.valueOf(comboBoxSetAmount.getSelectedItem().toString());
	            updateSetsPanel(setsPanel, numOfSets);
	        }
	    });
		comboBoxSetAmount.addFocusListener(new FocusAdapter() {
        	@Override
        	public void focusLost(FocusEvent e) {
        		if(comboBoxSetAmount.getSelectedIndex() != 0) {
        			btnAddEx.setEnabled(true);
        		}
        	}
        });
		
		
		btnAddEx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int numOfSets = Integer.valueOf(comboBoxSetAmount.getSelectedItem().toString());
				int nSets = Integer.valueOf(comboBoxSetAmount.getSelectedItem().toString());
				int reps[] = new int[nSets];
				
				Component[] components = setsPanel.getComponents();
				for (int i = 0; i < numOfSets; i++) {
		            if (components[i] instanceof JTextField) {
		                JTextField repTextField = (JTextField) components[i];
		                try {
		                    reps[i] = Integer.parseInt(repTextField.getText());
		                } catch (NumberFormatException e) {
		                    reps[i] = 0;
		                }
		            }
		        }
				
				Exercise e = new Exercise(exTextField.getText(), reps);
				exerciseArray.add(e);
				updateExerciseScrollPane(exScrollPane);
				
				exTextField.setText("");
				comboBoxSetAmount.setSelectedIndex(0);
				btnAddEx.setEnabled(false);
			}
		});
		btnAddEx.setBounds(627, 59, 114, 23);
		panel.add(btnAddEx);
		
		
		JLabel lblNumSets = new JLabel("Number of sets:");
		lblNumSets.setBounds(26, 95, 119, 14);
		panel.add(lblNumSets);
	}
	
	private void updateExerciseScrollPane(JScrollPane exScrollPane) {
	    JPanel viewportView = new JPanel();
	    viewportView.setLayout(new BoxLayout(viewportView, BoxLayout.Y_AXIS));

	    for (Exercise e : exerciseArray) {
	        ExerciseCard exerciseCard = new ExerciseCard(e);
	        exerciseCard.setPreferredSize(new Dimension(830, 100));
	        viewportView.add(exerciseCard);
	    }

	    exScrollPane.setViewportView(viewportView);
	    exScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    exScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    exScrollPane.revalidate();
	}
	
	private void updateSetsPanel(JPanel setsPanel, int numOfSets) {
	    setsPanel.removeAll();

	    for (int i = 1; i <= numOfSets; i++) {

	        JTextField repTextField = new JTextField("Set "+i);
	        repTextField.addFocusListener(new FocusAdapter() {
	        	@Override
	        	public void focusGained(FocusEvent e) {
	        		JTextComponent txtComponent = (JTextComponent)e.getSource();
	        		txtComponent.selectAll();
	        	}
	        });
	        repTextField.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyTyped(KeyEvent e) {
	                if(!Character.isDigit(e.getKeyChar())) {
	                	e.consume();
	                }
	            }
	        });
	        setsPanel.add(repTextField);

	    }

	    setsPanel.revalidate();
	    setsPanel.repaint();
	}
}
