package driver;

import server.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
	private static final Logger LOG = LogManager.getLogger(Driver.class.getName());

	public static void main(String[] args) {


 		new Server();
		

	}

}
