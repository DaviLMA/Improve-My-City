package improve.my.city.central.employeeView;

import java.util.InputMismatchException;
import java.util.Scanner;

import improve.my.city.enums.Status;
import improve.my.city.exceptions.CannotFindReportException;
import improve.my.city.exceptions.EmptyArrayException;
import improve.my.city.exceptions.OutOfTheOptionsException;
import improve.my.city.reports.Report;
import improve.my.city.reports.ReportList;
import improve.my.city.user.Employee;

public class EmployeeMenu {
    private static EmployeeMenu employeeMenu;

    private EmployeeMenu() {
    }

    public static EmployeeMenu getInstance(){
        if(employeeMenu == null){
            employeeMenu = new EmployeeMenu();
        }
        return employeeMenu;
    }

    public void showMenu (Scanner input, ReportList reports, Employee employee){
        int option = 0;
        FeedEmployee feedEmployee = FeedEmployee.getInstance();
        do{ 
            try{
            option = 0;
            System.out.println("\n1. Feed");
            System.out.println("2. Processos arquivados");
            System.out.println("3. Processos em aberto");
            System.out.println("4. Deslogar \n");
            option = input.nextInt();
            if(option < 1 || option > 4){
                throw new OutOfTheOptionsException();
            }
            }
            catch(InputMismatchException q){
                System.out.println("\nInput inválido! Por favor, insira um número. \n");
                input.nextLine();
            }catch (OutOfTheOptionsException b){
                System.out.println(b.getMessage());
            }
            
            switch(option){
                case 1:
                    try {
                        feedEmployee.showFeed(reports, employee, input);
                    } catch (EmptyArrayException q) {
                        System.out.println(q.getMessage());
                    } catch (CannotFindReportException l) {
                        System.out.println(l.getMessage());
                    }
                    break;
                case 2:
                    try {
                        archivedProcesses(reports, input);
                    } catch (EmptyArrayException e1) {
                        System.out.println(e1.getMessage());
                    }
                    break;
                case 3:
                    try {
                        try {
                            openedProcesses(reports, input);
                        } catch (EmptyArrayException g) {
                            System.out.println(g.getMessage());
                        }
                    } catch (CannotFindReportException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    option = 4;
                    break;
                default:
                break;   
            }
        }while(option != 4);
    }

    public static void archivedProcesses(ReportList reports, Scanner input) throws EmptyArrayException{
        ReportList array = new ReportList();
        int i,option = 0;
        System.out.println("\nEstes são os processos arquivados: \n");
        for(i=0;i < reports.getSize();i++){
            if(reports.getReport(i).getIssue().getStatus() == Status.Resolvido){
                array.addReport(reports.getReport(i));
            }
        }
        if(array.getSize() == 0 ||array == null){
            throw new EmptyArrayException();
        }else{
            for(i = 0; i < array.getSize(); i++){
                System.out.println(array.toString(i));
            }
        }
        do{

            try{
            System.out.println("\nGostaria de continuar visualizando? \n");
            System.out.println("1. Continuar visualizando");
            System.out.println("2. Retornar ao menu\n");
            option =input.nextInt();
            if(option < 1 || option > 2){
                throw new OutOfTheOptionsException();
            }
            }catch (InputMismatchException l){
                System.out.println("\nInput inválido! Por favor, insira um número.\n");
                input.nextLine();
            }
            catch (OutOfTheOptionsException o){
                System.out.println(o.getMessage());
            }
            
            if(option == 1){
                for(i=0;i < reports.getSize();i++){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Resolvido){
                        array.addReport(reports.getReport(i));
                    }
                }
                array.toString();
            }
        }while(option != 2);
    }

    public static void openedProcesses(ReportList reports, Scanner input) throws CannotFindReportException, EmptyArrayException{
        ReportList array = new ReportList();
        Report report;
        int i,choice =0, option =0, state =0;
        String id, data;
        try{
        System.out.println("\nEstes são os processos em aberto: \n");
        for(i = 0; i < reports.getSize(); i++){
            if(reports.getReport(i).getIssue().getStatus() == Status.Reconhecido ||
            reports.getReport(i).getIssue().getStatus() == Status.EmProgresso){
                array.addReport(reports.getReport(i));
            }
        }
        if(array.getSize() == 0|| array == null){
            throw new EmptyArrayException();
        }else{
            for(i = 0; i < array.getSize(); i++){
                System.out.println(array.toString(i));
            }
        }
        }catch(EmptyArrayException j){

            return;
        }
        do{
            choice = 0;
            state = 0;
            try{
            System.out.println("\n1. Continuar visualizando");
            System.out.println("2. Mudar status do processo");
            System.out.println("3. Retornar ao menu \n");
            choice = input.nextInt();
            if(choice < 1 || choice > 3){
                throw new OutOfTheOptionsException();
            }
            input.nextLine();
            }catch(InputMismatchException  k){
                System.out.println("\nInput inválido! Por favor, insira um número.\n");
                input.nextLine();
                state = 1;
            }catch (OutOfTheOptionsException t){
                System.out.println(t.getMessage());
                state = 1;
            }
            if(state != 1){
                try{
                    switch(choice){
                        case 1:
                            choice = 1;
                            break;
                        case 2:
                            System.out.println("\nInforme o Id do processo: \n");
                            id = input.nextLine();
                            report = reports.searchReport(id);
                            if(report == null || report.getId() == null){
                                throw new CannotFindReportException();
                            }
                            try{
                                System.out.println("\nSelecione o novo status: ");
                                System.out.println("1. Em progresso");
                                System.out.println("2. Resolvido \n");
                                option = input.nextInt();
                                if(option < 1 || option > 2){
                                    throw new OutOfTheOptionsException();
                                }
                                input.nextLine();
                            }catch(OutOfTheOptionsException v){
                                System.out.println(v.getMessage());
                                state = 1;
                            }
                            if(option == 1){
                                report.getIssue().setStatus(Status.EmProgresso);
                            }else{
                                report.getIssue().setStatus(Status.Resolvido);
                            }
                            System.out.println("\nInforme a data: \n");
                            data = input.nextLine();
                            report.setUpdate(data);
                            break;
                        case 3:
                            choice = 3;
                            break;
        
                        default:
                        break;
                        }
                    }catch (InputMismatchException s){
                        System.out.println("\nInput inválido! Por favor, insira um valor equivalente ao tipo de dado:");
                        System.out.println("Id -> Letras, números e símbolos.");
                        System.out.println("Opção -> Apenas números.\n");
                        input.nextLine();
                        state = 1;
                    }catch (CannotFindReportException c){
                        System.out.println(c.getMessage());
                        state = 1;
                    }
            }
        }while(choice != 3 || state == 1);
    }

    
}
