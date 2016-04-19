package game_entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import desktop_fields.Field;
import fields.AbstractFields;
import fields.Territory;

public class Board {
	
	private static final String CSV_FILE = "/fields/fieldData.csv";
	private AbstractFields[] fields; 
	
	
	private void createFields() {
		String line = "";
		String splitBy = ",";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(CSV_FILE));
			int i = 0;
			while((line = br.readLine()) != null) {
				String[] field = line.split(splitBy);
				
				switch(field[1]) {
				case "Territory":
					fields[i] = new Territory(field[2], Integer.parseInt(field[3]), Integer.parseInt(field[10]), int{field[4],field[5],field[6],field[7], field[8],field[9]})
				
				}
				
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
