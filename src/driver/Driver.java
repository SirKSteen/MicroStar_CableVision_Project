package driver;

import controllers.ComplaintController;
import controllers.ResponseController;
import controllers.UserController;
import models.Account;
import models.Complaint;
import models.Response;
import models.User;
import utils.ComplaintCategory;
import utils.ComplaintType;
import utils.CustomizedException;
import utils.PaymentStatus;
import utils.Role;

import java.sql.Date;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controllers.AccountController;
import controllers.AuthController;

public class Driver {
	private static final Logger LOG = LogManager.getLogger(Driver.class.getName());

	public static void main(String[] args) {

		
		UserController uc = new UserController();
		AccountController ac = new AccountController();
		ComplaintController cc = new ComplaintController();
		ResponseController rc = new ResponseController();
		User user = null;
	
		try {
			Complaint ccc = cc.findById(4);
			for(Response res: rc.getResponsesPerComplaint(3)) {
				System.out.println(res);
			}
			
			;
			
		} catch (CustomizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
