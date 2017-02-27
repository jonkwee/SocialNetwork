package controllers;

//import project2.AddActivityController;
import components.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartController {
	@FXML
	Button signIn;
	@FXML
	Button createAccount;

	private Users users;

	@FXML
	public void initialize(){
		users = new Users();
		// add test User
		users.add("Bobby", "12345", "Bobby", null, null, null);
	}

	@FXML
	public void openSignIn(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("SignIn.fxml"));
			Pane root = (Pane) loader.load();

			SignInController signIn = (SignInController)loader.getController();
			signIn.importVariables(this);

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	@FXML
	public void openCreateAccount(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("CreateAccount.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			CreateAccountController createAccount = (CreateAccountController)loader.getController();
			createAccount.getStart(this);

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public Users getUsers() {
		return users;
	}
	
}
