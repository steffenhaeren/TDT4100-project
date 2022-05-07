package satbank;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller fro our login fxml.
 */
public class LoginController extends SuperController {

    private User user;

    @FXML
    private Label feedBack;

    @FXML
    private TextField userName;

    @FXML
    private TextField passWord;

    @FXML
    private Button loginBTN;

    /**
     * Logs the user in.
     */
    public void login() {

        DataHandler handler = new DataHandler();
        user = new User(userName.getText(), passWord.getText(), handler.getEmail(userName.getText()),
                handler.getAccounts(userName.getText()));
        super.changeScene("meny.fxml", user, (Stage) feedBack.getScene().getWindow());
    }

    /**
     * Checks if login is valid. Runs login if its okay.
     */
    @FXML
    public void isLoginValid() {
        String username = userName.getText();
        String password = passWord.getText();
        DataHandler handler;
        handler = new DataHandler();
        String answer = handler.checkLoginInfo(username, password);
        if (answer.equals("Login")) {
            login();
        } else {
            feedBack.setText(answer);
        }

    }

    /**
     * Fires when user clicks on "register" button.
     */
    @FXML
    public void registerUser() {
        super.changeScene("register.fxml", null, (Stage) feedBack.getScene().getWindow());
    }
}
