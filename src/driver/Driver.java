package driver;

import controllers.AuthController;
import controllers.UserController;
import models.User;
import utils.CustomizedException;
import utils.Role;
import views.LoginForm;
import views.RegistrationForm;
import views.UpdatePasswordForm;

public class Driver {

	public static void main(String[] args) {
		
	LoginForm rs=	new LoginForm();
	RegistrationForm rf = new RegistrationForm();
	UpdatePasswordForm up = new UpdatePasswordForm();
	
	User user = new User("foo","bar","patri@gmail.com","1234",Role.CUSTOMER);
	
	AuthController ac = new AuthController();
	UserController uc = new UserController();
	//rs.showForm(Role.CUSTOMER);
	up.showForm();
	/*
	 * try { //uc.createUser(user); ac.login(1711, "1234", Role.CUSTOMER);
	 * //ac.updatePassword(1711, "secret", "1234"); } catch (CustomizedException e)
	 * { // TODO Auto-generated catch block e.printStackTrace(); }
	 */
	
	
	}
}