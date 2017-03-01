package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

import components.Users;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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
	@FXML
	TextField host;
	@FXML
	TextField port;

	StartController start;
	Users users;

	ArrayBlockingQueue<String> messages = new ArrayBlockingQueue<>(20);

	@FXML
	public void initialize(){
		new Thread(() -> {
			for (;;) {
				try {
					String msg = messages.take();
				} catch (Exception e) {
					badNews(e.getMessage());
				}

			}
		}).start();
	}

	void badNews(String what) {
		Alert badNum = new Alert(AlertType.ERROR);
		badNum.setContentText(what);
		badNum.show();
	}

	void send() {
		try {
			sendTo(host.getText(), Integer.parseInt(this.port.getText()), null);
		} catch (NumberFormatException nfe) {
			badNews(String.format("\"%s\" is not an integer", this.port.getText()));
		}
	}

	void sendTo(String host, int port, String message) {
		new Thread(() -> {
			try {
				Socket target = new Socket(host, port);
				send(target, message);
				receive(target);
				target.close();
			} catch (Exception e) {
				Platform.runLater(() -> badNews(e.getMessage()));
				e.printStackTrace();
			}
		}).start();
	}

	void send(Socket target, String message) throws IOException {
		PrintWriter sockout = new PrintWriter(target.getOutputStream());
		sockout.println(message);
		sockout.flush();
	}

	void receive(Socket target) throws IOException {
		BufferedReader sockin = new BufferedReader(new InputStreamReader(target.getInputStream()));
		while (!sockin.ready()) {}
		while (sockin.ready()) {
			try {
				String msg = sockin.readLine();
				messages.put(msg);
			} catch (Exception e) {
				Platform.runLater(() -> badNews(e.getMessage()));
				e.printStackTrace();
			}
		}
	}

	public void getStart(StartController start){
		this.start = start;
		this.users = start.getUsers();
	}

	@FXML
	public void accountCreation() throws IOException{
		String currentUsername = username.getText();
		String currentPassword = password.getText();
		String currentConfirmPass = confirmPass.getText();
		String currentHost = host.getText();
		String currentPort = port.getText();
		if (requiredNotFilled(currentUsername, currentPassword, currentConfirmPass, currentHost, currentPort)) {
			prompt.setText("Please fill in the required fields!"); 
		} else if (users.checkUserName(currentUsername)) {
			prompt.setText("This username has already been chosen. Please choose another one.");
		} else if (!checkPassWordConfirmation(currentPassword, currentConfirmPass)) {
			prompt.setText("Password and Confirm Password fields are different.");
		} else {
			System.out.println(users);
			System.out.println(username.getText());
			System.out.println(password.getText());
			System.out.println(name.getText());
			System.out.println(phone.getText());
			System.out.println(email.getText());
			System.out.println(birthday.getValue());
			users.add(username.getText(), password.getText(), name.getText(), phone.getText(),
					  email.getText(), birthday.getValue());
			openSignIn();
		}
	}

	/**
	 * Checks if required values are filled
	 * @param  		String username, password, confirmPassword
	 * @return      boolean (if at least one default value is not filled, returns True)
	 */
	public boolean requiredNotFilled(String username, String password, String confirmPassword, String host, String port) {
		return (username.equals("")) || (password.equals("")) || (confirmPassword.equals("") || (host.equals("")) || (port.equals("")));
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

		Stage stage = (Stage) createAccount.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void close() {
		cancel.getScene().getWindow().hide();
	}
}