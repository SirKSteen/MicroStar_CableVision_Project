package controllers;

import utils.CustomizedException;

import utils.Role;

import models.User;

/*This class will isolate the logic for authentication and updating password.*/
public class AuthController {

	private UserController userController;
	private User user;

	public AuthController() {
		this.userController = new UserController();
		this.user = new User();
	}

	public boolean login(int userId, String password, Role role) throws CustomizedException {

		this.user = this.userController.findById(userId);
		boolean loggedIn = false;
		if (this.user != null) {

			try {
				this.userController.validatePassword(password, this.user.getPassword());

				if (this.user.getRole() == role) {
					loggedIn = true;// user logged in successfully
				} else {
					throw new CustomizedException("User is not registered as " + role);
				}
			} catch (CustomizedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				throw new CustomizedException(e.getMessage());

			}
		} else {
			System.out.println("user not found");
		}

		return loggedIn;
	}

	public boolean updatePassword(int userId, String oldPassword, String newPassword) throws CustomizedException {
		boolean passwordUpdated = false;
		this.user = this.userController.findById(userId);
		if (this.user != null) {

			try {
				// test if old password matches the user current password
				this.userController.validatePassword(oldPassword, this.user.getPassword());

				// if passwords match, then we can change it
				this.user.setPassword(this.userController.generatePasswordHash(newPassword));
				passwordUpdated = true;
				// update user in database
				this.userController.updateUser(this.user);
			} catch (CustomizedException e) {
				// TODO Auto-generated catch block
				throw new CustomizedException(e.getMessage());
			}

		} else {
			System.out.println("user not found");
			throw new CustomizedException("User not found");
		}
		return passwordUpdated;
	}

	public int register(User user) throws CustomizedException {

		int userId = -1;
		try {

			userId = this.userController.createUser(user);
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			throw new CustomizedException(e.getMessage());
		}

		return userId;
	}
}
