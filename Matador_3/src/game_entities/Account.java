package game_entities;

/**
 * 
 * @author PC / Valdemar
 *
 */

public class Account {

	private int balance;

	public Account (int startingBalance){
		this.balance = startingBalance; 

	}

	public  int getBalance(){
		return balance;
	}

	public void setBalance(int value){

		balance =value;

	}

	public void deposit(int value){

		balance = balance + value;
	}

	public void withdraw(int value) throws NotEnoughMoneyException{
		if (balance - value < 0 ){
			throw new NotEnoughMoneyException(balance, balance-value);
		}
		balance -= value;
		
	}


}
