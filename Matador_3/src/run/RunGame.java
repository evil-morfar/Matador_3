package run;
import desktop_resources.GUI;
import game_controller.MainController;
import game_entities.Board;

public class RunGame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board board = new Board();
		GUI.create(board.getGuiFields());
		GUI.addPlayer("Name", 100);
		MainController game = new MainController();
		//game.run();
	}

}
