package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class ReaderTest {

	//private static final String CSV_FILE = "src/game_boundaries/GUItext.csv";

	
	public static void main(String[] args) {
	
		 File file = new File("src/game_boundaries/GUItext.csv");
		
		
//		String line = "";
//		BufferedReader br = null;
		
//		try {
//			br = new BufferedReader(new FileReader(CSV_FILE));
//			int i = 0;
//			while((line = br.readLine()) != null) {	
//				
//				
//				i++;
//			}
//			
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			
//		} finally {
//			if(br != null)
//				try {
//					br.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//		
//		}
		
	    try {

	        Scanner scanner = new Scanner(file);
	        
	        while (scanner.hasNextLine()) {
	        	
	          String line = scanner.nextLine();
	          System.out.println(line);
	        }
	        scanner.close();
	      } catch (FileNotFoundException e) {
	        e.printStackTrace();
	      }
		
		
		
	}

	}


