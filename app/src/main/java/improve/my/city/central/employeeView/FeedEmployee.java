package improve.my.city.central.employeeView;

import java.util.InputMismatchException;
import java.util.Scanner;

import improve.my.city.enums.Institutions;
import improve.my.city.enums.Status;
import improve.my.city.enums.Subtypes;
import improve.my.city.exceptions.CannotFindReportException;
import improve.my.city.exceptions.EmptyArrayException;
import improve.my.city.exceptions.OutOfTheOptionsException;
import improve.my.city.reports.Report;
import improve.my.city.reports.ReportList;
import improve.my.city.user.Employee;

public class FeedEmployee {
    private static FeedEmployee feedEmployee;
    private FeedEmployee() {
    }
    public static FeedEmployee getInstance(){
        if(feedEmployee == null){
            feedEmployee = new FeedEmployee();
        }
        return feedEmployee;
    }

    public void showFeed(ReportList reports, Employee employee, Scanner input) throws EmptyArrayException, CannotFindReportException{
        ReportList array;
        Report report;
        int option =0, i;
        String id;

        try{

            array = matches(employee, reports);
            if (array == null || array.getSize() == 0){
                throw new EmptyArrayException();
            }else{
                for(i = 0; i < array.getSize(); i++){
                    System.out.println(array.toString(i));
                }
                System.out.println("\n");
            }
            }catch( EmptyArrayException j){
    
                return;
            }
        do{
            try{
            System.out.println("\nGostaria de reconhecer um problema?");
            System.out.println("1. Sim");
            System.out.println("2. Não \n");

            option = input.nextInt();
            input.nextLine();
            if(option < 1 || option > 2){
                throw new OutOfTheOptionsException();
            }
            } catch(InputMismatchException f){
                System.out.println("\nInput inválido! Por favor, insira um número.\n");
                input.nextLine();
            } catch (OutOfTheOptionsException p){
                System.out.println(p.getMessage());
            }
            try{
            if(option == 1){
                System.out.println("\nInforme o Id do problema: \n");
                id = input.nextLine();
                report = reports.searchReport(id);
                if(report ==  null||report.getId() == null){
                    throw new CannotFindReportException();
                }else{
                    report.setMonitor(employee.getInstitution());
                    report.getIssue().setStatus(Status.Reconhecido); 
                    System.out.println("\nRelato reconhecido!\n");
                }  
            }else{
                option =2;
            }
            } catch(InputMismatchException l){
                System.out.println("\nInput inválido! Por favor, insira um Id composto por letras, números e símbolos.\n");
                input.nextLine();
            } 
        }while(option == 1);
    }

    public static ReportList matches (Employee employee, ReportList reports){
        int i;
        ReportList array = new ReportList();
        for(i=0; i < reports.getSize(); i++){
            if(employee.getInstitution() == Institutions.Polícia){
                if(reports.getReport(i).getIssue().getSubtType() == Subtypes.AtividadeCriminal ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.PessoaDesaparecida ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.AbusoDeAnimal){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }
                }
            }
            else if(employee.getInstitution() == Institutions.CorpoDeBombeiros){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.Acidente ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.PossívelPerigo){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                            array.addReport(reports.getReport(i));
                        }
                    }
                }

            }
            else if(employee.getInstitution() == Institutions.VigilânciaSanitária){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.ÁreaPoluída ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.Saneamento){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }
                }
            }
            else if(employee.getInstitution() == Institutions.AutarquiaDeManutençãoELimpezaUrbanaDoRecife){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.ColetaDeItemGrande ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.AnimalMorto||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.DespejoIlegal||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.EscoamentoEntupido ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.ÁreaPoluída ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.Buraco||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.Rachadura){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }
                }  
            }
            else if(employee.getInstitution() == Institutions.Compesa){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.FaltaDeBueiro ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.VazamentoDeÁgua){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }

                }
            }
            else if(employee.getInstitution() == Institutions.CTTU){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.FaltaDeSinalização ||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.Semáforo){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }

                }
            }
            else if(employee.getInstitution() == Institutions.Celpe){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.FaltaDeEnergia){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }

                }
            }
            else if(employee.getInstitution() == Institutions.CentroDeControleDeZoonoses){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.AnimalMorto){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }

                }
            }
            else if(employee.getInstitution() == Institutions.AssociaçãoDosProtetoresDeAnimaisDePernambuco){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.AbusoDeAnimal||
                reports.getReport(i).getIssue().getSubtType() == Subtypes.AnimalAbandonado){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }

                }
            }
            else if(employee.getInstitution() == Institutions.LimpaBrasil){
                if( reports.getReport(i).getIssue().getSubtType() == Subtypes.ÁreaPoluída){
                    if(reports.getReport(i).getIssue().getStatus() == Status.Submetido){
                        array.addReport(reports.getReport(i));
                    }
                }
            }
        }
        return array;
    
    }
    
}
