package satbank;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test for out accountclass.
 */
public class AccountTest {

    Account testAccount;
    
    @BeforeEach
    public void setup() throws IOException {
        testAccount = new Account("testKonto", 'c', 50);
    }

    @Test
    public void testConstructor() throws IOException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Account("testKonto", 'c', -100));
        
    }

    @Test
    public void testAddBalance() throws IOException {
        testAccount.addBalance(500);
        Assertions.assertEquals(testAccount.getBalance(), 550);
    }

    @Test
    public void testValidAddBalance() throws IOException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> testAccount.addBalance(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> testAccount.addBalance(-100));
    }

    @Test
    public void testRemoveBalance() throws IOException {
        testAccount.addBalance(500);
        testAccount.removeBalance(100);
        Assertions.assertEquals(testAccount.getBalance(), 450);
    }

    @Test
    public void testValidRemoveBalance() throws IOException {
        Assertions.assertThrows(IllegalArgumentException.class, () -> testAccount.removeBalance(0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> testAccount.removeBalance(-100));
        testAccount.addBalance(500);
        Assertions.assertThrows(IllegalArgumentException.class, () -> testAccount.removeBalance(-1000));
    }
}
