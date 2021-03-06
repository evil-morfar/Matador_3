package game.entities;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import desktop_fields.Brewery;
import desktop_fields.Chance;
import desktop_fields.Field;
import desktop_fields.Jail;
import desktop_fields.Refuge;
import desktop_fields.Shipping;
import desktop_fields.Start;
import desktop_fields.Street;
import desktop_fields.Tax;
import game.entities.fields.AbstractFields;
import game.entities.fields.AbstractOwnable;
import game.entities.fields.GoToJail;
import game.entities.fields.Territory;

/**
 * Creates a Monopoly board.
 * @author Nichlas
 *
 */
public class Board {

	private static final String CSV_FILE = "src/game/entities/fields/fieldData.csv";
	public AbstractFields[] fields; 
	private Field[] guiFields; // TODO Create these

	public Board(){
		this.fields = new AbstractFields[40];
		this.guiFields = new Field[40];
		createFields();
	}

	/**
	 * Gets the total amount of fields owned by the player
	 * @param player The player to check for
	 * @return The number of owned fields.
	 */
	public int getNumOwnedFields(Player player) {
		int num = 0;
		for(AbstractFields field: fields)
			if(field instanceof AbstractOwnable)
				if(((AbstractOwnable) field).getOwner() == player)
					num++;
		return num;
	}

	/**
	 * Gets the total value of the fields owned by the player 
	 * @param player
	 * @return The total value of the players owned fields
	 */

	public int getValueOfFields(Player player) {
		int value = 0;
		for(AbstractFields field: fields)
			if(field instanceof AbstractOwnable)
				if(((AbstractOwnable) field).getOwner() == player)
					value += ((AbstractOwnable) field).getPrice();
		return value;
	}

	public int getValueHouses(Player player) {
		int valueH = 0;
		for(AbstractFields field: fields)
			if(field instanceof AbstractOwnable)
				if(((AbstractOwnable) field).getOwner() == player)
					valueH += ((Territory) field).getHousePrice();
		return valueH;
	}


	/**
	 * Returns the number of Shipping fields owned by the player.
	 * @param player The player to check for
	 * @return The number of owned ships.
	 */
	public int getNumOwnedShips(Player player) {
		int num = 0;
		for(int i = 0; i < fields.length; i++){
			if(fields[i] instanceof game.entities.fields.Shipping)
				if(((AbstractOwnable) fields[i]).getOwner() == player)
					num++;
		}
		return num;
	}

	/**
	 * @param player The player to check for
	 * @return Number of owned Breweries
	 * @see Board#getNumOwnedShips(Player)
	 */
	public int getNumOwnedBreweries(Player player) {
		int num = 0;
		for(int i = 0; i < fields.length; i++)
			if(fields[i] instanceof game.entities.fields.Brewery)
				if(((AbstractOwnable) fields[i]).getOwner() == player)
					num++;
		return num;
	}

	/**
	 * Calculates the number of fields a player owns of a particular color
	 * @param player The player to check for
	 * @param color The color to check for
	 * @return The number of owned field (0-3)
	 */
	public int getNumOwnedSameColor(Player player, String color) {
		int num = 0;
		for (int i = 0; i < fields.length; i++)
			if (fields[i] instanceof Territory)				
				if(((AbstractOwnable) fields[i]).getOwner() == player)
					if( ((Territory) fields[i]).getColor().equals(color))
						num++;
		return num;
	}

	/**
	 * Gets the number of fields of a particular color.
	 * @param color The color to check for.
	 * @return The number of fields of that color.
	 */
	public int getNumColorFields(String color){
		int num = 0;
		for(int i = 0; i < fields.length; i++)
			if (fields[i] instanceof Territory)	
				if( ((Territory) fields[i]).getColor().equals(color))
					num++;
		return num;
	}

