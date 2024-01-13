package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel homePane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//FlatLightLaf.setup();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new FlatLightLaf());
					Home frame = new Home();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Home() {
		createHome();
		showHome();
	}

	/**
	 * Create the frame.
	 */
	public void createHome() {
		int paneWidth = 550;
		setIconImage(new ImageIcon(getClass().getResource("/gui/Assets/AppIcon.png")).getImage());
		setTitle("Home - Workout Generater");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, paneWidth, 300);
		homePane = new JPanel();

		setContentPane(homePane);
		homePane.setLayout(null);
		
		JButton btnCreateCustom = new JButton("Create a custom workout");
		btnCreateCustom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideHome();
				new CustomWorkoutFrame();				
			}
		});
		btnCreateCustom.setBounds(50, 154, 200, 26);
		homePane.add(btnCreateCustom);
		
		JButton btnGenerate = new JButton("Generate a workout");
		btnGenerate.setBounds(50 + 200 + 25, 154, 200, 26);
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hideHome();
				new GeneratedWorkoutFrame();
			}
		});
		homePane.add(btnGenerate);
		
		JLabel lblHome = new JLabel("Would you like to create a custom routine or have a workout generated for you?");
		lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblHome.setBounds(0, 83, paneWidth, 59);
		homePane.add(lblHome);
	}
	
	public void hideHome(){
		this.setVisible(false);
	}
	
	public void showHome() {
		this.setVisible(true);
	}
}
