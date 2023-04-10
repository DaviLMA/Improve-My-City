package improve.my.city.central.citizenView;

import java.util.InputMismatchException;
import java.util.Scanner;

import improve.my.city.enums.Districts;
import improve.my.city.enums.IssueType;
import improve.my.city.enums.Status;
import improve.my.city.enums.Subtypes;
import improve.my.city.exceptions.OutOfTheOptionsException;
import improve.my.city.reports.Issue;
import improve.my.city.reports.Report;
import improve.my.city.reports.ReportList;
import improve.my.city.user.Citizen;

public class ReportProblem {
    private static ReportProblem reportProblem;
    private ReportProblem() {
    }
    public static ReportProblem getInstance(){
        if(reportProblem == null){
            reportProblem = new ReportProblem();
        }
        return reportProblem;
    }

    public void makeReport (Citizen citizen, ReportList reports){
        Scanner input = new Scanner(System.in);
        ReportList list = reports;
        int option = 0, state = 0;
        do{  
            state = 0;
            Issue issue = new Issue();
            Report report = Report.getInstance();
            option = 0;
            String description, data;
            tipoDeProblema(input, issue, report);
            try{
            System.out.println("\nDeseja adicionar descrição ?");
                System.out.println("1. Sim");
                System.out.println("2. Não\n");
                option = input.nextInt();
                input.nextLine();
                if(option < 1 ||option > 2){
                    throw new OutOfTheOptionsException();
                } 
                if(option == 1){
                    System.out.println("\nDescreva o problema: \n");
                    description = input.nextLine();
                    issue.setDescription(description);
                }
            } catch (InputMismatchException r){
                System.out.println("\nInput inválido! Por favor, insira um valor referente ao tipo de dado: ");
                System.out.println("Opção -> Apenas números.");
                System.out.println("Descrição -> Letras e números.\n");
                input.nextLine();
                state = 1;
            }catch(OutOfTheOptionsException x){
                System.out.println(x.getMessage());
                state = 1;
            }
            if(state != 1){
                local(input, report);
                try{
                    System.out.println("\nInforme a data. \n");
                    data = input.nextLine();

                    report.setDate(data);
                    adicionarMidia(input, report);

                    System.out.println("\nDeseja tornar o relato anônimo ?");
                    option = 0;
                    System.out.println("1. Sim");
                    System.out.println("2. Não \n");
                    option = input.nextInt();
                    input.nextLine();
                    if(option < 1 ||option > 2){
                        throw new OutOfTheOptionsException();
                    } 
                }catch (InputMismatchException l){
                    System.out.println("\nInput inválido! Por favor, insira um valor referente ao tipo de dado: ");
                    System.out.println("Opção -> Apenas números.");
                    System.out.println("Data -> Números e /.\n");
                    input.nextLine();
                    state = 1;
                }catch(OutOfTheOptionsException b){
                    System.out.println(b.getMessage());
                    state = 1;
                }
                if(state != 1){
                    if(option == 1){
                        report.setUser(null);
                    }
                    else if(option == 2){
                    report.setUser(citizen);
                    }

                    report.setIssue(issue);
                    report.getIssue().setStatus(Status.Submetido);
    
                    list.addReport(report);
                    citizen.setCriados(report.getId());
                    try{
                        option = 0;
                        System.out.println("\nGostaria de reportar outro problema?");
                        System.out.println("1. Sim");
                        System.out.println("2. Não \n");
                        option = input.nextInt();
                        if(option < 1 ||option > 2){
                            throw new OutOfTheOptionsException();
                        } 
                    }catch (InputMismatchException l){
                        System.out.println("\nInput inválido! Por favor, insira um número dentre os listados.\n");
                        input.nextLine();
                        state = 1;
                    }catch(OutOfTheOptionsException b){
                        System.out.println(b.getMessage());
                        state = 1;
                    }
                }
            }       
        }while(option == 1 && state == 1);
        
    }

    public static int tipoDeProblema(Scanner input, Issue issue, Report report){
        int option = 0;
        try{
        System.out.println("\nSelecione o tipo e problema");
        System.out.println("1. Infraestura");
        System.out.println("2. Saneamento");
        System.out.println("3. Segurança");
        System.out.println("4. Animal\n");
        option = input.nextInt();
        input.nextLine();
        if(option < 1 || option > 4){
            throw new OutOfTheOptionsException();
        }
        switch(option){
            case 1:
                issue.setIssueType(IssueType.Infraestrutura);
                problemaInfraestrutura(issue, input);
                break;
            case 2:
                issue.setIssueType(IssueType.Lixo);
                problemaLixo(issue, input);
                break;
            case 3:
                issue.setIssueType(IssueType.Segurança);
                problemaSeguranca(issue, input);
                break;
            case 4:
                issue.setIssueType(IssueType.Animal);
                problemaAnimal(input, issue);
                break;
            default:
            break;
        }
        } catch (InputMismatchException z){
            System.out.println("\nInput inválido! Por favor, insira um número.\n");
        } catch (OutOfTheOptionsException m){
            System.out.println(m.getMessage());
        }
        return option;
    }

