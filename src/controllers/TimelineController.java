package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ArrayBlockingQueue;

import com.sun.corba.se.spi.activation.Server;

import components.Message;
import components.UserInfo;
import components.Users;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TimelineController {

	StartController start;
	Users users;

	@FXML
	ListView<String> messageView;

	ObservableList<String> messageList;
	List<String> currentUser;

	ArrayBlockingQueue<String> messages = new ArrayBlockingQueue<>(20);


	@FXML
	public void initialize(){
		messageList = FXCollections.observableArrayList();
		//System.out.println(messageList.equals(null));
		new Thread(() -> {
			for (;;) {
				try {
					String msg = messages.take();
					Platform.runLater(() -> {
						Message current = new Message(msg, currentUser.get(0));
						messageList.add(current.toString());
					});
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
			sendTo(users.getCurrentUser(currentUser.get(0)).get(6)  , Integer.parseInt(this.users.getCurrentUser(currentUser.get(0)).get(7)), "Kelsey");
		} catch (NumberFormatException nfe) {
			badNews(String.format("\"%s\" is not an integer", this.users.getCurrentUser(currentUser.get(0)).get(7)));
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

	@FXML
	public void viewProfile(){
		openProfile();
	}

	public void openProfile(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("Profile.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			ProfileController profile = (ProfileController) loader.getController();
			profile.importVariables(start, this);
			profile.setProfile((currentUser.get(2).equals("null"))?"":currentUser.get(2),
					(currentUser.get(5).equals("null"))?"":currentUser.get(5),
							(currentUser.get(4).equals("null"))?"":currentUser.get(4),
									(currentUser.get(3).equals("null"))?"":currentUser.get(3));

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			Image anotherIcon = new Image("https://lh3.ggpht.com/am4rWpEvZqhjEMJoD4Imp-tdKxtQpsa6uel50xRHegrxtIybnDdT8spmvLOH9wPZiIs=w300");
			secondStage.getIcons().add(anotherIcon);
		    secondStage.setTitle(currentUser.get(0));
			secondStage.setScene(scene);
			secondStage.show();


		} catch (Exception exc) {
			Alert r = new Alert(AlertType.NONE, "Cannot view Profile." , ButtonType.OK);
			r.setTitle("ERROR");
			r.showAndWait();
			exc.printStackTrace();
		}

	}

	@FXML
	public void signOut(){
		Alert r = new Alert(AlertType.NONE,
				"You are about to sign out.\nThis will completely close the program. \nDo you wish to continue?" ,
				ButtonType.YES, ButtonType.CANCEL);
		r.setTitle("Sign Out?");

		Optional<ButtonType> result = r.showAndWait();
		if (result.get() == ButtonType.YES){
			System.exit(0);
		} else {
		   r.close();
		}
	}

	public void openEditProfile(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("EditInfo.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			EditProfileController editProfile = (EditProfileController) loader.getController();
			editProfile.importVariables(start, this);
			editProfile.prePopulate((currentUser.get(0).equals("null"))?"":currentUser.get(0),
					(currentUser.get(2).equals("null"))?"":currentUser.get(2),
							(currentUser.get(3).equals("null"))?"":currentUser.get(3),
									(currentUser.get(4).equals("null"))?"":currentUser.get(4), "");

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			Image anotherIcon = new Image("https://lh3.ggpht.com/am4rWpEvZqhjEMJoD4Imp-tdKxtQpsa6uel50xRHegrxtIybnDdT8spmvLOH9wPZiIs=w300");
			secondStage.getIcons().add(anotherIcon);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			Alert r = new Alert(AlertType.NONE, "Cannot view Profile." , ButtonType.OK);
			r.setTitle("ERROR");
			r.showAndWait();
			exc.printStackTrace();
		}
	}
	public void openNewPost(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("Post.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			NewPostController post = (NewPostController) loader.getController();
			post.importVariables(start,this);

			Stage secondStage = new Stage();
			Scene scene = new Scene(root);
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			Alert r = new Alert(AlertType.NONE, "Cannot create a new post." , ButtonType.OK);
			r.setTitle("ERROR");
			r.showAndWait();
			exc.printStackTrace();
		}
	}

	public void importVariables(StartController start, List<String> currentUser) {
		this.start = start;
		this.users = start.getUsers();
		this.currentUser = currentUser;
	}


	/**
	 * Adds Message into listview
	 * @param  		String message
	 * @return      None
	 */
	public void addMessage(String msg) {
		messageList.add(msg);
		messageView.setItems(messageList);

// TODO: When someone logs out, should it close the program or should it take them back to the sign in page?
		// Because you're still technically a server if the program doesn't close and hit the red square
		// It should close the program and call "system.exit(0);" to completely shut down everything in the GUI.
		// This means that Kelsey needs to alter the requirements document.
// TODO: Ask how to make it so you don't have to enter other people's IP addresses
		// There needs to be another GUI that asks the user to enter the IP addresses of the people that they would like
		// to message.
		// Ferrer said that this means Kelsey needs to update the requirements document to literally have a use case
		// that requires that the user obtain IP addresses from the people they want to communicate with
		// and declare that our program finding the other users IP addresses is out of the scope for this assignment.
		// (because it is not possible -- trust me I asked)

		try {
			sendTo("10.253.193.153" , Integer.parseInt(this.users.getCurrentUser(currentUser.get(0)).get(7)), msg);
			sendTo("10.253.199.8" , Integer.parseInt(this.users.getCurrentUser(currentUser.get(0)).get(7)), msg);
		} catch (NumberFormatException nfe) {
			badNews(String.format("\"%s\" is not an integer", this.users.getCurrentUser(currentUser.get(0)).get(7)));
		}


	}

	public List<String> getCurrentUserInfo() {
		return currentUser;
	}
}
