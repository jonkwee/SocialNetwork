package controllers;

import components.Users;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewPostController {

		
		StartController start;
		Users users;
		//TimelineVer2Controller timeline;
		TimelineController timeline;
		
		@FXML
		public void initialize(){}
		
		public void importVariables(StartController start, TimelineController timeline) {
			this.start = start;
			this.users = start.getUsers();
			this.timeline = timeline;
		}
		
		
//		public void addMessage(String message) {
//			timeline.updateTextField(message);
//		}
		
		
}

