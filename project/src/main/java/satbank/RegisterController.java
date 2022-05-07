package satbank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controllerclass for registerpage.
 */
public class RegisterController extends SuperController {

    @FXML
    private TextField newUsername;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newEmail;

    @FXML
    private Button regNewUser;

    @FXML
    private Text feedback;

    @FXML
    private Button back;

    /**
     * Registering a new user. Gets info from textboxes and checks if it is okay. 
     */
    @FXML
    public void registerUser() {

        String username = newUsername.getText();
        String password = newPassword.getText();
        String email = newEmail.getText();
        if (username.isBlank() || password.isBlank() || email.isBlank()) {
            feedback.setText("You have to fill out all values");
        } else {

            DataHandler handler = new DataHandler();
            if (!handler.checkIfExists(username)) {

                handler.newUser(username, password, email);
                super.changeScene("login.fxml", null, (Stage) feedback.getScene().getWindow());
            } else {
                feedback.setText("Username not available");
            }

        }

    }


    /**
     * Back button if user does not want to register after going to that page.
     */
    @FXML
    public void back() {
        super.changeScene("login.fxml", null, (Stage) feedback.getScene().getWindow());
    }
}
