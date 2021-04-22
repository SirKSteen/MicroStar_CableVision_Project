package driver;

import controllers.UserController;
import models.User;
import server.Server;
import utils.CustomizedException;
import utils.Role;

public class Driver {

	public static void main(String[] args) {
		
		new Server();	
		
		/*
		User u1 = new User(
				"Jack",
				"Jemison",
				"jemija@yahoo.com",
				Role.CUSTOMER,
				"pass123",
				"18769514123"
				);
		UserController uc = new UserController();
		try {
			uc.createUser(u1);
			System.out.println("success!");
			
			System.out.println("Name: "+u1.getFirstName()+" "+u1.getLastName());
			System.out.println("Contact Number: "+u1.getContactNum());
			
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UserController uc = new UserController();
		try {
			User u2 = uc.findById(1743);
			u2.setContactNum("18765123695");
			uc.updateUser(u2);
			System.out.println("User has been updated:\n"+u2);
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}

}
