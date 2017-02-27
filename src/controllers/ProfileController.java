package controllers;


import java.time.LocalDate;

import Objects.UserInfo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

public class ProfileController  {
	@FXML
	Button edit;

	@FXML
	TextArea biography;

	@FXML
	ImageView profilePic;

	@FXML
	Label name;

	@FXML
	Label birthday;

	@FXML
	Label email;

	@FXML
	Label phoneNumber;


	UserInfo baertt = new UserInfo("password", "Taylor Baer", LocalDate.now(), "281-740-3405" , "baertt@hendrix.edu"
			/*"Hello! My name is Taylor. This is my profile."*/);
					//, getProfilePic() );



	@FXML
	public void initialize(){
		name.setText(baertt.getName());
		birthday.setText("Birthday:" + " " + baertt.getBday());
		email.setText("Email: " + " " + baertt.getEmail());
		phoneNumber.setText("Phone Number: " + " " + baertt.getPhone());
		biography.setText(baertt.getBiography());
		//profilePic.setImage(getProfilePic());
	}




}


