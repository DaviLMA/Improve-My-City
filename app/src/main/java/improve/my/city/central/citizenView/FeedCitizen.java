package improve.my.city.central.citizenView;

import java.util.InputMismatchException;
import java.util.Scanner;

import improve.my.city.enums.Subtypes;
import improve.my.city.exceptions.CannotFindReportException;
import improve.my.city.exceptions.EmptyArrayException;
import improve.my.city.exceptions.OutOfTheOptionsException;
import improve.my.city.reports.ReportList;
import improve.my.city.user.Citizen;

public class FeedCitizen {
    private static FeedCitizen feedCitizen;
    private FeedCitizen() {
    }
    public static FeedCitizen getInstance(){
        if(feedCitizen == null){
            feedCitizen = new FeedCitizen();
        }
        return feedCitizen;
    }

    public void showFeed(ReportList reports, Citizen citizen, Scanner input) throws EmptyArrayException, CannotFindReportException{
        ReportList array = new ReportList();
        int option =0, i, choice =0;
        String id;
        do{
            try{
            System.out.println("\n1. Problemas ao seu redor.");
            System.out.println("2. Pessoas desaparecidas.");
            System.out.println("3. Perigos ao seu redor.");
            System.out.println("4. Sair do feed.\n");
            choice = input.nextInt();
            if(choice < 1 || choice > 4){
                throw new OutOfTheOptionsException();
            }
            } catch (InputMismatchException k){
                System.out.println("\nInput inválido! Por favor, insira um número.\n");
                input.nextLine();
            } catch (OutOfTheOptionsException j){
                System.out.println(j.getMessage());
            }
            try{
            switch(choice){
                case 1:
                    option =0;
                    array = problemasAoRedor(citizen, reports);
                    if(array == null || array.getSize() == 0){
                        throw new EmptyArrayException();
                    }else{
                        for(i = 0; i < array.getSize();i++){
                            System.out.println(array.toString(i));  
                        }

                    do{
                    System.out.println("\nGostaria de confirmar um relato? \n");
                    System.out.println("1. Sim");
                    System.out.println("2. Não\n");
                    option = input.nextInt();
                    if(option < 1 || option > 2){
                        throw new OutOfTheOptionsException();
                    }
                    input.nextLine();
                    if(option == 1){
                        System.out.println("\nInforme o Id do relato: \n");
                        id = input.nextLine();
                        if(reports.searchReport(id) == null){
                            throw new CannotFindReportException(); 
                        }else{
                            reports.searchReport(id).setVotes();
                            citizen.setConfirmados(id);
                            System.out.println("\nRelato Confirmado!\n");
                        }
                    }
                    }while(option == 1);
                    }

                    break;
                case 2:
                    array = pessoasDesaparecidas(reports);
                    if(array == null || array.getSize() == 0){
                        throw new EmptyArrayException();
                    }else{
                        for(i = 0; i < array.getSize();i++){
                            System.out.println(array.toString(i));  
                        }
                        do{
                        System.out.println("\n Gostaria de continuar visualizando ?");
                        System.out.println("1. Sim");
                        System.out.println("2. Não\n");
                        option = input.nextInt();
                        if(option < 1 || option > 2){
                            throw new OutOfTheOptionsException();
                        }
                        input.nextLine();
                        }while(option == 1);
                    }
                    break;
                case 3:
                    array = perigosAoRedor(citizen, reports);
                    if(array == null || array.getSize() == 0){
                        throw new EmptyArrayException();
                    }else{
                        for(i = 0; i < array.getSize();i++){
                            System.out.println(array.toString(i));  
                        }
                    do{
                        System.out.println("\n Gostaria de continuar visualizando ?");
                        System.out.println("1. Sim");
                        System.out.println("2. Não\n");
                        if(option < 1 || option > 2){
                            throw new OutOfTheOptionsException();
                        }
                        option = input.nextInt();
                        input.nextLine();
                        }while(option == 1);
                    }
                    break;
                    case 4:
                        return;
                default:
                break;
            }
        }catch (OutOfTheOptionsException j){
            System.out.println(j.getMessage());
        }catch(InputMismatchException f){
            System.out.println("\nInput inválido! Por favor, insira um valor equivalente ao tipo de dado:");
            System.out.println("Id -> Letras, números e símbolos.");
            System.out.println("Opção -> Apenas números.\n");
            input.nextLine();
        }catch (EmptyArrayException a){
            System.out.println(a.getMessage());
        }catch (CannotFindReportException c){
            System.out.println(c.getMessage());
        }
    }while(choice != 4);
    }

    public static ReportList pessoasDesaparecidas(ReportList reports){
        int i;
        ReportList array = new ReportList();
        for(i =0; i < reports.getSize(); i++){
            if(reports.getReport(i).getIssue().getSubtType().equals(Subtypes.PessoaDesaparecida)){
                array.addReport(reports.getReport(i));
                }
            }
            return array;
        }


    
    public static ReportList perigosAoRedor(Citizen citizen, ReportList reports){
        int i;
        ReportList array = new ReportList();
        for(i =0; i < reports.getSize(); i++){
            if(citizen.getResidence().equals(reports.getReport(i).getDistrict())){
                if(reports.getReport(i).getIssue().getSubtType() == Subtypes.PossívelPerigo){
                    array.addReport(reports.getReport(i));
                }
            }
        }
        return array;
    } 

    public static ReportList problemasAoRedor(Citizen citizen, ReportList reports){
        int i;
        ReportList array = new ReportList();
        for(i =0; i < reports.getSize(); i++){
            if(citizen.getResidence().equals(reports.getReport(i).getDistrict())){
                array.addReport(reports.getReport(i));
            }
        }
        return array;
    }
    
}
