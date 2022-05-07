package satbank;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** Test for our datahandler.
 * 
 */
public class DataHandlerTest {

    //private static String DIR = System.getProperty("user.dir") + "/src/main/java/satbank/db.txt";

    

    @Test
    public void testConstructor() throws IOException {
        DataHandler test = new DataHandler();
        Assertions.assertNotNull(test);
    }


}
