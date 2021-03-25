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

		User user = new User("","","","",Role.REPRESENTATIVE);
		ResponseController rc = new ResponseController();
		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		UserController uc = new UserController();
		ComplaintController cc = new ComplaintController();	
		
//		uc.createUser(user);	
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
		
		
		User u2 = new User(
				"Wally",
				"Banks",
				"bankswall@gmail.com",
				"live321",
				Role.CUSTOMER);
		
		System.out.println("Create User");
//		uc.createUser(u2);
		
		User cust1 = uc.findById(1731);
		
		Complaint c2 = new Complaint(
				cust1,
				null,
				ComplaintCategory.SEVERE,
				"This is a sample complaint for Wally",
				sqlDate,
				ComplaintType.CABLE
				);
		
//		try {
//			cc.addComplaint(c2);
//			System.out.println("Complaint added successfully!");
//		} catch (CustomizedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		ArrayList <Complaint> complaintsResult = new ArrayList <> ();
		
		System.out.println("Complaints retrieved for user id 1724\n");
		complaintsResult = cc.getComplaintsPerUser(1724);
		
		System.out.println(complaintsResult);
	}

}



























