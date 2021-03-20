package driver;


import controllers.ComplaintController;
import controllers.ResponseController;
import controllers.UserController;
import models.Complaint;
import models.Response;
import models.User;
import utils.ComplaintCategory;
import utils.CustomizedException;

import utils.Role;

public class Driver {

	public static void main(String[] args) {

		User user = new User("","","","",Role.REPRESENTATIVE);
		ResponseController rc = new ResponseController();

		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		UserController uc = new UserController();
		Complaint c = new Complaint(1703, 1702, ComplaintCategory.MILD, "",sqlDate, null);
		ComplaintController cc = new ComplaintController();
		
		Response resp = new Response();
		user = uc.findById(1703);
		try {
			
			resp = rc.findById(1);
		} catch (CustomizedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// new Response(c, null, "response message");
		
		System.out.println(resp);
		
		
	}

}



























