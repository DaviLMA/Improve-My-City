package improve.my.city.central.citizenView;

import java.util.InputMismatchException;
import java.util.Scanner;

import improve.my.city.exceptions.CannotFindReportException;
import improve.my.city.exceptions.EmptyArrayException;
import improve.my.city.exceptions.OutOfTheOptionsException;
import improve.my.city.reports.ReportList;
import improve.my.city.user.Citizen;

public class CitizenMenu {
    private static CitizenMenu citizenMenu;
    private CitizenMenu() {
    }

    public static CitizenMenu getInstance(){
        if(citizenMenu == null){
            citizenMenu = new CitizenMenu();
        }
        return citizenMenu;
    }

    public void showMenu (Citizen citizen, ReportList reports){
        Scanner input = new Scanner(System.in);
        FeedCitizen feedCitizen = FeedCitizen.getInstance();
        ReportProblem reportProblem = ReportProblem.getInstance();
        int option =0, state = 0;
        boolean choice = false;
        do{ 
            choice = false;
            state = 0;
            try{
                System.out.println("\n1. Feed");
                System.out.println("2. Atividade");
                System.out.println("3. Seus dados");
                System.out.println("4. Reportar Problema");
                System.out.println("5. Deslogar\n");
                option = input.nextInt();
                if(option < 1 || option > 5){
                    throw new OutOfTheOptionsException();
                }
            }
            catch(InputMismatchException k){
                System.out.println("\nInput inválido! Por favor, insira um número.\n"); 
                input.nextLine(); 
                state = 1;       
            }catch(OutOfTheOptionsException o){
                System.out.println(o.getMessage());
                state = 1;
            }
            if(state != 1){
                switch(option){
                    case 1:
                        try {
                            feedCitizen.showFeed(reports, citizen, input);
                        } catch (EmptyArrayException v) {
                            System.out.println(v.getMessage());
                        }catch (CannotFindReportException c){
                            System.out.println(c.getMessage());
                        }
                        break;
                    case 2:
                        try {
                        atividade(citizen, reports);
                        } catch (CannotFindReportException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        seusDados(citizen);
                        break;
                    case 4:
                        reportProblem.makeReport(citizen, reports);
                        break;
                    case 5:
                        choice = true;
                        break;
                    default:
                    break;
                }
            }
        
        }while(option != 5 || state == 1);

    }

    public static void atividade(Citizen citizen, ReportList reports) throws  CannotFindReportException{
        int i;
        ReportList criados = new ReportList(); 
        ReportList confirmados = new ReportList();
        
            System.out.println("\nRelatos confirmados:\n ");
            for(i =0; i < citizen.getConfirmedSize(); i++){
                
                confirmados.addReport(reports.searchReport(citizen.getConfirmed(i)));
    
            }
            for(i = 0; i < confirmados.getSize();i++){
        
                System.out.println(confirmados.toString(i));  
            }
 
            System.out.println("\nRelatos criados:\n ");
            for(i =0; i < citizen.getCreatedSize(); i++){
                
                criados.addReport(reports.searchReport(citizen.getCreated(i)));
    
            }
            for(i = 0; i < criados.getSize();i++){
        
                System.out.println(criados.toString(i));  
            }    
        
    }

    public static void seusDados(Citizen citizen){
        System.out.println(citizen.toString());
    }



    
}
