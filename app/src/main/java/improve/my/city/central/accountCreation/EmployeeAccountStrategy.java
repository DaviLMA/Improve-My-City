package improve.my.city.central.accountCreation;

import java.util.InputMismatchException;
import java.util.Scanner;

import improve.my.city.central.employeeView.EmployeeList;
import improve.my.city.enums.Institutions;
import improve.my.city.exceptions.CannotCreateAccountException;
import improve.my.city.exceptions.OutOfTheOptionsException;
import improve.my.city.user.Employee;

public class EmployeeAccountStrategy implements AccountCreationStrategy  {
    //Implementação Concreta da Strategy
    @Override
	public void create(Object employeeList, Scanner input) throws CannotCreateAccountException{
        
            int state = 0;
            String name = null, id = null;
            int password = 0, option = 0;
            EmployeeList employees = (EmployeeList) employeeList;
            do{
                state = 0;
                try{
            
                input.nextLine();
                System.out.println("\nInforme seu nome: \n");
                name = input.nextLine();
                    
                System.out.println("\nInforme sua identicação de funcionário: \n");
                id = input.nextLine();
                    
                System.out.println("\nCrie uma senha: \n");
                password = input.nextInt();
                }
                catch(InputMismatchException e){
                    System.out.println("\nInput inválido! Por favor, insira o tipo de dado equivalente:");
                    System.out.println("Nome -> Apenas letras.");
                    System.out.println("Identificação -> Letras,números e símbolos.");
                    System.out.println("Senha -> Apenas números. \n");
                    input.nextLine();
                    state = 1;
                }
            
                if(state !=1 ){
                    if(employees.searchUser(id)){
                        throw new CannotCreateAccountException();
                    }
                    else{
                        try{
                        input.nextLine();
                        System.out.println("\n Selecione a instituição da qual pertence: ");
                        System.out.println("1. Polícia.");
                        System.out.println("2. Corpo de Bombeiros.");
                        System.out.println("3. Vigilância Sanitária.");
                        System.out.println("4. Centro de Controle de Zoonoses.");
                        System.out.println("5. Autarquia de Manutenção e Limpeza Urbana do Recife.");
                        System.out.println("6. CTTU.");
                        System.out.println("7. Celpe.");
                        System.out.println("8. Compesa.");
                        System.out.println("9. Limpa Brasil.");
                        System.out.println("10. Associação do Protetores de Animais de Pernambuco.\n");
                        option = input.nextInt();
                        if(option < 1 || option > 10){
                            throw new OutOfTheOptionsException();
                        }
                        input.nextLine();
                        }
                        catch(InputMismatchException o){
                            System.out.println("\nInput inválido! Por favor, insira um número.\n");
                            input.nextLine();
                            state = 1;
                        }catch(OutOfTheOptionsException s){
                            System.out.println(s.getMessage());
                            state = 1;
                        }
                        if(state != 1){
                            Employee employee = Employee.getInstance(id, name, password);
                            switch(option){
                                case 1:
                                    employee.setInstitution(Institutions.Polícia);
                                break;
                                case 2:
                                    employee.setInstitution(Institutions.CorpoDeBombeiros);
                                break;
                                case 3:
                                    employee.setInstitution(Institutions.VigilânciaSanitária);
                                break;
                                case 4:
                                    employee.setInstitution(Institutions.CentroDeControleDeZoonoses);
                                break;
                                case 5:
                                    employee.setInstitution(Institutions.AutarquiaDeManutençãoELimpezaUrbanaDoRecife);
                                break;
                                case 6:
                                    employee.setInstitution(Institutions.CTTU);
                                break;
                                case 7:
                                    employee.setInstitution(Institutions.Celpe);
                                break;
                                case 8:
                                    employee.setInstitution(Institutions.Compesa);
                                break;
                                case 9:
                                    employee.setInstitution(Institutions.LimpaBrasil);
                                break;
                                case 10:
                                    employee.setInstitution(Institutions.AssociaçãoDosProtetoresDeAnimaisDePernambuco);
                                break;
                                default:
                                break;
                            }
                        employees.addUser(employee);
                        System.out.println("\n Funcionário adicionado.\n");
                        }
                    }
                }
            }while(state == 1);
            
        }
}

