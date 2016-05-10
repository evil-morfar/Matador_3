package game.boundaries;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BoundaryReader {

	public BoundaryReader(){
		GUIreader();
	}
	
	private String[] GUIline = new String[40];

	private static final String GUI_FILE = "src/game/boundaries/GUItext.csv";


	private void GUIreader() {

		String line = "";
		String splitBy = "§";
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(GUI_FILE));
			int i = 1;
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
//		String test = String.format("%s har købt %s for %d","Bo","Cola",200);
//		System.out.println(test);
	}
	
	public String[] getGUIText() {
		return GUIline;
	}
}
