package driver;


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
		
//		uc.createUser(user);
		
		
		
		

		ComplaintController cc = new ComplaintController();	
		try {
			System.out.println("find Complaint");
			Complaint c2 = cc.findById(18);
			System.out.println(c2);
			
			User user2 = uc.findById(1721);
			System.out.println("Update Complaint");
			c2.setEmpID(user2);
			cc.assignTechnician(c2);
		} catch (CustomizedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		
		
		
		
		
		
		
		
		
		
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
		
		
	}

}



























