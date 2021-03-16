package driver;

import java.sql.Date;
import java.util.ArrayList;


import controllers.AuthController;
import controllers.ComplaintController;
import controllers.UserController;
import models.Complaint;
import models.User;
import utils.ComplaintCategory;
import utils.ComplaintType;
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
	
		
		ComplaintController cc = new ComplaintController();
		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		
		Complaint c1 = new Complaint(
				1724,
				1720, 
				ComplaintCategory.MODERATE,
				"This is a sample complaint.",
				sqlDate,
				ComplaintType.BROADBAND
				);
	
		Complaint c2 = new Complaint(
				1728,
				1721, 
				ComplaintCategory.SEVERE,
				"This is another sample complaint.",
				sqlDate,
				ComplaintType.CABLE
				);
	
		
//		cc.addComplaint(c1);
//		cc.addComplaint(c2);
		
		ArrayList <Complaint> complaintsList = new ArrayList<>();
		complaintsList= cc.getAllComplaints();
		
		System.out.println(complaintsList);
		
		Complaint c10 = new Complaint();
		c10 = cc.findById(13);
		System.out.println("\nComplaint Search results: "+c10);
		
		
		Complaint c3 = new Complaint(
				1728,
				1727, 
				ComplaintCategory.MODERATE,
				"This is yet another sample complaint.",
				sqlDate,
				ComplaintType.CABLE
				);
		
		
		Complaint c4 = new Complaint(
				1724,
				1723, 
				ComplaintCategory.MODERATE,
				"This is yet another another sample complaint.",
				sqlDate,
				ComplaintType.BROADBAND
				);
		
		
//		cc.addComplaint(c3);
		
//		c4.setComplaintID(14);
//		
//		Complaints complaintUpdate = new Complaints();
//		
//		complaintUpdate = cc.updateComplaints(c4);
//
//		System.out.println("\nUpdate Complaint: \n"+complaintUpdate);
	
		
		Complaint c5 = new Complaint(
				1728,
				1721, 
				ComplaintCategory.SEVERE,
				"This is going to be another sample complaint.",
				sqlDate,
				ComplaintType.CABLE
				);
//		cc.addComplaint(c5);
		cc.deleteComplaint(16);
		
		
	}

}





















































