package controllers;

import java.util.List;

import components.UserInfo;
import components.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SignInController {
	@FXML
	Button signIn;
	@FXML
	Button cancel;
	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	Label prompt;

	StartController start;
	Users users;
	List<String> currentUser;

	@FXML
	public void initialize(){}

	@FXML
	public void signIn(){
		String currentUsername = username.getText();
		String currentPassword = password.getText();
		boolean userExist = users.checkUser(currentUsername, currentPassword);
		if (!userExist) {
			prompt.setText("Username or Password doesn't exist");
		} else {
			currentUser = users.getCurrentUser(currentUsername);
			openTimeline();
		}
	}


	public void openTimeline(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("TimelineVer2.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			TimelineController timeline = (TimelineController) loader.getController();
			timeline.importVariables(start, currentUser);
			

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			Alert r = new Alert(AlertType.NONE, "Cannot open the timeline." , ButtonType.OK);
			r.setTitle("ERROR");
			r.showAndWait();
			exc.printStackTrace();
		}
		
		Stage stage = (Stage) signIn.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void close() {
		cancel.getScene().getWindow().hide();
	}
	
	void importVariables(StartController start) {
		this.start = start;
		this.users = start.getUsers();
	}
}
