package driver;

import java.util.ArrayList;

import controllers.AccountController;
import controllers.AuthController;
import controllers.UserController;
import models.Account;
import models.User;
import utils.PaymentStatus;
import utils.Role;

public class Driver {

	public static void main(String[] args) {
		
		AccountController ac = new AccountController();
		UserController uc = new UserController();
		
		User user = new User("Patr","For","pat@emai.com","12345",Role.REPRESENTATIVE);
		Account account = new Account();//(PaymentStatus.CANCELLED,12.5f,user);
		
		account = ac.findById(1);
		
		System.out.println(account);
		//account.setAmt_due(55.6f);
		
	//uc.createUser(user);
	//ac.updateAccount(account);
	
	}

}
