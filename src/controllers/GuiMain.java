package controllers;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GuiMain extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(GuiMain.class.getResource("Start.fxml"));
		Pane root = (Pane) loader.load();

		Scene scene = new Scene(root, 355, 190);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args){
		Thread serverThread = new Thread(() -> {
			try{ 
				Server s = new Server(8880);
				s.listen();
			} catch(IOException e){
				 e.printStackTrace();
			}
			});
		serverThread.start();
		launch(args);
	}
}


