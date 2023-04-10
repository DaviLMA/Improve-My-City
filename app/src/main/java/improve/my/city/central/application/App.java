package improve.my.city.central.application;

import java.util.InputMismatchException;
import java.util.Scanner;

import improve.my.city.central.accountCreation.AccountCreator;
import improve.my.city.central.accountCreation.CitizenAccountStrategy;
import improve.my.city.central.accountCreation.EmployeeAccountStrategy;
import improve.my.city.central.citizenView.CitizenList;
import improve.my.city.central.citizenView.CitizenMenu;
import improve.my.city.central.employeeView.EmployeeList;
import improve.my.city.central.employeeView.EmployeeMenu;
import improve.my.city.central.login.Connector;
import improve.my.city.central.login.LoginCitizenStrategy;
import improve.my.city.central.login.LoginEmployeeStrategy;
import improve.my.city.exceptions.OutOfTheOptionsException;
import improve.my.city.reports.ReportList;

public class App {
    
    public static void main(String [] args) throws OutOfTheOptionsException{

        Scanner input = new Scanner(System.in);
        CitizenList citizens = CitizenList.getInstance();
        EmployeeList employees = EmployeeList.getInstance();
        ReportList reports = new ReportList();
        AccountCreator creator = AccountCreator.getInstance();
        Connector login = Connector.getInstance();
        CitizenMenu citizenMenuFacade = CitizenMenu.getInstance();
        EmployeeMenu employeeMenuFacade = EmployeeMenu.getInstance();
        int choice = 0, option = 0, state = 0;
        boolean getBack = false;
        String loginResult;
        do{
            choice = 0;
            try{
                System.out.println("\n Selecione uma opção: ");
                System.out.println("1. Login.");
                System.out.println("2. Criar conta.");
                System.out.println("3. Encerrar programa.\n");
                choice = input.nextInt();
                if(choice > 3 || choice < 1){
                    throw new OutOfTheOptionsException();
                }
            }
            catch (InputMismatchException x){
                System.out.println("\nInput inválido! Por favor, insira apenas números.\n");
                input.nextLine();
            }
            catch (OutOfTheOptionsException e){
                System.out.println(e.getMessage());
            }
            switch(choice){
                case 1:
                do{ 
                    state = 0;
                    try{
                        option = 0;
                        getBack = false;
                        System.out.println("\n Selecione o tipo de usuário para login.");
                        System.out.println("1. Cidadão");
                        System.out.println("2. Funcionário");
                        System.out.println("3. Voltar\n");
                        option = input.nextInt();
                        
                        if(option > 3 || option < 1){
                            throw new OutOfTheOptionsException();
                        }
                        }
                        catch (InputMismatchException x){
                            System.out.println("\nInput inválido! Por favor, insira só números. \n");
                            input.nextLine();
                            state = 1;
                        }catch(OutOfTheOptionsException d){
                            System.out.println(d.getMessage());
                            state = 1;
                        }
                        if(option == 1){
                            loginResult = login.connect(new LoginCitizenStrategy(), input, citizens);
                            if(loginResult != "fail"){
                                citizenMenuFacade.showMenu(citizens.getUser(loginResult), reports);
                            }else{
                                System.out.println("\nNão foi possível fazer o login.\n");
                                state = 1;
                            }
                        }else if(option == 2){
                            loginResult = login.connect(new LoginEmployeeStrategy(), input, employees);
                            if(loginResult != "fail"){
                                employeeMenuFacade.showMenu(input, reports, employees.getUser(loginResult));
                            }
                        }else{
                            getBack = true;
                        }
                }while(state == 1 && getBack == false);
                break;
                case 2:
                    do{
                        state = 0;
                        try{
                            System.out.println("\nSelecione o tipo de usuário.");
                            System.out.println("1. Cidadão");
                            System.out.println("2. Funcionário");
                            System.out.println("3. Voltar\n");
                            option = input.nextInt();
                            if(option > 3 || option < 1){
                                throw new OutOfTheOptionsException();
                            }
    
                            if(option == 1){   // Client da Strategy
                                creator.create(new CitizenAccountStrategy(), citizens, input) ;                       
                            }else if (option == 2){ 
                                creator.create(new EmployeeAccountStrategy(), employees, input);
                            }else{

                            }
                        } catch(InputMismatchException x){
                            System.out.println("\nInput inválido! Por favor, insira apenas números.");
                            input.nextLine();
                            state = 1;
                        } catch(OutOfTheOptionsException z){
                            System.out.println(z.getMessage());
                            state = 1;
                        }
                   }while(state == 1 || option !=3);
                break;
                case 3:
                    System.out.println("\nEncerrando o programa...");
                break;
            }
        }while(choice != 3);
        input.close();
    
    }

    public Object getGreeting() {
        return null;
    }
}
