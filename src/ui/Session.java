package ui;

import model.User;

public class Session {

	static User currentUser;

	public static User getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(User currentUser) {
		Session.currentUser = currentUser;
	}
	
}
