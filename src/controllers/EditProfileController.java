package controllers;


import java.time.LocalDate;
import java.util.List;

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
	TimelineController timeline;
	List<String> currentUser;


	@FXML
	Button saveChanges;

	@FXML
	Button cancel;

	@FXML
	TextField username;

	@FXML
	TextField name;

	@FXML
	TextField password;

	@FXML
	TextField confirmPass;

	@FXML
	DatePicker birthday;

	@FXML
	TextField phone;

	@FXML
	TextField email;

	@FXML
	TextArea bio;



	@FXML
	public void initialize(){
	}

	@FXML
	public void saveChanges(ActionEvent event){
		//users.getCurrentUser(currentUser.get(0));
		//users.


	}

	@FXML
	public void closeEdit(ActionEvent event) {
	    Stage stage = (Stage) cancel.getScene().getWindow();
	    stage.close();
	}

	public void importVariables(StartController start, TimelineController timeline) {
		this.start = start;
		this.users = start.getUsers();
		this.timeline = timeline;
		this.currentUser = currentUser;
	}

	public void prePopulate(String username, String name, String phone,
			String email, String bio) {
		this.username.setText(username);
		this.name.setText(name);
		this.phone.setText(phone);
		this.email.setText(email);
		this.bio.setText(bio);
	}



}