    public static void local (Scanner input, Report report){
        int option = 0, state = 0;
        String local = null;
        do{
            state = 0;
            try{
            System.out.println("\nInforme a localização:\n ");
            local = input.nextLine();
            }catch (InputMismatchException z){
                System.out.println("\nInput inválido! Por favor, insira apenas números e letras.\n");
                state = 1;
            } 
            if(state != 1){
                report.setLocation(local);
                try{
                    System.out.println("\nSelecione o bairro: ");
                    System.out.println("1. Caxangá");
                    System.out.println("2. Dois Irmãos");
                    System.out.println("3. Iputinga");
                    System.out.println("4. Cordeiro");
                    System.out.println("5. Engenho do Meio");
                    System.out.println("6. Várzea \n");
                    option = input.nextInt();
                    input.nextLine();
                    if(option < 1 || option > 6){
                        throw new OutOfTheOptionsException();
                    }
                    switch(option){
                        case 1:
                            report.setDistrict(Districts.Caxangá);
                            break;
                        case 2:
                            report.setDistrict(Districts.DoisIrmãos);
                            break;
                        case 3:
                            report.setDistrict(Districts.Iputinga);
                            break;
                        case 4:
                            report.setDistrict(Districts.Cordeiro);
                            break;
                        case 5:
                            report.setDistrict(Districts.EngenhoDoMeio);
                            break;
                        case 6:
                            report.setDistrict(Districts.Várzea);
                            break;
                        default:
                        break;
                    }
                }catch (InputMismatchException z){
                    System.out.println("\nInput inválido! Por favor, insira um número.\n");
                    input.nextLine();
                    state = 1;
                } catch (OutOfTheOptionsException m){
                    System.out.println(m.getMessage());
                    state = 1;
                }
            }
        }while(state == 1);
        
    }

    public static void adicionarMidia(Scanner input, Report report) throws OutOfTheOptionsException{
        int option = 0, state = 0;
        do{
            state = 0;
            try{
            System.out.println("\n Deseja adicionar mídia ?");
            System.out.println("1. Sim");
            System.out.println("2. Não \n");
            option = input.nextInt();
            input.nextLine();
            if(option <1 || option >2){
                throw new OutOfTheOptionsException();
            }
            }catch(InputMismatchException t){
                System.out.println("\nInput inválido! Por favor, insira um número.\n");
                state = 1;
            }catch (OutOfTheOptionsException n){
                System.out.println(n.getMessage());
                state = 1;
            }
            if(option == 1){
                report.setMedia(true);
            }
        }while(state == 1);
    }

    public static void problemaAnimal(Scanner input, Issue issue) throws OutOfTheOptionsException{
        int option = 0, state = 0;
        do{
            state = 0;
            try{
                System.out.println("\nQue tipo de problema animal deseja relatar ?");
                System.out.println("1. Animal Morto");
                System.out.println("2. Animal Abandonado");
                System.out.println("3. Abuso de Animal");
                System.out.println("4. Peste\n");
                option = input.nextInt();
                input.nextLine();
                if(option < 1 || option > 4){
                    throw new OutOfTheOptionsException();
                }
                switch(option){
                    case 1:
                        issue.setSubtype(Subtypes.AnimalMorto);
                        break;
                    case 2:
                        issue.setSubtype(Subtypes.AnimalAbandonado);
                        break;
                    case 3:
                        issue.setSubtype(Subtypes.AbusoDeAnimal);
                        break;
                    case 4:
                        issue.setSubtype(Subtypes.ControleDePeste);
                        break;
                    default:
                    break;  
            }
            }catch (InputMismatchException z){
                System.out.println("\nInput inválido! Por favor, insira um número.\n");
                input.nextLine();
                state = 1;
            } catch (OutOfTheOptionsException m){
                System.out.println(m.getMessage());
                state = 1;
            }
        }while(state == 1);
    }

