package driver;


import java.util.ArrayList;

import controllers.ComplaintController;
import controllers.ResponseController;
import controllers.UserController;
import models.Complaint;
import models.User;
import utils.ComplaintCategory;
import utils.ComplaintType;
import utils.CustomizedException;

import utils.Role;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controllers.AuthController;



public class Driver {
	private static final Logger LOG = LogManager.getLogger(Driver.class.getName());
	public static void main(String[] args) {


		AuthController ac = new AuthController();
		
		try {
			boolean t = ac.updatePassword(1700, "secrets","secret");
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOG.debug("Debug Message Logged"); 
        LOG.fatal("Sample fatal message");
        LOG.info("Info Message Logged");
        LOG.error("Error Message Logged");
        LOG.warn("Test Warn message");
        LOG.trace("TRACE MESSAGE");
        

		User user = new User("","","","",Role.REPRESENTATIVE);
		ResponseController rc = new ResponseController();
		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		UserController uc = new UserController();

		

//		uc.createUser(user);
		
		
		
		

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
		
		
//
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
//		User cust1;
//		try {
//			cust1 = uc.findById(1731);
//		} catch (CustomizedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		Complaint c2 = new Complaint(
//				cust1,
//				null,
//				ComplaintCategory.SEVERE,
//				"This is a sample complaint for Wally",
//				sqlDate,
//				ComplaintType.CABLE
//				);
		
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




