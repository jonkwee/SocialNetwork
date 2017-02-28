package controllers;

import java.io.FileNotFoundException;

//import project2.AddActivityController;
import components.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
	}

	@FXML
	public void addExistingUsers() throws FileNotFoundException{
		users.readFromUserFile(this);
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
			Alert r = new Alert(AlertType.NONE, "Cannot open Sign In page." , ButtonType.OK);
			r.setTitle("ERROR");
			r.showAndWait();
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

		Stage stage = (Stage) signIn.getScene().getWindow();
	    stage.close();
	}

	public Users getUsers() {
		return users;
	}

}
