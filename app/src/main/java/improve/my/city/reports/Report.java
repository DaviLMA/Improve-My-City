package improve.my.city.reports;

import improve.my.city.user.Citizen;
import improve.my.city.enums.Districts;
import improve.my.city.enums.Institutions;


public class Report {
    private Citizen citizen;
    private String id;
    private Issue issue;
    private String location;
    private Districts district;
    private String date;
    private String update;
    private boolean media;
    private int votes;
    private Institutions monitor;
    private static Report reportInstance;

    //Inplementação de Singleton
    private Report() {    
    }
    public static Report getInstance(){
        if(reportInstance == null){
            reportInstance = new Report();
            reportInstance.id = GenerateId.getAlphaNumericString(10);   
        }
        return reportInstance;
    }

    @Override
    public String toString(){
        return this.issue.toString() +"\n" +"Localização: " +this.location + "\n" + "Bairro: " +this.district + 
        "\nConfirmado por: " +this.votes + " cidadãos" +"\n" +"ID: " + this.id +"\n"+ "Criado por: " +getName(this.citizen)+ 
         "\n"+ "Criado em: " +this.date + "\n" + "Atualizado em: " +this.update+ "\n" +"Mídia: "+ this.media+ "\nMonitorado por: " + this.monitor+"\n \n \n";
    }

    public static String getName(Citizen citizen){
        if(citizen == null){
            return "Anônimo";
        }else{
            return citizen.getName();
        }
    }



    
    public void setUpdate(String update) {
        this.update = update;
    }
    public String getId() {
        return id;
    }
    public Districts getDistrict(){
        return this.district;
    }


    public Institutions getMonitor() {
        return monitor;
    }
    public void setMonitor(Institutions monitor) {
        this.monitor = monitor;
    }
    public Issue getIssue() {
        return issue;
    }
    public void setVotes() {
        this.votes += 1;
    }
    public Citizen getCitizen() {
        return citizen;
    }
    public void setUser(Citizen citizen) {
        this.citizen = citizen;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setDistrict(Districts district) {
        this.district = district;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setMedia(boolean media) {
        this.media = media;
    }
    public void setIssue(Issue issue) {
        this.issue = issue;
    }
    


    

    

    
    

}
