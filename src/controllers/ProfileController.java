package controllers;


import java.time.LocalDate;

import javafx.event.Event;
//import Objects.UserInfo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import components.UserInfo;
import components.Users;

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
	
	StartController start;
	Users users;


	UserInfo example = new UserInfo("password", "First Last", LocalDate.now(), "123-456-7890" , "example@hendrix.edu");
			//"Hello! My name is Taylor. This is my profile.");
					//, getProfilePic() );



	@FXML
	public void initialize(){
		biography.setEditable(false);
		name.setText(example.getName());
		birthday.setText("Birthday:" + " " + example.getBday());
		email.setText("Email: " + " " + example.getEmail());
		phoneNumber.setText("Phone Number: " + " " + example.getPhone());
		biography.setText(example.getBiography());
		//profilePic.setImage(getProfilePic());
	}
	
	public void openEdit(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("EditInfo.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			EditProfileController editProfile = (EditProfileController) loader.getController();
			editProfile.importVariables(start);

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}

	@FXML
	public void editProfile(){
		openEdit();
	}
	
	public void importVariables(StartController start) {
		this.start = start;
		this.users = start.getUsers();
	}


}