	/**
	 * Creates fields based on the input .csv file
	 * Note: Due to naming conventions in our program and the GUI,
	 * we're explicitly naming our objects as it's easier.
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
				 * 1: Field type
				 * 2: Field name
				 * 3: Buy price
				 * 4 - 9: Rent prices, and more
				 * 10: House price
				 * 11: Color
				 */
				switch(field[1]) {
				case "Territory":
					fields[i] = new Territory(Integer.parseInt(field[0]),
							field[2], 
							Integer.parseInt(field[3]), 
							Integer.parseInt(field[10]),
							field[11],
							new int[]{
									Integer.parseInt(field[4]),
									Integer.parseInt(field[5]),
									Integer.parseInt(field[6]),
									Integer.parseInt(field[7]),
									Integer.parseInt(field[8]),
									Integer.parseInt(field[9])										
					}
							);
					// Stupid way Java needs to get a color from a string
					Color color = Color.white;
					java.lang.reflect.Field f;
					try {
						f = Class.forName("java.awt.Color").getField(field[11].toLowerCase());
						color = (Color)f.get(null);
					} catch (Exception e) {
						e.printStackTrace();
					}
					guiFields[i] = new Street.Builder()
							.setTitle(field[2])
							.setDescription(field[2])
							.setRent(field[4]+",-")
							.setSubText(field[3]+",-")
							.setBgColor(color)
							.build();
					break;
				case "Brewery":
					fields[i] = new game.entities.fields.Brewery(Integer.parseInt(field[0]), field[2], Integer.parseInt(field[3]), Integer.parseInt(field[4]));
					guiFields[i] = new Brewery.Builder()
							.setTitle(field[2])
							.setDescription(field[2])
							.setSubText(field[3]+",-")
							.setRent(field[4]+",-")
							.setBgColor(Color.darkGray)
							.build();

					break;
				case "Shipping":
					fields[i] = new game.entities.fields.Shipping(Integer.parseInt(field[0]), field[2],Integer.parseInt(field[3]), Integer.parseInt(field[4]));
					guiFields[i] = new Shipping.Builder()
							.setTitle(field[2])
							.setSubText(field[3]+",-")
							.setDescription(field[2])
							.setRent(field[4])
							.build();
					break;
				case "Chance":
					fields[i] = new game.entities.fields.Chance(Integer.parseInt(field[0]));
					guiFields[i] = new Chance.Builder()
							//.setBgColor(Color.darkGray)
							.build();
					break;
				case "Empty":
					fields[i] = new game.entities.fields.Empty(Integer.parseInt(field[0]), field[2]);
					if (i == 0) //Start needs special treatment in the GUI
						guiFields[i] = new Start.Builder()
						.setTitle("Start")
						.setSubText("")
						.build();
					else if (i == 10) // so does jail
						guiFields[i] = new Jail.Builder()
						.setSubText(field[2])
						.build();
					else	
						guiFields[i] = new Refuge.Builder()
						.setTitle(field[2])
						.setSubText(field[2])
						.build();
					break;
				case "FlatTax":
					fields[i] = new game.entities.fields.FlatTax(Integer.parseInt(field[0]),field[2], Integer.parseInt(field[3]));
					guiFields[i] = new Tax.Builder()
							.setTitle(field[2])
							.setSubText(field[2])
							.setDescription("2.000 kr")
							.build();
					break;
				case "Tax":
					fields[i] = new game.entities.fields.Tax(Integer.parseInt(field[0]), field[2], Integer.parseInt(field[3]), Integer.parseInt(field[4]));
					guiFields[i] = new Tax.Builder()
							.setTitle(field[2])
							.setSubText(field[2])
							.setDescription("4000 / 10%")
							.build();
					break;
				case "GoToJail":
					fields[i] = new GoToJail(Integer.parseInt(field[0]), field[2]);
					guiFields[i] = new Refuge.Builder()
							.setTitle(field[2])
							.setSubText(field[2])
							.build();					
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

	public Field[] getGuiFields(){
		return this.guiFields;
	}

}
