package driver;

import controllers.AuthController;

public class Driver {

	public static void main(String[] args) {
		
		AuthController ac = new AuthController();
		
		boolean t = ac.updatePassword(1700, "secrets","secret");
		
		System.out.println(t);
	}

}
