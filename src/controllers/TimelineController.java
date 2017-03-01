package controllers;

import components.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TimelineController {
	
	StartController start;
	Users users;
	
	/*@FXML
	Menu myProfile;
	
	@FXML
	MenuBar profileItems;*/
	
	@FXML
	Button myProfile;
	
	
	@FXML
	public void initialize(){}
	
	@FXML
	public void viewProfile(){
		openProfile();
	}
	
	public void openProfile(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("Profile.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			ProfileController profile = (ProfileController) loader.getController();
			profile.importVariables(start);

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	public void openNewPost(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("Post.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			NewPostController post = (NewPostController) loader.getController();
			post.importVariables(start);

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			Alert r = new Alert(AlertType.NONE, "Cannot create a new post." , ButtonType.OK);
			r.setTitle("ERROR");
			r.showAndWait();
		}
	}
	
	public void importVariables(StartController start) {
		this.start = start;
		this.users = start.getUsers();
	}
}
