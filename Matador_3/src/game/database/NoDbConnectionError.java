package game.database;

public class NoDbConnectionError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoDbConnectionError(){
		System.out.println("Couldn't establish connection to the Database");
	}

}
