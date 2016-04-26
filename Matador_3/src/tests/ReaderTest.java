package tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReaderTest {

	private static final String GUI_FILE = "src/game_boundaries/GUItext.csv";

	public static void main(String[] args) {
		// TODO Auto-generated method stub/ indl�s fil med tekster

		String[] GUIline;
		GUIline = new String[40];
		
		String line = "";
		String splitBy = "�";
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(GUI_FILE));
			int i = 0;
			while((line = br.readLine()) != null) {
				String[] field = line.split(splitBy);
		
				GUIline[i] = field[0];
				i++;
			}

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
String test = String.format("%s har k�bt %s for %d","Bo","Cola",200);
System.out.println(test);
	}
}
