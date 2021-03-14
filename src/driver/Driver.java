package driver;

import java.util.ArrayList;

import controllers.AuthController;
import controllers.UserController;
import models.User;
import utils.InvalidPasswordException;
import utils.Role;

public class Driver {

	public static void main(String[] args) {
		
//		AuthController ac = new AuthController();
//		
//		boolean t = ac.updatePassword(1700, "secrets","secret");
//		
//		System.out.println(t);
		
		User user1 = new User("Jacob","Smith","jsmith@gmail.com",
				"password",Role.CUSTOMER);
		
		User user2 = new User("Peter","Parker","parker.peter@gmail.com",
				"secret1",Role.TECHNICIAN);
		
		User user3 = new User("Bruce","Wayne","wayne_bruce@yahoo.com",
				"wayne001",Role.REPRESENTATIVE);
		
		UserController uc1 = new UserController();
		
//		uc1.createUser(user1);
//		uc1.createUser(user2);
//		uc1.createUser(user3);
		
		ArrayList <User> userList = new ArrayList<>();
		userList= uc1.getAllUsers();
		
		System.out.println(userList);
		
		User result = new User();
		
//		result = uc1.findById(1709);
		
		System.out.println("\nResults from the User search: \n"+result);
		
		User userUpdate = new User();
		User user5 = new User("Jacob","Forrestor","jforres@gmail.com",
				"password",Role.TECHNICIAN);
		
//		user5.setUserId(1720);
		
//		uc1.createUser(user5);
//		System.out.println("User created!\n");
		
//		userUpdate = uc1.updateUser(user5);
//		
//		System.out.println("\nUpdate User: \n"+userUpdate);
	
//		uc1.deleteUser(0);
		
		
		User user6 = new User("Brian","OConner","brcnner@gmail.com",
				"coding123",Role.REPRESENTATIVE);
		
		UserController uc3 = new UserController();
		
		uc3.createUser(user6);
		
		String hashed = user6.getPassword();
		System.out.println("Password input: "+hashed);
		
		String input = "coding123";
		
		
		try {
			uc3.validatePassword(input, hashed);
			System.out.println(input+" and "+hashed+" match!");
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	
	}

}





















































