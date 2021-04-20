package driver;

import controllers.ComplaintController;
import controllers.UserController;
import models.Complaint;
import models.User;
import server.Server;
import utils.ComplaintStatus;
import utils.CustomizedException;

public class Driver {
	public static void main(String[] args) {
			
			new Server();
			/*
			UserController uc = new UserController();
			try {
				User u1 = uc.findById(1730);
				u1.setFirstName("Monkey");
				uc.updateUser(u1);
				System.out.println("Completetd!");
			} catch (CustomizedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
			/*
			ComplaintController cc = new ComplaintController();
			try {
				Complaint c1 = cc.findById(36);
				c1.setComplaintStatus(ComplaintStatus.RESOLVED);
				cc.updateComplaints(c1);
				System.out.println("Completetd!");
			} catch (CustomizedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			
	}
}

		
			

	
	


