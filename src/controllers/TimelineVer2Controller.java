package controllers;

import components.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TimelineVer2Controller {
	
	@FXML
	ListView<String> commentArea;
	
	ObservableList<String> comments;
	
	StartController start;
	Users users;
	
	public void initialize() {
		commentArea = new ListView<String>();
		comments = FXCollections.observableArrayList();
	}
	
	public void updateTextField(String message) {
		comments.add(message);
		commentArea.setItems(comments);
	}
	
	public ObservableList<String> getCommentList() {
		return comments;
	}
	
	public void importVariables(StartController start) {
		this.start = start;
		this.users = start.getUsers();
	}
	
	@FXML
	public void openPost() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("Post.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			NewPostController newPost = (NewPostController) loader.getController();
			newPost.importVariables(start, this);

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
	
	
	
	
}
