package improve.my.city.reports;
import improve.my.city.enums.*;

public class Issue {
    private String description;
    private IssueType issueType;
    private Status status;
    private Subtypes subtype;

    public Issue() {
       
    }
    @Override
    public String toString(){
        return "Tipo de problema: " + this.issueType +"\n" +"Subtipo: "+this.subtype +"\n" +"Descrição: " +this.description+
        "\n"+ "Status: " +this.status;
    }

    

    public Status getStatus() {
        return status;
    }
    public IssueType getIssueType() {
        return issueType;
    }
    public Subtypes getSubtType() {
        return this.subtype;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setIssueType(IssueType issueType) {
        this.issueType = issueType;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setSubtype(Subtypes subtype) {
        this.subtype = subtype;
    }
    
    
    

}
