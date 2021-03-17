package driver;

import java.util.ArrayList;

import controllers.AuthController;
import models.Account;
import models.User;
import utils.PaymentStatus;
import utils.Role;


public class Driver {

	public static void main(String[] args) {
		
		AuthController ac = new AuthController();
		
		
	
		
		new User("John", "Brown", "eih.gmail.com", "73802", Role.CUSTOMER);
		
		ArrayList<Account> accountsList = new ArrayList<Account>();
		
		accountsList.add(new Account(PaymentStatus.SUCCESS, 57283.3, User));
		
		
		boolean t = ac.updatePassword(1700, "secrets","secret");
		
		
		
		System.out.println(t);
	}

}
