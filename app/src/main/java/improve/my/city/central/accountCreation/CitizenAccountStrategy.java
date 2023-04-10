package improve.my.city.central.accountCreation;

import java.util.InputMismatchException;
import java.util.Scanner;

import improve.my.city.central.citizenView.CitizenList;
import improve.my.city.enums.Districts;
import improve.my.city.exceptions.CannotCreateAccountException;
import improve.my.city.exceptions.OutOfTheOptionsException;
import improve.my.city.user.Citizen;

public class CitizenAccountStrategy implements AccountCreationStrategy {
    // Implementação concreta da Strategy
    @Override
	public void create(Object citizenList, Scanner input) throws CannotCreateAccountException{
            int state = 0;
                do{
                    state = 0;
                    String name = null, cpf = null, address = null;
                        int password =0, option = 0;
                        CitizenList citizens = (CitizenList) citizenList;
                    
                    try{
                        input.nextLine();
                        System.out.println("\nInforme seu nome: \n");
                        name = input.nextLine();
                    
                        System.out.println("\nInforme sua identificação de cidadão (CPF): \n");
                        cpf = input.nextLine();
                    
                        System.out.println("\nInforme seu endereço: \n");
                        address = input.nextLine();
                    
                        System.out.println("\nCrie uma senha: \n");
                         password = input.nextInt();
                        }
                    catch(InputMismatchException e){
                            System.out.println("\nInput inválido! Por favor, insira o tipo de dado equivalente:");
                            System.out.println("Nome -> Apenas letras.");
                            System.out.println("Identificação -> Letras,números e símbolos.");
                            System.out.println("Senha -> Apenas números. \n");
                            state = 1;
                        }
                    
                    if(state != 1){
                        if(citizens.searchUser(cpf)){
                            throw new CannotCreateAccountException();
                        }
                        else{
                            Citizen citizen = Citizen.getInstance(name, password, cpf, address);
                            try{
                                System.out.println("\nSelecione um bairro");
                                System.out.println("1. Caxangá");
                                System.out.println("2. Dois Irmãos");
                                System.out.println("3. Iputinga");
                                System.out.println("4. Cordeiro");
                                System.out.println("5. Engenho do Meio");
                                System.out.println("6.  Várzea \n");
                                option = input.nextInt();
                                if(option < 1 || option > 6){
                                    throw new OutOfTheOptionsException();
                                } 
                            }
                            catch(InputMismatchException e){
                                System.out.println("\nInput inválido! Por favor, insira um número.\n");
                                input.nextLine();
                                state = 1;
                            }
                            catch(OutOfTheOptionsException p){
                                System.out.println(p.getMessage());
                                state = 1;
                            }
                            if(state != 1){
                                switch(option){
                                    case 1:
                                        citizen.setResidence(Districts.Caxangá);
                                    break;
                                    case 2:
                                        citizen.setResidence(Districts.DoisIrmãos);
                                    break;
                                    case 3:
                                        citizen.setResidence(Districts.Iputinga);
                                    break;
                                    case 4:
                                        citizen.setResidence(Districts.Cordeiro);
                                    break;
                                    case 5:
                                        citizen.setResidence(Districts.EngenhoDoMeio);
                                    break;
                                    case 6:
                                        citizen.setResidence(Districts.Várzea);
                                    break;
                                    default:
                                        System.out.println("\nOpção inválida!\n");
                                        state = 1;
                                    break;      
                                }
                            citizens.addUser(citizen);
                            System.out.println("\nCidadão adicionado.\n");
                            }
                        }	
                    }
                }while(state == 1); 
                
            }
        
        
}
