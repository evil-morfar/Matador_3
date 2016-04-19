package game_entities;

public class Account {

	private int balance;
	
	public Account (int startingBalance){
		this.balance = startingBalance; 
		
	}
	
	
	public  int getBalance(){
		return balance;
	}
	
	public void deposit(int value){
	
		balance = balance + value;
	}

	public void withdraw(int value){
		
		balance = balance - value;
		
	}
	
	
}
