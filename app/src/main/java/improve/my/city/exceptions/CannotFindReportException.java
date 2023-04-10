package improve.my.city.exceptions;

public class CannotFindReportException extends Exception {

    public CannotFindReportException() {
        super("\nNenhum relato encontrado!\n");
    }
   
}
