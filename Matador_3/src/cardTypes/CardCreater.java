package cardTypes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CardCreater {

	private SuperCard[] chanceCard = new SuperCard[33];
	private static final String CHANCE_FILE = "src/game_boundaries/GUItext.csv";

	private void chanceReader() {

		String line = "";
		String splitBy = ";";
		BufferedReader br = null;

		try {
			br = new BufferedReader(new FileReader(CHANCE_FILE));
			int i = 1;
			while((line = br.readLine()) != null) {
				String[] card = line.split(splitBy);

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
					chanceCard[i] = new JailCard(Integer.parseInt(card[0]), card[2]);
					
					i++;
				}
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

}
