package satbank;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for our userclass.
 */
public class UserTest {

    User testUser;
    ArrayList<Account> accounts = new ArrayList<>();

    
// (String name, String password, String email, ArrayList<Account> accounts)

    @BeforeEach
    public void setup() throws IOException {
        testUser = new User("testUser", "hei", "hei@hei.hei", accounts);
    }

    @Test
    public void testValidCreateAccount() throws IOException {
        Account testAccount = new Account("testKonto", 'm', 1000);
        accounts.add(testAccount);
        User testUser2 = new User("testUser", "hei", "hei@hei.hei", accounts);
        Assertions.assertThrows(IllegalArgumentException.class, () -> testUser2.createAccount(testAccount));
    }

    @Test
    public void testValidDeleteAccount() throws IOException {
        Account testAccount = new Account("testKonto", 'm', 1000);
        Assertions.assertThrows(IllegalArgumentException.class, () -> testUser.deleteAccount(testAccount));
    }
    
}
