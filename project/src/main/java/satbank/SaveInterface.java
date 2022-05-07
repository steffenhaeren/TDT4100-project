package satbank;

/**
 * Interface for our datahandler with methods used there.
 */
public interface SaveInterface {
    

    public void addAcc(User user, Account account);

    public void removeAcc(User user, String accName); 

    public void newUser(String name, String password, String email); 

    public void editBalance(User user, String accName, int balance, boolean add); 

}
