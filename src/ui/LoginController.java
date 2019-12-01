package ui;

import java.io.IOException;

import DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.User;

public class LoginController {

	// Login

	@FXML
	private VBox loginPane;

	@FXML
	private TextField userField;

	@FXML
	private PasswordField passField;

	@FXML
	private Button loginBtn;

	@FXML
	private Text infoText;

	// Link to Create account Pane
	@FXML
	private Hyperlink signUpLink;

	// Create account Pane

	@FXML
	private Pane signUpPane;

	@FXML
	private TextField signUpUserField;
	
	@FXML
	private TextField signUpAddressField;
	
	@FXML
	private TextField signUpNameField;

	@FXML
	private PasswordField signUpPassField;

	@FXML
	private Button signUpBtn;

	// Link to Login Pane
	@FXML
	private Hyperlink loginLink;

	@FXML
	private Text signUpInfoText;

	@FXML
	void onOpenSignUp(ActionEvent event) {
		signUpPane.setVisible(true);
		loginPane.setVisible(false);
	}

	@FXML
	void onRegister(ActionEvent event) {
		String username = signUpUserField.getText();
		String pass = signUpPassField.getText();

		if (username.trim().length() == 0) {
			signUpInfoText.setText("Username can not be empty");
			return;
		}

		UserDAO userDAO = new UserDAO();

		if (userDAO.getByName(username) != null) {
			signUpInfoText.setText("Username already taken");
			return;
		}

		if (pass.trim().length() == 0) {
			signUpInfoText.setText("Password can not be empty");
			return;
		}

		User user = new User();
		user.setUserName(username);
		user.setUserPass(pass);

		userDAO.add(user);
		onBackToLogin(null);
		infoText.setText("Account created successfully.");
	}

	@FXML
	void onBackToLogin(ActionEvent event) {
		signUpPane.setVisible(false);
		loginPane.setVisible(true);
	}

	@FXML
	void onLogin(ActionEvent event) throws IOException {

		String username = userField.getText();
		String pass = passField.getText();

		User user = new UserDAO().getByName(username);

		if (user == null) {
			infoText.setText("Username not found");
			return;
		}

		if (user.getUserPass().equals(pass)) {
			Session.setCurrentUser(user);
//			loadAuctionWindow();
		} else {
			infoText.setText("Incorrect password");
		}

//	}
//
//	
//	void loadAuctionWindow() throws IOException {
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Bid.fxml"));
//		Stage primaryStage = new Stage();
//		Parent root = fxmlLoader.load();
//		Scene auctionScene = new Scene(root, 800, 600);
//		auctionScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//		primaryStage.setScene(auctionScene);
//		primaryStage.setTitle("Auction");
//		primaryStage.setResizable(false);
//		primaryStage.show();
//
//		Main.getStage().close();
//		Main.setStage(primaryStage);
//	}

	}
}
