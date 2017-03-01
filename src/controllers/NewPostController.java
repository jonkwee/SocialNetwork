package controllers;

import components.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class NewPostController {

		
		StartController start;
		
		Users users;
		
		@FXML
		public void initialize(){}
		
		public void importVariables(StartController start) {
			this.start = start;
			this.users = start.getUsers();
		}
		
}

