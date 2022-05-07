package satbank;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

/**
 * Supercontroller which all other controllers use.
 */
public class SuperController {

    private App app = new App(); 
    

    /**
     * Used to change the scenes.
     * @param page
     * @param user
     * @param stage
     */
    public void changeScene(String page, User user, Stage stage) {
        stage.close(); 

        try {
            FXMLLoader loader = new FXMLLoader(SuperController.class.getResource(page));
            if (page.contains("meny")) {
                loader.setController(new MainController(user));
            }
            else if (page.contains("login")) {
                //loader.setController(new LoginController());
            }
            else if (page.contains("register")) {
                loader.setController(new RegisterController());
            }
    
        app.newPane(loader); 
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
}
