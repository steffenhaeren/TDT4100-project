package satbank;

/**
 * Backend for our account class.
 */
public class Account {

    private int balance;
    private String name; // Dette vil være vår identifikator, man kan ikke ha samme navn på to
                         // forskjellige kontoer
    private char type;

    /**
     * Constructor with validation.
     * 
     * @param name
     * @param type
     * @param balance
     */
    public Account(String name, char type, int balance) {
        if (!(balance >= 0)) {
            throw new IllegalArgumentException("Balance must be over null!");
        } else {
            this.name = name;
            this.type = type;
            this.balance = balance;
        }
    }

    /**
     * 
     * @return Balance of the given account.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * 
     * @return Name for the given account.
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return Type of the given account.
     */
    public char getType() {
        return type;
    }

    /**
     * Adds balance to this object.
     * 
     * @param balance how much.
     */
    protected void addBalance(int balance) {
        if (balance > 0) {
            this.balance += balance;
        } else {
            throw new IllegalArgumentException("Cannot add a non-positive balance!");
        }
    }

    /**
     * Removes balance from the account.
     * 
     * @param balance how much.
     */
    protected void removeBalance(int balance) {
        if (balance > 0 && balance <= this.balance) {
            this.balance -= balance;
        } else {
            throw new IllegalArgumentException("Number must be positive or less than current balance!");
        }
    }
}
