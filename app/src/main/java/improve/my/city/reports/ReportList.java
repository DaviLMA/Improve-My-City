package improve.my.city.reports;

import java.util.ArrayList;

public class ReportList {
    private ArrayList <Report> reports;
   



    public ReportList() {
        this.reports = new ArrayList<Report>();
    }
    public void addReport(Report report){
        reports.add(report);
    }
    public Report getReport (int i){
        if(i < reports.size()){
            return reports.get(i);
        }
        return null;
    }

    public Report searchReport (String id){
        int i;
        for(i = 0; i < reports.size(); i++){
            if(reports.get(i).getId().equals(id)){
                return reports.get(i);
            }
        }
        return null;
    }

    public boolean reportExist(String id){
        int i;
        for(i = 0; i < reports.size(); i++){
            if(reports.get(i).getId()== id){
                return true;
            }
        }
        return false;
    }

    
    public String toString(int i){

        return this.getReport(i).toString();
    }
    public int getSize(){
        return reports.size();
    }
}

    

