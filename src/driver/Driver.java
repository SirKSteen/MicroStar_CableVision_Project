package driver;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

import utils.ComplaintCategory;
import utils.ComplaintType;
import utils.CustomizedException;

import utils.Role;

public class Driver {

	public static void main(String[] args) {

		User user = new User("","","","",Role.REPRESENTATIVE);
		UserController controller = new UserController();
		
//		controller.addUser(user);

		
		Date d = new Date(0);
		User user = new User("Pat","For", "ema", "", Role.CUSTOMER);
		
		UserController uc = new UserController();
		uc.createUser(user);
		Complaint c = new Complaint(1703, 1702, ComplaintCategory.MILD, "",d, null);
		ComplaintController cc = new ComplaintController();
		
		int id = cc.addComplaint(c);
		
		//c = cc.findById(id);
		Response resp = new Response(c, null, "response message");
		
		
		ResponseController rc = new ResponseController();
		
		
		rc.addResponse(resp);
		java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
		
		
	}

}



























