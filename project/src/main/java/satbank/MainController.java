package satbank;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Maincontroller class.
 */
public class MainController extends SuperController {

    private User user;

    @FXML
    private TextField showbalance; 

    @FXML
    private Label feedBack;

    @FXML
    private Text feedback;

    @FXML
    private Button logOut;

    @FXML
    private Button deposit;

    @FXML
    private Button withdraw;

    @FXML
    private Button createAcc;

    @FXML
    private Button deleteAcc;

    @FXML
    private VBox showAccounts;

    @FXML
    private TextField dAccName;

    @FXML
    private TextField cAccName;

    @FXML
    private TextField delAccName;

    @FXML
    private TextField dAmount;

    @FXML
    private TextField cAmount;

    @FXML
    private TextField cType;

    /**
     * Constructor where we pass our user object, making it easier to use.
     * 
     * @param user
     */
    public MainController(User user) {
        this.user = user;
    }

    /**
     * Fires on startup to show all accounts.
     */
    @FXML
    private void initialize() {
        showAccounts();
    }

    /**
     * Logs the user out.
     */
    @FXML
    private void logOut() {
        super.changeScene("login.fxml", null, (Stage) feedback.getScene().getWindow());
    }

    /**
     * Removes balance if possible.
     */
    @FXML
    private void removeBalance() {
        DataHandler handler;

        handler = new DataHandler();
        if (!user.hasAcc(dAccName.getText())) {
            feedBack.setText("That account does not exist");
            return;
        }
        try {
            if (Integer.parseInt(dAmount.getText()) <= 0) {
                feedBack.setText("Cannot remove zero or less from acc");
                return;
            }
        } catch (Exception e) {
            feedBack.setText(e.getMessage());
        }
        if (user.getBalance(dAccName.getText()) < Integer.parseInt(dAmount.getText())) {
            feedBack.setText("Cannot remove more than the amount on acc");
            return;
        }
        user.removeFromAcc(Integer.parseInt(dAmount.getText()), dAccName.getText());
        feedBack.setText("You successfully removed balance");

        handler.editBalance(user, dAccName.getText(), Integer.parseInt(dAmount.getText()), false);
        dAmount.clear();
        dAccName.clear();
        showAccounts.getChildren().clear();
        showAccounts();

    }

    /**
     * Adds balance to the account gives if possible.
     */
    @FXML
    private void addBalance() {

        DataHandler handler;

        try {
            handler = new DataHandler();
            if (!user.hasAcc(dAccName.getText())) {
                feedBack.setText("That account does not exist");
                return;
            }

            if (Integer.parseInt(dAmount.getText()) <= 0) {
                feedBack.setText("Cannot add zero or less to acc");
                return;
            }
            user.addToAcc(Integer.parseInt(dAmount.getText()), dAccName.getText());
            handler.editBalance(user, dAccName.getText(), Integer.parseInt(dAmount.getText()), true);
            feedBack.setText("You successfully added balance");
            dAmount.clear();
            dAccName.clear();
            showAccounts.getChildren().clear();
            showAccounts();

        } catch (Exception e) {
            feedBack.setText(e.getMessage());
        }
    }

    /**
     * Shows all accounts in mainpage, and total value.
     */
    @FXML
    private void showAccounts() {
        
        int balance = 0; 
        balance = user.getAccounts().stream().mapToInt(x -> x.getBalance()).sum();
        showbalance.setText(Integer.toString(balance));

        try {
            ArrayList<HBox> hboxes = new ArrayList<HBox>();

            TextField name = new TextField();
            name.setText("Name");
            name.setEditable(false);

            TextField typeName = new TextField();
            typeName.setText("Type");
            typeName.setEditable(false);

            TextField bbalance = new TextField();
            bbalance.setText("Balance");
            bbalance.setEditable(false);
            hboxes.add(new HBox(name, typeName, bbalance));

            for (Account account : user.getAccounts()) {
                TextField field = new TextField();
                field.setText(account.getName());
                field.setEditable(false);

                TextField type = new TextField();
                type.setText(account.getType() + " ");
                type.setEditable(false);

                TextField bbbalance = new TextField();
                bbbalance.setText(Integer.toString(account.getBalance()));
                bbbalance.setEditable(false);

                hboxes.add(new HBox(field, type, bbbalance));

            }

            showAccounts.getChildren().addAll(hboxes);


        } catch (Exception e) {

            feedback.setText(e.getMessage());
        }
    }

    /**
     * Adds account if possible.
     */
    @FXML
    public void addAccount() {
        try {
            int balance = Integer.parseInt(cAmount.getText());
            String name = cAccName.getText();
            if (name.isEmpty() || balance <= 0) {
                feedBack.setText("Need to fill all correctly");
                return;
            }
        } catch (Exception e) {
            feedBack.setText(e.getMessage());
            return;
        }

        Account account = new Account(cAccName.getText(), cType.getText().charAt(0),
                Integer.parseInt(cAmount.getText()));

        DataHandler handler = new DataHandler();

        if (user.hasAcc(cAccName.getText())) {
            feedBack.setText("Cannot add another account with that name");
            return;

        }
        user.createAccount(account);
        handler.addAcc(user, account);
        feedBack.setText("Success!");
        cAccName.clear();
        cType.clear();
        cAmount.clear();
        showAccounts.getChildren().clear();
        showAccounts();

    }

    /**
     * Removes account if possible.
     */
    @FXML
    public void removeAcc() {

        String accName = delAccName.getText();
        ArrayList<Account> accounts = user.getAccounts();
        for (Account account : accounts) {
            if (account.getName().equals(accName)) {
                user.deleteAccount(account);
                feedBack.setText("Success!");
                delAccName.clear();
                showAccounts.getChildren().clear();
                showAccounts();
                DataHandler handler = new DataHandler();
                handler.removeAcc(user, accName);
                return;
            }

        }
        feedBack.setText("Could not find that account");
        return;

    }

}
