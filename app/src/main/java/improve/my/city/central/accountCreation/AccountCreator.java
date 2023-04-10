package improve.my.city.central.accountCreation;

import java.util.Scanner;

import improve.my.city.exceptions.CannotCreateAccountException;

public class AccountCreator {
    private static AccountCreator creator;
	// Context da Strategy

	private AccountCreator(){

	}
	public static AccountCreator getInstance (){
		if(creator == null){
			creator = new AccountCreator();
		}
		return creator;
	}

	public void create (AccountCreationStrategy strategy, Object list, Scanner input){
		try{
			strategy.create(list, input);	
		}catch(CannotCreateAccountException e){
			System.out.println(e.getMessage());
		}
	}
    	
}
