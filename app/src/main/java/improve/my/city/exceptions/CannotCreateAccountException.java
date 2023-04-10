package improve.my.city.exceptions;

public class CannotCreateAccountException extends Exception {

    public CannotCreateAccountException() {
        super("\nNão foi possível criar sua conta! Já existe uma conta utilizando a identificação informada.\n");
    }
    
    
}