    public static void problemaInfraestrutura(Issue issue, Scanner input) throws OutOfTheOptionsException{
        int option = 0, state = 0;
        do{
            state = 0;
            try{
                System.out.println("\nQue tipo de problema de infraestrutura deseja relatar ?");
                System.out.println("1. Vazemento de água.");
                System.out.println("2. Escoamento entupido.");
                System.out.println("3. Saneamento.");
                System.out.println("4. Semáforo.");
                System.out.println("5. Falta de sinalização.");
                System.out.println("6. Iluminação Pública.");
                System.out.println("7. Falta de Energia.");
                System.out.println("8. Rachadura.");
                System.out.println("9. Buraco.");
                System.out.println("10. Falta de Bueiro.\n");

                option = input.nextInt();

                if(option < 1 || option > 10){
                    throw new OutOfTheOptionsException();
                }
                input.nextLine();
                switch(option){
                    case 1:
                        issue.setSubtype(Subtypes.VazamentoDeÁgua);
                        break;
                    case 2:
                        issue.setSubtype(Subtypes.EscoamentoEntupido);
                        break;
                    case 3:
                        issue.setSubtype(Subtypes.Saneamento);
                        break;
                    case 4:
                        issue.setSubtype(Subtypes.Semáforo);
                        break;
                    case 5:
                        issue.setSubtype(Subtypes.FaltaDeSinalização);
                        break;
                    case 6:
                        issue.setSubtype(Subtypes.IluminaçãoPública);
                        break;
                    case 7:
                        issue.setSubtype(Subtypes.FaltaDeEnergia);
                        break;
                    case 8:
                        issue.setSubtype(Subtypes.Rachadura);
                        break;
                    case 9:
                        issue.setSubtype(Subtypes.Buraco);
                        break;
                    case 10:
                        issue.setSubtype(Subtypes.FaltaDeBueiro);
                        break;
                    default:
                    break;   
                }
            }catch (InputMismatchException z){
                System.out.println("\nInput inválido! Por favor, insira um número.\n");
                state = 1;
                input.nextLine();
            } catch (OutOfTheOptionsException m){
                System.out.println(m.getMessage());
                state = 1;
            }
        }while(state == 1);

    }

    public static void problemaSeguranca(Issue issue, Scanner input) throws OutOfTheOptionsException{
        int option = 0, state = 0;

        do{
            state = 0;
            try{
                System.out.println("\nQue tipo de problema de segurança deseja relatar ?");
                System.out.println("1. Acidente.");
                System.out.println("2. Possível perigo.");
                System.out.println("3. Atividade criminal.");
                System.out.println("4. Pessoa desaparecida;\n");
                option = input.nextInt();
                input.nextLine();
                if(option < 1 || option > 4){
                    throw new OutOfTheOptionsException();
                }
                switch(option){
                    case 1:
                        issue.setSubtype(Subtypes.Acidente);
                        break;
                    case 2:
                        issue.setSubtype(Subtypes.PossívelPerigo);
                        break;
                    case 3:
                        issue.setSubtype(Subtypes.AtividadeCriminal);
                        break;
                    case 4:
                        issue.setSubtype(Subtypes.PessoaDesaparecida);
                        break;
                    default:
                    break;
                    
                }
            }catch (InputMismatchException z){
                System.out.println("\nInput inválido! Por favor, insira um número.\n");
                state = 1;
                input.nextLine();
            } catch (OutOfTheOptionsException m){
                System.out.println(m.getMessage());
                state = 1;
            }
        }while(state ==1);
    }

    public static void problemaLixo(Issue issue, Scanner input) throws OutOfTheOptionsException{
        int option = 0, state = 0;
        do{
            state = 0;
            try{
                System.out.println(" \nQue tipo de problema de lixo deseja relatar ?");
                System.out.println("1. Despejo ilegal.");
                System.out.println("2. Área poluída.");
                System.out.println("3. Coleta de item grande.\n");
                option = input.nextInt();
                input.nextLine();
                if(option < 1 || option > 3){
                    throw new OutOfTheOptionsException();
                }
                switch (option){
                    case 1:
                        issue.setSubtype(Subtypes.DespejoIlegal);
                        break;
                    case 2:
                        issue.setSubtype(Subtypes.ÁreaPoluída);   
                        break;
                    case 3:
                        issue.setSubtype(Subtypes.ColetaDeItemGrande);
                        break;
                    default:
                    break;

                }
            }catch (InputMismatchException z){
                System.out.println("\nInput inválido! Por favor, insira um número.\n");
                state = 1;
                input.nextLine();
            } catch (OutOfTheOptionsException m){
                System.out.println(m.getMessage());
                state = 1;
            }
    }while(state == 1);
    }
    
    
}
