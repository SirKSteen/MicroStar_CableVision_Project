package driver;



import controllers.UserController;
import models.User;
import utils.Role;

public class Driver {

	public static void main(String[] args) {
		
		User user = new User("","","","",Role.REPRESENTATIVE);
		UserController controller = new UserController();
		
		controller.addUser(user);

   
	}

}
