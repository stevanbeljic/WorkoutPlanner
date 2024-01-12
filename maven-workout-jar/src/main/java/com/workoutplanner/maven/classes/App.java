package com.workoutplanner.maven.classes;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * Hello world!
 *
 */
public class App {
	
	public static void main(String args[]){
		
		Scanner kb = new Scanner(System.in);
		
		System.out.print("How many exercises would you like to do: ");
		String nExercisesString = kb.nextLine();
		
		int nExercises;
		try {
			nExercises = Integer.valueOf(nExercisesString);
			if(nExercises < 1) {
				kb.close();
				throw new NumberFormatException("Provided argument less than 1");
			}
		} catch(NumberFormatException e) {
			System.out.println("ERROR: Must be a digit greater or equal to 1.");
			return;
		}
		
		Exercise e[] = new Exercise[nExercises];
		for(int i = 0; i < nExercises; i++) {
			String name;
			int sets, reps;
			try {
				System.out.print("What is the name: ");
				name = kb.nextLine();
				
				System.out.print("How many sets: ");
				String stringSets = kb.nextLine();
				sets = Integer.valueOf(stringSets);
				
				System.out.print("How many reps: ");
				String stringReps = kb.nextLine();
				reps = Integer.valueOf(stringReps);
			
			} catch (NumberFormatException f) {
				System.out.println("ERROR: Invalid arguments provided");
				continue;
			}
			
			e[i] = new Exercise(name, sets, reps);
			
		}
		
		for(int i = 0; i < nExercises; i++) {
			e[i].print();
		}
		
		System.out.print("Write to sheet? [Y/N]: ");
		char c = kb.next().charAt(0);
		
		if(c == 'Y') writeToSheet(e);
		kb.close();
		
	}
	
	public static void writeToSheet(Exercise e[]) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Basic");

            // Headers
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Exercise Name");

            // Data
            for (int i = 0; i < e.length; i++) {
                Row dataRow = sheet.createRow(i + 1);
                dataRow.createCell(0).setCellValue(e[i].getName());
            }

            // Save the workbook to a file
            try (FileOutputStream fileOut = new FileOutputStream("workout_plan.xlsx")) {
                workbook.write(fileOut);
            }

            System.out.println("Data written successfully to workout_plan.xlsx");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
