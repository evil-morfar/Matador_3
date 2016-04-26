package tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ReaderTest {
	
private static final String GUI_FILE = "src/game_boundaries/GUItext.csv";
	
  public static void main(String[] args) {
    // TODO Auto-generated method stub/ indlæs fil med tekster
	  
	  String[] GUIline;
		GUIline = new String[40];

		String line = "";
		String splitBy = "#";
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(GUI_FILE));
			int i = 0;
			while((line = br.readLine()) != null) {
				String[] field = line.split(splitBy);
				
				 GUIline[i] = field[0];
				i++;
			}
		
			System.out.println(GUIline[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} finally {
			if(br != null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
  }

}
}
