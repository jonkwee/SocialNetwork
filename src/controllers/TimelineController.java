package controllers;

import components.Users;
import javafx.fxml.FXML;

public class TimelineController {
	
	StartController start;
	Users users;
	
	
	@FXML
	public void initialize(){}
	
	
	public void importVariables(StartController start) {
		this.start = start;
		this.users = start.getUsers();
	}
}
