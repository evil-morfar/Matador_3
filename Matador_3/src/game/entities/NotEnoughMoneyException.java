package game.entities;

/**
 * Exception for when an Account balance goes below 0.
 * @author Nichlas N. Pilemand
 *
 */
public class NotEnoughMoneyException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private int balance;
	private int requestedBalance;

	public NotEnoughMoneyException(int balance, int newBalance) {
		this.balance = balance;
		this.requestedBalance = newBalance;
	}

	public NotEnoughMoneyException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NotEnoughMoneyException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public NotEnoughMoneyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public NotEnoughMoneyException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	
	public int getBalance(){
		return balance;
	}
	public int getRequestedBalance(){
		return requestedBalance;
	}

}
