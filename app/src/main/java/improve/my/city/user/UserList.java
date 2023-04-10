package improve.my.city.user;

public interface UserList {
    public void addUser(User user);
    public User getUser (String id);
    public boolean searchUser(String id);
}
