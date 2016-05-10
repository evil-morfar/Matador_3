package tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import game_entities.cards.BalanceChange;
import game_entities.cards.JailCard;
import game_entities.cards.MoveField;
import game_entities.cards.MoveFleet;
import game_entities.cards.MoveJail;
import game_entities.cards.PayHouses;
import game_entities.cards.Poor;
import game_entities.cards.SuperCard;

/**
 * made to test if the cards are written properly, so this class doesn't shuffle the cards when inserting them into an array
 * @author Henrik
 *
 */

public class CardCreaterMockClass {

	private SuperCard[] chanceCard;
	private static final String CHANCE_FILE = "src/cardTypes/ChanceCardData.csv";

	public CardCreaterMockClass() {
		this.chanceCard = new SuperCard[33];
		chanceReader();
	}


	private void chanceReader() {

		String line = "";
		String splitBy = ";";
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(CHANCE_FILE));
			int i = 0;
			while((line = br.readLine()) != null) {
				String[] card = line.split(splitBy);
				/* index 0: fieldnumber 
				index 1: Card type 
				index 2:  Card description 
				index 3: amount 
				index 4: move amount 
				 ** for "payhouses" index 3 and 4 is both used for amount calculations **
				 */
				switch(card[1]) {

				case "BalanceChange":
					chanceCard[i] = new BalanceChange(Integer.parseInt(card[0]), card[2],Integer.parseInt(card[3]));
					break;

				case "PayHouses":
					chanceCard[i] = new PayHouses(Integer.parseInt(card[0]), card[2],Integer.parseInt(card[3]), Integer.parseInt(card[4]));
					break;

				case "MoveField":
					chanceCard[i] = new MoveField(Integer.parseInt(card[0]), card[2], Integer.parseInt(card[4]));
					break;

				case "MoveJail":
					chanceCard[i] = new MoveJail(Integer.parseInt(card[0]), card[2]);
					break;

				case "MoveFleet":
					chanceCard[i] = new MoveFleet(Integer.parseInt(card[0]), card[2]);
					break;

				case "JailCard":
					chanceCard[i] = new JailCard(Integer.parseInt(card[0]), card[2]);
					break;	

				case "Poor":
					chanceCard[i] = new Poor(Integer.parseInt(card[0]), card[2]);
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

	public SuperCard[] getCards(){
		return this.chanceCard;
	}

}