package satbank;

import java.util.ArrayList;

/**
 * Backend for our user class.
 */
public class User {

  private String name;
  private String password;
  private String email;
  private ArrayList<Account> accounts;

  /**
   * Constructor for this class.
   * 
   * @param name
   * @param password
   * @param email
   * @param accounts
   */
  public User(String name, String password, String email, ArrayList<Account> accounts) {
    this.name = name;
    this.password = password;
    this.email = email;
    this.accounts = accounts;
  }

  /**
   * Creates a new account to this object if possible.
   * 
   * @param account
   */
  public void createAccount(Account account) {
    if (accounts.contains(account)) {
      throw new IllegalArgumentException("You already have that account!");
    } else {
      accounts.add(account);
      return;
    }
  }

  /**
   * Checks if this object has a specific account.
   * 
   * @param name
   * @return
   */
  public boolean hasAcc(String name) {
    for (Account account : accounts) {
      if (name.equals(account.getName())) {
        return true;
      }
    }
    return false;

  }

  /**
   * Gets the balance of a specific account.
   * 
   * @param accName
   * @return
   */
  public int getBalance(String accName) {
    for (Account account : accounts) {
      if (account.getName().equals(accName)) {
        return account.getBalance();
      }
    }
    return -1;
  }

  /**
   * Deletes a specific account.
   * 
   * @param account
   */
  public void deleteAccount(Account account) {
    if (!accounts.contains(account)) {
      throw new IllegalArgumentException("You do not have the account you wish to delete!");
    } else {
      accounts.remove(account);
    }
  }

  /**
   * 
   * @return The name of the user.
   */
  public String getName() {
    return name;
  }

  /**
   * 
   * @return The email of a user.
   */
  public String getEmail() {
    return email;
  }

  /**
   * 
   * @return An arraylist of the accounts in this user.
   */
  public ArrayList<Account> getAccounts() {
    return accounts;
  }

  /**
   * Adds value to an account. Validation in account class.
   * 
   * @param value how much
   * @param name  to find right account.
   */
  public void addToAcc(int value, String name) {
    for (Account account : accounts) {
      if (name.equals(account.getName())) {
        account.addBalance(value);
        return;
      }
    }

  }

  /**
   * Removes value from an account. Validation in account class.
   * 
   * @param value how much.
   * @param name  name of the account.
   */
  public void removeFromAcc(int value, String name) {
    for (Account account : accounts) {
      if (name.equals(account.getName())) {
        account.removeBalance(value);
        return;
      }
    }
  }
}
