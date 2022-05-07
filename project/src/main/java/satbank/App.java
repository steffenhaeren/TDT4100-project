package satbank;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Standard setup.
 */
public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Satbank");
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("login.fxml"))));
		primaryStage.show();
	}

	public static void main(String[] args) {
		App.launch(App.class, args);
	}

	public void newPane(FXMLLoader loader) throws IOException {
		Stage stage = new Stage();
		Parent parent = loader.load();
		stage.setTitle("SaTBank");
		stage.setScene(new Scene(parent));
		stage.show();

	}
}
