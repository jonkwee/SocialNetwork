package controllers;

import components.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CreateAccountController {
	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	PasswordField confirmPass;
	@FXML
	TextField name;
	@FXML
	DatePicker birthday;
	@FXML
	TextField phone;
	@FXML
	TextField email;
	@FXML
	Button createAccount;
	@FXML
	Button cancel;
	@FXML
	Label prompt;

	StartController start;
	Users users;

	@FXML
	public void initialize(){}

	public void getStart(StartController start){
		this.start = start;
		this.users = start.getUsers();
	}

	@FXML
	public void accountCreation(){
		String currentUsername = username.getText();
		String currentPassword = password.getText();
		String currentConfirmPass = confirmPass.getText();
		if (requiredNotFilled(currentUsername, currentPassword, currentConfirmPass)) {
			prompt.setText("Please fill in the required fields!"); 
		} else if (users.checkUserName(currentUsername)) {
			prompt.setText("This username has already been chosen. Please choose another one.");
		} else if (!checkPassWordConfirmation(currentPassword, currentConfirmPass)) {
			prompt.setText("Password and Confirm Password fields are different.");
		} else {
			users.add(username.getText(), password.getText(), name.getText(), birthday.getValue(),
						phone.getText(), email.getText());
			openSignIn();
		}
	}
	

	
	/**
	 * Checks if required values are filled
	 * @param  		String username, password, confirmPassword
	 * @return      boolean (if at least one default value is not filled, returns True)
	 */
	public boolean requiredNotFilled(String username, String password, String confirmPassword) {
		return (username.equals("")) || (password.equals("")) || (confirmPassword.equals(""));
	}
	
	/**
	 * Checks if password and confirm password are the same.
	 * @param  		String password, confirmPassword
	 * @return      boolean (returns True if they are the same)
	 */
	public boolean checkPassWordConfirmation(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}


	public void openSignIn(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("SignIn.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			
			SignInController signIn = (SignInController) loader.getController();
			signIn.importVariables(start);

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	@FXML
	void close() {
		cancel.getScene().getWindow().hide();
	}
}