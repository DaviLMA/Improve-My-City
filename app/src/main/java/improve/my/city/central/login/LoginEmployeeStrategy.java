package improve.my.city.central.login;

import improve.my.city.central.employeeView.EmployeeList;
import improve.my.city.exceptions.CannotFindUserException;

public class LoginEmployeeStrategy implements ConnectionType {
    
    @Override
    public boolean login(Object employeeList, String id, int password) throws CannotFindUserException{ 
        EmployeeList employees = (EmployeeList) employeeList;
        if(employees.searchUser(id)){
            if(employees.getUser(id).equals(null)){
                throw new CannotFindUserException();
            }else{
                if(employees.getUser(id).getPassword() == password){
                    return true;
                }else{
                    return false;
                } 
            }
        }else{
            return false;
        }         
    }
}
