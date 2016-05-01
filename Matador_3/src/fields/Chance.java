package fields;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import game_controller.MainController;
import game_entities.Player;

/**
 * The NonOwnable Field, Chance. Several of these exists in a standard Monopoly game.
 * @author Nichlas N. Pilemand
 */
public class Chance extends AbstractNonOwnables {

	private static final String FIELD_TYPE = "Chance";

	/**
	 * Creates a NonOwnable Field of type Chance. Used to give random cards
	 * to players during a game.
	 * @param id The id of the field.
	 */
	public Chance(int id) {
		// Name and fieldType is the same.
		super(id, FIELD_TYPE, FIELD_TYPE);

	}

	private String[] chanceCard = new String[40];

	private static final String CHANCE_FILE = "src/game_boundaries/GUItext.csv";


	private void GUIreader() {

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
					
					break;
				case "MoveField":
					
					break;
				case "MoveJail":
					
					break;
				case "MoveFleet":
					
					break;
				case "Move":
					
					break;
				case "ReceivePlayers":

					break;
				case "JailCard":

					break;	
				case "Poor":





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

	/**
	 * Draws and executes a chance card on the player landing on the Field.
	 * @param controller Main game controller.
	 * @see AbstractFields#landOnField
	 */
	@Override
	public void landOnField(MainController controller) {
		// TODO Draw and execute a chance card on the player

	}

}
