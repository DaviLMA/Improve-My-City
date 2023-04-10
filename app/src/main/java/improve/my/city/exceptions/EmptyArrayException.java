package improve.my.city.exceptions;

public class EmptyArrayException extends Exception{

    public EmptyArrayException() {
        super("\nNão foram encontrados relatos!.\n");
    }
    
    
}
