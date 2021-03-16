package driver;

import controllers.AuthController;
import controllers.UserController;
import models.User;
import utils.CustomizedException;
import utils.Role;
import views.RegistrationForm;

public class Driver {

	public static void main(String[] args) {
		
	RegistrationForm rs=	new RegistrationForm();
	
	rs.showForm();
	 
	 
/*	
	User user = new User("","","","",Role.CUSTOMER);
	
	AuthController ac = new AuthController();
	UserController uc = new UserController();
	
	try {
		uc.createUser(user);
	} catch (CustomizedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//ac.register(user);
	
	}*/

}
}