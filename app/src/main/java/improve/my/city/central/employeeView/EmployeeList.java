package improve.my.city.central.employeeView;

import java.util.ArrayList;

import improve.my.city.user.Employee;
import improve.my.city.user.User;
import improve.my.city.user.UserList;

public class EmployeeList implements UserList{
    private ArrayList <Employee> employees;
    private static EmployeeList employeeListInstance;

    //Implementação de Singleton
    private EmployeeList(){
    }

    public static EmployeeList getInstance (){
        if(employeeListInstance == null){
            employeeListInstance = new EmployeeList();
            employeeListInstance.employees = new ArrayList<Employee>();
        }
        return employeeListInstance;
    }

    @Override
    public void addUser(User employee) {
        employees.add((Employee) employee);
        
    }

    public Employee getUser(String id){
        int i;
        for(i = 0; i < employees.size(); i++){
            if(employees.get(i).getEmployeeId().equals(id)){
                return employees.get(i);
            }
        }
        return null;
        }

    public boolean searchUser(String id){
        int i;
        for(i = 0; i < employees.size(); i++){
            if(employees.get(i).getEmployeeId().equals(id)){
                return true;
            }
        }
        return false;
    }


}
