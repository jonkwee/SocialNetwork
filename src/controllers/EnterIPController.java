package controllers;

import java.util.ArrayList;
import java.util.List;

import components.Users;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

public class EnterIPController {
	@FXML
	TextField friend1;
	@FXML
	TextField friend2;
	@FXML
	TextField friend3;
	@FXML
	TextField friend4;
	@FXML
	Button connect;
	@FXML
	Button cancel;

	StartController start;
	Users users;
	List<String> currentUser;
	ArrayList<String> ips;

	public void importVariables(StartController start, List<String> currentUser) {
		this.start = start;
		this.users = start.getUsers();
		this.currentUser = currentUser;
	}

	@FXML
	public void addUsers(){
		if(!friend1.getText().equals(null)) ips.add(friend1.getText());
		if(!friend2.getText().equals(null)) ips.add(friend2.getText());
		if(!friend3.getText().equals(null)) ips.add(friend3.getText());
		if(!friend4.getText().equals(null)) ips.add(friend4.getText());
		openTimeline();
	}

	public void openTimeline(){
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GuiMain.class.getResource("TimelineVer2.fxml"));
			AnchorPane root = (AnchorPane) loader.load();

			TimelineController timeline = (TimelineController) loader.getController();
			timeline.importVariables(start, currentUser, ips);


			Stage secondStage = new Stage();
			Scene scene = new Scene(root);

			secondStage.setOnCloseRequest(e -> {
			        Platform.exit();
			        System.exit(0);
			   });

			Image anotherIcon = new Image("https://lh3.ggpht.com/am4rWpEvZqhjEMJoD4Imp-tdKxtQpsa6uel50xRHegrxtIybnDdT8spmvLOH9wPZiIs=w300");
            secondStage.getIcons().add(anotherIcon);
			//secondStage.getIcons().add(new Image(imgLink));
		    secondStage.setTitle(currentUser.get(0));
			secondStage.setScene(scene);
			secondStage.show();
		} catch (Exception exc) {
			Alert r = new Alert(AlertType.NONE, "Cannot open the timeline." , ButtonType.OK);
			r.setTitle("ERROR");
			r.showAndWait();
			exc.printStackTrace();
		}

		Stage stage = (Stage) connect.getScene().getWindow();
	    stage.close();
	}

	@FXML
	void close() {
		cancel.getScene().getWindow().hide();
	}
}
