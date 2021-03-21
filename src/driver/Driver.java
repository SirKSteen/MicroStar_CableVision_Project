package driver;

import controllers.ComplaintController;
import controllers.ResponseController;
import controllers.UserController;
import models.Complaint;
import models.User;
import utils.ComplaintCategory;
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
		Complaint c = new Complaint(1703, 1702, ComplaintCategory.MILD, "",sqlDate, null);
		ComplaintController cc = new ComplaintController();
		
	}


	}
	
