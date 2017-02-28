package controllers;


import java.time.LocalDate;

import javafx.event.ActionEvent;
//import Objects.UserInfo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import components.UserInfo;
import components.Users;

public class EditProfileController  {
	
	StartController start;
	Users users;

	@FXML
	Button cancel;



	@FXML
	public void initialize(){
	}

	@FXML
	public void closeEdit(ActionEvent event) {
	    Stage stage = (Stage) cancel.getScene().getWindow();
	    stage.close();
	}
	
	public void importVariables(StartController start) {
		this.start = start;
		this.users = start.getUsers();
	}


}


