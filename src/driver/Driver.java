package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controllers.AuthController;


public class Driver {
	private static final Logger LOG = LogManager.getLogger(Driver.class.getName());
	public static void main(String[] args) {
		
		AuthController ac = new AuthController();
		
		boolean t = ac.updatePassword(1700, "secrets","secret");
		
		
		 //LOG.debug("Debug Message Logged"); 
		LOG.debug("Debug Message Logged"); 
        LOG.fatal("Sample fatal message");
        LOG.info("Info Message Logged");
        LOG.error("Error Message Logged");
        LOG.warn("Test Warn message");
        LOG.trace("TRACE MESSAGE");
	}

}
