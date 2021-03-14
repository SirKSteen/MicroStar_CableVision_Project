package controllers;

import utils.CustomizedException;
import models.User;
/*This class will isolate the logic for authentication and updating password.*/
public class AuthController {

	private UserController userController;
	private User user;
	
	public AuthController() {
		this.userController = new UserController();
		this.user = new User();
	}
	
	public boolean login(int userId,String password) {
		
	    this.user = this.userController.findById(userId);
	    boolean loggedIn = false;
	    if(this.user != null) {
	    	
	    	try {
	    		this.userController.validatePassword(password, this.user.getPassword());
				loggedIn = true;//user logged in successfully
				System.out.println("user logged in");
			} catch (CustomizedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	    }else {
	    	System.out.println("user not found");
	    }
		
	    return loggedIn;
	}
	
	public boolean updatePassword(int userId,String oldPassword,String newPassword) {
		boolean passwordUpdated = false;
		 this.user = this.userController.findById(userId);
		 if(this.user != null) {
		    	
		    	try {
		    		//test if old password matches the user current password
		    		this.userController.validatePassword(oldPassword, this.user.getPassword());
					
		    		//if passwords match, then we can change it
		    		this.user.setPassword(this.userController.generatePasswordHash(newPassword));
					passwordUpdated=true;
					//update user in database
					this.userController.updateUser(this.user);
				} catch (CustomizedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		    }else {
		    	System.out.println("user not found");
		    }
		return passwordUpdated;
	}
}
