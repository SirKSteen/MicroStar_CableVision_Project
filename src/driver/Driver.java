package driver;


import java.util.ArrayList;

import controllers.ComplaintController;
import controllers.ResponseController;
import controllers.UserController;
import models.Complaint;
import models.Response;
import models.User;
import utils.ComplaintCategory;
import utils.ComplaintType;
import utils.CustomizedException;

import utils.Role;

public class Driver {

	public static void main(String[] args) {
//
//		User user = new User("","","","",Role.REPRESENTATIVE);
//		ResponseController rc = new ResponseController();
//		
//		try {
//			System.out.println("find Complaint");
//			Complaint c2 = cc.findById(18);
//			System.out.println(c2);
//			
//			User user2 = uc.findById(1721);
//			System.out.println("Update Complaint");
//			c2.setEmpID(user2);
//			cc.assignTechnician(c2);
//		} catch (CustomizedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} 
//		
//		
	
//		Response resp = new Response();
//		user = uc.findById(1703);
//		try {
//			
//			resp = rc.findById(1);
//		} catch (CustomizedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		// new Response(c, null, "response message");
		
//		System.out.println(resp);
		
		
//		User u2 = new User(
//				"Wally",
//				"Banks",
//				"bankswall@gmail.com",
//				"live321",
//				Role.CUSTOMER);
//		
//		System.out.println("Create User");
////		uc.createUser(u2);
//		
//		User cust1 = uc.findById(1731);
//		
		
//		User u1 = new User ("Max","Hunter","mhunt@gmail.com",
//				"pass000",Role.CUSTOMER);
		UserController uc = new UserController();
		
//		uc.createUser(u1);	
		
//		In order to create a complaint, do a find by id and store that user
//		into a new user and use that as the custId for complaint
		
		User custID = uc.findById(1732);
		System.out.println("Found: "+custID);
		
		ComplaintController cc = new ComplaintController();	
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		
		Complaint c1 = new Complaint(
				custID,
				null,
				ComplaintCategory.MODERATE,
				"This is a sample complaint for Max",
				sqlDate,
				ComplaintType.BROADBAND
				);
//		
//		try {
//			cc.addComplaint(c1);
//			System.out.println("Complaint added successfully!");
//		} catch (CustomizedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		ArrayList <Complaint> dispList = new ArrayList<> ();
		try {
			dispList = cc.getAllComplaints();
			System.out.println("Printing:\n"+dispList);
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		ArrayList <Complaint> complaintsResult = new ArrayList <> ();
//		
//		System.out.println("Complaints retrieved for user id 1724\n");
//		complaintsResult = cc.getComplaintsPerUser(1724);
//		
//		System.out.println(complaintsResult);
		

		
			
		
		
	}

}



























