package improve.my.city.user;

import improve.my.city.enums.*;

public class Employee extends User {
    private Institutions institution;
    private String employeeId;
    private static Employee employeeInstance;
    
    //Implementação de Singleton
    private Employee() {
    }
    public static Employee getInstance(String employeeId, String name, int password){
        if(employeeInstance == null){
            employeeInstance = new Employee();
            employeeInstance.name = name;
            employeeInstance.password = password;
            employeeInstance.employeeId = employeeId;
        }
        return employeeInstance;
    }


    public void setInstitution(Institutions institution) {
        this.institution = institution;
    }

    public String getEmployeeId() {
        return employeeId;
    }


    public Institutions getInstitution() {
        return institution;
    }

    

    
}
