package controllers;

import components.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewPostController {

		
		StartController start;
		Users users;
		TimelineController timeline;
		
		@FXML
		Button cancel;
		
		@FXML
		Button post;
		
		@FXML
		TextArea textArea;
		
		
		@FXML
		public void initialize(){}
		
		public void importVariables(StartController start, TimelineController timeline) {
			this.start = start;
			this.users = start.getUsers();
			this.timeline = timeline;
		}
		
		@FXML
		public void quitWindow() {
			cancel.getScene().getWindow().hide();
		}
		
		@FXML
		public void post() {
			String msg = textArea.getText();
			timeline.addMessage(timeline.getCurrentUserInfo().get(0) + ": " + msg);
			// TO-DO
			// Add networking code
			// Send msg to other apps too!
			
			quitWindow();
		}
		
}

