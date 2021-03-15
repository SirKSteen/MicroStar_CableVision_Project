package driver;

import java.sql.Date;
import java.util.ArrayList;


import controllers.AuthController;
import controllers.ComplaintsController;
import controllers.UserController;
import models.Complaints;
import models.User;
import utils.ComplaintsCategory;
import utils.ComplaintsType;
import utils.CustomizedException;
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
		
//		System.out.println(userList);
		
		User result = new User();
		
//		result = uc1.findById(1709);
		
//		System.out.println("\nResults from the User search: \n"+result);
		
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
		
		User user7 = new User("Haary","Jakeson","harryson@gmail.com",
				"passwrd321",Role.CUSTOMER);
		
		UserController uc3 = new UserController();
		
//		uc3.createUser(user7);
		
//		String hashed = user6.getPassword();
//		System.out.println("Password input: "+hashed);
//		
//		String input = "coding123";
//		
//		
//		try {
//			uc3.validatePassword(input, hashed);
//			System.out.println(input+" and "+hashed+" match!");
//		} catch (CustomizedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		
		ComplaintsController cc = new ComplaintsController();
		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		
		Complaints c1 = new Complaints(
				1724,
				1720, 
				ComplaintsCategory.MODERATE,
				"This is a sample complaint.",
				sqlDate,
				ComplaintsType.BROADBAND
				);
	
		Complaints c2 = new Complaints(
				1728,
				1721, 
				ComplaintsCategory.SEVERE,
				"This is another sample complaint.",
				sqlDate,
				ComplaintsType.CABLE
				);
	
		
//		cc.addComplaint(c1);
//		cc.addComplaint(c2);
		
		ArrayList <Complaints> complaintsList = new ArrayList<>();
		complaintsList= cc.getAllComplaints();
		
		System.out.println(complaintsList);
		
		Complaints c10 = new Complaints();
		c10 = cc.findById(13);
		System.out.println("\nComplaint Search results: "+c10);
		
		
		Complaints c3 = new Complaints(
				1728,
				1727, 
				ComplaintsCategory.MODERATE,
				"This is yet another sample complaint.",
				sqlDate,
				ComplaintsType.CABLE
				);
		
		
		Complaints c4 = new Complaints(
				1724,
				1723, 
				ComplaintsCategory.MODERATE,
				"This is yet another another sample complaint.",
				sqlDate,
				ComplaintsType.BROADBAND
				);
		
		
//		cc.addComplaint(c3);
		
//		c4.setComplaintID(14);
//		
//		Complaints complaintUpdate = new Complaints();
//		
//		complaintUpdate = cc.updateComplaints(c4);
//
//		System.out.println("\nUpdate Complaint: \n"+complaintUpdate);
	
		
		Complaints c5 = new Complaints(
				1728,
				1721, 
				ComplaintsCategory.SEVERE,
				"This is going to be another sample complaint.",
				sqlDate,
				ComplaintsType.CABLE
				);
//		cc.addComplaint(c5);
		cc.deleteComplaint(15);
		
		
	}

}





















































