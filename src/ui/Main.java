package ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	/**
	 * Current Stage
	 */
	public static Stage currentStage;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		Scene loginScene = null;
		try {
			
			FXMLLoader loader = new FXMLLoader();
			   try {
			 
			      final String address ="D:/Cursuri/An II/Raluca/MagazinElectronice/src/UI/Login2.fxml";
			      final InputStream fxmlStream = new FileInputStream(address);
			      final Parent login =  loader.load(fxmlStream);
			      loginScene = new Scene(login,300,400);
			 
			 
			   } catch (IOException e) {
			      e.printStackTrace();
			   }
			
//			Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
//			URL url = getClass().getResource("/ui/Login.fxml");
//			Parent login = FXMLLoader.load(getClass().getClassLoader().getResource("/ui/Login.fxml"));
//			Parent login = FXMLLoader.load(url);
//			loginScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(loginScene);
			primaryStage.setTitle("Auction");
			primaryStage.setResizable(false);
			primaryStage.show();
			
			currentStage = primaryStage;
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	/**
	 * Returns the current Stage
	 */
	public static Stage getStage(){
		return currentStage;
	}
	
	/**
	 * Sets the current Stage to the stage specified
	 */
	public static void setStage(Stage stage){
		currentStage = stage;
	}
}