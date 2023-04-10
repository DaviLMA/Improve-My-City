package improve.my.city.central.login;

import improve.my.city.exceptions.CannotFindUserException;

public interface ConnectionType {
    public boolean login (Object list, String id, int password) throws CannotFindUserException;
}
