package improve.my.city.central.login;

import java.util.InputMismatchException;
import java.util.Scanner;

import improve.my.city.exceptions.CannotFindUserException;

public class Connector {
    private static Connector instance;
    private static String id;
    private static int password;
    private Connector() {
    }
    public static Connector getInstance (){
		if(instance == null){
			instance = new Connector();
		}
		return instance;
	}

    public String connect(ConnectionType strategy, Scanner input, Object list ){
        int state = 0;
        boolean loginResult = false;
        do{
            state = 0;
            try{
                input.nextLine();
                System.out.println("\nInforme sua identificação:\n");
                id = input.nextLine();
                System.out.println("\nInforme sua senha:\n" );
                password = input.nextInt(); 
                input.nextLine();
            }catch (InputMismatchException e){
                System.out.println("\nInput inválido! \n");
                state = 1;
            }
            if(state != 1){
                try {
                    loginResult = strategy.login(list, id, password);
                    if(loginResult == true){
                        return id;
                    } else{
                        return "fail";
                    }
                } catch (CannotFindUserException e) {
                    System.out.println(e.getMessage());
                }
            }
        }while(state == 1);
        return "fail";
    }
    
}
