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


import server.Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
	private static final Logger LOG = LogManager.getLogger(Driver.class.getName());

	public static void main(String[] args) {

//
//		User user = new User("","","","",Role.REPRESENTATIVE);
//		ResponseController rc = new ResponseController();
//		

 		new Server();
 		
		User u1 = new User("Jonathon","Kent","jkent@gmail.com",
				"forever123",Role.CUSTOMER);
		UserController ucc = new UserController();
		
		try {
			ucc.createUser(u1);
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseController rc = new ResponseController();
		
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		UserController uc = new UserController();

//		uc.createUser(user);

		ComplaintController cc = new ComplaintController();	
		
		ArrayList <Complaint> dispList = new ArrayList<> ();
//		try {
//			dispList = cc.getAllComplaints();
//			System.out.println("Printing:\n"+dispList);
//		} catch (CustomizedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		/*
		try {
			Complaint searchC = cc.findById(14);
			System.out.println("Complaint: \n"+searchC);
			
			Response r2 = new Response(
					searchC, 
					sqlDate, 
					"Thanks for everything!!"
					);
			ResponseController rc1 = new ResponseController();
			rc1.addResponse(r2);
			System.out.println("Response added!");
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		ComplaintController cc1 = new ComplaintController();
		ArrayList <Complaint> mc = new ArrayList <> ();
		/*
		try {
			mc = cc1.getAllMildComplaints();
			System.out.println(mc);
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		/*
		try {
			
			Complaint c3 = cc1.viewComplaintAndResponses(14);
			System.out.println(c3);
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		

		
			
		
	}	
	

}

