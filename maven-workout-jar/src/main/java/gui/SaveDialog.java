package gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fileNameField;
	
	private String textExtension = ".txt";
	private String workbookExtension = ".xlsx";
	
	private ArrayList<Exercise> exercises;
	
	public SaveDialog(ArrayList<Exercise> listedExercises) {
		try {
			exercises = listedExercises;
			createSaveDialog();
			setVisible(true);
			setTitle("Save As - Workout Generator");
			setIconImage(new ImageIcon(getClass().getResource("/gui/Assets/AppIcon.png")).getImage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JRadioButton saveWorksheetRadio;
	private JRadioButton saveTextButton;
	private JButton saveButton;

	public void createSaveDialog() {
		setTitle("Save As - Workout Generator");
		setAlwaysOnTop(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 174);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		saveWorksheetRadio = new JRadioButton("Worksheet");
		saveWorksheetRadio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enableSaveButton();
			}
		});
		saveWorksheetRadio.setBounds(37, 40, 111, 23);
		contentPane.add(saveWorksheetRadio);
		
		saveTextButton = new JRadioButton("Text Document");
		saveTextButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				enableSaveButton();
			}
		});
		saveTextButton.setBounds(37, 65, 111, 23);
		contentPane.add(saveTextButton);
		
		saveButton = new JButton("Save");
		saveButton.setEnabled(false);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(saveTextButton.isSelected()) {
					saveText();
					dispose();
				}
				
				if(saveWorksheetRadio.isSelected()) {
					saveWorksheet();
					dispose();
				}
			}
		});
		saveButton.setBounds(300, 109, 89, 23);
		contentPane.add(saveButton);
		
		JLabel saveLabel = new JLabel("Select how to save the workout");
		saveLabel.setBounds(32, 11, 191, 14);
		contentPane.add(saveLabel);
		
		fileNameField = new JTextField();
		fileNameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				enableSaveButton();
			}
		});
		fileNameField.setBounds(32, 110, 225, 20);
		contentPane.add(fileNameField);
		fileNameField.setColumns(10);
		
		JLabel fileNameLabel = new JLabel("Please provide a file name (without extensions)");
		fileNameLabel.setBounds(32, 95, 245, 14);
		contentPane.add(fileNameLabel);
	}
	
	public void enableSaveButton() {
		if((saveTextButton.isSelected() || saveWorksheetRadio.isSelected()) && !fileNameField.getText().isBlank()){
			saveButton.setEnabled(true);
		} else {
			saveButton.setEnabled(false);
		}
	}
	
	public void saveText() {
		String fileName = fileNameField.getText();
		if(!fileName.endsWith(textExtension)) {
			fileName = fileName.concat(textExtension);
		}
		
		File file = new File(fileName);
		try {
			if(!file.createNewFile()) {
				file.delete();
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		try {
			FileWriter fileW = new FileWriter(fileName);
			fileW.write("Your Workout Plan\n\n");
			
			for(Exercise e : exercises) {
				fileW.write(e.getName()+"\n");
				ArrayList<Set> sets = e.getSets();
				for(int i = 0; i < sets.size(); i++) {
					fileW.write("\t Set "+(i+1)+": "+sets.get(i).getReps()+" repetitions\n");
				}
				fileW.write("\n");
			}
			fileW.close();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public void saveWorksheet() {
		String fileName = fileNameField.getText();
		if(!fileName.endsWith(workbookExtension)) {
			fileName = fileName.concat(workbookExtension);
		}
		try {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Your Workout");
			
			XSSFRow labelsRow = sheet.createRow(0);
			labelsRow.createCell(0).setCellValue("Your Workout Plan");
			
			int rowCount = 2;
			
			for(Exercise e : exercises) {
				XSSFRow eRow = sheet.createRow(rowCount);
				eRow.createCell(0).setCellValue(e.getName());
				ArrayList<Set> sets = e.getSets();
				rowCount++;
				
				for(int i = 0; i < sets.size(); i++, rowCount++) {
					XSSFRow sRow = sheet.createRow(rowCount);
					sRow.createCell(1).setCellValue("Set "+ (i+1) +":");
					sRow.createCell(2).setCellValue(sets.get(i).getReps());
				}
				
				rowCount++;
				
			}
			
			FileOutputStream fileOut = new FileOutputStream(fileName);
            workbook.write(fileOut);
            
			fileOut.close();
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
