package game_entities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import fields.*;

/**
 * Creates a Monopoly board.
 * @author Nichlas N. Pilemand
 *
 */
public class Board {
	
	private static final String CSV_FILE = "src/fields/fieldData.csv";
	public AbstractFields[] fields; 
	
	public Board(){
		this.fields = new AbstractFields[40];
		createFields();
	}
	
	
	/**
	 * Creates fields based on the input .csv file
	 */
	private void createFields() {
		String line = "";
		String splitBy = ";";
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(CSV_FILE));
			int i = 0;
			while((line = br.readLine()) != null) {
				String[] field = line.split(splitBy);
				/* .csv indicies:
				 * 0: Fieldnumber
				 * 1: Field Name
				 * 2: Buy price
				 * 3 - 9: Rent prices, and more
				 * 10: House price
				 */
				switch(field[1]) {
				case "Territory":
					fields[i] = new Territory(Integer.parseInt(field[0]),
							field[2], 
							Integer.parseInt(field[3]), 
							Integer.parseInt(field[10]),
								new int[]{
										Integer.parseInt(field[4]),
										Integer.parseInt(field[5]),
										Integer.parseInt(field[6]),
										Integer.parseInt(field[7]),
										Integer.parseInt(field[8]),
										Integer.parseInt(field[9])
								}
					);
					break;
				case "Brewery":
					fields[i] = new Brewery(Integer.parseInt(field[0]), field[2], Integer.parseInt(field[3]), Integer.parseInt(field[4]));
					break;
				case "Shipping":
					fields[i] = new Shipping(Integer.parseInt(field[0]), field[2],Integer.parseInt(field[3]), Integer.parseInt(field[4]));
					break;
				case "Chance":
					fields[i] = new Chance(Integer.parseInt(field[0]));
					break;
				case "Empty":
					fields[i] = new Empty(Integer.parseInt(field[0]), field[1]);
					break;
				case "FlatTax":
					fields[i] = new FlatTax(Integer.parseInt(field[0]),field[2], Integer.parseInt(field[3]));
					break;
				case "Tax":
					fields[i] = new Tax(Integer.parseInt(field[0]), field[2], Integer.parseInt(field[3]), Integer.parseInt(field[4]));
					break;
				case "GoToJail":
					fields[i] = new GoToJail(Integer.parseInt(field[0]), field[2]);
					break;
				default:
					//First line is headers.
					i--;
				}
				
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
		
	}
	
	public AbstractFields[] getFields() {
		return fields;
	}

}
