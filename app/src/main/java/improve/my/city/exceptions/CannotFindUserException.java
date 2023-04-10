package improve.my.city.exceptions;

public class CannotFindUserException extends Exception {

    public CannotFindUserException() {
        super("\nUsuário não encontrado!\n");
    }
    
}
