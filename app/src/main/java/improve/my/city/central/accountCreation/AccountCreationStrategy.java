package improve.my.city.central.accountCreation;

import java.util.Scanner;

import improve.my.city.exceptions.CannotCreateAccountException;

public interface AccountCreationStrategy {
    public void create(Object e, Scanner input) throws CannotCreateAccountException;
}
